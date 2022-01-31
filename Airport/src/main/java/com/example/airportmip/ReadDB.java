package com.example.airportmip;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class ReadDB {
    private static Vector<Flight> FlightList = new Vector<Flight>();
    private static Vector<Plane> planeList = new Vector<Plane>();
    private static Vector<Ticket> ticketList = new Vector<Ticket>();
    private static Vector<Destination> destinationList = new Vector<Destination>();
    private static Connection connection;


    public static Vector<Flight> getFlightList() {
        return FlightList;
    }

    public static Vector<Plane> getPlaneList() {
        return planeList;
    }

    public static Vector<Ticket> getTicketList() {
        return ticketList;
    }

    public static Vector<Destination> getDestinationList() {
        return destinationList;
    }



   static public void connectionDb()  {
         connection = null;
        String host="localhost";
        String port="5432";
        String db_name="AirportProject";
        String username="postgres";
        String password="deluxdls128";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

static public void insertReservation(String nameI,String surnameI,int flightClass,int flight_id)
{
    Statement stmt;
    try {
        stmt = connection.createStatement();
          stmt.executeUpdate
                ("INSERT INTO public.\"Rezervation\"(name, surname, class, id_flight) VALUES ( '"+nameI+"','"+surnameI+"','"+ flightClass+"','"+ flight_id+"');");
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

    static public Vector<String> getAllCities()
    {
        connectionDb();
        readPlane();
        readDestination();
        readTicket();
        readFlight();
        Vector<String> cities = new Vector<>();
        Set<String> unique = new HashSet<String>();

        for(Flight a : getFlightList())
        {
            if(!a.getM_id_destinationTo().getCity().equals("Brasov"))
                if(!unique.contains(a.getM_id_destinationTo().getCity()))
                {
                    cities.add(a.getM_id_destinationTo().getCity());
                    unique.add(a.getM_id_destinationTo().getCity());
                }

        }

        return cities;
    }

    static private Destination findDestination(int idDestination)
    {
        for(Destination a : destinationList)
            if(a.getId_destination()==idDestination)
                return a;
            return destinationList.get(0);
    }

    static public Vector<Flight> readFlightsArriveHome() {

        Vector<Flight> flightsHome = new Vector<>();
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery
                    ("Select * from  \"Flight\" f "  +
                            "inner join \"Destination\" d on d.id_destination = f.id_destination_to" +
                            " where d.city ='Brasov'");
            while (resultQuery.next()) {
                Integer id_flight = resultQuery.getInt("id_flight");
                Integer id_plane = resultQuery.getInt("id_plane");
                Date flightDate = resultQuery.getDate("date_flight");

                Time flightTime = resultQuery.getTime("time_flight");
                Integer terminal = resultQuery.getInt("terminal");
                Boolean active = resultQuery.getBoolean("active");
                Integer id_destination_from = resultQuery.getInt("id_destination_from");
                Integer id_destination_to = resultQuery.getInt("id_destination_to");


                Vector<Ticket> tickets = getTicketsForFlight(id_flight);
                Plane planeAux = planeList.get(id_plane-1);
                Destination getDestTo = findDestination(id_destination_to);
                Destination getDestFrom = findDestination(id_destination_from);
                Flight aux = new Flight(id_flight, planeAux, tickets, getDestTo, getDestFrom, flightDate, flightTime, terminal, active);
                flightsHome.add(aux);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return flightsHome;
    }


    static public Vector<Flight> readFlightsLeaveHome() {

        Vector<Flight> flightsLeft = new Vector<>();
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery
                    ("Select * from  \"Flight\" f "  +
                            "inner join \"Destination\" d on d.id_destination = f.id_destination_from" +
                            " where d.city ='Brasov'");
            while (resultQuery.next()) {
                Integer id_flight = resultQuery.getInt("id_flight");
                Integer id_plane = resultQuery.getInt("id_plane");
                Date flightDate = resultQuery.getDate("date_flight");

                Time flightTime = resultQuery.getTime("time_flight");
                Integer terminal = resultQuery.getInt("terminal");
                Boolean active = resultQuery.getBoolean("active");
                Integer id_destination_from = resultQuery.getInt("id_destination_from");
                Integer id_destination_to = resultQuery.getInt("id_destination_to");


                Vector<Ticket> tickets = getTicketsForFlight(id_flight);
                Plane planeAux = planeList.get(id_plane-1);
                Destination getDestTo = findDestination(id_destination_to);
                Destination getDestFrom = findDestination(id_destination_from);
                Flight aux = new Flight(id_flight, planeAux, tickets, getDestTo, getDestFrom, flightDate, flightTime, terminal, active);
                flightsLeft.add(aux);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return flightsLeft;
    }


    static Vector<Ticket> getTicketsForFlight(Integer id_flight)
    {
        Vector<Ticket> tickets =new Vector<Ticket>();

    try{

        Statement stmt = connection.createStatement();
        ResultSet resultQuery = stmt.executeQuery("Select * from  \"Ticket\" where id_flight = " + id_flight);
        while (resultQuery.next()) {
            Integer id_ticket = resultQuery.getInt("id_ticket");

            tickets.add(ticketList.get(id_ticket-1));
        }
        }
          catch(Exception e){
            e.printStackTrace();}
    return tickets;
        }



    static public Vector<Flight> readFlight() {

        if(destinationList.size()==0)
            readDestination();
        if(planeList.size()==0)
            readPlane();
        if(ticketList.size()==0)
            readTicket();

        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery("Select * from  \"Flight\"  ");
            while (resultQuery.next()) {
                 Integer id_flight = resultQuery.getInt("id_flight");
                Integer id_plane = resultQuery.getInt("id_plane");
                Date flightDate = resultQuery.getDate("date_flight");

                Time flightTime = resultQuery.getTime("time_flight");
                Integer terminal = resultQuery.getInt("terminal");
                Boolean active = resultQuery.getBoolean("active");
                Integer id_destination_from = resultQuery.getInt("id_destination_from");
                Integer id_destination_to = resultQuery.getInt("id_destination_to");


               Vector<Ticket> tickets = getTicketsForFlight(id_flight);
                Plane planeAux = planeList.get(id_plane-1);
                Destination getDestTo = findDestination(id_destination_to);
                Destination getDestFrom = findDestination(id_destination_from);
                Flight aux = new Flight(id_flight, planeAux, tickets, getDestTo, getDestFrom, flightDate, flightTime, terminal, active);
                FlightList.add(aux);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return FlightList;
    }

    static public Vector<Destination> readDestination() {


        try {
            Statement stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery("Select * from  \"Destination\"  ");
            while (resultQuery.next()) {
                Integer id_destination = resultQuery.getInt("id_destination");
                String name = resultQuery.getString("name");
                Integer nr_airport = resultQuery.getInt("nr_airport");
                String city = resultQuery.getString("city");
                Destination aux = new Destination(id_destination, name, nr_airport, city);
                destinationList.add(aux);


            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return destinationList;
    }


    static public Vector<Plane> readPlane() {


        try {
            Statement stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery("Select * from  \"Plane\"  ");
            while (resultQuery.next()) {
                Integer id_plane = resultQuery.getInt("id_plane");
                String company = resultQuery.getString("company");
                String nr_plane = resultQuery.getString("nr_plane");
                Integer nr_seats = resultQuery.getInt("nr_seats");
                Plane aux = new Plane(id_plane, company, nr_plane, nr_seats);
                planeList.add(aux);

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return planeList;
    }


    static public Vector<Ticket> readTicket() {

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultQuery = stmt.executeQuery("Select * from  \"Ticket\"  ");
            while (resultQuery.next()) {
                Integer id_ticket = resultQuery.getInt("id_ticket");
                Integer id_flight = resultQuery.getInt("id_flight");
                Integer flight_class = resultQuery.getInt("class");
                Float price = resultQuery.getFloat("price");
                Ticket aux = new Ticket(id_ticket,id_flight,price,flight_class);
                ticketList.add(aux);

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
          return ticketList;
    }


    ReadDB()
    {
        connectionDb();
        readPlane();
        readDestination();
        readTicket();
        readFlight();
    }
}
