package nail.a65app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nail.a65app.R;
import nail.a65app.ui.specialty.SpecialityFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            SpecialityFragment firstFragment = new SpecialityFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, firstFragment).commit();
        }
    }
}

