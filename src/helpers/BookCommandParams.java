package helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import helpers.interfaces.CommandParams;

public class BookCommandParams implements CommandParams {

	private Integer showNumber;
	private String phoneNumber;
	private Set<String> seats;

	public Integer getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Set<String> getSeats() {
		return seats;
	}
	public void setSeats(Set<String> seats) {
		this.seats = seats;
	}
	
	public BookCommandParams(String showNumber, String phoneNumber, String seats) {
		this.showNumber = Integer.parseInt(showNumber);
		this.phoneNumber = phoneNumber;
		this.seats = new HashSet<String>();
		this.seats.addAll(Arrays.asList(seats.split(",")));
	}
	
}
