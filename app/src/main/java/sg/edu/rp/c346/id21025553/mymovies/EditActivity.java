package sg.edu.rp.c346.id21025553.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {

    Movies data;
    EditText etID, etTitle, etGenre, etYear;
    Spinner spinnerRating;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);

        spinnerRating = findViewById(R.id.spinnerRating);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        DBHelper dbh = new DBHelper(EditActivity.this);

        etID.setFocusable(false);
        etID.setHint(String.valueOf(data.getId()));

        etTitle.setText(data.getTitle());
        etYear.setText(String.valueOf(data.getYear()));
        etGenre.setText(data.getGenre());

        if (data.getRating() == spinnerRating.getSelectedItem().toString()){
            spinnerRating.setSelection(spinnerRating.getSelectedItemPosition());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(spinnerRating.getSelectedItem().toString());

                dbh.updateMovie(data);
                dbh.close();

                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteMovie(data.getId());

                Intent i = new Intent(EditActivity.this,
                        MainActivity.class);
                startActivity(i);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });
    }
}