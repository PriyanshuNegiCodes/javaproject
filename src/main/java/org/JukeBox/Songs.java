package org.JukeBox;

public class Songs {
    private int index;
    private String album;
    private String artist;
    private String genre;
    private String name;
    private String path;
    public Songs( int index, String album, String genre, String artist, String name, String path) {
        this.index=index;
        this.album=album;
        this.artist = artist;
        this.genre = genre;
        this.name = name;
        this.path=path;
    }
    public Songs( int index, String name, String path) {
        this.index=index;
        this.name = name;
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
