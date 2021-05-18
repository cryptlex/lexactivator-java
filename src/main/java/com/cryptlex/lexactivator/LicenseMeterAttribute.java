package com.cryptlex.lexactivator;

public class LicenseMeterAttribute {

    public String name;

    public int allowedUses;

    public int totalUses;

    public int grossUses;

    public LicenseMeterAttribute(String name, int allowedUses, int totalUses, int grossUses) {
        this.name = name;
        this.allowedUses = allowedUses;
        this.totalUses = totalUses;
        this.grossUses = grossUses;
    }
}
