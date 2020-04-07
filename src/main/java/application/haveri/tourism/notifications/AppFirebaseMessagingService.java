package application.haveri.tourism.notifications;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

import application.haveri.tourism.utils.AppLogger;

public class AppFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NotNull String token) {
        AppLogger.e("Refreshed FCM Token   %s", token);
        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
    }
}
