package application.haveri.tourism.data.model.api.request;

import android.os.Build;

class DeviceInfo {
    private String device_version_name = Build.VERSION.RELEASE; // Version String.
    private int device_sdk_int = Build.VERSION.SDK_INT;// API Level.
    private String device_model = Build.MODEL;// Device Model.
    private String device_manufacturer = Build.MANUFACTURER;// Device manufacturer.
}
