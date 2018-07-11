package sample.com.emiratesauctionassignment.domain.modules;

import javax.inject.Singleton;
import dagger.Component;
import sample.com.emiratesauctionassignment.presentation.ui.activity.MainActivity;
import sample.com.emiratesauctionassignment.presentation.ui.services.TimeService;
import sample.com.emiratesauctionassignment.presentation.utils.LanguageUtils;


@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface ApiComponent {

    void inject(MainActivity mainActivity);

    void inject(LanguageUtils languageUtils);

    void inject(TimeService timeService);


}


