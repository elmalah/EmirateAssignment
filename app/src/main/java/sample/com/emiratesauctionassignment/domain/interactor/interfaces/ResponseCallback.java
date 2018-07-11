package sample.com.emiratesauctionassignment.domain.interactor.interfaces;

/**
 * Created by elmalah on 3/5/2017.
 */

public interface ResponseCallback {
    void unsubscribe();

    interface MYCallback {
        void success(Object data);

        void error(Throwable t);
    }
}
