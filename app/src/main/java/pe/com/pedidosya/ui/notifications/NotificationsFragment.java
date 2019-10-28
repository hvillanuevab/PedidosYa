package pe.com.pedidosya.ui.notifications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.com.pedidosya.R;
import pe.com.pedidosya.adapter.CategoriaAdapter;
import pe.com.pedidosya.adapter.EmpresaAdapter;
import pe.com.pedidosya.api.Api;
import pe.com.pedidosya.api.ApiDos;
import pe.com.pedidosya.api.NetworkClient;
import pe.com.pedidosya.beans.Categoria;
import pe.com.pedidosya.beans.Data;
import pe.com.pedidosya.beans.Empresa;
import pe.com.pedidosya.beans.RepuestaEmpresa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    ProgressDialog progressDialog;
    private List<Empresa> empresaList=new ArrayList<>();

    String url="https://adylconsulting.com/portafolio/urgent/api/v.1.0/empresasAliadas";
    @BindView(R.id.rvLista)  RecyclerView rvCategoria;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this,root);
        inicializar();
        wsListarEmpresa();
        return root;
    }

    private void inicializar(){
        rvCategoria.setHasFixedSize(true); //tamaño de nuestro recycler view
        rvCategoria.setLayoutManager(new GridLayoutManager(getActivity(),1));
        rvCategoria.setItemAnimator(new DefaultItemAnimator());
    }

    private void wsListarEmpresa(){

        Gson gson = new GsonBuilder()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adylconsulting.com/portafolio/urgent/api/v.1.0/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api api = retrofit.create(Api.class);

        JsonObject jsonObject=new JsonObject();

            jsonObject.addProperty("token",getActivity().getString(R.string.tokenApi));
            jsonObject.addProperty("codEmpresa","-");
            jsonObject.addProperty("tipo","-1");
            jsonObject.addProperty("codCategoria","-1");

        Call<RepuestaEmpresa> call=api.getListEmpresas(jsonObject);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                RepuestaEmpresa respuesta= (RepuestaEmpresa) response.body();
                rvCategoria.setAdapter(new EmpresaAdapter(getActivity(),respuesta.getEmpresaList()));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                String a="sss";
            }
        });
    }

    private void wsListarEmpresas(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("token",getActivity().getString(R.string.tokenApi));
            jsonObject.put("codEmpresa","-");
            jsonObject.put("tipo","-1");
            jsonObject.put("codCategoria","-1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObject_Categoria=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("respuesta");
                    if (jsonArray != null && jsonArray.length() > 0){
                        empresaList.clear();
                        for (int i = 0; i <jsonArray.length() ; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Empresa categoria =new Empresa(

                                    jsonObject.getString("cPerCodigo"),
                                    jsonObject.getString("nomEmpAliada"),
                                    jsonObject.getString("imagen"),
                                    jsonObject.getString("banner"),
                                    jsonObject.getString("direccion"),
                                    jsonObject.getString("latitud"),
                                    jsonObject.getString("longitud"),
                                    jsonObject.getString("nAliTipo"),
                                    jsonObject.getString("precioMinimo"),
                                    jsonObject.getString("tipoEntrega"),
                                    jsonObject.getString("nCantDiasReserva")
                            );
                            empresaList.add(categoria);
                            rvCategoria.setAdapter(new EmpresaAdapter(getActivity(),empresaList));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObject_Categoria);
    }
}