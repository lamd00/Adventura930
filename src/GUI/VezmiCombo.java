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
import utils.Observer;

/**
 *
 * @author Dominik
 */
public class VezmiCombo extends ComboBox implements Observer {
    
      private IHra hra;
    private final ObservableList<String> seznam = FXCollections.observableArrayList();
    String value = " ";
    
    public VezmiCombo(IHra hra) {
        this.hra = hra;
        init();
    }
    
    public ComboBox getComboBox() {
        return this;
    }

    public void init() {
        this.setItems(seznam);
        this.setPromptText("Předměty");
        this.setEditable(true);
        this.setPrefWidth(100);
        hra.getHerniPlan().registerObserver(this);
        update();

      }

    /**
     * 
     */
    public void update() {
        seznam.clear();
        for (String vec : hra.getHerniPlan().getAktualniProstor().getVeci().keySet()) {
            seznam.add(vec);
        }
    }
    
    

    /**
     * 
     */
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

}