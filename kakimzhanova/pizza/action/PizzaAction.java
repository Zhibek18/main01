package main01.kakimzhanova.pizza.action;
import main01.kakimzhanova.pizza.entity.*;
public class PizzaAction{
	public static double calculatePizzaCost(Pizza pizza){
		double cost = 0;
		Ingredient[] ingredients = pizza.getIngredients();
		for (Ingredient ing: ingredients){
			cost += ing.getPrice();	
		}
		return cost;
	}
}