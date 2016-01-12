package com.sevenrealm.base.imovies;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    int id = 0;
    ImageView imageView;
    TextView title, genre, vote, tag, desc;
    loading loading;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        loading = new loading();
        loading.execute();
    }

    private class loading extends AsyncTask {

        JSONObject movie;
        Core core = new Core(getBaseContext());

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onPostExecute(Object o) {
            setContentView(R.layout.activity_full_image);
            imageView = (ImageView) findViewById(R.id.imageView);
//            title = (TextView) findViewById(R.id.textView);
            genre = (TextView) findViewById(R.id.textView2);
            vote = (TextView) findViewById(R.id.textView3);
            tag = (TextView) findViewById(R.id.textView4);
            desc = (TextView) findViewById(R.id.textView5);
            String url;
            String gen = "";
            String v;
            try {
                url = core.xlarge_image_url + movie.getString("backdrop_path");
                Picasso.with(getBaseContext()).load(url).into(imageView);
//                title.setText(movie.getString("title"));
                toolbar.setTitle(movie.getString("title"));
                for (int i = 0; i < movie.getJSONArray("genres").length(); i++) {
                    gen = gen + " " + movie.getJSONArray("genres").getJSONObject(i).getString("name");
                }
                genre.setText(gen);
                v = "Vote " + movie.getString("vote_average") + " From " + movie.getString("vote_count") + "users";
                vote.setText(v);
                tag.setText(movie.getString("tagline"));
                desc.setText(movie.getString("overview"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            if (id != 0) {
                try {
                    movie = core.getMovieById(id);
                    Log.d("movie", movie.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
