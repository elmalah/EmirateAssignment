package sample.com.emiratesauctionassignment.domain.interactor.impl;

/**
 * Created by elmalah on 3/5/2017.
 */

public class DefaultSubscriber<T> extends rx.Subscriber<T> {
    @Override
    public void onCompleted() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onNext(T t) {
        // no-op by default.
    }
}
