/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
 
import controller.UserController;
import dao.DAOImpl;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
import org.primefaces.event.SlideEndEvent;
 
@ManagedBean(name ="sliderView")
@SessionScoped
@RequestScoped
public class SliderView
{
    private int number3;  //number of pixels bought; 
    private double donation;
    private double finalDonation;
    private String message;//display name
    private final double PIXEL_PRICE =.22;
    private String state;
    
    @ManagedProperty(value="#{userController}")
    private UserController uc;
    @ManagedProperty(value="#{dropdownView}")
    private DropdownView dv;

    public void sendMail(){
        String to=uc.getEmail();
        String from="jmsalvador2395@gmail.com";
        String usr="jmsalvador2395@gmail.com";
        String pass="jmsalvador2319@yahoo.com";
        String host="smtp.gmail.com";
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usr, pass);
            }
        });
        
        try{
            MimeMessage message=new MimeMessage(session);
            MimeMultipart mp=new MimeMultipart();
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Thank You For Your Donation To The Feed My Starving Children Organization");
            message.setText("Thank You " + uc.getFname()+", \nWe greatly appreciate you donating "+ number3 +" meals to our non-profit organization. Your contribution will be used for the greater good!! \n\nThank You, \nFeed My Starving Children Organization");
            BodyPart bp=new MimeBodyPart();
            String htmlText="<img src=\"cid:image\">";
            bp.setContent(htmlText, "text/html");
            mp.addBodyPart(bp);
            
            bp=new MimeBodyPart();
            DataSource fds=new FileDataSource("image.png");
            bp.setDataHandler(new DataHandler(fds));
            bp.setHeader("Content-ID", "<image>");
            //message.setContent(mp);
            Transport.send(message);
            
        }
        catch (MessagingException mex){
        }
    }
    public String donate(){
        DAOImpl d=new DAOImpl();
        if(d.donate(number3, message, getUc().getUid(), dv.getState())==1)
        {
            sendMail();
            return "https://www.sandbox.paypal.com/cgi-bin/webscr";
        }
        return "donationerror.xhtml";
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public double getFinalDonation() {
        return finalDonation;
    }

    public void setFinalDonation(double finalDonation) {
        this.finalDonation = finalDonation;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getNumber3() 
    {
        return number3;
    }

    public double getDonation() 
    {
        return donation;
    }

    public void setDonation(double donation) 
    {
        this.donation = donation;
        toString();
    }
 
    public void setNumber3(int number3) 
    {
        this.number3 = number3;
    }

    //.22 assumed price per pixel
    public double calculateDonation()
    {
        setDonation(number3 * PIXEL_PRICE);
        return donation;
    }
     
    public void onSlideEnd(SlideEndEvent event) {
        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + getDonation());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 

    @Override
    public String toString() {
        return "SliderView{" + "number3=" + number3 + ", donation=" + donation + ", message=" + message + '}';
    }

    /**
     * @return the uc
     */
    public UserController getUc() {
        return uc;
    }

    /**
     * @param uc the uc to set
     */
    public void setUc(UserController uc) {
        this.uc = uc;
    }

    /**
     * @return the dv
     */
    public DropdownView getDv() {
        return dv;
    }

    /**
     * @param dv the dv to set
     */
    public void setDv(DropdownView dv) {
        this.dv = dv;
    }  
}