package pe.com.pedidosya.beans;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class oTRO {

    @SerializedName("categoriaid")
    @Expose
    private Integer categoriaid;
    @SerializedName("nombrecat")
    @Expose
    private String nombrecat;

    public oTRO() {
    }

    /**
     *
     * @param categoriaid
     * @param nombrecat
     */
    public oTRO(Integer categoriaid, String nombrecat) {
        super();
        this.categoriaid = categoriaid;
        this.nombrecat = nombrecat;
    }

    public Integer getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(Integer categoriaid) {
        this.categoriaid = categoriaid;
    }

    public String getNombrecat() {
        return nombrecat;
    }

    public void setNombrecat(String nombrecat) {
        this.nombrecat = nombrecat;
    }


}
