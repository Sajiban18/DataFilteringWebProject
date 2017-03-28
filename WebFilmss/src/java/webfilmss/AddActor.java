package webfilmss;

import filmbusinesslayer.FilmBusinessLayer;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import applicationvariables.ApplicationVariables.Database;

@ManagedBean(name="addActor")
@ViewScoped

public class AddActor
{
    //Validate each entry
    private String afname, asname, aid, fid;
    
    public void load() throws SQLException
    {
        FilmBusinessLayer fbl = new FilmBusinessLayer();
        Base base = new Base();
        base.Message(fbl.AddPerson(fid, afname, asname, aid, Database.ActorQuery));
    }
    
    public String getAFName(){return this.afname;}
        public void setAFName(String affname){this.afname = affname;}
    public String getASName(){return this.asname;}
        public void setASName(String asfname){this.asname = asfname;}
    public String getFID(){return this.fid;}
        public void setFID(String ffid){this.fid = ffid;}
    public String getAID(){return this.aid;}
        public void setAID(String afid){this.aid = afid;}
}
