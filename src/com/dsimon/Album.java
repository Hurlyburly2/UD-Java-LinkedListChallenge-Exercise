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

    public Song getSong(int songIndex) {
        return songs.get(songIndex);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void displayList() {
        Iterator songIterator = songs.listIterator();
        int counter = 0;
        while (songIterator.hasNext()) {
            Song currentSong = (Song) songIterator.next();
            System.out.println("   " + counter + ". " + currentSong.getTitle() + " - " + currentSong.getDuration());
            counter++;
        }
    }
}
