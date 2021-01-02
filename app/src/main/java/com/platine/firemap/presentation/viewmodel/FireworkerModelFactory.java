package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.home.list.mapper.FireworkToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FireworkerModelFactory extends ViewModel {
    private FireworkDisplayDataRepository fireworkRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<FireworkerDetail>> fireworkers = new MutableLiveData<List<FireworkerDetail>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> postSuccess = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> putSuccess = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> errorConnexion = new MutableLiveData<Boolean>();

    public FireworkerModelFactory (FireworkDisplayDataRepository fireworkRepository) {
        this.fireworkRepository = fireworkRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<FireworkerDetail>> getFireworkers() {
        isDataLoading.setValue(true);
        if (fireworkers == null) {
            fireworkers = new MutableLiveData<List<FireworkerDetail>>();
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
}

