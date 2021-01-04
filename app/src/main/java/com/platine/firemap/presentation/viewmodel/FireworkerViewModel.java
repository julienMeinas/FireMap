package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.home.list.mapper.FireworkToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FireworkerViewModel extends ViewModel {
    private FireworkDisplayDataRepository fireworkRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<FireworkerDetail>> fireworkers = new MutableLiveData<List<FireworkerDetail>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> errorConnexion = new MutableLiveData<Boolean>();
    private MutableLiveData<FireworkerDetail> currentFireworker = new MutableLiveData<FireworkerDetail>();

    public FireworkerViewModel(FireworkDisplayDataRepository fireworkRepository) {
        this.fireworkRepository = fireworkRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<FireworkerDetail>> getFireworkers() {
        isDataLoading.setValue(true);
        if (fireworkers == null) {
            fireworkers = new MutableLiveData<List<FireworkerDetail>>();
            compositeDisposable.clear();
            compositeDisposable.add(fireworkRepository.getFireworkers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<FireworkerDetail>>() {

                        @Override
                        public void onNext(List<FireworkerDetail> fireworerksList) {
                            fireworkers.setValue(fireworerksList);
                            isDataLoading.setValue(false);
                            System.out.println("BIND FAVORITES");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return fireworkers;
    }


    public MutableLiveData<FireworkerDetail> getFireworkerById(int id) {
        isDataLoading.setValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(fireworkRepository.getFireworkerById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<FireworkerDetail>() {

                    @Override
                    public void onSuccess(FireworkerDetail fireworkerDetail) {
                        currentFireworker.setValue(fireworkerDetail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                    }
                }));
        return currentFireworker;
    }
}

