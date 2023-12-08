package helpers.interfaces;

import beans.BookingData;

@SuppressWarnings("hiding")
public interface CommandHelper<CommandParams> {
	
	boolean validate(String input);
	CommandParams resolveParams(String input);
	boolean execute(BookingData bookingData, CommandParams params);
	
}
