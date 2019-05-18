package main01.kakimzhanova.pizza.report;
import java.io.*;
import main01.kakimzhanova.pizza.entity.Order;
public class OrderFileReport{
	public static void printFileOrderReport(String fileName, Order order){
		File f = new File(fileName);
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(f, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(order.getOrderReport());
		}catch (IOException e){
			System.err.println("Error while opening file: " + e);
		}finally {
			if (pw != null){
				pw.close();
			}
		}
	}
}