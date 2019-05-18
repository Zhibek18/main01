package main01.kakimzhanova.pizza.entity;
import java.time.LocalTime;
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
	private int pizzaArraySize = 0;
	private Pizza pizzaArray[];
	private String report = "Order number: ";
	private LocalTime localTime;
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public Order(Client client){
		clientName = client.getClientName();
		orderId = orderCount++;
		report += orderId + "\n";
		clientId = client.getClientId();
		pizzaArray = new Pizza[MAX_NUMBER_OF_PIZZAS];
		localTime = LocalTime.now();
	}
	public int getclientId(){
		return clientId;
	}
	public int getorderId(){
		return orderId;
	}
	public int getPizzaArraySize(){
		return pizzaArraySize;
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
			pizza.editPizzaName(clientName +"_"+ (pizzaArraySize + 1));
		}
		pizza.setOrderId(orderId);
		pizza.setClientId(clientId);
		pizzaArray[pizzaArraySize] = pizza;
		report += pizza.getReport();
		pizzaArraySize++;
	}
	public void deletePizza(String name){
		for (int i = 0; i < pizzaArraySize; i++){
			if (pizzaArray[i].getPizzaName().equals(name)){
				int j = i;
				while (pizzaArray[j]!=null){
					pizzaArray[j]=pizzaArray[j+1];
					j++;
				}
				pizzaArraySize--;
				return;
			}
		}
		report += "Couldn't find pizza with name "+name+". Please try again\n";
	}
	public String toString(){
		String outBorder = "******************************\n";
		String s = outBorder +
					"Time: " + localTime.format(timeFormatter)+"\n" +
					"Order: "+orderId+"\n" +
					"Client: "+clientId+"\n";
		for (int i = 0; i < pizzaArraySize; i++){
			s += pizzaArray[i];
		}
		s += "Total sum:" + String.format("%18.2f",OrderAction.calculateOrderCost(this)) + " €\n";
		s += outBorder;
		return s;
	}
}