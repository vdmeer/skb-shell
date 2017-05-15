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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import de.vandermeer.skb.interfaces.messages.errors.IsError;
import de.vandermeer.skb.interfaces.messages.errors.Templates_CliOptions;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

/**
 * A typed command as boolean.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractTypedCmdBoolean extends AbstractTypedCmd<Boolean> {

	/**
	 * Creates a new typed command of type `boolean`.
	 * @param name the command name, must not be blank
	 * @param displayName the command display name, must not be blank
	 * @param description the command description, must not be blank
	 * @param category the command category, must not be null
	 * @param argumentName a display name for the argument, must not be blank
	 * @param argumentDescription a description of the argument, must not be blank
	 * @param defaultValue command default value, null or blank if not required
	 */
	protected AbstractTypedCmdBoolean(String name, String displayName, String description, CmdCategory category, String argumentName, String argumentDescription, Boolean defaultValue) {
		super(name, displayName, description, category, argumentName, argumentDescription, defaultValue);
	}

	@Override
	public IsError setCmdValue(String value) {
		if(StringUtils.isBlank(value)){
			return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
		}
		else{
			this.commandValue = BooleanUtils.toBooleanObject(value);
		}
		return null;
	}

}
