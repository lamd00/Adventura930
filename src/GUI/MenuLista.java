/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 * Trida slouzi k vytvoreni listy, ve ktere bude nabidka s napovedou, novou hrou atd.
 * @author dominik
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    
    /*
    Vytvoreni listy
    @param hra
    @param main
    */
    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
    }
    /*
    Polozky v nabidce
    */
    
    private void init(){
        
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Pomoc!");
        
        MenuItem novaHra = new MenuItem("Nova hra");
        
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        MenuItem konecHry = new MenuItem("Konec hry");
        
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Napoveda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        novaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            /*
            Funkce tlacitka nova hra - spusti novou hru
            */
            public void handle(ActionEvent event) {
                hra = new Hra();
                main.newGame(hra);
            }
        });
        
        oProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            
            /*
            Okenko O programu            
            */
            public void handle(ActionEvent event) {
            
                Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                
                oProgramuAlert.setTitle("Adventura - projekt v predmetu Softwarove inzenyrstvi 4IT115 na VSE v Praze");
                oProgramuAlert.setHeaderText("Jednoducha aplikace v jazyce Java - hokejova hra");
                oProgramuAlert.setContentText("Dominik Lamacz (lamd00), ZS 2017/18");
                oProgramuAlert.initOwner(main.getStage());
                
                oProgramuAlert.showAndWait();
            }
        });
        /*
        Okenko s Napovedou
        
        */
        
       napovedaItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                
                WebView webView = new WebView();
                
                webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webView, 500,500));
                stage.show();
            
            }
        });
        
        
    }
    
}
