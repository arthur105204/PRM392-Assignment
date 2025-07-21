package vn.edu.fpt.koreandictionary.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import vn.edu.fpt.koreandictionary.data.repository.DictionaryRepository;
import vn.edu.fpt.koreandictionary.network.FavoritesApiService;
import vn.edu.fpt.koreandictionary.network.HistoryApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DictionaryViewModel extends AndroidViewModel {
    private final DictionaryRepository repository;
    private final MutableLiveData<List<FavoritesApiService.FavoriteInfo>> favorites = new MutableLiveData<>();
    private final MutableLiveData<List<HistoryApiService.HistoryEntry>> history = new MutableLiveData<>();

    public DictionaryViewModel(@NonNull Application application) {
        super(application);
        repository = new DictionaryRepository(application.getApplicationContext());
    }

    // FAVORITES
    public LiveData<List<FavoritesApiService.FavoriteInfo>> getFavorites() { return favorites; }
    public void loadFavorites() {
        repository.getAllFavorites(new Callback<List<FavoritesApiService.FavoriteInfo>>() {
            @Override
            public void onResponse(Call<List<FavoritesApiService.FavoriteInfo>> call, Response<List<FavoritesApiService.FavoriteInfo>> response) {
                favorites.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<FavoritesApiService.FavoriteInfo>> call, Throwable t) {
                favorites.postValue(null);
            }
        });
    }
    public void addFavorite(FavoritesApiService.FavoriteInfo favorite) {
        repository.addFavorite(favorite, new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadFavorites(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
    public void removeFavorite(String word) {
        repository.removeFavorite(word, new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadFavorites(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
    public void clearFavorites() {
        repository.clearFavorites(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadFavorites(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }

    // HISTORY
    public LiveData<List<HistoryApiService.HistoryEntry>> getHistory() { return history; }
    public void loadHistory() {
        repository.getAllHistory(new Callback<List<HistoryApiService.HistoryEntry>>() {
            @Override
            public void onResponse(Call<List<HistoryApiService.HistoryEntry>> call, Response<List<HistoryApiService.HistoryEntry>> response) {
                history.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<HistoryApiService.HistoryEntry>> call, Throwable t) {
                history.postValue(null);
            }
        });
    }
    public void addHistory(HistoryApiService.HistoryEntry entry) {
        repository.addHistory(entry, new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadHistory(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
    public void removeHistory(String word) {
        repository.removeHistory(word, new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadHistory(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
    public void clearHistory() {
        repository.clearHistory(new Callback<Void>() {
            @Override public void onResponse(Call<Void> call, Response<Void> response) { loadHistory(); }
            @Override public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
} 