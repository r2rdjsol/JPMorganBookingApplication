package tests.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import services.BookingService;
import tests.BookingServiceTest;
import utils.UserTypeEnum;

public class AvailabilityCommandTest {

	@Test
	public void processSuccessfulAvailabilityCommand() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability 1");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability 2");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability 1");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability 2");
		assertEquals(isSuccessful, true);
	}
	
	@Test
	public void processAvailabilityCommandWithMissingParam() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability");
		assertEquals(isSuccessful, false);
	}
	
	@Test
	public void processAvailabilityCommandWithNonIntegerShowNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability A");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability A");
		assertEquals(isSuccessful, false);
	}
	
	@Test
	public void processAvailabilityCommandWithNonExistingShowNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability 11");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "Availability 12");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability 11");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "Availability 12");
		assertEquals(isSuccessful, false);
	}

}
