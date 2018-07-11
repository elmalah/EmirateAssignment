package sample.com.emiratesauctionassignment.presentation.presenter.impl;


import retrofit2.Retrofit;
import sample.com.emiratesauctionassignment.domain.interactor.impl.TasksListInteractor;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;

/**
 * Created by elmalah on 3/5/2017.
 */

public class TasksListPresenter implements MainPresenter, ResponseCallback.MYCallback {
    PresenterCallBack presenterCallBack;
    ResponseCallback responseCallback;

    public TasksListPresenter(Retrofit retrofit,String userId,int pageNumber, String accessToken, String lang,boolean isHistory, PresenterCallBack presenterCallBack) {
        this.presenterCallBack = presenterCallBack;
        this.responseCallback = new TasksListInteractor(retrofit,userId,pageNumber, accessToken, lang,isHistory, this, presenterCallBack);
    }

    @Override
    public void success(Object data) {
        presenterCallBack.updateView(data);
    }

    @Override
    public void error(Throwable t) {
        presenterCallBack.showConnectionError(t);
    }

    /*
    this is the LifeCyclePresenter Methods
     */
    @Override
    public void onStart() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        responseCallback.unsubscribe();
    }
}
