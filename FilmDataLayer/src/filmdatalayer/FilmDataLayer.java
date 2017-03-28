package filmdatalayer;

import filmclasslayer.FilmClassLayer.*;
import applicationvariables.ApplicationVariables.DataIDs.*;
import applicationvariables.ApplicationVariables.Database;
import applicationvariables.ApplicationVariables.SystemValues.*;
import com.opencsv.CSVReader;
import filmclasslayer.FilmClassLayer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class FilmDataLayer
{
    String[] csvline = new String[10];
    String[] sqlline = new String[10];
    FilmClassLayer fcl = new FilmClassLayer();
    
    static Connection conn = null;
    Films films = null;
    
    //----------   CONSTRUCTOR   ----------
    public FilmDataLayer() throws SQLException
    {
        try
        {
           //----------   CONNECTION TO MYSQL   ---------- 
           Class.forName(Database.dbDriver);
           conn = DriverManager.getConnection(Database.dbURL, Database.username, Database.password);
           films = fcl.new Films();
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //----------   ADD FILM TO MYSQL   ----------
    
    public int AddFilmToSql(String fid, String fname, String fyear, String frating) throws SQLException
    {
        try
        {
            String query = Database.FilmQuery;
            
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, fid);
            stmt.setString(2, fname);
            stmt.setString(3, fyear);
            stmt.setString(4, frating);
            stmt.registerOutParameter(5, java.sql.Types.SMALLINT);
            
            stmt.execute();
            
            int message = stmt.getInt(5);
            return message;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //stmt.close();
            //conn.close();
        }
        return 10;
    }
    
    //----------   ADD PERSON TO MYSQL   ----------
    
    public int AddPersonToSql(String fid, String pfname, String psname, String pid, String query) throws SQLException
    {
        try(CallableStatement stmt = conn.prepareCall(query))
        {
            stmt.setString(1, fid);
            stmt.setString(2, pid);
            stmt.setString(3, pfname);
            stmt.setString(4, psname);
            stmt.registerOutParameter(5, java.sql.Types.SMALLINT);
            
            stmt.execute();
            
            int message = stmt.getInt(5);
            return message;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //conn.close();
        }
        return 10;
    }
    
    //---------- GET FILM   ----------
    
    public Films GetSqlFilm() throws SQLException
    {
        try
        {
            String query = Database.FilmsQuery;
            //Scrollable result sets
            CallableStatement stmt = conn.prepareCall(query);
            
            boolean isResultSet = stmt.execute();
            if(!isResultSet){}
            
            while (isResultSet)
            {
            //----------   FILM   ----------
            ResultSet res = stmt.getResultSet();
            while (res.next())
            {
                sqlline[0] = res.getString(SqlLists.FilmID);
                sqlline[1] = res.getString(SqlLists.FilmName);
                sqlline[2] = res.getString(SqlLists.ImdbRating);
                sqlline[3] = res.getString(SqlLists.DirectorID);
                sqlline[4] = res.getString(SqlLists.DirectorName);
                sqlline[5] = res.getString(SqlLists.ActorID);
                sqlline[6] = res.getString(SqlLists.ActorName);
                sqlline[7] = res.getString(SqlLists.FilmYear);
                
                films = LoadData(true, sqlline, films);
            }
            res.close();
        
            }
            stmt.close();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return films;
    }
    
    public Films GetFilm(String csvPath) throws FileNotFoundException, IOException
    {
        films = fcl.new Films();
        
        try(CSVReader csv = new CSVReader(new FileReader(csvPath));)
        {
            String[] headers = csv.readNext();
            while((csvline = csv.readNext()) != null)
            {
                films = LoadData(false, csvline, films);
            }
        }
        catch(IOException ex)
        {
            
        }
        return films;
    }
    
    //----------   GET CSV DATA   ----------
    
    /*public Films GetCsvData(String csvPath)
    {   
        try(CSVReader csv = new CSVReader(new FileReader(csvPath), ',');)
        {
            String[] headers = csv.readNext(); //headers is assigned with the first line of file
            
            while((csvline = csv.readNext()) != null)
            {
                LoadData(false, csvline);
            }
        }
        catch(IOException e)
        {
            //do something
        }
        return films;
    }*/
    
    //----------   LOAD DATA   ----------
    
    public Films LoadData(boolean SQL, String[] line, Films films)
    {
        Films tmpfilms = films;
        if (SQL == true)
        {
            if(tmpfilms.stream().anyMatch(item -> item.FilmID.equals(sqlline[Sql_Movies.FilmID])))
            {
                Film tmpFilm = tmpfilms.stream().filter(item -> item.FilmID.equals(sqlline[Sql_Movies.FilmID])).findFirst().get();
                if(tmpFilm.Directors.stream().anyMatch(item -> item.PersonID.equals(sqlline[Sql_Movies.DirectorID]))){}
                else
                {
                    Director director = this.getDirectorFromSQL(sqlline);
                    tmpFilm.Directors.add(director);
                }
                if(tmpFilm.Actors.stream().anyMatch(item -> item.PersonID.equals(sqlline[Sql_Movies.ActorID]))){}
                else
                {
                    Actor actor = this.getActorFromSQL(sqlline);
                    tmpFilm.Actors.add(actor);
                }
            }
            else
            {
                Film film = this.getFilmFromSQL(sqlline);
                tmpfilms.add(film);
            }
        }
        else
        {   
            if(tmpfilms.stream().anyMatch(item -> item.FilmID.equals(csvline[CsvItem_Movies.FilmID])))
            {
                Film tmpFilm = tmpfilms.stream().filter(item -> item.FilmID.equals(csvline[CsvItem_Movies.FilmID])).findFirst().get();
                if(tmpFilm.Directors.stream().anyMatch(item -> item.PersonID.equals(csvline[CsvItem_Movies.DirectorID]))){}
                else
                {
                    Director director = this.getDirectorFromCSV(csvline);
                    tmpFilm.Directors.add(director);
                }
                if(tmpFilm.Actors.stream().anyMatch(item -> item.PersonID.equals(csvline[CsvItem_Movies.ActorID]))){}
                else
                {
                    Actor actor = this.getActorFromCSV(csvline);
                    tmpFilm.Actors.add(actor);
                }
            }
            else
            {
                Film film = this.getFilmFromCSV(csvline);
                tmpfilms.add(film);
            }
        }
        return tmpfilms;
    }
    
    //----------   GET FILM, ACTOR & DIRECTOR FROM CSV   ----------
    
    private Director getDirectorFromCSV(String[] record)
    {
        Director director = fcl.new Director(record[CsvItem_Movies.DirectorID].trim(), record[CsvItem_Movies.DirectorName].trim());
        return director;
    }
    
    private Actor getActorFromCSV(String[] record)
    {
        Actor actor = fcl.new Actor(record[CsvItem_Movies.ActorID].trim(), record[CsvItem_Movies.ActorName].trim());
        return actor;
    }
    
    private Film getFilmFromCSV(String[] record)
    {
        Director director = this.getDirectorFromCSV(record);
        Actor actor = this.getActorFromCSV(record);
        Film film = fcl.new Film(record[CsvItem_Movies.FilmID].trim(), record[CsvItem_Movies.FilmName].trim(), record[CsvItem_Movies.ImdbRating].trim(), record[CsvItem_Movies.FilmYear].trim());
        film.Directors.add(director);
        film.Actors.add(actor);
        return film;
    }
    
    //----------   GET FILM, ACTOR & DIRECTOR FROM MYSQL   ----------
    
    private Director getDirectorFromSQL(String[] record)
    {
        Director director = fcl.new Director(record[Sql_Movies.DirectorID].trim(), record[Sql_Movies.DirectorName].trim());
        return director;
    }
    
    private Actor getActorFromSQL(String[] record)
    {
        Actor actor = fcl.new Actor(record[Sql_Movies.ActorID].trim(), record[Sql_Movies.ActorName].trim());
        return actor;
    }
    
    private Film getFilmFromSQL(String[] record)
    {
        Director director = this.getDirectorFromSQL(record);
        Actor actor = this.getActorFromSQL(record);
        Film film = fcl.new Film(record[Sql_Movies.FilmID].trim(), record[Sql_Movies.FilmName].trim(), record[Sql_Movies.ImdbRating].trim(), record[Sql_Movies.FilmYear].trim());
        film.Directors.add(director);
        film.Actors.add(actor);
        return film;
    }
}