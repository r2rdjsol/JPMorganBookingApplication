package helpers;

import java.util.Date;
import java.util.Set;

import beans.BookingData;
import beans.Show;
import beans.Ticket;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandValidator;
import utils.DataErrors;

public class BookCommandHelper implements CommandHelper<BookCommandParams> {

	private CommandValidator commandValidator;
	
	public BookCommandHelper() {
		commandValidator = new BookCommandValidator();
	}

	@Override
	public boolean validate(String input) {
		return commandValidator.validate(input);
	}
	
	@Override
	public BookCommandParams resolveParams(String input) {
		String[] params = input.split(" ");
		return new BookCommandParams(params[1], params[2], params[3]);
	}

	@Override
	public boolean execute(BookingData bookingData, BookCommandParams params) {
		Show show = bookingData.getShow(params.getShowNumber());

		if (show == null) {
			System.out.println(String.format(DataErrors.SHOW_DOES_NOT_EXIST, params.getShowNumber()));
			return false;
		}
		
		if (show.isPhoneNumberExisting(params.getPhoneNumber())) {
			System.out.println(String.format(DataErrors.BOOKING_EXISTS, params.getPhoneNumber(), params.getShowNumber()));
			return false;
		}			
			
		Set<String> unavailableSeats = show.checkUnavailableSeats(params.getSeats());
		if (!unavailableSeats.isEmpty()) {
			System.out.println(String.format(DataErrors.SEATS_UNAVAILABLE, params.getShowNumber(), String.join(",", unavailableSeats)));
			return false;
		}
		
		int lastTicketNumber = bookingData.getLastTicketNumber();
		Ticket ticket = new Ticket(++lastTicketNumber, params.getShowNumber(), params.getPhoneNumber(), params.getSeats(), new Date());
		bookingData.getShow(params.getShowNumber()).addTicket(params.getPhoneNumber(), ticket.getTicketNumber());
		bookingData.getShow(params.getShowNumber()).removeAvailableSeats(params.getSeats());
		bookingData.addTicket(ticket);
		bookingData.incrementLastTicketNumber();
		return true;
	}

}
