package logika;

/**
 * Trida PrikazHit slouzi k provedeni prikazu hit, ktery je dulezity pro prekonani daneho typu protihracu.
 *
 * @author Dominik Lamacz 
 * @version 7.1.2017
 * 
 * 
 */
public class PrikazHit implements IPrikaz {
    private static final String NAZEV ="hit";
    private HerniPlan hPlan;
    private Hra hra;
    private Protihrac typ;
    private Hra setKonecHry;

    /**
     * Constructor for objects of class PrikazHit
     * 
     * @param hra - umoznuje zavolat metodu ukonceni hry
     */

    public PrikazHit(Hra hra)
    {
        this.hra = hra;
        hPlan = hra.getHerniPlan();

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return NAZEV - nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     *  Metoda slouzi k provedeni prikazu hit. Pokud v danem prostoru neni zadny protihrac, napise to chybovou hlasku
     *  Pokud byl prikaz zvolen proti spatnemu hraci, hra konci prohrou
     *  Pokud byl zadan dobre, bude napsana hlaska o uspechu
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu. -- tady neni potreba
     *  
     *  
     *  
     *  @return - reakce hry na zadany prikaz - lisi se podle situace hry
     */
    @Override
    public String proved(String... parametry){

        Protihrac protihrac = hPlan.getAktualniProstor().getProtihrac();
        if (protihrac == null || protihrac.isPorazen()) { // pokud v pasmu zadny protihrac neni
            hra.getHerniPlan().notifyObservers();
            return "Vrazil jsi do vzduchu. Pěkná práce! Příště by asi bylo lepší hitovat nějakého hráče";
        }

        if(protihrac.isTyp() == true) { // zjistuje typ hrace (true = zrucny hrac, ktery se da porazit hitem)

            protihrac.setPorazen(true);
            hra.getHerniPlan().notifyObservers();
            return "Sejmul jsi ho, dobrá práce!";
        }
        else{ // pokud se hrac neda porazit hitem a uzivatel zvoli prikaz hit => prohra
            hra.setKonecHry(true);
            hra.getHerniPlan().notifyObservers();
            return "Hráč hit ustál a obral tě o puk, vrať se na střídačku, příští střídání snad bude lepší";
            

        }
    }
}
