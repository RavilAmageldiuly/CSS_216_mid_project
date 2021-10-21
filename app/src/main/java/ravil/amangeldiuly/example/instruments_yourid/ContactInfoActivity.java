package ravil.amangeldiuly.example.instruments_yourid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent intent = getIntent();
        String info = intent.getExtras().getString("info");
        String[] infoString = info.split(";");

        ImageView gender = findViewById(R.id.genderIconImage);
        gender.setImageBitmap(null);

        switch(infoString[0]) {
            case "male":
                gender.setBackgroundResource(R.drawable.male);
                break;
            case "female":
                gender.setBackgroundResource(R.drawable.female);
                break;
        }

        TextView name = findViewById(R.id.nameTextView);
        TextView surname = findViewById(R.id.surnameTextView);
        TextView phone = findViewById(R.id.phoneTextView);

        name.setText(infoString[1]);
        surname.setText(infoString[2]);
        phone.setText(infoString[3]);
    }
}