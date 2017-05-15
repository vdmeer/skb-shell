package de.vandermeer.shell.commands;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CmdArgument;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public abstract class AbstractComplexCmd extends AbstractCmd implements de.vandermeer.skb.interfaces.shell.ComplexCmd {

	protected final transient CmdArgument<?>[] arguments;

	protected AbstractComplexCmd(String name, String displayName, String description, CmdCategory category, CmdArgument<?>[] arguments) {
		super(name, displayName, description, category);

		Validate.notEmpty(arguments);
		this.arguments = arguments;
	}

	@Override
	public CmdArgument<?>[] getArguments() {
		return this.arguments;
	}

}
