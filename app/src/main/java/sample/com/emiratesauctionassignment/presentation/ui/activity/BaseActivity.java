package sample.com.emiratesauctionassignment.presentation.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import sample.com.emiratesauctionassignment.presentation.utils.LanguageHelper;


/**
 * Created by elmalah on 1/1/2018.
 */

public class BaseActivity extends AppCompatActivity {
    // override the base context of application to update default locale for this activity
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base));
    }
}
