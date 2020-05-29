package com.bh183.sulastari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgKampus;
    private TextView tvNama, tvAlamat, tvCaption, tvTelepon, tvSejarah;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgKampus = findViewById(R.id.iv_kampus);
        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvCaption = findViewById(R.id.tv_caption);
        tvTelepon = findViewById(R.id.tv_telepon);
        tvSejarah = findViewById(R.id.tv_sejarah);

        Intent terimaData = getIntent();
        tvNama.setText(terimaData.getStringExtra("NAMA"));
        tvAlamat.setText(terimaData.getStringExtra("ALAMAT"));
        tvCaption.setText(terimaData.getStringExtra("CAPTION"));
        tvTelepon.setText(terimaData.getStringExtra("TELEPON"));
        tvSejarah.setText(terimaData.getStringExtra("SEJARAH"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgKampus.setImageBitmap(bitmap);
            imgKampus.setContentDescription(imgLocation);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }

        link = terimaData.getStringExtra("LINK");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tampil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_bagikan)
        {
            Intent bagikan = new Intent(Intent.ACTION_SEND);
            bagikan.putExtra(Intent.EXTRA_SUBJECT, tvNama.getText().toString());
            bagikan.putExtra(Intent.EXTRA_TEXT, link);
            bagikan.setType("text/plain");
            startActivity(Intent.createChooser(bagikan, "Bagikan"));
        }
        return super.onOptionsItemSelected(item);
    }
}


