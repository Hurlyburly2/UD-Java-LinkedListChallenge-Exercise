package com.dsimon;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Playlist playlist = new Playlist("My Playlist");

        LinkedList<Song> albumOneSongs = new LinkedList<Song>();
        albumOneSongs.add(new Song("SongOne", 123));
        albumOneSongs.add(new Song("SongTwo", 345));
        albumOneSongs.add(new Song("SongThree", 567));
        albumOneSongs.add(new Song("SongFour", 890));
        albumOneSongs.add(new Song("SongFive", 012));
        playlist.addAlbum("AlbumOne", albumOneSongs);

        LinkedList<Song> albumTwoSongs = new LinkedList<Song>();
        albumTwoSongs.add(new Song("AlbumTwo-One", 123));
        albumTwoSongs.add(new Song("AlbumTwo-Two", 123));
        albumTwoSongs.add(new Song("AlbumTwo-Three", 123));
        albumTwoSongs.add(new Song("AlbumTwo-Four", 123));
        albumTwoSongs.add(new Song("AlbumTwo-Five", 123));
        albumTwoSongs.add(new Song("AlbumTwo-Six", 123));
        playlist.addAlbum("AlbumTwo", albumTwoSongs);

        playlist.runPlayList();
    }
}
