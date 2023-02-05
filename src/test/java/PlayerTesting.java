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
    @Test
    public void userPlaylistSongsPass(){
       Assert.assertTrue(playerInput.userPlaylistSongs(2).get(0).getSongName().equals("KedarNath"));
       Assert.assertTrue(playerInput.userPlaylistSongs(1).get(2).getSongName().equals("Thunder"));

    }
    @Test
    public void userPlaylistSongsFail(){
        Assert.assertFalse(playerInput.userPlaylistSongs(1).get(0).getSongName().equals("KedarNath"));
        Assert.assertFalse(playerInput.userPlaylistSongs(2).get(2).getSongName().equals("Thunder"));

    }
    @Test
    public void catalogFilterPass(){
        list=playerInput.catalogFilter("Album", 4, "W");
        list.forEach(in->Assert.assertNotNull(list));
        list=playerInput.catalogFilter("Artist", 5, "B");
        list.forEach(in->Assert.assertNotNull(list));
        list=playerInput.catalogFilter("language", 2, "P");
        list.forEach(in->Assert.assertNotNull(list));
        list=playerInput.catalogFilter("Genre", 4, "W");
        list.forEach(in->Assert.assertNotNull(list));
    }
    @Test
    public void catalogFilterFail(){
        list=playerInput.catalogFilter("Album", 4, "P");
        list.forEach(in->Assert.assertNull(list));
        list=playerInput.catalogFilter("Artist", 6, "F");
        list.forEach(in->Assert.assertNull(list));
        list=playerInput.catalogFilter("language", 3, "P");
        list.forEach(in->Assert.assertNull(list));
        list=playerInput.catalogFilter("Genre", 4, "Z");
        list.forEach(in->Assert.assertNull(list));
    }
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
    @Test
    public void checktestTimePass() {
        Assert.assertEquals("3:18",  timeOperations.timer(198000000));
    }
    @Test
    public void checktestTimeFail() {
        Assert.assertNotEquals("3:18",  timeOperations.timer(199000000));
    }
    @Test
    public void checkVerifySongInPlaylist(){
        Assert.assertFalse(createPlaylist.verifySongInPlaylist(2, 4));
        Assert.assertTrue(createPlaylist.verifySongInPlaylist(4, 1));
    }
}
