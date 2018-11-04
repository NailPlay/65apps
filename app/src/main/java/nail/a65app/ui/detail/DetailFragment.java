package nail.a65app.ui.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nail.a65app.EmployesViewModel;
import nail.a65app.R;
import nail.a65app.SharedViewModel;
import nail.a65app.database.Employes;
import nail.a65app.ui.worker.WorkerFragment;


public class DetailFragment extends Fragment {
    private EmployesViewModel employesViewModel;
    private SharedViewModel modelShare;

    @BindView(R.id.tvF_name)
    TextView tvF_name;

    @BindView(R.id.tvL_name)
    TextView tvL_name;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvAge)
    TextView tvAge;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        employesViewModel = ViewModelProviders.of(this).get(EmployesViewModel.class);
        modelShare.getId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer id) {
                employesViewModel.getWorkerId(id).observe(getActivity(), new Observer<List<Employes>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onChanged(@Nullable List<Employes> employes) {
                        // Update ui
                        tvF_name.setText(employes.get(0).getF_name());
                        tvL_name.setText(employes.get(0).getL_name());
                        tvName.setText(employes.get(0).getName());


                        try {
                            tvAge.setText("Возраст: " + employes.get(0).getAge());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
        return view;
    }


    public static WorkerFragment newInstance() {
        return new WorkerFragment();
    }
}
