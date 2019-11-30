/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

/**
 *
 * @author Psche
 */
public class Embarcacion {
    String tipo;
    String marca;
    String modelo;
    String motor;
    String nSerie;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }
    public String getInformacion(){
        return ("\nTipo embarcacion: "+this.getTipo()+"\nMarca: "+this.marca+"\nModelo: "+this.modelo+"\nMotor: "+this.motor+"\nNumero serie: "+this.nSerie);
}
    
}
