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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.messages.errors.IsError;
import de.vandermeer.skb.interfaces.messages.errors.Templates_CliOptions;
import de.vandermeer.skb.interfaces.shell.Sh_LongTypedArgument;
import de.vandermeer.skb.interfaces.transformers.Object_To_Target;

/**
 * Base for a complex command argument with factory methods for various types.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractLongTypedArg<T> implements Sh_LongTypedArgument<T> {

	/**
	 * Creates a new `boolean` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<Boolean> asBoolean(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<Boolean>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				this.commandValue = Object_To_Target.convert(value, Boolean.class);
				if(this.commandValue==null){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				return null;
			}

			@Override
			public String valueType() {
				return "Boolean";
			}
		};
	}

	/**
	 * Creates a new `character` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<Character> asCharacter(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<Character>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				this.commandValue = Object_To_Target.convert(value, Character.class);
				if(this.commandValue==null){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				return null;
			}

			@Override
			public String valueType() {
				return "Character";
			}
		};
	}

	/**
	 * Creates a new `double` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<Double> asDouble(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<Double>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				this.commandValue = Object_To_Target.convert(value, Double.class);
				if(this.commandValue==null){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				return null;
			}

			@Override
			public String valueType() {
				return "Double";
			}
		};
	}

	/**
	 * Creates a new `integer` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<Integer> asInteger(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<Integer>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				this.commandValue = Object_To_Target.convert(value, Integer.class);
				if(this.commandValue==null){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				return null;
			}

			@Override
			public String valueType() {
				return "Integer";
			}
		};
	}

	/**
	 * Creates a new `string` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<String> asString(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<String>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				if(StringUtils.isBlank(value)){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				else{
					this.commandValue = value;
				}
				return null;
			}

			@Override
			public String valueType() {
				return "String";
			}
		};
	}

	/**
	 * Creates a new `string list` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<List<String>> asStringList(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<List<String>>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				if(StringUtils.isBlank(value)){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				else{
					String[] array = StringUtils.split(value, ',');
					if(array!=null){
						this.commandValue = new ArrayList<>();
						for(String str : array){
							this.commandValue.add(str);
						}
					}
				}
				return null;
			}

			@Override
			public String valueType() {
				return "List<String>";
			}
		};
	}

	/**
	 * Creates a new `integer list` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @return new argument
	 */
	public static Sh_LongTypedArgument<List<Integer>> asIntegerList(final String name, final String displayName, final String description){
		return new AbstractLongTypedArg<List<Integer>>(name, displayName, description) {
			@Override
			public IsError setCmdValue(String value) {
				if(StringUtils.isBlank(value)){
					return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
				}
				else{
					String[] array = StringUtils.split(value, ',');
					if(array!=null){
						this.commandValue = new ArrayList<>();
						for(String str : array){
							try{
								this.commandValue.add(Integer.valueOf(str));
							}
							catch(NumberFormatException nfex){
								nfex.printStackTrace();//TODO
							}
						}
					}
				}
				return null;
			}

			@Override
			public String valueType() {
				return "List<Integer>";
			}
		};
	}

	/** The argument name. */
	protected final transient String name;

	/** The argument display name. */
	protected final transient String displayName;

	/** The argument description. */
	protected final transient String description;

	/** The value of the argument read from command line. */
	protected T commandValue;

	/**
	 * Creates a new argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name, must not be blank
	 * @param description argument short description, must not be blank
	 */
	protected AbstractLongTypedArg(final String name, final String displayName, final String description) {
		Validate.notBlank(name);
		Validate.notBlank(displayName);
		Validate.notBlank(description);
		this.name = name;
		this.displayName = displayName;
		this.description = description;
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
	public T getCmdValue() {
		return this.commandValue;
	}

	@Override
	public void resetCmdValue() {
		this.commandValue = null;
	}

}
