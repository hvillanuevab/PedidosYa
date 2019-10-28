package pe.com.pedidosya.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
import com.orm.query.Condition;
import com.orm.query.Select;

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
import pe.com.pedidosya.service.ConnectivityReceiver;

public class DashboardFragment extends Fragment {

    ProgressDialog progressDialog;
    String url="https://adylconsulting.com/portafolio/urgent/api/v.1.0/listarCategorias";

    private List<Categoria> categoriaList=new ArrayList<>();

    @BindView(R.id.rvLista) RecyclerView rvCategoria;
    @BindView(R.id.etBuscar)  EditText etBuscar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,root);

        inicializar();
        if (ConnectivityReceiver.getConnectivityStatus(getActivity())){
            wsListarCategoria();
        }else {
            Toast.makeText(getActivity(),"No hay conexión a internet",Toast.LENGTH_SHORT).show();
            categoriaList= Select.from(Categoria.class).list();
            rvCategoria.setAdapter(new CategoriaAdapter(getActivity(),categoriaList));

        }


        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    String query = "SELECT * FROM categoria where NOM_CATEGORIA like '%" + etBuscar.getText().toString().trim().toUpperCase() + "%'";
                    categoriaList= Categoria.findWithQuery(Categoria.class, query);
                    //categoriaList=Select.from(Categoria.class).where(Condition.prop("NOM_CATEGORIA").eq(etBuscar.getText().toString().trim())).list();
                }else{
                    categoriaList=Select.from(Categoria.class).list();
                }
                rvCategoria.setAdapter(new CategoriaAdapter(getActivity(),categoriaList));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

    private void inicializar(){
        rvCategoria.setHasFixedSize(true); //tamaño de nuestro recycler view
        rvCategoria.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvCategoria.setItemAnimator(new DefaultItemAnimator());
    }

    private void wsListarCategoria(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), new HurlStack());
        progressDialog=showProgressDialog();
        progressDialog.show();
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
                        Categoria.deleteAll(Categoria.class);
                        for (int i = 0; i <jsonArray.length() ; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Categoria categoria =new Categoria(
                                jsonObject.getInt("codCategoria"),
                                    jsonObject.getString("nomCategoria").trim(),
                                    jsonObject.getString("imagen")
                            );
                            categoria.save();
                            categoriaList.add(categoria);
                            rvCategoria.setAdapter(new CategoriaAdapter(getActivity(),categoriaList));
                            progressDialog.hide();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
            }
        });

        requestQueue.add(jsonObject_Categoria);
    }

    private ProgressDialog showProgressDialog(){
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light_Dialog));
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Cargando categorias");
        progressDialog.setTitle(getActivity().getString(R.string.app_name));
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }


}