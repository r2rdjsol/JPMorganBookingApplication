package helpers;

import helpers.interfaces.CommandParams;

public class AvailabilityCommandParams implements CommandParams {

	private Integer showNumber;

	public Integer getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	
	public AvailabilityCommandParams(String showNumber) {
		this.showNumber = Integer.parseInt(showNumber);
	}

}
