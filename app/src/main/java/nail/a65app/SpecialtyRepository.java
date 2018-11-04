package nail.a65app;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import nail.a65app.models.EmployesList;
import nail.a65app.database.Employes;
import nail.a65app.database.EmployesDao;
import nail.a65app.database.EmployesRoomDatabase;
import nail.a65app.network.DataServiceGenerator;
import nail.a65app.network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialtyRepository {

    private EmployesDao mEmployesDao;
    private LiveData<List<String>> specialtyName;
    private LiveData<List<Employes>> specialtyWorker;
    private LiveData<List<Employes>> workerId;

    public SpecialtyRepository(Application application) {
        EmployesRoomDatabase db = EmployesRoomDatabase.getDatabase(application);
        mEmployesDao = db.employesDao();
        specialtyName = mEmployesDao.getSpecialtyName();
    }

    public void loadDataNetwork() {
        DataServiceGenerator DataServiceGenerator = new DataServiceGenerator();
        Service service = DataServiceGenerator.createService(Service.class);
        Call<EmployesList> call = service.getData();
        call.enqueue(new Callback<EmployesList>() {
            @Override
            public void onResponse(Call<EmployesList> call, Response<EmployesList> response) {
                EmployesList employesModelList = response.body();
                for (int i = 0; i < employesModelList.getEmploye().size(); i++) {
                    String f_name = employesModelList.getEmploye().get(i).getF_name();
                    String l_name = employesModelList.getEmploye().get(i).getL_name();
                    Log.d("DATABASE", "Add database: " + employesModelList.getEmploye().get(i).getBirthday());
                    String birthday = employesModelList.getEmploye().get(i).getBirthday();
                    String avatr_url = employesModelList.getEmploye().get(i).getAvatr_url();
                    String specialty_id = employesModelList.getEmploye().get(i).getSpecialty().get(0).getSpecialty_id();
                    String name = employesModelList.getEmploye().get(i).getSpecialty().get(0).getName();
                    Employes questions = new Employes(f_name, l_name,
                            birthday, avatr_url, specialty_id, name);
                    insert(questions);
                }
            }

            @Override
            public void onFailure(Call<EmployesList> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Employes>> getSpecialtyWorker(String specialty) {
        return mEmployesDao.getSpecialtyWorker(specialty);
    }

    public LiveData<List<Employes>> getWorkerId(int id) {
        return mEmployesDao.getId(id);
    }

    public LiveData<List<String>> getSpecialtyName() {
        return specialtyName;
    }

    public void insert(Employes employes) {
        new insertAsyncTask(mEmployesDao).execute(employes);
    }

    public void insertEmployes(Employes... employes) {
        new insertEmployesAsyncTask(mEmployesDao).execute(employes);
    }

    private static class insertEmployesAsyncTask extends AsyncTask<Employes, Void, Void> {
        private EmployesDao mAsyncTaskDao;

        insertEmployesAsyncTask(EmployesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employes... params) {
            mAsyncTaskDao.insertEmployes(params[0]);
            return null;
        }
    }


    private static class insertAsyncTask extends AsyncTask<Employes, Void, Void> {
        private EmployesDao mAsyncTaskDao;

        insertAsyncTask(EmployesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employes... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}