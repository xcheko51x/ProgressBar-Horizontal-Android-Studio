package com.xcheko51x.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvTexto;
    ProgressBar progressBar;
    int progreso = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pbHorizontal);
        tvTexto = findViewById(R.id.tvTexto);

        // INICIA EL HILO CON LA ACCION DEL PROGRESS BAR
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progreso < 100) {
                    progreso = progreso + 1;

                    // ACTUALIZA EL PROGRESS BAR Y ACTUALIZA EL TEXT VIEW
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progreso);
                            if(progreso == 100) {
                                Toast.makeText(MainActivity.this, "SE TERMINO LA CARGA", Toast.LENGTH_LONG).show();
                            }

                            tvTexto.setText(progreso + " / " + progressBar.getMax());
                        }
                    });

                    try {
                        // DUERME EL HILO POR 200 MILISEGUNDOS
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
