package com.adl.firebaseauthentication.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.drawable.Drawable
import android.hardware.camera2.params.RggbChannelVector.RED
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.adl.firebaseauthentication.PromotionPage
import com.adl.firebaseauthentication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.atomic.AtomicInteger

class MessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Firebase","my newTOken "+ token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("Firebase","onMessageReceived "+ message.from + "\n data" + message.data)

        sendNotification(this@MessagingService, message)

    }

    private fun sendNotification(context : Context, message: RemoteMessage){
        val intent = Intent(applicationContext, PromotionPage::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val extras = Bundle()
        extras.putString("title",message.notification?.title)
        extras.putString("url",message.data.get("pageUrl"))
        intent.putExtras(extras)

        val pendingIntent:PendingIntent = PendingIntent.getActivity(applicationContext,NotificationID.id,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(context.applicationContext,"ADL FIREBASE")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setLights(Color.RED,500,500)
            .setVibrate(longArrayOf(500,500,500))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)

        val notificationManager = NotificationManagerCompat.from(context)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var channel = NotificationChannel("ADL FIREBASE","pesam", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

        }

        Glide.with(applicationContext)
            .asBitmap()
            .load(message.notification?.imageUrl)
            .into(object:CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    notificationBuilder.setLargeIcon(resource)
                    notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(resource))
                    notificationManager.notify(NotificationID.id,notificationBuilder.build())
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })
    }

}

internal object NotificationID{
    private val c = AtomicInteger(100)
    val id:Int
            get()= c.incrementAndGet()

}