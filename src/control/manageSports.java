package control;

import model.Sport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class manageSports 
{
	
	@Resource(name = "betDatasource")
	private DataSource myDataSourceRef;
	
    protected static void toSport(String n_sport, String i_sport, Connection conn){

    	PreparedStatement statement = null;
    	try{
    		//Connection conn = myDataSourceRef.getConnection();
    		//Connection conn = openConn();
    	
	    	String query="INSERT INTO" + " SPORTS"+ " VALUES(?,?)";
	        
	    	statement = conn.prepareStatement(query);
	        statement.setString(1, n_sport);
	        statement.setString(2, i_sport);
	        statement.executeUpdate();
	        statement.close();
	        
	        //conn.close();
	        
    	}catch(SQLException e){try {
			statement.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}
    }
    
    private  static Connection openConn(){
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
 
    public static void findAllSports(String name, String startdate, String enddate){
    	
    	//Connection conn = myDataSourceRef.getConnection();
		Connection conn = openConn();
	  	PreparedStatement statement = null;
    	try{
    		String query = null;
    		String n_sport = null;
    		String i_sport =null;
    		if (name == null && startdate == null)
    		{
    			// Nessun parametro
    			query="SELECT NAME FROM SPORTS";
    		}
    		else if (name == null && startdate!=null)
    		{
    			//Ricerca per range data
    		}
    		else{
    			//Ricerca per range e nome
    		}
	        query="INSERT INTO" + " SPORTS"+ " VALUES(?,?)";
	        
	    	statement = conn.prepareStatement(query);
	        statement.setString(1, n_sport);
	        statement.setString(2, i_sport);
	        statement.executeUpdate();
	        statement.close();
	        
	        //conn.close();
	        
    	}catch(SQLException e){try {
			statement.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}
    }
    
    //findSportForId
    
    //removeSport
    
}
    
    
    