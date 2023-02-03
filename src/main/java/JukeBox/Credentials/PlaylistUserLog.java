package JukeBox.Credentials;

public class PlaylistUserLog {
    private String playListName;
    private int userId;

    public String getPlayListName() {
        return playListName;
    }

    public PlaylistUserLog(String playListName, int userId) {
        this.playListName = playListName;
        this.userId = userId;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
