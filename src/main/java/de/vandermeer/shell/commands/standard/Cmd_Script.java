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

import de.vandermeer.shell.commands.AbstractLongTypedArg;
import de.vandermeer.shell.commands.AbstractLongTypedCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;
import de.vandermeer.skb.interfaces.shell.LongTypedArgument;

/**
 * Command `script`.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_Script extends AbstractLongTypedCmd {

	/** The action argument. */
	public static final LongTypedArgument<String> action = AbstractLongTypedArg.asString("action", "Action", "The requested action for script");

	/** The action-arg argument. */
	public static final LongTypedArgument<String> actionArg = AbstractLongTypedArg.asString("action-arg", "Action argument", "The argument for the script action, depends on the action");

	/**
	 * Creates a new command.
	 * @param category the command category, must not be null
	 */
	public Cmd_Script(CmdCategory category) {
		super(
				"script",
				"Script",
				"collection of actions for scripts, e.g.: list, info, run",
				category,
				new LongTypedArgument<?>[]{action, actionArg}
		);
	}

	@Override
	public int executeCommand() {
		switch(action.getValue()){
			case "ls":
				this.doLs(actionArg.getValue());
				break;
			case "info":
				this.doInfo(actionArg.getValue());
				break;
			case "run":
				this.doRun(actionArg.getValue());
				break;
			default:
				throw new IllegalStateException("unknown action <" + action.getValue() + "> in command <" + this.getName() + ">");
		}
		return 0;
	}

	/**
	 * Lists all scripts in a directory
	 * @param dirname the directory name
	 */
	protected void doLs(String dirname){
		System.out.println("ls: " + dirname);//TODO
	}

	/**
	 * Prints information about a script if available.
	 * @param filename the script filename
	 */
	protected void doInfo(String filename){
		System.out.println("info: " + filename);//TODO
	}

	/**
	 * Runs a script.
	 * @param filename the script filename
	 */
	protected void doRun(String filename){
		System.out.println("run: " + filename);//TODO
	}
}
