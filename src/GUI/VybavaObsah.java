/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import static java.awt.Color.green;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javax.management.Query.value;
import logika.Vec;
import utils.Observer;
import logika.IHra;
import main.Main;

/**
 * 
 *
 * @author Dominik
 */
public class VybavaObsah extends ListView implements Observer {

    private IHra hra;
    private ObservableList<Object> data = FXCollections.observableArrayList();

    /**
     * 
     *
     *
     * @param hra slouzi k ukonceni hry
     */
    public VybavaObsah(IHra hra) {
        this.hra = hra;
        init();
    }
    
    /*
    Inicializace panelu, predmety, ktere v nem jsou (data - veci ve vybave), rozmery a zavolani observera
    
    */

    private void init() {
        this.setItems(data);
        this.setPrefSize(200, 280);      
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Vypise seznam predmetu ve vybave a priradi k nim obrazek
     * 
     */
    @Override
    public void update() {

        Map<String, Vec> seznamVeci;
        seznamVeci = hra.getHerniPlan().getVybava().getCelaVybava();
        data.clear();
        for (String x : seznamVeci.keySet()) {
            Vec helper = seznamVeci.get(x);
            ImageView obrazek
                     
                    = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + helper.getObrazek()), 160, 120, false, false));

            data.add(obrazek);
        }
    }

    /**
     * Pri zavolani nove hry se obrazky ve vybave vymazou
     *
     * @param hra slouzi k ukonceni hry
     */
    public void novaHra(IHra hra) {
        // this.hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

    
}
