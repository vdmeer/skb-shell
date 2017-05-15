package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class SimpleQM extends AbstractSimpleCmd {

	public SimpleQM(CmdCategory category) {
		super(
				"?",
				"Help",
				"Prints help information for the shell",
				category
		);
	}

	@Override
	public int executeCommand() {
		// TODO Auto-generated method stub
		return 0;
	}

}
