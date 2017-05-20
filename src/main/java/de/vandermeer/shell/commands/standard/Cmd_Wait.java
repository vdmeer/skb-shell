/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.shell.commands.standard;

import de.vandermeer.shell.commands.AbstractTypedCmdInteger;
import de.vandermeer.skb.interfaces.MessageType;
import de.vandermeer.skb.interfaces.console.MessageConsole;
import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;

/**
 * Command `wait`.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_Wait extends AbstractTypedCmdInteger {

	/**
	 * Creates a new command.
	 * @param category the category
	 * @param defaultValue default wait in milliseconds
	 */
	public Cmd_Wait(Sh_CmdCategory category, Integer defaultValue) {
		super(
				"wait",
				"Wait",
				"shell waits for <MS> milliseconds before accepting the next command",
				category,
				"MS",
				"time to wait in milliseconds",
				true,
				defaultValue
		);
		this.setLongDescription("Simply stops the shell and any command execution for the given amount of milli seconds. This command can be used when the shell executes a script to slow down command execution.");
	}

	@Override
	public int executeCommand() {
		if(this.getValue()==null){
			MessageConsole.con(MessageType.ERROR, "wait value was null");
			return -3;
		}
		if(this.getValue()<1){
			MessageConsole.con(MessageType.ERROR, "wait value was less than 1");
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
