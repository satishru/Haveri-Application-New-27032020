package application.haveri.tourism.notifications;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class AppFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NotNull String token) {
        Timber.e("Refreshed token   %s", token);
        //sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
    }
}
