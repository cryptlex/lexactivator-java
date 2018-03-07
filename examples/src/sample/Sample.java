package sample;

import com.cryptlex.lexactivator.LexActivator;
import com.cryptlex.lexactivator.LexActivatorException;
import java.io.File;
import java.time.Instant;
import java.io.UnsupportedEncodingException;

public class Sample
{
    public static void main(String[] args)
    {
        int status;
        try
        {
            // String path = System.getProperty("user.dir") + File.separator +"Product.dat";
            // LexActivator.SetProductFile(path);
            LexActivator.SetProductData("PASTE_CONTENT_OF_PRODUCT.DAT_FILE");
            LexActivator.SetProductVersionGuid("PASTE_PRODUCT_VERSION_GUID", LexActivator.LA_USER);

            status = LexActivator.IsProductGenuine();
            if (LexActivator.LA_OK == status)
            {
                System.out.println("Product is genuinely activated!");
            } else if (LexActivator.LA_EXPIRED == status)
            {
                System.out.println("Product is genuinely activated, but license validity has expired!");
            } else if (LexActivator.LA_GP_OVER == status)
            {
                System.out.println("Product is genuinely activated, but grace period is over!");
            } else if (LexActivator.LA_REVOKED == status)
            {
                System.out.println("Product is genuinely activated, but product key has been revoked!");
            } else
            {
                int trialStatus;
                trialStatus = LexActivator.IsTrialGenuine();
                if (LexActivator.LA_OK == trialStatus)
                {
                    int trialExpiryDate = LexActivator.GetTrialExpiryDate();
		    long daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86500;
                    System.out.println("Trial days left: "+ daysLeft);
                } 
                else if (LexActivator.LA_T_EXPIRED == trialStatus)
                {
                    System.out.println("Trial has expired!");
                    // Time to buy the product key and activate the app
                    LexActivator.SetProductKey("PASTE_PRODUCT_KEY");
                    LexActivator.SetActivationExtraData("sample data");
                    // Activating the product
                    status = LexActivator.ActivateProduct();    // Ideally on a button click inside a dialog
                    if (LexActivator.LA_OK == status){
                        System.out.println("Product activated successfully!");
                    }
                    else {
                        System.out.println("Product activation failed: " + status);
                    }
                } 
                else
                {
                    System.out.println("Either trial has not started or has been tampered!");
                    // Activating the trial
                    trialStatus = LexActivator.ActivateTrial();   // Ideally on a button click inside a dialog
                    if (LexActivator.LA_OK == trialStatus){
                        int trialExpiryDate = LexActivator.GetTrialExpiryDate();
                        long daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86500;
                        System.out.println("Trial days left: "+ daysLeft);                    }
                    else {
                        //Trial was tampered or has expired
                        System.out.println("Trial activation failed: " + trialStatus);
                    }
                }
            }

        } 
        catch (LexActivatorException ex)
        {
            System.out.println(ex.getCode() + ": " + ex.getMessage());
        }
    }

}
