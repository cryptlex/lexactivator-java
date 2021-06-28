package com.cryptlex.lexactivator;

import com.sun.jna.Library;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.NativeLong;

import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.Callback;

public class LexActivatorNative implements Library {

    static{
            Native.register("LexActivator");
    }
    
    public interface CallbackType extends Callback {

        void invoke(NativeLong status);
    }

    public static native NativeLong SetProductFile(String filePath);

    public static native NativeLong SetProductFile(WString filePath);

    public static native NativeLong SetProductData(String productData);

    public static native NativeLong SetProductData(WString productData);

    public static native NativeLong SetProductId(String productId, NativeLong flags);

    public static native NativeLong SetProductId(WString productId, NativeLong flags);

//    public static native NativeLong SetDataDirectory(String directoryPath);
//    
//    public static native NativeLong SetDataDirectory(WString directoryPath);

    public static native NativeLong SetCustomDeviceFingerprint(String fingerprint);

    public static native NativeLong SetCustomDeviceFingerprint(WString fingerprint);

    public static native NativeLong SetLicenseKey(String licenseKey);

    public static native NativeLong SetLicenseKey(WString licenseKey);

    public static native NativeLong SetLicenseUserCredential(String email, String password);

    public static native NativeLong SetLicenseUserCredential(WString email, WString password);

    public static native NativeLong SetLicenseCallback(CallbackType callback);

    public static native NativeLong SetActivationMetadata(String key, String value);

    public static native NativeLong SetActivationMetadata(WString key, WString value);

    public static native NativeLong SetTrialActivationMetadata(String key, String value);

    public static native NativeLong SetTrialActivationMetadata(WString key, WString value);

    public static native NativeLong SetAppVersion(String appVersion);

    public static native NativeLong SetAppVersion(WString appVersion);

    public static native NativeLong SetOfflineActivationRequestMeterAttributeUses(String name, NativeLong uses);

    public static native NativeLong SetOfflineActivationRequestMeterAttributeUses(WString name, NativeLong uses);

    public static native NativeLong SetNetworkProxy(String proxy);

    public static native NativeLong SetNetworkProxy(WString proxy);

    public static native NativeLong SetCryptlexHost(String host);

    public static native NativeLong SetCryptlexHost(WString host);

    public static native NativeLong GetProductMetadata(String key, ByteBuffer value, NativeLong length);

    public static native NativeLong GetProductMetadata(WString key, CharBuffer value, NativeLong length);

    public static native NativeLong GetLicenseMetadata(String key, ByteBuffer value, NativeLong length);

    public static native NativeLong GetLicenseMetadata(WString key, CharBuffer value, NativeLong length);

    public static native NativeLong GetLicenseMeterAttribute(String name, NativeLongByReference allowedUses, NativeLongByReference totalUses, NativeLongByReference grossUses);

    public static native NativeLong GetLicenseMeterAttribute(WString name, NativeLongByReference allowedUses, NativeLongByReference totalUses, NativeLongByReference grossUses);

    public static native NativeLong GetLicenseKey(ByteBuffer licenseKey, NativeLong length);

    public static native NativeLong GetLicenseKey(CharBuffer licenseKey, NativeLong length);

    public static native NativeLong GetLicenseExpiryDate(NativeLongByReference expiryDate);

    public static native NativeLong GetLicenseAllowedActivations(NativeLongByReference allowedActivations);

    public static native NativeLong GetLicenseTotalActivations(NativeLongByReference totalActivations);

    public static native NativeLong GetLicenseUserEmail(ByteBuffer email, NativeLong length);

    public static native NativeLong GetLicenseUserEmail(CharBuffer email, NativeLong length);

    public static native NativeLong GetLicenseUserName(ByteBuffer name, NativeLong length);

    public static native NativeLong GetLicenseUserName(CharBuffer name, NativeLong length);

    public static native NativeLong GetLicenseUserCompany(ByteBuffer company, NativeLong length);

    public static native NativeLong GetLicenseUserCompany(CharBuffer company, NativeLong length);

    public static native NativeLong GetLicenseUserMetadata(String key, ByteBuffer value, NativeLong length);

    public static native NativeLong GetLicenseUserMetadata(WString key, CharBuffer value, NativeLong length);

    public static native NativeLong GetLicenseType(ByteBuffer licenseType, NativeLong length);

    public static native NativeLong GetLicenseType(CharBuffer licenseType, NativeLong length);

    public static native NativeLong GetActivationMetadata(String key, ByteBuffer value, NativeLong length);

    public static native NativeLong GetActivationMeterAttributeUses(String name, NativeLongByReference uses);

    public static native NativeLong GetActivationMeterAttributeUses(WString name, NativeLongByReference uses);

    public static native NativeLong GetActivationMetadata(WString key, CharBuffer value, NativeLong length);

    public static native NativeLong GetServerSyncGracePeriodExpiryDate(NativeLongByReference expiryDate);

    public static native NativeLong GetTrialActivationMetadata(String key, ByteBuffer value, NativeLong length);

    public static native NativeLong GetTrialActivationMetadata(WString key, CharBuffer value, NativeLong length);

    public static native NativeLong GetTrialExpiryDate(NativeLongByReference trialExpiryDate);

    public static native NativeLong GetTrialId(ByteBuffer trialId, NativeLong length);

    public static native NativeLong GetTrialId(CharBuffer trialId, NativeLong length);

    public static native NativeLong GetLocalTrialExpiryDate(NativeLongByReference trialExpiryDate);

    public static native NativeLong GetLibraryVersion(ByteBuffer libraryVersion, NativeLong length);

    public static native NativeLong GetLibraryVersion(CharBuffer libraryVersion, NativeLong length);

    public static native NativeLong CheckForReleaseUpdate(String platform, String version, String channel, CallbackType callback);

    public static native NativeLong CheckForReleaseUpdate(WString platform, WString version, WString channel, CallbackType callback);

    public static native NativeLong ActivateLicense();

    public static native NativeLong ActivateLicenseOffline(String filePath);

    public static native NativeLong ActivateLicenseOffline(WString filePath);

    public static native NativeLong GenerateOfflineActivationRequest(String filePath);

    public static native NativeLong GenerateOfflineActivationRequest(WString filePath);

    public static native NativeLong DeactivateLicense();

    public static native NativeLong GenerateOfflineDeactivationRequest(String filePath);

    public static native NativeLong GenerateOfflineDeactivationRequest(WString filePath);

    public static native NativeLong IsLicenseGenuine();

    public static native NativeLong IsLicenseValid();

    public static native NativeLong ActivateTrial();

    public static native NativeLong ActivateTrialOffline(String filePath);

    public static native NativeLong ActivateTrialOffline(WString filePath);

    public static native NativeLong GenerateOfflineTrialActivationRequest(String filePath);

    public static native NativeLong GenerateOfflineTrialActivationRequest(WString filePath);

    public static native NativeLong IsTrialGenuine();

    public static native NativeLong ActivateLocalTrial(NativeLong trialLength);

    public static native NativeLong IsLocalTrialGenuine();

    public static native NativeLong ExtendLocalTrial(NativeLong trialExtensionLength);

    public static native NativeLong IncrementActivationMeterAttributeUses(String name, NativeLong increment);

    public static native NativeLong IncrementActivationMeterAttributeUses(WString name, NativeLong increment);

    public static native NativeLong DecrementActivationMeterAttributeUses(String name, NativeLong decrement);

    public static native NativeLong DecrementActivationMeterAttributeUses(WString name, NativeLong decrement);

    public static native NativeLong ResetActivationMeterAttributeUses(String name);

    public static native NativeLong ResetActivationMeterAttributeUses(WString name);

    public static native NativeLong Reset();
}
