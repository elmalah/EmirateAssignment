package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;

/**
 * Created by elmalah on 6/25/2017.
 */

public class ChangePasswordParam implements Serializable {
    public String userID;
    public String oldPassword;
    public String newPassword;
}
