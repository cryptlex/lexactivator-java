package com.cryptlex.lexactivator;

public class LicenseMeterAttribute {

    public String name;

    public int allowedUses;

    public int totalUses;

    public LicenseMeterAttribute(String name, int allowedUses, int totalUses) {
        this.name = name;
        this.allowedUses = allowedUses;
        this.totalUses = totalUses;
    }
}
