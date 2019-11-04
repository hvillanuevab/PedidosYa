package pe.com.pedidosya.ui.empresas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import pe.com.pedidosya.R;
import pe.com.pedidosya.adapter.EmpresaAdapter;
import pe.com.pedidosya.beans.Empresa;

public class EmpresaActivity extends AppCompatActivity implements EmpresaMVP.View  {


    EmpresaMVP.Presenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rvEmpresas) RecyclerView rvEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        ButterKnife.bind(this);

        toolbar.setTitle("MVP");
        setSupportActionBar(toolbar);

        presenter=new EmpresaPresenter(this);
        presenter.start(this);
    }



    @OnClick(R.id.fab)
    public void clickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @OnTextChanged(value = R.id.etEmpresa, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void buscarEmpresa(CharSequence text) {
        presenter.BuscarEmpresa(text.toString());
    }

    @Override
    public void init() {
        rvEmpresas.setHasFixedSize(true);
        rvEmpresas.setLayoutManager(new GridLayoutManager(this,1));
        rvEmpresas.setItemAnimator(new DefaultItemAnimator());


        presenter.listEmpresa();

    }


    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEmpresa(List<Empresa> empresaList) {
        Toast.makeText(this,"listado de enmpresas ",Toast.LENGTH_SHORT).show();
        rvEmpresas.setAdapter(new EmpresaAdapter(this,empresaList));
    }


}

