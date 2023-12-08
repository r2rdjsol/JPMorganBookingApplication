package tests.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import beans.Show;
import services.BookingService;
import tests.constants.TestConstants;
import utils.BookingConstants;
import utils.UserTypeEnum;

public class SetupCommandTest {
	
	@Test
	public void processSetupCommandSuccessfulWithCancellationWindowParam() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 10 10 5");
		assertEquals(isSuccessful, true);
		
		Show show = bookingService.getBookingData().getShows().get(1);
		assertNotEquals(show, null);
		
		Set<String> availableSeats = new TreeSet<String>();
		availableSeats.addAll(Arrays.asList(TestConstants.SUCCESSFUL_SETUP_COMMAND_SEATS_SHOW_1));
		assertEquals(show.getAvailableSeats(), availableSeats);
		assertEquals(show.getCancellationWindowInMinutes(), 5);
	}

	@Test
	public void processSetupCommandSuccessfulWithoutCancellationWindowParam() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 10 10");
		assertEquals(isSuccessful, true);
		
		Show show = bookingService.getBookingData().getShows().get(1);
		assertNotEquals(show, null);
		
		Set<String> availableSeats = new TreeSet<String>();
		availableSeats.addAll(Arrays.asList(TestConstants.SUCCESSFUL_SETUP_COMMAND_SEATS_SHOW_1));
		assertEquals(show.getAvailableSeats(), availableSeats);
		assertEquals(show.getCancellationWindowInMinutes(), BookingConstants.DEFAULT_CANCELLATION_WINDOW_IN_MINUTES);
	}

	@Test
	public void processSetupCommandWith1MissingParams() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 1");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWith2MissingParams() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithoutParams() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandASBuyer() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Setup");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithNonIntegerShowNumber() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup A 10 10");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithExistingShowNumber() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 10 10 5");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 5");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithNonIntegerNumberOfRows() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 A 5");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWirhNumberOfRowsOutsideLimit() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 -1 5");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 0 5");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 27 5");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithNonIntegerNumberOfSeatsPerRow() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 A");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithNumberOfSeatsPerRowOutsideLimit() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 -1");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 0");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 11");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processSetupCommandWithNonIntegerCancellationWindow() {
		BookingService bookingService = new BookingService();
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 5 5 A");
		assertEquals(isSuccessful, false);
	}

}
