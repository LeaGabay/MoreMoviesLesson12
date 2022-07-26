package sg.edu.rp.c346.id21025553.mymovies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvMovie = rowView.findViewById(R.id.tvMovieTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvDate = rowView.findViewById(R.id.tvDate);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        // Obtain the Movie Information based on the position
        Movies currentMovie = movieList.get(position);

        tvMovie.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvDate.setText(String.valueOf(currentMovie.getYear()));

        String draw = "rating_" + currentMovie.getRating().toLowerCase();
        int id = parent_context.getResources().getIdentifier(draw, "drawable", parent_context.getPackageName());
        Drawable img = parent_context.getResources().getDrawable(id);
        imageView.setImageDrawable(img);

        return rowView;
    }
}
