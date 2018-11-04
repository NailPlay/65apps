package nail.a65app.models;

import java.util.List;

public class EmployesModel {
    private String f_name;
    private String l_name;
    private String birthday;
    private String avatr_url;
    private List<SpecialtyModel> specialty;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatr_url() {
        return avatr_url;
    }

    public void setAvatr_url(String avatr_url) {
        this.avatr_url = avatr_url;
    }

    public List<SpecialtyModel> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<SpecialtyModel> specialty) {
        this.specialty = specialty;
    }
}
