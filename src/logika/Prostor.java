package logika;

// import java.util.*;
import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 
import java.util.stream.Collectors; 

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version ZS 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci;  // seznam veci v prostoru
    private Protihrac protihrac;
    private double posLeft;
    private double posTop;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param posLeft - parametr pro umisteni vlevo
     * @param posTop - parametr pro umisteni vpravo
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.posLeft = posLeft;
        this.posTop = posTop;
        vychody = new HashSet<>();
        veci = new HashMap<>();

    }
    
    public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }


    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v " + popis + ".\n"
        + getJmenoProtihrace()+ "\n" // dalsi radek
        + popisVeci() + "\n"
        + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Příkazem 'jdi ##' můžeš přejet do:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     *Vypise veci, ktere jsou v danem prostoru, zobrazuje se i kdyz v prostoru nic neni, aby mel hrac o teto funkci prehled
     * @return vracenyText - nazev veci v prostoru
     */
    private String popisVeci() {

        String vracenyText = "Věci:";
        for (String nazev : getVeci().keySet()) {
            vracenyText += " " + nazev;
        }
        return vracenyText;

    }

    /**
     * Overi, ze v danem prostoru je nejaky protihrac (pokud uz je porazen, tak zmizi), pote, pokud je, vypise jeho jmeno
     *
     * @return Popis postav - jmen // UPRAVIT
     */
    private String getJmenoProtihrace() {
        if(protihrac == null || protihrac.isPorazen()){
            return "";
        }
        else{
            return "Protihráč: " + protihrac.getJmeno();
        }
    }

    /**
     * Metoda pro vypsani protihrace v prostoru
     * @return protihrac - jmeno protihrace v prostoru
     */
    public Protihrac getProtihrac(){
        return protihrac;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Metoda slouzici pro vkladani veci do prostoru
     * 
     * @param vec - vec, ktera je vkladana do zvoleneho prostoru
     * 
     */

    public void vlozVec(Vec vec) {
        getVeci().put(vec.getNazev(), vec);
    }

    /**
     * Metoda, ktera vyhleda vec podle sveho unikatniho nazvu
     * 
     * @param nazevVeci - nazev vyhledavane veci
     * @return getVeci - vrati nazev veci
     */
    public Vec najdiVec (String nazevVeci)
    {
        return getVeci().get(nazevVeci);
    }

    /**
     * Metoda slouzici k odebrani veci z daneho prostoru
     * 
     * @param nazev - nazev veci, kterou chceme z prostoru odebrat
     * @return getVeci vrati nazev veci a vymaze vybranou
     */
    public boolean odeberVec(String nazev) {
        Vec vec = getVeci().get(nazev);
        return getVeci().remove(nazev,vec);
    }

    /**
     * Metoda slouzici k vlozeni protihrace do daneho prostoru
     * 
     * @param pridavan - jmeno hrace, ktery je vkladan
     * 
     */
    public void vlozHrace(Protihrac pridavan) {

        this.protihrac = pridavan;
    }

    /**
     * @return the veci
     */
    public Map<String, Vec> getVeci() {
        return veci;
    }

    /**
     * @param veci the veci to set
     */
    public void setVeci(Map<String, Vec> veci) {
        this.veci = veci;
    }

}
