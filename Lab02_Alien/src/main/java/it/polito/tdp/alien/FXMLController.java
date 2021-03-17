package it.polito.tdp.alien;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    DizionarioAlieno dizionarioAlieno = new DizionarioAlieno();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserisci;

    @FXML
    private TextArea txtTraduzione;


    @FXML
    void doClear(ActionEvent event) {
    	txtTraduzione.setText("");
    	dizionarioAlieno.svuota();
         
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	txtTraduzione.clear();
    	String testo=txtInserisci.getText();
    	if(testo.matches("(?=.*[0-9]).{1,2000}") || testo.matches("(?=.*[@#!]).{1,2000}" )) {
    		txtTraduzione.setText("Errore inserire solo lettere");
    		return;
    	}
    	
    	testo.toLowerCase();
    	String[] arrayTesto=testo.split(" ");
    	if(arrayTesto.length==0 || arrayTesto[0]=="" || arrayTesto[0]==" ") {
    		txtTraduzione.setText("Errore inserire almeno 1 parola");
    		return;
    	}
    	if(arrayTesto.length>2) {
    		//txtTraduzione.setText("Errore inserire al massimo 2 parole"); Esercizio 1
    		//return;
    		LinkedList<String> temp=new LinkedList<String>();
    		for(int i=1;i<arrayTesto.length;i++) {
    			String s=arrayTesto[i];
    			temp.add(s);
    		}
    		String inizio=arrayTesto[0];
    		dizionarioAlieno.add2(inizio, temp);
    		txtInserisci.clear();
    		
    	}
    	if (arrayTesto.length==1) {
    		if(!arrayTesto[0].matches("(?=.*[?]).{1,2000}")) {
    		if( dizionarioAlieno.listaParoleNelDizionario2.size()==0) {
    			txtTraduzione.setText("Errore dizionario vuoto");
        		return;
    		}
    		String s=dizionarioAlieno.traduzione(arrayTesto[0]);
    		txtTraduzione.setText(s);
    		txtInserisci.clear();
    		}
    		else {
    			String s=dizionarioAlieno.traduzione(arrayTesto[0].replace('?','.'));
    			txtTraduzione.setText(s);
        		txtInserisci.clear();
    		}
    		
    		
    		
    		
    		
    	}
    	if(arrayTesto.length==2) {
    		LinkedList<String> lista=new LinkedList<String>();
    		lista.add(arrayTesto[1]);
    		dizionarioAlieno.add2(arrayTesto[0],lista);
    		txtInserisci.clear();
    		
    		
    		
    	}
    	
    	

    }
    
    
    @FXML
    void initialize() {

    }
}