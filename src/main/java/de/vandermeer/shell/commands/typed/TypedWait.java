package de.vandermeer.shell.commands.typed;

import de.vandermeer.shell.commands.AbstractTypedCmdInteger;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public class TypedWait extends AbstractTypedCmdInteger {

	public TypedWait(CmdCategory category, Integer defaultValue) {
		super(
				"wait",
				"Wait",
				"shell waits for <MS> milliseconds before accepting the next command",
				category,
				"MS",
				"time to wait in milliseconds",
				defaultValue
		);
		this.setLongDescription("Simply stops the shell and any command execution for the given amount of milli seconds. This command can be used when the shell executes a script to slow down command execution.");
	}

	@Override
	public int executeCommand() {
		if(this.getValue()==null){
			//TODO exception
			return -3;
		}
		if(this.getValue()<1){
			//TODO exception
			return -2;
		}
		try{
			Thread.sleep(this.getValue());
		}
		catch (InterruptedException e) {
			throw new IllegalStateException("interrupted in wait: " + e.getMessage());
		}
		return 0;
	}

}
