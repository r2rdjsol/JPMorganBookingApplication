package helpers;

import helpers.interfaces.CommandParams;

public class ViewCommandParams implements CommandParams {

	private Integer showNumber;

	public Integer getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	
	public ViewCommandParams(String showNumber) {
		this.showNumber = Integer.parseInt(showNumber);
	}
	
}
