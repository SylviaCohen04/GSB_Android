package com.example.ppe_gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FraisAuForfait extends MainActivity{

    //Proprietes
    SQLHelper BDD;
    Spinner ListeFrais;//liste deroulante des frais au forfait
    EditText Quantite;
    EditText Montant;
    TextView Date;
    DatePickerDialog Picker;
    //recupere la date actuelle et met dans une variable
    Calendar dateActuelle = Calendar.getInstance();
    int aaaa = dateActuelle.get(Calendar.YEAR);
    int mm = dateActuelle.get(Calendar.MONTH);
    int jj = dateActuelle.get(Calendar.DAY_OF_MONTH);

    //Tableau montant des frais au forfait de type double
    Double montantFrais[] = new Double[]{110.00, 0.62, 80.00, 25.00};
    //pas la peine donc de declarer un tel tableau String typeFrais[] = new String []{Forfaitetape, FraisKilométrique, NuitéeHôtel, RepasRestaurant};
    Double tarif;

    @SuppressLint("WrongViewCast")// C pr supprimer un frais
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);

        BDD = new SQLHelper(this);
        ListeFrais = findViewById(R.id.typeFrais);
        Quantite = findViewById(R.id.quantite);
        Date = findViewById(R.id.dateFrais);
        //ajouter date saisie
    }

    /*
     * Methode declenchee par le clic de la souris sur le bouton et realise des traitements en fonction
     * du bouton en question.
     * Clic sur bouton ajouter : recupere le frais selectionne, la quantite et la date saisies, puis les
     *  enregistre dans la BDD.
     * clic sur bouton retour : ferme la fenetre des frais au forfait et retourne au menu
     *
     * @param view
     */
    public void ajouterDonnees(View view) {

                if (ListeFrais.getSelectedItem().toString().trim().length() == 0) {
                    afficherMessage("ERREUR", "aucun type de frais sélectionné");

                } else if (Quantite.getText().toString().trim().length() == 0) {//.getText().toString().trim().length()
                    afficherMessage("ERREUR", "aucune quantité saisie");

                } else if (Date.getText().toString().trim().length() == 0) {
                    afficherMessage("ERREUR", "aucune date sélectionnée");

                } else {
                    String Libelle1 = ListeFrais.getSelectedItem().toString();
                    Integer Quantite1 = Integer.parseInt(Quantite.getText().toString());
                    String DateFrais1 = Date.getText().toString();


                    int position = ListeFrais.getSelectedItemPosition();//on récupère le type de
                    // frais du spinner et sa position(du xml) grâce à cette méthode
                    //LocalDateTime DateactuF = LocalDateTime.now() ;
                    tarif = Quantite1 * montantFrais[position]; //calcul du montant total
                    if (BDD.insertData(Libelle1, Quantite1, tarif, DateFrais1)) {
                        Quantite.setText("");
                        Date.setText("");
                        ListeFrais.setSelection(0);
                        Toast.makeText(FraisAuForfait.this, "Frais enregistré", Toast.LENGTH_LONG).show();

                    }

                }


        }



    public void clic_retour(View view) {
        switch (view.getId()) {
            case R.id.btnRetour:
                finish();
        }
    }

    public void supprimerFrais(View view){

    }
    /**
     * cree un calendrier dans lequel le visiteur selectionnera la date de ses frais (date actuelle
     * par defaut)
     *
     * @param view
     */
    public void ShowCal(View view) {
        Picker = new DatePickerDialog(FraisAuForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, aaaa, mm, jj);
        Picker.show();
    }
}