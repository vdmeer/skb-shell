package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class SimpleBye extends AbstractSimpleCmd {

	public SimpleBye(CmdCategory category) {
		super(
				"bye",
				"Bye",
				"Terminates the shell",
				category
		);
	}

	@Override
	public int executeCommand() {
		return 2;
	}

}
