package main01.kakimzhanova.pizza.action;
import main01.kakimzhanova.pizza.entity.*;

public class OrderAction{
	public static double calculateOrderCost(Order order){
		double cost = 0;
		Order.Pizza[] pizzaArray = order.getPizzaArray();
		for (int i = 0; i < order.getPizzaArraySize(); i++){
			cost += PizzaAction.calculatePizzaCost(pizzaArray[i]) * pizzaArray[i].getCount();
		}
		return cost;
	}
}