package com.example.airportmip;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Vector;


public class Flight {

    private int m_id_flight;
   private Plane m_id_plane;
    private Vector<Ticket> m_id_ticket;
    private Destination m_id_destinationFrom;
    private Destination m_id_destinationTo;
    private Date m_date;
    private Time m_time;
    private int m_terminal;
    private boolean m_active;



    public Flight(int id_flight, Plane id_plane, Vector<Ticket> id_ticket, Destination destinationFrom, Destination destinationTo, Date date, Time time, int terminal, boolean active)
    {
    this.m_id_flight=id_flight;
    this.m_id_plane=id_plane;
    this.m_id_ticket=id_ticket;
    this.m_id_destinationFrom=destinationFrom;
    this.m_id_destinationTo=destinationTo;
    this.m_date=date;
    this.m_time=time;
    this.m_terminal=terminal;
    this.m_active=active;
    }

    public int getM_id_flight() {
        return m_id_flight;
    }

    public Plane getM_id_plane() {
        return m_id_plane;
    }

    public Vector<Ticket> getM_id_ticket() {
        return m_id_ticket;
    }

    public Destination getM_id_destinationFrom() {
        return m_id_destinationFrom;
    }

    public Destination getM_id_destinationTo() {
        return m_id_destinationTo;
    }

    public Date getM_date() {
        return m_date;
    }

    public Time getM_time() {
        return m_time;
    }

    public int getM_terminal() {
        return m_terminal;
    }

    public boolean isM_active() {
        return m_active;
    }
    public void setM_id_flight(int m_id_flight) {
        this.m_id_flight = m_id_flight;
    }

    public void setM_id_plane(Plane m_id_plane) {
        this.m_id_plane = m_id_plane;
    }

    public void setM_id_ticket(Vector<Ticket> m_id_ticket) {
        this.m_id_ticket = m_id_ticket;
    }

    public void setM_id_destinationFrom(Destination m_id_destinationFrom) {
        this.m_id_destinationFrom = m_id_destinationFrom;
    }

    public void setM_id_destinationTo(Destination m_id_destinationTo) {
        this.m_id_destinationTo = m_id_destinationTo;
    }

    public void setM_date(Date m_date) {
        this.m_date = m_date;
    }

    public void setM_time(Time m_time) {
        this.m_time = m_time;
    }

    public void setM_terminal(int m_terminal) {
        this.m_terminal = m_terminal;
    }

    public void setM_active(boolean m_active) {
        this.m_active = m_active;
    }
}
