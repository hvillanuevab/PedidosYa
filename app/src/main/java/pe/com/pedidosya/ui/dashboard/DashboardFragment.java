package pe.com.pedidosya.ui.dashboard;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.pedidosya.R;
import pe.com.pedidosya.adapter.CategoriaAdapter;
import pe.com.pedidosya.beans.Categoria;

public class DashboardFragment extends Fragment {

    String url="https://adylconsulting.com/portafolio/urgent/api/v.1.0/listarCategorias";

    private List<Categoria> categoriaList=new ArrayList<>();

    @BindView(R.id.rvLista) RecyclerView rvCategoria;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,root);

        inicializar();
        wsListarCategoria();
        return root;
    }

    private void inicializar(){
        rvCategoria.setHasFixedSize(true); //tamaño de nuestro recycler view
        rvCategoria.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvCategoria.setItemAnimator(new DefaultItemAnimator());
    }

    private void wsListarCategoria(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("token",getActivity().getString(R.string.tokenApi));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObject_Categoria=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("respuesta");
                    if (jsonArray != null && jsonArray.length() > 0){
                        categoriaList.clear();
                        for (int i = 0; i <jsonArray.length() ; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Categoria categoria =new Categoria(
                                jsonObject.getInt("codCategoria"),
                                    jsonObject.getString("nomCategoria"),
                                    jsonObject.getString("imagen")
                            );
                            categoriaList.add(categoria);
                            rvCategoria.setAdapter(new CategoriaAdapter(getActivity(),categoriaList));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObject_Categoria);
    }


}