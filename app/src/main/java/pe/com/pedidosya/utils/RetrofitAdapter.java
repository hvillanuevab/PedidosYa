package pe.com.pedidosya.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    public static final String BASE_URL = "https://adylconsulting.com/portafolio/urgent/api/v.1.0/";
    public static Retrofit retrofit;
    private static Gson gson;

    public void NetworkClient(){

    }

    public static Retrofit getRetrofit() {
        if (retrofit==null){

            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }

            retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
