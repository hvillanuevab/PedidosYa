package pe.com.pedidosya.beans;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("cPerCodigo")
    @Expose
    private String cPerCodigo;
    @SerializedName("nomEmpAliada")
    @Expose
    private String nomEmpAliada;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("nAliTipo")
    @Expose
    private String nAliTipo;
    @SerializedName("precioMinimo")
    @Expose
    private String precioMinimo;
    @SerializedName("tipoEntrega")
    @Expose
    private String tipoEntrega;
    @SerializedName("nCantDiasReserva")
    @Expose
    private String nCantDiasReserva;

    /**
     * No args constructor for use in serialization
     *
     */
    public Empresa() {
    }

    /**
     *
     * @param precioMinimo
     * @param nAliTipo
     * @param direccion
     * @param imagen
     * @param nCantDiasReserva
     * @param tipoEntrega
     * @param latitud
     * @param nomEmpAliada
     * @param cPerCodigo
     * @param longitud
     * @param banner
     */
    public Empresa(String cPerCodigo, String nomEmpAliada, String imagen, String banner, String direccion, String latitud, String longitud, String nAliTipo, String precioMinimo, String tipoEntrega, String nCantDiasReserva) {
        super();
        this.cPerCodigo = cPerCodigo;
        this.nomEmpAliada = nomEmpAliada;
        this.imagen = imagen;
        this.banner = banner;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nAliTipo = nAliTipo;
        this.precioMinimo = precioMinimo;
        this.tipoEntrega = tipoEntrega;
        this.nCantDiasReserva = nCantDiasReserva;
    }

    public String getCPerCodigo() {
        return cPerCodigo;
    }

    public void setCPerCodigo(String cPerCodigo) {
        this.cPerCodigo = cPerCodigo;
    }

    public String getNomEmpAliada() {
        return nomEmpAliada;
    }

    public void setNomEmpAliada(String nomEmpAliada) {
        this.nomEmpAliada = nomEmpAliada;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNAliTipo() {
        return nAliTipo;
    }

    public void setNAliTipo(String nAliTipo) {
        this.nAliTipo = nAliTipo;
    }

    public String getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(String precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getNCantDiasReserva() {
        return nCantDiasReserva;
    }

    public void setNCantDiasReserva(String nCantDiasReserva) {
        this.nCantDiasReserva = nCantDiasReserva;
    }
}
