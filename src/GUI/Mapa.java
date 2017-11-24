/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Trida slouzi k zobrazeni mapy a aktualni pozice hrace
 * @author dominik
 */
public class Mapa extends AnchorPane implements Observer {

    private IHra hra;
    private ImageView poloha;
    
/*
    Konstruktor vytvori mapu
    @param hra
    */
    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
/*
    Metoda vlozi obrazke mapy a hrace
    */
    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/hriste.jpg"), 200, 267, false, true));

        poloha = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/hrac.png"), 30, 30, false, true));
        
  

        this.getChildren().addAll(obrazekImageView, poloha);
        update();
    }
    /*
    
    Metoda aktualizuje pozici hrace pri nove hre
    @param novaHra
    */
    public void newGame(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
/*
    Umisteni panelu s mapou, nahore vlevo
    */
    @Override
    public void update() {
        this.setTopAnchor(poloha, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(poloha, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

}
