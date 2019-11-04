package pe.com.pedidosya.ui.empresas;

import com.google.gson.JsonObject;
import com.orm.query.Select;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import pe.com.pedidosya.R;
import pe.com.pedidosya.beans.Empresa;
import pe.com.pedidosya.beans.RepuestaEmpresa;
import pe.com.pedidosya.utils.Callback;
import pe.com.pedidosya.utils.NetworkingUtils;

public class EmpresaModel implements EmpresaMVP.Model {

    public EmpresaModel() {
    }

    @Override
    public void getEmpresas(Callback<RepuestaEmpresa> respuesta) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("token","62641225-85af-4ed4-aa0c-c689e772fe7c");
        jsonObject.addProperty("codEmpresa","-");
        jsonObject.addProperty("tipo","-1");
        jsonObject.addProperty("codCategoria","-1");

        NetworkingUtils.getApi()
                .getEmpresas(jsonObject)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<RepuestaEmpresa>() {
                    @Override
                    public void onNext(RepuestaEmpresa repuestaEmpresa) {
                            respuesta.returnResult(repuestaEmpresa);
                    }

                    @Override
                    public void onError(Throwable e) {
                           respuesta.returnError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public List<Empresa> getBuscarEmpresa(String empresa) {

        if (empresa.length()>0){ //nomEmpAliada
            String query = "SELECT * FROM empresa where NOM_EMP_ALIADA like '%" +empresa+ "%'";
             return Empresa.findWithQuery(Empresa.class, query);
            //categoriaList=Select.from(Categoria.class).where(Condition.prop("NOM_CATEGORIA").eq(etBuscar.getText().toString().trim())).list();
        }else{
             return Select.from(Empresa.class).list();
        }
    }

}
