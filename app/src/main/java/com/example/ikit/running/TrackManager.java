package com.example.ikit.running;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class TrackManager extends AppCompatActivity{

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_layout);
        displayTracks();
    }

    public void displayTracks(){
        int i;
        listView = findViewById(R.id.track_list_view);
        ArrayList<String> listTrack = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter;
        TextView textView = findViewById(R.id.track_layout_text_view);
        File dir = getApplicationContext().getDir(Constantes.DIR_NAME_TRACK, MODE_PRIVATE);
        File[] listeFile = dir.listFiles();
        if(listeFile.length >0){
            for(i = 0; i< listeFile.length; i++){
                listTrack.add(listeFile[i].getName().toString());
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTrack);
            listView.setAdapter(arrayAdapter);
        }else{
            textView.setText("Erreur aucun fichier trouvé");
        }
    }
}
