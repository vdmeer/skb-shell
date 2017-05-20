/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.shell.commands.standard;

import java.io.File;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import de.vandermeer.shell.commands.AbstractLongTypedArg;
import de.vandermeer.shell.commands.AbstractLongTypedCmd;
import de.vandermeer.skb.interfaces.MessageType;
import de.vandermeer.skb.interfaces.console.MessageConsole;
import de.vandermeer.skb.interfaces.fidibus.FileSystemFilters;
import de.vandermeer.skb.interfaces.fidibus.directories.APIOWalker;
import de.vandermeer.skb.interfaces.fidibus.files.FileSource;
import de.vandermeer.skb.interfaces.fidibus.files.StringFileLoader;
import de.vandermeer.skb.interfaces.render.DoesRender;
import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;
import de.vandermeer.skb.interfaces.shell.IsSetShell;
import de.vandermeer.skb.interfaces.shell.Sh_LongTypedArgument;

/**
 * Command `script`.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_Script extends AbstractLongTypedCmd {

	/** Extension for script files. */
	public static final String SCRIPT_FILE_EXTENSION = "ssc";

	/** A documentation comment in a script, similar to a Javadoc comments. */
	public static final String SCRIPT_DOC_COMMENT = "//**";

	/** The action argument. */
	public static final Sh_LongTypedArgument<String> action = AbstractLongTypedArg.asString("action", "Action", "The requested action for script, one of: ls, info, run");

	/** The action-arg argument. */
	public static final Sh_LongTypedArgument<String> actionArg = AbstractLongTypedArg.asString("action-arg", "Action argument", "The argument for the script action: <directory> for ls, <filename> for run and info");

	/** A shell to run commands in. */
	private final IsSetShell shell;

	/** Last script run by the shell. */
	protected String lastScript;

	/**
	 * Creates a new command.
	 * @param category the command category, must not be null
	 * @param shell a shell to run a command, must not be null
	 */
	public Cmd_Script(Sh_CmdCategory category, IsSetShell shell) {
		super(
				"script",
				"Script",
				"collection of actions for scripts, e.g.: list, info, run",
				category,
				new Sh_LongTypedArgument<?>[]{action, actionArg}
		);
		Validate.notNull(shell);
		this.shell = shell;
	}

	@Override
	public int executeCommand() {
		switch(action.getValue()){
			case "ls":
				return this.doLs(actionArg.getValue());
			case "info":
				return this.doInfo(actionArg.getValue());
			case "run":
				return this.doRun(actionArg.getValue());
			default:
				throw new IllegalStateException("unknown action <" + action.getValue() + "> in command <" + this.getName() + ">");
		}
	}

	/**
	 * Lists all scripts in a directory.
	 * @param dirname the directory name
	 * @return 0 on success, -1 on error
	 */
	protected int doLs(String dirname){
		APIOWalker walker = APIOWalker.create(
				dirname,
				DirectoryFileFilter.INSTANCE,
				FileSystemFilters.WILDECARD(SCRIPT_FILE_EXTENSION)
		);
		if(walker.hasErrors()){
			for(DoesRender error : walker.getErrorSet().getMessages()){
				MessageConsole.con(MessageType.ERROR, error);
			}
			return -1;
		}
		for(FileSource source : walker.read()){
			source.validateSource();
			MessageConsole.con(MessageType.INFO, "script file - <{}{}{}>", source.fnPath(dirname), File.separator, source.fnBasename());
		}
		return 0;
	}

	/**
	 * Prints information about a script if available.
	 * @param filename the script filename
	 * @return 0 on success, -1 on error
	 */
	protected int doInfo(String filename){
		filename = (filename.endsWith(SCRIPT_FILE_EXTENSION))?filename:filename + "." + SCRIPT_FILE_EXTENSION;
		StringFileLoader loader = StringFileLoader.create(filename);
		if(loader.hasErrors()){
			for(DoesRender error : loader.getErrorSet().getMessages()){
				MessageConsole.con(MessageType.ERROR, error);
			}
			return -1;
		}

		for(String s : loader.read()){
			if(s.startsWith(SCRIPT_DOC_COMMENT)){
				MessageConsole.con(MessageType.INFO, StringUtils.substringAfter(s, SCRIPT_DOC_COMMENT));
			}
		}
		return 0;
	}

	/**
	 * Runs a script.
	 * @param filename the script filename
	 * @return 0 on success, -1 on error
	 */
	protected int doRun(String filename){
		if(filename.equals(".") && this.lastScript!=null){
			filename = this.lastScript;
		}
		filename = (filename.endsWith(SCRIPT_FILE_EXTENSION))?filename:filename + "." + SCRIPT_FILE_EXTENSION;
		StringFileLoader loader = StringFileLoader.create(filename);
		if(loader.hasErrors()){
			for(DoesRender error : loader.getErrorSet().getMessages()){
				MessageConsole.con(MessageType.ERROR, error);
			}
			return -1;
		}

		for(String string : loader.read()){
			if(!string.startsWith(SCRIPT_DOC_COMMENT) && !StringUtils.isBlank(string)){
				MessageConsole.con(MessageType.TRACE, ".");
				this.shell.runCommand(string);
			}
		}
		this.lastScript = filename;//TODO not used yet
		return 0;
	}
}
