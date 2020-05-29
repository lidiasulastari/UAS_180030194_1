package com.bh183.sulastari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Kampus> dataKampus = new ArrayList<>();
    private RecyclerView rvKampus;
    private KampusAdapter kampusAdapter;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvKampus = findViewById(R.id.rv_tampil_kampus);
        dbHandler = new DatabaseHandler(this);
        tampilDataKampus();
    }

    private void tampilDataKampus() {
        dataKampus = dbHandler.getAllKampus();
        kampusAdapter = new KampusAdapter(this, dataKampus);
        RecyclerView.LayoutManager layManager = new LinearLayoutManager(MainActivity.this);
        rvKampus.setLayoutManager(layManager);
        rvKampus.setAdapter(kampusAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.item_menu_tambah) {
            Intent bukaInput = new Intent(getApplicationContext(), InputActivity.class);
            bukaInput.putExtra("OPERASI", "insert");
            startActivity(bukaInput);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilDataKampus();
    }
}
