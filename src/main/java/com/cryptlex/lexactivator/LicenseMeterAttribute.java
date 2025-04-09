package com.cryptlex.lexactivator;
import java.math.BigInteger;

public class LicenseMeterAttribute {

    /**
     * The name of the meter attribute.
     */
    public String name;

    /**
     * The allowed uses of the meter attribute. A value of -1 indicates unlimited allowed uses.
     */
    public long allowedUses;

    /**
     * The total uses of the meter attribute.
     */
    public BigInteger totalUses;

    /**
     * The gross uses of the meter attribute. 
     */
    public BigInteger grossUses;

    public LicenseMeterAttribute(String name, long allowedUses, BigInteger totalUses, BigInteger grossUses) {
        this.name = name;
        this.allowedUses = allowedUses;
        this.totalUses = totalUses;
        this.grossUses = grossUses;
    }
}
