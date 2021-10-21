package ravil.amangeldiuly.example.instruments_yourid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> {
            finish();
        });

        ListView listView = findViewById(R.id.listView);
        List<String> contactList = new ArrayList<>();
        contactList.add("Ravil");
        contactList.add("Dina");
        contactList.add("Arsen");
        contactList.add("Nurgul");

        List<String> contactInfoList = new ArrayList<>();
        contactInfoList.add("male;Ravil;Amangeldiuly;87476955051");
        contactInfoList.add("female;Dina;Kengesbay;87005553535");
        contactInfoList.add("male;Arsen;Ulykbekov;87012654230");
        contactInfoList.add("female;Nurgul;Mazhit;87023654545");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, contactList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ContactInfoActivity.class);
                String info = contactInfoList.get(i);
                intent.putExtra("info", info);
                startActivity(intent);
            }
        });
    }
}