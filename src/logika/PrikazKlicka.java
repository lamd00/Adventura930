package logika;

/**
 * trid PrikazKlicka slouzi pro prikaz klicka, ktery je soucasti hry
 * 
 * @author Dominik Lamacz
 * @version 8.1. 2017
 */
public class PrikazKlicka implements IPrikaz {
    private static final String NAZEV ="klicka";
    private HerniPlan hPlan;
    private Hra hra;

    /**
     * Constructor for objects of class PrikazKlicka
     * 
     * @param hra - umoznuje zavolat metodu ukonceni hry
     */

    public PrikazKlicka(Hra hra)
    {
        this.hra = hra;
        hPlan = hra.getHerniPlan();

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * Metoda slouzi k provedeni prikazu klicka. Pokud v danem prostoru neni zadny protihrac, napise to chybovou hlasku
     *  Pokud byl prikaz zvolen proti spatnemu hraci, hra konci prohrou
     *  Pokud byl zadan dobre, bude napsana hlaska o uspechu
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu - tady neni potreba
     *  @return hlaska o chybe, nebo uspechu
     *  
     */
    @Override
    public String proved(String... parametry){

        Protihrac protihrac = hPlan.getAktualniProstor().getProtihrac();
        if (protihrac == null) {
            return "Obkličkoval jsi sám sebe. Potlesk od fanoušků jsi sklidil, ale teď se zkus držet plánu";
        }

        if(protihrac.isTyp()) { 
            hra.setKonecHry(true);
            return "Hráč ti vypíchnul puk, vrať se na střídačku, příští střídání snad bude lepší";
        }
        else{
            protihrac.setPorazen(true);
            return "Obkličkoval jsi ho, paráda!";
        }
    }
}
