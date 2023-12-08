package beans;

import java.util.Date;
import java.util.Set;

public class Ticket {

	private Integer ticketNumber;
	private Integer showNumber;
	private String phoneNumber;
	private Set<String> seats;
	private Date bookingDate;
	
	public Integer getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(Integer ticketNo) {
		this.ticketNumber = ticketNo;
	}
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
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public Ticket(Integer ticketNumber, Integer showNumber, String phoneNumber, Set<String> seats, Date bookingDate) {
		this.ticketNumber = ticketNumber;
		this.showNumber = showNumber;
		this.phoneNumber = phoneNumber;
		this.seats = seats;
		this.bookingDate = bookingDate;
	}
	
}
