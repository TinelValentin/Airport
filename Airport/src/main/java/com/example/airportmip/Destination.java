package com.example.airportmip;

public class Destination {

    private Integer id_destination;
    private String country;
    private Integer nr_airport;
    private String city;

    public Destination(Integer id_destination, String country, Integer nr_airport, String city) {
        this.id_destination = id_destination;
        this.country = country;
        this.nr_airport = nr_airport;
        this.city = city;
    }



    public void setId_destination(Integer id_destination) {
        this.id_destination = id_destination;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNr_airport(Integer nr_airport) {
        this.nr_airport = nr_airport;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId_destination() {
        return id_destination;
    }

    public String getCountry() {
        return country;
    }

    public Integer getNr_airport() {
        return nr_airport;
    }

    public String getCity() {
        return city;
    }


}
