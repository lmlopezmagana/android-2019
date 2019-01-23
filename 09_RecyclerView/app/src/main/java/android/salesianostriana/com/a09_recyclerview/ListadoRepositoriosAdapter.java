package android.salesianostriana.com.a09_recyclerview;

import android.content.Context;
import android.salesianostriana.com.a09_recyclerview.model.Repo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListadoRepositoriosAdapter
        extends RecyclerView.Adapter<ListadoRepositoriosAdapter.ListadoRepositoriosViewHolder> {

    List<Repo> mDataset;
    Context mContext;

    public ListadoRepositoriosAdapter(Context context, List<Repo> dataset) {
        mContext = context;
        mDataset = dataset;
    }


    public static class ListadoRepositoriosViewHolder extends RecyclerView.ViewHolder {

        public TextView text1;
        public TextView text2;
        public ImageView imageView;


        public ListadoRepositoriosViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }


    @NonNull
    @Override
    public ListadoRepositoriosAdapter.ListadoRepositoriosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ListadoRepositoriosViewHolder vh = new ListadoRepositoriosViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoRepositoriosAdapter.ListadoRepositoriosViewHolder listadoRepositoriosViewHolder, int i) {
        Repo repo = mDataset.get(i);

        listadoRepositoriosViewHolder.text1.setText(repo.getName());
        listadoRepositoriosViewHolder.text2.setText(repo.getFull_name());
        Glide
                .with(mContext)
                .load(repo.getOwner().getAvatar_url())
                .into(listadoRepositoriosViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
