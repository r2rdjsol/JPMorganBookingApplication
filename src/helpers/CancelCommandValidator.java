package helpers;

public class CancelCommandValidator extends AbstractCommandValidator {

	@Override
	public boolean validate(String input) {
		String[] params = input.split(" ");
		int invalidCount = 0;
		
		if (!validateNumberOfParams(params, 2, 0)) return false;
		if (!validateIntegerParam("Ticket Number", params[1])) invalidCount++;
		if (!validatePhoneNumber("Phone Number", params[2])) invalidCount++;
		
		return invalidCount == 0;
	}

}
