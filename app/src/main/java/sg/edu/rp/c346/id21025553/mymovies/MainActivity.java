package sg.edu.rp.c346.id21025553.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Creating variables
    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    Spinner spinnerRating;
    ArrayList<Movies> movieList;
    ArrayAdapter<Movies> movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking variables to UI elements
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);

        btnInsert = findViewById(R.id.insertBtn);
        btnShowList = findViewById(R.id.showBtn);

        spinnerRating = findViewById(R.id.spinnerRating);

        movieList = new ArrayList<Movies>();
        movieAdapter = new ArrayAdapter<Movies>(this, android.R.layout.simple_list_item_1, movieList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dataTitle = etTitle.getText().toString();
                String dataGenre = etGenre.getText().toString();
                String dataYear = etYear.getText().toString();
                String dataRating = spinnerRating.getSelectedItem().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(dataTitle, dataGenre, dataYear, dataRating);

                movieList.addAll(db.getAllMovies());
                movieAdapter.notifyDataSetChanged();

                etTitle.setText("");
                etGenre.setText("");
                etYear.setText("");

                spinnerRating.setSelection(0);

                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });
    }
}