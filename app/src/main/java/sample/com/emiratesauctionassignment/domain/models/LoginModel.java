package sample.com.emiratesauctionassignment.domain.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by elmalah on 3/5/2017.
 */

public class LoginModel extends BaseModel {
    public ArrayList<LoginObj> data;

    public class LoginObj implements Serializable {


        public String id;
        public String email;
        public String firstName;
        public String lastName;
        public String refershToken;
        public String accessToken;
        public Company company;
        public String imageURL;
        public City city;
        public String address;
        public String mobile;
        public boolean track;

        public class Company implements Serializable {
            public int id;
            public String name;
            public boolean active;
            public boolean track;
        }

        public class City implements Serializable {
            public int id;
            public String name;
        }


        public boolean attendEveryDay;
        public String attendTime;
        public String attendFrom;
        public String attendTo;

    }
}
