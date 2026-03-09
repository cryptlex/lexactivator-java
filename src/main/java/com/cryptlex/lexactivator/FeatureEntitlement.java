package com.cryptlex.lexactivator;

public class FeatureEntitlement {
    /**
     * The name of the feature.
     */
    public String featureName;

    /**
     * The display name of the feature.
     */
    public String featureDisplayName;

    /**
     * The value of the feature.
     */
    public String value;

    /**
     * The default value of the feature inherited from the entitlement set.
     */
    public String baseValue;

    /**
     * Timestamp when the license feature entitlement will expire.
     */
    public long expiresAt;
    
}
