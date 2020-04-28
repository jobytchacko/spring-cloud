package com.ms.currencyconversionservice;

public class CurrencyConversionBean {
	
	String from;
	String to;
	int quantity;
	int rate;
	int amount;
	int port;
	
	public CurrencyConversionBean() {
		
	}

	public CurrencyConversionBean(String from, String to, int quantity, int rate, int port) {
		super();
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = this.rate * this.quantity;
		this.port = port;
		// TODO Auto-generated constructor stub
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
