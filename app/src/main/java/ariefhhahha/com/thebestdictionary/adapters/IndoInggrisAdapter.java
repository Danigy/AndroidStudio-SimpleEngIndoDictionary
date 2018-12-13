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
import ariefhhahha.com.thebestdictionary.models.IndoInggrisModel;

public class IndoInggrisAdapter extends RecyclerView.Adapter<IndoInggrisAdapter.IndoInggrisHolder> {

    private ArrayList<IndoInggrisModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public IndoInggrisAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public IndoInggrisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_indo_inggris_row, parent, false);
        return new IndoInggrisHolder(view);
    }

    public void addItem(ArrayList<IndoInggrisModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull IndoInggrisHolder holder, int position) {
        holder.tvKata.setText(mData.get(position).getKata_indonesia());
        holder.tvArti.setText(mData.get(position).getArti_indonesia());
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

    public class IndoInggrisHolder extends RecyclerView.ViewHolder {

        TextView tvKata;
        TextView tvArti;

        public IndoInggrisHolder(@NonNull View itemView) {
            super(itemView);

            tvKata = itemView.findViewById(R.id.tv_kata);
            tvArti = itemView.findViewById(R.id.tv_arti);
        }
    }
}
