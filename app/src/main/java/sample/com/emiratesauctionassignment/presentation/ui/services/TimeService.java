package sample.com.emiratesauctionassignment.presentation.ui.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;


import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.ButterKnife;
import sample.com.emiratesauctionassignment.EmiAuctionApp;
import sample.com.emiratesauctionassignment.domain.models.TrackParam;
import sample.com.emiratesauctionassignment.presentation.ui.eventbus.GlobalBus;
import sample.com.emiratesauctionassignment.presentation.utils.Constants;


/**
 * Created by elmalah on 8/1/2017.
 */

public class TimeService extends Service {
    // constant
//    public static final long NOTIFY_INTERVAL = 10*1000; // 10 seconds
    public static final long NOTIFY_INTERVAL = 20*60*1000; // 10 seconds

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;
    // GPSTracker class
//    GPSTracker gps;
    double latitude, longitude;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // cancel if already existed
        // Register this fragment to listen to event.
        EmiAuctionApp app = (EmiAuctionApp) getApplication();
        app.getApiComponent().inject(this);
        GlobalBus.getBus().register(this);

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }
    @Subscribe
    public void getTrack(TrackParam trackParam) {

//        Toast.makeText(getApplicationContext(),trackParam.getLat(),
//                Toast.LENGTH_SHORT).show();
    }
    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
//                    Toast.makeText(getApplicationContext(), getDateTime() + " ," + (getLocation() != null ? getLocation().latitude : ""),
//                            Toast.LENGTH_SHORT).show();
//                    if (getLocation() != null)
//                        GlobalBus.getBus().post(new TrackParam(Integer.parseInt(sharedPreferences.getString(Constants.LOGIN_USER_ID, "-1")), getLocation().latitude, getLocation().longitude, getDateTime()));
                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            return sdf.format(new Date());
        }

    }

//    LatLng getLocation() {
//        gps = new GPSTracker(this);
//        LatLng location = null;
//        // check if GPS enabled
//        if (gps.canGetLocation()) {
//
//            latitude = gps.getLatitude();
//            longitude = gps.getLongitude();
//
//            // \n is for new line  lat=30.0816289  lon=31.0182903
//
////            Toast.makeText(this, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//            location = new LatLng(latitude, longitude);
//
//        } else {
//            // can't get location
//            // GPS or Network is not enabled
//            // Ask user to enable GPS/network in settings
////            gps.showSettingsAlert();
//        }
//        return location;
//    }
}