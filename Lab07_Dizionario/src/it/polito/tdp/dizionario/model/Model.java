package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	Graph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
	public List<String> createGraph(int numeroLettere) {
		WordDAO dao=new WordDAO();
		List<String> parole=new ArrayList<String>();
		
		parole=dao.getAllWordsFixedLength(numeroLettere);
		
		for(int i=0; i<parole.size(); i++){
			grafo.addVertex(parole.get(i));
		}
		
		for(int i=0; i<parole.size(); i++){
			int count=0;
			String a=parole.get(i);
			for(int j=i; j<parole.size(); j++){
				String b=parole.get(j);
				for(int k=0; k<a.length() && count<3; k++){
					 if(a.charAt(k) != b.charAt(k)){
						 count++;
					 }
				}
				if(count==1){
					grafo.addEdge(a,b);
				}
			}
		}
		System.out.println(grafo);
		return parole;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		
		for (DefaultEdge arch: grafo.edgesOf(parolaInserita)) {

			System.out.format("-- adiacent vertex: %s\n", Graphs.getOppositeVertex(grafo, arch, parolaInserita));

			if (grafo.getEdgeSource(arch) == parolaInserita) {
				System.out.format("adiacent vertex: %s\n", grafo.getEdgeTarget(arch));
			} else {
				System.out.format("adiacent vertex: %s\n", grafo.getEdgeSource(arch));
			}
		}
		
		
		
		
		
		
		return new ArrayList<String>();
	}

	public String findMaxDegree() {
		System.out.println("Model -- TODO");
		return "";
	}
}
