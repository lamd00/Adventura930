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
import logika.Vec;
import utils.Observer;

/**
 *
 * @author Dominik
 * 
 * 
 */
public class ZahodCombo extends ComboBox implements Observer {
    
    /*
    
    Konstruktor tvori seznam, ve kterem se zobrazi predmety, ktere se daji zahodit
    @param hra
    */
    private IHra hra;
    private final ObservableList<String> options = FXCollections.observableArrayList();
    String value = " ";
    
    public ZahodCombo(IHra hra) {
        this.hra = hra;
        init();
    }
    
    /*
    
    Vyvola combo box
    */
    
    public ComboBox getComboBox() {
        return this;
    }

    /*
    Nastavi vlastnosti combo boxu - napis, editovatelnost, sirku a observera
    
    */
    public void init() {
        this.setItems(options);
        this.setPromptText("Předmět");
        this.setEditable(true);
        this.setPrefWidth(100);
        hra.getHerniPlan().registerObserver(this);
        update();

      }

    /**
     * Pri shozeni itemu se predmet vymaze a aktualizuje a vypise seznam veci ve vybave
     */
    public void update() {
        options.clear();
        for (String vec : hra.getHerniPlan().getVybava().getCelaVybava().keySet()) {
            options.add(vec);
        }
    }

    /**
     * Observeri aktualizuji combo box pri nove hre
     * @param hra
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

}