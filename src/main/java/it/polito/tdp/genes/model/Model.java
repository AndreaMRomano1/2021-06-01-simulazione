package it.polito.tdp.genes.model;

import java.util.*;
import java.util.stream.Collectors;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.GraphSpecificsStrategy;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
		private List<Genes> essentialGenes;
		private Graph<Genes, DefaultWeightedEdge> grafo; 
		private Map<String, Genes> essentialGenesIdMap;
		
		
		public List<Genes> getEssentialGenes() {
			return essentialGenes;
		}


		public void setEssentialGenes(List<Genes> essentialGenes) {
			this.essentialGenes = essentialGenes;
		}


		public Graph<Genes, DefaultWeightedEdge> getGrafo() {
			return grafo;
		}


		public void setGrafo(Graph<Genes, DefaultWeightedEdge> grafo) {
			this.grafo = grafo;
		}


		public Map<String, Genes> getEssentialGenesIdMap() {
			return essentialGenesIdMap;
		}


		public void setEssentialGenesIdMap(Map<String, Genes> essentialGenesIdMap) {
			this.essentialGenesIdMap = essentialGenesIdMap;
		}


		public Model(List<Genes> essentialGenes, Graph<Genes, DefaultWeightedEdge> grafo,
				Map<String, Genes> essentialGenesIdMap) {
		
			this.essentialGenes = essentialGenes;
			this.grafo = grafo;
			this.essentialGenesIdMap = essentialGenesIdMap;
		}


		public Model() {
			// TODO Auto-generated constructor stub
		}


		public String CreaGrafo() {
			GenesDao dao= new GenesDao();
			this.essentialGenes=dao.getAllEssentialGenes();
			this.essentialGenesIdMap= new HashMap<>();
			for(Genes g: this.essentialGenes) {
				this.essentialGenesIdMap.put(g.getGeneId(), g);
				
			}
			this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
			Graphs.addAllVertices(this.grafo, this.essentialGenes);
			List<Interactions> archi = dao.getInteractions(essentialGenesIdMap);
			for(Interactions arco: archi) {
				if(arco.getGene1().getChromosome()==arco.getGene2().getChromosome()) {
					Graphs.addEdge(this.grafo, arco.getGene1(), arco.getGene2(),Math.abs( arco.getExpressionCorr()*2.0));
				}else {
					Graphs.addEdge(this.grafo, arco.getGene1(), arco.getGene2(),Math.abs(arco.getExpressionCorr()));
					
				}
			}
			return String.format("Grafo creato con successo %d vertici e %d archi \n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
		}
		
		public List<Adiacente> getGeniAdiacenti(Genes g){
			List<Genes> vicini= Graphs.neighborListOf(this.grafo, g);
			List<Adiacente> result= new ArrayList<Adiacente>();
			for(Genes v: vicini) {
				result.add(new Adiacente(v, this.grafo.getEdgeWeight(this.grafo.getEdge(g,v))));
				
			}
			Comparator<Adiacente> compara= Comparator.comparingDouble(Adiacente::getPeso).reversed(); 
			List<Adiacente> x= result.stream().sorted(compara).collect(Collectors.toList());
			return x;
		}
	
}
