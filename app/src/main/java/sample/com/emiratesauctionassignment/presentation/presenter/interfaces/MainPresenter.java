package sample.com.emiratesauctionassignment.presentation.presenter.interfaces;

/**
 * Created by elmalah on 3/5/2017.
 */

public interface MainPresenter extends LifeCyclePresenter {
    interface PresenterCallBack {
        void showLoading(boolean show);

        void showConnectionError(Throwable throwable);

        void updateView(Object object);

    }
}
