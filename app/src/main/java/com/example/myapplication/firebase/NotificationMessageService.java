package com.example.myapplication.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.myapplication.R;
import com.example.myapplication.model.request.UserNotifyRequest;
import com.example.myapplication.util.LocalData;
import com.example.myapplication.viewModel.repositories.UserRepository;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.jetbrains.annotations.NotNull;

public class NotificationMessageService extends FirebaseMessagingService {

    private final String CHANNEL_ID = "SYSTECHCHANNEL";

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage message) {
        super.onMessageReceived(message);

        var data = message.getData();
        NotificationManager gerNotify  =  (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        createChannel(gerNotify);

        var notify = new NotificationCompat
                .Builder(this, "app")
                .setContentText("notify")
                .setContentText("mensagem recebida")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();


        gerNotify.notify(1, notify);


                //.setSmallIcon()


    }


    void createChannel(NotificationManager gerNotify){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var name = getString(R.string.channel_name);
            var description = getString(R.string.channel_desc);
            var importance = NotificationManager.IMPORTANCE_DEFAULT;
            var channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            gerNotify.createNotificationChannel(channel);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("SYSTECH", "onNewToken: " + token);
        this.save(token);
    }



    private void save(String token){
        var context = getBaseContext();
        var obj = new UserNotifyRequest(0, token);
        new LocalData(context).setTokenFireBase(obj);
    }
}
