package pe.com.pedidosya.api;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import pe.com.pedidosya.beans.RepuestaEmpresa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @POST("empresasAliadas/{id}")
    @FormUrlEncoded
    Call<RepuestaEmpresa> getEmpresas(@Path("id") String token, @Field("codEmpresa") String codEmpresa, @Field("tipo") String tipo, @Field("codCategoria") String codCategoria);

    @POST("empresasAliadas")
    Call<RepuestaEmpresa> getListEmpresas
            ( @Body JsonObject body);

    @POST("empresasAliadas")
    Observable<RepuestaEmpresa> getEmpresas(@Body JsonObject body);

}
