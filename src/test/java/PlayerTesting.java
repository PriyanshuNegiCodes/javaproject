import JukeBox.Credentials.PlaylistUserLogImp;
import JukeBox.Credentials.UserImp;
import JukeBox.Music;
import JukeBox.PlayerInput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerTesting {
    UserImp userImp;
    PlaylistUserLogImp playlistUserLogImp;
    PlayerInput playerInput;
    List<Music> list;
    @Before
    public void setUp(){
        list=new ArrayList<>();
        userImp=new UserImp();
        playerInput=new PlayerInput();
        playlistUserLogImp=new PlaylistUserLogImp();
    }
    @After
    public void tearDown(){
        list=null;
        userImp=null;
        playerInput=null;
        playlistUserLogImp=null;
    }
    @Test
    public void testCheckUser(){
        boolean status=userImp.checkUser(1, "Priyanshu", "Negi");
        Assert.assertTrue(status);
    }
    @Test
    public void testCheckUserFail(){
        boolean status=userImp.checkUser(1, "Sameer", "Negi");
        Assert.assertFalse(status);
    }
    @Test
    public void userPlaylistSongsPass(){
        List<Music> list;
        list=playerInput.userPlaylistSongs(2);
        String name="";
        for(Music in:list){
            Assert.assertEquals("Stream", in.getSongName());
        }

        for(Music in:list){
            if(in.getSongName().contains("Waves")){
                name=name.concat(in.getSongName());
                Assert.assertEquals("Waves", name);
            }
        }
    }
    @Test
    public void userPlaylistSongsFail(){
        List<Music> list;
        list=playerInput.userPlaylistSongs(1);
        list.forEach(in->Assert.assertFalse(in.getSongName().contains("Mirage")));
        list=playerInput.userPlaylistSongs(2);
        list.forEach(in->Assert.assertFalse(in.getSongName().contains("Mirage")));
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
    public void getPlaylistNamePass(){
        Assert.assertTrue(playlistUserLogImp.getPlaylistName(1).contains("Play1"));
        Assert.assertTrue(playlistUserLogImp.getPlaylistName(4).contains("TestPlayList"));
        Assert.assertTrue(playlistUserLogImp.getPlaylistName(6).contains("YunusTest"));
    }
    @Test
    public void getPlaylistNameFails(){
        Assert.assertFalse(playlistUserLogImp.getPlaylistName(1).contains("Name1"));
        Assert.assertFalse(playlistUserLogImp.getPlaylistName(4).contains("Name2"));
        Assert.assertFalse(playlistUserLogImp.getPlaylistName(4).contains("Name3"));
    }
    @Test
    public void checkSongIDPass(){
        //Return false if song is already there
        Assert.assertFalse(playlistUserLogImp.checkSongID(11));
        Assert.assertFalse(playlistUserLogImp.checkSongID(12));
        Assert.assertFalse(playlistUserLogImp.checkSongID(13));
        Assert.assertFalse(playlistUserLogImp.checkSongID(14));
        Assert.assertFalse(playlistUserLogImp.checkSongID(15));
    }
    @Test
    public void checkSongIDFail(){
        //Return false if song is already there
        Assert.assertTrue(playlistUserLogImp.checkSongID(1));
        Assert.assertTrue(playlistUserLogImp.checkSongID(2));
        Assert.assertTrue(playlistUserLogImp.checkSongID(3));
        Assert.assertTrue(playlistUserLogImp.checkSongID(4));
    }
}
