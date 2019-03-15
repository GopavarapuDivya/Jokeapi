package com.example.acer.jokeapi;

public class JokeModel
{
    String name;
    public JokeModel(String jokename)
    {
        this.name=jokename;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
