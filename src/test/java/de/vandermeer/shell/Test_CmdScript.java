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

package de.vandermeer.shell;

import org.junit.Test;

import de.vandermeer.shell.commands.standard.Cmd_Exit;
import de.vandermeer.shell.commands.standard.Cmd_HelpSetList;
import de.vandermeer.shell.commands.standard.Cmd_HelpSetTable;
import de.vandermeer.shell.commands.standard.Cmd_Script;
import de.vandermeer.shell.commands.standard.Cmd_Wait;
import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;

/**
 * Simple interactive test for `script` command.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Test_CmdScript {

	@Test
	public void test_Command(){
		Sh_CmdCategory cat = Sh_CmdCategory.create("test", "Test Category", "for tests");
		SkbShell shell = new SkbShell("shell", "Test Shell", "1.0", "testing commands");

		shell.addCommand(new Cmd_HelpSetTable(cat, shell.getCommands()));
		shell.addCommand(new Cmd_HelpSetList(cat, shell.getCommands()));
		shell.addCommand(new Cmd_Exit(cat));
		shell.addCommand(new Cmd_Wait(cat, null));
		shell.addCommand(new Cmd_Script(cat, shell));
//		shell.runShell();
	}
}
