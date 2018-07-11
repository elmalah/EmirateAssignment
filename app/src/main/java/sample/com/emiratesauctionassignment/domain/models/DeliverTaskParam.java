package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by elmalah on 7/14/2017.
 */

public class DeliverTaskParam implements Serializable {

    public int userID;
    public int taskID;
    public double lon;
    public double lat;
    public boolean isDone;
    public String comment;
    public ArrayList<QuestionParam> questions;
}
