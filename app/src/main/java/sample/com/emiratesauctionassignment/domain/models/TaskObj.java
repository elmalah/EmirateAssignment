package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by elmalah on 6/25/2017.
 */

public class TaskObj extends TaskDetailsObj implements Serializable {

    public boolean isDone;
    public ArrayList<CommentObj> comments;


    public class CommentObj implements Serializable{

            public int id;
            public String comment;
            public String who;
            public String dateTime;



    }
}