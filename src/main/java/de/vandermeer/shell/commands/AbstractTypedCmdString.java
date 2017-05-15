package de.vandermeer.shell.commands;

import org.apache.commons.lang3.StringUtils;

import de.vandermeer.skb.interfaces.messages.errors.IsError;
import de.vandermeer.skb.interfaces.messages.errors.Templates_CliOptions;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public abstract class AbstractTypedCmdString extends AbstractTypedCmd<String> {

	/**
	 * Creates a new typed command of type `string`.
	 * @param name the command name, must not be blank
	 * @param displayName the command display name, must not be blank
	 * @param description the command description, must not be blank
	 * @param category the command category, must not be null
	 * @param argumentName a display name for the argument, must not be blank
	 * @param argumentDescription a description of the argument, must not be blank
	 * @param defaultValue command default value, null or blank if not required
	 */
	protected AbstractTypedCmdString(String name, String displayName, String description, CmdCategory category, String argumentName, String argumentDescription, String defaultValue) {
		super(name, displayName, description, category, argumentName, argumentDescription, defaultValue);
	}

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

}
