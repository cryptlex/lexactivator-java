package com.cryptlex.lexactivator;

import com.sun.jna.Library;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.WString;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Callback;
import java.io.File;

public class LexActivatorNative implements Library
{

    public static NativeLibrary GetNativeLibrary()
    {
        String libraryPath, libraryName, arch;
        libraryPath = System.getProperty("user.dir") + File.separator + "lexactivator";
        libraryName = null;
        arch = Platform.is64Bit() ? "-64" : "";
        switch (Platform.getOSType())
        {
            case Platform.WINDOWS:
                libraryPath = libraryPath + File.separator + "win32-x86" + arch;
                libraryName = "LexActivator.dll";
                break;
            case Platform.MAC:
                libraryPath = libraryPath + File.separator + "mac";
                libraryName = "libLexActivator.dylib";
                break;
            case Platform.LINUX:
                libraryPath = libraryPath + File.separator + "linux-x86" + arch;
                libraryName = "libLexActivator.so";
                break;
        }
        return NativeLibrary.getInstance(libraryPath + File.separator + libraryName);

    }

    static
    {
        Native.register(GetNativeLibrary());
    }

    public interface CallbackType extends Callback
    {

        void invoke(int status);
    }

    public static native int SetProductFile(String filePath);

    public static native int SetProductFile(WString filePath);

    public static native int SetProductData(String productData);

    public static native int SetProductData(WString productData);

    public static native int SetProductId(String productId, int flags);

    public static native int SetProductId(WString productId, int flags);

    public static native int SetLicenseKey(String licenseKey);

    public static native int SetLicenseKey(WString licenseKey);

    public static native int SetLicenseCallback(CallbackType callback);

    public static native int SetActivationMetadata(String key, String value);

    public static native int SetActivationMetadata(WString key, WString value);

    public static native int SetTrialActivationMetadata(String key, String value);

    public static native int SetTrialActivationMetadata(WString key, WString value);

    public static native int SetAppVersion(String appVersion);

    public static native int SetAppVersion(WString appVersion);

    public static native int SetNetworkProxy(String proxy);

    public static native int SetNetworkProxy(WString proxy);

    public static native int GetProductMetadata(String key, ByteBuffer value, int length);

    public static native int GetProductMetadata(WString key, CharBuffer value, int length);

    public static native int GetLicenseMetadata(String key, ByteBuffer value, int length);

    public static native int GetLicenseMetadata(WString key, CharBuffer value, int length);

    public static native int GetLicenseKey(ByteBuffer licenseKey, int length);

    public static native int GetLicenseKey(CharBuffer licenseKey, int length);

    public static native int GetLicenseExpiryDate(IntByReference expiryDate);

    public static native int GetLicenseUserEmail(ByteBuffer email, int length);

    public static native int GetLicenseUserEmail(CharBuffer email, int length);

    public static native int GetLicenseUserName(ByteBuffer name, int length);

    public static native int GetLicenseUserName(CharBuffer name, int length);

    public static native int GetActivationMetadata(String key, ByteBuffer value, int length);

    public static native int GetActivationMetadata(WString key, CharBuffer value, int length);

    public static native int GetTrialActivationMetadata(String key, ByteBuffer value, int length);

    public static native int GetTrialActivationMetadata(WString key, CharBuffer value, int length);

    public static native int GetTrialExpiryDate(IntByReference trialExpiryDate);

    public static native int GetTrialId(ByteBuffer trialId, int length);

    public static native int GetTrialId(CharBuffer trialId, int length);

    public static native int GetLocalTrialExpiryDate(IntByReference trialExpiryDate);

    public static native int ActivateLicense();

    public static native int ActivateLicenseOffline(String filePath);

    public static native int ActivateLicenseOffline(WString filePath);

    public static native int GenerateOfflineActivationRequest(String filePath);

    public static native int GenerateOfflineActivationRequest(WString filePath);

    public static native int DeactivateLicense();

    public static native int GenerateOfflineDeactivationRequest(String filePath);

    public static native int GenerateOfflineDeactivationRequest(WString filePath);

    public static native int IsLicenseGenuine();

    public static native int IsLicenseValid();

    public static native int ActivateTrial();

    public static native int IsTrialGenuine();

    public static native int ActivateLocalTrial(int trialLength);

    public static native int IsLocalTrialGenuine();

    public static native int ExtendLocalTrial(int trialExtensionLength);

    public static native int Reset();
}
