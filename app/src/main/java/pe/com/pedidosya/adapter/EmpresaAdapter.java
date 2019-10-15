package pe.com.pedidosya.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pe.com.pedidosya.R;
import pe.com.pedidosya.beans.Empresa;

public class EmpresaAdapter  extends RecyclerView.Adapter<EmpresaAdapter.EmpresaItem> {

    private List<Empresa> empresaList;
    private Activity activity;

    public EmpresaAdapter(Activity activity,List<Empresa> empresaList) {
        this.empresaList = empresaList;
        this.activity=activity;
    }


    @NonNull
    @Override
    public EmpresaItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard_empresa,parent,false);
        return new EmpresaItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaItem holder, int position) {
        Empresa empresa=empresaList.get(position);
        holder.tvNombre.setText(empresa.getNomEmpAliada());
        holder.tvDireccion.setText(empresa.getDireccion());
        holder.tvPrecio.setText(empresa.getPrecioMinimo());
        String foto=empresa.getImagen();
        foto=foto.replace("http","https");
        Glide.with(activity).load(foto)
                .thumbnail(0.5f)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.sinfoto)
                .dontAnimate()
                .placeholder(R.drawable.sinfoto)
                .into(holder.cvFoto);
    }

    @Override
    public int getItemCount() {
        return empresaList.size();
    }

    public class EmpresaItem extends RecyclerView.ViewHolder{

        @BindView(R.id.cvFoto)
        CircleImageView cvFoto;

        @BindView(R.id.tvNombre) TextView tvNombre;
        @BindView(R.id.tvDireccion) TextView tvDireccion;
        @BindView(R.id.tvPrecio) TextView tvPrecio;

        public EmpresaItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
