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
    String tipo="vacio";
    String marca="vacio";
    String modelo="vacio";
    String motor="vacio";
    String nSerie="vacio";
    String codigo="vacio";
    String llave="vacio";
    boolean enGuarderia=false;

    public boolean isEnGuarderia() {
        return enGuarderia;
    }

    public void setEnGuarderia(boolean enGuarderia) {
        this.enGuarderia = enGuarderia;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getTipoMarca(){
        return tipo+" "+marca;
    }
    public boolean getEnGuarderia() {
        return enGuarderia;
    }
    public String getEnGuarderiaSiNo() {
        if(enGuarderia==true){
            return "SI";
        }
        else{
            return "NO";
        }
        
    }
    public void setEnGuarderiaSI() {
        this.enGuarderia = true;
    }
    public void setEnGuarderiaNo() {
        this.enGuarderia = false;
    }
    
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
        return ("\nTipo embarcacion: "+this.getTipo()+"\nMarca: "+this.marca+
                "\nModelo: "+this.modelo+"\nMotor: "+this.motor+"\nNumero serie: "+
                this.nSerie+"\nClave Embarcacion: "+this.llave);
}
    
}
