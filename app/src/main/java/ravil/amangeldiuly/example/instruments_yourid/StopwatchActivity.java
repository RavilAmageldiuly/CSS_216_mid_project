package ravil.amangeldiuly.example.instruments_yourid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean isRunning = false;
    TextView timeTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        timeTextView = findViewById(R.id.timeTextView);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            runnable.run();
        }

        ImageButton stopwatchBackButton = findViewById(R.id.stopwatchBackButton);
        stopwatchBackButton.setOnClickListener(view -> {
            finish();
        });

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;
            timeTextView.setText("");
            @SuppressLint("DefaultLocale") String timeString = String.format("%02d:%02d:%02d", hours, minutes, secs);
            timeTextView.setText(timeString);
            if (isRunning) {
                seconds++;
            }
            handler.postDelayed(this, 1000);
        }
    };

    public void startStopwatch(View view) {
        if (!isRunning) {
            isRunning = true;
            runnable.run();
        }
    }

    public void stopStopwatch(View view) {
        isRunning = false;
        handler.removeCallbacks(runnable);
    }

    @SuppressLint("SetTextI18n")
    public void resetStopwatch(View view) {
        isRunning = false;
        seconds = 0;
        timeTextView.setText("00:00:00");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
        super.onSaveInstanceState(savedInstanceState);
    }
}




