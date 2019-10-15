package pe.com.pedidosya.beans;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("data")
    @Expose
    private List<oTRO> data = null;

    public Data() {
    }

    /**
     *
     * @param data
     */
    public Data(List<oTRO> data) {
        super();
        this.data = data;
    }

    public List<oTRO> getData() {
        return data;
    }

    public void setData(List<oTRO> data) {
        this.data = data;
    }

}