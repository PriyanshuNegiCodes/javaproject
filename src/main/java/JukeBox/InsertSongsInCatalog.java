package JukeBox;

import JukeBox.Database.TablesImp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/// Insert the song in the catalog from this class
public class InsertSongsInCatalog extends CreatePlaylist {
    Scanner sc = new Scanner(System.in);
    TablesImp tablesImp=new TablesImp();

    public String insertSongs(List<Songs> songsList) {
        try {
            st = getConnection().createStatement();
            for (Songs data : songsList) {
            st.executeUpdate(" INSERT INTO song (LanguageId, AlbumId, ArtistId, GenreId, SongName, Path, Duration)\n" +
                    "VALUES ("+data.getLanguageId()+", "+data.getAlbumId()+", "+data.getArtistid()+", "+data.getGenreId()+", '"+data.getSongName()+"', '"+data.getPath()+"', '"+data.getDuration()+"')");
            }
            System.out.println("Song inserted");

        } catch ( SQLException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return "Song Inserted in the Catalog";
    }
    public List<Songs> insertSong(){
        List<Songs> list=new ArrayList<>();
        TimeOperations timeOperations=new TimeOperations();
        int input;
        do{
            System.out.println("Enter the languageID");
            int languageId=sc.nextInt();
            while(tablesImp.checkData(languageId, "language")){
                 System.out.println("Id already exists, enter the id again");
                languageId=sc.nextInt();
            }
            System.out.println("Enter the id of the artist");
            int artistId=sc.nextInt();
            while(tablesImp.checkData(artistId, "artist")){
                System.out.println("Id already exists, enter the id again");
                artistId=sc.nextInt();
            }
            System.out.println("Enter the id of the Genre");
            int genreId=sc.nextInt();
            while(tablesImp.checkData(genreId, "genre")){
                System.out.println("Id already exists, enter the id again");
                genreId=sc.nextInt();
            }
            System.out.println("Enter the id of the album");
            int albumId=sc.nextInt();
            while(tablesImp.checkData(albumId, "album")){
                System.out.println("Id already exists, enter the id again");
                albumId=sc.nextInt();
            }
            System.out.println("Enter the name of the song");
            String SongName=sc.next();
            String commonLocation="C:\\Users\\admin\\IdeaProjects\\JavaProject\\src\\main\\resources\\"+SongName+".wav";
            long duration=timeOperations.calculateSongLength(commonLocation);
            commonLocation=commonLocation.replace("\\","/");
            Songs songs=new Songs(languageId, albumId, artistId, genreId, SongName, commonLocation, timeOperations.timer1(duration));

            list.add(songs);
            System.out.println("Press 1 if you want to insert more Songs else 0");
            input= sc.nextInt();
        }while (input==1);
        return list;
    }
}

