package helpers;

import java.util.Set;

import beans.BookingData;
import beans.Show;
import beans.Ticket;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandValidator;
import utils.DataErrors;

public class ViewCommandHelper implements CommandHelper<ViewCommandParams> {
	
	private CommandValidator commandValidator;
	
	public ViewCommandHelper() {
		commandValidator = new ViewCommandValidator();
	}

	@Override
	public boolean validate(String input) {
		return commandValidator.validate(input);
	}

	@Override
	public ViewCommandParams resolveParams(String input) {
		String[] params = input.split(" ");
		return new ViewCommandParams(params[1]);
	}

	@Override
	public boolean execute(BookingData bookingData, ViewCommandParams params) {
		Show show = bookingData.getShow(params.getShowNumber());

		if (show == null) {
			System.out.println(String.format(DataErrors.SHOW_DOES_NOT_EXIST, params.getShowNumber()));
			return false;
		}
			
		Set<String> keySet = show.getTickets().keySet();
		if (keySet.isEmpty()) {
			System.out.println(String.format(DataErrors.NO_TICKETS_BOOKED, params.getShowNumber()));
			return true;
		}
		
		System.out.println("<Show Number> <Ticket #> <Buyer Phone #> <Seat Numbers>");
		String ticketInfo = "%d %d %s %s";
		for (String phoneNumber : show.getTickets().keySet()) {
			Ticket ticket = bookingData.getTickets().get(show.getTickets().get(phoneNumber));
			System.out.println(String.format(ticketInfo, ticket.getShowNumber(), ticket.getTicketNumber(), ticket.getPhoneNumber(), String.join(",", ticket.getSeats())));
		}
		return true;
	}

}
