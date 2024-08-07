package com.cryptlex.lexactivator;

public class UserLicense {

    /**
     * The allowed activations of the license. A value of -1 indicates unlimited number of activations.
     */
    public long allowedActivations;

    /**
     * The allowed deactivations of the license. A value of -1 indicates unlimited number of deactivations.
     */
    public long allowedDeactivations;
    
    /**
     * The license key.
     */
    public String key;

    /**
     * The license type (node-locked or hosted-floating).
     */
    public String type;
}
