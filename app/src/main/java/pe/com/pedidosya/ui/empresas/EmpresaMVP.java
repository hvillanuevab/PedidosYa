package pe.com.pedidosya.ui.empresas;

import android.app.Activity;

import java.util.List;

import pe.com.pedidosya.beans.Empresa;
import pe.com.pedidosya.beans.RepuestaEmpresa;
import pe.com.pedidosya.utils.Callback;

public interface EmpresaMVP {

    interface View {
        void init();

        void showDialog();

        void hideDialog();
        void showError(String message);

        void loadEmpresa(List<Empresa> empresaList);

    }

    interface Presenter {
        void start(Activity activity);
        void listEmpresa();

        void BuscarEmpresa(String empresa);
    }

    interface Model {
        void getEmpresas(Callback<RepuestaEmpresa> respuesta);

        List<Empresa> getBuscarEmpresa(String empresa);
    }
}
