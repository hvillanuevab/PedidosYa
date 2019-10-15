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
import pe.com.pedidosya.R;
import pe.com.pedidosya.beans.Categoria;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaItem> {

    private List<Categoria> categoriaList;
    private Activity activity;

    public CategoriaAdapter(Activity activity,List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public CategoriaItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard_categoria,parent,false);
        return new CategoriaItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaItem holder, int position) {
        Categoria categoria=categoriaList.get(position);
        holder.tvNombre.setText(categoria.getNomCategoria());
        String foto=categoria.getImagen();
        foto=foto.replace("http","https");
        Glide.with(activity).load(foto)
                .thumbnail(0.5f)
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.sinfoto)
                .dontAnimate()
                .placeholder(R.drawable.sinfoto)
                .into(holder.ivCategoria);
    }

    @Override
    public int getItemCount() {
        return categoriaList.size();
    }

    public class CategoriaItem extends RecyclerView.ViewHolder{

        @BindView(R.id.ivCategoria)
        ImageView ivCategoria;

        @BindView(R.id.tvNombre)
        TextView tvNombre;

        public CategoriaItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
