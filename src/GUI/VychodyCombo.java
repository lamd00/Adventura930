/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 * Trida slouzi k zobrazeni seznamu s vychody z aktualniho prostoru a moznosti prechodu
 * @author Dominik
 * 
 *  
 */
public class VychodyCombo extends ComboBox implements Observer {
    
    private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    String value = " ";
    
    /* 
    Konstruktor vytvori seznam s moznymi vychody z daneho prostoru
    @param hra
    
    */
    public VychodyCombo(IHra hra) {
        this.hra = hra;
        init();
    }
    /*
    vygeneruje combo box
    */
    public ComboBox getComboBox() {
        return this;
    }
    /*
    Inicializace panelu, predmety, ktere v nem jsou (options - mozne vychody), defaultni napis v radku, rozmery a zavolani observera
    
    */

    public void init() {
        this.setItems(options);
        this.setPromptText("Pásmo");
        this.setEditable(true);
        this.setPrefWidth(100);
        hra.getHerniPlan().registerObserver(this);
        update();

      }

    /**
     * Metoda   nejdrive smaze vychody ze stareho prostoru a pote vypise nove vychody
     */
    @Override
    public void update() {
        options.clear();
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()) {
            options.add(prostor.getNazev());
        }
    }
    
    

    /**
     * Observeri, kteri aktualizuji seznam pri nove hre
     * @param hra slouzi k ukonceni hry
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

}
    
    
    
    
    

