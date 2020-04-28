package com.ms.currencyexchangeservice;

public class ExchangeValue {
	
	public ExchangeValue() {
		// TODO Auto-generated constructor stub
	}
	
	public ExchangeValue(String from, String to, int port) {
		this.from = from;
		this.to = to;
		this.rate = 72;
		this.port = port;
	}
	
	
	String from;
	String to;
	int rate;
	int port;

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
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}



	
}
