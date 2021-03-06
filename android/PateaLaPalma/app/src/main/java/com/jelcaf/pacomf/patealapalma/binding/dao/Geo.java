package com.jelcaf.pacomf.patealapalma.binding.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by Paco on 28/03/2015.
 */
public class Geo extends Model {

    public Sendero getSendero() {
        return sendero;
    }

    public Geo(){
        super();
    }

    public Geo(Double latitud, Double longitud, String type, Sendero sendero) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.type = type;
        this.sendero = sendero;
    }

    public void setSendero(Sendero sendero) {
        this.sendero = sendero;
    }

    @Column(name = "senderoFK")
    private Sendero sendero;

    @Column(name = "type")
    private String type;

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;
}
