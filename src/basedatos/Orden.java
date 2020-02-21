/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;

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
    String numTxT;
    int totalManoObra;
    int totalVarios;
    int total;
    int totalRepuestos;
    boolean cancelado=false;
    ArrayList<String> manoObra = new ArrayList<>();
    ArrayList <Repuesto> listadoRepuestos= new ArrayList<>();
    public String getEmbrcacion() {
        return embrcacion;
    }
    public String getNumTxT() {
        return numTxT;
    }

    public void setNumTxT(String numCarpeta) {
        this.numTxT = numCarpeta;
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
    public String getDeuda() {
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
        String s="----------------------------------------------------------------------\n                                   DETALLES\n----------------------------------------------------------------------\n\n"+
                "N° Orden: "+this.numeroOrden+"\nCliente: "+this.cliente+"\nEmbarcacion: "+this.embrcacion+"\nFecha: "+this.getFecha()+"\nCancelada: "+this.getCancelado()+
                "\n\nMANO DE OBRA"+this.getManoObraVisualizar()+
                "\n\nREPUESTOS"+this.getRepuestos()+
                "\n\nVARIOS\n "+this.getVarios()+
                "\n\nNOTA\n"+this.getNota()+
                "\n\nCOSTE\n   Mano de obra: $ "+Integer.toString(this.getTotalManoObra())+"\n   Repuestos: $ "+Integer.toString(this.getTotalRepuestos())+
                "\n   Varios: $ "+Integer.toString(this.getTotalVarios())+"\n   TOTAL: $ "+Integer.toString(this.getTotal())+
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
            mObra+="\n - "+this.manoObra.get(c);
        }
        return mObra;
    }
    public void addManoObra(String mo) {
        this.manoObra.add(mo);
    }
    public String getRepuestos() {
       String rep="";
       //System.out.println("cant rep: "+this.listadoRepuestos.size());
       for (int f=0; f<this.listadoRepuestos.size();f++) {
            Repuesto repu=this.listadoRepuestos.get(f);
            //System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
            Double t=repu.cantidad*repu.precio;
            rep+="\n - "+repu.getCantidad()+" "+repu.getNombre()+"   $ "+repu.getPrecio();
        }
       System.out.println(rep);
        return rep;
    }
    public String getOrdenID(){
        String ordEMb=this.embrcacion+" "+this.numeroOrden+" "+this.fecha;
            
        return ordEMb;
    }
    public void addRepuesto(Repuesto repuesto) {
        //System.out.println("Añadiendo Repuesto: "+repuesto.getNombre()+"  Codigo: "+repuesto.getCodigo()+"  Precio: "+repuesto.getPrecio()+"   cantidad: "+repuesto.getCantidad());
        System.out.println(repuesto.getInfoParaTxT());
        this.listadoRepuestos.add(repuesto);
    }
}
