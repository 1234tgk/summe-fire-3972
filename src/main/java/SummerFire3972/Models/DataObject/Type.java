package SummerFire3972.Models.DataObject;

public enum Type {

	FOOD("Food"), ENTERTAINMENT("Entertainment"), UTILITY("Utility"), OTHER("Other");
	
	public String value;
	
	private Type (String value) {
		this.value = value;
	}
	
	public static Type getType(String value) {
		for (Type t : values()) {
			if (value.equalsIgnoreCase(t.value))
				return t;
		}
		
		return null;
	}
}
