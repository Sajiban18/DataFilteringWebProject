package applicationvariables;

public class ApplicationVariables
{
    public ApplicationVariables(){}
        
    public static class CsvPath
    {
        public static String MovieCSV = "C:\\Users\\sajiban_18\\Documents\\NetBeansProjects\\WebFilmss\\src\\java\\webfilmss\\TestData.csv";
    }
    
    public static class ImdbPath
    {
        public static String FilmURL = "http://www.imdb.com/title/tt";
        public static String PersonURL = "http://www.imdb.com/name/nm";
    }
    
    public static class Database
    {
        public static String dbDriver = "com.mysql.jdbc.Driver";
        public static String dbURL = "jdbc:mysql://localhost:3306/db_movie?autoReconnect=true&useSSL=false";
        public static String username = "root";
        public static String password = "Saji_SQL!123";
        public static String FilmsQuery = "{CALL getFilmDetails()}";
        public static String FilmQuery = "{CALL addFilm(?,?,?,?,?)}";
        public static String ActorQuery = "{CALL addActor(?,?,?,?,?)}";
        public static String DirectorQuery = "{CALL addDirector(?,?,?,?,?)}";
    }
    
    public static class SystemValues
    {
        public static class DropDownLists
        {
            public static String DefaultValue = "NOT SELECTED";
            public static String DefaultText = "----- SELECT -----";
        }
        
        public static class SqlLists
        {
            public static int FilmID = 1;
            public static int FilmName = 2;
            public static int ImdbRating = 3;
            public static int DirectorID = 4;
            public static int DirectorName = 5;
            public static int ActorID = 6;
            public static int ActorName = 7;
            public static int FilmYear = 8;
        }
    }
    
    public static class Messages
    {
        public static class AddMessage
        {
            public static String Zero = "SQL Exception Warning!!!";
            public static String One = "Film Already Exists!!!";
            public static String Two = "Film Has Been Successfully Added!!!";
            public static String Three = "Actor Already Linked With Film!!!";
            public static String Four = "Actor Has Been Successfully Linked To Film!!!";
            public static String Five = "Actor Has Been Successfully Added and Linked To Film!!!";
            public static String Six = "Film Not Available!!!";
            public static String Seven = "Director Already Linked With Film!!!";
            public static String Eight = "Director Has Been Successfully Linked To Film!!!";
            public static String Nine = "Director Has Been Successfully Added and Linked To Film!!!";
            
            public static String ms[] = {Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine};
            
        }
    }
    
    public static class DataIDs
    {
        public static class CsvItem_Movies
        {
            public static int FilmID = 0;
            public static int FilmName = 1;
            public static int ImdbRating = 2;
            public static int DirectorID = 3;
            public static int DirectorName = 4;
            public static int ActorID = 5;
            public static int ActorName = 6;
            public static int FilmYear = 7;
        }
        
        public static class Sql_Movies
        {
            public static int FilmID = 0;
            public static int FilmName = 1;
            public static int ImdbRating = 2;
            public static int DirectorID = 3;
            public static int DirectorName = 4;
            public static int ActorID = 5;
            public static int ActorName = 6;
            public static int FilmYear = 7;
        }
    }
}