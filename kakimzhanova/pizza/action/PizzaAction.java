package main01.kakimzhanova.pizza.action;
import main01.kakimzhanova.pizza.entity.*;
public class PizzaAction{
	public static double calculatePizzaCost(Order.Pizza pizza){
		double cost = 0;
		Ingredient[] ingredients = pizza.getIngredients();
		for (int i = 0; i < pizza.getNumberOfIngredients(); i++){
			cost += ingredients[i].getPrice();	
		}
		return cost;
	}
}