package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.home.list.mapper.FireworkToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {
    private FireworkDisplayDataRepository fireworkRepository;
    private CompositeDisposable compositeDisposable;
    private FireworkToViewModelMapper fireworkToViewModelMapper;
    private MutableLiveData<List<FireworkViewItem>> fireworks = new MutableLiveData<List<FireworkViewItem>>();
    private MutableLiveData<List<FireworkViewItem>> fireworksByCity = new MutableLiveData<List<FireworkViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> postSuccess = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> putSuccess = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> errorConnexion = new MutableLiveData<Boolean>();

    public ListViewModel(FireworkDisplayDataRepository fireworkRepository) {
        this.fireworkRepository = fireworkRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.fireworkToViewModelMapper = new FireworkToViewModelMapper();
        loadFireWorks();
    }

    public MutableLiveData<List<FireworkViewItem>> getFireworks() {
        return fireworks;
    }
    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }
    public MutableLiveData<Boolean> getPostSuccess() {
        return postSuccess;
    }
    public MutableLiveData<Boolean> getPutSuccess() {
        return putSuccess;
    }

    public MutableLiveData<Boolean> getErrorConnexion() {
        return errorConnexion;
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
                        errorConnexion.setValue(false);
                        fireworks.setValue(fireworkToViewModelMapper.map(fireworkModels));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isDataLoading.setValue(false);
                        errorConnexion.setValue(true);
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                        errorConnexion.setValue(false);
                        isDataLoading.setValue(false);
                    }
                }));
                    
    }


    public void loadFireWorksFuture() {
        isDataLoading.setValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(fireworkRepository.getFireworksFuture()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<FireworkModel>>() {
                    @Override
                    public void onNext(List<FireworkModel> fireworkModels) {
                        isDataLoading.setValue(false);
                        errorConnexion.setValue(false);
                        fireworks.setValue(fireworkToViewModelMapper.map(fireworkModels));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isDataLoading.setValue(false);
                        errorConnexion.setValue(true);
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                        errorConnexion.setValue(false);
                        isDataLoading.setValue(false);
                    }
                }));

    }

    

    public void getAllFireworksByCity(String city) {
        isDataLoading.setValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(fireworkRepository.getAllFireworksByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<FireworkModel>>() {
                    @Override
                    public void onNext(List<FireworkModel> fireworkModels) {
                        isDataLoading.setValue(false);
                        errorConnexion.setValue(false);
                        fireworksByCity.setValue(fireworkToViewModelMapper.map(fireworkModels));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isDataLoading.setValue(false);
                        errorConnexion.setValue(true);
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                        errorConnexion.setValue(false);
                        isDataLoading.setValue(false);
                    }
                }));

    }



    public void addFirework(FireworkModel firework) {
        Call<FireworkModel> call = this.fireworkRepository.addFirework(firework);
        call.enqueue(new Callback<FireworkModel>() {
            @Override
            public void onResponse(Call<FireworkModel> call, Response<FireworkModel> response) {
                postSuccess.setValue(true);
            }

            @Override
            public void onFailure(Call<FireworkModel> call, Throwable t) {
                // DO NOTHING
            }

        });
    }

    public void updateFirework(int id, int price, boolean accessHandicap, String duration, String crowed) {
        Call<FireworkModel> call = this.fireworkRepository.updateFirework(id, price, accessHandicap, duration, crowed);
        call.enqueue(new Callback<FireworkModel>() {
            @Override
            public void onResponse(Call<FireworkModel> call, Response<FireworkModel> response) {
                postSuccess.setValue(true);
            }

            @Override
            public void onFailure(Call<FireworkModel> call, Throwable t) {
                // DO NOTHING
            }
        });
    }


    public void addParking(int id, String name, int price) {
        Call<FireworkModel> call = this.fireworkRepository.addParking(id, name, price);
        call.enqueue(new Callback<FireworkModel>() {
            @Override
            public void onResponse(Call<FireworkModel> call, Response<FireworkModel> response) {
                postSuccess.setValue(true);
            }

            @Override
            public void onFailure(Call<FireworkModel> call, Throwable t) {
                // DO NOTHING
            }
        });
    }




}
