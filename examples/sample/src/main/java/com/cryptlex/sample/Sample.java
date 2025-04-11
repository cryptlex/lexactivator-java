package com.cryptlex.sample;

import com.cryptlex.lexactivator.LexActivator;
import com.cryptlex.lexactivator.LicenseCallbackEvent;
import com.cryptlex.lexactivator.ReleaseCallbackEvent;
import com.cryptlex.lexactivator.ReleaseUpdateCallbackEvent;
import com.cryptlex.lexactivator.Release;
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
            LexActivator.SetProductData("QjhCMzYzQ0Y1QjBDNjdDMkFDQkRCRURCQjJGRkUxRTM=.W6xjK0PSnNVX/2tU/aGYM36gM8ttLV6sCpSgL+ZWm1f9W3zaYPobLxXY3DPGTLJ+xq6VoYrHr5ZG0WKcE4WaYmFO8c6udPbN6p7yVIRN68JPVyNYyzcRheYu4met+LSD693WmOljZcTqU0uvx8iUkScv6FfkJj8L0Bv9oz/M1WdYEwY1+N1iKEYzSfVPmSDk5gRRWW4O6GjPYWy8GRFHPmFhRz2/cO8EkNb0Quh5J48wcBGBcld/rtImOGG9+5RcDNYPIzYYPVSVDTlvrV9m8FjvAi1RzNgSiZGFMPlgc2HVb3/4IGlsIuQMUFP9BWR9fR1fmziOmauMKpxYl//BgIDpiWvMsYn9fbcDrDIDWLkiZRyJ7GY6oT8aP9VulbOjWhw6m0ikUSPjRrSICUBH5PHEmfh6otg//rO0WXUf0vUohqpWQpJGy2Ak/hMp6EmBgeX6BoZ1wbM896TJNSOXhGh74/GOODRTNl5RKhT0QWldNGk1+qVLGNFB0gENROIbGIyMIZtuIUkEQo35wxcgUDu50hT2nWX90Jn21mVoD5zxczAtvPUgVKs2nMSJqP7MlnBLtONlJ3VdgmrBT4iUs7Bu42EQYxojmTRAG9LHWeguZ5bVn3judc6uAY/zQcZolHiANpB+e2BfbjKt7t2Fsons6IZGC2U/D+BsJR/5bAlM+q0EVqkvLMHr1dbJsnhCEAyrgljFI6WWC+4TctjF6FTPlApwWahYWn92PlVK9cEX9iApJJCLGvRqbjp1Dd1yw1kROTmdF4evYJdTN8lT6A8EYUzTgLHs/STv9/E31kXjV6PVnaRS/XK+ahcrGKV3");
            LexActivator.SetProductId("2a2d5582-a2f9-4cfd-9db3-bf819166fd9b", LexActivator.LA_USER);
            LexActivator.SetReleaseVersion("1.0.0");  // Set this to the release version of your app
            // Setting license callback is recommended for floating licenses
            // LicenseCallbackEventListener licenseEventListener = new LicenseCallbackEventListener();
            // LexActivator.SetLicenseCallbackListener(licenseEventListener);
            status = LexActivator.IsLicenseGenuine();
            if (LexActivator.LA_OK == status) {
                System.out.println("License is genuinely activated!");

                String entitlementSetName = LexActivator.GetLicenseEntitlementSetName();
                System.out.println("Entitlement Set Name: " + entitlementSetName);

                String entitlementSetDisplayName = LexActivator.GetLicenseEntitlementSetDisplayName();
                System.out.println("Entitlement Set Display Name: " + entitlementSetDisplayName);

                String displayName = LexActivator.GetProductVersionDisplayName();
                System.out.println("Product Version Display Name: " + displayName);

                
                
                

                // Checking for software release update
                // ReleaseUpdateCallbackEventListener releaseUpdateEventListener = new ReleaseUpdateCallbackEventListener();
                // LexActivator.CheckReleaseUpdate(releaseUpdateEventListener, LexActivator.LA_RELEASES_ALL, null);
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
                    LexActivator.SetLicenseKey("5C809E-049639-4A6596-D819AE-30EDC0-BAFFBC");
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
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Encoding error: " + ex.getMessage());
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

class ReleaseUpdateCallbackEventListener implements ReleaseUpdateCallbackEvent {
    // Release update callback is invoked when CheckReleaseUpdate() gets a response from the server
    @Override
    public void ReleaseUpdateCallback(int status, Release release, Object userData) {
        switch (status) {
            case LexActivator.LA_RELEASE_UPDATE_AVAILABLE:
                System.out.println("A new update is available for the app!\n");
                System.out.println("Release notes: " + release.notes);
                break;
            case LexActivator.LA_RELEASE_UPDATE_AVAILABLE_NOT_ALLOWED:
                System.out.println("A new update is available for the app but it's not allowed!\n");
                System.out.println("Release notes: " + release.notes);
                break;
            case LexActivator.LA_RELEASE_NO_UPDATE_AVAILABLE:
                System.out.println("Current version is already latest....!\n");
                break;
            default:
                System.out.println("Error code: " + status);
        }
    }
}

