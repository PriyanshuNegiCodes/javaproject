package JukeBox.Playlist;

import JukeBox.Database.TablesImp;
import JukeBox.Player.TimeOperations;
import JukeBox.connector.Connector;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/// Insert the song in the catalog from this class
public class InsertSongsInCatalog extends Connector {
    Scanner sc = new Scanner(System.in);
    TablesImp tablesImp=new TablesImp();
    static Statement st;
//    public String insertSongs(List<Songs> songsList) {
//        try {
//            st = getConnection().createStatement();
//            for (Songs data : songsList) {
//            st.executeUpdate(" INSERT INTO song (LanguageId, AlbumId, ArtistId, GenreId, SongName, Path, Duration)\n" +
//                    "VALUES ("+data.getLanguageId()+", "+data.getAlbumId()+", "+data.getArtistid()+", "+data.getGenreId()+", '"+data.getSongName()+"', '"+data.getPath()+"', '"+data.getDuration()+"')");
//            }
//            System.out.println("Song inserted");
//
//        } catch ( SQLException e) {
//            System.out.println("File not found: " + e.getMessage());
//        }
//        return "Song Inserted in the Catalog";
//    }
//    public List<Songs> insertSong(){
//        List<Songs> list=new ArrayList<>();
//        TimeOperations timeOperations=new TimeOperations();
//        int input;
//        do{
//            System.out.println("Enter the languageID");
//            int languageId=sc.nextInt();
//            while(tablesImp.checkData(languageId, "language")){
//                 System.out.println("Id already exists or is not there in the database, enter the id again");
//                languageId=sc.nextInt();
//            }
//            System.out.println("Enter the id of the artist");
//            int artistId=sc.nextInt();
//            while(tablesImp.checkData(artistId, "artist")){
//                System.out.println("Id already exists or is not there in the database, enter the id again");
//                artistId=sc.nextInt();
//            }
//            System.out.println("Enter the id of the Genre");
//            int genreId=sc.nextInt();
//            while(tablesImp.checkData(genreId, "genre")){
//                System.out.println("Id already exists or is not there in the database, enter the id again");
//                genreId=sc.nextInt();
//            }
//            System.out.println("Enter the id of the album");
//            int albumId=sc.nextInt();
//            while(tablesImp.checkData(albumId, "album")){
//                System.out.println("Id already exists or is not there in the database, enter the id again");
//                albumId=sc.nextInt();
//            }
//            System.out.println("Enter the name of the song");
//            String SongName=sc.next();
//            String commonLocation="D:\\NIIT\\Course2\\javaproject\\src\\main\\resources\\"+SongName+".wav";
//            long duration=timeOperations.calculateSongLength(commonLocation);
//            commonLocation=commonLocation.replace("\\","/");
//            Songs songs=new Songs(languageId, albumId, artistId, genreId, SongName, commonLocation, timeOperations.timer1(duration));
//
//            list.add(songs);
//            System.out.println("Press 1 if you want to insert more Songs else 0");
//            input= sc.nextInt();
//        }while (input==1);
//        return list;
//    }
//public String insertSongs(List<Songs> songsList) {
//    try {
//        st = getConnection().createStatement();
//        for (Songs data : songsList) {
//            st.executeUpdate(" INSERT INTO song (LanguageId, AlbumId, ArtistId, GenreId, SongName, Path, Duration)\n" +
//                    "VALUES ("+data.getLanguageId()+", "+data.getAlbumId()+", "+data.getArtistid()+", "+data.getGenreId()+", '"+data.getSongName()+"', '"+data.getPath()+"', '"+data.getDuration()+"')");
//        }
//        System.out.println("Song inserted");
//
//    } catch ( SQLException e) {
//        System.out.println("File not found: " + e.getMessage());
//    }
//    return "Song Inserted in the Catalog";
//}
    public String insertSong(){
        TimeOperations timeOperations=new TimeOperations();
        int input;
        do{
            System.out.println("Enter the languageID");
            int languageId=sc.nextInt();
            while(tablesImp.checkData(languageId, "language")){
                System.out.println("Id already exists or is not there in the database, enter the id again");
                languageId=sc.nextInt();
            }
            System.out.println("Enter the id of the artist");
            int artistId=sc.nextInt();
            while(tablesImp.checkData(artistId, "artist")){
                System.out.println("Id already exists or is not there in the database, enter the id again");
                artistId=sc.nextInt();
            }
            System.out.println("Enter the id of the Genre");
            int genreId=sc.nextInt();
            while(tablesImp.checkData(genreId, "genre")){
                System.out.println("Id already exists or is not there in the database, enter the id again");
                genreId=sc.nextInt();
            }
            System.out.println("Enter the id of the album");
            int albumId=sc.nextInt();
            while(tablesImp.checkData(albumId, "album")){
                System.out.println("Id already exists or is not there in the database, enter the id again");
                albumId=sc.nextInt();
            }
            System.out.println("Enter the name of the song");
            String SongName=sc.next();
            String commonLocation="D:\\NIIT\\Course2\\javaproject\\src\\main\\resources\\"+SongName+".wav";
            long duration=timeOperations.calculateSongLength(commonLocation);
            commonLocation=commonLocation.replace("\\","/");
            try {
                st = getConnection().createStatement();
                st.executeUpdate(" INSERT INTO song (LanguageId, AlbumId, ArtistId, GenreId, SongName, Path, Duration)\n" +
                        "VALUES ("+languageId+", "+albumId+", "+artistId+", "+genreId+", '"+SongName+"', '"+commonLocation+"', '"+timeOperations.timer1(duration)+"')");

                System.out.println("Song inserted");

            } catch ( SQLException e) {
                System.out.println("File not found: " + e.getMessage());
            }
            System.out.println("Press 1 if you want to insert more Songs else 0");
            input= sc.nextInt();
        }while (input==1);
        return "Song Inserted in the Catalog";
    }
}

