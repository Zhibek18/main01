package main01.kakimzhanova.pizza.valid;

public class NameValidator{
	final static int MAX_NAME_LENGTH = 20;
	final static int MIN_NAME_LENGTH = 4;

	public static boolean validatePizzaName(String name){
		int l = name.length();
		return ((l >= MIN_NAME_LENGTH)&&(l <= MAX_NAME_LENGTH));
	}
}
