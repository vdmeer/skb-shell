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

import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.asciitable.AT_ColumnWidthCalculator;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.asciithemes.TA_Grid;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.asciithemes.u8.U8_Grids;
import de.vandermeer.shell.commands.AbstractTypedCmdString;
import de.vandermeer.skb.interfaces.MessageType;
import de.vandermeer.skb.interfaces.console.MessageConsole;
import de.vandermeer.skb.interfaces.shell.CmdBase;
import de.vandermeer.skb.interfaces.shell.CmdCategory;
import de.vandermeer.skb.interfaces.shell.CommandSet;
import de.vandermeer.skb.interfaces.shell.ComplexArgument;
import de.vandermeer.skb.interfaces.shell.ComplexCmd;
import de.vandermeer.skb.interfaces.shell.LongTypedArgument;
import de.vandermeer.skb.interfaces.shell.LongTypedCmd;
import de.vandermeer.skb.interfaces.shell.TypedCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * Command `help` that prints general or command specific help in table form.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.2.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.1
 */
public class Cmd_HelpSetTable extends AbstractTypedCmdString {

	/** The grid for the table. */
	protected final transient TA_Grid grid;

	/** The grid theme for the table. */
	protected final transient TA_GridThemes theme;

	/** Table width calculated. */
	protected final transient AT_ColumnWidthCalculator cwc;

	/** Command set for help. */
	protected final transient CommandSet set;

	/**
	 * Creates a new help command with defaults for CWC, grid, and theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 */
	public Cmd_HelpSetTable(final CmdCategory category, final CommandSet set) {
		this(category, set, new CWC_FixedWidth().add(14).add(20).add(70), U8_Grids.borderStrongDoubleLight(), TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command with defaults for grid and theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 3 column table
	 */
	public Cmd_HelpSetTable(final CmdCategory category, final CommandSet set, final AT_ColumnWidthCalculator cwc) {
		this(category, set, cwc, U8_Grids.borderStrongDoubleLight(), TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command with defaults for grid.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 3 column table
	 * @param theme a theme for the table, must not be null
	 */
	public Cmd_HelpSetTable(final CmdCategory category, final CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_GridThemes theme) {
		this(category, set, cwc, U8_Grids.borderStrongDoubleLight(), theme);
	}

	/**
	 * Creates a new help command with defaults for theme.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 3 column table
	 * @param grid a grid for the table, must not be null
	 */
	public Cmd_HelpSetTable(final CmdCategory category, final CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_Grid grid) {
		this(category, set, cwc, grid, TA_GridThemes.LATEX);
	}

	/**
	 * Creates a new help command.
	 * @param category the command category, must not be null
	 * @param set the command set this command is operating on, must not be null
	 * @param cwc a width calculator, must not be null, must allow for a 3 column table
	 * @param grid a grid for the table, must not be null
	 * @param theme a theme for the table, must not be null
	 */
	public Cmd_HelpSetTable(final CmdCategory category, final CommandSet set, final AT_ColumnWidthCalculator cwc, final TA_Grid grid, final TA_GridThemes theme) {
		super(
				"help",
				"Help",
				"Prints help information on all commands in a set or a specific command, in table form",
				category,
				"CMD",
				"an optional command to get detailed help for",
				false,
				null
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
		return (this.getValue()==null)?this.generalHelp():this.specificHelp();
	}

	/**
	 * Help on a specific command.
	 * @return 0 on success
	 */
	protected int specificHelp(){
		String command = this.getValue();
		Validate.validState(this.set.hasCommand(command), "unknown command for help: <" + command + ">");

		CmdBase cmdBase = set.getCommand(command);
		MessageConsole.con(MessageType.INFO, "");
		MessageConsole.con(MessageType.INFO, "{} - {}", cmdBase.getDisplayName(), cmdBase.getDescription());
		MessageConsole.con(MessageType.INFO, "command:  {}", cmdBase.getName());
		MessageConsole.con(MessageType.INFO, "category: {} ({} - {})", cmdBase.getCategory().getName(), cmdBase.getCategory().getDisplayName(), cmdBase.getCategory().getDescription());

		if(set.getSimpleMap().keySet().contains(command)){
			MessageConsole.con(MessageType.INFO, "argument: none");
		}
		else if(set.getTypedMap().keySet().contains(command)){
			TypedCmd<?> tc = set.getTypedMap().get(command);
			MessageConsole.con(MessageType.INFO, "argument: {} - {}", tc.getArgumentName(), tc.getArgumentDecription());
			MessageConsole.con(MessageType.INFO, "- type:          {}", tc.valueType());
			MessageConsole.con(MessageType.INFO, "- default value: {}", tc.getDefaultValue());
		}
		else if(set.getLongTypedMap().keySet().contains(command)){
			LongTypedCmd ltc = set.getLongTypedMap().get(command);
			MessageConsole.con(MessageType.INFO, "arguments:");
			for(LongTypedArgument<?> arg : ltc.getArguments()){
				MessageConsole.con(MessageType.INFO, "- {} - {}, type {}", arg.getName(), arg.getDescription(), arg.valueType());
			}
		}
		else if(set.getComplexMap().keySet().contains(command)){
			ComplexCmd cc = set.getComplexMap().get(command);
			MessageConsole.con(MessageType.INFO, "arguments:");
			for(ComplexArgument<?> arg : cc.getArguments()){
				MessageConsole.con(MessageType.INFO, "- {} - {}, type {}, default value {}", arg.getName(), arg.getDescription(), arg.valueType(), arg.getDefaultValue());
			}
		}

		if(cmdBase.getLongDescription()!=null){
			MessageConsole.con(MessageType.INFO, "");
			AsciiParagraph ap = new AsciiParagraph();
			ap.addText(cmdBase.getLongDescription());
			ap.getContext().setAlignment(TextAlignment.JUSTIFIED_LEFT);
			ap.getContext().setTextLeftRightMargin(5);
			ap.getContext().setWidth(80);
			MessageConsole.con(MessageType.INFO, ap.render());
		}
		MessageConsole.con(MessageType.INFO, "");

		return 0;
	}

	/**
	 * General help on all commands if no value is given.
	 * @return 0 on success
	 */
	protected int generalHelp(){
		AsciiTable at = new AsciiTable();
		at.getContext().setGrid(this.grid);
		at.getContext().setGridTheme(this.theme);
		at.getRenderer().setCWC(this.cwc);

		TreeMap<String, List<CmdBase>> catMap = new TreeMap<>();
		for(CmdBase cmd : this.set.sortedList()){
			if(!catMap.containsKey(cmd.getCategory().getName())){
				catMap.put(cmd.getCategory().getName(), new ArrayList<>());
			}
			catMap.get(cmd.getCategory().getName()).add(cmd);
		}

		at.addStrongRule();
		at.addRow("Category", "Command", "Description");
		at.addStrongRule();
		int count = 0;
		for(Entry<String, List<CmdBase>> entry : catMap.entrySet()){
			if(count>0){
				at.addRule();
			}
			String cat = entry.getKey();
			for(int i=0; i<entry.getValue().size(); i++){
				if(i==0){
					at.addRow(cat, entry.getValue().get(i).getName(), entry.getValue().get(i).getDescription());
				}
				else{
					at.addRow("", entry.getValue().get(i).getName(), entry.getValue().get(i).getDescription());
				}
			}
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
