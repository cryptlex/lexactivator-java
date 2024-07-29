package com.cryptlex.lexactivator;

public class LicenseMeterAttribute {

    public String name;

    public long allowedUses;

    public long totalUses;

    public long grossUses;

    public LicenseMeterAttribute(String name, long allowedUses, long totalUses, long grossUses) {
        this.name = name;
        this.allowedUses = allowedUses;
        this.totalUses = totalUses;
        this.grossUses = grossUses;
    }
}
