package nail.a65app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nail.a65app.R;
import nail.a65app.database.Employes;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvF_name;
        private final TextView tvL_name;
        private final TextView tvBirthday;

        private WordViewHolder(View itemView) {
            super(itemView);
            this.tvF_name = itemView.findViewById(R.id.tvF_name);
            this.tvL_name = itemView.findViewById(R.id.tvL_name);
            this.tvBirthday = itemView.findViewById(R.id.tvBirthday);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Employes item);
    }

    private final LayoutInflater mInflater;
    private List<Employes> mEmpliyes;
    private final OnItemClickListener listener;

    public WorkerAdapter(Context context, OnItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_worker, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mEmpliyes.get(position));
            }
        });
        if (mEmpliyes != null) {
            Employes current = mEmpliyes.get(position);
            holder.tvL_name.setText(current.getL_name());
            holder.tvF_name.setText(current.getF_name());
            String s = current.getBirthday();
            Log.d("MAIN",s);
            if (!s.equals("-")) {
                s = s.replace("-", ".") + " г.";
            }
            holder.tvBirthday.setText(s);
        } else {
            holder.tvF_name.setText("Null");
        }
    }


    public void setSpecialty(List<Employes> employes) {
        mEmpliyes = employes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEmpliyes != null)
            return mEmpliyes.size();
        else return 0;
    }
}