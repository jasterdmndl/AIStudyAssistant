package com.example.aistudyassistant.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.example.aistudyassistant.R;

public class PomodoroActivity extends BaseActivity {

    private TextView txtTimer;
    private TextView txtSessions;

    private Button btnStart;
    private Button btnPause;
    private Button btnReset;

    private CountDownTimer countDownTimer;

    private long timeLeft = 1500000; // 25 mins

    private boolean isRunning = false;

    private int sessionCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        txtTimer = findViewById(R.id.txtTimer);
        txtSessions = findViewById(R.id.txtSessions);

        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);

        loadSessions();

        btnStart.setOnClickListener(v -> startTimer());

        btnPause.setOnClickListener(v -> pauseTimer());

        btnReset.setOnClickListener(v -> resetTimer());

        updateTimerText();
    }

    private void startTimer() {

        if(isRunning)
            return;

        countDownTimer =
                new CountDownTimer(timeLeft,1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        timeLeft = millisUntilFinished;

                        updateTimerText();
                    }

                    @Override
                    public void onFinish() {

                        sessionCount++;

                        saveSessions();

                        txtSessions.setText(
                                "Today's Sessions: " + sessionCount
                        );

                        resetTimer();
                    }
                };

        countDownTimer.start();

        isRunning = true;
    }

    private void pauseTimer() {

        if(countDownTimer != null) {

            countDownTimer.cancel();
        }

        isRunning = false;
    }

    private void resetTimer() {

        if(countDownTimer != null) {

            countDownTimer.cancel();
        }

        timeLeft = 1500000;

        isRunning = false;

        updateTimerText();
    }

    private void updateTimerText() {

        int minutes =
                (int)(timeLeft / 1000) / 60;

        int seconds =
                (int)(timeLeft / 1000) % 60;

        String time =
                String.format(
                        "%02d:%02d",
                        minutes,
                        seconds
                );

        txtTimer.setText(time);
    }

    private void saveSessions() {

        SharedPreferences prefs =
                getSharedPreferences(
                        "pomodoro",
                        MODE_PRIVATE
                );

        prefs.edit()
                .putInt(
                        "sessions",
                        sessionCount
                )
                .apply();
    }

    private void loadSessions() {

        SharedPreferences prefs =
                getSharedPreferences(
                        "pomodoro",
                        MODE_PRIVATE
                );

        sessionCount =
                prefs.getInt(
                        "sessions",
                        0
                );

        txtSessions.setText(
                "Today's Sessions: " +
                        sessionCount
        );
    }
}