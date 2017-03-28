package filmbusinesslayer;

import filmclasslayer.FilmClassLayer;
import filmclasslayer.FilmClassLayer.*;
import applicationvariables.ApplicationVariables.Database;
import filmdatalayer.*;
import filmutilitylayer.FilmUtilityLayer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.ehcache.Cache;


public class FilmBusinessLayer 
{
    FilmClassLayer fcl = new FilmClassLayer();
    static Cache cache = FilmUtilityLayer.CacheData();
    
    public int AddFilm(String fid, String fname, String fyear, String frating) throws SQLException
    {
        FilmDataLayer fdl = new FilmDataLayer();
        int outcome = fdl.AddFilmToSql(fid, fname, fyear, frating);
        if(outcome == 2)
        {
            FilmUtilityLayer.CacheData().clear();
            return outcome;
        }
        else
        {
            return outcome;
        }
    }
    
    public int AddPerson(String fid, String dfname, String dsname, String did, String query) throws SQLException
    {
        FilmDataLayer fdl = new FilmDataLayer();
        int outcome = fdl.AddPersonToSql(fid, dfname, dsname, did, query);
        if((outcome == 8 || outcome == 9) || (outcome == 4 || outcome == 5))
        {
            FilmUtilityLayer.CacheData().clear();
            return outcome;
        }
        else
        {
            return outcome;
        }
    }
    
    public int[] UploadData(String csvPath) throws SQLException, IOException, ClassNotFoundException
    {
        Films films = new FilmDataLayer().GetFilm(csvPath);
        int filmoutcome = 10, actoroutcome = 10, directoroutcome = 10;
        
        Class.forName("com.mysql.jdbc.Driver");
        FilmDataLayer fdl = new FilmDataLayer();
        
        for(Film film : films)
        {
            filmoutcome = fdl.AddFilmToSql(film.FilmID, film.FilmName, film.FilmYear, film.ImdbRating);
            for(Actor actor : film.Actors)
            {
                FilmUtilityLayer ful = new FilmUtilityLayer();
                String[] AName = ful.SplitNames(actor.PersonName);
                String AFirstname = AName[0], ASurname = AName[1];
                
                actoroutcome = fdl.AddPersonToSql(film.FilmID, AFirstname, ASurname, actor.PersonID, Database.ActorQuery);
            }
            for(Director director : film.Directors)
            {
                FilmUtilityLayer ful = new FilmUtilityLayer();
                String[] DName = ful.SplitNames(director.PersonName);
                String DFirstname = DName[0], DSurname = DName[1];
                
                directoroutcome = fdl.AddPersonToSql(film.FilmID, DFirstname, DSurname, director.PersonID, Database.DirectorQuery);
            }
        }
        
        if((filmoutcome == 2) && (actoroutcome == 4 || actoroutcome == 5) && (directoroutcome == 8 || directoroutcome == 9))
        {
            FilmUtilityLayer.CacheData().clear();
            int[] outcome = {filmoutcome, actoroutcome, directoroutcome};
            return outcome;
        }
        else
        {
            int[] outcome = {filmoutcome, actoroutcome, directoroutcome};
            return outcome;
        }
    }
    
    public Films GetFilms(String csvPath)
    {
        try
        {
            FilmDataLayer fdl = new FilmDataLayer();
            if (FilmUtilityLayer.get(cache) == null)
            {
                Films film = fdl.GetSqlFilm();
                //Films film = fdl.GetCsvData(csvPath);
                FilmUtilityLayer.put(film, cache);
            }
            return FilmUtilityLayer.get(cache);
        }
        catch(SQLException e)
        {
            return null;
        }
    }
    
    //----------   FILM   ----------
    
    public List<SimplisticFilm> GetDistinctSimplisticFilmsFromFilms(Films films)
    {
        return (films == null) ? null : films.ToListOfSimplisticFilms();
    }
    
    public Films GetFilmSubset(String filmID, String directorID, String actorID, String fyear, String irating, Films films)
    {
        return films.GetFilmsFilteredSubset(filmID, directorID, actorID, fyear, irating);
    }
    
    //----------   DIRECTOR   ----------
    
    public List<Director> GetDistinctDirectorsFromFilms(Films films)
    {
        return (films == null) ? null : films.ToListDistinctDirector();
    }
    
    public List<Director> GetDistinctDirector(Films films, String directorID)
    {
        return films.GetDistinctDirector(directorID);
    }
    
    //----------   ACTOR   ----------
    
    public List<Actor> GetDistinctActorsFromFilms(Films films)
    {
        return (films == null) ? null : films.ToListDistinctActor();
    }
    
    public List<Actor> GetDistinctActor(Films films, String actorID)
    {
        return films.GetDistinctActor(actorID);
    }
    
    //----------   YEAR   ----------
    
    public List<String> GetDistinctFYearsFromFilms(Films films)
    {
        return (films == null) ? null : films.ToListDistinctFYear();
    }
    
    public List<String> GetDistinctFYear(Films films, String fyear)
    {
        return films.GetDistinctFYear(fyear);
    }
    
    //----------   IMDB RATING   ----------
    
    public List<String> GetDistinctIRatingsFromFilms(Films films)
    {
        return (films == null) ? null : films.ToListDistinctIRating();
    }
    
    public List<String> GetDistinctIRating(Films films, String irating)
    {
        return films.GetDistinctIRating(irating);
    }
}
