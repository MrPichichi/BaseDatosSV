/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Psche
 */
public class Orden {
    String numeroOrden;
    String cliente;
    String fecha;
    String nota;
    int totalManoObra;
    int totalVarios;
    int total;
    ArrayList<String> ordenes = new ArrayList<>();
    ArrayList<String> manoObra = new ArrayList<>();
    ArrayList <String> listadoRepuestos= new ArrayList<>();
    HashMap<String ,String> repuestos= new HashMap<>();

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public ArrayList<String> getOrdenes() {
        return ordenes;
    }
    public void addOrden() {
        this.ordenes.add(cliente);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getInformacion(){
        String s="\n N° Orden: "+this.numeroOrden+"\n Cliente: "+this.cliente+"\n Fecha: "+this.getFecha()
                +"\n\n                             MANO DE OBRA\n\n"+this.getManoObra()+"\n\n                             REPUESTOS\n\n"+this.getRepuestos()+"\n\n                                   COSTE \n\n Mano de obra: "+Integer.toString(this.getTotalManoObra())+"\n Varios: "
                +Integer.toString(this.getTotalVarios())+"\n Total: "+Integer.toString(this.getTotal())+"\n\n                             NOTA\n\n"+this.getNota();
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


    public String getManoObra() {
        String mObra="";
        for(int c=0;c<this.manoObra.size();c++){
            mObra+=" - "+this.manoObra.get(c)+"\n";
        }
        return mObra;
    }

    public void addManoObra(String mo) {
        this.manoObra.add(mo);
    }

    public String getRepuestos() {
       String rep="";
       //System.out.println("cant rep: "+this.listadoRepuestos.size());
       for(int z=0;z<this.listadoRepuestos.size();z++){
          rep+=" - "+listadoRepuestos.get(z)+" "+this.repuestos.get(listadoRepuestos.get(z))+"\n";
       }
        return rep;
    }

    public void addRepuesto(String repuesto, String cant) {
        this.repuestos.put(repuesto,cant);
        this.listadoRepuestos.add(repuesto);
    }
    
}
