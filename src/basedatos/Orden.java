/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author Psche
 */
public class Orden {
    String numeroOrden;
    int totalManoObra;
    int totalVarios;
    int total;
    String observacion;
    ArrayList<String> manoObra = new ArrayList<>();
    HashMap<String ,String> repuestos= new HashMap<>();
    ArrayList <String> listadoRepuestos= new ArrayList<>();
    
    public String getInformacion(){
        String s=this.numeroOrden+"\n"+Integer.toString(this.getTotalManoObra())+"\n"+Integer.toString(this.getTotalVarios())+"\n"+Integer.toString(this.getTotal())
                +"\n"+this.getManoObra()+"\n"+this.getRepuestos();
        return s;
                

    }
            
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getTotalManoObra() {
        return totalManoObra;
    }

    public void setTotalManoObra(String totalManoObra) {
        this.totalManoObra = parseInt(totalManoObra,10);
    }
    public int getTotalVarios() {
        return totalVarios;
    }

    public void setTotalVarios(String totalVarios) {
        this.totalVarios = parseInt(totalVarios,10);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = parseInt(total,10);;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ArrayList<String> getManoObra() {
        return manoObra;
    }

    public void addManoObra(String mo) {
        this.manoObra.add(mo);
    }

    public String getRepuestos() {
       String rep="";
       //System.out.println("cant rep: "+this.listadoRepuestos.size());
       for(int z=0;z<this.listadoRepuestos.size();z++){
          rep+=listadoRepuestos.get(z)+" "+this.repuestos.get(listadoRepuestos.get(z))+"\n";
       }
        return rep;
    }

    public void addRepuesto(String repuesto, String cant) {
        this.repuestos.put(repuesto,cant);
        this.listadoRepuestos.add(repuesto);
    }
    
}
