package com.example.ppe_gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * permet d'ouvrir la page (l'activite xml) vers laquelle mene le bouton
     *
     * @param view
     */
    public void clic_bouton(View view) {

        if (view.getId() == R.id.btnF) {
            Intent intent = new Intent(getApplicationContext(), FraisAuForfait.class);
            startActivity(intent);
        }
/*
*
*
* *  if(view.getId() == R.id.btnFHF){
            Intent intent = new Intent(getApplicationContext(), FraisHorsForfait.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnS) {
            Intent intent = new Intent(getApplicationContext(), SyntheseDuMois.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnEnvoiDD) {
            Intent intent = new Intent(getApplicationContext(), EnvoiDesDonnees.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnParametres) {
            Intent intent = new Intent(getApplicationContext(), Parametres.class);
            startActivity(intent);
        }
 */


    }

}

