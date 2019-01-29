package com.cumulations.alltogetherapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.cumulations.alltogetherapp.model.Record;
import com.cumulations.alltogetherapp.model.User;
import com.cumulations.alltogetherapp.remote.APIService;
import com.cumulations.alltogetherapp.remote.RetroClass;
import com.cumulations.alltogetherapp.room.UserDao;
import com.cumulations.alltogetherapp.room.UserDb;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private Context context;
    private UserDb userDb;
    private LiveData<List<Record>> listLiveData = new MutableLiveData<>();

    public UserRepository(Context context) {
        userDb = Room.databaseBuilder(context, UserDb.class, "userDb").build();
        this.context = context;
    }

    public void getUserList() {
        APIService apiService = RetroClass.getAPIService();
        Call<User> userCall = apiService.getUserList();
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, final Response<User> response) {

                Log.e("@@@", "onResponse");

                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        List<Record> records = new ArrayList<Record>();
                        for (int i = 0; i <= response.body().getRecords().size(); i++) {
                            Record record = new Record();
                            record.setFirstName(response.body().getRecords().get(i).getFirstName());
                            record.setLastName(response.body().getRecords().get(i).getLastName());
                            record.setAge(response.body().getRecords().get(i).getAge());
                            record.setId(response.body().getRecords().get(i).getId());
                            records.add(record);
                        }
                        if (userDb.userDao().getAll().getValue().isEmpty()) {
                            for (int i = 0; i < records.size(); i++) {
                                userDb.userDao().insertAll4theFirstTime(records.get(i));
                            }
                        } else {
                            for (int i = 0; i < records.size(); i++) {
                                userDb.userDao().updateRecord(records.get(i));
                            }
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("@@@", "onError");
                    }
                });


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    public LiveData<List<Record>> getListLiveData() {
        listLiveData = userDb.userDao().getAll();
        return listLiveData;
    }
}
