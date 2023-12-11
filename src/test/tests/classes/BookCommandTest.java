package tests.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import beans.Show;
import beans.Ticket;
import services.BookingService;
import tests.BookingServiceTest;
import tests.constants.TestConstants;
import utils.UserTypeEnum;

public class BookCommandTest {

	@Test
	public void processSuccessfulBookCommandn() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		Integer ticketNumber = bookingService.getBookingData().getLastTicketNumber();
		assertNotEquals(bookingService.getBookingData().getTickets().get(ticketNumber), null);
		
		Ticket ticket = bookingService.getBookingData().getTickets().get(ticketNumber);
		assertEquals(ticket.getShowNumber(), 1);
		assertEquals(ticket.getPhoneNumber(), "+639111111111");
		Set<String> seats = new TreeSet<String>();
		seats.addAll(Arrays.asList(TestConstants.TICKET_1_SEATS));
		assertEquals(ticket.getSeats(), seats);
		
		Show show = bookingService.getBookingData().getShows().get(1);
		Set<String> availableSeats = new TreeSet<String>();
		availableSeats.addAll(Arrays.asList(TestConstants.AVAILABLE_SEATS_SHOW_1_AFTER_TICKET_1));
		assertEquals(show.getAvailableSeats(), availableSeats);
		assertEquals(show.getTicket("+639111111111"), ticketNumber);
	}

	@Test
	public void processBookCommandWithMissingParams() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWith1MissingParam() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639111111111");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWith2MissingParams() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWithNonIntegerShowNumnber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book A +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book A +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWithNonExistingShowNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 11 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 11 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWithInvalidPhoneNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 11 09111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 ABCDEFGHIJKLM A01,A02,A03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 11 09111111111 A01,A02,A03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 ABCDEFGHIJKLM A01,A02,A03");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWithExistingPhoneNumberInSameShow() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639111111111 B01,B02,B03");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 B01,B02,B03");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processBookCommandWithExistingPhoneNumberInAnotherShow() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 2 +639111111111 B01,B02,B03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 3 +639111111111 B01,B02,B03");
		assertEquals(isSuccessful, true);
	}

	@Test
	public void processBookCommandWithInvalidSeats() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639222222222 A01,A02");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Book 1 +639222222222 K01,K02");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639222222222 A01,A02");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639222222222 K01,K02");
		assertEquals(isSuccessful, false);
	}

}
