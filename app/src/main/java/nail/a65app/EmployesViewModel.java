package nail.a65app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import nail.a65app.database.Employes;

public class EmployesViewModel extends AndroidViewModel {

    private SpecialtyRepository specialtyRepository;
    private LiveData<List<String>> specialtyName;
    private LiveData<List<Employes>> specialtyWorker;
    private LiveData<List<Employes>> workerId;

    public EmployesViewModel(Application application) {
        super(application);
        specialtyRepository = new SpecialtyRepository(application);
        specialtyName = specialtyRepository.getSpecialtyName();
    }

    public LiveData<List<Employes>> getSpecialtyWorker(String specialty) {
        specialtyWorker = specialtyRepository.getSpecialtyWorker(specialty);
        return specialtyWorker;
    }

    public LiveData<List<Employes>> getWorkerId(int id) {
        workerId = specialtyRepository.getWorkerId(id);
        return workerId;
    }

    public LiveData<List<String>> getSpecialtyName() {
        return specialtyName;
    }

    public void getLoadDataInternet() {
        specialtyRepository.loadDataNetwork();
    }

    public void insert(Employes employes) {
        specialtyRepository.insert(employes);
    }

    public void insertEmployes(Employes... employes) {
        specialtyRepository.insertEmployes(employes);
    }
}