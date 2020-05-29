package com.bh183.sulastari;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class KampusAdapter extends RecyclerView.Adapter<KampusAdapter.KampusViewHolder> {

    private Context context;
    private ArrayList<Kampus> dataKampus;

    public KampusAdapter(Context context, ArrayList<Kampus> dataKampus) {
        this.context = context;
        this.dataKampus = dataKampus;
    }

    @NonNull
    @Override
    public KampusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_kampus, parent, false);
        return new KampusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KampusViewHolder holder, int position) {
        Kampus tempKampus = dataKampus.get(position);
        holder.idKampus = tempKampus.getIdKampus();
        holder.tvNama.setText(tempKampus.getNama());
        holder.tvHeadline.setText(tempKampus.getSejarah());
        holder.alamat = tempKampus.getAlamat();
        holder.gambar = tempKampus.getGambar();
        holder.caption = tempKampus.getCaption();
        holder.telepon = tempKampus.getTelepon();
        holder.link = tempKampus.getLink();

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgKampus.setImageBitmap(bitmap);
            holder.imgKampus.setContentDescription(holder.gambar);
        } catch (FileNotFoundException er) {
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataKampus.size();
    }

    public class KampusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgKampus;
        private TextView tvNama, tvHeadline;
        private int idKampus;
        private String alamat, gambar, caption, telepon, sejarah, link;


        public KampusViewHolder(@NonNull View itemView) {
            super(itemView);

            imgKampus = itemView.findViewById(R.id.iv_kampus);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvHeadline = itemView.findViewById(R.id.tv_headline);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaKampus = new Intent(context, TampilActivity.class);
            bukaKampus.putExtra("ID", idKampus);
            bukaKampus.putExtra("NAMA", tvNama.getText().toString());
            bukaKampus.putExtra("ALAMAT", alamat);
            bukaKampus.putExtra("GAMBAR", gambar);
            bukaKampus.putExtra("CAPTION", caption);
            bukaKampus.putExtra("TELEPON", telepon);
            bukaKampus.putExtra("SEJARAH", tvHeadline.getText().toString());
            bukaKampus.putExtra("LINK", link);
            context.startActivity(bukaKampus);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idKampus);
            bukaInput.putExtra("NAMA", tvNama.getText().toString());
            bukaInput.putExtra("ALAMAT", alamat);
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("CAPTION", caption);
            bukaInput.putExtra("TELEPON", telepon);
            bukaInput.putExtra("SEJARAH", tvHeadline.getText().toString());
            bukaInput.putExtra("LINK", link);
            context.startActivity(bukaInput);
            return true;
        }
    }
}