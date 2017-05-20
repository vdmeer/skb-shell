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

import de.vandermeer.skb.interfaces.shell.Sh_CommandMultiSet;
import de.vandermeer.skb.interfaces.shell.IsMultiShellMT;

/**
 * Standard simple shell for a a command multi-set running in its own thread.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class SkbMultiShell_MT extends SkbMultiShell implements IsMultiShellMT {

	/** The current shell thread. */
	protected Thread thread;

	/** The notify object for the thread. */
	protected Object notifyObject;

	/**
	 * Creates a new multi-threaded shell for a command multi-set with all commands and basic shell informations: name, display name, version, description 
	 * @param multiSet the underlying multi-set
	 */
	public SkbMultiShell_MT(Sh_CommandMultiSet multiSet) {
		super(multiSet);
	}

	/**
	 * Creates a new multi-set shell.
	 * @param name the shell (and multi-set) name, must not be blank
	 * @param displayName the shell (and multi-set) display name, must not be blank
	 * @param version the shell (and multi-set) version, must not be blank
	 * @param description the shell (and multi-set) description, must not be blank
	 */
	public SkbMultiShell_MT(final String name, final String displayName, final String version, final String description){
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
