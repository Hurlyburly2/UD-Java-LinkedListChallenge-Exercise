package com.dsimon;

import java.util.Iterator;
import java.util.LinkedList;

public class Album {
    private String name;
    private LinkedList<Song> songs;

    public Album(String name, LinkedList<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void displayList() {
        System.out.println(name + ": ");

        Iterator songIterator = songs.listIterator();
        while (songIterator.hasNext()) {
            Song currentSong = (Song) songIterator.next();
            System.out.println(" * " + currentSong.getTitle() + " - " + currentSong.getDuration());
        }
    }
}
