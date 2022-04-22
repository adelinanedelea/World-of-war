package TemaPOO;

import java.util.ArrayList;
import java.util.TreeSet;

public class Account {

    Information information;
    ArrayList <Character> characters;
    class Information{

        Credentials credentials;
        TreeSet<String> list;
        String name, country;
        //Builder Pattern pentru information
        public  class Builder {
            Credentials credentials;
            TreeSet<String> list;
            String name, country;
            public Builder(Credentials credentials) {

                this.credentials = credentials;

            }
            public Builder withName ( String name) {

                this.name = name;
                return this;

            }
            public Builder withCountry ( String country) {

                this.country = country;
                return this;

            }
            public Builder withList ( TreeSet <String> list ) {

                this.list = list;
                return this;
            }
            public Information build () {

                Information information = new Information();
                information.credentials = this.credentials;
                information.list = this.list;
                information.name = this.name;
                information.country = this.country;
                return information;

            }
        }
        public String getName() {

            return this.name;

        }
    }

    Integer maps_completed;
    public Account (Information information, Integer maps_completed, ArrayList<Character> characters) {

        this.information = information;
        this.maps_completed = maps_completed;
        this.characters = characters;

    }
    public Account() {

    }
    public String toString () {

        return information.credentials.getEmail() +" " +  information.credentials.getPass()+ " " + information.name +" "+ information.country + " " + characters+ " ";

    }

}
