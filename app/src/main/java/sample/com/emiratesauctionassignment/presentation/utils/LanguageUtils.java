package sample.com.emiratesauctionassignment.presentation.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;


import java.util.Locale;

import javax.inject.Inject;

import sample.com.emiratesauctionassignment.EmiAuctionApp;

/**
 * Created by elmalah on 8/14/16.
 */
public class LanguageUtils
{
    @Inject
    SharedPreferences sharedPreferences;

    AppCompatActivity activity;

    public LanguageUtils(AppCompatActivity activity)
    {
        this.activity = activity;

        EmiAuctionApp app = (EmiAuctionApp) activity.getApplication();
        app.getApiComponent().inject(this);
    }

    public void setLanguage(boolean updatelocale, boolean restartActivity)
    {
        String storedLang = sharedPreferences.getString(Constants.LANGUAGE, "");
        String deviceLang = Locale.getDefault().getLanguage().toUpperCase();

        if(storedLang.equals(""))
        {
            if(deviceLang.equals(Constants.ENGLISH) || deviceLang.equals(Constants.ARABIC_LOCAL))
            {
                sharedPreferences.edit().putString(Constants.LANGUAGE, deviceLang).commit();
                if(updatelocale)
                {
                    updateLocale(deviceLang, restartActivity);
                }
            }
            else
            {
                String deviceCountry = Locale.getDefault().getISO3Country().toUpperCase();

                if(deviceCountry.equals(Constants.ARABIC_LOCAL) )
                {
                    sharedPreferences.edit().putString(Constants.LANGUAGE, Constants.ARABIC_LOCAL).commit();
                    if(updatelocale)
                    {
                        updateLocale(Constants.ARABIC_LOCAL, restartActivity);
                    }
                }
                else
                {
                    sharedPreferences.edit().putString(Constants.LANGUAGE, Constants.ENGLISH).commit();
                    if(updatelocale)
                    {
                        updateLocale(Constants.ENGLISH, restartActivity);
                    }
                }
            }
        }
        else
        {
            if(storedLang.equals(Constants.ARABIC) )
            {
                // no need to re-save stored language
                if(updatelocale) {
                    updateLocale(storedLang, restartActivity);
                }
            }
            else
            {
                sharedPreferences.edit().putString(Constants.LANGUAGE, Constants.ENGLISH).commit();
                if(updatelocale)
                {
                    updateLocale(Constants.ENGLISH, restartActivity);
                }
            }
        }
    }

    public void updateLocale(String lang, boolean restartActivity)
    {
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang.toLowerCase());
        res.updateConfiguration(conf, dm);
        if(restartActivity) {
            Intent intent = activity.getIntent();
            activity.finish();
            activity.startActivity(intent);
        }
    }

}
