package services;

import beans.BookingData;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandParams;
import utils.CommandEnum;
import utils.UserTypeEnum;

@SuppressWarnings({"unchecked","rawtypes"})
public class BookingService {

	private BookingData bookingData;
	
	public BookingData getBookingData() {
		return bookingData;
	}
	public void setBookingData(BookingData bookingData) {
		this.bookingData = bookingData;
	}
	
	public BookingService() {
		this.bookingData = new BookingData();
	}
	
	public boolean processCommand(UserTypeEnum userType, String input) {
		CommandEnum command = CommandEnum.fromString(input.split(" ")[0]);
		
		if (command == null) {
			System.out.println("Invalid command. Please enter a valid command from the list below:");
			if (userType.equals(UserTypeEnum.ADMIN)) {
				System.out.println("- Setup <Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes (optional)>");
				System.out.println("- View <Show Number>");
			}
			System.out.println("- Availability <Show Number> ");
			System.out.println("- Book <Show Number> <Phone#> <Comma separated list of seats>");
			System.out.println("- Cancel <Ticket#> <Phone#>");
			return false;
		} else if (command.isAdminRequired() && userType.equals(UserTypeEnum.BUYER)) {
			System.out.println("This command requires Admin user");
			return false;
		} else {
			CommandHelper commandHelper = command.getCommandHelper();
			if (commandHelper.validate(input)) {
				CommandParams params = (CommandParams) command.getCommandHelper().resolveParams(input);
				return command.getCommandHelper().execute(bookingData, params);
			} else {
				return false;
			}
		}		
	}
	
}
