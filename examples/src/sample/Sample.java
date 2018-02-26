package sample;

import com.cryptlex.lexactivator.LexActivator;
import com.cryptlex.lexactivator.LexActivatorException;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class Sample
{
    public static void main(String[] args)
    {
        int status;
        String path = System.getProperty("user.dir") + File.separator +"Product.dat";
        try
        {
            LexActivator.SetProductFile(path);
            LexActivator.SetVersionGUID("59A44CE9-5415-8CF3-BD54-EA73A64E9A1B", LexActivator.LA_USER);

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
            } else
            {
                int trialStatus;
                LexActivator.SetTrialKey("CCEAF69B-144EDE48-B763AE2F-A0957C93-98827434");
                trialStatus = LexActivator.IsTrialGenuine();
                if (LexActivator.LA_OK == trialStatus)
                {
                    System.out.println("Trial days left: "+ LexActivator.GetTrialDaysLeft(LexActivator.LA_V_TRIAL));
                } 
                else if (LexActivator.LA_T_EXPIRED == trialStatus)
                {
                    System.out.println("Trial has expired!");
                    // Time to buy the product key and activate the app
                    LexActivator.SetProductKey("986D8-DE8AF-C2B37-50BF5-03EA1");
                    LexActivator.SetExtraActivationData("sample data");
                    // Activating the product
                    status = LexActivator.ActivateProduct();    // Ideally on a button click inside a dialog
                    if (LexActivator.LA_OK == status){
                        System.out.println("Product activated successfully!");
                        //System.out.println("Custom field 301: "+ LexActivator.GetCustomLicenseField("301"));
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
                        System.out.println("Trial started, days left: "+ LexActivator.GetTrialDaysLeft(LexActivator.LA_V_TRIAL));
                    }
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
