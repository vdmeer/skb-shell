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

package de.vandermeer.shell.commands.simple;

import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.examples.StandardExampleRunner;
import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;

/**
 * A command that runs an example in a shell using the standard example API.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_ExampleRunner extends AbstractSimpleCmd {

	/** The example to run. */
	protected final transient StandardExampleAsCmd example;

	/**
	 * Creates a new command
	 * @param example the example to run
	 * @param category the category.
	 */
	public Cmd_ExampleRunner(StandardExampleAsCmd example, Sh_CmdCategory category) {
		super(
				example.getName(),
				example.getDisplayName(),
				example.getDescription(),
				category
		);
		this.example = example;
	}

	@Override
	public Object getLongDescription(){
		return this.example.getLongDescription();
	}

	@Override
	public int executeCommand() {
		StandardExampleRunner ser = new StandardExampleRunner(){};
		System.out.println();
		System.out.println();
		System.out.println();
		ser.runExampleWithCode(this.example);
		return 0;
	}

}
