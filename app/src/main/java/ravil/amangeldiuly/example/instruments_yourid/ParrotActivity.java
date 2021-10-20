package ravil.amangeldiuly.example.instruments_yourid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ParrotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parrot);

        Intent intent = getIntent();
        String message = intent.getExtras().getString("message", "");
        TextView repeatTextView = findViewById(R.id.repeatedMessage);

        repeatTextView.setText(message);

        ImageButton backButton = findViewById(R.id.imageButton2);
        backButton.setOnClickListener(view -> {
            finish();
        });
    }
}