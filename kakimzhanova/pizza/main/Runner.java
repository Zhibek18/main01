package main01.kakimzhanova.pizza.main;
import main01.kakimzhanova.pizza.report.*;
import main01.kakimzhanova.pizza.entity.*;
public class Runner{
	public static void main(String[] args){
		Client firstClient = new Client(7717, "Alisa");
		Order firstOrder = new Order(firstClient);
		Pizza pizzaMar = new Pizza("Margatita", true, 2);
		pizzaMar.addIngredient(Ingredient.TOMATO_PASTE);
		pizzaMar.addIngredient(Ingredient.GARLIC);
		pizzaMar.addIngredient(Ingredient.PEPPERONI);
		pizzaMar.addIngredient(Ingredient.BACON);
		firstOrder.addPizza(pizzaMar);

		Pizza pizzaPep = new Pizza("PepperoniOro", false, 8);
		pizzaPep.addIngredient(Ingredient.TOMATO_PASTE);
		pizzaPep.addIngredient(Ingredient.CHEESE);
		pizzaPep.addIngredient(Ingredient.PEPPERONI);
		pizzaPep.addIngredient(Ingredient.OLIVES);
		firstOrder.addPizza(pizzaPep);
		firstOrder.editPizzaCount("PepperoniOro", 9);
		System.out.println(firstOrder);

		Client secondClient = new Client(4372, "Mark");
		Order secondOrder = new Order(secondClient);
		Pizza pizzaPzz = new Pizza("BasePZZ", false, 12);
		secondOrder.addPizza(pizzaPzz);
		System.out.println(secondOrder);

		OrderReport.printOrderReport(firstOrder);
		OrderFileReport.printFileOrderReport("main01/kakimzhanova/pizza/report/result.txt",secondOrder);
	}	
}