package sample.com.emiratesauctionassignment.presentation.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by elmalah on 6/28/2017.
 */

public class LanguageSupport {
    public static void changeLocale(Resources res, String locale) {

        Configuration config;
        config = new Configuration(res.getConfiguration());


        switch (locale) {

            case "AR":
                config.locale = new Locale("ar");
                break;
            case "EN":
                config.locale = Locale.ENGLISH;
                break;
            default:
                config.locale = Locale.ENGLISH;
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        // reload files from assets directory
    }
}
