package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class SimpleH extends AbstractSimpleCmd {

	public SimpleH(CmdCategory category) {
		super(
				"h",
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
