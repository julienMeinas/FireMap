package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkViewModel;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.mapper.FireworkToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FireworkListViewModel extends ViewModel {
    private FireworkDisplayDataRepository fireworkRepository;
    private CompositeDisposable compositeDisposable;
    private FireworkToViewModelMapper fireworkToViewModelMapper;
    private MutableLiveData<List<FireworkViewModel>> fireworks = new MutableLiveData<List<FireworkViewModel>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public FireworkListViewModel(FireworkDisplayDataRepository fireworkRepository) {
        this.fireworkRepository = fireworkRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.fireworkToViewModelMapper = new FireworkToViewModelMapper();
        loadFireWorks();
    }

    public MutableLiveData<List<FireworkViewModel>> getFireworks() {
        return fireworks;
    }
    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void loadFireWorks() {
        isDataLoading.setValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(fireworkRepository.getFireworks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<FireworkModel>>() {
                    @Override
                    public void onNext(List<FireworkModel> fireworkModels) {
                        isDataLoading.setValue(false);
                        fireworks.setValue(fireworkToViewModelMapper.map(fireworkModels));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                        isDataLoading.setValue(false);
                    }
                }));
                    
    }
}
