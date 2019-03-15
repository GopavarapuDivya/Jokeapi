package com.example.acer.jokeapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.MyViewHolder>
{

    Context context;
    ArrayList<JokeModel> jokes;

    public JokesAdapter(ResultsActivity resultsActivity, ArrayList<JokeModel> jokeModels)
    {
        this.context=resultsActivity;
        this.jokes=jokeModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v=LayoutInflater.from(context).inflate(R.layout.design,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        JokeModel search=jokes.get(i);
        myViewHolder.tv.setText(search.getName());

    }

    @Override
    public int getItemCount()
    {
        return jokes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv=itemView.findViewById(R.id.text);
        }
    }
}
