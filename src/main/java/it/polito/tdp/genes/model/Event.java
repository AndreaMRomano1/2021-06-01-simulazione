package it.polito.tdp.genes.model;

public class Event {
	private int T; //tempo
	private int nIng; //numero ingegnere
	public Event(int t, int nIng) {
		super();
		T = t;
		this.nIng = nIng;
	}
	public int getT() {
		return T;
	}
	public int getnIng() {
		return nIng;
	}
	
}
