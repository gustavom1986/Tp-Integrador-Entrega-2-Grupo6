/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpentrega2;

/**
 *
 * @author grupo6
 */
class Pronosticos {
private String participante;
private String codigo;
private Resultados.ResultadosEnum resultado;
private int puntos;


    public Pronosticos(String participante, String codigo, Resultados.ResultadosEnum resultado) {
        this.participante = participante;
        this.codigo = codigo;
        this.resultado = resultado;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Resultados.ResultadosEnum getResultado() {
        return resultado;
    }

    public void setResultado(Resultados.ResultadosEnum resultado) {
        this.resultado = resultado;
    }

    public int getPuntos() {
        return puntos;
    }


//sumará un punto en caso de que el resultado del pronóstico coincida con el resultado real    
public void calculaPuntosPronostico (Resultados.ResultadosEnum pronostico, Resultados.ResultadosEnum real) {
if (pronostico == real) {
  this.puntos = 1;  }
else { this.puntos = 0;}
}

}        
    

