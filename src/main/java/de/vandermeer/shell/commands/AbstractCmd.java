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

package de.vandermeer.shell.commands;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CmdBase;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

/**
 * Base for a command.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractCmd implements CmdBase {

	/** Command name. */
	protected final transient String name;

	/** Command display name. */
	protected final transient String displayName;

	/** Command short description. */
	protected final transient String description;

	/** Command category. */
	protected final transient CmdCategory category;

	/** An optional long description. */
	protected Object longDescription;

	/**
	 * Creates a new command.
	 * @param name the command name, must not be blank
	 * @param displayName the command display name, must not be blank
	 * @param description the command description, must not be blank
	 * @param category a category, must not be null
	 */
	protected AbstractCmd(final String name, final String displayName, final String description, final CmdCategory category){
		Validate.notBlank(name);
		Validate.notBlank(displayName);
		Validate.notBlank(description);
		Validate.notNull(category);

		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.category = category;
	}

	/**
	 * Sets the long description.
	 * @param longDescription new long description, null if not required
	 */
	public void setLongDescription(Object longDescription){
		this.longDescription = longDescription;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Object getLongDescription() {
		return this.longDescription;
	}

	@Override
	public CmdCategory getCategory() {
		return this.category;
	}

}
