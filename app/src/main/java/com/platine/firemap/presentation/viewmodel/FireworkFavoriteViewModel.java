package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.mapper.FireworkEntityToViewModelMapper;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.mapper.FireworkToViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FireworkFavoriteViewModel extends ViewModel {
    private FireworkDisplayDataRepository fireworkDisplayDataRepository;
    private FireworkEntityToViewModelMapper fireworkEntityToViewModelMapper;
    private FireworkToViewModelMapper fireworkToViewModelMapper;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<List<FireworkViewItem>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<FireworkEntity>> fireworkAddedEvent = new MutableLiveData<Event<FireworkEntity>>();
    final MutableLiveData<Event<Integer>> fireworkDeletedEvent = new MutableLiveData<Event<Integer>>();


    public FireworkFavoriteViewModel(FireworkDisplayDataRepository fireworkDisplayDataRepository) {
        this.fireworkDisplayDataRepository = fireworkDisplayDataRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.fireworkEntityToViewModelMapper = new FireworkEntityToViewModelMapper();
        this.fireworkToViewModelMapper = new FireworkToViewModelMapper();
    }

    public MutableLiveData<Event<FireworkEntity>> getBookAddedEvent() {
        return fireworkAddedEvent;
    }

    public MutableLiveData<Event<Integer>> getBookDeletedEvent() {
        return fireworkDeletedEvent;
    }

    public MutableLiveData<List<FireworkViewItem>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<FireworkViewItem>>();
            compositeDisposable.add(fireworkDisplayDataRepository.getFavorites()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<FireworkEntity>>() {

                        @Override
                        public void onNext(List<FireworkEntity> fireworkEntityList) {
                            loadFavorites(fireworkEntityList);
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
                            //Do Nothing
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public void loadFavorites(List<FireworkEntity> fireworkEntities) {
        List<FireworkViewItem> fireworkViewItems = new ArrayList<>();
        for(FireworkEntity fireworkEntity : fireworkEntities) {
            compositeDisposable.add(fireworkDisplayDataRepository.getFireworkById(fireworkEntity.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<FireworkModel>() {

                        @Override
                        public void onSuccess(FireworkModel fireworkModel) {
                            fireworkViewItems.add(fireworkToViewModelMapper.map(fireworkModel));
                        }

                        @Override
                        public void onError(Throwable e) {
                            // handle the error case
                            //Yet, do not do nothing in this app
                        }
                    }));
        }
        favorites.setValue(fireworkViewItems);
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }


    public void addFireworkToFavorite(final FireworkEntity fireworkEntity) {
        compositeDisposable.add(fireworkDisplayDataRepository.addFireworkToFavorites(fireworkEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        fireworkAddedEvent.setValue(new Event<FireworkEntity>(fireworkEntity));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void removeFireworkFromFavorites(final int id) {
        compositeDisposable.add(fireworkDisplayDataRepository.removeFireworkFromFavorites(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        fireworkDeletedEvent.setValue(new Event<Integer>(id));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
