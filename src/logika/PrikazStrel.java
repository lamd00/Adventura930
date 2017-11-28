package logika;

import javafx.scene.input.KeyCombination;

/**
 * Trida PrikazStrel slouzi pro prikaz strel, 
 * ktery bude umoznen provest po ziskani zlate pasky a prechodu do pasma B1
 * 
 * @author Dominik Lamacz
 * @version 8.1. 2017
 */
public class PrikazStrel implements IPrikaz
{

    private static final String NAZEV = "strel";
    private HerniPlan hPlan;
    private Vybava vybava;

    /**
     * Konstruktor  tridy
     * 
     * @param hPlan - herni plan
     */
    public PrikazStrel(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
        this.vybava = hPlan.getVybava();
    }

    /**
     * 
     * Metoda provede příkaz střel. Tento příkaz je možné provést jen v případě, 
     * že má hráč ve výbavě zlatou pásku a nachází se v prostoru B1 - před bránou
     * 
     * @param parametry - pripadne parametry - pro tento prikaz nepotrebne
     * @return - hlaska vypsana hraci
     */
    @Override
    public String proved(String... parametry){
        Prostor aktualniProstor = hPlan.getAktualniProstor(); 
        
        if(!vybava.jePaska()){ // zkontroluje, ze ma hrac zlatou pasku

            return "Bez zlaté pásky ti zklouzne puk z hole, zkus ji najít a pak teprve vystřelit.";
        }
      
        if (!(aktualniProstor.getNazev().equals("b1") || aktualniProstor.getNazev().equals("B1"))){ // zkontroluje, ze je hrac v pasmu pred branou

            return "Odsud se asi netrefíš, zajeď raději přímo před bránu a zkus střelit odtamtud.";
        }
        hPlan.setVyhra(true); 
        return "Paráda, úkol jsi splnil, teď se můžeš vrátit na střídačku!";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
