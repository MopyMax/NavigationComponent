package com.example.navigationcomponent;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class configurarFragment extends Fragment
        implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gLibrary;
    private View viewFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configurar, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        viewFragment = view;
        gestureSetup(viewFragment);
    }

    private void gestureSetup(View view){
        gLibrary = GestureLibraries.fromRawResource(getActivity(), R.raw.gestures); //Falta agregar el archivo gestures en la carpeta Raw
                if (!gLibrary.load()){
                    //finish();
                }
                GestureOverlayView gOverlay = view.findViewById(R.id.gestures);
                gOverlay.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
        ArrayList<Prediction> predictions =gLibrary.recognize(gesture);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String action = predictions.get(0).name;
            Log.i("Gesture", action);

            if (action.equals("configurar")) {
                Toast.makeText(getActivity(),
                        "Configurar gesture", Toast.LENGTH_LONG).show();
                Navigation.findNavController(viewFragment).navigate(R.id.inicioFragment3);
            }

            }
        }
    }
