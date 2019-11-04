package pe.com.pedidosya.ui.empresas;

import android.app.Activity;

import com.orm.query.Select;

import java.util.List;

import pe.com.pedidosya.beans.Empresa;
import pe.com.pedidosya.beans.RepuestaEmpresa;
import pe.com.pedidosya.service.ConnectivityReceiver;
import pe.com.pedidosya.utils.Callback;

public class EmpresaPresenter implements EmpresaMVP.Presenter {

    EmpresaMVP.View view;
    EmpresaMVP.Model model;
    Activity activity;

    public EmpresaPresenter(EmpresaMVP.View view) {
        this.view = view;
        this.model=new EmpresaModel();
    }

    @Override
    public void start(Activity activity) {
        this.activity=activity;
        view.init();

    }

    @Override
    public void listEmpresa() {
        if (ConnectivityReceiver.getConnectivityStatus(activity))
            model.getEmpresas(new Callback<RepuestaEmpresa>() {
                @Override
                public void returnResult(RepuestaEmpresa repuestaEmpresa) {
                    Empresa.deleteAll(Empresa.class);

                    for (Empresa empresa: repuestaEmpresa.getEmpresaList() )
                        empresa.save();

                    List<Empresa> empresaList= Select.from(Empresa.class).list();
                    view.loadEmpresa(repuestaEmpresa.getEmpresaList());
                }

                @Override
                public void returnError(String message) {
                    view.showError(message);
                }
            });
        else
            view.showError("Verifique su conexión a internet");
        }

    @Override
    public void BuscarEmpresa(String empresa) {
       view.loadEmpresa( model.getBuscarEmpresa(empresa));
    }
}
