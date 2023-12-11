package helpers;

public class SetupCommandValidator extends AbstractCommandValidator {

	@Override
	public boolean validate(String input) {
		String[] params = input.split(" ");
		int invalidCount = 0;
		
		if (!validateNumberOfParams(params, 4, 1)) return false;
		if (!validateIntegerParam("Show Number", params[1])) invalidCount++;
		if (!validateIntegerBetween("Number of Rows", params[2], 1, 26)) invalidCount++;
		if (!validateIntegerBetween("Number of Seats per Row", params[3], 1, 10)) invalidCount++;
		if (params.length == 5 && !validateOptionalIntegerParam("Cancellation Window in minutes", params[4])) invalidCount++;
		
		return invalidCount == 0;
	}

}
