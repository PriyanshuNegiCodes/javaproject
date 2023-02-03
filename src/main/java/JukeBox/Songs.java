package JukeBox;

public class Songs {
    private int languageId;
    private int albumId;
    private int artistid;
    private int genreId;
    private String SongName;
    private String duration;
    private String path;
    public Songs( int languageId, int albumId, int artistId, int genreId, String SongName, String path, String duration) {
        this.languageId=languageId;
        this.albumId=albumId;
        this.artistid = artistId;
        this.genreId = genreId;
        this.SongName = SongName;
        this.path=path;
        this.duration=duration;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
