package de.vandermeer.shell.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.vandermeer.skb.interfaces.messages.errors.IsError;
import de.vandermeer.skb.interfaces.messages.errors.Templates_CliOptions;

public class ArgumentIntegerList extends AbstractArgument<List<Integer>> {

	/**
	 * Creates a new `integer list` argument.
	 * @param name the argument name, must not be blank
	 * @param displayName the argument display name for help, must not be blank
	 * @param description the argument description, must not be blank
	 * @param isRequired a flag for required arguments
	 * @param defaultValue a default value, null if none required
	 */
	public ArgumentIntegerList(String name, String displayName, String description, boolean isRequired, List<Integer> defaultValue) {
		super(name, displayName, description, isRequired, defaultValue);
	}

	@Override
	public IsError setCmdValue(String value) {
		if(StringUtils.isBlank(value) && this.argIsRequired()){
			return Templates_CliOptions.MANDATORY_ARG_NULL.getError("Command", this.getDisplayName(), this.getName());
		}
		else if(value==null){
			this.commandValue = null;
			return null;
		}
		else{
			String[] array = StringUtils.split(value, ',');
			if(array!=null){
				this.commandValue = new ArrayList<>();
				for(String str : array){
					try{
						this.commandValue.add(Integer.valueOf(str));
					}
					catch(NumberFormatException nfex){
						nfex.printStackTrace();//TODO
					}
				}
			}
		}
		return null;
	}

}
