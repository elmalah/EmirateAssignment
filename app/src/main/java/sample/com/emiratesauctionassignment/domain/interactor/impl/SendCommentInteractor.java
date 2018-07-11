package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.CommentParam;
import sample.com.emiratesauctionassignment.domain.models.PostResponseModel;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;

/**
 * Created by elmalah on 3/5/2017.
 */

public class SendCommentInteractor extends UseCase implements ResponseCallback {


    ResponseCallback.MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Tasks tasks;
    CommentParam commentParam;
    String lang;

    public SendCommentInteractor(Retrofit retrofit, CommentParam commentParam, String lang, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;
        this.commentParam = commentParam;
        this.lang = lang;

        tasks = retrofit.create(WSController.Tasks.class);
        this.execute(new LoginSubscriber());

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.tasks.sendComment(commentParam).timeout(10, TimeUnit.SECONDS);
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
