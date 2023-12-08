# JPMorganBookingApplication
Coding Assessment for JP Morgan Application

## This project was written using Eclipse IDE but should be runnable using any IDE.
- The main class for running the application is BookingApplication.java
- The main class for running the unit tests is BookingApplicationTest.java

## The following assumptions were made when designing this app:
1. Users first need to login to identify whether they are Admins or Buyers. However, for simplicity, authentication is not fully implemented.
2. Admins can also use the command available to Buyers (Availability, Book, and Cancel)
3. Cancellation window in minutes is optional and will be set to 2 by default when not provided.
4. Booking will only be successful when all Seats are available.
5. For simplicity, show numbers and ticket numbers are assumed to be integers.
6. For simplicity, phone numbers will be Philipinne Mobile Phone Numbers with the format '+639xxxxxxxxx'
7. Since data is in memory, it will be cleared once the application stops running.
