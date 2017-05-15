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

import de.vandermeer.skb.interfaces.shell.CmdArgument;

/**
 * Base for a complex command argument.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractArgument<T> implements CmdArgument<T> {

	/** The argument name. */
	protected final transient String name;

	/** The argument display name. */
	protected final transient String displayName;

	/** The argument description. */
	protected final transient String description;

	/** Flag for required arguments. */
	protected final transient boolean isRequired;

	/** An optional default value. */
	protected final transient T defaultValue;

	/** The value of the argument read from command line. */
	protected T commandValue;

	/**
	 * Creates a new argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name, must not be blank
	 * @param description argument short description, must not be blank
	 * @param isRequired flag for being required
	 * @param defaultValue an optional default value
	 */
	protected AbstractArgument(final String name, final String displayName, final String description, final boolean isRequired, final T defaultValue) {
		Validate.notBlank(name);
		Validate.notBlank(displayName);
		Validate.notBlank(description);
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.defaultValue = defaultValue;
		this.isRequired = isRequired;
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
	public boolean argIsRequired() {
		return this.isRequired;
	}

	@Override
	public T getCmdValue() {
		return this.commandValue;
	}

	@Override
	public T getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public void resetCmdValue() {
		this.commandValue = null;
	}

}
