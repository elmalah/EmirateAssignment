package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by elmalah on 7/3/2017.
 */

public class TaskDetailsObj implements Serializable {

    public int id;
    public String description;
    public String details;
    public String startDateTime;
    public String endDateTime;
    public boolean track;
    public double lon;
    public double lat;
    public float rate;
    public ArrayList<QuestionObj> questions;


    public class QuestionObj implements Serializable{
        public int id;
        public String question;
        public boolean answer;
        public String comment;

    }


}
