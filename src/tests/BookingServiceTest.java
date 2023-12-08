package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import services.BookingService;
import tests.classes.AvailabilityCommandTest;
import tests.classes.BookCommandTest;
import tests.classes.CancelCommandTest;
import tests.classes.SetupCommandTest;
import tests.classes.ViewCommandTest;
import utils.UserTypeEnum;

@RunWith(Suite.class)
@SuiteClasses({
	SetupCommandTest.class,
	ViewCommandTest.class,
	AvailabilityCommandTest.class,
	BookCommandTest.class,
	CancelCommandTest.class
})
public class BookingServiceTest {

	public static void setupMultipleShows(BookingService bookingService) {
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 1 10 10 5");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 2 10 10");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 3 5 5 3");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 4 20 10");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 5 15 8 10");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 6 25 10");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 7 26 9");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 8 12 4 15");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 9 7 7");
		bookingService.processCommand(UserTypeEnum.ADMIN, "Setup 10 2 2 1");
	}
	
}
