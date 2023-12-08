package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helpers.interfaces.CommandValidator;
import utils.ValidationErrors;

public abstract class AbstractCommandValidator implements CommandValidator {

	protected static boolean validateNumberOfParams(String[] params, int numParams, int numOptParams) {
		if (params.length-1 == numParams-numOptParams || params.length-1 == numParams) {
			return true;
		} else {
			System.out.println(String.format(ValidationErrors.INCORRECT_NUMBER_OF_PARAMS, params[0].toUpperCase(), numParams));
			return false;
		}
	}
	
	protected static boolean validateNumberOfParams(String[] params, int numParams) {
		return validateNumberOfParams(params, numParams, 0);
	}
	
	protected static boolean validateIntegerParam(String parameterName, String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException nfe) {
			System.out.println(String.format(ValidationErrors.PARAM_MUST_BE_AN_INTEGER, parameterName));
			return false;
		}
	}
	
	protected static boolean validateOptionalIntegerParam(String parameterName, String input) {
		if (input != null) {
			return validateIntegerParam(parameterName, input);
		} else {
			return true;
		}
	}
	
	protected static boolean validateIntegerBetween(String parameterName, String input, int from, int to) {
		if (!validateIntegerParam(parameterName, input)) {
			return false;
		}
		
		int number = Integer.parseInt(input);
		if (number >= from && number <= to) {
			return true;
		} else {
			System.out.println(String.format(ValidationErrors.INTEGER_PARAM_LIMIT, parameterName, from, to));
			return false;
		}
	}
	
	protected static boolean validatePhoneNumber(String parameterName, String input) {
		Pattern pattern = Pattern.compile("^(\\+639)\\d{9}$");
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			return true;
		} else {
			System.out.println(String.format(ValidationErrors.INVALID_PHONE_NUMBER_FORMAT, parameterName));
			return false;
		}
	}
	
}
