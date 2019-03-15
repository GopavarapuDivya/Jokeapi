package com.example.acer.jokeapi;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>
{
    ArrayList<JokeModel> jokeModels;
    RecyclerView recyclerView;
    ProgressBar pb;
    String jokeurl="http://api.icndb.com/jokes/random/";
    String string;
    public static final int Loader_id=12;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        pb=findViewById(R.id.progress);
        jokeModels=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        string=getIntent().getStringExtra("data");

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            getLoaderManager().initLoader(Loader_id, null, this);
        }
        else
        {

            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("alert Box");
            alertDialog.setMessage("check your internet connection!");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            dialogInterface.cancel();
                            finish();
                        }
                    });
            AlertDialog dialog = alertDialog.create();
            dialog.show();
        }

    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<String>(this)
        {
            @Override
            public String loadInBackground()
            {
                try
                {
                    URL url=new URL(jokeurl+string);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Scanner scanner=new Scanner(inputStream);
                    scanner.useDelimiter("//A");
                    if (scanner.hasNext())
                    {
                        return scanner.next();
                    }
                    else {
                        return null;
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                pb.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s)
    {
        pb.setVisibility(View.GONE);
        if(s!=null)
        {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String jokename = object.getString("joke");
                    Log.i("jokeurl", jokename);
                    jokeModels.add(new JokeModel(jokename));
                    }
                recyclerView.setLayoutManager(new LinearLayoutManager(ResultsActivity.this));
                recyclerView.setAdapter(new JokesAdapter(ResultsActivity.this, jokeModels));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
