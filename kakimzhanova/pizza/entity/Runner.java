package main01.kakimzhanova.pizza.entity;

import java.util.Scanner;

public class Runner{
	public Order.Pizza chooseIngredients(Scanner scan, Order.Pizza myPizza){
		String s;
		System.out.println("Please choose the ingredients from:");
		for (int i = 0; i < Order.allowedIngredients.length; i++){
			System.out.println( (i+1) + ". " +
			String.format("%-20s",Order.allowedIngredients[i].getName()) +
			String.format(" %.2f", Order.allowedIngredients[i].getPrice())+" €");
		}
		System.out.println("-----------------------");
		System.out.print("To add ingredient type its number.");
		System.out.println(" To check added ingredients type <end>");
		s=scan.next();

		while (!s.equals("end")){
			myPizza.addIngredient(Integer.valueOf(s));
			s = scan.next();
		}
		return myPizza;
	}


	public Order.Pizza createPizza(Scanner scan, Order newOrder){
		String s;
		String name;
		boolean calsone = false;
		int n;
		System.out.println("Please choose name for your pizza:");
		name = scan.next();
		System.out.println("Do you want Calsone pizza? Type yes or no");
		s = scan.next();
		while ((!s.equalsIgnoreCase("yes"))&&(!s.equalsIgnoreCase("no"))){
			System.out.println("Do you want Calsone pizza? Type yes or no");
			s = scan.next();
		}
		if (s.equalsIgnoreCase("yes"))
			calsone = true;

		System.out.println("How many "+ name +" pizzas do you need?");
		n = scan.nextInt();
		while (newOrder.getNumberOfPizzas() + n > Order.MAX_NUMBER_OF_PIZZAS){
			System.out.println("You cannot order more than "+ Order.MAX_NUMBER_OF_PIZZAS+ " pizzas. How many "+ name +" pizzas do you need?");
			n = scan.nextInt();
		}
		Order.Pizza newPizza = newOrder.new Pizza(name, calsone, n);
		chooseIngredients(scan, newPizza);
		return newPizza;
	}



	public static void main(String[] args){
		Runner runner = new Runner();
		Scanner scan = new Scanner(System.in);
		while (true){
			System.out.println("Welcome to „Palmetto“!\nTo make an order type <order>.To exit type <exit>");
			String s = scan.next();
			if (s.equalsIgnoreCase("exit")){
				break;
			}
			if (s.equalsIgnoreCase("order")){
				System.out.println("Please enter your customer number:");
				int cusNum = scan.nextInt();
				System.out.println("Please enter your name:");
				Order newOrder = new Order(scan.next(), cusNum);
				for (int j = 0; j < 10; j++){


					Order.Pizza newPizza = runner.createPizza(scan, newOrder);
					System.out.println(newPizza);

					do{
						System.out.print("If everything is right type <ok>."); 
						System.out.println("To change number of pizzas please type <edit>");
						s = scan.next();

					}while((!s.equalsIgnoreCase("ok"))&&(!s.equalsIgnoreCase("edit")));
					
					if (s.equalsIgnoreCase("edit")){
						System.out.println("How many "+ newPizza.getPizzaName() +" pizzas do you need?");
						int n = scan.nextInt();
						while (newOrder.getNumberOfPizzas()- newPizza.getCount() + n > Order.MAX_NUMBER_OF_PIZZAS){
							System.out.println("You cannot order more than "+ Order.MAX_NUMBER_OF_PIZZAS+ " pizzas. How many "+ newPizza.getPizzaName() +" pizzas do you need?");
							n = scan.nextInt();
						}
						newPizza.editNumber(n);
					}
					newOrder.addPizza(newPizza);
					System.out.println(newOrder);
					System.out.println("Please chek your order. If everything is right type <ok>. To remove pizza from your order type <remove>");
					s = scan.next();
					while (!s.equalsIgnoreCase("ok")){
						if (!s.equalsIgnoreCase("remove")){
							System.out.println("Please check your order. If everything is right type <ok>. To remove pizza from your order type <remove>");
							s = scan.next();
						}
						else{
							System.out.println("Please type the name of pizza you want to remove");
							newOrder.deletePizza(scan.next());
							System.out.println(newOrder);
							System.out.println("Please check your order. If everything is right type <ok>. To remove pizza from your order type <remove>");
							s = scan.next();
						}
					}
					System.out.println(newOrder);
					System.out.println("Do you want to add another pizza? Type yes or no");
					s = scan.next();
					while ((!s.equalsIgnoreCase("no"))&&(!s.equalsIgnoreCase("yes"))){
						System.out.println("Do you want to add another pizza? Type yes or no");
						s = scan.next();
					}
					if (s.equalsIgnoreCase("no"))
						break;
					else if (s.equalsIgnoreCase("yes")){
						if (newOrder.getNumberOfPizzas() == Order.MAX_NUMBER_OF_PIZZAS){
							System.out.println("You cannot order more than "+ Order.MAX_NUMBER_OF_PIZZAS+ " pizzas.");
							break;
						}
					}
				
				}
				System.out.println("Thank you!");
			}

		}
		scan.close();
	}
}