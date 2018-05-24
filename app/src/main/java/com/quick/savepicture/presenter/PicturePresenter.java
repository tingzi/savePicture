package com.quick.savepicture.presenter;

import com.orhanobut.logger.Logger;
import com.quick.savepicture.bean.Picture;
import com.quick.savepicture.contract.PictureContract;
import com.quick.savepicture.model.PictureModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PicturePresenter implements PictureContract.Presenter {
    private PictureContract.Model model;
    private CompositeDisposable compositeDisposable;

    public PicturePresenter() {
        this.model = new PictureModel();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void load(PictureContract.View view) {
        if (null == view) {
            return;
        }

        List<Picture> pictures = model.getPhotos();
        Logger.d(pictures);

        Disposable disposable = Completable.fromAction(() -> model.savePhotos(pictures))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.show(pictures));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
