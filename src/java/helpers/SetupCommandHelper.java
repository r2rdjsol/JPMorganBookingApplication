package helpers;

import java.util.Set;
import java.util.TreeSet;

import beans.BookingData;
import beans.Show;
import helpers.interfaces.CommandHelper;
import helpers.interfaces.CommandValidator;
import utils.DataErrors;

public class SetupCommandHelper implements CommandHelper<SetupCommandParams> {

	private CommandValidator commandValidator;
	
	public SetupCommandHelper() {
		commandValidator = new SetupCommandValidator();
	}
	
	@Override
	public boolean validate(String input) {
		return commandValidator.validate(input);
	}
	
	@Override
	public SetupCommandParams resolveParams(String input) {
		String[] params = input.split(" ");
		if (params.length == 4) {
			return new SetupCommandParams(params[1], params[2], params[3]);
		} else {
			return new SetupCommandParams(params[1], params[2], params[3], params[4]);
		}
	}

	@Override
	public boolean execute(BookingData bookingData, SetupCommandParams params) {
		if (bookingData.isShowExisting(params.getShowNumber())) {
			System.out.println(String.format(DataErrors.SHOW_NUMBER_EXISTS, params.getShowNumber()));
			return false;
		} else {
			Set<String> availableSeats = generateSeats(params.getNumberOfRows(), params.getNumberOfSeatsPerRow());
			Show show = new Show(params.getShowNumber(), availableSeats, params.getCancellationWindowInMinutes());
			bookingData.addShow(show);
			return true;
		}
	}
	
	private Set<String> generateSeats (int numberOfRows, int numberOfSeatsPerRow) {
		Set<String> seats = new TreeSet<String>();
		int row = Character.getNumericValue('A');
		int seat = 1;
		StringBuffer sb;
		
		for (int i=0; i<numberOfRows; i++) {
			for (int j=0; j<numberOfSeatsPerRow; j++) {
				sb = new StringBuffer();
				sb.append(Character.toString(Character.forDigit(row, Character.MAX_RADIX)).toUpperCase());
				sb.append((seat != 10) ? "0" + seat : seat);
				seats.add(sb.toString());
				seat++;
			}
			seat = 1;
			row++;
		}

		return seats;
	}

}
