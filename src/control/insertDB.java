package control;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Bet;
import model.Choice;
import model.Event;
import model.Match;
import model.ObjectFactory;
import model.Sport;
import model.Sports;

@WebService(targetNamespace = "http://control/", portName = "insertDBPort", serviceName = "insertDBService")
public class insertDB 
{
    
    public String todb(String filename)
    { 	
    	Sports s = objectize(filename);
    	String r = s.getFileDate();
    	return r;
    	/*String i_sport = null;
    	String n_sport = null;
    	Connection conn = null;
    	
	    List<Sport> lspo = s.getSport();
	    int nspo = lspo.size();
	    for(int a = 0; a<nspo ; a++){
            
            // Scrivi su DB Sport
            i_sport = lspo.get(a).getId().toString();
            n_sport = lspo.get(a).getName();
            conn = openConn(); // Fatto qui per problemidi limiti di aperture connessioni
            manageSports.toSport(n_sport,i_sport,conn);
            
            ///////////////////////
            
            // Prende lista eventi
            List<Event> leve = s.getSport().get(a).getEvent();
            int neve = leve.size();
            for(int b = 0; b < neve; b++){
                
                // Scrivi su DB Event --> ricordati che la chiave esterna è i_sport
                String i_event = leve.get(b).getId().toString();
                String n_event = leve.get(b).getName();
                manageEvents.toEvent(n_event,i_event,i_sport,conn);
                
                
                // Prende lista match
                List<Match> lmat = s.getSport().get(a).getEvent().get(b).getMatch();
                int nmat = lmat.size();
                for(int c = 0; c < nmat-1;c++){
                    
                    // Scrivi su DB Matches --> ricordati che la chiave esterna è i_event
                    String i_match = lmat.get(c).getId().toString();
                    String n_match = lmat.get(c).getName();
                    String sd_match = lmat.get(c).getStartDate();
                    String l_match = lmat.get(c).getLiveId();
                    String st_match = lmat.get(c).getStreaming().toString();
                    manageMatches.toMatch(i_match,n_match,sd_match,l_match,st_match,i_event,conn);
                    ///////////////////////
            
                    List<Bet> lbet = s.getSport().get(a).getEvent().get(b).getMatch().get(c).getBets().getBet();
                    int nbet = lbet.size();
                    for(int d = 0; d < nbet; d++){
                    
                        // Scrivi su DB Event --> ricordati che la chiave esterna è i_match
                        String i_bet = lbet.get(d).getId().toString();
                        String c_bet = lbet.get(d).getCode();
                        String n_bet = lbet.get(d).getName();
                        manageBets.toBet(n_bet,c_bet,i_bet,i_match,conn);
                        ///////////////////////
                        
                        List<Choice> lcho = s.getSport().get(a).getEvent().get(b).getMatch().get(c).getBets().getBet().get(d).getChoice();
                        int ncho = lcho.size();
                        for(int e = 0; e < ncho ; e++){
                            // Scrivi su DB Choise --> ricordati che la chiave esterna è i_bet
                            String n_choice = lcho.get(e).getName();
                            String i_choice = lcho.get(e).getId().toString();
                            BigDecimal o_choice = lcho.get(e).getOdd();
                            String xml_date = s.getFileDate();
                            manageChoices.toChoice(n_choice,i_choice,o_choice,i_bet, xml_date,conn);
                            
                        }                
                    }
                }
             }	    	
	    }
	  try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
    } 

    private Connection openConn(){
    	Connection conn = null;
    	try{
    		String user="username_bet";
        	String password="212212";
        	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    		Class.forName("oracle.jdbc.driver.OracleDriver");
        	
    		conn =  DriverManager.getConnection(url,user,password);
    	}catch(ClassNotFoundException | SQLException e){
        	System.out.println(e);            	
        }
    	return conn;
    }
    
    //Una volta eseguito l'unmarshall crea l'oggetto riempito con i dati XML
    private Sports objectize(String s) {
       Sports obj = unMarsh(s);
       return obj;
      }
   
    //Esegue l'unmarshall dell' XML
    private Sports unMarsh(String name){
    	
    	Sports obj = null;
    	try {
    		
    	//File f = new File(p+name);
    	
    	URL url;
    		url = new URL(name);
		
    	URLConnection urlConn = url.openConnection();
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        obj = (Sports) unmarshaller.unmarshal(urlConn.getInputStream());
        //obj = (Sports) unmarshaller.unmarshal(f);
        
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
    }

}
    
    
    