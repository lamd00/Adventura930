/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logika.Vec;
import utils.Observer;
import logika.IHra;
import main.Main;


/**
 * Trida slouzi k zobrazeni panelu s aktualnimi predmety
 *
 * @author Dominik
 */
public class VeciProstor extends ListView implements Observer {

    private IHra hra;
    private ObservableList<Object> veci = FXCollections.observableArrayList();

    /**
     * 
     *
     * Vytvoreni panelu
     * @param hra
     */
    public VeciProstor(IHra hra) {
        this.hra = hra;
        init();
    }
    
    private void init() {
        this.setItems(veci);        
        this.setPrefSize(200, 280);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Metoda vypise seznam veci v podobe obrazku, obrazky se pridruzi k predmetum pomoci udaju v tr. HerniPlan
     * 
     */
    @Override
    public void update() {

        Map<String, Vec> sVeci;
        sVeci = hra.getHerniPlan().getAktualniProstor().getVeci();
        
        veci.clear();
        for (String x : sVeci.keySet()) {
            Vec pomocna = sVeci.get(x);
            ImageView obrazek
                     
                    = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + pomocna.getObrazek()), 160, 120, false, false));

            veci.add(obrazek);
        }
    }

    /**
     * 
     * Panel se pri spusteni nove hry vyprazdni
     * @param hra
     */
    public void novaHra(IHra hra) {
        // this.hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }

    
}
