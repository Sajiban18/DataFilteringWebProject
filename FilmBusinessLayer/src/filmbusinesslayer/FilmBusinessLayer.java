package filmbusinesslayer;

import filmclasslayer.FilmClassLayer;
import filmclasslayer.FilmClassLayer.*;
import filmdatalayer.*;
import filmutilitylayer.FilmUtilityLayer;
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
