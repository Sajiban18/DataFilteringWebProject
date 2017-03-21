package webfilmss;

import applicationvariables.ApplicationVariables.*;
import filmclasslayer.FilmClassLayer.*;
import filmbusinesslayer.*;
import filmclasslayer.FilmClassLayer;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="webFilmss")
@ViewScoped

public class WebFilmss implements Serializable
{         
    FilmClassLayer fcl = new FilmClassLayer();
    FilmBusinessLayer fbl = new FilmBusinessLayer();
    public String sfilm, director, actor, filmID, filmName, irating, directorID, directorName, actorID, actorName, filmYear, fyear, imdbRating;
    public String SelectedFilm, SelectedDirector, SelectedActor, SelectedFYear, SelectedIRating;
    public String FilmLink, DirectorLink, ActorLink;
    
    public List<String> SFilmVal, DirectorVal, ActorVal, FYearVal, IRatingVal, 
            SFilmLabel, DirectorLabel, ActorLabel, FYearLabel, IRatingLabel;
    
    public Map<String, String> SFilmOptions, DirectorOptions, ActorOptions, FYearOptions, IRatingOptions;
    
    //----------   LOADING TO WEB PAGE   ----------
    
    public void onload() throws IOException
    {
        if(isPostBack())
        {
            filmID = (SelectedFilm.equals(SystemValues.DropDownLists.DefaultValue)) ? null : SelectedFilm;
            directorID = (SelectedDirector.equals(SystemValues.DropDownLists.DefaultValue)) ? null : SelectedDirector;
            actorID = (SelectedActor.equals(SystemValues.DropDownLists.DefaultValue)) ? null : SelectedActor;
            fyear = (SelectedFYear.equals(SystemValues.DropDownLists.DefaultValue)) ? null : SelectedFYear;
            irating = (SelectedIRating.equals(SystemValues.DropDownLists.DefaultValue)) ? null : SelectedIRating;
                
            populateDropDownWithFilteredData(filmID, directorID, actorID, fyear, irating);
        }
        else
        {
            populateDropDownsWithOriginalData();
        } 
    }
    
    //----------   POPULATE DROP DOWN LIST WITH ORIGINAL/ FILTERED DATA   ----------
    
    public void populateDropDownsWithOriginalData() throws IOException
    {
        Films films = getFilms();
        
        List<SimplisticFilm> sFilms = fbl.GetDistinctSimplisticFilmsFromFilms(films);
        List<Director> directors = fbl.GetDistinctDirectorsFromFilms(films);
        List<Actor> actors = fbl.GetDistinctActorsFromFilms(films);
        List<String> fyears = fbl.GetDistinctFYearsFromFilms(films);
        List<String> iratings = fbl.GetDistinctIRatingsFromFilms(films);
        
        populateDropDownList(sFilms, directors, actors, fyears, iratings);
    }
    
    public void populateDropDownWithFilteredData(String filmID, String directorID, String actorID, String fyear, String irating) throws IOException
    {
        Films films = getFilms();
        
        Films tmp = fbl.GetFilmSubset(filmID, directorID, actorID, fyear, irating, films);
        
        List<SimplisticFilm> sFilms = (filmID == null) ? fbl.GetDistinctSimplisticFilmsFromFilms(tmp) : tmp.GetDistinctSimplisticFilm(filmID);
        List<Director> directors = (directorID == null) ? fbl.GetDistinctDirectorsFromFilms(tmp) : fbl.GetDistinctDirector(tmp, directorID);
        List<Actor> actors = (actorID == null) ? fbl.GetDistinctActorsFromFilms(tmp) : fbl.GetDistinctActor(tmp, actorID);
        List<String> fyears = (fyear == null) ? fbl.GetDistinctFYearsFromFilms(tmp) : fbl.GetDistinctFYear(tmp, fyear);
        List<String> iratings = (irating == null) ? fbl.GetDistinctIRatingsFromFilms(tmp) : fbl.GetDistinctIRating(tmp, irating);
        
        populateDropDownList(sFilms, directors, actors, fyears, iratings);
    }
    
    public void populateDropDownList(List<SimplisticFilm> sFilms, List<Director> directors, List<Actor> actors, List<String> fyears, List<String> iratings)
    {
        SFilmOptions = new LinkedHashMap<>();
        SFilmVal = sFilms.stream().map(object -> object.FilmID).collect(Collectors.toList());
        SFilmLabel = sFilms.stream().map(object -> object.FilmName).collect(Collectors.toList());
            if(sFilms.size()!= 1)
            {
                SFilmOptions.put(SystemValues.DropDownLists.DefaultText, SystemValues.DropDownLists.DefaultValue);
            }
            IntStream.range(0, SFilmVal.size()).forEach(i -> SFilmOptions.put(SFilmLabel.get(i), SFilmVal.get(i)));
        
        DirectorOptions = new LinkedHashMap<>();
        DirectorVal = directors.stream().map(object -> object.PersonID).collect(Collectors.toList());
        DirectorLabel = directors.stream().map(object -> object.PersonName).collect(Collectors.toList());
            if(directors.size()!= 1)
            {
                DirectorOptions.put(SystemValues.DropDownLists.DefaultText, SystemValues.DropDownLists.DefaultValue);
            }
            IntStream.range(0, DirectorVal.size()).forEach(i -> DirectorOptions.put(DirectorLabel.get(i), DirectorVal.get(i)));
        
        ActorOptions = new LinkedHashMap<>();
        ActorVal = actors.stream().map(object -> object.PersonID).collect(Collectors.toList());
        ActorLabel = actors.stream().map(object -> object.PersonName).collect(Collectors.toList());
            if(actors.size()!= 1)
            {
                ActorOptions.put(SystemValues.DropDownLists.DefaultText, SystemValues.DropDownLists.DefaultValue);
            }
            IntStream.range(0, ActorVal.size()).forEach(i -> ActorOptions.put(ActorLabel.get(i), ActorVal.get(i)));
        
        FYearOptions = new LinkedHashMap<>();
        FYearVal = fyears.stream().collect(Collectors.toList());
        FYearLabel = fyears.stream().collect(Collectors.toList());
            if(fyears.size() != 1)
            {
                FYearOptions.put(SystemValues.DropDownLists.DefaultText, SystemValues.DropDownLists.DefaultValue);
            }
            IntStream.range(0, FYearVal.size()).forEach(i -> FYearOptions.put(FYearLabel.get(i), FYearVal.get(i)));
            
        IRatingOptions = new LinkedHashMap<>();
        IRatingVal = iratings.stream().collect(Collectors.toList());
        IRatingLabel = iratings.stream().collect(Collectors.toList());
            if(iratings.size()!= 1)
            {
                IRatingOptions.put(SystemValues.DropDownLists.DefaultText, SystemValues.DropDownLists.DefaultValue);
            }
            IntStream.range(0, IRatingVal.size()).forEach(i -> IRatingOptions.put(IRatingLabel.get(i), IRatingVal.get(i)));
            
        if((sFilms.size() == 1) && (directors.size() == 1) && (actors.size() == 1) && (fyears.size() == 1) && (iratings.size() == 1))
        {
            FilmLink = ImdbPath.FilmURL + SFilmVal.get(0);
            DirectorLink = ImdbPath.PersonURL + DirectorVal.get(0);
            ActorLink = ImdbPath.PersonURL + ActorVal.get(0);
            filmName = SFilmLabel.get(0);
            directorName = DirectorLabel.get(0);
            actorName = ActorLabel.get(0);
            filmYear = FYearLabel.get(0);
            imdbRating = IRatingLabel.get(0);
        }
    }
    
    //----------   CHANGE DROP DOWN LIST VALUES   ----------
    
    public void changeFilm(ValueChangeEvent filmevent)
    {
        SelectedFilm = filmevent.getNewValue().toString();
    }
    
    public void changeDirector(ValueChangeEvent directorevent)
    {
        SelectedDirector = directorevent.getNewValue().toString();
    }
    
    public void changeActor(ValueChangeEvent actorevent)
    {
        SelectedActor = actorevent.getNewValue().toString();
    }
    
    public void changeFYear(ValueChangeEvent fyearevent)
    {
        SelectedFYear = fyearevent.getNewValue().toString();
    }
    
    public void changeIRating(ValueChangeEvent iratingevent)
    {
        SelectedIRating = iratingevent.getNewValue().toString();
    }
    
    public Map<String, String> getSFilmOptions(){return SFilmOptions;}
    public Map<String, String> getDirectorOptions(){return DirectorOptions;}
    public Map<String, String> getActorOptions(){return ActorOptions;}
    public Map<String, String> getFYearOptions(){return FYearOptions;}
    public Map<String, String> getIRatingOptions(){return IRatingOptions;}
    
    //----------   CALL FILMS LIST   ----------
    
    public Films getFilms()
    {
        try
        {
            Films films = fcl.new Films();
            films = fbl.GetFilms(CsvPath.MovieCSV);
            return films;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    //----------   RELOAD WEB PAGE   ----------
    
    public void reloadPage() throws IOException
    {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    //----------   IF IT'S A POST BACK   ----------
    
    public boolean isPostBack()
    {
        return FacesContext.getCurrentInstance().isPostback();        
    }
    
    public String getSFilm(){return this.sfilm;}
        public void setSFilm(String ssfilm){this.sfilm = ssfilm;}
    public String getDirector(){return this.director;}
        public void setDirector(String ddirector){this.director = ddirector;}
    public String getActor(){return this.actor;}
        public void setActor(String aactor){this.actor = aactor;}
    public String getFilmID(){return this.filmID;}
        public void setFilmID(String fid){this.filmID = fid;}
    public String getFilmName(){return this.filmName;}
        public void setFilmName(String fname){this.filmName = fname;}
    public String getIRating(){return this.irating;}
        public void setIRating(String rating){this.irating = rating;}
    public String getDirectorID(){return this.directorID;}
        public void setDirectorID(String did){this.directorID = did;}
    public String getDirectorName(){return this.directorName;}
        public void setDirectorName(String dname){this.directorName = dname;}
    public String getActorID(){return this.actorID;}
        public void setActorID(String aid){this.actorID = aid;}
    public String getActorName(){return this.actorName;}
        public void setActorName(String aname){this.actorName = aname;}
    public String getFYear(){return this.fyear;}
        public void setFYear(String year){this.fyear = year;}
    public String getFilmYear(){return this.filmYear;}
        public void setFilmYear(String year){this.filmYear = year;}
    public String getImdbRating(){return this.imdbRating;}
        public void setImdbRating(String irating){this.imdbRating = irating;}
    public String getFilmLink(){return this.FilmLink;}
        public void setFilmLink(String flink){this.FilmLink = flink;}
    public String getDirectorLink(){return this.DirectorLink;}
        public void setDirectorLink(String dlink){this.DirectorLink = dlink;}
    public String getActorLink(){return this.ActorLink;}
        public void setActorLink(String alink){this.ActorLink = alink;}
    
}
