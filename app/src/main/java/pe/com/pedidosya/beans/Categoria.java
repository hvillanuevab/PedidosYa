package pe.com.pedidosya.beans;

public class Categoria {

    private int codCategoria;
    private String nomCategoria;
    private String imagen;

    public Categoria() {
    }

    public Categoria(int codCategoria, String nomCategoria, String imagen) {
        this.codCategoria = codCategoria;
        this.nomCategoria = nomCategoria;
        this.imagen = imagen;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
