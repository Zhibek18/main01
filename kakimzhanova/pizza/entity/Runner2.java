package main01.kakimzhanova.pizza.entity;

public class Runner2{
	public static void main(String[] args){
		Client client = new Client(1111, "Alisa");
		Order order = new Order(client);
		Order.Pizza pizza = order.new Pizza("M", true, 1);
		pizza.addIngredient("Tomato Paste");
		pizza.addIngredient("Cheese");
		order.addPizza(pizza);
		System.out.println(order);
	}	
}