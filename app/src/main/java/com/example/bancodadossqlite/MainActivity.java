package com.example.bancodadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("appTeste", MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");
            //bancoDados.execSQL("DROP TABLE pessoas");

            //inserir dados
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Camila', 40)");
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES('Simone', 48)");


            //bancoDados.execSQL("UPDATE pessoas SET idade = 38 WHERE nome = 'Edson'");
            //bancoDados.execSQL("DELETE FROM pessoas WHERE id = 3");

            String consulta = "SELECT id, nome, idade FROM pessoas";

            //recuperar pessoas
            Cursor cursor = bancoDados.rawQuery( consulta , null);

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(bancoDados != null){

                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("Resultado - id ", id + "/ Nome " + nome + "/ idade: " + idade);
                cursor.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}