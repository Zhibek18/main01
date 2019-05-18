package main01.kakimzhanova.pizza.entity;
import java.time.LocalTime;
import java.util.Arrays;
import java.time.format.DateTimeFormatter;
import main01.kakimzhanova.pizza.valid.NameValidator;
import main01.kakimzhanova.pizza.action.*;
import main01.kakimzhanova.pizza.report.PizzaReport;

public class Order{
	public static final int MAX_NUMBER_OF_PIZZAS = 10;
	private static int orderCount = 10000;
	private final int orderId;
	private final int clientId;
	private String clientName;
	//private int pizzaArraySize = 0;
	private Pizza pizzaArray[];
	private String report = "Order number: ";
	private LocalTime localTime;
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public Order(Client client){
		clientName = client.getClientName();
		orderId = orderCount++;
		report += orderId + "\n";
		clientId = client.getClientId();
		//pizzaArray = new Pizza[MAX_NUMBER_OF_PIZZAS];
		localTime = LocalTime.now();
	}
	public int getclientId(){
		return clientId;
	}
	public int getorderId(){
		return orderId;
	}
	public int getPizzaArraySize(){
		if (pizzaArray == null){
			return 0;
		}
		else{
			return pizzaArray.length;
		}
	}
	public Pizza[] getPizzaArray(){
		return pizzaArray;
	}
	public String getOrderReport(){
		return report;
	}
	
	public void addPizza(Pizza pizza){
		if (OrderAction.calculatePizzaCount(this) + pizza.getCount() > MAX_NUMBER_OF_PIZZAS){
			report += "You cannot order more than "+MAX_NUMBER_OF_PIZZAS+" pizzas\n";
			return;
		}
		if (!NameValidator.validatePizzaName(pizza.getPizzaName())){
			pizza.editPizzaName(clientName +"_"+ (pizzaArray.length + 1));
		}
		pizza.setOrderId(orderId);
		pizza.setClientId(clientId);
		if (pizzaArray != null){
			Pizza[] newPizzas = Arrays.copyOf(pizzaArray, pizzaArray.length + 1);
			newPizzas[pizzaArray.length] = pizza;	
			pizzaArray = newPizzas;
		}
		else{
			pizzaArray = new Pizza[1];
			pizzaArray[0] = pizza;
		}
		
		report += pizza.getReport();
		
	}
	public void deletePizza(String name){
		Pizza[] newPizzas = new Pizza[pizzaArray.length - 1];
		int j = 0;
		try{
			for (Pizza pizza: pizzaArray){
				if (!pizza.getPizzaName().equals(name)){
					newPizzas[j++] = pizza;
				}
				
			}
		}catch(ArrayIndexOutOfBoundsException e){
			report += "Couldn't find pizza with name "+name+". Please try again\n";
			return;		
		}
		pizzaArray = newPizzas;
		
	}
	public String toString(){

		String outBorder = "******************************\n";
		String s = outBorder +
					"Time: " + localTime.format(timeFormatter)+"\n" +
					"Order: "+orderId+"\n" +
					"Client: "+clientId+"\n";
		if (pizzaArray == null){
			return s+"Empty order\n" + outBorder;
		}
		for (Pizza pizza: pizzaArray){
			s += pizza;
		}
		s += "Total sum:" + String.format("%18.2f",OrderAction.calculateOrderCost(this)) + " €\n";
		s += outBorder;
		return s;
	}
}