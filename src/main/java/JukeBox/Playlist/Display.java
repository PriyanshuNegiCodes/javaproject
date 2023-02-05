package JukeBox.Playlist;

import JukeBox.connector.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Display extends Connector {

    public List<Music> showCatalog(){
        List<Music> musicList=new ArrayList<>();
        try {
            Statement st=getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("SELECT Song.Sno, Language.LanguageName, " +
                    "Artist.ArtistName, Genre.GenreName, \n" +
                    "Album.AlbumName, Song.SongName, Song.Path, Song.Duration\n" +
                    "FROM Song, Language, Artist, Genre, Album\n" +
                    "WHERE Song.LanguageId = Language.LanguageId\n" +
                    "AND Song.ArtistId = Artist.ArtistId\n" +
                    "AND Song.GenreId = Genre.GenreId\n" +
                    "AND Song.AlbumId = Album.AlbumId;");
            System.out.println("\033[31m" + "------------------------------YOUR------------------------------------SONG---------------------------------------CATALOG----------------------------------------------------------------" + "\033[0m");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("%5s %-25s %-20s %-20s %-20s %-20s %-20s\n","SongId", "Language", "Artist", "Genre", "Album", "Song", "Duration");
            while(resultSet.next()){
                System.out.printf("%5d %-28s %-20s %-20s %-20s %-20s %-20s\n",resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(8));
                    Music music=new Music(resultSet.getInt(1),resultSet.getString(2),
                            resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                musicList.add(music);
                System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return musicList;
    }
    public List<Music> showCatalog(String name, int id){
        List<Music> musicList=new ArrayList<>();
        try {
            Statement st=getConnection().createStatement();
            ResultSet resultSet=st.executeQuery("SELECT Song.Sno, Language.LanguageName, " +
                    "Artist.ArtistName, Genre.GenreName, \n" +
                    "Album.AlbumName, Song.SongName, Song.Path, Song.Duration\n" +
                    "FROM Song, Language, Artist, Genre, Album\n" +
                    "WHERE Song.LanguageId = Language.LanguageId\n" +
                    "AND Song.ArtistId = Artist.ArtistId\n" +
                    "AND Song.GenreId = Genre.GenreId\n" +
                    "AND Song.AlbumId = Album.AlbumId\n" +
                    "AND "+name+"."+name+"Id = "+id+";");
            System.out.println("\033[31m" + "------------------------------YOUR------------------------------------SONG---------------------------------------CATALOG----------------------------------------------------------------" + "\033[0m");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("%5s %-25s %-20s %-20s %-20s %-20s %-20s\n","SongId", "Language", "Artist", "Genre", "Album", "Song", "Duration");
            while(resultSet.next()){
                System.out.printf("%5d %-28s %-20s %-20s %-20s %-20s %-20s\n",resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(8));
                Music music=new Music(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                musicList.add(music);
                System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return musicList;
    }
}
