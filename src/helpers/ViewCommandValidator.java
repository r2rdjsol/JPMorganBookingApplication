package helpers;

public class ViewCommandValidator extends AbstractCommandValidator {

	@Override
	public boolean validate(String input) {
		String[] params = input.split(" ");
		int invalidCount = 0;
		
		if (!validateNumberOfParams(params, 1, 0)) return false;
		if (!validateIntegerParam("Show Number", params[1])) invalidCount++;
		
		return invalidCount == 0;
	}

}
