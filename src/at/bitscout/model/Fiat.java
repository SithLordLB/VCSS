package at.bitscout.model;
/** Sub class of Currency, commercial currencies
 * @author Bandalo
 * @version 1.1
 */
public class Fiat extends Currency {
    /** Constructor for fiat currency
     * @param name name of fiat
     * @param isoCode iso code of fiat
     */
    public Fiat(String name, String isoCode) {
        super(name, isoCode);
    }
}
