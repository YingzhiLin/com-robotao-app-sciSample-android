package com.robotao.app.sampling.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import com.robotao.app.sampling.R;
import com.robotao.app.sampling.databinding.ActivityTaskBinding;

public class TaskActivity extends AppCompatActivity {

    ActivityTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                // TODO
                return true;

            case R.id.action_check_for_update:
                // TODO

                return true;

            case R.id.action_about_us:
                // TODO
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}