package com.bh183.sulastari;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "db_kampus";
    private final static String TABLE_KAMPUS = "t_kampus";
    private final static String KEY_ID_KAMPUS = "ID_Kampus";
    private final static String KEY_NAMA ="Nama";
    private final static String KEY_ALAMAT = "Alamat";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_CAPTION ="Caption";
    private final static String KEY_TELEPON = "Telepon";
    private final static String KEY_SEJARAH = "Sejarah";
    private final static String KEY_LINK = "Link";
    private Context context;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_KAMPUS = "CREATE TABLE " + TABLE_KAMPUS
                + "(" + KEY_ID_KAMPUS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAMA + " TEXT, " + KEY_ALAMAT + " TEXT, "
                + KEY_GAMBAR + " TEXT, " + KEY_CAPTION + " TEXT, "
                + KEY_TELEPON + " NUMBER, " + KEY_SEJARAH + " TEXT, "
                + KEY_LINK + " TEXT);";
        db.execSQL(CREATE_TABLE_KAMPUS);
        inisialisasiKampusAwal(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_KAMPUS;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahKampus(Kampus dataKampus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataKampus.getNama());
        cv.put(KEY_ALAMAT, dataKampus.getAlamat());
        cv.put(KEY_GAMBAR, dataKampus.getGambar());
        cv.put(KEY_CAPTION, dataKampus.getCaption());
        cv.put(KEY_TELEPON, dataKampus.getTelepon());
        cv.put(KEY_SEJARAH, dataKampus.getSejarah());
        cv.put(KEY_LINK, dataKampus.getLink());

        db.insert(TABLE_KAMPUS, null, cv);
        db.close();
    }

    public void tambahKampus(Kampus dataKampus, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataKampus.getNama());
        cv.put(KEY_ALAMAT, dataKampus.getAlamat());
        cv.put(KEY_GAMBAR, dataKampus.getGambar());
        cv.put(KEY_CAPTION, dataKampus.getCaption());
        cv.put(KEY_TELEPON, dataKampus.getTelepon());
        cv.put(KEY_SEJARAH, dataKampus.getSejarah());
        cv.put(KEY_LINK, dataKampus.getLink());

        db.insert(TABLE_KAMPUS, null, cv);
    }

    public void editKampus(Kampus dataKampus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, dataKampus.getNama());
        cv.put(KEY_ALAMAT, dataKampus.getAlamat());
        cv.put(KEY_GAMBAR, dataKampus.getGambar());
        cv.put(KEY_CAPTION, dataKampus.getCaption());
        cv.put(KEY_TELEPON, dataKampus.getTelepon());
        cv.put(KEY_SEJARAH, dataKampus.getSejarah());
        cv.put(KEY_LINK, dataKampus.getLink());

        db.update(TABLE_KAMPUS, cv, KEY_ID_KAMPUS + "=?", new String[]{String.valueOf(dataKampus.getIdKampus())});
        db.close();
    }

    public void hapusKampus(int idKampus) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_KAMPUS, KEY_ID_KAMPUS + "=?", new String[]{String.valueOf(idKampus)});
        db.close();
    }

    public ArrayList<Kampus> getAllKampus() {
        ArrayList<Kampus> dataKampus = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_KAMPUS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()) {
            do {
                Kampus tempKampus = new Kampus(
                  csr.getInt(0),
                  csr.getString(1),
                  csr.getString(2),
                  csr.getString(3),
                  csr.getString(4),
                  csr.getString(5),
                  csr.getString(6),
                  csr.getString(7)
                );

                dataKampus.add(tempKampus);
            }while (csr.moveToNext());
        }
        return dataKampus;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }
    private void inisialisasiKampusAwal(SQLiteDatabase db) {
        int idKampus = 0;

        //Menambah data Kampus ke-1

        Kampus kampus1 = new Kampus(
                idKampus,
                "ITB STIKOM Bali",
                "Jl. Raya Puputan No.86, Dangin Puri Klod, Kec. Denpasar Tim., Kota Denpasar, Bali 80234",
                storeImageFile(R.drawable.berita1),
                "Ruangan Rektor ITB STIKOM Bali",
                "0361 244445",
                "Berawal dari bertemunya para pemerhati, pencinta dan praktisi pendidikan yakni Prof. Dr. Made Bandem, MA.,  (saat itu Rektor ISI Jogjakarta), Dr. Dadang Hermawan (praktisi pendidikan), Drs. Ida Bagus Dharmadiaksa, M.Si., Ak. (Dosen) dan Drs. Satria Dharma (praktisi pendidikan) pada tahun 2.000 yang begitu menaruh perhatian atas pesat dan dinamisnya perkembangan teknologi informasi dan komunikasi (TIK) di dunia termasuk di Indonesia dan Bali, namun di lain pihak perguruan tinggi bidang TIK sampai dengan jenjang sarjana belum ada.\n" +
                        "\n" +
                        "\n" +
                        "Maka pada tanggal 20 Mei 2001, berdirilah Yayasan Widya Dharma Shanti yang  akan menjadi Badan Penyenggara Perguruan Tinggi Swasta dan selanjutnya diajukanlah ijin pendirian Institut Teknologi dan Bisnis (ITB) STIKOM Bali kepada Direktorat Jenderal Pendidikan Tinggi Kementerian Pendidikan Nasional.\n" +
                        "Dengan berbagai usaha dan lika liku pengurusan ijin serta doa, maka pada tanggal 10 Agustus 2002 keluarlah ijin dimaksud dengan nomor 157/D/O/2002 dengan 2 jurusan / program studi yakni Sistem Komputer (jenjang S1) dan Manajemen Informatika (jenjang D3) lalu pada tahun 2009 ada tambahan program studi baru yakni Sistem Infomasi (jenjang S1).\n" +
                        "\n" +
                        "\n" +
                        "Saat ini, Institut Teknologi dan Bisnis (ITB) STIKOM Bali sudah menjadi perguruan tinggi bertaraf internasional yang sangat dipercaya oleh masyarakat, terbukti dengan jumlah mahasiswa yang sedang kuliah tidak kurang dari 6.000 orang dan alumni yang mencapai hampir 4.000 orang (tahun 2015). Selain itu berbagai kerjasama dalam bidang tri dharma perguruan tinggi  telah dilakukan dengan berbagai pihak baik instansi pemerintah, instansi/perusahaan swasta, BUMN, BUMD, Perguruan Tinggi baik dalam maupun luar negeri.\n" +
                        "\n",
                "https://stikom-bali.ac.id/"
        );
        tambahKampus(kampus1, db);
        idKampus++;

        // Menambahkan data Kampus ke-2
        Kampus kampus2 = new Kampus(
                idKampus,
                "Universitas Udayana",
                "Kampus Bukit, Jl. Raya Kampus Unud Jimbaran, Kec. Kuta Sel., Kabupaten Badung, Bali 80361",
                storeImageFile(R.drawable.berita2),
                "Gedung Rektorat Universitas Udayana",
                "0361 701954",
                "Cikal bakal Unud adalah Fakultas Sastra Udayana cabang Universitas Airlangga yang diresmikan oleh P. J. M. Presiden Republik Indonesia Ir. Soekarno, dibuka oleh J. M. Menteri P.P dan K. Prof. DR. Priyono pada tanggal 29 September 1958 sebagaimana tertulis pada Prasasti di Fakultas Sastra Jalan Nias Denpasar.\n" +
                        "\n" +
                        "Universitas Udayana secara sah berdiri pada tanggal 17 Agustus 1962 dan merupakan perguruan tinggi negeri tertua di daerah Provinsi Bali. Sebelumnya, sejak tanggal 29 September 1958 di Bali sudah berdiri sebuah Fakultas yang bernama Fakultas Sastra Udayana sebagai cabang dari Universitas Airlangga Surabaya. Fakultas Sastra Udayana inilah yang merupakan embrio dari pada berdirinya Universitas Udayana. Berdasarkan Surat Keputusan Menteri PTIPNo.104/1962, tanggal 9 Agustus 1962, Universitas Udayana secara syah berdiri sejak tanggal 17 Agustus 1962. Tetapi oleh karena hari lahir Universitas Udayana jatuh bersamaan dengan hari Proklamasi Kemerdekaan RepublikIndonesia maka perayaan Hari Ulang Tahun Universitas Udayana dialihkan menjadi tanggal 29 September dengan mengambil tanggal peresmian Fakultas Sastra yang telah berdiri sejak tahun 1958.\n",
                "https://www.unud.ac.id/"
        );
        tambahKampus(kampus2, db);
        idKampus++;

        //Menambahkan data Kampus ke-3
        Kampus kampus3 = new Kampus(
                idKampus,
                "Universitas Warmadewa",
                "Jl. Terompong No.24, Sumerta Kelod, Kec. Denpasar Tim., Kota Denpasar, Bali 80239",
                storeImageFile(R.drawable.berita3),
                "Gedung Rektorat Universitas Warmadewaa",
                "0361 223858",
                "Pada Tanggal 12 November 1983 dalam acara Rapat Kerja Daerah Korpri Bali di Kertasabha Denpasar, Ketua Unit Korpri Universitas Udayana Drs. Putu Kuna Winaya mengusulkan pendirian Universitas Korpri, dengan prinsip dasar  ”biaya pendidikan terjangkau dan mutu terjamin” yang kemudian dikembangkan menjadi \"bermutu, berintegritas dan berwawasan lingkungan\". Usulan pendirian Universitas Korpri ini dimaksudkan sebagai upaya untuk menampung aspirasi masyarakat yang belum terakomodasi di Perguruan Tinggi Negeri. Setelah mendapat ijin Korpri Pusat, Ketua Pengurus Korpri Bali (Drs. Sembah Subhakti) dan Ketua Korpri Unit Universitas Udayana (Drs. Putu Kuna Winaya) sepakat untuk membentuk Universitas Korpri.\n" +
                        "\n" +
                        "Selanjutnya pada bulan Juni 1984 dilakukan penyusunan proposal pendirian Universitas Korpri dengan melibatkan unsur Korpri Universitas Udayana dan  Korpri Pemerintah Daerah, sekaligus menetapkan Badan Pendiri yang terdiri dari Drs. Sembah Subhakti, Drs. Putu Kuna Winaya, I Ketut Widjana,SH, dan I Wayan Waya, SH.\n" +
                        "\n" +
                        "Tanggal 17 Juli 1984 , Universitas Warmadewa  resmi didirikan dan. kepengurusan yayasan ditetapkan susunannya pada Rapat Korpri Bali tanggal 30 Juli 1984. Setelah kelengkapan Badan-Badan Yayasan terbentuk, selanjutnya dilaporkan kepada Kopertis Wilayah VIII , bahwa Universitas Korpri telah berdiri dengan nama Universitas Warmadewa. Nama Universitas Warmadewa diberikan oleh Gubernur Bali  yang waktu itu dijabat Prof. Dr. Ida Bagus Mantra, sebagai bentuk apresiasi terhadap  Raja Bali zaman sebelum Majapahit dari  Dinasti Warmadewa .\n" +
                        "\n" +
                        "Pada tanggal 15 Agustus 1984 Prof. dr. I Gusti Agung Gde Puthra ditetapkan sebagai  Rektor Pertama Universitas Warmadewa.  Perkuliahan perdana dilakukan dihalaman Kampus Unud pada tanggal 17 September 1984 yang sampai sekarang diperingati sebagai hari lahirnya Universitas Warmadewa.\n",
                "https://www.warmadewa.ac.id/"
        );
        tambahKampus(kampus3, db);
    }
}
