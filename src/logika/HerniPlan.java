package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Dominik Lamacz 
 * @version    8.1. 2017
 */
public class HerniPlan implements Subject{
    private Vybava vybava; // soukroma vybava pro hplan
    private Prostor aktualniProstor;
    private boolean vyhra = false;
    private boolean prohra = false;
    private Prostor c1;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry(); // zalozeni prostoru
        vybava = new Vybava(); // zalozeni vybavy

    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor satna = new Prostor("satna","šatna - zvol obtížnost", 5,13);
        Prostor tezka = new Prostor("tezka","střídačka - skoč na led", 35,109);
        Prostor lehka = new Prostor("lehka","střídačka - skoč na led", 35,109);
        
        
        Prostor A1 = new Prostor("A1","A1 - levé útočné pásmo \n |A|B|C|\n1|X|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|_|_|", 62,35);
        Prostor A2 = new Prostor("A2","A2 - levé střední pásmo - nahoře\n |A|B|C|\n1|_|#|_|\n2|X|_|_|\n3|_|_|_|\n4|_|_|_|", 62,80);
        Prostor A3 = new Prostor("A3","A3 - levé střední pásmo - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|X|_|_|\n4|_|_|_|", 62,122);
        Prostor A4 = new Prostor("A4","A4 - levé obranné pásmo\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|X|_|_|", 62,189);
        Prostor B1 = new Prostor("B1","B1 - střed útočného pásma - brána\n |A|B |C|\n1|_|X#|_|\n2|_|__|_|\n3|_|__|_|\n4|_|__|_|", 104,35);
        Prostor B2 = new Prostor("B2","B2 - střed neutrálního pásma - nahoře\n |A|B|C|\n1|_|#|_|\n2|_|X|_|\n3|_|_|_|\n4|_|_|_|", 104,80);
        Prostor B3 = new Prostor("B3","B3 - střed neutrálního pásma - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|X|_|\n4|_|_|_|", 104,122);
        Prostor B4 = new Prostor("B4","B4 - střed obranného pásma\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|X|_|", 104,189);
        Prostor C1 = new Prostor("C1","C1 - pravé útočné pásmo\n |A|B|C|\n1|_|#|X|\n2|_|_|_|\n3|_|_|_|\n4|_|_|_|", 142,35);
        Prostor C2 = new Prostor("C2","C2 - pravé střední pásmo - nahoře\n |A|B|C|\n1|_|#|_|\n2|_|_|X|\n3|_|_|_|\n4|_|_|_|", 142,80);
        Prostor C3 = new Prostor("C3","C3 - pravé střední pásmo - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|X|\n4|_|_|_|", 142,122);
        Prostor C4 = new Prostor("C4","C4 - pravé obranné pásmo\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|_|X|", 142,189);
        
        Prostor a1 = new Prostor("a1","A1 - levé útočné pásmo \n |A|B|C|\n1|X|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|_|_|", 62,35);
        Prostor a2 = new Prostor("a2","A2 - levé střední pásmo - nahoře\n |A|B|C|\n1|_|#|_|\n2|X|_|_|\n3|_|_|_|\n4|_|_|_|", 62,80);
        Prostor a3 = new Prostor("a3","A3 - levé střední pásmo - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|X|_|_|\n4|_|_|_|", 62,122);
        Prostor a4 = new Prostor("a4","A4 - levé obranné pásmo\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|X|_|_|", 62,189);
        Prostor b1 = new Prostor("b1","B1 - střed útočného pásma - brána\n |A|B |C|\n1|_|X#|_|\n2|_|__|_|\n3|_|__|_|\n4|_|__|_|", 104,35);
        Prostor b2 = new Prostor("b2","B2 - střed neutrálního pásma - nahoře\n |A|B|C|\n1|_|#|_|\n2|_|X|_|\n3|_|_|_|\n4|_|_|_|", 104,80);
        Prostor b3 = new Prostor("b3","B3 - střed neutrálního pásma - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|X|_|\n4|_|_|_|", 104,122);
        Prostor b4 = new Prostor("b4","B4 - střed obranného pásma\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|X|_|", 104,189);
        Prostor c1 = new Prostor("c1","C1 - pravé útočné pásmo\n |A|B|C|\n1|_|#|X|\n2|_|_|_|\n3|_|_|_|\n4|_|_|_|", 142,35);
        Prostor c2 = new Prostor("c2","C2 - pravé střední pásmo - nahoře\n |A|B|C|\n1|_|#|_|\n2|_|_|X|\n3|_|_|_|\n4|_|_|_|", 142,80);
        Prostor c3 = new Prostor("c3","C3 - pravé střední pásmo - dole\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|X|\n4|_|_|_|", 142,122);
        Prostor c4 = new Prostor("c4","C4 - pravé obranné pásmo\n |A|B|C|\n1|_|#|_|\n2|_|_|_|\n3|_|_|_|\n4|_|_|X|", 142,189);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        satna.setVychod(lehka);
        satna.setVychod(tezka);
        
        lehka.setVychod(a3);
        tezka.setVychod(A3);
        
        
        
        A1.setVychod(B1);
        A1.setVychod(A2);

        B1.setVychod(A1);
        B1.setVychod(C1);
        B1.setVychod(B2);

        C1.setVychod(B1);
        C1.setVychod(C2);

        A2.setVychod(A1);
        A2.setVychod(B2);
        A2.setVychod(A3);

        B2.setVychod(B1);
        B2.setVychod(A2);
        B2.setVychod(C2);
        B2.setVychod(B3);

        C2.setVychod(C1);
        C2.setVychod(B2);
        C2.setVychod(C3);

        A3.setVychod(A2);
        A3.setVychod(B3);
        A3.setVychod(A4);
        
        B3.setVychod(A3);
        B3.setVychod(B2);
        B3.setVychod(C3);
        B3.setVychod(B4);

        C3.setVychod(C2);
        C3.setVychod(B3);
        C3.setVychod(C4);

        A4.setVychod(A3);
        A4.setVychod(B4);

        B4.setVychod(A4);
        B4.setVychod(B3);
        B4.setVychod(C4);

        C4.setVychod(C3);
        C4.setVychod(B4);
        
        
        a1.setVychod(b1);
        a1.setVychod(a2);

        b1.setVychod(a1);
        b1.setVychod(c1);
        b1.setVychod(b2);

        c1.setVychod(b1);
        c1.setVychod(c2);

        a2.setVychod(a1);
        a2.setVychod(b2);
        a2.setVychod(a3);

        b2.setVychod(b1);
        b2.setVychod(a2);
        b2.setVychod(c2);
        b2.setVychod(b3);

        c2.setVychod(c1);
        c2.setVychod(b2);
        c2.setVychod(c3);

        a3.setVychod(a2);
        a3.setVychod(b3);
        a3.setVychod(a4);
        
                

        b3.setVychod(a3);
        b3.setVychod(b2);
        b3.setVychod(c3);
        b3.setVychod(b4);

        c3.setVychod(c2);
        c3.setVychod(b3);
        c3.setVychod(c4);

        a4.setVychod(a3);
        a4.setVychod(b4);

        b4.setVychod(a4);
        b4.setVychod(b3);
        b4.setVychod(c4);

        c4.setVychod(c3);
        c4.setVychod(b4);

        // vytvoříme několik věcí
        Vec brana = new Vec("brana", "", false, true, "brana.png"); // nezvednutelna - nepotrebuje popis
        Vec paska = new Vec("paska", "zlatou páskou na hokejku", true, true, "paska.gif");
        Vec dres = new Vec("dres", "dresem Philadelphia Flyers - je oranzovy, abys ho mohl mit v sobotu\nna zapas, v nedeli na lov a zbytek tydne pro sbirani odpadku na ulici", true, true, "dres.gif");
        Vec hul = new Vec("hul", "vycházkovou holí - je pružná a pevná", true, true, "hul.png");
        Vec brusleLeva = new Vec("leva_brusle","", true, true, "brusleleva.png"); // brusle maji svuj popis definovany v podmince ve tride vezmi
        Vec bruslePrava = new Vec("prava_brusle","", true, true, "brusleprava.png");

        // umístíme věci do prostorů
        b1.vlozVec(brana);
        a3.vlozVec(paska);
        a1.vlozVec(paska); // paska bude na dvou mistech, je to ale stejny predmet, takze ikdyz ji zvedne podruhe, bude ji mit jen jednou
        c3.vlozVec(brusleLeva);
        b2.vlozVec(bruslePrava);
        a2.vlozVec(dres);
        c2.vlozVec(hul);
        
        
        B1.vlozVec(brana);
        C4.vlozVec(paska);
        A1.vlozVec(paska); // paska bude na dvou mistech, je to ale stejny predmet, takze ikdyz ji zvedne podruhe, bude ji mit jen jednou
        B2.vlozVec(bruslePrava);
        A4.vlozVec(bruslePrava);
        A2.vlozVec(dres);
        C2.vlozVec(hul);


        // vytvorime postavy, false = fyzicky hrac; true = zrucny hrac

        Protihrac TJOshie = new Protihrac("T. J. Oshie", true, "oshie.jpg");
        Protihrac ZdenoChara = new Protihrac("Zdeno Chara", false, "chara.jpg");
        Protihrac BobProbert = new Protihrac("Bob Probert", false, "probert.jpg");
        Protihrac PavelDatsyuk = new Protihrac("Pavel Datsyuk", true, "datsyuk.jpg");
        Protihrac SidneyCrosby = new Protihrac("Sidney Crosby", true, "crosby.jpg");
        
        Protihrac MStone = new Protihrac("Mark Stone", true, "stone.jpg");
        Protihrac MBorowiecki = new Protihrac("Mark Borowiecki", false, "borowiecki.jpg");
        Protihrac MMartin = new Protihrac("Matt Martin", false, "martin.jpg");
        Protihrac LSchenn = new Protihrac("Luke Schenn", false, "shenn.jpg");
        Protihrac BMarchand = new Protihrac("Brad Marchand", true, "marchand.jpg");
        Protihrac MZuccarello = new Protihrac("Mats Zuccarello", true, "zuccarello.jpg");

        //umístíme postavy do prostorů

        a2.vlozHrace(TJOshie);
        a1.vlozHrace(ZdenoChara);
        b3.vlozHrace(BobProbert);
        c1.vlozHrace(PavelDatsyuk);
        c2.vlozHrace(SidneyCrosby);
        
        
        A2.vlozHrace(MStone);
        A1.vlozHrace(MBorowiecki);
        B3.vlozHrace(MMartin);
        C1.vlozHrace(LSchenn);
        C2.vlozHrace(BMarchand);
        B4.vlozHrace(MZuccarello);

        aktualniProstor = satna;  // hra začíná v šatně UPRAVIT!!
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
        
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     * Metoda slouzi pro presun hrace na pole C1 v pripade jejiho vyvolani
     * 
     * @return - zavolani promenne Prostor c1
     * 
     */
    public Prostor getTeleport(){
        return c1;

    }

    /**
     *  Metoda vrací odkaz na výhru.
     *
     *@return     vyhra
     */
    public boolean isVyhra() {
        return vyhra;
    }

    /**
     * Metoda v pripade zavolani zmeni stav na vyhru
     * 
     * @param stav - stav hry
     */
    public void setVyhra(boolean stav) {
        this.vyhra = stav;
    }

    /**
     *  Metoda vrací odkaz na prohru.
     *
     *@return     prohra
     */
    public boolean isProhra() {
        return prohra;
    }

    /**
     * Metoda v pripade zavolani zmeni stav na prohru
     * 
     * @param stav - stav hry
     */
    public void setProhra(boolean stav) {
        this.prohra = stav;
    }

    /**
     * Metoda, ktera vypise vybavu
     * 
     * @return - vypis seznamu veci ve vybave
     */
    public Vybava getVybava() {
        return this.vybava;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }

}
