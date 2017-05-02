package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	Model model;
	public void setModel(Model model){
		this.model=model;	
	}
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	@FXML
    private Button btnTrovaTuttiVicini;
	
	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
		inputParola.clear();
		inputNumeroLettere.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		try {
			int num;
			String s=inputNumeroLettere.getText();
			num=Integer.parseInt(s);
			model.createGraph(num);
			
			txtResult.appendText("Grafo generato.\n");
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		try {
			txtResult.appendText(model.findMaxDegree());

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		try {
			String s=inputParola.getText();
			String numero=inputNumeroLettere.getText();
			int num=Integer.parseInt(numero);
			if(s.length()!=num || s.compareTo("")==0 || s==null || s.matches("[0-9]*")){
				txtResult.appendText("Valore non valido.\n");
				return;
			}
			model.displayNeighbours(s);
			txtResult.appendText("Vicini trovati:\n");
			for(String parola: model.displayNeighbours(s)){
				txtResult.appendText(parola+"\n");
			}

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}
	@FXML
    void doTrovaTuttiVicini(ActionEvent event) {
		String s=inputParola.getText();
		if(s.compareTo("")==0 || s==null || s.matches("[0-9]*")){
			txtResult.appendText("Valore non valido.\n");
			return;
		}
		txtResult.appendText("Vertici raggiungibili:\n");
		for(String parola: model.getNodiAdiacenti(s)){
			txtResult.appendText(parola+"-");
		}
    }

	  @FXML
	    void initialize() {
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGradoMax\" was not injected: check your FXML file 'Dizionario.fxml'.";
	        assert btnTrovaTuttiVicini != null : "fx:id=\"btnTrovaTuttiVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
	    }
}