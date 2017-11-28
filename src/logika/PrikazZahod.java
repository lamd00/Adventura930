/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Trida Zahod slouzi k vykonani prikazu zahod - pro zahozeni predmetu posbiranych v prubehu hry
 *
 * @author    Dominik Lamacz
 * @version   8.1. 2017
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahod";

    private HerniPlan hPlan;
    private Vybava vybava;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor tridy
     *  
     *  @param hPlan herní plán, ve kterém se bude ve hře "chodit" 
     */
    public PrikazZahod(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
        this.vybava = hPlan.getVybava();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda kontroluje, jestli je prikaz a parametr zadan spravne, pokud ano, odlozi danou veci z vybavy do aktualniho prostoru
     * 
     * @param parametry - parametr prikazu - nazev zahozene veci
     * @return - hlaska vypsana hraci
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {  // pokud hrac nic nezada
            return "Pokud chceš, abych něco zahodil, musíš mi říct, co: 'zahod nazev_veci'";
        }

        String nazevVeci = parametry[0];
        Vec vec = vybava.najdiVec(nazevVeci);
   
        if (vec == null) { // pokud se vec nenachazi ve vybave
            return "Ať hledám jak hledám, takovou věc nemám.";
        }
        vybava.odeberVec(nazevVeci); // odebere zvolenou vec z vybavy
        hPlan.getAktualniProstor().vlozVec(vec); // vlozi vyhozenou vec do aktualniho pasma
        
        hPlan.notifyObservers();
        
        return  "Odložil jsi " + nazevVeci + ", teď leží v aktuálním pásmu. Škoda, možná by se to hodilo... nebo taky ne.";
    }

    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
