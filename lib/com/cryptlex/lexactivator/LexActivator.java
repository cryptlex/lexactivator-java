package com.cryptlex.lexactivator;

import com.sun.jna.Platform;
import com.sun.jna.WString;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.io.UnsupportedEncodingException;
import com.sun.jna.ptr.IntByReference;

public class LexActivator {

    /* Permission Flags */
    public static final int LA_USER = 1;
    public static final int LA_SYSTEM = 2;

    /**
     * Sets the path of the Product.dat file. This should be used if your
     * application and Product.dat file are in different folders or you have
     * renamed the Product.dat file.<p>
     * </p>
     * If this function is used, it must be called on every start of your
     * program before any other functions are called.
     *
     * @param filePath path of the product file (Product.dat)
     * @throws LexActivatorException
     */
    public static void SetProductFile(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductFile(new WString(filePath))
                : LexActivatorNative.SetProductFile(filePath);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the path of the Product.dat file. This should be used if your
     * application and Product.dat file are in different folders or you have
     * renamed the Product.dat file.<p>
     * </p>
     * If this function is used, it must be called on every start of your
     * program before any other functions are called.
     *
     * @param filePath path of the product file (Product.dat)
     * @throws LexActivatorException
     */
    public static void SetProductData(String productData) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductData(new WString(productData))
                : LexActivatorNative.SetProductData(productData);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the version GUID of your application.
     * <p>
     * </p>
     * This function must be called on every start of your program before any
     * other functions are called, with the exception of SetProductFile()
     * function.
     *
     * @param versionGUID the unique version GUID of your application as
     * mentioned on the product version page of your application in the
     * dashboard.
     * @param flags depending upon whether your application requires admin/root
     * permissions to run or not, this parameter can have one of the following
     * values: LA_SYSTEM, LA_USER
     * @throws LexActivatorException
     */
    public static void SetProductVersionGuid(String versionGuid, int flags) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductVersionGuid(new WString(versionGuid), flags)
                : LexActivatorNative.SetProductVersionGuid(versionGuid, flags);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the product key required to activate the application.
     *
     * @param productKey a valid product key generated for the application.
     * @throws LexActivatorException
     */
    public static void SetProductKey(String productKey) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductKey(new WString(productKey))
                : LexActivatorNative.SetProductKey(productKey);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the extra data which you may want to fetch from the user at the time
     * of activation.
     * <p>
     * </p>
     * The extra data appears along with activation details of the product key
     * in dashboard.
     * <p>
     * </p>
     * <b>Note: </b>If the length of the string is more than 256, it throws
     * an exception.
     *
     * @param extraData string of maximum length 256 characters with utf-8
     * encoding.
     * @throws LexActivatorException
     */
    public static void SetActivationExtraData(String extraData) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetActivationExtraData(new WString(extraData))
                : LexActivatorNative.SetActivationExtraData(extraData);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the extra data which you may want to fetch from the user at the time
     * of activation.
     * <p>
     * </p>
     * The extra data appears along with activation details of the product key
     * in dashboard.
     * <p>
     * </p>
     * <b>Note: </b>If the length of the string is more than 256, it throws
     * an exception.
     *
     * @param extraData string of maximum length 256 characters with utf-8
     * encoding.
     * @throws LexActivatorException
     */
    public static void SetTrialActivationExtraData(String extraData) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetTrialActivationExtraData(new WString(extraData))
                : LexActivatorNative.SetTrialActivationExtraData(extraData);
        if (LA_OK != status) {
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
    public static void SetNetworkProxy(String proxy) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetNetworkProxy(new WString(proxy))
                : LexActivatorNative.SetNetworkProxy(proxy);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the stored product key which was used for activation.
     *
     * @return Returns the product key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetAppVersion() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetAppVersion(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(30);
            status = LexActivatorNative.GetAppVersion(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }

        throw new LexActivatorException(status);

    }

    /**
     * Gets the stored product key which was used for activation.
     *
     * @return Returns the product key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetProductKey() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetProductKey(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetProductKey(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }

        throw new LexActivatorException(status);

    }

    /**
     * Gets the stored product key which was used for activation.
     *
     * @return Returns the product key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetProductKeyEmail() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetProductKeyEmail(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetProductKeyEmail(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }

        throw new LexActivatorException(status);

    }

    /**
     * Gets the timestamp of the expiry date.
     *
     * @return Returns the timestamp
     * @throws LexActivatorException
     */

    public static int GetProductKeyExpiryDate() throws LexActivatorException {
        int status;
        IntByReference expiryDate = new IntByReference(0);
        status = LexActivatorNative.GetProductKeyExpiryDate(expiryDate);
        switch (status) {
        case LA_OK:
            return expiryDate.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Get the value of the custom field associated with the product key.
     *
     * @param fieldId id of the custom field whose value you want to get
     * @return Returns the custom field value.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetProductKeyCustomField(String fieldId) throws LexActivatorException, UnsupportedEncodingException {

        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetProductKeyCustomField(new WString(fieldId), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetProductKeyCustomField(fieldId, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the value of the extra activation data.
     *
     * @return Returns the extra activation data.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetActivationExtraData() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(1024);
            status = LexActivatorNative.GetActivationExtraData(buffer, 1024);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            status = LexActivatorNative.GetActivationExtraData(buffer, 1024);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the value of the extra activation data.
     *
     * @return Returns the extra activation data.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetTrialActivationExtraData() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(1024);
            status = LexActivatorNative.GetTrialActivationExtraData(buffer, 1024);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            status = LexActivatorNative.GetTrialActivationExtraData(buffer, 1024);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8");
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the number of remaining trial days.
     * <p>
     * </p>
     * If the trial has expired or has been tampered, daysLeft is set to 0 days.
     * <p>
     * </p>
     * <b>Note: </b>The trial must be started by calling ActivateTrial() or
     * InitializeTrial() at least once in the past before calling this function.
     *
     * @param trialType depending upon whether your application uses verified
     * trial or not, this parameter can have one of the following values:
     * LA_V_TRIAL, LA_UV_TRIAL
     * @return Returns number of trial days left.
     * @throws LexActivatorException
     */
    public static int GetTrialExpiryDate() throws LexActivatorException {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetTrialExpiryDate(trialExpiryDate);
        switch (status) {
        case LA_OK:
            return daysLeft.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the number of remaining trial days.
     * <p>
     * </p>
     * If the trial has expired or has been tampered, daysLeft is set to 0 days.
     * <p>
     * </p>
     * <b>Note: </b>The trial must be started by calling ActivateTrial() or
     * InitializeTrial() at least once in the past before calling this function.
     *
     * @param trialType depending upon whether your application uses verified
     * trial or not, this parameter can have one of the following values:
     * LA_V_TRIAL, LA_UV_TRIAL
     * @return Returns number of trial days left.
     * @throws LexActivatorException
     */
    public static int GetLocalTrialExpiryDate() throws LexActivatorException {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLocalTrialExpiryDate(trialExpiryDate);
        switch (status) {
        case LA_OK:
            return daysLeft.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Activates your application by contacting the Cryptlex servers. It
     * validates the key and returns with encrypted and digitally signed
     * response which it stores and uses to activate your application.
     * <p>
     * </p>
     * This function should be executed at the time of registration, ideally on
     * a button click.
     *
     * @return LA_OK, LA_EXPIRED, LA_REVOKED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateProduct() throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateProduct();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_REVOKED:
            return LA_REVOKED;
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
     * @return LA_OK, LA_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateProductOffline(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.ActivateProductOffline(new WString(filePath))
                : LexActivatorNative.ActivateProductOffline(filePath);
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
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
    public static void GenerateOfflineActivationRequest(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.GenerateOfflineActivationRequest(new WString(filePath))
                : LexActivatorNative.GenerateOfflineActivationRequest(filePath);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Deactivates the application and frees up the correponding activation slot
     * by contacting the Cryptlex servers.
     * <p>
     * </p>
     * This function should be executed at the time of deregistration, ideally
     * on a button click.
     *
     * @return LA_OK, LA_EXPIRED, LA_REVOKED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int DeactivateProduct() throws LexActivatorException {
        int status;
        status = LexActivatorNative.DeactivateProduct();
        switch (status) {
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
     * product key in the dashboard and deactivates the application.
     * <p>
     * </p>
     * A valid offline deactivation file confirms that the application has been
     * successfully deactivated on the user's machine.
     *
     * @param filePath
     * @return LA_OK, LA_EXPIRED, LA_REVOKED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int GenerateOfflineDeactivationRequest(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.GenerateOfflineDeactivationRequest(new WString(filePath))
                : LexActivatorNative.GenerateOfflineDeactivationRequest(filePath);
        switch (status) {
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
     * After verifying locally, it schedules a server check in a separate thread
     * on due dates. The default interval for server check is 100 days and this
     * can be changed if required.
     * <p>
     * </p>
     * In case server validation fails due to network error, it retries every 15
     * minutes. If it continues to fail for fixed number of days (grace period),
     * the function returns LA_GP_OVER instead of LA_OK. The default length of
     * grace period is 30 days and this can be changed if required.
     * <p>
     * </p>
     * This function must be called on every start of your program to verify the
     * activation of your app.
     * <p>
     * </p>
     * <b>Note: </b>If application was activated offline using
     * ActivateProductOffline() function, you may want to set grace period to 0
     * to ignore grace period.
     *
     * @return LA_OK, LA_EXPIRED, LA_REVOKED, LA_GP_OVER, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsProductGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsProductGenuine();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_REVOKED:
            return LA_REVOKED;
        case LA_GP_OVER:
            return LA_GP_OVER;
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
     * cases.
     *
     * @return LA_OK, LA_EXPIRED, LA_REVOKED, LA_GP_OVER, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsProductActivated() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsProductActivated();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_REVOKED:
            return LA_REVOKED;
        case LA_GP_OVER:
            return LA_GP_OVER;
        case LA_FAIL:
            return LA_FAIL;
        default:
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
     * @return LA_OK, LA_T_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateTrial() throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateTrial();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_T_EXPIRED:
            return LA_T_EXPIRED;
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
     * @return LA_OK, LA_T_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsTrialGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsTrialGenuine();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_T_EXPIRED:
            return LA_T_EXPIRED;
        case LA_FAIL:
            return LA_FAIL;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Extends the trial using the trial extension key generated in the
     * dashboard for the product version.
     * <p>
     * </p>
     * <b>Note: </b>This function is only meant for verified trials.
     *
     * @param trialExtensionKey trial extension key generated for the product
     * version
     * @return LA_OK, LA_TEXT_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ExtendTrial(String trialExtensionKey) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.ExtendTrial(new WString(trialExtensionKey))
                : LexActivatorNative.ExtendTrial(trialExtensionKey);
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_T_EXPIRED:
            return LA_T_EXPIRED;
        case LA_FAIL:
            return LA_FAIL;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Starts the unverified trial if trial has not started yet and if trial has
     * already started, it verifies the validity of trial.
     * <p>
     * </p>
     * This function must be called on every start of your program during the
     * trial period.
     * <p>
     * </p>
     * <b>Note: </b>This function is only meant for unverified trials.
     *
     * @param trialLength trial length as set in the dashboard for the product
     * version
     * @return LA_OK, LA_T_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLocalTrial(int trialLength) throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateLocalTrial(trialLength);
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_LT_EXPIRED:
            return LA_LT_EXPIRED;
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
     * @return LA_OK, LA_T_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLocalTrialGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsLocalTrialGenuine();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_LT_EXPIRED:
            return LA_T_EXPIRED;
        case LA_FAIL:
            return LA_FAIL;
        default:
            throw new LexActivatorException(status);
        }
    }

    /*** Return Codes ***/

    public static final int LA_OK = 0;

    public static final int LA_FAIL = 1;

    /**
     * The product key has expired or system time has been tampered with. Ensure
     * your date and time settings are correct.
     */
    public static final int LA_EXPIRED = 2;

    /**
     * The product key has been revoked
     */
    public static final int LA_REVOKED = 3;

    /**
     * The grace period is over.
     */
    public static final int LA_GP_OVER = 4;

    /**
     * The trial has expired or system time has been tampered with. Ensure your
     * date and time settings are correct.
     */
    public static final int LA_T_EXPIRED = 19;

    /**
     * The local trial has expired or system time has been tampered
     * with. Ensure your date and time settings are correct.
     */
    public static final int LA_LT_EXPIRED = 20;

}
