package nail.a65app.database;

import android.annotation.SuppressLint;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.util.Date;


@Entity(tableName = "employes_table")
public class Employes {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "f_name")
    private String f_name;

    @NonNull
    @ColumnInfo(name = "l_name")
    private String l_name;

    @NonNull
    @ColumnInfo(name = "birthday")
    private String birthday;

    @NonNull
    @ColumnInfo(name = "avatr_url")
    private String avatr_url;

    @NonNull
    @ColumnInfo(name = "specialty_id")
    private String specialty_id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Employes(@NonNull String f_name, @NonNull String l_name, @NonNull String birthday, @NonNull String avatr_url, @NonNull String specialty_id, @NonNull String name) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.birthday = birthday;
        this.avatr_url = avatr_url;
        this.specialty_id = specialty_id;
        this.name = name;
    }

    public String getF_name() {
        return firstUpperCase(f_name);
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return firstUpperCase(l_name);
    }

    private String firstUpperCase(String word) {
        word = word.toLowerCase();
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }


    public String getBirthday() {
        Log.d("bbb", "getBirthday: " + birthday);
        if (birthday != null) {
            if (!birthday.equals("")) {
                return birthday;
            } else {
                return "-";
            }
        }
        return "-";
    }


    @SuppressLint("NewApi")
    public String getAge() throws ParseException, ParseException {
        Log.d("MAIN", "IndexOf: " + birthday.indexOf("-") + " String: " + birthday);
        int pos = birthday.indexOf("-");
        DateTimeFormatter format = null;
        switch (pos) {
            case 0:
                return "-";
            case 2:
                // 23-07-1982
                format = DateTimeFormat.forPattern("dd-MM-yyyy");
                break;
            case 4:
                // 1990-01-07
                format = DateTimeFormat.forPattern("yyyy-MM-dd");
                break;
        }
        DateTime date = format.parseDateTime(birthday);
        return String.valueOf(Years.yearsBetween(new LocalDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth()), new LocalDate()).getYears());
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatr_url() {
        if (avatr_url != null) {
            if (!avatr_url.equals("")) {
                return avatr_url;
            } else {
                return "-";
            }
        }
        return "-";
    }

    public void setAvatr_url(String avatr_url) {
        this.avatr_url = avatr_url;
    }

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
