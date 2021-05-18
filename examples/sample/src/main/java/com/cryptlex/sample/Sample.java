package com.cryptlex.sample;

import com.cryptlex.lexactivator.LexActivator;
import com.cryptlex.lexactivator.LicenseCallbackEvent;
import com.cryptlex.lexactivator.ReleaseCallbackEvent;
import com.cryptlex.lexactivator.LexActivatorException;
import java.io.File;
import java.time.Instant;
import java.io.UnsupportedEncodingException;

public class Sample {

    public static void main(String[] args) {
        int status;
        try {
            // String path = System.getProperty("user.dir") + File.separator +"Product.dat";
            // LexActivator.SetProductFile(path);
            LexActivator.SetProductData("PASTE_CONTENT_OF_PRODUCT.DAT_FILE");
            LexActivator.SetProductId("PASTE_PRODUCT_ID", LexActivator.LA_USER);

            // Setting license callback is recommended for floating licenses
            // LicenseCallbackEventListener licenseEventListener = new LicenseCallbackEventListener();
            // LexActivator.SetLicenseCallbackListener(licenseEventListener);
            status = LexActivator.IsLicenseGenuine();
            if (LexActivator.LA_OK == status) {
                System.out.println("License is genuinely activated!");

                // Checking for software release update
                // ReleaseCallbackEventListener releaseEventListener = new ReleaseCallbackEventListener();
                // LexActivator.CheckForReleaseUpdate("windows", "1.0.0", "stable", releaseEventListener);
            } else if (LexActivator.LA_EXPIRED == status) {
                System.out.println("License is genuinely activated but has expired!");
            } else if (LexActivator.LA_GRACE_PERIOD_OVER == status) {
                System.out.println("License is genuinely activated but grace period is over!");
            } else if (LexActivator.LA_SUSPENDED == status) {
                System.out.println("License is genuinely activated but has been suspended!");
            } else {
                int trialStatus;
                trialStatus = LexActivator.IsTrialGenuine();
                if (LexActivator.LA_OK == trialStatus) {
                    int trialExpiryDate = LexActivator.GetTrialExpiryDate();
                    long daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86400;
                    System.out.println("Trial days left: " + daysLeft);
                } else if (LexActivator.LA_TRIAL_EXPIRED == trialStatus) {
                    System.out.println("Trial has expired!");
                    // Time to buy the product key and activate the app
                    LexActivator.SetLicenseKey("PASTE_LICENSE_KEY");
                    LexActivator.SetActivationMetadata("key1", "value1");
                    // Activating the product
                    status = LexActivator.ActivateLicense(); // Ideally on a button click inside a dialog
                    if (LexActivator.LA_OK == status || LexActivator.LA_EXPIRED == status
                            || LexActivator.LA_SUSPENDED == status) {
                        System.out.println("License activated successfully: " + status);
                    } else {
                        System.out.println("License activation failed: " + status);
                    }
                } else {
                    System.out.println("Either trial has not started or has been tampered!");
                    // Activating the trial
                    trialStatus = LexActivator.ActivateTrial(); // Ideally on a button click inside a dialog
                    if (LexActivator.LA_OK == trialStatus) {
                        int trialExpiryDate = LexActivator.GetTrialExpiryDate();
                        long daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86400;
                        System.out.println("Trial days left: " + daysLeft);
                    } else {
                        // Trial was tampered or has expired
                        System.out.println("Trial activation failed: " + trialStatus);
                    }
                }
            }

        } catch (LexActivatorException ex) {
            System.out.println(ex.getCode() + ": " + ex.getMessage());
        }
    }

}

class LicenseCallbackEventListener implements LicenseCallbackEvent {

    // License callback is invoked when IsLicenseGenuine() completes a server sync
    @Override
    public void LicenseCallback(int status) {
        switch (status) {
            case LexActivator.LA_SUSPENDED:
                System.out.println("The license has been suspended.");
                break;
            case LexActivatorException.LA_E_INET:
                System.out.println("Network connection failure.");
                break;
            default:
                System.out.println("License status: " + status);
                break;
        }
    }
}

class ReleaseCallbackEventListener implements ReleaseCallbackEvent {

    // Software release update callback is invoked when CheckForReleaseUpdate() gets a response from the server
    @Override
    public void ReleaseCallback(int status) {
        switch (status) {
            case LexActivator.LA_RELEASE_UPDATE_AVAILABLE:
                System.out.println("A new update is available for the app.\n");
                break;

            case LexActivator.LA_RELEASE_NO_UPDATE_AVAILABLE:
                System.out.println("Current version is already latest.\n");
                break;

            default:
                System.out.println("Error code: " + status);
        }
    }
}

