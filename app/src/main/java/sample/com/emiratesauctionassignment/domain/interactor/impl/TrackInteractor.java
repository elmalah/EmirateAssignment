package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.LoginModel;
import sample.com.emiratesauctionassignment.domain.models.TrackParam;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;

/**
 * Created by elmalah on 3/5/2017.
 */

public class TrackInteractor extends UseCase implements ResponseCallback {


    MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Login login;
    ArrayList<TrackParam> trackParam;
    String lang;

    public TrackInteractor(Retrofit retrofit, ArrayList<TrackParam> trackParam, String lang, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.trackParam = trackParam;
        this.lang = lang;

        login = retrofit.create(WSController.Login.class);
        this.execute(new LoginSubscriber());

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.login.sendTrack(trackParam).timeout(10, TimeUnit.SECONDS);
    }

    @RxLogSubscriber
    private final class LoginSubscriber extends DefaultSubscriber<LoginModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(LoginModel LoginModel) {
            mCallback.success(LoginModel);
        }
    }
}
