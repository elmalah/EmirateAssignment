package sample.com.emiratesauctionassignment.domain.models;

import android.support.annotation.StringRes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by elmalah on 7/7/2018.
 */

public class EntityModel {

    String alertEn;
    String alertAr;
    int RefreshInterval;
    String Ticks;
    int count;
    int endDate;
    String sortOption;
    String sortDirection;

    public ArrayList<CarObject> data;

    public class CarObject implements Serializable {


        int carID;
        String image;
        String descriptionAr;
        String descriptionEn;
        int imgCount;
        String sharingLink;
        String sharingMsgEn;
        String sharingMsgAr;
        String mileage;
        int makeID;
        int modelID;
        int bodyId;
        int year;
        String makeEn;
        String makeAr;
        String modelEn;
        String modelAr;
        String bodyEn;
        String bodyAr;

        public class AuctionInfo implements Serializable {
            int bids;
            int endDate;
            String endDateEn;
            String endDateAr;
            String currencyEn;
            String currencyAr;
            int currentPrice;
            int minIncrement;
            int lot;
            int priority;
            int VATPercent;
            int isModified;
            int itemid;
            int iCarId;
            int iVinNumber;
        }

    }
}
