package hello;

/**
 * Created by Lenovo on 3/4/2015.
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModaretorData
{

    private int id;
    private static int counter=100000;
    private String name;
    private String email;
    private String password;
    private String created_time;

    public ModaretorData returnModaretorData()
    {

        return this;
    }

    /*
    ModaretorData(){};
    ModaretorData(String name,String email,String password)
    {
        this.setId();
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCreated_time();
    }
    */

    public int getId() {
        return id;
    }
    public void setId() {
        counter++;
        this.id = counter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time() {
        this.created_time =  getTimeNow();
    }
    public void resetCounter()
    {

        counter--;
    }

    @JsonCreator
    public ModaretorData(@JsonProperty("id")int id, @JsonProperty("name")String name,
                         @JsonProperty("email")String email, @JsonProperty("password")String password, @JsonProperty("created_at")String created_at) {
        this.setId();
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCreated_time();
    }


    private String  getTimeNow() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

}