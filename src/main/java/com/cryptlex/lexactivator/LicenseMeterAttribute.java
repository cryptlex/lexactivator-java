package com.cryptlex.lexactivator;
import java.math.BigInteger;

public class LicenseMeterAttribute {

    public String name;

    public long allowedUses;

    public BigInteger totalUses;

    public BigInteger grossUses;

    public LicenseMeterAttribute(String name, long allowedUses, BigInteger totalUses, BigInteger grossUses) {
        this.name = name;
        this.allowedUses = allowedUses;
        this.totalUses = totalUses;
        this.grossUses = grossUses;
    }
}
