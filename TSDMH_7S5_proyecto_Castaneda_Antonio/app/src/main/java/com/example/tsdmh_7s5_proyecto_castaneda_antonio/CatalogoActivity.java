package com.example.tsdmh_7s5_proyecto_castaneda_antonio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CatalogoActivity extends Activity {

    // Clase modelo para representar cada categoría/opción
    public static class Categoria {
        private final String nombre;
        private final String icono;
        private final int progreso;

        public Categoria(String nombre, String icono, int progreso) {
            this.nombre = nombre;
            this.icono = icono;
            this.progreso = progreso;
        }

        public String getNombre() {
            return nombre;
        }

        public String getIcono() {
            return icono;
        }

        public int getProgreso() {
            return progreso;
        }

        @NonNull
        @Override
        public String toString() {
            if (icono == null || icono.isEmpty()) {
                return nombre;
            }
            return icono + "  " + nombre;
        }
    }

    Spinner spinnerCategorias;
    LinearLayout contenedorProgreso;

    TextView txtTitulo;
    TextView txtPorcentaje;
    ProgressBar barraProgreso;

    // ArrayList que almacena las opciones del catálogo
    ArrayList<Categoria> listaCategorias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        contenedorProgreso = findViewById(R.id.contenedorProgreso);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        barraProgreso = findViewById(R.id.barraProgreso);

        cargarDatos();
        configurarSpinner();
    }

    private void cargarDatos() {
        listaCategorias.clear();
        listaCategorias.add(new Categoria("-- Seleccione una categoría --", "", -1));
        listaCategorias.add(new Categoria("Base de datos", "🗄️", 80));
        listaCategorias.add(new Categoria("Red", "🌐", 60));
        listaCategorias.add(new Categoria("Sincronización", "🔄", 40));
        listaCategorias.add(new Categoria("Reportes", "📊", 25));
    }

    private void configurarSpinner() {
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item_categoria,
                listaCategorias
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_categoria);

        spinnerCategorias.setAdapter(adapter);

        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    mostrarProgreso(position);
                } else {
                    contenedorProgreso.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                contenedorProgreso.setVisibility(View.GONE);
            }
        });
    }

    private void mostrarProgreso(int posicion) {
        Categoria item = listaCategorias.get(posicion);

        // MOSTRAR CONTENEDOR
        contenedorProgreso.setVisibility(View.VISIBLE);

        // CAMBIAR INFORMACION
        txtTitulo.setText(item.getNombre());

        txtPorcentaje.setText(
                "Progreso: " + item.getProgreso() + "%"
        );

        // ACTUALIZAR BARRA
        barraProgreso.setProgress(item.getProgreso());
    }
}
