package pe.com.pedidosya.beans;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepuestaEmpresa {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("respuesta")
    @Expose
    private List<Empresa> empresaList= null;

    public RepuestaEmpresa() {
    }

    public RepuestaEmpresa(String status, List<Empresa> empresaList) {
        this.status = status;
        this.empresaList = empresaList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }
}
