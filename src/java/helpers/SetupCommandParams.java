package helpers;

import helpers.interfaces.CommandParams;
import utils.BookingConstants;

public class SetupCommandParams implements CommandParams {

	private Integer showNumber;
	private Integer numberOfRows;
	private Integer numberOfSeatsPerRow;
	private Integer cancellationWindowInMinutes;

	public Integer getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	public Integer getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public Integer getNumberOfSeatsPerRow() {
		return numberOfSeatsPerRow;
	}
	public void setNumberOfSeatsPerRow(Integer numberOfSeatsPerRow) {
		this.numberOfSeatsPerRow = numberOfSeatsPerRow;
	}
	public Integer getCancellationWindowInMinutes() {
		return cancellationWindowInMinutes;
	}
	public void setCancellationWindowInMinutes(Integer cancellationWindowInMinutes) {
		this.cancellationWindowInMinutes = cancellationWindowInMinutes;
	}
	
	public SetupCommandParams(String showNumber, String numberOfRows, String numberOfSeatsPerRow, String cancellationWindowInMinutes) {
		this.showNumber = Integer.parseInt(showNumber);
		this.numberOfRows = Integer.parseInt(numberOfRows);
		this.numberOfSeatsPerRow = Integer.parseInt(numberOfSeatsPerRow);
		this.cancellationWindowInMinutes = Integer.parseInt(cancellationWindowInMinutes);
	}
	
	public SetupCommandParams(String showNumber, String numberOfRows, String numberOfSeatsPerRow) {
		this.showNumber = Integer.parseInt(showNumber);
		this.numberOfRows = Integer.parseInt(numberOfRows);
		this.numberOfSeatsPerRow = Integer.parseInt(numberOfSeatsPerRow);
		this.cancellationWindowInMinutes = BookingConstants.DEFAULT_CANCELLATION_WINDOW_IN_MINUTES;
	}
	
}
