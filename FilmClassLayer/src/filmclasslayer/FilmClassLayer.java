package filmclasslayer;
import java.util.*;
import java.util.stream.Collectors;

public class FilmClassLayer
{
    public class Films extends ArrayList<Film>
    {
        public Films() {}
        
        public Films(List<Film> films)
        {
            this.addAll(films);
        }
        
        //----------   FILMS   ----------
        
        public Films GetFilmsFilteredSubset(String filmID, String directorID, String actorID, String fyear, String irating)
        {
            Films tmpFilms = new Films(); 
            this.stream().filter(f -> f.FilmID.equals(filmID == null ? f.FilmID : filmID))
                         .filter(f -> f.Directors.stream().anyMatch(p -> p.PersonID.equals(directorID == null ? p.PersonID : directorID)))
                         .filter(f -> f.Actors.stream().anyMatch(p -> p.PersonID.equals(actorID == null ?  p.PersonID : actorID)))
                         .filter(f -> f.FilmYear.equals(fyear == null ? f.FilmYear : fyear))
                         .filter(f -> f.ImdbRating.equals(irating == null ? f.ImdbRating : irating))
                         .map(i -> tmpFilms.add(i))
                         .collect(Collectors.toList());
            //tmpFilms.sort(Comparator.comparing(compare -> compare.FilmName));
            //tmpFilms.sort(Comparator.comparing(compare -> compare.FilmID));
            return new Films(tmpFilms);
        }
        
        public List<SimplisticFilm> ToListOfSimplisticFilms()
        {
            List<SimplisticFilm> sf = new ArrayList();
            this.stream().map(f -> sf.add(f.GetSimplisticFilm())).collect(Collectors.toList());
            sf.sort(Comparator.comparing(compare -> compare.FilmName));
            return sf;
        }
        
        public List<SimplisticFilm> GetDistinctSimplisticFilm(String filmID)
        {
            List<SimplisticFilm> dsf = new ArrayList();
            this.stream().filter(f -> f.FilmID.equals(filmID)).map(f -> dsf.add(f.GetSimplisticFilm())).collect(Collectors.toList());
            return dsf;
        }
        
        //----------   DIRECTOR   ----------
        
        public List<Director> ToListDistinctDirector()
        {
            List<Director> directors = new ArrayList();
            this.stream().flatMap(film -> film.Directors.stream()
                                                        .filter(tmplist1 -> directors.stream()
                                                            .noneMatch(tmplist2 -> tmplist2.getPersonID()
                                                                .equals(tmplist1.getPersonID()))))
                                                        .map(ndirectors -> directors.add(ndirectors))
                                                        .collect(Collectors.toList());
            directors.sort(Comparator.comparing(compare -> compare.getPersonName()));
            return directors;
        }
        
        public List<Director> GetDistinctDirector(String directorID)
        {
            List<Director> directors = new ArrayList();
            this.stream().flatMap(film -> film.Directors.stream()
                                                        .filter(tmplist1 -> directors.stream()
                                                            .noneMatch(tmplist2 -> tmplist2.getPersonID()
                                                                .equals(tmplist1.getPersonID())) && tmplist1.getPersonID()
                                                                .equals(directorID)))
                                                        .map(ndirectors -> directors.add(ndirectors))
                                                        .collect(Collectors.toList());
            
            return directors;
        }
        
        //----------   ACTOR   ----------
        
        public List<Actor> ToListDistinctActor()
        {
            List<Actor> actors = new ArrayList();
            this.stream().flatMap(film -> film.Actors.stream()
                                                        .filter(tmplist1 -> actors.stream()
                                                            .noneMatch(tmplist2 -> tmplist2.getPersonID()
                                                                .equals(tmplist1.getPersonID()))))
                                                        .map(nactors -> actors.add(nactors))
                                                        .collect(Collectors.toList());
            
            actors.sort(Comparator.comparing(compare -> compare.getPersonName()));
            return actors;
        }
        
        public List<Actor> GetDistinctActor(String actorID)
        {
            List<Actor> actors = new ArrayList();
            this.stream().flatMap(film -> film.Actors.stream()
                                                        .filter(tmplist1 -> actors.stream()
                                                            .noneMatch(tmplist2 -> tmplist2.getPersonID()
                                                                .equals(tmplist1.getPersonID())) && tmplist1.getPersonID()
                                                                .equals(actorID)))
                                                        .map(nactors -> actors.add(nactors))
                                                        .collect(Collectors.toList());
            
            return actors;
        }
        
        //----------   YEAR   ----------
        public List<String> ToListDistinctFYear()
        {
            List<String> fyears = new ArrayList();
            this.stream().filter(tmplist1 -> fyears.stream()
                                                        .noneMatch(tmplist2 -> tmplist2
                                                            .equals(tmplist1.FilmYear)))
                                                        .map(f -> fyears.add(f.getFilmYear()))
                                                        .collect(Collectors.toList());
            
            Collections.sort(fyears);
            return fyears;
        }
        
        public List<String> GetDistinctFYear(String fyear)
        {
            List<String> fyears = new ArrayList();
            this.stream().filter(f -> f.FilmYear.equals(fyear)).map(f -> fyears.add(f.getFilmYear())).collect(Collectors.toList());
            return fyears;
        }
        
        //----------   IMDB RATING   ----------
        public List<String> ToListDistinctIRating()
        {
            List<String> iratings = new ArrayList();
            this.stream().filter(tmplist1 -> iratings.stream()
                                                        .noneMatch(tmplist2 -> tmplist2
                                                            .equals(tmplist1.ImdbRating)))
                                                        .map(f -> iratings.add(f.getImdbRating()))
                                                        .collect(Collectors.toList());
            
            Collections.sort(iratings);
            return iratings;
        }
        
        public List<String> GetDistinctIRating(String irating)
        {
            List<String> iratings = new ArrayList();
            this.stream().filter(f -> f.ImdbRating.equals(irating)).map(f -> iratings.add(f.getImdbRating())).collect(Collectors.toList());
            return iratings;
        }
    }
    
    
    public class Film extends SimplisticFilm
    {
        public String ImdbRating, FilmYear;
        public List<Director> Directors;
        public List<Actor> Actors;
        
        public Film()
        {
            this.Directors = new ArrayList<>();
            this.Actors = new ArrayList<>();
        }            
        public Film(String filmID, String filmName, String imdbRating, String filmYear)
        {
            super(filmID, filmName);
            this.ImdbRating = imdbRating;
            this.FilmYear = filmYear;
            this.Directors = new ArrayList<>();
            this.Actors = new ArrayList<>();
        }            
        public Film(String filmID, String filmName, String imdbRating, 
                List<Director> directors, List<Actor> actors, String filmYear)
        {
            super(filmID, filmName);
            this.ImdbRating = imdbRating;
            this.FilmYear = filmYear;
            this.Directors = directors;
            this.Actors = actors;
        }
        
        public SimplisticFilm GetSimplisticFilm()
        {
            return (SimplisticFilm) this; 
        }
        
        public String getImdbRating(){return this.ImdbRating;}
        void setImdbRating(String imdbRating){this.ImdbRating = imdbRating;}
        public String getFilmYear(){return this.FilmYear;}
        void setFilmYear(String filmYear){this.FilmYear = filmYear;}

        public List<Director> getDirectors(){return this.Directors;}
        public List<Director> setDirectors(){return this.Directors;}
        public List<Actor> getActors(){return this.Actors;}
        public List<Actor> setActors(){return this.Actors;}
    }

    public class SimplisticFilm
    {
        public String FilmID, FilmName;
        
        public SimplisticFilm(){}
        public SimplisticFilm(String filmID, String filmName)
        {
            this.FilmID = filmID;
            this.FilmName = filmName;
        }

        public boolean isValid()
        {
            if((this.FilmID == null) || (this.FilmID.isEmpty()))
            {
                return false;
            }
            else if((this.FilmName == null) || (this.FilmName.isEmpty()))
            {
                return false;
            }    
            else
            {
                return true;
            }
        }
        
        public String getFilmID(){return FilmID;}
        public void setFilmID(String filmID){this.FilmID = filmID;}
        public String getFilmName(){return FilmName;}
        public void setFilmName(String filmName){this.FilmName = filmName;}
    }
    
    public class Director extends Person
    {
        public Director(){}
        public Director(String directorID, String directorName)
        {
            super (directorID, directorName);
        }
    }

    public class Actor extends Person
    {
        public Actor() {}
        public Actor(String actorID, String actorName)
        {
            super(actorID ,actorName);
        }
    }
    
    public class Person
    {
        public String PersonID, PersonName;
        
        public Person(){}
        public Person(String personID, String personName)
        {
            this.PersonID = personID;
            this.PersonName = personName;
        }
        
        public Person GetPerson()
        {
            return (Person) this;
        }
        
        public String getPersonID(){return this.PersonID;}
        public void setPersonID(String personID){this.PersonID = personID;}
        public String getPersonName(){return this.PersonName;}
        public void setPersonName(String personName){this.PersonName = personName;}
    }
}
