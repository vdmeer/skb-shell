package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class SimpleExit extends AbstractSimpleCmd {

	public SimpleExit(CmdCategory category) {
		super(
				"help",
				"Help",
				"Terminates the shell",
				category
		);
	}

	@Override
	public int executeCommand() {
		return 2;
	}

}
