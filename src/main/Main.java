/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.HracProstor;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.VeciProstor;
import GUI.VezmiCombo;
import GUI.VybavaObsah;
import GUI.VychodyCombo;
import GUI.ZahodCombo;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 * Hlavni trida - slouzi ke spusteni aplikace
 * @author dominik
 */
public class Main extends Application {

    private TextArea centralText;
    private IHra hra;

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    
    private TextField zadejPrikazTextArea;
    private VychodyCombo vychodyCombo;
    private ZahodCombo zahodCombo;
    private VezmiCombo vezmiCombo;
    private Mapa mapa;
    private MenuLista menuLista;
    private VybavaObsah vybavaObsah;
    private VeciProstor veciProstor;
    private HracProstor hracProstor;
    private Stage stage;
    private Button button;
    private Button button2;
    private Button button3;
    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        
        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        vybavaObsah = new VybavaObsah(hra);
        veciProstor = new VeciProstor(hra);
        hracProstor = new HracProstor(hra);
        vychodyCombo = new VychodyCombo(hra);
        zahodCombo = new ZahodCombo(hra);
        vezmiCombo = new VezmiCombo(hra);
        button = new Button("Přejdi");        
        button3 = new Button("Vezmi");
        button2 = new Button("Zahod");
        
        BorderPane borderPane = new BorderPane();
        VBox box = new VBox();
        BorderPane buttonPane = new BorderPane();        
        BorderPane button2Pane = new BorderPane();
        BorderPane button3Pane = new BorderPane();
      
        buttonPane.setPadding(new Insets(15, 25, 15, 5));        
        button2Pane.setPadding(new Insets(15, 25, 15, 5));
        button3Pane.setPadding(new Insets(15, 25, 15, 5));
        
    
        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        initComboBox();
        
        initVezmiBox();
        initZahodBox();
        
        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //zadejPrikazLabel.setMaxWidth(1);
        
        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setMaxWidth(85);
        
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextArea.setText("");
                
                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        
        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextArea, buttonPane, button3Pane, button2Pane);
        
       
        
        
        borderPane.setLeft(mapa);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);        
        borderPane.setRight(box);
        box.getChildren().addAll(new Label (" Protihrac:"), hracProstor, new Label(" Vec(i) v danem pasmu:"), veciProstor, new Label(" Výbava:"), vybavaObsah);
        
        
        
        Scene scene = new Scene(borderPane, 850, 350);
        
        
        buttonPane.setLeft(vychodyCombo);
        buttonPane.setRight(button);
        button3Pane.setLeft(vezmiCombo);
        button3Pane.setRight(button3);
        button2Pane.setLeft(zahodCombo);
        button2Pane.setRight(button2);
        
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextArea.requestFocus();
    }

    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /*
    Spusti ComboBox s vychody a provede jejich prikaz
   
    */
    private void initComboBox() {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value = (String) vychodyCombo.getComboBox().getValue();
                String prikaz = "jdi " + value;
                String text = hra.zpracujPrikaz(prikaz);

                
                centralText.appendText("\n\n" + prikaz + "\n");
                centralText.appendText("\n\n" + text + "\n");

                zadejPrikazTextArea.setText("");

               
            }
        });
    }
    
    private void initZahodBox() {
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value = (String) zahodCombo.getComboBox().getValue();
                String prikaz = "zahod " + value;
                String text = hra.zpracujPrikaz(prikaz);

                
                centralText.appendText("\n\n" + prikaz + "\n");
                centralText.appendText("\n\n" + text + "\n");

                zadejPrikazTextArea.setText("");

              
            }
        });
    }
    
    private void initVezmiBox() {
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String value = (String) vezmiCombo.getComboBox().getValue();
                String prikaz = "vezmi " + value;
                String text = hra.zpracujPrikaz(prikaz);

             
                centralText.appendText("\n\n" + prikaz + "\n");
                centralText.appendText("\n\n" + text + "\n");

                zadejPrikazTextArea.setText("");

              
            }
        });
    }
    /*
    Metoda nastavi pomocne funkce pro observery v jinych tridach po spusteni nove hry
    @param novaHra
   
    */
    
    public void newGame(IHra novaHra){
        this.hra = novaHra;
        this.mapa.newGame(novaHra);
        this.centralText.setText(novaHra.vratUvitani());
        this.zahodCombo.novaHra(hra);
        this.vychodyCombo.novaHra(hra);
        this.vezmiCombo.novaHra(hra);
        this.vybavaObsah.novaHra(hra);
        this.veciProstor.novaHra(hra);
        this.hracProstor.novaHra(hra);
        
        
        
    }

}