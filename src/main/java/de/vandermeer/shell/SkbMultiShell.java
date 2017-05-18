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

import de.vandermeer.skb.interfaces.shell.CommandMultiSet;
import de.vandermeer.skb.interfaces.shell.CommandSet;
import de.vandermeer.skb.interfaces.shell.IsMultiShell;

/**
 * Standard simple shell for a a command multi-set.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class SkbMultiShell implements IsMultiShell {

	/** Flag indicating if the shell is running. */
	protected boolean isRunning;

	/** The command multi-set of the shell. */
	protected final transient CommandMultiSet multiSet;

	/**
	 * Creates a new multi-set shell.
	 * @param multiSet the multi-set for the shell with all commands and basic shell informations: name, display name, version, description
	 */
	public SkbMultiShell(CommandMultiSet multiSet){
		Validate.notNull(multiSet);
		this.multiSet = multiSet;
	}

	/**
	 * Creates a new multi-set shell.
	 * @param name the shell (and multi-set) name, must not be blank
	 * @param displayName the shell (and multi-set) display name, must not be blank
	 * @param version the shell (and multi-set) version, must not be blank
	 * @param description the shell (and multi-set) description, must not be blank
	 */
	public SkbMultiShell(final String name, final String displayName, final String version, final String description){
		this.multiSet = CommandMultiSet.create(name, displayName, version, description);
	}

	@Override
	public void cleanup() {
		//not needed here yet
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
	public CommandMultiSet getCommands() {
		return this.multiSet;
	}

	/**
	 * Adds a new command to the shell.
	 * @param set the command set, must not be blank and must exist
	 * @param command the new command, must not be null
	 * @return self to allow chaining
	 * @throws IllegalStateException if command was already added or in case of any other error
	 */
	public SkbMultiShell addCommand(String set, Object command){
		Validate.notBlank(set);
		Validate.validState(this.multiSet.hasSet(set), "no existing set for <" + set + ">");
		this.multiSet.getMultiMap().get(set).addCommand(command);
		return this;
	}

	/**
	 * Adds a new command set to the shell.
	 * @param name the set name, must not be blank
	 * @param displayName the set display name, must not be blank
	 * @param version the set version, must not be blank
	 * @param description the set description, must not be blank
	 * @return self to allow chaining
	 */
	public SkbMultiShell addSet(final String name, final String displayName, final String version, final String description){
		CommandSet set = CommandSet.create(name, displayName, version, description);
		this.multiSet.getMultiMap().put(set.getName(), set);
		return this;
	}
}
