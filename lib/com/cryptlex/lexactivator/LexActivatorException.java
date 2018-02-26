package com.cryptlex.lexactivator;

public class LexActivatorException extends Exception
{

    int errorCode;

    public LexActivatorException(String message)
    {
        super(message);
    }

    public LexActivatorException(int errorCode)
    {
        super(getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    public int getCode()
    {
        return this.errorCode;
    }

    public static String getErrorMessage(int errorCode)
    {
        String message;
        switch (errorCode)
        {
            case LA_E_INET:

                message = "Failed to connect to the server due to network error.";
                break;

            case LA_E_PKEY:

                message = "Invalid product key.";
                break;

            case LA_E_PFILE:

                message = "Invalid or corrupted product file.";
                break;

            case LA_E_FPATH:

                message = "Invalid product file path.";
                break;

            case LA_E_GUID:

                message = "The version GUID doesn't match that of the product file.";
                break;

            case LA_E_OFILE:

                message = "Invalid offline activation response file.";
                break;

            case LA_E_PERMISSION:

                message = "Insufficent system permissions. Occurs when LA_SYSTEM flag is used but application is not run with admin privileges.";
                break;

            case LA_E_EDATA_LEN:

                message = "Extra activation data length is more than 256 characters.";
                break;

            case LA_E_TKEY:

                message = "The trial key doesn't match that of the product file.";
                break;

            case LA_E_TIME:

                message = "The system time has been tampered with. Ensure your date and time settings are correct.";
                break;

            case LA_E_VM:

                message = "Application is being run inside a virtual machine / hypervisor, and activation has been disallowed in the VM.";

                break;

            case LA_E_WMIC:

                message = "Windows Management Instrumentation (WMI) service has been disabled.";
                break;

            case LA_E_TEXT_KEY:

                message = "Invalid trial extension key.";
                break;

            case LA_E_TRIAL_LEN:

                message = "The trial length doesn't match that of the product file.";
                break;

            case LA_E_CUSTOM_FIELD_ID:

                message = "Invalid custom field id.";
                break;

            case LA_E_NET_PROXY:

                message = "Invalid network proxy.";
                break;

            case LA_E_HOST_URL:

                message = "Invalid Cryptlex host url.";
                break;
            
            case LA_E_DEACT_LIMIT:

                message = "Deactivation limit for key has reached.";
                break;
            
            case LA_E_ACT_LIMIT:
                
                message = "Activation limit for key has reached.";
                break;

            default:
                message = "Unknown error!";

        }
        return message;
    }

    /**
     * * Error Codes **
     */

    /*
     CODE: LA_EXPIRED

     MESSAGE: The product key has expired or system time has been tampered
     with. Ensure your date and time settings are correct.
     */
    public static final int LA_EXPIRED = 0x02;

    /*
     CODE: LA_REVOKED

     MESSAGE: The product key has been revoked.
     */
    public static final int LA_REVOKED = 0x03;

    /*
     CODE: LA_GP_OVER

     MESSAGE: The grace period is over.
     */
    public static final int LA_GP_OVER = 0x04;

    /*
     CODE: LA_E_INET

     MESSAGE: Failed to connect to the server due to network error.
     */
    public static final int LA_E_INET = 0x05;

    /*
     CODE: LA_E_PKEY

     MESSAGE: Invalid product key.
     */
    public static final int LA_E_PKEY = 0x06;

    /*
     CODE: LA_E_PFILE

     MESSAGE: Invalid or corrupted product file.
     */
    public static final int LA_E_PFILE = 0x07;

    /*
     CODE: LA_E_FPATH

     MESSAGE: Invalid product file path.
     */
    public static final int LA_E_FPATH = 0x08;

    /*
     CODE: LA_E_GUID

     MESSAGE: The version GUID doesn't match that of the product file.
     */
    public static final int LA_E_GUID = 0x09;

    /*
     CODE: LA_E_OFILE

     MESSAGE: Invalid offline activation response file.
     */
    public static final int LA_E_OFILE = 0x0A;

    /*
     CODE: LA_E_PERMISSION

     MESSAGE: Insufficent system permissions. Occurs when LA_SYSTEM flag is used
     but application is not run with admin privileges.
     */
    public static final int LA_E_PERMISSION = 0x0B;

    /*
     CODE: LA_E_EDATA_LEN

     MESSAGE: Extra activation data length is more than 256 characters.
     */
    public static final int LA_E_EDATA_LEN = 0x0C;

    /*
     CODE: LA_E_TKEY

     MESSAGE: The trial key doesn't match that of the product file.
     */
    public static final int LA_E_TKEY = 0x0D;

    /*
     CODE: LA_E_TIME

     MESSAGE: The system time has been tampered with. Ensure your date
     and time settings are correct.
     */
    public static final int LA_E_TIME = 0x0E;

    /*
     CODE: LA_E_VM

     MESSAGE: Application is being run inside a virtual machine / hypervisor,
     and activation has been disallowed in the VM.
     but
     */
    public static final int LA_E_VM = 0x0F;

    /*
     CODE: LA_E_WMIC

     MESSAGE: Fingerprint couldn't be generated because Windows Management 
     Instrumentation (WMI) service has been disabled. This error is specific
     to Windows only.
     */
    public static final int LA_E_WMIC = 0x10;

    /*
     CODE: LA_E_TEXT_KEY

     MESSAGE: Invalid trial extension key.
     */
    public static final int LA_E_TEXT_KEY = 0x11;

    /*
     CODE: LA_E_TRIAL_LEN

     MESSAGE: The trial length doesn't match that of the product file.
     */
    public static final int LA_E_TRIAL_LEN = 0x12;

    /*
     CODE: LA_T_EXPIRED

     MESSAGE: The trial has expired or system time has been tampered
     with. Ensure your date and time settings are correct.
     */
    public static final int LA_T_EXPIRED = 0x13;

    /*
     CODE: LA_TEXT_EXPIRED

     MESSAGE: The trial extension key being used has already expired or system
     time has been tampered with. Ensure your date and time settings are correct.
     */
    public static final int LA_TEXT_EXPIRED = 0x14;

    /*
     CODE: LA_E_BUFFER_SIZE

     MESSAGE: The buffer size was smaller than required.
     */
    public static final int LA_E_BUFFER_SIZE = 0x15;

    /*
     CODE: LA_E_CUSTOM_FIELD_ID

     MESSAGE: Invalid custom field id.
     */
    public static final int LA_E_CUSTOM_FIELD_ID = 0x16;

    /*
     CODE: LA_E_NET_PROXY

     MESSAGE: Invalid network proxy.
     */
    public static final int LA_E_NET_PROXY = 0x17;

    /*
     CODE: LA_E_HOST_URL

     MESSAGE: Invalid Cryptlex host url.
     */
    public static final int LA_E_HOST_URL = 0x18;

    /*
     CODE: LA_E_DEACT_LIMIT

     MESSAGE: Deactivation limit for key has reached.
     */
    public static final int LA_E_DEACT_LIMIT = 0x19;
    
    /*
     CODE: LA_E_ACT_LIMIT
     
     MESSAGE: Activation limit for key has reached.
     */
    public static final int LA_E_ACT_LIMIT = 0x1A;

}
