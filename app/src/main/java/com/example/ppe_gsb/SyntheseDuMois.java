package com.example.ppe_gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class SyntheseDuMois extends AppCompatActivity {

    //Proprietes
    private SQLHelper BDD;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese_du_mois);

        BDD = new SQLHelper(this);
        BDD.open();

        //Générer le ListView a partir de SQLite Database
        displayListView();
    }

    private void displayListView() {

        Cursor pointeur = BDD.fetchAllFrais();


        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                SQLHelper.ID,
                SQLHelper.Libelle,
                SQLHelper.Quantite,
                SQLHelper.Montant,
                SQLHelper.DateFrais,
                SQLHelper.DateSaisie
        };


        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.id,
                R.id.libelle,
                R.id.quantite,
                R.id.montant,
                R.id.date,
                R.id.dateSaisie
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.frais_info,
                pointeur,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // des qu'on clique (avec le curseur) sur un element de la liste, un message
                // s'affiche avec la capitale
                String monId =
                        cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(),
                        monId, Toast.LENGTH_SHORT).show();
                Integer monId1 = Integer.parseInt(monId.toString());
                BDD.deleteData (monId1);
            }
        });


        // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats


        // des qu'on clique (avec le curseur) sur un element de la liste, un message
        // s'affiche avec la capitale
        // String typefrais =
        //        pointeur.getString(pointeur.getColumnIndexOrThrow("typeFrais"));
        //  Toast.makeText(getApplicationContext(),
        //      typefrais, Toast.LENGTH_SHORT).show();



    }

    /*
    Permet de fermer cette fenetre et de retourner au menu
    @param View
     */
    public void clic_btnRS(View view) {
        finish();

    }

    }