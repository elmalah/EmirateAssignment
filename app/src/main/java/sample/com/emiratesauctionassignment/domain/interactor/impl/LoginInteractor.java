package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.LoginModel;
import sample.com.emiratesauctionassignment.domain.models.LoginParam;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;

/**
 * Created by elmalah on 3/5/2017.
 */

public class LoginInteractor extends UseCase implements ResponseCallback {


    ResponseCallback.MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Login login;
    LoginParam loginParam;
    String lang;

    public LoginInteractor(Retrofit retrofit, LoginParam loginParam, String lang, ResponseCallback.MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.loginParam = loginParam;
        this.lang = lang;

        login = retrofit.create(WSController.Login.class);
        this.execute(new LoginSubscriber());

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.login.getLoginInfo(loginParam).timeout(10, TimeUnit.SECONDS);
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
