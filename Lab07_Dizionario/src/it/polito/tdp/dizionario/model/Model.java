package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	List<String> parole=new ArrayList<String>();
	
	public List<String> createGraph(int numeroLettere) {
		WordDAO dao=new WordDAO();
		
		parole=dao.getAllWordsFixedLength(numeroLettere);
		
		for(int i=0; i<parole.size(); i++){
			grafo.addVertex(parole.get(i));
		}
		for(String s: grafo.vertexSet()){
			List<String> verticiVicini=dao.getAllSimilarWords(s, numeroLettere);
			System.out.println(s);
			System.out.println(verticiVicini);
			for(String vertice: verticiVicini){
				if(!s.equals(vertice))
					grafo.addEdge(s, vertice);
			}
		}
		
//		for(String parola: parole){
//			List<String> paroleVicine=this.getParoleSimili(parola);
//			for(String b: paroleVicine){
//				grafo.addEdge(parola, b);
//			}
//		}
		return parole;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> vicini=Graphs.neighborListOf(grafo, parolaInserita);		
		return vicini;
	}

	public String findMaxDegree() {
		int num=0;
		int grado=0;
		String verticeGradoMax="";
		for(String parola: grafo.vertexSet()){
			if(grafo.degreeOf(parola)>num){
				verticeGradoMax=parola;
				grado=grafo.degreeOf(parola);
				num=grado;
			}
		}
		String temp="";
		for(String s: this.getParoleSimili(verticeGradoMax)){
			temp+=s+"-";
		}
		String ris="Grado massimo:\n"+verticeGradoMax+": grado "+grado+"\nVicini: "+temp+"\n";
		return ris;
	}
	
	public List<String> getParoleSimili(String a){
		int count=0;
		List<String> paroleVicine=new ArrayList<String>();
		for(String parola: parole){
			count=0;
			if(parola.compareTo(a)!=0){
				for(int k=0; k<a.length(); k++){
					 if(a.charAt(k) != parola.charAt(k)){
						 count++;
					 }
				}
				if(count==1){
					paroleVicine.add(parola);
				}
			}
		}
		return paroleVicine;	
	}
}
