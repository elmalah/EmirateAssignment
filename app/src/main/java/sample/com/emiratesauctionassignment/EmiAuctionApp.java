package sample.com.emiratesauctionassignment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


import sample.com.emiratesauctionassignment.domain.models.LoginModel;
import sample.com.emiratesauctionassignment.domain.modules.ApiComponent;
import sample.com.emiratesauctionassignment.domain.modules.ApiModule;
import sample.com.emiratesauctionassignment.domain.modules.AppModule;
import sample.com.emiratesauctionassignment.domain.modules.DaggerApiComponent;
import sample.com.emiratesauctionassignment.presentation.ui.activity.MainActivity;
import sample.com.emiratesauctionassignment.presentation.utils.LanguageHelper;

/**
 * Created by elmalah on 6/24/2017.
 */

public class EmiAuctionApp extends Application {
    ApiComponent apiComponent;
    MainActivity mainActivity;
    LoginModel.LoginObj loginObject;

//    private static void disableSSLCertificateChecking() {
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[]{};
//            }
//
//            @Override
//            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//            }
//        }};
//
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    if( hostname.equalsIgnoreCase("www.mahami.co"))
//                    return true;
//                    else
//                        return false;
//                }
//            });
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule(getApplicationContext()))
                .appModule(new AppModule(this))
                .build();


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

//        disableSSLCertificateChecking();

    }


    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

//    LoginModel.LoginObj retrieveLoginUser() {
//        Gson gson = new Gson();
//        String json = PreferenceManager.getDefaultSharedPreferences(MahamiApp.this).getString(Constants.LOGIN_USER, "-1");
//        return gson.fromJson(json, LoginModel.LoginObj.class);
//    }

//    void saveLoginUser(LoginModel.LoginObj obj){
//        Gson gson = new Gson();
//        String json = gson.toJson(obj);
//        PreferenceManager.getDefaultSharedPreferences(MahamiApp.this).edit().putString(Constants.LOGIN_USER, json).commit();
//    }


    // override the base context of application to update default locale for the application
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, LanguageHelper.getLanguage(base)));
    }
}
