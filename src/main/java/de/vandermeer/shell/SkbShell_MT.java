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

import de.vandermeer.skb.interfaces.shell.Sh_CommandSet;
import de.vandermeer.skb.interfaces.shell.IsSetShellMT;

/**
 * Standard simple shell for a single command set running in its own thread.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class SkbShell_MT extends SkbShell implements IsSetShellMT {

	/** The current shell thread. */
	protected Thread thread;

	/** The notify object for the thread. */
	protected Object notifyObject;

	/**
	 * Creates a new multi-threaded shell.
	 * @param cmdSet the command set with all commands and basic shell informations: name, display name, version, description
	 */
	public SkbShell_MT(Sh_CommandSet cmdSet) {
		super(cmdSet);
	}

	/**
	 * Creates a new shell.
	 * @param name the shell (and set) name, must not be blank
	 * @param displayName the shell (and set) display name, must not be blank
	 * @param version the shell (and set) version, must not be blank
	 * @param description the shell (and set) description, must not be blank
	 */
	public SkbShell_MT(final String name, final String displayName, final String version, final String description){
		super(name, displayName, version, description);
	}

	@Override
	public void setCurrentThread(Thread thread) {
		this.thread = thread;
	}

	@Override
	public Thread getCurrentThread() {
		return this.thread;
	}

	@Override
	public Object getNotifyObject() {
		return this.notifyObject;
	}

	@Override
	public void setNotifyObject(Object obj) {
		this.notifyObject = obj;
	}

}
