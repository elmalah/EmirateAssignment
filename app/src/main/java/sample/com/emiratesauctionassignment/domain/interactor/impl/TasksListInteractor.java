package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.TasksModel;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;
import sample.com.emiratesauctionassignment.presentation.utils.Constants;

/**
 * Created by elmalah on 3/5/2017.
 */

public class TasksListInteractor extends UseCase implements ResponseCallback {


    MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Tasks tasks;
    String Accessoken;
    String lang;
    String userId;
    int pageNumber;
    boolean isHistory;

    public TasksListInteractor(Retrofit retrofit,String userId,int pageNumber, String Accessoken, String lang,boolean isHistory, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.Accessoken = Accessoken;
        this.lang = lang;
        this.userId = userId;
        this.pageNumber = pageNumber;
        this.isHistory = isHistory;

        tasks = retrofit.create(WSController.Tasks.class);
        this.execute(new LoginSubscriber());

    }


    @Override
    protected Observable buildUseCaseObservable() {
        if (isHistory)
        return this.tasks.getTasksHistoryList(userId,pageNumber,Accessoken,lang.equalsIgnoreCase(Constants.ARABIC)?"ara":"en").timeout(10, TimeUnit.SECONDS);
        else
        return this.tasks.getTasksList(userId,pageNumber,Accessoken,lang.equalsIgnoreCase(Constants.ARABIC)?"ara":"en").timeout(10, TimeUnit.SECONDS);
    }

    @RxLogSubscriber
    private final class LoginSubscriber extends DefaultSubscriber<TasksModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(TasksModel tasksModel) {
            mCallback.success(tasksModel);
        }
    }
}
