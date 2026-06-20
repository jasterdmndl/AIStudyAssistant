package com.example.aistudyassistant.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.aistudyassistant.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void setupBottomNavigation(int selectedItemId) {

        BottomNavigationView bottomNavigation =
                findViewById(R.id.bottomNavigation);

        if (bottomNavigation == null) {
            return;
        }

        bottomNavigation.setSelectedItemId(selectedItemId);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == selectedItemId) {
                return true;
            }

            Intent intent = null;

            if (id == R.id.nav_home) {

                intent = new Intent(
                        this,
                        DashboardActivity.class
                );

            } else if (id == R.id.nav_notes) {

                intent = new Intent(
                        this,
                        NotesActivity.class
                );

            } else if (id == R.id.nav_flashcards) {

                intent = new Intent(
                        this,
                        FlashcardsActivity.class
                );

            } else if (id == R.id.nav_planner) {

                intent = new Intent(
                        this,
                        StudyPlannerActivity.class
                );
            }

            if (intent != null) {

                startActivity(intent);

                overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                );

                finish();
            }

            return true;
        });
    }
}