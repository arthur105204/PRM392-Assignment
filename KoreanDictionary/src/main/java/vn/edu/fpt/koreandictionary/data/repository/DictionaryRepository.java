package vn.edu.fpt.koreandictionary.data.repository;

import android.content.Context;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import vn.edu.fpt.koreandictionary.network.FavoritesApiService;
import vn.edu.fpt.koreandictionary.network.HistoryApiService;
import vn.edu.fpt.koreandictionary.network.KRDictApiService;
import vn.edu.fpt.koreandictionary.network.RetrofitClient;

public class DictionaryRepository {
    private final FavoritesApiService favoritesApiService;
    private final HistoryApiService historyApiService;
    private final KRDictApiService krDictApiService;

    public DictionaryRepository(Context context) {
        favoritesApiService = RetrofitClient.getFavoritesApiService();
        historyApiService = RetrofitClient.getHistoryApiService();
        krDictApiService = RetrofitClient.getKRDictApiService();
    }

    // FAVORITES
    public void getAllFavorites(Callback<List<FavoritesApiService.FavoriteInfo>> callback) {
        favoritesApiService.getAllFavorites().enqueue(callback);
    }
    public void addFavorite(FavoritesApiService.FavoriteInfo favorite, Callback<Void> callback) {
        favoritesApiService.addFavorite(favorite).enqueue(callback);
    }
    public void removeFavorite(String word, Callback<Void> callback) {
        favoritesApiService.removeFavorite(word).enqueue(callback);
    }
    public void clearFavorites(Callback<Void> callback) {
        favoritesApiService.clearFavorites().enqueue(callback);
    }
    public void isFavorite(String word, Callback<Boolean> callback) {
        favoritesApiService.isFavorite(word).enqueue(callback);
    }

    // HISTORY
    public void getAllHistory(Callback<List<HistoryApiService.HistoryEntry>> callback) {
        historyApiService.getAllHistory().enqueue(callback);
    }
    public void addHistory(HistoryApiService.HistoryEntry entry, Callback<Void> callback) {
        historyApiService.addHistory(entry).enqueue(callback);
    }
    public void removeHistory(String word, Callback<Void> callback) {
        historyApiService.removeHistory(word).enqueue(callback);
    }
    public void clearHistory(Callback<Void> callback) {
        historyApiService.clearHistory().enqueue(callback);
    }

    // DICTIONARY SEARCH (implement as needed)
    // ...
} 