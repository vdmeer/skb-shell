package de.vandermeer.shell.commands;

import org.apache.commons.lang3.Validate;

import de.vandermeer.skb.interfaces.shell.CmdBase;
import de.vandermeer.skb.interfaces.shell.CmdCategory;

public abstract class AbstractCmd implements CmdBase {

	protected final transient String name;

	protected final transient String displayName;

	protected final transient String description;

	protected final transient CmdCategory category;

	protected Object longDescription;

	protected AbstractCmd(final String name, final String displayName, final String description, final CmdCategory category){
		Validate.notBlank(name);
		Validate.notBlank(displayName);
		Validate.notBlank(description);
		Validate.notNull(category);

		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.category = category;
	}

	public void setLongDescription(Object longDescription){
		this.longDescription = longDescription;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Object getLongDescription() {
		return this.longDescription;
	}

	@Override
	public CmdCategory getCategory() {
		return this.category;
	}

}
