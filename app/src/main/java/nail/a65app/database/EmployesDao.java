package nail.a65app.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EmployesDao {

    // выбор уникальных значений профессии.
    @Query("SELECT DISTINCT name from employes_table")
    LiveData<List<String>> getSpecialtyName();


    // выборорка по профессии.
    @Query("SELECT * FROM employes_table WHERE name = :name")
    LiveData<List<Employes>> getSpecialtyWorker(String name);

    // выбор по id.
    @Query("SELECT * FROM employes_table WHERE id = :id ")
    LiveData<List<Employes>> getId(int id);


    @Insert
    void insert(Employes employes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertEmployes(Employes... employes);

    @Query("DELETE FROM employes_table")
    void deleteAll();
}
