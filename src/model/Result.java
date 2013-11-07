package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Result {
	
    protected List<Match> match;
    protected String f_half;
    protected BigInteger id;
    protected String s_half;
    protected String o_time;
    protected String penalty;
    
    public List<Match> getMatches() {
        if (match == null) {
            match = new ArrayList<Match>();
        }
        return this.match;
    }
    
    public String getF_Half() {
        return f_half;
    }
    
    public void setF_Half(String value) {
        this.f_half = value;
    }
    
    public BigInteger getId() {
        return id;
    }
    
    public void setId(BigInteger value) {
        this.id = value;
    }
    
    public String getS_Half() {
        return f_half;
    }
    
    public void setS_Half(String value) {
        this.f_half = value;
    }
    
    public String getO_time() {
        return o_time;
    }
    
    public void setO_time(String value) {
        this.o_time = value;
    }
    
    public String getPenalty() {
        return penalty;
    }
    
    public void setPenalty(String value) {
        this.penalty = value;
    }
}
