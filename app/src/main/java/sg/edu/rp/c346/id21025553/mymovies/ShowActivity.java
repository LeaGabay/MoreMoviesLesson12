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

import java.util.ArrayList;
import java.util.Arrays;

public class ShowActivity extends AppCompatActivity {

    ArrayList<Movies> al;
    CustomAdapter aa;

    ListView lv;
    Spinner spinnerRating;
    Button btnRating;
    ArrayList<String> ratingsList;
    ArrayAdapter ratingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String[] ratings = getResources().getStringArray(R.array.ratingArray);
        spinnerRating = findViewById(R.id.spinnerRating);
        lv = findViewById(R.id.lv);
        btnRating = findViewById(R.id.btnRating);
        ratingsList = new ArrayList<>(Arrays.asList(ratings));

        al = new ArrayList<>();
        aa = new CustomAdapter(this, R.layout.row, al);
        ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ratingsList);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al.clear();
        al.addAll(dbh.getAllMovies());

        lv.setAdapter(aa);
        spinnerRating.setAdapter(ratingAdapter);
        spinnerRating.setSelection(0);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnRating.isPressed()){
                    al.clear();
                    al.addAll(dbh.getAllMovies());
                } else {
                    al.clear();
                    al.addAll(dbh.getAllMovies(spinnerRating.getSelectedItem().toString()));
                }
                aa.notifyDataSetChanged();
            }
        });

        spinnerRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    ratingsList.set(0, "Filter by rating");

                } else {
                    ratingsList.set(0, "View all movies");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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