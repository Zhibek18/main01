package maintask02;

public class Ingredient{
	 String ingredientName;
	 double ingredientPrice;
	public Ingredient(String name, double price){
		ingredientName = name;
		ingredientPrice = price;
	}
	public String getName(){
		return ingredientName;
	}
	public double getPrice(){
		return ingredientPrice;
	}
		
}