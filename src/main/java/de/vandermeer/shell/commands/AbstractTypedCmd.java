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

import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;
import de.vandermeer.skb.interfaces.shell.Sh_TypedCmd;

/**
 * A typed command.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractTypedCmd<T> extends AbstractCmd implements Sh_TypedCmd<T> {

	/** Argument name. */
	protected final transient String argumentName;

	/** Argument description. */
	protected final transient String argumentDescription;

	/** Argument default value. */
	protected final transient T defaultValue;

	/** Argument value from command line. */
	protected T commandValue;

	/** Flag for required argument. */
	protected final boolean argRequired;

	/**
	 * Creates a new typed command.
	 * @param name the command name, must not be blank
	 * @param displayName the display name, must not be blank
	 * @param description the short description, must not be blank
	 * @param category a category, must not be null
	 * @param argumentName a name for the argument, must not be blank
	 * @param argumentDescription a description for the argument, must not be blank
	 * @param argRequired true if argument is required, false otherwise
	 * @param defaultValue a default value, null if not required
	 */
	protected AbstractTypedCmd(final String name, final String displayName, final String description, final Sh_CmdCategory category, final String argumentName, final String argumentDescription, final boolean argRequired, final T defaultValue) {
		super(name, displayName, description, category);

		Validate.notBlank(argumentName);
		Validate.notBlank(argumentDescription);
		this.argumentName = argumentName;
		this.argumentDescription = argumentDescription;
		this.defaultValue = defaultValue;
		this.argRequired = argRequired;
	}

	@Override
	public String getArgumentDecription() {
		return this.argumentDescription;
	}

	@Override
	public String getArgumentName() {
		return this.argumentName;
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

	@Override
	public boolean argIsRequired() {
		return this.argRequired;
	}

}
