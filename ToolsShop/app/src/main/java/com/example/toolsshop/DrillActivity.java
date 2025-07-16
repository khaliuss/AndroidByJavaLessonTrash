package com.example.toolsshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DrillActivity extends AppCompatActivity {

    ListView listViewDrills;
    ArrayList<Drill> drillArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill);

        listViewDrills = findViewById(R.id.drillListView);
        drillArrayList = new ArrayList<>();

        drillArrayList.add(new Drill(getString(R.string.makita_title), getString(R.string.makita_description), R.drawable.makita_icon));
        drillArrayList.add(new Drill(getString(R.string.interskol_title), getString(R.string.interskol_description), R.drawable.interskol_icon));
        drillArrayList.add(new Drill(getString(R.string.dewalt_title), getString(R.string.dewalt_description), R.drawable.dewalt_icon));

        ArrayAdapter<Drill> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, drillArrayList);
        listViewDrills.setAdapter(adapter);

        listViewDrills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drill drill = drillArrayList.get(position);
                Intent intent = new Intent(getApplicationContext(), DrillDetailActivity.class);
                intent.putExtra("title", drill.getTitle());
                intent.putExtra("description", drill.getDescription());
                intent.putExtra("icon", drill.getIcon());

                startActivity(intent);

            }
        });


    }
}