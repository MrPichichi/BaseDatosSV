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
    ArrayList<String> listadoOrdenes =new ArrayList<>();
    String listadoDeembarcaciones[]=new String[1000];
    String listadoDeOrdenes[]=new String[1000];
    boolean guarderia=false; 
    boolean deudaOrden=false;
    boolean deudaGuarderia=false;
    public String nombre="vacio";
    public String apellido="vacio";
    public String celular="vacio";
    public String TelFijo="vacio";
    public String TelFijoOficina1="vacio";
    public String TelFijoOficina2="vacio";
    public String correo="vacio";
    public String cuidador="vacio";
    public String celularCuidador="vacio";
    Embarcacion emb;

    public String getTelFijoOficina1() {
        return TelFijoOficina1;
    }
    public void actualizarListadoEmbarcaciones(){
        this.listadoDeembarcaciones=new String[this.listadoEmbarcaciones.size()];
        for(int x=0;x<this.listadoEmbarcaciones.size();x++ ){
            listadoDeembarcaciones[x]=this.listadoEmbarcaciones.get(x);
        }
    }
    public void setTelFijoOficina1(String TelFijoOficina1) {
        this.TelFijoOficina1 = TelFijoOficina1;
    }

    public String getTelFijoOficina2() {
        return TelFijoOficina2;
    }

    public void setTelFijoOficina2(String TelFijoOficina2) {
        this.TelFijoOficina2 = TelFijoOficina2;
    }
    
    public void setGuarderiaSi() {
        this.guarderia=true;
    }

    public void setGuarderiaNO() {
        this.guarderia=false;
    }
    public void setDeudaGuarderiaSI() {
        this.deudaGuarderia = true;
    }
     public void setDeudaGuarderiaNO() {
        this.deudaGuarderia = false;
    }
    public void setDeudaOrdenSi() {
        this.deudaOrden = true;
    }
     public void setDeudaOrdenNo() {
        this.deudaOrden = false;
    }
    public String[] getListadoDeembarcaciones() {
        return listadoDeembarcaciones;
    }

    public void setListadoDeembarcaciones(String[] listadoDeembarcaciones) {
        this.listadoDeembarcaciones = listadoDeembarcaciones;
    }
    public void eliminarEmbarcacion(String motor){
        this.embarcaciones.remove(motor);
        this.listadoEmbarcaciones.remove(motor);
    }
   

    public String[] getListadoEmbarcaciones() {
        this.listadoDeembarcaciones=new String[1000];
        for (int g=0;g<this.listadoEmbarcaciones.size();g++) {
            listadoDeembarcaciones[g]=this.listadoEmbarcaciones.get(g);
            //System.out.println("\nExistente: "+this.listadoDeembarcaciones[g]);
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
        in.add(this.TelFijoOficina1);
        in.add(this.TelFijoOficina2);
        in.add(this.correo);
        in.add(this.cuidador);
        in.add(this.celularCuidador);
        if(this.guarderia==true){
            in.add("SI");
        }
        else{
            in.add("NO");
        }
        if(this.deudaGuarderia==true){
            in.add("SI");
        }
        else{
            in.add("NO");
        }
         if(this.deudaOrden==true){
            in.add("SI");
        }
       else{
            in.add("NO");
        }
        return in;
    }
    public String getGuarderia(){
        if(this.guarderia==true){
            return "SI";
        }
        else{
            return "NO";
        }
    }
    public String getDeudaOrden(){
        if(this.deudaOrden==true){
            return "SI";
        }
        else{
            return "NO";
        }
    }
    public String getDeudaGuarderia(){
        if(this.deudaGuarderia==true){
            return "SI";
        }
        else{
            return "NO";
        }
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
            in.add(emb.codigo);
        }
        return in;
    }
    public String getInformacionClienteVisualizar(){
        return ("                                       \n\n  Nombre: "+this.nombre+"\n  Apellido: "+this.apellido+"\n  Correo: "+this.correo+"\n  Celular: "+this.celular+
                "\n  Telefono fijo: "+this.TelFijo+"\n  Telefono fijo Oficina 1: "+this.TelFijoOficina1+"\n  Telefono fijo Oficina 2: "+this.TelFijoOficina2+"\n  Cuidador: "+this.cuidador+"\n  Numero Celular de Cuidador: "+
                this.celularCuidador+"\n  Guarderia: "+this.getGuarderia()+"\n  Deuda Guarderia: "+this.getDeudaGuarderia()+"\n  Deuda Orden: "+this.getDeudaOrden());
    }
    public String getInformacionEmbarcaciones(){
        String infoEmb="\n                             EMBARCACIONES\n";
        for(int x=0;x<this.listadoEmbarcaciones.size();x++){
            this.emb=this.embarcaciones.get(this.listadoEmbarcaciones.get(x));
            infoEmb+="\n  Tipo: "+emb.tipo+"\n  Marca: "+emb.marca+"\n  Modelo: "+emb.modelo+"\n  Motor: "+emb.motor+"\n  Numero Serie: "+emb.nSerie+"\n  Codigo: "+emb.codigo;
            infoEmb+="\n";
        }
        return infoEmb;
    }
    public void setCelularCuidador(String celularCuidador) {
        this.celularCuidador = celularCuidador;
    }
   
}
