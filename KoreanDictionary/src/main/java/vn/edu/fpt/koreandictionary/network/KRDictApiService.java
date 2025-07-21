package vn.edu.fpt.koreandictionary.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.edu.fpt.koreandictionary.model.KRDictResponse;

public interface KRDictApiService {
    @GET("search")
    Call<KRDictResponse> searchWord(
        @Query("q") String query,
        @Query("translated") String translated,      // "y" for translation (default)
        @Query("trans_lang") String transLang,      // "1" for English (default)
        @Query("num") Integer num,                  // e.g., 10 (default)
        @Query("start") Integer start,              // e.g., 1 (default)
        @Query("sort") String sort,                 // "dict" or "popular" (default)
        @Query("part") String part,                 // "word", "ip", etc. (default)
        @Query("advanced") String advanced          // "y" or "n" (default)
    );
}