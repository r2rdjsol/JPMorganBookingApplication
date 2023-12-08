package helpers;

import java.util.Calendar;

import beans.BookingData;
import beans.Ticket;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandValidator;
import utils.DataErrors;

public class CancelCommandHelper implements CommandHelper<CancelCommandParams> {

	private CommandValidator commandValidator;
	
	public CancelCommandHelper() {
		commandValidator = new CancelCommandValidator();
	}

	@Override
	public boolean validate(String input) {
		return commandValidator.validate(input);
	}
	
	@Override
	public CancelCommandParams resolveParams(String input) {
		String[] params = input.split(" ");
		return new CancelCommandParams(params[1], params[2]);
	}

	@Override
	public boolean execute(BookingData bookingData, CancelCommandParams params) {
		if (!bookingData.isTicketExisting(params.getTicketNumber())) {
			System.out.println(String.format(DataErrors.TICKET_DOES_NOT_EXIST, params.getTicketNumber()));
			return false;
		}
		
		Ticket ticket = bookingData.getTicket(params.getTicketNumber());
		if (!ticket.getPhoneNumber().equals(params.getPhoneNumber())) {
			System.out.println(String.format(DataErrors.TICKET_PHONE_MISMATCH, params.getPhoneNumber(), params.getTicketNumber()));
			return false;
		}
		
		Calendar cancellationDate = Calendar.getInstance();
		cancellationDate.setTime(ticket.getBookingDate());
		cancellationDate.add(Calendar.MINUTE, bookingData.getShow(ticket.getShowNumber()).getCancellationWindowInMinutes());
		
		Calendar now = Calendar.getInstance();
		if (now.compareTo(cancellationDate) > 0) {
			System.out.println(String.format(DataErrors.CANCELLATION_WINDOW_OVER, params.getTicketNumber()));
			return false;
		}
		
		bookingData.getShow(ticket.getShowNumber()).removeTicket(params.getPhoneNumber());
		bookingData.getShow(ticket.getShowNumber()).addAvailableSeats(ticket.getSeats());
		bookingData.removeTicket(ticket.getTicketNumber());
		return true;
	}

}
