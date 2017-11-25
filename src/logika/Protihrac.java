package logika;

/*******************************************************************************
 * Trida Protihrac popisuje protihrace, rozdeluje je na typy podle zrucnosti/fyzicke hry
 *
 * @author    Dominik Lamacz
 * @version 8.1. 2017
 */
public class Protihrac
{
    private String jmeno;
    private boolean typ; 
    private boolean porazen; 
    private String obraz;

    //== Konstruktory a tovární metody =============================================
    /***************************************************************************
     *  Konstruktor vytvari protihrace a identifukje ho jmenem a urcuje jeho typ (zrucny, fyzicky)
     * 
     * @param   jmeno       jmeno hrace
     * @param   typ         typ hrace
     */
    public Protihrac (String jmeno, boolean typ, String obraz) {
        this.jmeno = jmeno;
        this.typ = typ;
        this.obraz = obraz;
        porazen = false;

    }

    /**
     * Metoda ukaze jmeno hrace
     * 
     * @return jmeno - Jméno protihrace
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Metoda zjisti typ hrace
     * 
     * @return typ - typ hrace (true = zrucny; false = fyzicky)
     */

    public boolean isTyp() {
        return typ;
    }

    /**
     * Metoda nastavi parametr porazen protihraci, ktery uz byl v dane hre porazen 
     * 
     * @param porazka - prirazen hraci, ktery byl porazen
     */
    public void setPorazen(boolean porazka){
        porazen = porazka;
    }

    /**
     * Metoda zjistuje, jestli byl hrac v danem porazen
     * 
     * @return porazen true - paklize byl uz hrac porazen
     * 
     */

    public boolean isPorazen() {
        return porazen;
    }
    
    public String getObraz() {
        return obraz;
    }

}
