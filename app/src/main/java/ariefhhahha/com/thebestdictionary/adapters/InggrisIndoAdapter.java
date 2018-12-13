package ariefhhahha.com.thebestdictionary.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ariefhhahha.com.thebestdictionary.R;
import ariefhhahha.com.thebestdictionary.models.InggrisIndoModel;

public class InggrisIndoAdapter extends RecyclerView.Adapter<InggrisIndoAdapter.InggrisIndoHolder> {

    private ArrayList<InggrisIndoModel>  mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public InggrisIndoAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public InggrisIndoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indo_inggris_row, parent, false);
        return new InggrisIndoHolder(view);
    }

    public void addItem(ArrayList<InggrisIndoModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull InggrisIndoHolder holder, int position) {
        holder.tvKata.setText(mData.get(position).getKata_inggris());
        holder.tvArti.setText(mData.get(position).getArti_inggris());
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InggrisIndoHolder extends RecyclerView.ViewHolder {

        TextView tvKata;
        TextView tvArti;

        public InggrisIndoHolder(@NonNull View itemView) {
            super(itemView);
            tvKata = itemView.findViewById(R.id.tv_kata);
            tvArti = itemView.findViewById(R.id.tv_arti);
        }
    }
}
