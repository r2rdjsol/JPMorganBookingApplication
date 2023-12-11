package helpers;

import beans.BookingData;
import beans.Show;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandValidator;
import utils.DataErrors;

public class AvailabilityCommandHelper implements CommandHelper<AvailabilityCommandParams> {

	private CommandValidator commandValidator;
	
	public AvailabilityCommandHelper() {
		commandValidator = new AvailabilityCommandValidator();
	}

	@Override
	public boolean validate(String input) {
		return commandValidator.validate(input);
	}
	
	@Override
	public AvailabilityCommandParams resolveParams(String input) {
		String[] params = input.split(" ");
		return new AvailabilityCommandParams(params[1]);
	}

	@Override
	public boolean execute(BookingData bookingData, AvailabilityCommandParams params) {
		Show show = bookingData.getShow(params.getShowNumber());

		if (show == null) {
			System.out.println(String.format(DataErrors.SHOW_DOES_NOT_EXIST, params.getShowNumber()));
			return false;
		}

		if (show.getAvailableSeats().isEmpty()) {
			System.out.println(String.format(DataErrors.NO_AVAILABLE_SEATS, params.getShowNumber()));
			return false;
		} else {
			System.out.println(String.join(",", show.getAvailableSeats()));
			return true;
		}
	}

}
