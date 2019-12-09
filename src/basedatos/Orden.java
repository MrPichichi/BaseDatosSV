/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Psche
 */
public class Orden {
    Interfaz interfaz;
    String numeroOrden;
    String cliente;
    String embrcacion;
    String fecha;
    String nota;
    String varios;
    int totalManoObra;
    int totalVarios;
    int total;
    int totalRepuestos;
    boolean cancelado=false;
    ArrayList<String> manoObra = new ArrayList<>();
    ArrayList <Repuesto> listadoRepuestos= new ArrayList<>();
    HashMap<String ,String> repuestos= new HashMap<>();
    public String getEmbrcacion() {
        return embrcacion;
    }

    public void setEmbrcacion(String embrcacion) {
        this.embrcacion = embrcacion;
    }

    public String getVarios() {
        return varios;
    }

    public void setVarios(String varios) {
        this.varios = varios;
    }
    

    public int getTotalRepuestos() {
        return totalRepuestos;
    }
 
    
    public String getCancelado(){
        if(this.cancelado==true){
            return "SI";
        }
        else{
            return "NO";
        }
    }
    
    public void setDeudaSi() {
        this.cancelado = true;
    }
     public void setDeudaNo() {
        this.cancelado = false;
    }
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
    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
     public ArrayList getManoObra(){
         
        
        return this.manoObra;
    }
    public String getInformacion(){
        String s="----------------------------------------------------------------------\n                                   DETALLES\n                                   ---------------\n\n"+
                "  N° Orden: "+this.numeroOrden+"\n  Cliente: "+this.cliente+"\n  Embarcacion: "+this.embrcacion+"\n  Fecha: "+this.getFecha()+"\n  Cancelada: "+this.getCancelado()+
                "\n\n                                MANO DE OBRA\n                                ----------------------\n\n"+this.getManoObraVisualizar()+
                "\n\n                                   REPUESTOS\n                                   ------------------\n\n"+this.getRepuestos()+
                "\n\n                                        VARIOS\n                                        -----------\n\n"+" "+this.getVarios()+
                "\n\n                                          NOTA\n                                          --------\n\n"+this.getNota()+
                "\n\n                                         COSTE\n                                         ----------\n\n  Mano de obra: "+Integer.toString(this.getTotalManoObra())+"\n  Repuestos: "+Integer.toString(this.getTotalRepuestos())+
                "\n  Varios: "+Integer.toString(this.getTotalVarios())+"\n  TOTAL: "+Integer.toString(this.getTotal())+
                "\n\n----------------------------------------------------------------------\n";
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
    public void setTotalRepuestos(String totalRepuestos) {
        this.totalRepuestos = parseInt(totalRepuestos,10);
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


    public String getManoObraVisualizar() {
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
       for (Map.Entry<String, String> entry : repuestos.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
            rep+=" - "+entry.getValue()+" "+entry.getKey()+"\n";
        }
        return rep;
    }

    public void addRepuesto(String repuesto, String cant) {
        System.out.println(" R"+repuesto+"   c: "+cant);
        this.repuestos.put(repuesto,cant);
        
    }
    
}
