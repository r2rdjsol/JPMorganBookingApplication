package tests.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import services.BookingService;
import tests.BookingServiceTest;
import utils.UserTypeEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ViewCommandTest {

	@Test
	public void processViewCommandSuccessful() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View 1");
		assertEquals(isSuccessful, true);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View 2");
		assertEquals(isSuccessful, true);
	}
	
	@Test
	public void processViewCommandAsBuyer() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.BUYER, "View 1");
		assertEquals(isSuccessful, false);
	}

	@Test
	public void processViewCommandWithMissingParam() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View");
		assertEquals(isSuccessful, false);
	}
	
	@Test
	public void processViewCommandViewWithNonIntegerShowNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View A");
		assertEquals(isSuccessful, false);
	}
	
	@Test
	public void processViewCommandWithNonExistingShowNumber() {
		BookingService bookingService = new BookingService();
		BookingServiceTest.setupMultipleShows(bookingService);
		
		boolean isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View 11");
		assertEquals(isSuccessful, false);
		
		isSuccessful = bookingService.processCommand(UserTypeEnum.ADMIN, "View 12");
		assertEquals(isSuccessful, false);
	}

}
