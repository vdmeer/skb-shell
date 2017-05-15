package de.vandermeer.shell.commands;

import de.vandermeer.skb.interfaces.shell.CmdCategory;
import de.vandermeer.skb.interfaces.shell.SimpleCmd;

public abstract class AbstractSimpleCmd extends AbstractCmd implements SimpleCmd {

	protected AbstractSimpleCmd(String name, String displayName, String description, CmdCategory category) {
		super(name, displayName, description, category);
	}

}
