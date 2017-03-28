package webfilmss;

import filmbusinesslayer.FilmBusinessLayer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

@ManagedBean(name="uploadData")
@ViewScoped

public class UploadData
{
    private Part file;
    private String fileName, filePath;
    
    public void upload() throws IOException, SQLException, ClassNotFoundException
    {
        try(InputStream input = file.getInputStream())
        {
            fileName = "CSVDATA " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".csv";
            filePath = "C:\\Users\\sajiban_18\\Documents\\";
            Files.copy(input, new File(filePath, fileName).toPath());
            FilmBusinessLayer fbl = new FilmBusinessLayer();
            
            int[] temp = fbl.UploadData(filePath+fileName);
            
            Base base = new Base();
            base.Message(temp[0], temp[1], temp[2]);
        }
        catch(IOException e)
        {
            
        }
    }
    
    public Part getFile(){return file;}
        public void setFile(Part ffile){this.file = ffile;}
}
