package com.example.tsdmh_7s5_proyecto_castaneda_antonio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnSolicitarDatos;
    private ProgressBar progressBar;
    private TextView tvCargandoDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnSolicitarDatos = findViewById(R.id.btnSolicitarDatos);
        progressBar = findViewById(R.id.progressBar);
        tvCargandoDatos = findViewById(R.id.tvCargandoDatos);

        btnSolicitarDatos.setOnClickListener(v -> iniciarCargaDeDatos());
    }

    private void iniciarCargaDeDatos() {
        btnSolicitarDatos.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        tvCargandoDatos.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(2500); // 2.5 segundos de carga
        animator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            progressBar.setProgress(progress);
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                btnSolicitarDatos.setEnabled(true);
                progressBar.setVisibility(View.GONE);
                tvCargandoDatos.setVisibility(View.GONE);

                Intent intent = new Intent(PrincipalActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        animator.start();
    }
}
