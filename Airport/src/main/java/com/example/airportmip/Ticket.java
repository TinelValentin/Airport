package com.example.airportmip;

public class Ticket {

    private int m_id_ticket;
    private int m_id_flight;
    private float m_price;
    private int m_class;

    public Ticket(int m_id_ticket, int m_id_flight, float m_price, int m_class) {
        this.m_id_ticket = m_id_ticket;
        this.m_id_flight = m_id_flight;
        this.m_price = m_price;
        this.m_class = m_class;
    }

    public void setM_id_ticket(int m_id_ticket) {
        this.m_id_ticket = m_id_ticket;
    }

    public void setM_id_flight(int m_id_flight) {
        this.m_id_flight = m_id_flight;
    }

    public void setM_price(float m_price) {
        this.m_price = m_price;
    }

    public void setM_class(int m_class) {
        this.m_class = m_class;
    }

    public int getM_id_ticket() {
        return m_id_ticket;
    }

    public int getM_id_flight() {
        return m_id_flight;
    }

    public float getM_price() {
        return m_price;
    }

    public int getM_class() {
        return m_class;
    }







}
