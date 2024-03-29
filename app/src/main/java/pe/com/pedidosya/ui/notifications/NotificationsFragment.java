package pe.com.pedidosya.ui.notifications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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
import pe.com.pedidosya.R;
import pe.com.pedidosya.adapter.EmpresaAdapter;
import pe.com.pedidosya.api.Api;

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


}