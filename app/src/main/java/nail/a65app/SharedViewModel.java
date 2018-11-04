package nail.a65app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> specialityName = new MutableLiveData<String>();
    private final MutableLiveData<Integer> id = new MutableLiveData<Integer>();

    public void setSpecialityNae(String s) {
        specialityName.setValue(s);
    }

    public LiveData<String> getSpecialityName() {
        return specialityName;
    }

    public void setId(int i) {
        id.setValue(i);
    }

    public LiveData<Integer> getId() {
        return id;
    }
}