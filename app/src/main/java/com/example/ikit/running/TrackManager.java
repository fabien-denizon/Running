package com.example.ikit.running;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        final ArrayList<String> listTrack = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter;

        TextView textView = findViewById(R.id.track_layout_text_view);

        //get the directory where the races are stored
        File dir = getApplicationContext().getDir(Constantes.DIR_NAME_TRACK, MODE_PRIVATE);
        final File[] listeFile = dir.listFiles();

        //build the adapter for the listview
        if(listeFile.length >0){
            for(i = 0; i< listeFile.length; i++){
                listTrack.add(listeFile[i].getName().toString());
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTrack);

            //add the adapter to the listview
            listView.setAdapter(arrayAdapter);

            //add onclicklistener to the listview
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //call the method with the name clicked
                    startDisplayRace(listTrack.get(position).toString());
                }
            });
        }else{
            textView.setText("Erreur aucun fichier trouvé");
        }
    }

    //start the DisplayRace passing the nameFile in parameter to display
    public void startDisplayRace(String nameFile){
        Intent intent = new Intent(this, DisplayRace.class);
        Bundle bundle = new Bundle();
        bundle.putString("race", nameFile);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
