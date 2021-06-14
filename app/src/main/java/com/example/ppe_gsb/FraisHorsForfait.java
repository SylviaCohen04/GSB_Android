package com.example.ppe_gsb;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import android.os.Bundle;

public class FraisHorsForfait extends MainActivity {

    TextView aaa;
    SQLHelper BDD;
    TextView Date;
    EditText Libelle;
    EditText Montant;
    DatePickerDialog Picker;
    Calendar dateActuelle = Calendar.getInstance();
    int aaaa = dateActuelle.get(Calendar.YEAR);
    int mm = dateActuelle.get(Calendar.MONTH);
    int jj = dateActuelle.get(Calendar.DAY_OF_MONTH);


    /**
     * @param savedInstanceState est appelée au lancement de l'activité (l'equivalent du constructeur)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hors_forfait);
        BDD = new SQLHelper(this);
        Libelle = findViewById(R.id.libelle);
        Montant = findViewById(R.id.montant);
        Date = findViewById(R.id.dateFHF);
    }


    /**
     * @param view rentre les donnees du formulaire dans la bdd grace a la methode insertData()
     */
    public void save_DATAHF(View view) {

        if (Libelle.getText().toString().trim().length() == 0) { // si rien dans txtQTE
            //Toast.makeText(this, "ERREUR, aucun libellé saisi", Toast.LENGTH_LONG).show();
            afficherMessage("ERREUR", "aucun libellé saisi");

        } else if (Montant.getText().toString().trim().length() == 0) {//c juste?
            afficherMessage("ERREUR", "aucun montant saisi");

        } else if (Date.getText().toString().trim().length() == 0) {
            afficherMessage("ERREUR", "aucune date sélectionnée");

        } else {
            String Libelle1 = Libelle.getText().toString();
            Double Montant1 = Double.parseDouble(Montant.getText().toString());
            String Date1 = Date.getText().toString();


            if (BDD.insertData(Libelle1, 0, Montant1, Date1)) {
                Libelle.setText("");
                Montant.setText("");
                Date.setText("");
                //Toast.makeText() => message qui s'affiche, ici "Frais enregistré", et ainsi le
                // visiteur peut passer à la saisie de la ligne suivante
                Toast.makeText(FraisHorsForfait.this, "Frais enregistré", Toast.LENGTH_LONG).show();

            }
        }

    }

    /**
     * ferme la page FraisAuForfait et permet de retourner au menu
     *
     * @param view
     */
    public void clic_Bouton(View view) {
        if (view.getId() == R.id.btnRFHF) {
            finish();
        }
    }

    /**
     * cree un calendrier dans lequel le visiteur selectionnera la date de ses frais (date actuelle*
     * par defaut)
     *
     * @param view
     */
    public void ShowCalFHF(View view) {
        Picker = new DatePickerDialog(FraisHorsForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, aaaa, mm, jj);
        Picker.show();
    }

}