/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author grupo6
 */
public class Personas {
private final String nombre;
private int puntajeTotal; 
Map<Integer, Integer> map = new HashMap<>();

    public Personas(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    
    public void setPuntaje(int puntaje) {
        this.puntajeTotal = puntaje;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }


    
}
