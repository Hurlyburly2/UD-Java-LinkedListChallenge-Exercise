package com.dsimon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Playlist {
    private String name;
    private ArrayList<Album> albums;
    private LinkedList<Song> playList;
    private Scanner scanner = new Scanner(System.in);

    public Playlist(String name) {
        this.name = name;
        albums = new ArrayList<Album>();
        playList = new LinkedList<Song>();
    }

    public void addAlbum(String name, LinkedList<Song> songs) {
        Album newAlbum = new Album(name, songs);
        albums.add(newAlbum);
    }

    public void runPlayList() {
        displayOptions();

        boolean done = false;
        while(!done) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        displayOptions();
                        break;
                    case 2:
                        displayAll();
                        break;
                    case 5:
                        done = true;
                        break;
                    default:
                        System.out.println("Not a valid entry. Please enter a number from the list.");
                        break;
                }
            }
        }
    }

    private void displayOptions() {
        System.out.println("\n" + name + "PlayList Options:");
        System.out.println(" 1. Display Options");
        System.out.println(" 2. View all Albums and Songs");
        System.out.println(" 5. Quit");
    }

    private void displayAll() {
        Iterator albumIterator = albums.iterator();
        while (albumIterator.hasNext()) {
            Album currentAlbum = (Album) albumIterator.next();
            currentAlbum.displayList();
        }
    }
}
