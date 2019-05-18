package main01.kakimzhanova.pizza.entity;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import main01.kakimzhanova.pizza.valid.NameValidator;
import main01.kakimzhanova.pizza.action.*;
import main01.kakimzhanova.pizza.report.PizzaReport;


public class Pizza{
		private int orderId;
		private int clientId;
		private String pizzaName;
		private Ingredient ingredients[];
		private int count;
		private String report = "Pizza\n";
		private final static int MAX_NUMBER_OF_INGREDIENTS = 7;
		
		public Pizza (String name, boolean calzone, int number){
			pizzaName = name;
			ingredients = new Ingredient[1];
			count = number;
			if (calzone){
				ingredients[0] = Ingredient.PIZZA_BASE_CALSONE;
			}
			else{
				ingredients[0] = Ingredient.PIZZA_BASE_DEFAULT;
			}
			reportPizza();
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
			for (Ingredient ing: ingredients){
				s += String.format("%-24s",ing.getName())+ String.format("%.2f",ing.getPrice())+" €\n";
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
			if (ingredients.length - 1 == MAX_NUMBER_OF_INGREDIENTS){
				report += "You cannot add more than "+ MAX_NUMBER_OF_INGREDIENTS + " ingredients\n";
				return;
			}
			for (Ingredient ing : ingredients){
				if (ing.getName().equals(name)){
					report += "You have already added " + name + "\n";
					return;
				}
			}
			Ingredient[] newIngredients = Arrays.copyOf(ingredients, ingredients.length + 1);
			newIngredients[ingredients.length] = ingredient;
			ingredients = newIngredients;
			report += name + " has been added\n";
			return;
		}
	}