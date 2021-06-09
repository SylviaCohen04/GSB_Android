package com.example.ppe_gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import static android.content.ContentValues.TAG;

public class SQLHelper extends SQLiteOpenHelper {

    //Proprietes
    private static final String DB_NAME = "GSB.db";
    private static final String DB_TABLE = "Users_Tablefrais";//nom de la table, necessaire pour
    // l'appeler dans les methodes
    private static final String CREATE_TABLE = "CREATE TABLE Users_Tablefrais (ID INTEGER PRIMARY " +
            "KEY AUTOINCREMENT, Libelle TEXT, Quantite Integer, Montant Float, " +
            "DateFrais TEXT, DateSaisie DATETIME DEFAULT CURRENT_TIMESTAMP)";


    public static final String ID = "ID";
    public static final String Libelle = "Libelle";
    public static final String Quantite = "Quantite";
    public static final String Montant = "Montant";
    public static final String DateFrais = "DateFrais";
    public static final String DateSaisie = "DateSaisie";

    private SQLHelper maBDDHelper;
    private SQLiteDatabase maBDD;
    private final Context monContexte;

    /*
     * Constructeur
     * @param Context context
     */
    public SQLHelper(Context context) {


        super(context, DB_NAME, null, 1);
        this.monContexte = context;
    }

    /*
     *methode onCreate()
     * @param SQLiteDatabase sqLiteDatabase
     * permet d'accéder à la bdd gsb non encore creee
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);// exécuter une req sql
        Log.w(TAG, CREATE_TABLE);
    }

    /*
     *
     * @param SQLiteDatabase sqLiteDatabase
     */
    //si la version de la bdd évolue, cette méthode permettra de mettre à jour
    //le schéma de bdd existant ou de supprimer la bdd existante
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * @param Quantite1
     * @param Libelle1
     * @param Montant1
     * @param DateFrais1
     * @return
     *
     * String typeFrais1, Integer quantite1, String date1, double montant1, String libelle1)
     */
    public boolean insertData(String Libelle1, Integer Quantite1, Double Montant1,
                              String DateFrais1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Libelle", Libelle1);
        contentValues.put("Quantite", Quantite1);
        contentValues.put("Montant", Montant1);
        contentValues.put("DateFrais", DateFrais1);

        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    /*
     *@param ID de type Integer
     * permet de supprimer une ligne
     */
    public boolean deleteData(Integer ID1) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID1, null);

        return result != -1;

    }

    public SQLHelper open() throws SQLException {
        maBDDHelper = new SQLHelper(monContexte);
        maBDD = maBDDHelper.getWritableDatabase();
        return this;
    }

    /*

     */
    public void close() {
        if (maBDDHelper != null) {
            maBDDHelper.close();
        }
    }

    public Cursor fetchAllFrais() {

        Cursor curseur = maBDD.query(DB_TABLE, new String[]{"rowid _id", ID, Libelle,
                        Quantite, Montant, DateFrais, DateSaisie},
                null, null, null, null, null, null);

        if (curseur != null) {
            curseur.moveToFirst();
        }
        return curseur;
    }

}

