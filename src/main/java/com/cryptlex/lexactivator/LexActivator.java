package com.cryptlex.lexactivator;

import com.sun.jna.Platform;
import com.sun.jna.WString;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.io.UnsupportedEncodingException;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;
import java.util.List;

public class LexActivator {
    private static LexActivatorNative.CallbackType privateLicenseCallback = null;
    private static LexActivatorNative.CallbackType privateReleaseCallback = null;
    private static List<LicenseCallbackEvent> licenseCallbackEventListeners = null;
    private static List<ReleaseCallbackEvent> releaseCallbackEventListeners = null;

    /* Permission Flags */
    public static final int LA_USER = 1;
    public static final int LA_SYSTEM = 2;
    public static final int LA_IN_MEMORY = 4;

    /**
     * Sets the absolute path of the Product.dat file.
     * This function must be called on every start of your program before any other
     * functions are called.
     *
     * @param filePath absolute path of the product file (Product.dat)
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
     * Embeds the Product.dat file in the application.
     * It can be used instead of SetProductFile() in case you want to embed the
     * Product.dat file in your application.
     * This function must be called on every start of your program before any other
     * functions are called.
     *
     * @param productData content of the Product.dat file
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
     * Sets the product id of your application.
     * This function must be called on every start of your program before any other
     * functions are called, with the exception of SetProductFile() or
     * SetProductData() function.
     *
     * @param productId the unique product id of your application as mentioned on
     *                  the product page in the dashboard.
     * @param flags     depending upon whether your application requires admin/root
     *                  permissions to run or not, this parameter can have one of
     *                  the following values: LA_SYSTEM, LA_USER, LA_IN_MEMORY
     * @throws LexActivatorException
     */
    public static void SetProductId(String productId, int flags) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetProductId(new WString(productId), flags)
                : LexActivatorNative.SetProductId(productId, flags);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }
    
    /**
     * In case you want to change the default directory used by LexActivator to
     * store the activation data on Linux and macOS, this function can be used to
     * set a different directory.
     * If you decide to use this function, then it must be called on every start of
     * your program before calling SetProductFile() or SetProductData() function.
     * Please ensure that the directory exists and your app has read and write
     * permissions in the directory.
     *
     * @param directoryPath absolute path of the directory.
     * @throws LexActivatorException
     */
    public static void SetDataDirectory(String directoryPath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetDataDirectory(new WString(directoryPath))
                : LexActivatorNative.SetDataDirectory(directoryPath);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * In case you don't want to use the LexActivator's advanced
     * device fingerprinting algorithm, this function can be used to set a custom
     * device fingerprint.
     * If you decide to use your own custom device fingerprint then this function must be
     * called on every start of your program immediately after calling SetProductFile()
     * or SetProductData() function.
     * The license fingerprint matching strategy is ignored if this function is used.
     *
     * @param fingerprint string of minimum length 64 characters and maximum length 256 characters.
     * @throws LexActivatorException
     */
    public static void SetCustomDeviceFingerprint(String fingerprint) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetCustomDeviceFingerprint(new WString(fingerprint))
                : LexActivatorNative.SetCustomDeviceFingerprint(fingerprint);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the license key required to activate the license.
     *
     * @param licenseKey a valid license key.
     * @throws LexActivatorException
     */
    public static void SetLicenseKey(String licenseKey) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetLicenseKey(new WString(licenseKey))
                : LexActivatorNative.SetLicenseKey(licenseKey);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }
    
    /**
     * Sets the license user email and password for authentication.
     * This function must be called before ActivateLicense() or IsLicenseGenuine()
     * function if <b>requireAuthentication</b> property of the license is set to true.
     *
     * @param email user email address.
     * @param password user password.
     * @throws LexActivatorException
     */
    public static void SetLicenseUserCredential(String email, String password) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetLicenseUserCredential(new WString(email), new WString(password))
                : LexActivatorNative.SetLicenseUserCredential(email, password);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets server sync callback function.
     * Whenever the server sync occurs in a separate thread, and server returns the
     * response, event listener function gets invoked with the following status
     * codes: LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_E_REVOKED,
     * LA_E_ACTIVATION_NOT_FOUND, LA_E_MACHINE_FINGERPRINT LA_E_COUNTRY, LA_E_INET,
     * LA_E_SERVER, LA_E_RATE_LIMIT, LA_E_IP
     *
     * @param listener
     * @throws LexActivatorException
     */
    public static void SetLicenseCallbackListener(LicenseCallbackEvent listener) throws LexActivatorException {
        if (licenseCallbackEventListeners == null) {
            licenseCallbackEventListeners = new ArrayList<>();
            licenseCallbackEventListeners.add(listener);
        }
        if (privateLicenseCallback == null) {
            privateLicenseCallback = new LexActivatorNative.CallbackType() {
                public void invoke(int status) {
                    // Notify everybody that may be interested.
                    for (LicenseCallbackEvent event : licenseCallbackEventListeners) {
                        event.LicenseCallback(status);
                    }
                }
            };
            int status;
            status = LexActivatorNative.SetLicenseCallback(privateLicenseCallback);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        }

    }

    /**
     * Sets the activation metadata.
     * The metadata appears along with the activation details of the license in
     * dashboard.
     *
     * @param key   string of maximum length 256 characters with utf-8 encoding.
     *              encoding.
     * @param value string of maximum length 256 characters with utf-8 encoding.
     *              encoding.
     * @throws LexActivatorException
     */
    public static void SetActivationMetadata(String key, String value) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetActivationMetadata(new WString(key), new WString(value))
                : LexActivatorNative.SetActivationMetadata(key, value);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the trial activation metadata.
     * The metadata appears along with the trial activation details of the product
     * in dashboard.
     *
     * @param key   string of maximum length 256 characters with utf-8 encoding.
     *              encoding.
     * @param value string of maximum length 256 characters with utf-8 encoding.
     *              encoding.
     * @throws LexActivatorException
     */
    public static void SetTrialActivationMetadata(String key, String value) throws LexActivatorException {
        int status;
        status = Platform.isWindows()
                ? LexActivatorNative.SetTrialActivationMetadata(new WString(key), new WString(value))
                : LexActivatorNative.SetTrialActivationMetadata(key, value);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the current app version of your application.
     * The app version appears along with the activation details in dashboard. It is
     * also used to generate app analytics.
     *
     * @param appVersion string of maximum length 256 characters with utf-8
     *                   encoding.
     * @throws LexActivatorException
     */
    public static void SetAppVersion(String appVersion) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetNetworkProxy(new WString(appVersion))
                : LexActivatorNative.SetNetworkProxy(appVersion);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Sets the meter attribute uses for the offline activation request.
     * This function should only be called before GenerateOfflineActivationRequest()
     * function to set the meter attributes in case of offline activation.
     * 
     * @param name name of the meter attribute
     * @param uses the uses value
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static void SetOfflineActivationRequestMeterAttributeUses(String name, int uses) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            status = LexActivatorNative.SetOfflineActivationRequestMeterAttributeUses(new WString(name), uses);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        } else {
            status = LexActivatorNative.SetOfflineActivationRequestMeterAttributeUses(name, uses);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        }
    }

    /**
     * Sets the network proxy to be used when contacting CryptLex servers.
     * The proxy format should be: [protocol://][username:password@]machine[:port]
     * <b>Note: </b> Proxy settings of the computer are automatically detected. So,
     * in most of the cases you don't need to care whether your user is behind a
     * proxy server or not.
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
     * In case you are running Cryptlex on-premise, you can set the host for your
     * on-premise server.
     *
     * @param host the address of the Cryptlex on-premise server
     * @throws LexActivatorException
     */
    public static void SetCryptlexHost(String host) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.SetCryptlexHost(new WString(host))
                : LexActivatorNative.SetCryptlexHost(host);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the product metadata as set in the dashboard.
     * This is available for trial as well as license activations.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetProductMetadata(String key) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetProductMetadata(new WString(key), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetProductMetadata(key, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
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
    public static String GetLicenseMetadata(String key) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseMetadata(new WString(key), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseMetadata(key, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }
    
    /**
     * Gets the license meter attribute allowed, total and gross uses.
     *
     * @param name name of the meter attribute
     * @return Returns the values of meter attribute allowed, total and gross uses.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static LicenseMeterAttribute GetLicenseMeterAttribute(String name) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        IntByReference allowedUses = new IntByReference(0);
        IntByReference totalUses = new IntByReference(0);
        IntByReference grossUses = new IntByReference(0);

        if (Platform.isWindows()) {
            status = LexActivatorNative.GetLicenseMeterAttribute(new WString(name), allowedUses, totalUses, grossUses);
            if (LA_OK == status) {
                return new LicenseMeterAttribute(name, allowedUses.getValue(), totalUses.getValue(), grossUses.getValue());
            }
        } else {
            status = LexActivatorNative.GetLicenseMeterAttribute(name, allowedUses, totalUses, grossUses);
            if (LA_OK == status) {
                return new LicenseMeterAttribute(name, allowedUses.getValue(), totalUses.getValue(), grossUses.getValue());
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
    public static String GetLicenseKey() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseKey(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseKey(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the allowed activations of the license.
     *
     * @return Returns the allowed activations
     * @throws LexActivatorException
     */
    public static int GetLicenseAllowedActivations() throws LexActivatorException {
        int status;
        IntByReference allowedActivations = new IntByReference(0);
        status = LexActivatorNative.GetLicenseAllowedActivations(allowedActivations);
        switch (status) {
        case LA_OK:
            return allowedActivations.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the total activations of the license.
     *
     * @return Returns the total activations
     * @throws LexActivatorException
     */
    public static int GetLicenseTotalActivations() throws LexActivatorException {
        int status;
        IntByReference totalActivations = new IntByReference(0);
        status = LexActivatorNative.GetLicenseTotalActivations(totalActivations);
        switch (status) {
        case LA_OK:
            return totalActivations.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the license expiry date timestamp.
     *
     * @return Returns the timestamp
     * @throws LexActivatorException
     */
    public static int GetLicenseExpiryDate() throws LexActivatorException {
        int status;
        IntByReference expiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLicenseExpiryDate(expiryDate);
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
     * Gets the email associated with license user.
     *
     * @return Returns the license user email.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserEmail() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserEmail(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserEmail(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the name associated with the license user.
     *
     * @return Returns the license user name.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserName() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserName(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserName(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the company associated with the license user.
     *
     * @return Returns the license user company.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserCompany() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserCompany(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserCompany(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the metadata associated with the license user.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseUserMetadata(String key) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserMetadata(new WString(key), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseUserMetadata(key, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the license type (node-locked or hosted-floating).
     *
     * @return Returns the license type.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLicenseType() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseType(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLicenseType(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
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
    public static String GetActivationMetadata(String key) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetActivationMetadata(new WString(key), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetActivationMetadata(key, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }
    
    /**
     * Gets the meter attribute uses consumed by the activation.
     *
     * @param name name of the meter attribute
     * @return Returns the value of meter attribute uses by the activation.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static int GetActivationMeterAttributeUses(String name) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        IntByReference uses = new IntByReference(0);
        if (Platform.isWindows()) {
            status = LexActivatorNative.GetActivationMeterAttributeUses(new WString(name), uses);
            if (LA_OK == status) {
                return uses.getValue();
            }
        } else {
            status = LexActivatorNative.GetActivationMeterAttributeUses(name, uses);
            if (LA_OK == status) {
                return uses.getValue();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Gets the server sync grace period expiry date timestamp.
     *
     * @return Returns server sync grace period expiry date timestamp.
     * @throws LexActivatorException
     */
    public static int GetServerSyncGracePeriodExpiryDate() throws LexActivatorException {
        int status;
        IntByReference expiryDate = new IntByReference(0);
        status = LexActivatorNative.GetServerSyncGracePeriodExpiryDate(expiryDate);
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
     * Gets the trial activation metadata.
     *
     * @param key key to retrieve the value
     * @return Returns the value of metadata for the key.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetTrialActivationMetadata(String key)
            throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetTrialActivationMetadata(new WString(key), buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetTrialActivationMetadata(key, buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
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
    public static int GetTrialExpiryDate() throws LexActivatorException {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetTrialExpiryDate(trialExpiryDate);
        switch (status) {
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
    public static String GetTrialId() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetTrialId(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetTrialId(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
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
    public static int GetLocalTrialExpiryDate() throws LexActivatorException {
        int status;
        IntByReference trialExpiryDate = new IntByReference(0);
        status = LexActivatorNative.GetLocalTrialExpiryDate(trialExpiryDate);
        switch (status) {
        case LA_OK:
            return trialExpiryDate.getValue();
        case LA_FAIL:
            return 0;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Gets the version of this library.
     *
     * @return Returns the library version.
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static String GetLibraryVersion() throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            CharBuffer buffer = CharBuffer.allocate(256);
            status = LexActivatorNative.GetLibraryVersion(buffer, 256);
            if (LA_OK == status) {
                return buffer.toString().trim();
            }
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            status = LexActivatorNative.GetLibraryVersion(buffer, 256);
            if (LA_OK == status) {
                return new String(buffer.array(), "UTF-8").trim();
            }
        }
        throw new LexActivatorException(status);
    }

    /**
     * Checks whether a new release is available for the product.
     * This function should only be used if you manage your releases through
     * Cryptlex release management API.
     *
     * @param platform release platform e.g. windows, macos, linux
     * @param version current release version
     * @param channel release channel e.g. stable
     * @param listener listener to listen to the release update event
     * @throws LexActivatorException
     */
    public static void CheckForReleaseUpdate(String platform, String version, String channel,
            ReleaseCallbackEvent listener) throws LexActivatorException {
        if (releaseCallbackEventListeners == null) {
            releaseCallbackEventListeners = new ArrayList<>();
            releaseCallbackEventListeners.add(listener);
        }
        if (privateReleaseCallback == null) {
            privateReleaseCallback = new LexActivatorNative.CallbackType() {
                public void invoke(int status) {
                    // Notify everybody that may be interested.
                    for (ReleaseCallbackEvent event : releaseCallbackEventListeners) {
                        event.ReleaseCallback(status);
                    }
                }
            };
            int status;
            status = Platform.isWindows()
                    ? LexActivatorNative.CheckForReleaseUpdate(new WString(platform), new WString(version),
                            new WString(channel), privateReleaseCallback)
                    : LexActivatorNative.CheckForReleaseUpdate(platform, version, channel, privateReleaseCallback);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }

        }
    }

    /**
     * Activates the license by contacting the Cryptlex servers. It validates the
     * key and returns with encrypted and digitally signed token which it stores and
     * uses to activate your application.
     * This function should be executed at the time of registration, ideally on a
     * button click.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLicense() throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateLicense();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_SUSPENDED:
            return LA_SUSPENDED;
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
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLicenseOffline(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.ActivateLicenseOffline(new WString(filePath))
                : LexActivatorNative.ActivateLicenseOffline(filePath);
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_SUSPENDED:
            return LA_SUSPENDED;
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
     *                 request.
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
     * Deactivates the license activation and frees up the correponding activation
     * slot by contacting the Cryptlex servers.
     * This function should be executed at the time of deregistration, ideally on a
     * button click.
     *
     * @return LA_OK, LA_FAIL
     * @throws LexActivatorException
     */
    public static int DeactivateLicense() throws LexActivatorException {
        int status;
        status = LexActivatorNative.DeactivateLicense();
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
     * license in the dashboard and deactivates the license locally.
     * A valid offline deactivation file confirms that the license has been
     * successfully deactivated on the user's machine.
     *
     * @param filePath
     * @return LA_OK, LA_FAIL
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
     * It verifies whether your app is genuinely activated or not. The verification
     * is done locally by verifying the cryptographic digital signature fetched at
     * the time of activation.
     * After verifying locally, it schedules a server check in a separate thread.
     * After the first server sync it periodically does further syncs at a frequency
     * set for the license.
     * In case server sync fails due to network error, and it continues to fail for
     * fixed number of days (grace period), the function returns
     * LA_GRACE_PERIOD_OVER instead of LA_OK.
     * This function must be called on every start of your program to verify the
     * activation of your app.
     * <b>Note: </b>If application was activated offline using
     * ActivateLicenseOffline() function, you may want to set grace period to 0 to
     * ignore grace period.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_GRACE_PERIOD_OVER, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLicenseGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsLicenseGenuine();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_SUSPENDED:
            return LA_SUSPENDED;
        case LA_GRACE_PERIOD_OVER:
            return LA_GRACE_PERIOD_OVER;
        case LA_FAIL:
            return LA_FAIL;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether your app is genuinely activated or not. The verification
     * is done locally by verifying the cryptographic digital signature fetched at
     * the time of activation.
     * This is just an auxiliary function which you may use in some specific cases,
     * when you want to skip the server sync.
     * <b>Note: </b>You may want to set grace period to 0 to ignore grace period.
     *
     * @return LA_OK, LA_EXPIRED, LA_SUSPENDED, LA_GRACE_PERIOD_OVER, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLicenseValid() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsLicenseValid();
        switch (status) {
        case LA_OK:
            return LA_OK;
        case LA_EXPIRED:
            return LA_EXPIRED;
        case LA_SUSPENDED:
            return LA_SUSPENDED;
        case LA_GRACE_PERIOD_OVER:
            return LA_GRACE_PERIOD_OVER;
        case LA_FAIL:
            return LA_FAIL;
        default:
            throw new LexActivatorException(status);
        }
    }

    /**
     * Starts the verified trial in your application by contacting the Cryptlex
     * servers.
     * This function should be executed when your application starts first time on
     * the user's computer, ideally on a button click.
     * <b>Note: </b>This function is only meant for verified trials.
     *
     * @return LA_OK, LA_TRIAL_EXPIRED
     * @throws LexActivatorException
     */
    public static int ActivateTrial() throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateTrial();
        switch (status) {
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
     * Activates the trial using the offline activation response file.
     *
     * @param filePath path of the offline activation response file.
     * @return LA_OK, LA_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateTrialOffline(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.ActivateTrialOffline(new WString(filePath))
                : LexActivatorNative.ActivateTrialOffline(filePath);
        switch (status) {
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
     * Generates the offline trial activation request needed for generating offline
     * trial activation response in the dashboard.
     *
     * @param filePath path of the file, needed to be created, for the offline
     *                 request.
     * @throws LexActivatorException
     */
    public static void GenerateOfflineTrialActivationRequest(String filePath) throws LexActivatorException {
        int status;
        status = Platform.isWindows() ? LexActivatorNative.GenerateOfflineTrialActivationRequest(new WString(filePath))
                : LexActivatorNative.GenerateOfflineTrialActivationRequest(filePath);
        if (LA_OK != status) {
            throw new LexActivatorException(status);
        }
    }

    /**
     * It verifies whether trial has started and is genuine or not. The verification
     * is done locally by verifying the cryptographic digital signature fetched at
     * the time of trial activation.
     * This function must be called on every start of your program during the trial
     * period.
     * <b>Note: </b>This function is only meant for verified trials.
     *
     * @return LA_OK, LA_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsTrialGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsTrialGenuine();
        switch (status) {
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
     * This function should be executed when your application starts first time on
     * the user's computer, ideally on a button click.
     * <b>Note: </b>The function is only meant for local(unverified) trials.
     *
     * @param trialLength trial length in days
     * @return LA_OK, LA_LOCAL_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ActivateLocalTrial(int trialLength) throws LexActivatorException {
        int status;
        status = LexActivatorNative.ActivateLocalTrial(trialLength);
        switch (status) {
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
     * It verifies whether trial has started and is genuine or not. The verification
     * is done locally.
     * This function must be called on every start of your program during the trial
     * period.
     * <b>Note: </b>The function is only meant for local(unverified) trials.
     *
     * @return LA_OK, LA_LOCAL_TRIAL_EXPIRED, LA_FAIL
     * @throws LexActivatorException
     */
    public static int IsLocalTrialGenuine() throws LexActivatorException {
        int status;
        status = LexActivatorNative.IsLocalTrialGenuine();
        switch (status) {
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
     * <b>Note: </b>This function is only meant for unverified trials.
     *
     * @param trialExtensionLength number of days to extend the trial
     * @return LA_OK, LA_FAIL
     * @throws LexActivatorException
     */
    public static int ExtendLocalTrial(int trialExtensionLength) throws LexActivatorException {
        int status;
        status = LexActivatorNative.ExtendLocalTrial(trialExtensionLength);
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
     * Increments the meter attribute uses of the activation.
     *
     * @param name name of the meter attribute
     * @param increment the increment value
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static void IncrementActivationMeterAttributeUses(String name, int increment) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            status = LexActivatorNative.IncrementActivationMeterAttributeUses(new WString(name), increment);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        } else {
            status = LexActivatorNative.IncrementActivationMeterAttributeUses(name, increment);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        }
    }
    
    /**
     * Decrements the meter attribute uses of the activation.
     *
     * @param name name of the meter attribute
     * @param decrement the decrement value
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static void DecrementActivationMeterAttributeUses(String name, int decrement) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            status = LexActivatorNative.DecrementActivationMeterAttributeUses(new WString(name), decrement);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        } else {
            status = LexActivatorNative.DecrementActivationMeterAttributeUses(name, decrement);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        }
    }
    
    /**
     * Resets the meter attribute uses of the activation.
     *
     * @param name name of the meter attribute
     * @throws LexActivatorException
     * @throws UnsupportedEncodingException
     */
    public static void ResetActivationMeterAttributeUses(String name) throws LexActivatorException, UnsupportedEncodingException {
        int status;
        if (Platform.isWindows()) {
            status = LexActivatorNative.ResetActivationMeterAttributeUses(new WString(name));
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        } else {
            status = LexActivatorNative.ResetActivationMeterAttributeUses(name);
            if (LA_OK != status) {
                throw new LexActivatorException(status);
            }
        }
    }

    /**
     * Resets the activation and trial data stored in the machine.
     * This function is meant for developer testing only.
     * <b>Note: </b>The function does not reset local(unverified) trial data.
     *
     * @throws LexActivatorException
     */
    public static void Reset() throws LexActivatorException {
        int status;
        status = LexActivatorNative.Reset();
        if (LA_OK != status) {
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
     * The license has expired or system time has been tampered with. Ensure your
     * date and time settings are correct.
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
     * The trial has expired or system time has been tampered with. Ensure your date
     * and time settings are correct.
     */
    public static final int LA_TRIAL_EXPIRED = 25;

    /**
     * The local trial has expired or system time has been tampered with. Ensure
     * your date and time settings are correct.
     */
    public static final int LA_LOCAL_TRIAL_EXPIRED = 26;

    /*
     * CODE: LA_RELEASE_UPDATE_AVAILABLE
     * 
     * MESSAGE: A new update is available for the product. This means a new release
     * has been published for the product.
     */
    public static final int LA_RELEASE_UPDATE_AVAILABLE = 30;

    /*
     * CODE: LA_RELEASE_NO_UPDATE_AVAILABLE
     * 
     * MESSAGE: No new update is available for the product. The current version is
     * latest.
     */
    public static final int LA_RELEASE_NO_UPDATE_AVAILABLE = 31;

}
