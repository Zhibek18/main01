package main01.kakimzhanova.pizza.report;
import main01.kakimzhanova.pizza.entity.Order;
public class OrderReport{
	public static void printOrderReport(Order order){
		System.out.println("Printing order report:\n" + order.getOrderReport());
	}
}
