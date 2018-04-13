package com.cryptlex.lexactivator;

import com.sun.jna.Platform;
import com.sun.jna.WString;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.io.UnsupportedEncodingException;
import com.sun.jna.ptr.IntByReference;

public class LexActivator
{

    /* Permission Flags */
    public static final int LA_USER = 1;
    public static final int LA_SYSTEM = 2;

    /**
     * Sets the absolute path of the Product.dat file.<p>
     * </p>
     * This function must be called on every start of your program before any
     * other functions are called.
     *
     * @param filePath absolute path of the product file (Product.dat)
     * @throws LexActivatorException
     */
    public static void SetProductFile(String filePath) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductFile(new WString(filePath))
                : LexActivatorNative.SetProductFile(filePath);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Embeds the Product.dat file in the application.<p>
     * </p>
     * It can be used instead of SetProductFile() in case you want to embed the
     * Product.dat file in your application.<p>
     * </p>
     * This function must be called on every start of your program before any
     * other functions are called.
     *
     * @param productData content of the Product.dat file
     * @throws LexActivatorException
     */
    public static void SetProductData(String productData) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductData(new WString(productData))
                : LexActivatorNative.SetProductData(productData);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the product id of your application.
     * <p>
     * </p>
     * This function must be called on every start of your program before any
     * other functions are called, with the exception of SetProductFile() or
     * SetProductData() function.
     *
     * @param productId the unique product id of your application as mentioned
     * on the product page in the dashboard.
     * @param flags depending upon whether your application requires admin/root
     * permissions to run or not, this parameter can have one of the following
     * values: LA_SYSTEM, LA_USER
     * @throws LexActivatorException
     */
    public static void SetProductId(String versionGuid, int flags) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductId(new WString(versionGuid), flags)
                : LexActivatorNative.SetProductId(versionGuid, flags);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the license key required to activate the license.
     *
     * @param licenseKey a valid license key.
     * @throws LexActivatorException
     */
    public static void SetLicenseKey(String licenseKey) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetLicenseKey(new WString(licenseKey))
                : LexActivatorNative.SetLicenseKey(licenseKey);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the activation metadata.
     * <p>
     * </p>
     * The metadata appears along with the activation details of the license in
     * dashboard.
     * <p>
     * </p>
     *
     * @param key string of maximum length 256 characters with utf-8 encoding.
     * encoding.
     * @param value string of maximum length 256 characters with utf-8 encoding.
     * encoding.
     * @throws LexActivatorException
     */
    public static void SetActivationMetadata(String key, String value) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetActivationMetadata(new WString(key), new WString(value))
                : LexActivatorNative.SetActivationMetadata(key, value);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the trial activation metadata.
     * <p>
     * </p>
     * The metadata appears along with the trial activation details of the
     * product in dashboard.
     * <p>
     * </p>
     *
     * @param key string of maximum length 256 characters with utf-8 encoding.
     * encoding.
     * @param value string of maximum length 256 characters with utf-8 encoding.
     * encoding.
     * @throws LexActivatorException
     */
    public static void SetTrialActivationMetadata(String key, String value) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetTrialActivationMetadata(new WString(key), new WString(value))
                : LexActivatorNative.SetTrialActivationMetadata(key, value);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the current app version of your application.
     * <p>
     * </p>
     * The app version appears along with the activation details in dashboard.
     * It is also used to generate app analytics.
     *
     * @param appVersion string of maximum length 256 characters with utf-8
     * encoding.
     * @throws LexActivatorException
     */
    public static void SetAppVersion(String appVersion) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetNetworkProxy(new WString(appVersion))
                : LexActivatorNative.SetNetworkProxy(appVersion);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the network proxy to be used when contacting CryptLex servers.
     * <p>
     * </p>
     * The proxy format should be:
     * [protocol://][username:password@]machine[:port]
     * <p>
     * </p>
     * <b>Note: </b> Proxy settings of the computer are automatically detected.
     * So, in most of the cases you don't need to care whether your user is
     * behind a proxy server or not.
     *
     * @param proxy proxy string having correct proxy format
     * @throws LexActivatorException
     */
    public static void SetNetworkProxy(String proxy) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetNetworkProxy(new WString(proxy))
                : LexActivatorNative.SetNetworkProxy(proxy);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the product metadata as set in the dashboard.
     * <p>
     * </p>
     * This is available for trial as well as license activations.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetProductMetadata(String key) throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetProductMetadata(new WString(key), buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetProductMetadata(key, buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the license metadata as set in the dashboard.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseMetadata(String key) throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseMetadata(new WString(key), buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseMetadata(key, buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the license key used for activation.
     *
     * @return Returns the license key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseKey() throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseKey(buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseKey(buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the license expiry date timestamp.
     *
     * @return Returns the timestamp
     * @throws LexActivatorException
     */
    public static int GetLicenseExpiryDate() throws LexActivatorException
    {
        int status;
        IntByReference expiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLicenseExpiryDate(expiryDate);
        switch (status)
        {
            case LA_OK:
                return expiryDate.getValue();
            case LA_FAIL:
                return 0;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the license usage count.
     *
     * @return Returns the timestamp
     * @throws LexActivatorException
     */
    public static int GetLicenseUsageCount() throws LexActivatorException
    {
        int status;
        IntByReference expiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLicenseUsageCount(expiryDate);
        switch (status)
        {
            case LA_OK:
                return expiryDate.getValue();
            case LA_FAIL:
                return 0;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the email associated with license user.
     *
     * @return Returns the license user email.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserEmail() throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserEmail(buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserEmail(buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the name associated with license user.
     *
     * @return Returns the license user name.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserName() throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserName(buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserName(buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the activation metadata.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetActivationMetadata(String key) throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetActivationMetadata(new WString(key), buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetActivationMetadata(key, buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the trial activation metadata.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetTrialActivationMetadata(String key) throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetTrialActivationMetadata(new WString(key), buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetTrialActivationMetadata(key, buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the trial expiry date timestamp.
     *
     * @return Returns trial expiry date timestamp.
     * @throws LexActivatorException
     */
    public static int GetTrialExpiryDate() throws LexActivatorException
    {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetTrialExpiryDate(trialExpiryDate);
        switch (status)
        {
            case LA_OK:
                return trialExpiryDate.getValue();
            case LA_FAIL:
                return 0;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the trial activation id. Used in case of trial extension.
     *
     * @return Returns the trial id.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetTrialId() throws LexActivatorException, UnsupportedEncodingException
    {
        int status;
        if (Platform.isWindows())
        {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetTrialId(buffer, 256);
            if (LA_OK == status)
            {
                return buffer.toString().trim();
            }
        } else
        {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetTrialId(buffer, 256);
            if (LA_OK == status)
            {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the local trial expiry date timestamp.
     *
     * @return Returns trial expiry date timestamp.
     * @throws LexActivatorException
     */
    public static int GetLocalTrialExpiryDate() throws LexActivatorException
    {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLocalTrialExpiryDate(trialExpiryDate);
        switch (status)
        {
            case LA_OK:
                return trialExpiryDate.getValue();
            case LA_FAIL:
                return 0;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Activates the license by contacting the Cryptlex servers. It validates
     * the key and returns with encrypted and digitally signed token which it
     * stores and uses to activate your application.
     * <p>
     * </p>
     * This function should be executed at the time of registration, ideally on
     * a button click.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_USAGE_LIMIT_REACHED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLicense() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.ActivateLicense();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_EXPIRED:
                return LA_EXPIRED;
            case LA_SUSPENDED:
                return LA_SUSPENDED;
            case LA_USAGE_LIMIT_REACHED:
                return LA_USAGE_LIMIT_REACHED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Activates your application using the offline activation response file.
     *
     * @param filePath path of the offline activation response file.
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_USAGE_LIMIT_REACHED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLicenseOffline(String filePath) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.ActivateLicenseOffline(new WString(filePath))
                : LexActivatorNative.ActivateLicenseOffline(filePath);
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_EXPIRED:
                return LA_EXPIRED;
            case LA_SUSPENDED:
                return LA_SUSPENDED;
            case LA_USAGE_LIMIT_REACHED:
                return LA_USAGE_LIMIT_REACHED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Generates the offline activation request needed for generating offline
     * activation response in the dashboard.
     *
     * @param filePath path of the file, needed to be created, for the offline
     * request.
     * @throws LexActivatorException
     */
    public static void GenerateOfflineActivationRequest(String filePath) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.GenerateOfflineActivationRequest(new WString(filePath))
                : LexActivatorNative.GenerateOfflineActivationRequest(filePath);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Deactivates the license activation and frees up the correponding
     * activation slot by contacting the Cryptlex servers.
     * <p>
     * </p>
     * This function should be executed at the time of deregistration, ideally
     * on a button click.
     *
     * @return LA_OK, LA_FAIL
     * @throws LexActivatorException
     */
    public static int DeactivateLicense() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.DeactivateLicense();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Generates the offline deactivation request needed for deactivation of the
     * license in the dashboard and deactivates the license locally.
     * <p>
     * </p>
     * A valid offline deactivation file confirms that the license has been
     * successfully deactivated on the user's machine.
     *
     * @param filePath
     * @return LA_OK, LA_FAIL
     * @throws LexActivatorException
     */
    public static int GenerateOfflineDeactivationRequest(String filePath) throws LexActivatorException
    {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.GenerateOfflineDeactivationRequest(new WString(filePath))
                : LexActivatorNative.GenerateOfflineDeactivationRequest(filePath);
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether your app is genuinely activated or not. The
     * verification is done locally by verifying the cryptographic digital
     * signature fetched at the time of activation.
     * <p>
     * </p>
     * After verifying locally, it schedules a server check in a separate
     * thread. After the first server sync it periodically does further syncs at
     * a frequency set for the license.
     * <p>
     * </p>
     * In case server sync fails due to network error, and it continues to fail
     * for fixed number of days (grace period), the function returns
     * LA_GRACE_PERIOD_OVER instead of LA_OK.
     * <p>
     * </p>
     * This function must be called on every start of your program to verify the
     * activation of your app.
     * <p>
     * </p>
     * <b>Note: </b>If application was activated offline using
     * ActivateLicenseOffline() function, you may want to set grace period to 0
     * to ignore grace period.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_GRACE_PERIOD_OVER,
     * LA_USAGE_LIMIT_REACHED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLicenseGenuine() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.IsLicenseGenuine();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_EXPIRED:
                return LA_EXPIRED;
            case LA_SUSPENDED:
                return LA_SUSPENDED;
            case LA_USAGE_LIMIT_REACHED:
                return LA_USAGE_LIMIT_REACHED;
            case LA_GRACE_PERIOD_OVER:
                return LA_GRACE_PERIOD_OVER;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether your app is genuinely activated or not. The
     * verification is done locally by verifying the cryptographic digital
     * signature fetched at the time of activation.
     * <p>
     * </p>
     * This is just an auxiliary function which you may use in some specific
     * cases, when you want to skip the server sync.
     * <p>
     * </p>
     * <b>Note: </b>You may want to set grace period to 0 to ignore grace
     * period.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_GRACE_PERIOD_OVER,
     * LA_USAGE_LIMIT_REACHED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLicenseValid() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.IsLicenseValid();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_EXPIRED:
                return LA_EXPIRED;
            case LA_SUSPENDED:
                return LA_SUSPENDED;
            case LA_USAGE_LIMIT_REACHED:
                return LA_USAGE_LIMIT_REACHED;
            case LA_GRACE_PERIOD_OVER:
                return LA_GRACE_PERIOD_OVER;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Increments the usage count of the license.
     * <p>
     * </p>
     * If increment is more than allowed uses it has no effect.
     *
     * @param increment - the positive increment to add to the usage count
     * @throws LexActivatorException
     */
    public static void IncrementLicenseUsage(int increment) throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.IncrementLicenseUsage(increment);
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Starts the verified trial in your application by contacting the Cryptlex
     * servers.
     * <p>
     * </p>
     * This function should be executed when your application starts first time
     * on the user's computer, ideally on a button click.
     * <p>
     * </p>
     * <b>Note: </b>This function is only meant for verified trials.
     *
     * @return LA_OK, LA_TRIAL_EXPIRED
     * @throws LexActivatorException
     */
    public static int ActivateTrial() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.ActivateTrial();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_TRIAL_EXPIRED:
                return LA_TRIAL_EXPIRED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether trial has started and is genuine or not. The
     * verification is done locally by verifying the cryptographic digital
     * signature fetched at the time of trial activation.
     * <p>
     * </p>
     * This function must be called on every start of your program during the
     * trial period.
     * <p>
     * </p>
     * <b>Note: </b>This function is only meant for verified trials.
     *
     * @return LA_OK, LA_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsTrialGenuine() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.IsTrialGenuine();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_TRIAL_EXPIRED:
                return LA_TRIAL_EXPIRED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Starts the local(unverified) trial.
     * <p>
     * </p>
     * This function should be executed when your application starts first time
     * on the user's computer, ideally on a button click.
     * <p>
     * </p>
     * <b>Note: </b>The function is only meant for local(unverified) trials.
     *
     * @param trialLength trial length in days version
     * @return LA_OK, LA_LOCAL_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLocalTrial(int trialLength) throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.ActivateLocalTrial(trialLength);
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_LOCAL_TRIAL_EXPIRED:
                return LA_LOCAL_TRIAL_EXPIRED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether trial has started and is genuine or not. The
     * verification is done locally.
     * <p>
     * </p>
     * This function must be called on every start of your program during the
     * trial period.
     * <p>
     * </p>
     * <b>Note: </b>The function is only meant for local(unverified) trials.
     *
     * @return LA_OK, LA_LOCAL_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLocalTrialGenuine() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.IsLocalTrialGenuine();
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_LOCAL_TRIAL_EXPIRED:
                return LA_LOCAL_TRIAL_EXPIRED;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Extends the local trial.
     * <p>
     * </p>
     * <b>Note: </b>This function is only meant for unverified trials.
     *
     * @param trialExtensionLength number of days to extend the trial version
     * @return LA_OK, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ExtendLocalTrial(int trialExtensionLength) throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.ExtendLocalTrial(trialExtensionLength);
        switch (status)
        {
            case LA_OK:
                return LA_OK;
            case LA_FAIL:
                return LA_FAIL;
            default:
                throw new LexActivatorException(status);
        }
    }

    /**
     * Resets the activation and trial data stored in the machine.
     * <p>
     * </p>
     * This function is meant for developer testing only.
     * <p>
     * </p>
     * <b>Note: </b>The function does not reset local(unverified) trial data.
     *
     * @throws LexActivatorException
     */
    public static void Reset() throws LexActivatorException
    {
        int status;
        status = LexActivatorNative.Reset();
        if (LA_OK != status)
        {
            throw new LexActivatorException(status);
        }
    }

    /**
     * * Return Codes **
     */
    /**
     * Success code.
     */
    public static final int LA_OK = 0;

    /**
     * Failure code.
     */
    public static final int LA_FAIL = 1;

    /**
     * The license has expired or system time has been tampered with. Ensure
     * your date and time settings are correct.
     */
    public static final int LA_EXPIRED = 20;

    /**
     * The license has been suspended.
     */
    public static final int LA_SUSPENDED = 21;

    /**
     * The grace period for server sync is over.
     */
    public static final int LA_GRACE_PERIOD_OVER = 22;

    /**
     * The license has reached it's allowed usage limit.
     */
    public static final int LA_USAGE_LIMIT_REACHED = 23;

    /**
     * The trial has expired or system time has been tampered with. Ensure your
     * date and time settings are correct.
     */
    public static final int LA_TRIAL_EXPIRED = 24;

    /**
     * The local trial has expired or system time has been tampered with. Ensure
     * your date and time settings are correct.
     */
    public static final int LA_LOCAL_TRIAL_EXPIRED = 25;

}
