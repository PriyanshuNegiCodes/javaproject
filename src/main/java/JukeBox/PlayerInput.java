package JukeBox;

import JukeBox.Database.TablesImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
// Shuffle the song and give list input to the player
public class PlayerInput extends CreatePlaylist{
    static Controller obj=new Controller();
    List<Music> listMusic=new ArrayList<>();
    ResultSet resultSet;
    // this method will iterate the main catalog
    public List<Music> catalogFilter(String table, int id, String startsWith) {
        try {
            System.out.println("\033[31m" + "-------------------Your----------------------------Catalog-------------------------Search Result-------------------------------" + "\033[0m");
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%6s |%12s |%12s |%12s |%12s |%12s |%12s|\n",
                    "SongNumber", "LanguageName", "ArtistName", "GenreName", "AlbumName",
                     "SongName", " Duration(in min)");
            System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------+");

            st=super.getConnection().createStatement();
            resultSet=st.executeQuery("SELECT song.Sno, language.LanguageName, " +
                    " artist.ArtistName , genre.GenreName, album.AlbumName, " +
                    "song.SongName, song.Path, song.Duration\n" +
                    "FROM song, language, album, artist, genre\n" +
                    "WHERE song.LanguageId = language.LanguageId\n" +
                    "AND song.AlbumId = album.AlbumId\n" +
                    "AND song.ArtistId = artist.ArtistId\n" +
                    "AND song.GenreId = genre.GenreId\n" +
                    "AND "+table+"."+table+"Id = "+id+ "\n"+
                    "AND song.SongName LIKE '" + startsWith + "%';");
            while(resultSet.next()){
                Music music=new Music(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8));
                System.out.printf("|%12d |%12s |%12s |%12s |%12s |%12s |%12s|\n",
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6)
                        , resultSet.getString(8));
                System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------+");
                listMusic.add(music);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            if(listMusic.size()==0){
                System.out.println("No Results found");
            }
        return listMusic;
    }

    public List<Music> userPlaylistSongs(int playlistId){
        try {
            System.out.println("\033[31m" + "-------------------YOUR----------------------------SONG-------------------------PlAYLIST-------------------------------" + "\033[0m");
            System.out.println("+--------------------------------------------------------------------------------------------+");
            System.out.println("SongId  LanguageName ArtistName GenreName AlbumName SongName");
            System.out.println("+--------------------------------------------------------------------------------------------+");
            st=super.getConnection().createStatement();
            resultSet=st.executeQuery("SELECT Song.Sno, Language.LanguageName, Artist.ArtistName, Genre.GenreName, " +
                    "Album.AlbumName, Song.SongName, Song.Path, Song.Duration FROM Song, Language, Album, Artist, Genre, Playlist\n" +
                    "WHERE Song.LanguageID = Language.LanguageID AND Song.AlbumID = Album.AlbumID AND " +
                    "Song.ArtistID = Artist.ArtistID AND Song.GenreID = Genre.GenreID AND Song.Sno = Playlist.SongId " +
                    "AND Playlist.PlaylistId = "+playlistId+";");
            while(resultSet.next()){
                Music music=new Music(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7),resultSet.getString(8));
                System.out.println(resultSet.getInt(1)+"     "+resultSet.getString(2)+"     "+
                        resultSet.getString(3)+"      "+ resultSet.getString(4)+"      "+
                        resultSet.getString(5)+"      "+resultSet.getString(6));
                System.out.println("+--------------------------------------------------------------------------------------------+");
                listMusic.add(music);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(listMusic.size()==0){
            System.out.println("No Results found");
        }
        return listMusic;
    }
    public void songsSequence(List<Music>list) {
        Player player = new Player();
        Scanner sc = new Scanner(System.in);
        if (list.size() == 0) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Error!! you do not have any song in the playlist, kindly add the songs first");
            System.out.println("------------------------------------------------------------------------");
            obj.mainReturnMethod();
        }
        if (list.size() == 1) {
            System.out.println(player.playSong(list));
            obj.mainReturnMethod();
        }
        while (true) {
            System.out.println("1. Play in sequence\n2. Play in shuffle \n3. Return to Main Menu");
            int input = sc.nextInt();
            if (input == 1) {
                System.out.println(player.playSong(list));
            } else if (input == 2) {
                Collections.shuffle(list);
                System.out.println(player.playSong(list));
            } else if (input==3){
                obj.mainReturnMethod();
            } else{
            System.out.println("Invalid Input: Enter the input again");
            }
        }
    }
    public void FilterSongMethod(){
        PlayerInput playerInput=new PlayerInput();
        TablesImp tablesImp=new TablesImp();
        Display display=new Display();
        System.out.println("1. Search into album\n2. Search into artist\n3. Search into genre\n" +
                "4. Search into Language\n5. Return to the main menu");
        int value = sc.nextInt();
        String temp="";
        switch (value) {
            case 1:
                temp = temp.concat("album");
                break;
            case 2:
                temp = temp.concat("artist");
                break;
            case 3:
                temp = temp.concat("genre");
                break;
            case 4:
                temp = temp.concat("language");
                break;
            case 5: obj.mainReturnMethod();
            default:
                System.out.println("invalid input for the table selection");
        }
        tablesImp.showDetailsTable(temp);
        System.out.println("Enter the id as per which you want to filter");
        int filterId= sc.nextInt();
        tablesImp.checkData(filterId, temp);
        display.showCatalog(temp, filterId);
        System.out.println("Enter the first character of the song to filter the data:");
        String firstInitial=sc.next();
        playerInput.songsSequence(playerInput.catalogFilter(temp, filterId, firstInitial));
    }

}



