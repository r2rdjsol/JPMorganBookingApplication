package helpers;

public class BookCommandValidator extends AbstractCommandValidator {

	@Override
	public boolean validate(String input) {
		String[] params = input.split(" ");
		int invalidCount = 0;
		
		if (!validateNumberOfParams(params, 3, 0)) return false;
		if (!validateIntegerParam("Show Number", params[1])) invalidCount++;
		if (!validatePhoneNumber("Phone Number", params[2])) invalidCount++;
		
		return invalidCount == 0;
	}

}
