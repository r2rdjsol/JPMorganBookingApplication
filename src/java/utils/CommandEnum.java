package utils;

import helpers.AvailabilityCommandHelper;
import helpers.BookCommandHelper;
import helpers.CancelCommandHelper;
import helpers.SetupCommandHelper;
import helpers.ViewCommandHelper;
import helpers.interfaces.CommandHelper;

@SuppressWarnings("rawtypes")
public enum CommandEnum {
	SETUP ("Setup", new SetupCommandHelper(), true),
	VIEW ("View", new ViewCommandHelper(), true),
	AVAILABILITY ("Availability", new AvailabilityCommandHelper(), false),
	BOOK ("Book", new BookCommandHelper(), false),
	CANCEL ("Cancel", new CancelCommandHelper(), false);
	
	private String command;
	private CommandHelper commandHelper;
	private boolean adminRequired;
	
	private CommandEnum(String command, CommandHelper commandHelper, boolean adminRequired) {
		this.command = command;
		this.commandHelper = commandHelper;
		this.adminRequired = adminRequired;
	}
	
	public static CommandEnum fromString(String command) {
		if (command.equalsIgnoreCase("Setup")) {
			return SETUP;
		} else if (command.equalsIgnoreCase("View")) {
			return VIEW;
		} else if (command.equalsIgnoreCase("Availability")) {
			return AVAILABILITY;
		} else if (command.equalsIgnoreCase("Book")) {
			return BOOK;
		} else if (command.equalsIgnoreCase("Cancel")) {
			return CANCEL;
		} else {
			return null;
		}
	}
	
	public String toString() {
		return command;
	}
	
	public CommandHelper getCommandHelper() {
		return commandHelper;
	}
	
	public boolean isAdminRequired() {
		return adminRequired;
	}
	
}
