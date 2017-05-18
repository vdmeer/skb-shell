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

package de.vandermeer.shell;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CommandSet;
import de.vandermeer.skb.interfaces.shell.IsSetShell;

/**
 * Standard simple shell for a single command set.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class SkbShell implements IsSetShell {

	/** Flag indicating if the shell is running. */
	protected boolean isRunning;

	/** The command set of the shell. */
	protected final transient CommandSet cmdSet;

	/**
	 * Creates a new shell.
	 * @param cmdSet the command set with all commands and basic shell informations: name, display name, version, description
	 */
	public SkbShell(CommandSet cmdSet){
		Validate.notNull(cmdSet);
		this.cmdSet = cmdSet;
	}

	/**
	 * Creates a new shell.
	 * @param name the shell (and set) name, must not be blank
	 * @param displayName the shell (and set) display name, must not be blank
	 * @param version the shell (and set) version, must not be blank
	 * @param description the shell (and set) description, must not be blank
	 */
	public SkbShell(final String name, final String displayName, final String version, final String description){
		this.cmdSet = CommandSet.create(name, displayName, version, description);
	}

	@Override
	public void cleanup() {
		//not needed yet
	}

	@Override
	public boolean isRunning() {
		return this.isRunning;
	}

	@Override
	public void setIsRunning(boolean running) {
		this.isRunning = running;
	}

	@Override
	public CommandSet getCommands() {
		return this.cmdSet;
	}

	/**
	 * Adds a new command to the shell.
	 * @param command the new command, must not be null
	 * @return self to allow chaining
	 * @throws IllegalStateException if command was already added or in case of any other error
	 */
	public SkbShell addCommand(Object command){
		this.cmdSet.addCommand(command);
		return this;
	}

}
