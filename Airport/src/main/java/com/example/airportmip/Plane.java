package com.example.airportmip;

public class Plane {
    private int m_id_plane;
    private String m_company;
    private String m_nr_plane;
    private int m_nr_seats;

    public Plane(int id_plane, String company, String nr_plane, int nr_seats) {
        this.m_id_plane = id_plane;
        this.m_company = company;
        this.m_nr_plane = nr_plane;
        this.m_nr_seats = nr_seats;
    }
    public int getM_id_plane() {
        return m_id_plane;
    }

    public String getM_company() {
        return m_company;
    }

    public String getM_nr_plane() {
        return m_nr_plane;
    }

    public int getM_nr_seats() {
        return m_nr_seats;
    }

    public void setM_id_plane(int m_id_plane) {
        this.m_id_plane = m_id_plane;
    }

    public void setM_company(String m_company) {
        this.m_company = m_company;
    }

    public void setM_nr_plane(String m_nr_plane) {
        this.m_nr_plane = m_nr_plane;
    }

    public void setM_nr_seats(int m_nr_seats) {
        this.m_nr_seats = m_nr_seats;
    }
}
