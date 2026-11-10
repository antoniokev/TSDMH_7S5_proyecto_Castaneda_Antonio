package com.example.tsmdh_7s5_p2p4_castaeda_antonio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principalContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnVerImagen = findViewById(R.id.btnVerImagen);
        Button btnCapturaDatos = findViewById(R.id.btnCapturaDatos);

        btnVerImagen.setOnClickListener(v ->
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerPrincipal, new ImagenFragment())
                        .commit()
        );

        btnCapturaDatos.setOnClickListener(v -> {
            Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
