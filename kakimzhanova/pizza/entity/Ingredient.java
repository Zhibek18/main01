package main01.kakimzhanova.pizza.entity;

public enum Ingredient{
	TOMATO_PASTE("Tomato Paste", 1.0), CHEESE("Cheese", 1.0), SALAMI("Salami",1.5), BACON("Bacon", 1.2), 
	GARLIC("Garlic",0.3), CORN("Corn", 0.7), PEPPERONI("Pepperoni", 0.6), OLIVES("Olives",0.5),
	PIZZA_BASE_DEFAULT("Pizza Base(Default)", 1.0), PIZZA_BASE_CALSONE("Pizza Base(Calsone)",1.5);

	private double price;
	private String name;

	Ingredient (String name, double price){
		this.name = name;
		this.price = price;
	}
	public double getPrice(){
		return price;
	}
	public String getName(){
		return name;
	}
}