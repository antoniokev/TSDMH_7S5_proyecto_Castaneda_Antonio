package com.example.tsdmh_7s5_proyecto_castaneda_antonio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private static final String ARG_RESULT_DATA = "result_data";

    public interface OnResetListener {
        void onResetRequested();
    }

    private OnResetListener resetListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(String resultData) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESULT_DATA, resultData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnResetListener) {
            resetListener = (OnResetListener) context;
        } else {
            throw new RuntimeException(context + " debe implementar OnResetListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView tvResultContent = view.findViewById(R.id.tvResultContent);
        Button btnNuevoProceso = view.findViewById(R.id.btnNuevoProceso);

        if (getArguments() != null) {
            String result = getArguments().getString(ARG_RESULT_DATA);
            tvResultContent.setText(result);
        }

        btnNuevoProceso.setOnClickListener(v -> {
            if (resetListener != null) {
                resetListener.onResetRequested();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        resetListener = null;
    }
}
