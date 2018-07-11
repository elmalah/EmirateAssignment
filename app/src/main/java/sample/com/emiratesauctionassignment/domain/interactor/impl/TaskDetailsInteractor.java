package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.TaskDetailsModel;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;
import sample.com.emiratesauctionassignment.presentation.utils.Constants;


/**
 * Created by elmalah on 3/5/2017.
 */

public class TaskDetailsInteractor extends UseCase implements ResponseCallback {


    MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Tasks tasks;
    String Accessoken;
    String lang;
    int id;

    public TaskDetailsInteractor(Retrofit retrofit,int id, String Accessoken, String lang, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.Accessoken = Accessoken;
        this.id = id;
        this.lang = lang;

        tasks = retrofit.create(WSController.Tasks.class);
        this.execute(new LoginSubscriber());

    }


    @Override
    protected Observable buildUseCaseObservable() {
        return this.tasks.getTaskDetails(id,Accessoken,lang.equalsIgnoreCase(Constants.ARABIC)?"ara":"en").timeout(10, TimeUnit.SECONDS);
    }

    @RxLogSubscriber
    private final class LoginSubscriber extends DefaultSubscriber<TaskDetailsModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(TaskDetailsModel taskDetailsModel) {
            mCallback.success(taskDetailsModel);
        }
    }
}
