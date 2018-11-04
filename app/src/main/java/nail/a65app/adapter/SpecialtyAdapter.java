package nail.a65app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nail.a65app.R;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.SpecialtyViewHolder> {

    class SpecialtyViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private SpecialtyViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    private final LayoutInflater mInflater;
    private List<String> mSpecialty;
    private final OnItemClickListener listener;

    public SpecialtyAdapter(Context context, OnItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public SpecialtyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_specialty, parent, false);
        return new SpecialtyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpecialtyViewHolder holder, final int position) {
        holder.wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mSpecialty.get(position));
            }
        });

        if (mSpecialty != null) {
            String current = mSpecialty.get(position);
            holder.wordItemView.setText(current.toString());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No employes");
        }
    }


    public void setSpecialty(List<String> specialty) {
        mSpecialty = specialty;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSpecialty != null)
            return mSpecialty.size();
        else return 0;
    }
}