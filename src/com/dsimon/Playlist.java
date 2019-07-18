package com.dsimon;

import java.util.*;

public class Playlist {
    private String name;
    private ArrayList<Album> albums;
    private Scanner scanner = new Scanner(System.in);

    private LinkedList<Song> playList;
    private ListIterator<Song> nowPlaying;
    private Song loadedSong = null;
    boolean goingForward = true;

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
        boolean done = false;

        while(!done) {
            displayOptions();
            if (loadedSong != null) {
                System.out.println("NOW PLAYING: " + loadedSong.getTitle());
            }
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        displayOptions();
                        break;
                    case 2:
                        display("all");
                        break;
                    case 3:
                        showPlayList();
                        break;
                    case 4:
                        addSong();
                        break;
                    case 5:
                        nextSong();
                        break;
                    case 6:
                        previousSong();
                        break;
                    case 7:
                        repeatSong();
                        break;
                    case 8:
                        if (playList.size() > 0) {
                            removeCurrentSong();
                        }
                        break;
                    case 9:
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
        System.out.println(" 3. View All Playlist Songs");
        System.out.println(" 4. Add a Song to Playlist");
        System.out.println(" 5. Next Song");
        System.out.println(" 6. Previous Song");
        System.out.println(" 7. Repeat Song");
        System.out.println(" 8. Remove Song");
        System.out.println(" 9. Quit");
    }

    private int display(String type) {
        Iterator albumIterator = albums.iterator();
        int counter = 0;
        while (albumIterator.hasNext()) {
            Album currentAlbum = (Album) albumIterator.next();
            System.out.println(counter + ". " + currentAlbum.getName());
            counter++;
            if (type.equals("all")) {
                currentAlbum.displayList();
            }
        }
        return counter;
    }

    private void addSong() {
        System.out.println("Select an Album:");
        display("albumsOnly");

        boolean done = false;
        int selectedAlbumIndex = -1;
        while (!done) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                if (selection < albums.size() && selection >= 0) {
                    selectedAlbumIndex = selection;
                    done = true;
                } else {
                    System.out.println("Invalid selection");
                }
            } else {
                System.out.println("Must enter a number.");
            }
            scanner.nextLine();
        }

        Album selectedAlbum = albums.get(selectedAlbumIndex);
        System.out.println("Select a Song:");
        selectedAlbum.displayList();

        done = false;
        int selectedSongIndex = -1;
        while(!done) {
            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                if (selection < selectedAlbum.getSongs().size() && selection >= 0) {
                    selectedSongIndex = selection;
                    done = true;
                } else {
                    System.out.println("Invalid Selection");
                }
            } else {
                System.out.println("Must enter a number");
            }
            scanner.nextLine();
        }

        Song selectedSong = selectedAlbum.getSong(selectedSongIndex);
        playList.add(selectedSong);
        System.out.println("Song added!");
        nowPlaying = playList.listIterator();
        loadedSong = nowPlaying.next();
    }

    private void showPlayList() {
        Iterator playlistIterator = playList.iterator();
        if (playList.size() > 0) {
            int count = 0;
            while (playlistIterator.hasNext()) {
                Song currentSong = (Song) playlistIterator.next();
                String isItPlaying = "   ";
                if (currentSong.equals(loadedSong)) {
                    isItPlaying = " * ";
                }
                System.out.println(isItPlaying + count + ". " + currentSong.getTitle() + " - " + currentSong.getDuration());
            }
        } else {
            System.out.println("You haven't added any songs yet!");
        }

    }

    private void nextSong() {
        if (!goingForward) {
            if (nowPlaying.hasNext()) {
                nowPlaying.next();
            }
            goingForward = true;
        }
        if (nowPlaying.hasNext()) {
            loadedSong = nowPlaying.next();
            System.out.println("Now playing " + loadedSong.getTitle());
        } else {
            System.out.println("There are no more songs in the playlist");
        }
    }

    private void previousSong() {
        if (goingForward) {
            if (nowPlaying.hasPrevious()) {
                nowPlaying.previous();
            }
            goingForward = false;
        }
        if (nowPlaying.hasPrevious()) {
            loadedSong = nowPlaying.previous();
            System.out.println("Now playing " + loadedSong.getTitle());
        } else {
            System.out.println("There are no earlier songs in the playlist");
        }
    }

    private void repeatSong() {
        if (playList.size() > 0) {
            nowPlaying.previous();
            nowPlaying.next();
            System.out.println("Repeating song " + loadedSong.getTitle());
        } else {
            System.out.println("No songs in the playlist");
        }
    }

    private void removeCurrentSong() {
        System.out.println("Removing song " + loadedSong.getTitle());
        nowPlaying.remove();
        if (nowPlaying.hasNext()) {
            loadedSong = nowPlaying.next();
            System.out.println("Now playing: " + loadedSong.getTitle());
        } else if (nowPlaying.hasPrevious()) {
            loadedSong = nowPlaying.previous();
            System.out.println("Now playing " + loadedSong.getTitle());
        } else {
            System.out.println("No songs left");
            loadedSong = null;
        }
    }
}
