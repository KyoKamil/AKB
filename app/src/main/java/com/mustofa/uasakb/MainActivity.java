/** 15-08-2019 10116593-Mustofa Kamil-IF13  */
package com.mustofa.uasakb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Deklarasi dan Menginisialisasi variable nama dengan Label Nama dari Layout MainActivity */
        TextView nama = findViewById(R.id.tampilnama);

        /* Men-set Label Nama dengan data User sedang login dari Preferences */
        nama.setText(Preferences.getLoggedInUser(getBaseContext()));

        /* Men-set Status dan User yang sedang login menjadi default atau kosong di
         * Data Preferences. Kemudian menuju ke LoginActivity*/
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke Login Activity
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }

}
