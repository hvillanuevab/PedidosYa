package pe.com.pedidosya.utils;

import pe.com.pedidosya.api.Api;

public class NetworkingUtils {

    private static Api api;

    public static Api getApi() {

        if (api==null){
            api=RetrofitAdapter.getRetrofit().create(Api.class);
        }
        return api;
    }
}
