package main01.kakimzhanova.pizza.entity;

public class Client{
	private int clientId;
	private String clientName;

	public Client(int id, String name){
		clientId = id;
		clientName = name;
	}
	public int getClientId(){
		return clientId;
	}
	public String getClientName(){
		return clientName;
	}
}