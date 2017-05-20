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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.asciitable.AT_ColumnWidthCalculator;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.asciithemes.TA_Grid;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.shell.commands.AbstractSimpleCmd;
import de.vandermeer.skb.interfaces.MessageType;
import de.vandermeer.skb.interfaces.console.MessageConsole;
import de.vandermeer.skb.interfaces.shell.Sh_CmdBase;
import de.vandermeer.skb.interfaces.shell.Sh_CmdCategory;
import de.vandermeer.skb.interfaces.shell.Sh_CommandSet;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * Command help using `help` providing simple lists of commands per category.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_HelpSetList extends AbstractSimpleCmd {

	/** The grid for the table. */
	protected final transient TA_Grid grid;

	/** The grid theme for the table. */
	protected final transient TA_GridThemes theme;

	/** Table width calculated. */
	protected final transient AT_ColumnWidthCalculator cwc;

	/** Command set for help. */
	protected final transient Sh_CommandSet set;

	/**
	 * Creates a new help command with defaults for CWC, grid, and theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 */
	public Cmd_HelpSetList(final Sh_CmdCategory category, final Sh_CommandSet set) {
		this(category, set, new CWC_FixedWidth().add(15).add(70), U8_Grids.borderStrongDoubleLight(), TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command with defaults for grid and theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 2 column table
	 */
	public Cmd_HelpSetList(final Sh_CmdCategory category, final Sh_CommandSet set, final AT_ColumnWidthCalculator cwc) {
		this(category, set, cwc, U8_Grids.borderStrongDoubleLight(), TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command with defaults for grid.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 2 column table
	 * @param theme a theme for the table, must not be null
	 */
	public Cmd_HelpSetList(final Sh_CmdCategory category, final Sh_CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_GridThemes theme) {
		this(category, set, cwc, U8_Grids.borderStrongDoubleLight(), theme);
	}

	/**
	 * Creates a new help command with defaults for theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 2 column table
	 * @param grid a grid for the table, must not be null
	 */
	public Cmd_HelpSetList(final Sh_CmdCategory category, final Sh_CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_Grid grid) {
		this(category, set, cwc, grid, TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 2 column table
	 * @param grid a grid for the table, must not be null
	 * @param theme a theme for the table, must not be null
	 */
	public Cmd_HelpSetList(final Sh_CmdCategory category, final Sh_CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_Grid grid, final TA_GridThemes theme) {
		super(
				"help-ls",
				"Help as list",
				"Prints help information as list of commands per category in a table",
				category
		);

		Validate.notNull(set);
		Validate.notNull(cwc);
		Validate.notNull(grid);
		Validate.notNull(theme);
		this.set = set;
		this.cwc = cwc;
		this.grid = grid;
		this.theme = theme;
	}

	@Override
	public int executeCommand() {
		AsciiTable at = new AsciiTable();
		at.getContext().setGrid(this.grid);
		at.getContext().setGridTheme(this.theme);
		at.getRenderer().setCWC(this.cwc);

		TreeMap<String, List<String>> catMap = new TreeMap<>();
		for(Sh_CmdBase cmd : this.set.sortedList()){
			if(!catMap.containsKey(cmd.getCategory().getName())){
				catMap.put(cmd.getCategory().getName(), new ArrayList<>());
			}
			catMap.get(cmd.getCategory().getName()).add(cmd.getName());
		}
		at.addStrongRule();
		at.addRow("Category", "Commands");
		at.addStrongRule();
		int count = 0;
		for(Entry<String, List<String>> entry : catMap.entrySet()){
			if(count>0){
				at.addRule();
			}
			at.addRow(entry.getKey(), new StrBuilder().appendWithSeparators(entry.getValue(), ", ").build()).setTextAlignment(TextAlignment.LEFT);
			count++;
		}
		at.addStrongRule();

		MessageConsole.con(MessageType.INFO, "");
		MessageConsole.con(MessageType.INFO, "{} - {}", this.set.getDisplayName(), this.set.getDescription());
		MessageConsole.con(MessageType.INFO, "");
		MessageConsole.con(MessageType.INFO, at.render(80));
		MessageConsole.con(MessageType.INFO, "");
		return 0;
	}

}
