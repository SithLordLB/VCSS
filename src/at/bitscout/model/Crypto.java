package at.bitscout.model;

/** Sub class of Currency, for cryptos
 * @author Bandalo
 * @version 1.1
 */
public class Crypto extends Currency {

    /** Constructor for Cryptos
     * @param name name of crypto
     * @param isoCode iso Code of crypto
     */
    public Crypto(String name, String isoCode) {
        super(name, isoCode);
    }
}
