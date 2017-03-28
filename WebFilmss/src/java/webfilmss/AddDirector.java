package webfilmss;

import applicationvariables.ApplicationVariables.Database;
import filmbusinesslayer.FilmBusinessLayer;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="addDirector")
@ViewScoped

public class AddDirector
{
    //Validate each entry
    private String dfname, dsname, did, fid;
    
    public void load() throws SQLException
    {
        FilmBusinessLayer fbl = new FilmBusinessLayer();
        Base base = new Base();
        base.Message(fbl.AddPerson(fid, dfname, dsname, did, Database.DirectorQuery));
    }
    
    public String getDFName(){return this.dfname;}
        public void setDFName(String dffname){this.dfname = dffname;}
    public String getDSName(){return this.dsname;}
        public void setDSName(String dsfname){this.dsname = dsfname;}
    public String getFID(){return this.fid;}
        public void setFID(String ffid){this.fid = ffid;}
    public String getDID(){return this.did;}
        public void setDID(String dfid){this.did = dfid;}
}
