package me.songning.mvp.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nicholas on 2016/10/30.
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;

    private CompositeSubscription mCompositeSubscription; //对RxJava进行管理
    
    // unSubscribe方法对View进行null赋值和清除Rx的Subscription（订阅），防止内存泄漏

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

}
