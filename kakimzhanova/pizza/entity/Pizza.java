package main01.kakimzhanova.pizza.entity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import main01.kakimzhanova.pizza.valid.NameValidator;
import main01.kakimzhanova.pizza.action.*;
import main01.kakimzhanova.pizza.report.PizzaReport;

public class Pizza{
		private int orderId;
		private int clientId;
		private String pizzaName;
		private Ingredient ingredients[];
		private int count;
		private int numberOfIngredients = 0;
		private String report = "Pizza\n";
		private final static int MAX_NUMBER_OF_INGREDIENTS = 7;
		
		public Pizza (String name, boolean calzone, int number){
			
			pizzaName = name;
			ingredients = new Ingredient[MAX_NUMBER_OF_INGREDIENTS];
			count = number;
			
			if (calzone){
				ingredients[0] = Ingredient.PIZZA_BASE_CALSONE;
				numberOfIngredients++;
			}
			else{
				ingredients[0] = Ingredient.PIZZA_BASE_DEFAULT;
				numberOfIngredients++;	
			}
			reportPizza();
		}
		public int getNumberOfIngredients(){
			return numberOfIngredients;
		}
		public Ingredient[] getIngredients(){
			return ingredients;
		}
		public String getReport(){
			return report;
		}
		public int getCount(){
			return count;
		}
		public String getPizzaName(){
			return pizzaName;
		}
		public void editPizzaName(String newName){
			pizzaName = newName;
		}
		public void setOrderId(int orderId){
			this.orderId = orderId;
		}
		public void setClientId(int clientId){
			this.clientId = clientId;
		}
		public void reportPizza(){
			report += "["+ orderId + ":" 
				+ clientId + ":" 
				+ pizzaName + ":"
				+count+"]" + "\n";
		}
		
		public String toString(){
			String s;
			String border = "-----------------------------\n";
			s = "Name:"+pizzaName+"\n"+ border;
			for (int i = 0; i < numberOfIngredients; i++){
				s += String.format("%-24s",ingredients[i].getName())+ String.format("%.2f",ingredients[i].getPrice())+" €\n";
			}
			s += border+"Total:"+String.format("%22.2f",PizzaAction.calculatePizzaCost(this)) +" €\n";
			s += "Count:"+ String.format("%24d",count) + "\n" + border;
			return s;
		}
		public void editNumber(int newNumber){
			count = newNumber;
		}
		public void addIngredient(Ingredient ingredient){
			String name = ingredient.getName();
			if (numberOfIngredients-1==MAX_NUMBER_OF_INGREDIENTS){
				report += "You cannot add more than "+ MAX_NUMBER_OF_INGREDIENTS + " ingredients\n";
				return;
			}
			for (int i =0; i < numberOfIngredients; i++){
				if (ingredients[i].getName().equals(name)){
					report += "You have already added "+name+"\n";
					return;
				}
			}
			ingredients[numberOfIngredients] = ingredient;
			numberOfIngredients++;
			report += name + " has been added\n";
			return;
		}
	}