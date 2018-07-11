package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;

/**
 * Created by elmalah on 8/4/2017.
 */

public class TrackParam implements Serializable {

    int userId;
    Double lat;
    Double lon;
    String datetime;

    public TrackParam(int userId,Double latitude,Double longtude,String dateTime){
        this.userId=userId;
        this.lat=latitude;
        this.lon=longtude;
        this.datetime=dateTime;
    }
    public String getLat(){
        return this.userId+","+lat;
    }
}
