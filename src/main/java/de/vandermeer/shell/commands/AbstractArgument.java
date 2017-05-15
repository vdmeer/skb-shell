package de.vandermeer.shell.commands;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CmdArgument;

public abstract class AbstractArgument<T> implements CmdArgument<T> {

	protected final transient String name;

	protected final transient String displayName;

	protected final transient String description;

	protected final transient boolean isRequired;

	protected final transient T defaultValue;

	protected T commandValue;

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
