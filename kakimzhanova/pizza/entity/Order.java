package main01.kakimzhanova.pizza.entity;

public class Order{
	private static int orderCount = 10000;
	//private static int customerCount;

	private final int orderNumber;
	private final int customerNumber;

	private String customerName;
	private int pizzaArraySize = 0;
	private int numberOfPizzas = 0;
	private Pizza pizzaArray[];

	public static final int MAX_NUMBER_OF_PIZZAS = 10;

	private double totalSum = 0;
	public static Ingredient[] allowedIngredients = new Ingredient[8];
	static{
		
		allowedIngredients[0]= new Ingredient("Tomato Paste", 1);
		allowedIngredients[1]= new Ingredient("Cheese", 1);
		allowedIngredients[2]= new Ingredient("Salami", 1.5);
		allowedIngredients[3]= new Ingredient("Bacon", 1.2);
		allowedIngredients[4]= new Ingredient("Garlic", 0.3);
		allowedIngredients[5]= new Ingredient("Corn", 0.7);
		allowedIngredients[6]= new Ingredient("Pepperoni", 0.6);
		allowedIngredients[7]= new Ingredient("Olives", 0.5);
		
	}
	
	public Order(Client client){
		customerName = client.getClientName();
		orderCount++;
		//customerCount++;
		orderNumber = orderCount;
		customerNumber = client.getClientId();
		pizzaArray = new Pizza[MAX_NUMBER_OF_PIZZAS];
	}
	
	public int getCustomerNumber(){
		return customerNumber;
	}
	public int getOrderNumber(){
		return orderNumber;
	}
	public int getNumberOfPizzas(){
		return numberOfPizzas;
	}
	public class Pizza{
		private String pizzaName;
		private Ingredient ingredients[];
		private int count;
		private int numberOfIngredients = 0;
		private double cost;

		private final int MAX_NUMBER_OF_INGREDIENTS = 7;
		
		public Pizza (String name, boolean calzone, int number){
			int l = name.length();
			if ((l < 4)||(l > 20)){
				name = customerName +"_"+ (pizzaArraySize + 1);
			}
			pizzaName = name;
			ingredients = new Ingredient[MAX_NUMBER_OF_INGREDIENTS];
			count = number;
			numberOfPizzas+=number;
			if (calzone){
				ingredients[0] = new Ingredient("Pizza Base(Calsone)", 1.5);
				cost += 1.5;
				numberOfIngredients++;
			}
			else{
				ingredients[0] = new Ingredient("Pizza Base(Default)", 1);
				cost += 1;
				numberOfIngredients++;	
			}

		}
		public double getCost(){
			return cost;
		}
		public int getCount(){
			return count;
		}
		public void printPizza(){
			String s;
			s = "["+ orderNumber + ":" 
				+ customerNumber + ":" 
				+ pizzaName + ":"
				+count+"]";
			System.out.println(s);
		}
		public String toString(){
			String s;
			String border = "-----------------------------\n";

			s = "Name:"+pizzaName+"\n"+ border;
			for (int i = 0; i < numberOfIngredients; i++){
				s += String.format("%-24s",ingredients[i].getName())+ String.format("%.2f",ingredients[i].getPrice())+" €\n";
			}
			s += border+"Total:"+String.format("%22.2f",cost) +" €\n";
			s += "Count:"+ String.format("%24d",count) + "\n" + border;

			return s;
		}
		public String getPizzaName(){
			return pizzaName;
		}
		public void editNumber(int newNumber){
			numberOfPizzas-=count - newNumber;
			count = newNumber;
			
		}
		public void addIngredient(String name){
			
			if (numberOfIngredients-1==MAX_NUMBER_OF_INGREDIENTS){
				System.out.println("You cannot add more than "+ MAX_NUMBER_OF_INGREDIENTS + " ingredients");
				return;
			}
			for (int i =0; i < numberOfIngredients; i++){
				if (ingredients[i].getName().equals(name)){
					System.out.println("You have already added "+name);
					return;
				}
			}
			for (Ingredient ing: allowedIngredients){
				if (ing.getName().equals(name)){
					ingredients[numberOfIngredients] = new Ingredient(name, ing.getPrice());
					numberOfIngredients++;
					cost += ing.getPrice();
					System.out.println(name + " has been added");		
					return;
				}
			}
			
		}
	}
	public void addPizza(Pizza pizza){
		pizzaArray[pizzaArraySize] = pizza;
		pizzaArraySize++;
		totalSum += pizza.getCost()*pizza.getCount();
	}
	public void deletePizza(String name){
		for (int i = 0; i < pizzaArraySize; i++){
			if (pizzaArray[i].getPizzaName().equals(name)){
				numberOfPizzas-=pizzaArray[i].getCount();
				totalSum -= pizzaArray[i].getCost()*pizzaArray[i].getCount();
				int j = i;
				while (pizzaArray[j]!=null){
					pizzaArray[j]=pizzaArray[j+1];
					j++;
				}
				pizzaArraySize--;
				return;
			}
		}
		System.out.println("Couldn't find pizza with name "+name+". Please try again");
	}
	public void changePizzaArraySize(String name, int newNumber){
		if (newNumber == 0){
			deletePizza(name);
		}
		else{
			for (int i = 0; i < pizzaArraySize; i++){
				if (pizzaArray[i].getPizzaName().equals(name)){
					totalSum -= pizzaArray[i].getCost()*(pizzaArray[i].getCount() - newNumber);
					pizzaArray[i].editNumber(newNumber);

					return;
				}
			}
			System.out.println("Couldn't find pizza with name "+name+". Please try again");
		}
	}
	public String toString(){
		
		String outBorder = "******************************\n";
		String s = outBorder +
					"Order:"+orderNumber+"\n" +
					"Customer:"+customerNumber+"\n";
		for (int i = 0; i < pizzaArraySize; i++){
			s += pizzaArray[i];
			
		}
		s += "Total sum:" + String.format("%18.2f",totalSum) + " €\n";
		s += outBorder;
		return s;
	}


}