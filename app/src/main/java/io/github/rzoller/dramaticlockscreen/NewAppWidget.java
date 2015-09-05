package io.github.rzoller.dramaticlockscreen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;


/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static String TAG = "NewAppWidget";

    private static String BUTTON_DRAMA_CLICKED = "BUTTON_DRAMA_CLICKED";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (BUTTON_DRAMA_CLICKED.equals(intent.getAction())) {
            Toast.makeText(context, "DRAMA", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Clicked button: drama");
        }
    }

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        Log.w(TAG, "Init button: drama");

        Intent intent = new Intent(context, DramaService.class);
        PendingIntent serviceIntent = PendingIntent.getService(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.button_drama, serviceIntent);

//        views.setOnClickPendingIntent(R.id.button_drama, createClickIntent(context, BUTTON_DRAMA_CLICKED));

//        final Button button = (Button) context.findViewById(R.id.button_drama);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//            }
//        });

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static PendingIntent createClickIntent(Context context, String action) {
        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}


