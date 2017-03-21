package webfilmss;

import webfilmss.Base;
import filmbusinesslayer.FilmBusinessLayer;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="addFilm")
@ViewScoped

public class AddFilm
{
    //Validate each entry
    private String fname, fid, fyear, frating;
    
    public void load() throws IOException, SQLException, InterruptedException
    {
        FilmBusinessLayer fbl = new FilmBusinessLayer();
        Base base = new Base();
        base.Message(fbl.AddFilm(fid, fname, fyear, frating));
        //Thread.sleep(5000);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    public String getFName(){return this.fname;}
        public void setFName(String ffname){this.fname = ffname;}
    public String getFID(){return this.fid;}
        public void setFID(String ffid){this.fid = ffid;}
    public String getFRating(){return this.frating;}
        public void setFRating(String ffrating){this.frating = ffrating;}
    public String getFYear(){return this.fyear;}
        public void setFYear(String ffyear){this.fyear = ffyear;}
}
