package nail.a65app.ui.worker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nail.a65app.EmployesViewModel;
import nail.a65app.R;
import nail.a65app.SharedViewModel;
import nail.a65app.adapter.SpecialtyAdapter;
import nail.a65app.adapter.WorkerAdapter;
import nail.a65app.database.Employes;
import nail.a65app.ui.detail.DetailFragment;

import static android.graphics.drawable.ClipDrawable.VERTICAL;


public class WorkerFragment extends Fragment {
    @BindView(R.id.workerRecyclerView)
    RecyclerView recyclerView;
    private EmployesViewModel employesViewModel;
    private SharedViewModel modelShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_worker, container, false);
        ButterKnife.bind(this, view);
        final WorkerAdapter adapter = new WorkerAdapter(getContext(),
                new WorkerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Employes item) {
                        modelShare.setId(item.getId());
                        DetailFragment nextFrag = new DetailFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contentFrame, nextFrag)
                                .addToBackStack(null)
                                .commit();

                    }
                });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        employesViewModel = ViewModelProviders.of(this).get(EmployesViewModel.class);
        modelShare.getSpecialityName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String specialityName) {
                employesViewModel.getSpecialtyWorker(specialityName).observe(getActivity(), new Observer<List<Employes>>() {
                    @Override
                    public void onChanged(@Nullable List<Employes> employes) {
                        adapter.setSpecialty(employes);
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
