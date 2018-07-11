package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;

/**
 * Created by elmalah on 7/14/2017.
 */

public class AttendParam implements Serializable {

    public int userID;
    public boolean isAttend;
    public String attendTime;
    public String attendDate;
    public double lon;
    public double lat;

}
