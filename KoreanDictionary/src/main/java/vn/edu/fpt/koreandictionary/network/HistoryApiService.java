package vn.edu.fpt.koreandictionary.network;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface HistoryApiService {
    class HistoryEntry {
        public String word;
        public long timestamp;
    }

    @GET("history")
    Call<List<HistoryEntry>> getAllHistory();

    @POST("history")
    Call<Void> addHistory(@Body HistoryEntry entry);

    @DELETE("history")
    Call<Void> removeHistory(@Query("word") String word);

    @DELETE("history/clear")
    Call<Void> clearHistory();
} 