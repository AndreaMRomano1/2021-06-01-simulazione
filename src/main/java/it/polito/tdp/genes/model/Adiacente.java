package it.polito.tdp.genes.model;

public class Adiacente {
	private Genes gene;
	private Double peso;
	public Adiacente(Genes gene, Double peso) {
		super();
		this.gene = gene;
		this.peso = peso;
	}
	public Genes getGene() {
		return gene;
	}
	public void setGene(Genes gene) {
		this.gene = gene;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
}
	
