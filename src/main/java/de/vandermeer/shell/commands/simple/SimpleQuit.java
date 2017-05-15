package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class SimpleQuit extends AbstractSimpleCmd {

	public SimpleQuit(CmdCategory category) {
		super(
				"quit",
				"Quit",
				"Terminates the shell",
				category
		);
	}

	@Override
	public int executeCommand() {
		return 2;
	}

}
