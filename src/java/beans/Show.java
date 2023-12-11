package beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import utils.BookingConstants;

public class Show {
	
	private Integer showNumber;
	private Set<String> availableSeats;
	private Map<String, Integer> tickets;
	private Integer cancellationWindowInMinutes;
	
	public Integer getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	public Set<String> getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Set<String> availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Map<String, Integer> getTickets() {
		return tickets;
	}
	public void setTickets(Map<String, Integer> tickets) {
		this.tickets = tickets;
	}
	public Integer getCancellationWindowInMinutes() {
		return cancellationWindowInMinutes;
	}
	public void setCancellationWindowInMinutes(Integer cancellationWindowInMinutes) {
		this.cancellationWindowInMinutes = cancellationWindowInMinutes;
	}
	
	public Show(Integer showNumber) {
		this.showNumber = showNumber;
		this.availableSeats = new TreeSet<String>();
		this.tickets = new HashMap<String, Integer>();
		this.cancellationWindowInMinutes = BookingConstants.DEFAULT_CANCELLATION_WINDOW_IN_MINUTES;
	}
	
	public Show(Integer showNumber, Set<String> availableSeats) {
		this.showNumber = showNumber;
		this.availableSeats = availableSeats;
		this.tickets = new HashMap<String, Integer>();
		this.cancellationWindowInMinutes = BookingConstants.DEFAULT_CANCELLATION_WINDOW_IN_MINUTES;
	}
	
	public Show(Integer showNumber, Set<String> availableSeats, Integer cancellationWindowInMinutes) {
		this.showNumber = showNumber;
		this.availableSeats = availableSeats;
		this.tickets = new HashMap<String, Integer>();
		this.cancellationWindowInMinutes = cancellationWindowInMinutes;
	}
	
	public void addAvailableSeats(Set<String> availableSeats) {
		this.availableSeats.addAll(availableSeats);
	}
	
	public void removeAvailableSeats(Set<String> availableSeats) {
		this.availableSeats.removeAll(availableSeats);
	}
	
	public Set<String> checkUnavailableSeats(Set<String> seats) {
		if (this.availableSeats.containsAll(seats)) {
			return new TreeSet<String>();
		} else {
			return seats.stream().filter(s -> !this.availableSeats.contains(s)).collect(Collectors.toSet());
		}
	}
	
	public void addTicket(String phoneNumber, Integer ticketNumber) {
		this.tickets.put(phoneNumber, ticketNumber);
	}
	
	public Integer removeTicket(String phoneNumber) {
		return this.tickets.remove(phoneNumber);
	}
	
	public Integer getTicket(String phoneNumber) {
		return this.tickets.get(phoneNumber);
	}
	
	public boolean isPhoneNumberExisting(String phoneNumber) {
		return this.tickets.containsKey(phoneNumber);
	}
	
}
