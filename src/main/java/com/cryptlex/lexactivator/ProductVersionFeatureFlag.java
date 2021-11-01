package com.cryptlex.lexactivator;


public class ProductVersionFeatureFlag {
    public String name;

    public Boolean enabled;

    public String data;

    public ProductVersionFeatureFlag(String name, int enabled, String data) {
        this.name = name;
        this.enabled = enabled > 0 ? true : false;
        this.data = data;
    }
}
