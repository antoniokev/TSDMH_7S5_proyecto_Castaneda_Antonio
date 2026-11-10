package com.example.tsdmh_7s5_prac3_par2_castaedaantonio;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrincipalActivity extends AppCompatActivity {
    TextView tvEstado, tvPorcentaje;
    ProgressBar progressBar;
    Button btnIniciar, btnCancelar;
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    final Handler handler = new Handler(Looper.getMainLooper());
    Future<?> tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        View mainView = findViewById(R.id.main);
        final int paddingLeft = mainView.getPaddingLeft();
        final int paddingTop = mainView.getPaddingTop();
        final int paddingRight = mainView.getPaddingRight();
        final int paddingBottom = mainView.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left + paddingLeft,
                    systemBars.top + paddingTop,
                    systemBars.right + paddingRight,
                    systemBars.bottom + paddingBottom
            );
            return insets;
        });

        tvEstado = findViewById(R.id.tvEstado);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        progressBar = findViewById(R.id.progressBar);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnIniciar.setOnClickListener(v -> IniciarTarea(100));
        btnCancelar.setOnClickListener(v -> {
            if (tarea != null && !tarea.isDone()) {
                tarea.cancel(true);
            }
        });
    }

    // Equivale a onPreExecute
    private void IniciarTarea(int pasos) {
        tvEstado.setText(R.string.iniciando_tarea);
        btnIniciar.setEnabled(false);
        btnCancelar.setEnabled(true);
        tvPorcentaje.setText(R.string.cero_porciento);
        progressBar.setProgress(0);

        // Realizamos lo equivalente a doInBackground
        tarea = executor.submit(() -> {
            for (int i = 0; i <= pasos; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    handler.post(this::alCancelar);
                    return;
                }
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    handler.post(this::alCancelar);
                    return;
                }
                // Realizamos lo equivalente a onProgressUpdate + publishProgress
                final int progreso = (i * 100) / pasos;
                handler.post(() -> {
                    tvPorcentaje.setText(getString(R.string.progreso_formato, progreso));
                    progressBar.setProgress(progreso);
                });
            }
            // Realizamos lo equivalente a onPostExecute
            handler.post(() -> alFinalizar(getString(R.string.tarea_finalizada)));
        });
    }

    private void alCancelar() {
        tvEstado.setText(R.string.tarea_cancelada);
        tvPorcentaje.setText(R.string.cero_porciento);
        progressBar.setProgress(0);
        btnIniciar.setEnabled(true);
        btnCancelar.setEnabled(false);
    }

    private void alFinalizar(String resultado) {
        tvEstado.setText(resultado);
        tvPorcentaje.setText(R.string.cien_porciento);
        progressBar.setProgress(100);
        btnIniciar.setEnabled(true);
        btnCancelar.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tarea != null && !tarea.isDone()) {
            tarea.cancel(true);
        }
        handler.removeCallbacksAndMessages(null);
        executor.shutdownNow();
    }
}
