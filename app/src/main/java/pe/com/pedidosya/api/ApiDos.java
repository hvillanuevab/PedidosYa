package pe.com.pedidosya.api;

import pe.com.pedidosya.beans.Data;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDos {
    @GET("categorias")
    Call<Data> getData();
}
