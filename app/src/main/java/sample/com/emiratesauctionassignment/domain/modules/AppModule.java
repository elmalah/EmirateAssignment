package sample.com.emiratesauctionassignment.domain.modules;

import android.app.Application;

import sample.com.emiratesauctionassignment.EmiAuctionApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amr elmalah on 5/4/16.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(EmiAuctionApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
