package sample.com.emiratesauctionassignment.presentation.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import sample.com.emiratesauctionassignment.EmiAuctionApp;
import sample.com.emiratesauctionassignment.R;
import sample.com.emiratesauctionassignment.domain.models.EntityModel;
import sample.com.emiratesauctionassignment.domain.models.LoginModel;
import sample.com.emiratesauctionassignment.domain.models.PostResponseModel;
import sample.com.emiratesauctionassignment.domain.models.TrackParam;
import sample.com.emiratesauctionassignment.presentation.presenter.impl.AuctionsListPresenter;
import sample.com.emiratesauctionassignment.presentation.presenter.impl.TasksListPresenter;
import sample.com.emiratesauctionassignment.presentation.presenter.impl.TrackPresenter;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;
import sample.com.emiratesauctionassignment.presentation.ui.adapters.CustomAdapter;
import sample.com.emiratesauctionassignment.presentation.utils.Constants;


public class MainActivity extends BaseActivity
        implements  MainPresenter.PresenterCallBack {


//    @BindView(R.id.toolbar)
//    public Toolbar toolbar;
//    @BindView(R.id.fragment_title_txt)
//    public TextView fragmentTitleTxt;
//
//    @BindView(R.id.setting_icon)
//    public ImageView setting_icon;
//
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawer;
//    @BindView(R.id.nav_view)
//    NavigationView navigationView;
//    ActionBarDrawerToggle toggle;

    LoginModel.LoginObj loginObject;
    @Inject
    SharedPreferences sharedPreferences;
    //NotificationReceivedHandler notificationReceivedHandler=new NotificationReceivedHandler();
    @Inject
    Retrofit retrofit;
    MainPresenter mainPresenter;

@BindView(R.id.simpleSwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

@BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 8", "Person 9", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        EmiAuctionApp app = (EmiAuctionApp) getApplication();
        app.getApiComponent().inject(this);
        app.setMainActivity(this);


//        new LanguageUtils(MainActivity.this).setLanguage(true, false);
//        String lang = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.LANGUAGE, Constants.ENGLISH);
//        Locale newLocale = new Locale(lang);
//        Locale.setDefault(newLocale);
//
//        Configuration config = new Configuration();
//        config.locale = newLocale;
//
//        final Resources res = getResources();
//        res.updateConfiguration(config, res.getDisplayMetrics());
//        setLocale(this);
        /*************************************************************/

//        setSupportActionBar(toolbar);
//        setUpNavigationDrawer();
//
//        loginObject = retrieveLoginUser();
//        hideItem();
//        this.onActionBar();
//        setProfile();
//
//        setUpMainFragmentView(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                shuffleItems();
            }
        });

        connectToGetAuctions();
    }

    public void shuffleItems() {
        // shuffle the ArrayList's items and set the adapter
        Collections.shuffle(personNames, new Random(System.currentTimeMillis()));
//        Collections.shuffle(personImages, new Random(System.currentTimeMillis()));
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, personNames);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
    public static void setLocale(Context context) {
        final String lang = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.LANGUAGE, Constants.ENGLISH);
        final Locale newLocale = new Locale(lang);
        Locale.setDefault(newLocale);
        final Configuration config = new Configuration();
        config.locale = newLocale;

        final Resources res = context.getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        String lang = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.LANGUAGE, Constants.ENGLISH);
        Locale newLocale = new Locale(lang);
        Locale.setDefault(newLocale);

        Configuration config = new Configuration();
        config.locale = newLocale;

        final Resources res = getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Register this fragment to listen to event.
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
//        GlobalBus.getBus().register(this);
    }


    @Override
    public void onBackPressed() {

    }

    @Subscribe
    public void getTrack(TrackParam trackParam) {

        ArrayList<TrackParam> trackArray = new ArrayList<>();
        trackArray.add(trackParam);
        //loginObject = retrieveLoginUser();
        if (loginObject.track) {
//            Toast.makeText(getApplicationContext(), " tracked", Toast.LENGTH_SHORT).show();
            mainPresenter = new TrackPresenter(retrofit, trackArray, sharedPreferences.getString(Constants.LANGUAGE, Constants.ARABIC), MainActivity.this);
        } else {
//            Toast.makeText(getApplicationContext(), "not tracked",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void getChangeIsTracked(boolean isTracked) {
//        Toast.makeText(getApplicationContext(), isTracked + "",
//                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showConnectionError(Throwable throwable) {

    }
    void connectToGetAuctions() {
       // progress_bar.setVisibility(View.VISIBLE);
//        mainPresenter = new TasksListPresenter(retrofit,pageNumber,"824c72a3-0817-44b0-820d-963848095d2d", sharedPreferences.getString(Constants.LANGUAGE, Constants.ARABIC).equalsIgnoreCase(Constants.ARABIC) ? Constants.ARABIC : Constants.ENGLISH,false, TasksListFragment.this);
        mainPresenter = new AuctionsListPresenter(retrofit, MainActivity.this);
    }
    @Override
    public void updateView(Object object) {
        if (object != null && object instanceof EntityModel) {
            Toast.makeText(MainActivity.this,"Success", Toast.LENGTH_LONG).show();
//            if (((PostResponseModel) object).messages.code == 0) {
////                Toast.makeText(MainActivity.this,((PostResponseModel) object).messages.message.length()>0?((PostResponseModel) object).messages.message:"Check your Email for Reset", Toast.LENGTH_LONG).show();
////                sharedPreferences.edit().putString(Constants.LOGIN_USER, "-1").commit();
////                sharedPreferences.edit().putString(Constants.LOGIN_USER_ID, "-1").commit();
////                startActivity(new Intent(MainActivity.this, LoginActivity.class));
////                MainActivity.this.finish();
//            } else {
////                Toast.makeText(MainActivity.this,((PostResponseModel) object).messages.message.length()>0?((PostResponseModel) object).messages.message:"Reset Password Failed", Toast.LENGTH_LONG).show();
//
//            }
        }else{
            Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_LONG).show();
        }
    }

    boolean checkCurrentDateToAttend(String startDateString,String endDateString){
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            Date currentDate = getCurrentDate();
//            Date currentDate = sdf.parse("2015-08-12");

            if (currentDate.compareTo(startDate) >= 0) {
                if (currentDate.compareTo(endDate) <= 0) {
                    System.out.println("currentDate is in between startDate and endDate");
                    return true;
                } else {
                    System.out.println("currentDate is NOT in between startDate and endDate");
                    return false;
                }
            } else {
                System.out.println("currentDate is NOT in between startDate and endDate");
                return false;
            }

        } catch (ParseException pe) {
            pe.printStackTrace();
            return false;
        }
    }

    Date getCurrentDate(){
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);
        try {
            return formatter.parse(todayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
