package nail.a65app.network;

import nail.a65app.models.EmployesList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("65gb/static/raw/master/testTask.json")
    Call<EmployesList> getData();

}