package nail.a65app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployesList {
    @SerializedName("response")
    private List<EmployesModel> employe;


    public List<EmployesModel> getEmploye() {
        return employe;
    }

    public void setEmploye(List<EmployesModel> employe) {
        this.employe = employe;
    }
}
