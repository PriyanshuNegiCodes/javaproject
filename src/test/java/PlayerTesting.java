import JukeBox.Credentials.PlaylistUserLogImp;
import JukeBox.Credentials.UserImp;
import JukeBox.Player.TimeOperations;
import JukeBox.Playlist.CreatePlaylist;
import JukeBox.Playlist.Display;
import JukeBox.Playlist.Music;
import JukeBox.Playlist.PlayerInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTesting {
    UserImp userImp;
    PlaylistUserLogImp playlistUserLogImp;
    PlayerInput playerInput;
    TimeOperations timeOperations;
    List<Music> list;
    Display display;
    CreatePlaylist createPlaylist;

    @Before
    public void setUp(){
        list=new ArrayList<>();
        userImp=new UserImp();
        playerInput=new PlayerInput();
        playlistUserLogImp=new PlaylistUserLogImp();
        display=new Display();
        timeOperations=new TimeOperations();
        createPlaylist=new CreatePlaylist();
    }
    @After
    public void tearDown(){
        list=null;
        userImp=null;
        playerInput=null;
        playlistUserLogImp=null;
        display=null;
        timeOperations=null;
        createPlaylist=null;
    }
    @Test
    public void testCheckUser(){
        int status=userImp.checkUser("Priyanshu", "Negi");
        Assert.assertEquals(1,status);
    }
    @Test
    public void testCheckUserFail(){
        int status=userImp.checkUser( "Sameer", "Negi");
        Assert.assertEquals(0,status);
    }
//    Is method extracting users playlists songs perfect ?
    @Test
    public void userPlaylistSongsPass(){
        Assert.assertEquals("KedarNath", playerInput.userPlaylistSongs(2).get(0).getSongName());
        Assert.assertEquals("Thunder", playerInput.userPlaylistSongs(1).get(2).getSongName());

    }
    @Test
    public void userPlaylistSongsFail(){
        Assert.assertNotEquals("KedarNath", playerInput.userPlaylistSongs(1).get(0).getSongName());
        Assert.assertNotEquals("Thunder", playerInput.userPlaylistSongs(2).get(2).getSongName());

    }
//    Is catalog search working fine for initial letter of song ?
    @Test
    public void catalogFilterPass(){
        Assert.assertFalse(playerInput.catalogFilter("album", 5, "K").isEmpty());
        Assert.assertFalse(playerInput.catalogFilter("language", 2, "F").isEmpty());
        Assert.assertFalse(playerInput.catalogFilter("genre", 2, "G").isEmpty());
        Assert.assertFalse(playerInput.catalogFilter("artist", 6, "B").isEmpty());
    }
    @Test
    public void catalogFilterFail(){
        Assert.assertTrue(playerInput.catalogFilter("album", 1, "C").isEmpty());
        Assert.assertTrue(playerInput.catalogFilter("language", 2, "Z").isEmpty());
        Assert.assertTrue(playerInput.catalogFilter("genre", 2, "K").isEmpty());
        Assert.assertTrue(playerInput.catalogFilter("artist", 6, "M").isEmpty());
    }
    // Check is the song id is there in the catalog or not
    @Test
    public void checkSongIDFail(){
        //Return false if song is already there
        Assert.assertTrue(playlistUserLogImp.checkSongID(11));
        Assert.assertTrue(playlistUserLogImp.checkSongID(12));
        Assert.assertTrue(playlistUserLogImp.checkSongID(13));
        Assert.assertTrue(playlistUserLogImp.checkSongID(14));
        Assert.assertTrue(playlistUserLogImp.checkSongID(15));
    }
    @Test
    public void checkSongIDPass(){
        //Return false if song is already there
        Assert.assertFalse(playlistUserLogImp.checkSongID(1));
        Assert.assertFalse(playlistUserLogImp.checkSongID(2));
        Assert.assertFalse(playlistUserLogImp.checkSongID(3));
        Assert.assertFalse(playlistUserLogImp.checkSongID(4));
    }
    // Check if the catalog is working fine for any category

    @Test
    public void checkShowCatalogPass(){
        list=display.showCatalog("Album", 4);
        list.forEach(s->Assert.assertEquals("NamoNamo",list.get(0).getSongName()));
        list=display.showCatalog("Artist", 1);
        list.forEach(s->Assert.assertEquals("KedarNath",list.get(0).getSongName()));
        list=display.showCatalog("Genre", 1);
        list.forEach(s->Assert.assertEquals("Thunder",list.get(0).getSongName()));
        list=display.showCatalog("Language", 1);
        list.forEach(s->Assert.assertEquals("TeraFitoor",list.get(2).getSongName()));
    }
    @Test
    public void checkShowCatalogFail(){
        list=display.showCatalog("Album", 2);
        list.forEach(s->Assert.assertNotEquals("NamoNamo",list.get(0).getSongName()));
        list=display.showCatalog("Artist", 3);
        list.forEach(s->Assert.assertNotEquals("KedarNath",list.get(0).getSongName()));
        list=display.showCatalog("Genre", 2);
        list.forEach(s->Assert.assertNotEquals("Thunder",list.get(0).getSongName()));
        list=display.showCatalog("Language", 2);
        list.forEach(s->Assert.assertNotEquals("TeraFitoor",list.get(2).getSongName()));
    }
    // Check if time calculator is working fine
    @Test
    public void checktestTimePass() {
        Assert.assertEquals("3:18",  timeOperations.timer(198000000));
    }
    @Test
    public void checktestTimeFail() {
        Assert.assertNotEquals("3:18",  timeOperations.timer(199000000));
    }
    // Check if the song is there in the playlist
    @Test
    public void checkVerifySongInPlaylist(){
        Assert.assertFalse(createPlaylist.verifySongInPlaylist(2, 4));
        Assert.assertTrue(createPlaylist.verifySongInPlaylist(4, 1));
    }
    @Test
    public void checkLogDataFail(){
        Assert.assertTrue(playlistUserLogImp.checkLogData("Hii", 1));
        Assert.assertTrue(playlistUserLogImp.checkLogData("Hello", 2));
    }
    @Test
    public void checkLogDataSuccess(){
        Assert.assertFalse(playlistUserLogImp.checkLogData("Play1", 1));
        Assert.assertFalse(playlistUserLogImp.checkLogData("Play2", 2));
    }
}
