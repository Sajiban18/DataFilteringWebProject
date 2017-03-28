package webfilmss;

import applicationvariables.ApplicationVariables.Messages.AddMessage;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean(name="base")
@ViewScoped

public class Base
{
    public void ValidateFile(FacesContext context, UIComponent comp, Object value)
    {
        Part file = (Part) value;
        if(file == null || file.getSize() <= 0 || file.getContentType().isEmpty())
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("SELECT A VALID FILE!!!"));
        }
        else if(!file.getContentType().endsWith("csv"))
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("SELECT A CSV FILE!!!"));
        }
        else if(file.getSize()>500000)
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("FILE SIZE TOO BIG!!!"));
        }
    }
    
    public void ValidateName(FacesContext context, UIComponent comp, Object value)
    {
        String name = (String) value;
        // Validate Name (No Numeric Values, Spaces, other characters except ' 
    }
    
    public void ValidateImdbID(FacesContext context, UIComponent comp, Object value)
    {
        String id = (String) value;
        if(!NumberAndLengthCheck(id, 7))
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("***Imdb ID*** Incorrect Format"));
        }
    }
    
    public void ValidateFilmYear(FacesContext context, UIComponent comp, Object value)
    {
        String year = (String) value;
        if(!NumberAndLengthCheck(year, 4) && !inRange(1888, 2017, year))
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("***Film Year*** Incorrect Format"));
        }
    }
    
    public void ValidateFilmRating(FacesContext context, UIComponent comp, Object value)
    {
        String rating = (String) value;
        if(!isDouble(rating) && !inRange(0.0, 10.0, rating))
        {
            ((UIInput) comp).setValid(false);
            context.addMessage(comp.getClientId(context), new FacesMessage("***Film Rating*** Incorrect Format"));
        }
    }
    
    public boolean NumberAndLengthCheck(String value, int length)
    {
        value.chars().filter(c -> Character.isDigit(c));
        if(value.matches("[0-9]+") && value.length() == length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isDouble(String value)
    {
        try
        {
            Double.parseDouble(value);
            return true;
        } 
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    public boolean inRange(double Minimum, double Maximum, String value)
    {
        double dvalue = Double.parseDouble(value);
        if((dvalue >= Minimum) && (dvalue <= Maximum))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean inRange(int Minimum, int Maximum, String value)
    {
        int dvalue = Integer.parseInt(value);
        if((dvalue >= Minimum) && (dvalue <= Maximum))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void Message(int message)
    {
        FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(AddMessage.ms[message]));
    }
    public void Message(int message1, int message2, int message3)
    {
        FacesContext.getCurrentInstance().addMessage("message1", new FacesMessage(AddMessage.ms[message1]));
        FacesContext.getCurrentInstance().addMessage("message2", new FacesMessage(AddMessage.ms[message2]));
        FacesContext.getCurrentInstance().addMessage("message3", new FacesMessage(AddMessage.ms[message3]));
    }
}
