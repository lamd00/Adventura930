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
import logika.HerniPlan;
import logika.Protihrac;
import utils.Observer;
import logika.IHra;
import main.Main;


/**
 * Trida slouzi k zobrazeni panelu s aktualnimi predmety
 *
 * @author Dominik
 */
public class HracProstor extends ListView implements Observer {

    private IHra hra;
    private HerniPlan plan;
    private ObservableList<Object> hraci = FXCollections.observableArrayList();

    /**
     * 
     *
     * Vytvoreni panelu
     * @param hra slouzi k ukonceni hry
     */
    public HracProstor(IHra hra) {
        this.hra = hra;
        init();
    }
    
    /*
    Inicializace panelu, predmety, ktere v nem jsou (hraci), rozmery a zavolani observera
    
    */
    
    private void init() {
        this.setItems(hraci);        
        this.setPrefSize(200, 306);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Metoda vypise seznam veci v podobe obrazku, obrazky se pridruzi k predmetum pomoci udaju v tr. HerniPlan
     * 
     */
    @Override
    public void update() {

       
        
        Protihrac sHraci = hra.getHerniPlan().getAktualniProstor().getProtihrac();
        hraci.clear();
        if (sHraci != null && !sHraci.isPorazen()){
        
       
            ImageView obraz
                     
                    = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/" + sHraci.getObraz()), 145, 155, false, false));
            hraci.add(obraz);
        }
        }
    

    /**
     * 
     * Panel se pri spusteni nove hry vyprazdni
     * @param hra slouzi k ukonceni hry
     */
    public void novaHra(IHra hra) {
        // this.hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        this.hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    

    
}
