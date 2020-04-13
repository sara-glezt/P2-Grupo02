/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sara
 */
public class Penalizacion{
    private Date fechaInicio;
    private Date fechaFin;

    public Penalizacion(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = sumarDias(fechaInicio, 30);
    }
    
    private Date sumarDias(Date fecha, int dias){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    calendar.add(Calendar.DAY_OF_YEAR, dias);
    return calendar.getTime();
    }
}
