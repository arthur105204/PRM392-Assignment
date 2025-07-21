package vn.edu.fpt.koreandictionary.network;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import vn.edu.fpt.koreandictionary.network.FavoritesApiService;
import vn.edu.fpt.koreandictionary.network.KRDictApiService;
import vn.edu.fpt.koreandictionary.network.HistoryApiService;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.50.208:8080/api/"; // Replace with your PC's IP
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static FavoritesApiService getFavoritesApiService() {
        return getClient().create(FavoritesApiService.class);
    }
    public static KRDictApiService getKRDictApiService() {
        return getClient().create(KRDictApiService.class);
    }
    public static HistoryApiService getHistoryApiService() {
        return getClient().create(HistoryApiService.class);
    }
}