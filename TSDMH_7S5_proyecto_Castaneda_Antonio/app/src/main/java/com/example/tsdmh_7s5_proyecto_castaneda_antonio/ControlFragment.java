package com.example.tsdmh_7s5_proyecto_castaneda_antonio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    public interface OnDataProcessedListener {
        void onDataProcessed(String data);
    }

    private OnDataProcessedListener listener;

    public ControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDataProcessedListener) {
            listener = (OnDataProcessedListener) context;
        } else {
            throw new RuntimeException(context + " debe implementar OnDataProcessedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        EditText etInputData = view.findViewById(R.id.etInputData);
        Button btnProcesar = view.findViewById(R.id.btnProcesar);

        btnProcesar.setOnClickListener(v -> {
            String input = etInputData.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.error_empty_input), Toast.LENGTH_SHORT).show();
            } else {
                if (listener != null) {
                    listener.onDataProcessed(input);
                }
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
