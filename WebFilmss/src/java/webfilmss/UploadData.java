package webfilmss;

import java.io.IOException;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

@ManagedBean(name="uploadData")
@ViewScoped

public class UploadData
{
    private Part file;
    private String fileContent;
    
    public void upload() throws IOException
    {
        try
        {
            fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
        }
        catch(IOException e)
        {
            
        }
    }
    
    public Part getFile(){return file;}
        public void setFile(Part ffile){this.file = ffile;}
}
