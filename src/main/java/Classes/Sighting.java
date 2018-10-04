package Classes;

import java.util.Arrays;
import java.util.List;

public class Sighting {
    //This class stores each sighting.
    private String dateTime;
    private String city;
    private String state;
    private String country;

    public Sighting(List<String> s){
        dateTime = s.get(0);
        city = s.get(1);
        state = s.get(2);
        country = s.get(3);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        if(state.equals(""))
            this.state = "Not A State";
        else
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getState() {
        return state;
    }


    //methods below extract year, month, hour and min from the date/time attribute
    public int getYear (){
        List<String> split = Arrays.asList(dateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(0).split("/"));
        return Integer.parseInt(split2.get(2));
    }
    public int getMonth (){
        List<String> split = Arrays.asList(dateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(0).split("/"));
        return Integer.parseInt(split2.get(0));
    }
    public int getHour (){
        List<String> split = Arrays.asList(dateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(1).split(":"));
        return Integer.parseInt(split2.get(0));
    }
    public int getMin (){
        List<String> split = Arrays.asList(dateTime.split(" "));
        List<String> split2 = Arrays.asList(split.get(1).split(":"));
        return Integer.parseInt(split2.get(1));
    }
}
