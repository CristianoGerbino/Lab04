package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> ComboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtinserisciMatricola;

    @FXML
    private Button btnRestituisciStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    public void setModel(Model model) {
    	this.model = model;
    	Corso c = new Corso ("", 1, "", 1);
    	this.ComboBox.getItems().add(c);
    	this.ComboBox.getItems().addAll(model.getAllCorsi());
  
    }
    	

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String s = this.txtinserisciMatricola.getText();
    	int matricola;
    	
    	if (s.equals("")) {
    		this.txtResult.appendText("Inserisci un numero di matricola\n");
    		return;
    	}
    	
    	try {
    		matricola = Integer.parseInt(s);
    		Studente st = this.model.getStudenteByMatricola(matricola);
    		
    		if (st == null) {
    			this.txtResult.appendText("Non esiste nessuno studente con tale numero di matricola!\n");
    		}
    		
    		for (Corso c : model.getCorsiByStudente(matricola) ) {
    			this.txtResult.appendText("\n"+c.stampaCorso());
        	}
    
    	} catch (NumberFormatException e) {
		this.txtResult.appendText("La matricola è composta da soli numeri\n");
		e.printStackTrace();
	}
    	
    }
    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	Corso c = this.ComboBox.getValue();
    	if (c == null || c.getNome().equals("")) {
    		this.txtResult.appendText("Errore: devi selezionare un corso!\n");
    		return;
    	}
    	for (Studente s : model.getStudentiPerCorso(c)) {
    		this.txtResult.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void giveMeStudent(ActionEvent event) {
    	String s = this.txtinserisciMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(s);
    		Studente st = this.model.getStudenteByMatricola(matricola);
    		
    		if (st == null) {
    			this.txtResult.appendText("Non esiste nessuno studente con tale numero di matricola!\n");
    		}
    		else {
	    		this.txtNome.setText(st.getNome());
	    		this.txtCognome.setText(st.getCognome());
    		}
	    		
    	} catch (NumberFormatException e) {
    		this.txtResult.appendText("La matricola è composta da soli numeri\n");
    		e.printStackTrace();
    	}
    	

    }

    @FXML
    void initialize() {
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtinserisciMatricola != null : "fx:id=\"txtinserisciMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnRestituisciStudente != null : "fx:id=\"btnRestituisciStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
    }
}
