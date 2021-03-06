package sample.com.emiratesauctionassignment.domain.interactor.impl;


import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import sample.com.emiratesauctionassignment.domain.controller.WSController;
import sample.com.emiratesauctionassignment.domain.interactor.interfaces.ResponseCallback;
import sample.com.emiratesauctionassignment.domain.models.EntityModel;
import sample.com.emiratesauctionassignment.domain.models.TasksModel;
import sample.com.emiratesauctionassignment.presentation.presenter.interfaces.MainPresenter;
import sample.com.emiratesauctionassignment.presentation.utils.Constants;

/**
 * Created by elmalah on 3/5/2017.
 */

public class AuctionListInteractor extends UseCase implements ResponseCallback {


    MYCallback mCallback;
    MainPresenter.PresenterCallBack presenterCallBack;
    WSController.Auctions auctions;


    public AuctionListInteractor(Retrofit retrofit, MYCallback mCallback, MainPresenter.PresenterCallBack presenterCallBack) {
        this.mCallback = mCallback;
        this.presenterCallBack = presenterCallBack;


        auctions = retrofit.create(WSController.Auctions.class);
        this.execute(new AuctionSubscriber());

    }


    @Override
    protected Observable buildUseCaseObservable() {

        return this.auctions.getAuctionsList().timeout(10, TimeUnit.SECONDS);
    }

    @RxLogSubscriber
    private final class AuctionSubscriber extends DefaultSubscriber<EntityModel> {

        @Override
        public void onCompleted() {
            presenterCallBack.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            mCallback.error(e);
        }

        @Override
        public void onNext(EntityModel entityModel) {
            mCallback.success(entityModel);
        }
    }
}
