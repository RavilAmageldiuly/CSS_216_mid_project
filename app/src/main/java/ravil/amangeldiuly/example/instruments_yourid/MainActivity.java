package ravil.amangeldiuly.example.instruments_yourid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String languages[] = {"ru", "en"};
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button changeButton = findViewById(R.id.changeButton);
        LanManager lanManager = new LanManager(this);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanManager.update(spinner.getSelectedItem().toString());
                recreate();
            }
        });

        EditText messageTextView = findViewById(R.id.repeatingText);

        Button repeatButton = findViewById(R.id.repeatButton);
        repeatButton.setOnClickListener(view -> {
            Intent i = new Intent(this, ParrotActivity.class);

            String message = messageTextView.getText().toString();
            i.putExtra("message", message);

            startActivity(i);
        });

        Button startStopwatchButton = findViewById(R.id.stopwatchStartButton);
        startStopwatchButton.setOnClickListener(view -> {
            Intent stopwatchIntent = new Intent(getBaseContext(), StopwatchActivity.class);
            startActivity(stopwatchIntent);
        });

        Button converterButton = findViewById(R.id.converterButton);
        converterButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ConverterActivity.class);
            startActivity(intent);
        });
    }
}