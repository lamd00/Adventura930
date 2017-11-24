/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Třída příkazVezmi představuje příkaz pro sbírání zvedatelných předmětů a jejich ukládání do výbavy
 *
 * @author    Dominik Lamacz
 * @version   9.1. 2017
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    private HerniPlan herniPlan;
    private Vybava vybava;
    private Hra hra;


    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy PrikazVezmi
     *  
     *  @param hra - slouzi k pripadnemu zavolani konec hry
     */
    public PrikazVezmi(Hra hra)
    {

        herniPlan = hra.getHerniPlan();
        vybava = herniPlan.getVybava();
        this.hra = hra;

    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda s příkazem vezmi zjistí, jestli je prostor volný od portihráčů, 
     * jestli daný předmět v daném prostoru existuje a jestlidaný předmět možno zvednout a uložit do výbavy,
     * pokud ne, vypíše hlášku s odůvodněním, proč ne. Pokud ano, předmět bude uložen do výbavy 
     * a oznámí tak hráči.
     * 
     *@param parametry      parametr je název předmětu
     *@return               hláška vypsána hráči
     */
    public String proved(String... parametry) {

        Protihrac protihrac = herniPlan.getAktualniProstor().getProtihrac();
        if (protihrac != null && !protihrac.isPorazen()){
            return "Nejdříve musíš porazit hráče v tomhle pásmu";
        }

        if (parametry.length < 1) { // pokud hrac za prikaz vezmi nevlozi nic
            return "Nevím, co mám sebrat";
        }

        String nazevVeci = parametry[0];

        Vec vec = herniPlan.getAktualniProstor().najdiVec(nazevVeci);
        if (vec == null) { // zjisti, jestli se dana vec nachazi v danem prostoru
            return "Nic takového tu není.";
        }

        if(!vec.isPrenositelna()){  // zjisti, jestli je parametr isPrenositelna false 
            herniPlan.notifyObservers();
            return "Nechám to tam, kde to je.";
        }

        if(vybava.plno()){ // zjisti, jestli je vybava plna
            return "Více už toho neuneseš, ale můžes něco odhodit příkazem zahod.";
        }

        if(vec.getNazev().equals("leva_brusle")){ // zjisti, jestli zvedany predmet je leva_brusle
            herniPlan.setAktualniProstor(herniPlan.getTeleport());
            herniPlan.getAktualniProstor().odeberVec(nazevVeci);

            vybava.vlozitVec(vec);
            return "Vybavil ses levou bruslí, díky které jsi rychlejší a přejel jsi rovnou do útočného pásma.\n" + herniPlan.getTeleport().dlouhyPopis();

        }
        if(vec.getNazev().equals("prava_brusle")){ // zjisti, jestli je zvedany predmet prava_brusle
            hra.setKonecHry(true);
            return "Brusle má tupou čepel a ty jsi s ní ukloznul a zranil se, vrať se na střídačku, aby tě lékař ošetřil. \nAž budeš v pořádku, stačí jen naskočit na led a zkusit to znovu.";          
        }
        // pokud je věc možno zvednout a uložit do výbavy, smaže se její instance z daného prostoru        
        herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        // a věc se uloží do výbavy
        vybava.vlozitVec(vec);
        herniPlan.notifyObservers();
        return "Vybavil ses " + vec.getPopis() + "."; 
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return NAZEV - nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
