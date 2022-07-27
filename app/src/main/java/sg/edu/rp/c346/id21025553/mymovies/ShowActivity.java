package sg.edu.rp.c346.id21025553.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowActivity extends AppCompatActivity {

    ArrayList<Movies> al;
    ArrayList<String> ratingsList;

    CustomAdapter aa;
    ArrayAdapter<String> ratingAdapter;

    ListView lv;
    Spinner spinnerRating;
    Button btnRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String[] ratings = getResources().getStringArray(R.array.ratingArray);

        spinnerRating = findViewById(R.id.spinnerRating);
        lv = findViewById(R.id.listView);
        btnRating = findViewById(R.id.btnRating);

        ratingsList = new ArrayList<String>(Arrays.asList(ratings));
        ratingsList.add(0,"Filter by rating");
        al = new ArrayList<>();

        aa = new CustomAdapter(this, R.layout.row, al);
        ratingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingsList);

        DBHelper dbh = new DBHelper(ShowActivity.this);

        al.clear();
        al.addAll(dbh.getAllMovies());

        spinnerRating.setAdapter(ratingAdapter);
        lv.setAdapter(aa);
        spinnerRating.setSelection(0);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                al.addAll(dbh.getAllMovies());
                if (spinnerRating.getSelectedItem().toString().equals("Filter by rating")) {

                    Toast.makeText(ShowActivity.this, "Please select a rating", Toast.LENGTH_SHORT).show();

                } else if (spinnerRating.getSelectedItem().toString().equals("View all movies")) {

                    al.clear();
                    al.addAll(dbh.getAllMovies());
                    ratingsList.set(0, "Filter by rating");


                } else {

                    for (int i = 0; i < spinnerRating.getCount(); i++) {
                        if (spinnerRating.getSelectedItemPosition() == i) {
                            al.clear();
                            al.addAll(dbh.getAllMovies(String.valueOf(spinnerRating.getItemAtPosition(i))));

                        }
                    }
                    ratingsList.set(0, "View all movies");

                }
                ratingAdapter.notifyDataSetChanged();
                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies data = al.get(position);
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
}