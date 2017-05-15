package de.vandermeer.shell.commands;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CmdCategory;
import de.vandermeer.skb.interfaces.shell.TypedCmd;

public abstract class AbstractTypedCmd<T> extends AbstractCmd implements TypedCmd<T> {

	protected final transient String argumentName;

	protected final transient String argumentDescription;

	protected final transient T defaultValue;

	protected T commandValue;

	protected AbstractTypedCmd(final String name, final String displayName, final String description, final CmdCategory category, final String argumentName, final String argumentDescription, final T defaultValue) {
		super(name, displayName, description, category);

		Validate.notBlank(argumentName);
		Validate.notBlank(argumentDescription);
		this.argumentName = argumentName;
		this.argumentDescription = argumentDescription;
		this.defaultValue = defaultValue;
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

}
