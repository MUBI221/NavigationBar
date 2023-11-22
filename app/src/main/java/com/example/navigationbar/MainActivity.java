package com.example.navigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.navigationbar.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot ());

        frameLayout = findViewById (R.id.frameLayout);

        loadFragment(new HomeFragment());
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getTitle().toString()){
                case "Home":
                    loadFragment(new HomeFragment());
                    return true;
                case "Search":
                    loadFragment(new SearchFragment());
                    return true;
                case "Notification":
                    loadFragment(new NotificationFragment());
                    return true;
                case "Profile":
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return true;
        });

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,fragment).commit();
    }

    public static void createModal(Context context,int delay, String msg, String type){
        Dialog dialog = new Dialog (context);
        dialog.setContentView (R.layout.dialog_custom);
        dialog.getWindow ().setLayout (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView message;
        ImageView icon;
        ProgressBar loader;
        message = dialog.findViewById (R.id.message);
        icon = dialog.findViewById (R.id.icon);
        loader = dialog.findViewById (R.id.loader);
        message.setText (msg);
        dialog.setCancelable (false);
        dialog.setCanceledOnTouchOutside (false);
        dialog.show ();
        new Handler ().postDelayed (new Runnable () {
            @Override
            public void run() {
                dialog.dismiss ();
            }
        },delay);
        if(type.equals ("loading")){

        } else if(type.equals ("success")){
            loader.setVisibility (View.GONE);
            icon.setVisibility (View.VISIBLE);
        } else if(type.equals ("cancel")){
            loader.setVisibility (View.GONE);
            icon.setVisibility (View.VISIBLE);
            icon.setImageResource (R.drawable.cancel);
        }
    }

}