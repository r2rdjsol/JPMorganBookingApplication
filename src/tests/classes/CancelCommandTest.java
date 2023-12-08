package tests.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import beans.Show;
import services.BookingService;
import tests.BookingServiceTest;
import tests.constants.TestConstants;
import utils.UserTypeEnum;

public class CancelCommandTest {

	@Test
	public void processSuccessfulCancelCommand() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 1 +639111111111");
		assertEquals(isSuccessful, true);
		assertEquals(bookingService.getBookingData().getTickets().get(1), null);
		
		Show show = bookingService.getBookingData().getShows().get(1);
		Set<String> availableSeats = new TreeSet<String>();
		availableSeats.addAll(Arrays.asList(TestConstants.SUCCESSFUL_SETUP_COMMAND_SEATS_SHOW_1));
		assertEquals(show.getAvailableSeats(), availableSeats);
		assertEquals(show.getTicket("+639111111111"), null);
	}

	@Test
	public void processCancelCommandWithMissingParams() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWith1MissingParam() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel 1");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 1");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWithNonIntegerTicketNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel A +639111111111");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel A +639111111111");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWithNonExistingTicketNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel 2 +639111111111");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 2 +639111111111");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWithInvalidPhoneNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel 2 ABCDEFGHIJKLM");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 2 ABCDEFGHIJKLM");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWithNonMatchingPhoneNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 1 +639111111111 A01,A02,A03");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel 1 +639222222222");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 1 +639222222222");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processCancelCommandWithCancellationWindowOver() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		try {
			boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Book 10 +639111111111 A01,A02");
			assertEquals(isSuccessful, true);
			
			TimeUnit.SECONDS.sleep(70);
			
			isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Cancel 1 +639111111111");
			assertEquals(isSuccessful, false);
			
			isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Cancel 1 +639111111111");
			assertEquals(isSuccessful, false);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
