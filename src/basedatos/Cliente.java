/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Psche
 */
public class Cliente {
    HashMap<String ,Embarcacion> embarcaciones= new HashMap<>();
    ArrayList<String> listadoEmbarcaciones =new ArrayList<>();
    String listadoDeembarcaciones[]=new String[1000];
    public String nombre="";
    public String apellido="";
    public String celular="";
    public String TelFijo="";
    public String correo="";
    public String cuidador="";
    public String celularCuidador="";
    Embarcacion emb;
 
   

    public String[] getListadoEmbarcaciones() {
        for (int g=0;g<this.listadoEmbarcaciones.size();g++) {
            listadoDeembarcaciones[g]=this.listadoEmbarcaciones.get(g);
            System.out.println("\nExistente: "+this.listadoDeembarcaciones[g]);
        }
        return listadoDeembarcaciones;
    }
    
    public void setListadoEmbarcaciones(ArrayList<String> listadoEmbarcaciones) {
        this.listadoEmbarcaciones = listadoEmbarcaciones;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void addEmbarcacion(String nom, Embarcacion emb){
        this.embarcaciones.put(nom,emb);
        this.listadoEmbarcaciones.add(nom);
    }
    public void setNombe(String nombe) {
        this.nombre = nombe;
    }
    public void imprimirnombre(){
        System.out.println(this.nombre);
    }
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getNombreApellido(){
        return this.nombre+" "+this.apellido;
    }
    public String getApellidoNombre(){
         return this.apellido+" "+this.nombre;
    }
     public String getTelFijo() {
        return TelFijo;
    }

    public void setTelFijo(String TelFijo) {
        this.TelFijo = TelFijo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCuidador() {
        return cuidador;
    }

    public void setCuidador(String cuidador) {
        this.cuidador = cuidador;
    }

    public String getCelularCuidador() {
        return celularCuidador;
    }
    public ArrayList getInformacionC(){
        ArrayList<String> in=new ArrayList<>();
        in.add(this.nombre);
        in.add(this.apellido);
        in.add(this.celular);
        in.add(this.TelFijo);
        in.add(this.correo);
        in.add(this.cuidador);
        in.add(this.celularCuidador);
        return in;
    }
    public ArrayList getInformacionE(){
        ArrayList<String> in=new ArrayList<>();
        for(int k=0;k<this.listadoEmbarcaciones.size();k++){
            emb=this.embarcaciones.get(this.listadoEmbarcaciones.get(k));
            in.add("*");
            in.add(emb.tipo);
            in.add(emb.marca);
            in.add(emb.modelo);
            in.add(emb.motor);
            in.add(emb.nSerie);
        }
        return in;
    }
    public String getInformacionCiente(){
        return ("                                       \n\n  Nombre: "+this.nombre+"\n  Apellido: "+this.apellido+"\n  Correo: "+this.correo+"\n  Celular: "+this.celular+
                "\n  Telefono fijo: "+this.TelFijo+"\n  Cuidador: "+this.cuidador+"\n  Numero Celular de Cuidador: "+this.celularCuidador+"\n");
    }
    public String getInformacionEmbarcacuiones(){
        String infoEmb="\n                             EMBARCACIONES\n";
        for(int x=0;x<this.listadoEmbarcaciones.size();x++){
            this.emb=this.embarcaciones.get(this.listadoEmbarcaciones.get(x));
            infoEmb+="\n  Tipo: "+emb.tipo+"\n  Marca: "+emb.marca+"\n  Modelo: "+emb.modelo+"\n  Motor: "+emb.motor+"\n  Numero Serie: "+emb.nSerie;
            infoEmb+="\n";
        }
        return infoEmb;
    }
    public void setCelularCuidador(String celularCuidador) {
        this.celularCuidador = celularCuidador;
    }
   
}
