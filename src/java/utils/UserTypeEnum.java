package utils;

public enum UserTypeEnum {
	ADMIN ("Admin"),
	BUYER ("Buyer");
	
	private String type;
	
	private UserTypeEnum(String type) {
		this.type = type;
	}
	
	public static UserTypeEnum fromString(String type) {
		if (type.equalsIgnoreCase("Admin")) {
			return ADMIN;
		} else if (type.equalsIgnoreCase("Buyer")) {
			return BUYER;
		} else {
			return null;
		}
	}
	
	public String toString() {
		return this.type;
	}
	
}
