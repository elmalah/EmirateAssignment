package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.DeliverTaskParam;
import sample.com.emiratesauctionassignment.domain.models.PostResponseModel;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;


/**
 * Created by elmalah on 3/5/2017.
 */

public class DeliverTaskInteractor extends UseCase implements ResponseCallback {


    MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Tasks tasks;
    DeliverTaskParam deliverTaskParam;
    String lang;
    String userId;
    String taskId;

    public DeliverTaskInteractor(Retrofit retrofit,String userId,String taskId, DeliverTaskParam deliverTaskParam, String lang, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.deliverTaskParam = deliverTaskParam;
        this.lang = lang;
        this.userId = userId;
        this.taskId = taskId;

        tasks = retrofit.create(WSController.Tasks.class);
        this.execute(new LoginSubscriber());

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.tasks.deliverTask(deliverTaskParam).timeout(10, TimeUnit.SECONDS);
//        return this.tasks.deliverTask(userId,taskId,deliverTaskParam).timeout(10, TimeUnit.SECONDS);
    }

    @RxLogSubscriber
    private final class LoginSubscriber extends DefaultSubscriber<PostResponseModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(PostResponseModel postResponseModel) {
            mCallback.success(postResponseModel);
        }
    }
}
