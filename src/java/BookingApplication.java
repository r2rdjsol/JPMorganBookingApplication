import java.util.Scanner;

import services.BookingService;
import utils.UserTypeEnum;

public class BookingApplication {

	public static void main (String[] args) {
		System.out.println("Simple Console Booking Application");
		boolean isRunning = true;
		boolean isLoggedIn = false;
		boolean isSuccessful = false;
		
		Scanner scanner = new Scanner(System.in);
		String input = null;
		UserTypeEnum userType = null;
		BookingService bookingService = new BookingService();
		
		while (isRunning) {
			System.out.println("");
			System.out.println("Please Login (ADMIN or BUYER)?");
			while (!isLoggedIn) {
				input = scanner.nextLine();
				userType = UserTypeEnum.fromString(input);
				
				if (userType == null) {
					System.out.println("Please enter a valid user type (ADMIN or BUYER).");
				} else {
					isLoggedIn = true;
				}
			}
			
			while (isLoggedIn) {
				System.out.println("");
				System.out.println("Please enter a command");
				input = scanner.nextLine();
				isSuccessful = bookingService.processCommand(userType, input);
				
				if (isSuccessful) {
					System.out.println("");
					System.out.println("Do you wish to enter another command? (Please answer Y, otherwise you will be loged out.)");
					input = scanner.nextLine();
					if (!input.equalsIgnoreCase("Y")) {
						isLoggedIn = false;
					}
				}
			}
			
			System.out.println("");
			System.out.println("Login again? (Please answer Y, otherwise application will be terminated");
			input = scanner.nextLine();
			if (!input.equalsIgnoreCase("Y")) {
				System.out.println("");
				System.out.println("Thank you for using this Simple Console Booking Application");
				isRunning = false;
			}
		}
		
		scanner.close();
	}
	
}
