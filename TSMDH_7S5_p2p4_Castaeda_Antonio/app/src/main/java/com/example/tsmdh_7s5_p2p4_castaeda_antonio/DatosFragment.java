package com.example.tsmdh_7s5_p2p4_castaeda_antonio;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DatosFragment extends Fragment {

    public DatosFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etNombre = view.findViewById(R.id.etNombre);
        EditText etEdad = view.findViewById(R.id.etEdad);
        EditText etTelefono = view.findViewById(R.id.etTelefono);
        Button btnGuardarDatos = view.findViewById(R.id.btnGuardarDatos);

        btnGuardarDatos.setOnClickListener(v -> {
            String nombre = etNombre.getText() != null ? etNombre.getText().toString().trim() : "";
            String edad = etEdad.getText() != null ? etEdad.getText().toString().trim() : "";
            String telefono = etTelefono.getText() != null ? etTelefono.getText().toString().trim() : "";

            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(edad) || TextUtils.isEmpty(telefono)) {
                Toast.makeText(requireContext(), R.string.msg_campos_vacios, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), R.string.msg_datos_guardados, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
