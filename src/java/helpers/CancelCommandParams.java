package helpers;

import helpers.interfaces.CommandParams;

public class CancelCommandParams implements CommandParams {

	private Integer ticketNumber;
	private String phoneNumber;
	
	public Integer getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public CancelCommandParams(String ticketNumber, String phoneNumber) {
		this.ticketNumber = Integer.parseInt(ticketNumber);
		this.phoneNumber = phoneNumber;
	}

}
