package com.cryptlex.lexactivator;

public class LexActivatorException extends Exception {

    int errorCode;

    public LexActivatorException(String message) {
        super(message);
    }

    public LexActivatorException(int errorCode) {
        super(getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    public int getCode() {
        return this.errorCode;
    }

    public static String getErrorMessage(int errorCode) {
        String message;
        switch (errorCode) {
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

        case LA_E_PKEY_TYPE:

            message = "Invalid product key type.";
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

        case LA_E_OFILE_EXPIRED:

            message = "The offline activation response has expired.";
            break;

        case LA_E_BUFFER_SIZE:

            message = "The buffer size was smaller than required.";
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

        case LA_E_PDATA:

            message = "Invalid product data.";
            break;
        case LA_E_TRIAL_NOT_EXPIRED:

            message = "Trial has not expired.";
            break;

        case LA_E_COUNTRY:
            message = "Country is not allowed.";
            break;

        case LA_E_IP:
            message = "IP address is not allowed.";
            break;

        case LA_E_FILE_PERMISSION:
            message = "No permission to write to file.";
            break;
            
        case LA_E_LOCAL_TRIAL_NOT_EXPIRED:
            message = "Trial has not expired";
            break;

        case LA_E_SERVER:
            message = "Server error.";
            break;

        case LA_E_CLIENT:
            message = "Client error.";
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

    public static final int LA_EXPIRED = 2;

    /*
    CODE: LA_REVOKED
    
    MESSAGE: The product key has been revoked.
    */

    public static final int LA_REVOKED = 3;

    /*
    CODE: LA_GP_OVER
    
    MESSAGE: The grace period is over.
    */

    public static final int LA_GP_OVER = 4;

    /*
    CODE: LA_T_EXPIRED
    
    MESSAGE: The trial has expired or system time has been tampered
    with. Ensure your date and time settings are correct.
    */

    public static final int LA_T_EXPIRED = 5;

    /*
    CODE: LA_LT_EXPIRED
    
    MESSAGE: The local trial has expired or system time has been tampered
    with. Ensure your date and time settings are correct.
    */

    public static final int LA_LT_EXPIRED = 6;

    /*
    CODE: LA_E_PFILE
    
    MESSAGE: Invalid or corrupted product file.
    */

    public static final int LA_E_PFILE = 7;

    /*
    CODE: LA_E_FPATH
    
    MESSAGE: Invalid product file path.
    */

    public static final int LA_E_FPATH = 8;

    /*
    CODE: LA_E_GUID
    
    MESSAGE: The version GUID doesn't match that of the product file.
    */

    public static final int LA_E_GUID = 9;

    /*
    CODE: LA_E_OFILE
    
    MESSAGE: Invalid offline activation response file.
    */

    public static final int LA_E_OFILE = 10;

    /*
    CODE: LA_E_PERMISSION
    
    MESSAGE: Insufficent system permissions. Occurs when LA_SYSTEM flag is used
    but application is not run with admin privileges.
    */

    public static final int LA_E_PERMISSION = 11;

    /*
    CODE: LA_E_EDATA_LEN
    
    MESSAGE: Extra activation data length is more than 256 characters.
    */

    public static final int LA_E_EDATA_LEN = 12;

    /*
    CODE: LA_E_PKEY_TYPE
    
    MESSAGE: Invalid product key type.
    */

    public static final int LA_E_PKEY_TYPE = 13;

    /*
    CODE: LA_E_TIME
    
    MESSAGE: The system time has been tampered with. Ensure your date
    and time settings are correct.
    */

    public static final int LA_E_TIME = 14;

    /*
    CODE: LA_E_VM
    
    MESSAGE: Application is being run inside a virtual machine / hypervisor,
    and activation has been disallowed in the VM.
    but
    */

    public static final int LA_E_VM = 15;

    /*
    CODE: LA_E_WMIC
    
    MESSAGE: Fingerprint couldn't be generated because Windows Management 
    Instrumentation (WMI; service has been disabled. This error is specific
    to Windows only.
    */

    public static final int LA_E_WMIC = 16;

    /*
    CODE: LA_E_TEXT_KEY
    
    MESSAGE: Invalid trial extension key.
    */

    public static final int LA_E_TEXT_KEY = 17;

    /*
    CODE: LA_E_OFILE_EXPIRED
    
    MESSAGE: The offline activation response has expired.
    */

    public static final int LA_E_OFILE_EXPIRED = 18;
    
    /*
    CODE: LA_E_INET
    
    MESSAGE: Failed to connect to the server due to network error.
    */

    public static final int LA_E_INET = 19;

    /*
    CODE: LA_E_PKEY
    
    MESSAGE: Invalid product key.
    */

    public static final int LA_E_PKEY = 20;

    /*
    CODE: LA_E_BUFFER_SIZE
    
    MESSAGE: The buffer size was smaller than required.
    */

    public static final int LA_E_BUFFER_SIZE = 21;

    /*
    CODE: LA_E_CUSTOM_FIELD_ID
    
    MESSAGE: Invalid custom field id.
    */

    public static final int LA_E_CUSTOM_FIELD_ID = 22;

    /*
    CODE: LA_E_NET_PROXY
    
    MESSAGE: Invalid network proxy.
    */

    public static final int LA_E_NET_PROXY = 23;

    /*
    CODE: LA_E_HOST_URL
    
    MESSAGE: Invalid Cryptlex host url.
    */

    public static final int LA_E_HOST_URL = 24;

    /*
    CODE: LA_E_DEACT_LIMIT
    
    MESSAGE: Deactivation limit for key has reached
    */

    public static final int LA_E_DEACT_LIMIT = 25;

    /*
    CODE: LA_E_ACT_LIMIT
    
    MESSAGE: Activation limit for key has reached
    */

    public static final int LA_E_ACT_LIMIT = 26;

    /*
    CODE: LA_E_PDATA
    
    MESSAGE: Invalid product data
    */

    public static final int LA_E_PDATA = 27;

    /*
    CODE: LA_E_TRIAL_NOT_EXPIRED
    
    MESSAGE: Trial has not expired.
    */

    public static final int LA_E_TRIAL_NOT_EXPIRED = 28;

    /*
    CODE: LA_E_COUNTRY
    
    MESSAGE: Country is not allowed
    */

    public static final int LA_E_COUNTRY = 29;

    /*
    CODE: LA_E_IP
    
    MESSAGE: IP address is not allowed
    */

    public static final int LA_E_IP = 30;

    /*
    CODE: LA_E_FILE_PERMISSION
    
    MESSAGE: No permission to write to file
    */

    public static final int LA_E_FILE_PERMISSION = 31;
    
    /*
    CODE: LA_E_LOCAL_TRIAL_NOT_EXPIRED

    MESSAGE: Trial has not expired.
    */

    public static final int LA_E_LOCAL_TRIAL_NOT_EXPIRED = 32;

    /*
    CODE: LA_E_SERVER
    
    MESSAGE: Server error
    */

    public static final int LA_E_SERVER = 33;

    /*
    CODE: LA_E_CLIENT
    
    MESSAGE: Client error
    */

    public static final int LA_E_CLIENT = 34;

}
