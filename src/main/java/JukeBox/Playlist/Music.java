package JukeBox.Playlist;

public class Music {
    private int Sno;
    private String  LangaugeName, AlbumName, ArtistName,
            GenreName, SongName, path, Duration;

    public Music(int sno, String langaugeName,String artistName,String genreName, String albumName,
                 String songName, String path, String duration) {
        this.Sno = sno;
        this.LangaugeName = langaugeName;
        this.AlbumName = albumName;
        this.ArtistName = artistName;
        this.GenreName = genreName;
        this.SongName = songName;
        this.path = path;
        this.Duration = duration;
    }

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }

    public String getLangaugeName() {
        return LangaugeName;
    }

    public void setLangaugeName(String langaugeName) {
        LangaugeName = langaugeName;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "Music{" +
                "Sno=" + Sno +
                ", LangaugeName='" + LangaugeName + '\'' +
                ", AlbumName='" + AlbumName + '\'' +
                ", ArtistName='" + ArtistName + '\'' +
                ", GenreName='" + GenreName + '\'' +
                ", SongName='" + SongName + '\'' +
                ", path='" + path + '\'' +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}
