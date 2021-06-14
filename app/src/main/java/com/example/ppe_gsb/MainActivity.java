package com.example.ppe_gsb;

import androidx.appcompat.app.AlertDialog;
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

        if (view.getId() == R.id.btnFHF) {
            Intent intent = new Intent(getApplicationContext(), FraisHorsForfait.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnS) {
            Intent intent = new Intent(getApplicationContext(), SyntheseDuMois.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.Visi) {
            Intent intent = new Intent(getApplicationContext(), InscriptionVisiteur.class);
            startActivity(intent);
        }
    }

    /**
     * affiche message d'erreur et desactive le bouton "ajouter"
     *
     * @param titre
     * @param message
     */
    public void afficherMessage(String titre, String message) {
        AlertDialog.Builder Builder = new AlertDialog.Builder(this);
        Builder.setCancelable(true);
        Builder.setTitle(titre);
        Builder.setMessage(message);
        Builder.show();
    }

}

