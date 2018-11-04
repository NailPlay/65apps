package nail.a65app.ui.specialty;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nail.a65app.EmployesViewModel;
import nail.a65app.R;
import nail.a65app.SharedViewModel;
import nail.a65app.adapter.SpecialtyAdapter;
import nail.a65app.adapter.WorkerAdapter;
import nail.a65app.ui.worker.WorkerFragment;

import static android.graphics.drawable.ClipDrawable.VERTICAL;

public class SpecialityFragment extends Fragment {
    @BindView(R.id.specialityRecyclerView)
    RecyclerView recyclerView;
    private EmployesViewModel employesViewModel;
    private SharedViewModel modelShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static SpecialityFragment newInstance() {
        return new SpecialityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speciality, container, false);
        ButterKnife.bind(this, view);
        modelShare = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        final SpecialtyAdapter adapter = new SpecialtyAdapter(getContext(),
                new SpecialtyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        //Toast.makeText(getContext(), "Item Clicked " + item, Toast.LENGTH_LONG).show();
                        // передает данные
                        modelShare.setSpecialityNae(item);
                        WorkerFragment nextFrag = new WorkerFragment();
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
        employesViewModel = ViewModelProviders.of(this).get(EmployesViewModel.class);
        employesViewModel.getSpecialtyName().observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> specialty) {
                if (specialty.isEmpty()) {
                    employesViewModel.getLoadDataInternet();
                } else {
                    adapter.setSpecialty(specialty);
                }
            }
        });
        return view;
    }

}
