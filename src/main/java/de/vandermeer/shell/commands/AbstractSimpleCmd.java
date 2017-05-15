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

package de.vandermeer.shell.commands;

import de.vandermeer.skb.interfaces.shell.CmdCategory;
import de.vandermeer.skb.interfaces.shell.SimpleCmd;

/**
 * A simple command.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public abstract class AbstractSimpleCmd extends AbstractCmd implements SimpleCmd {

	/**
	 * Creates a new simple command.
	 * @param name the command name, must not be blank
	 * @param displayName the command display name, must not be blank
	 * @param description the short description, must not be blank
	 * @param category a category, must not be null
	 */
	protected AbstractSimpleCmd(String name, String displayName, String description, CmdCategory category) {
		super(name, displayName, description, category);
	}

}
