package it.polito.tdp.genes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulator {
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	//modello del mondo
	
	private List<Genes> geneStudiato; 
	
	//parametri di input
	private Genes startGene;
	private int nTotIng;
	private int TMAX=36;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private double probMantenereGene=0.3; 
	//valori calcolati
	public Genes getStartGene() {
		return startGene;
	}
	public void setStartGene(Genes startGene, int n, Graph<Genes, DefaultWeightedEdge> grafo ) {
		this.startGene = startGene;
		this.nTotIng=n;
		this.grafo=grafo;
		this.queue= new PriorityQueue<>();
		for(int nIng=0;nIng<this.nTotIng;nIng++) {
			this.queue.add(new Event(0, nIng));
		}
		this.geneStudiato= new ArrayList<>();
		for(int nIng=0;nIng<this.nTotIng;nIng++) {
			this.geneStudiato.add(this.startGene);
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event ev= queue.poll();
			int T= ev.getT();
			int nIng=ev.getnIng();
			Genes g= this.geneStudiato.get(nIng);
			
			if(T<this.TMAX) {
				if(Math.random()<this.probMantenereGene) {
					this.queue.add(new Event(T+1, nIng));
				}else {
					
				}
				
				
			}
		}
	}
	public int getnTotIng() {
		return nTotIng;
	}
	public void setnTotIng(int nTotIng) {
		this.nTotIng = nTotIng;
	}
	
	
	

}
