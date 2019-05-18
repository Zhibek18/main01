package main01.kakimzhanova.pizza.action;
import main01.kakimzhanova.pizza.entity.*;

public class OrderAction{
	public static double calculateOrderCost(Order order){
		double cost = 0;
		Pizza[] pizzaArray = order.getPizzaArray();
		for (int i = 0; i < order.getPizzaArraySize(); i++){
			cost += PizzaAction.calculatePizzaCost(pizzaArray[i]) * pizzaArray[i].getCount();
		}
		return cost;
	}
	public static int calculatePizzaCount(Order order){
		int count = 0;
		Pizza[] pizzaArray = order.getPizzaArray();
		for (int i = 0; i < order.getPizzaArraySize(); i++){
			count += pizzaArray[i].getCount();
		}
		return count;
	}
}