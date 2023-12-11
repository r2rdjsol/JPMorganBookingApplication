package beans;

import java.util.HashMap;
import java.util.Map;

public class BookingData {

	private int lastTicketNumber;
	private Map<Integer, Show> shows;
	private Map<Integer, Ticket> tickets;
	
	public int getLastTicketNumber() {
		return lastTicketNumber;
	}
	public void setLastTicketNumber(int lastTicketNumber) {
		this.lastTicketNumber = lastTicketNumber;
	}
	public Map<Integer, Show> getShows() {
		return shows;
	}
	public void setShows(Map<Integer, Show> shows) {
		this.shows = shows;
	}
	public Map<Integer, Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(Map<Integer, Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public BookingData() {
		lastTicketNumber = 0;
		shows = new HashMap<Integer, Show>();
		tickets = new HashMap<Integer, Ticket>();
	}
	
	public void incrementLastTicketNumber() {
		this.lastTicketNumber++;
	}
	
	public void addShow(Show show) {
		this.shows.put(show.getShowNumber(), show);
	}
	
	public Show getShow(Integer showNumber) {
		return shows.get(showNumber);
	}
	
	public boolean isShowExisting(Integer showNumber) {
		return shows.containsKey(showNumber);
	}
	
	public void addTicket(Ticket ticket) {
		this.tickets.put(ticket.getTicketNumber(), ticket);
	}
	
	public boolean removeTicket(Integer ticketNumber) {
		return (this.tickets.remove(ticketNumber) != null);
	}
	
	public Ticket getTicket(Integer ticketNumber) {
		return this.tickets.get(ticketNumber);
	}
	
	public boolean isTicketExisting(Integer ticketNumber) {
		return this.tickets.containsKey(ticketNumber);
	}
	
}
