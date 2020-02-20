/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Psche
 */
public class Guarderia {
    Pago pago;
    ArrayList<String> listadoEmbarcaciones=new ArrayList<>();
    HashMap<String ,HashMap> embarcacionsYPAgos=new HashMap<>();
    ArrayList<String> año=new ArrayList<>();
    //<embarcacion><HasMap<año><pagos>>
    HashMap<String ,Pago> abonosYTOtal=new HashMap<>();
    HashMap<String ,Integer> PrecioDeembarcaciones=new HashMap<>();
    Cliente cliente;

    public Pago getPago() {
        return pago;
    }
    public void setPago(Pago pago) {
        this.pago = pago;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void inicializarAñosGuarderia(String precio){
        abonosYTOtal=new HashMap<>();
        int añoInicio=2014;
        int añoTermino=2040;
        while(añoInicio<=añoTermino){
            año.add( Integer.toString(añoInicio) );
            añoInicio+=1;
        }
        pago=new Pago(precio);
        for(int g=0;g<año.size();g++){
            //System.out.println(año.get(g));
            abonosYTOtal.put(año.get(g), pago);
        }
        abonosYTOtal.entrySet().forEach((Map.Entry<String, Pago> entry) -> {
            //System.out.println(" Fecha: "+entry.getKey());
            //Collections.sort(Interfaz.this.arrayListOrdenes, String::compareTo);
        });
    }
    public void agregarLanchaAGuarderia(String embarcacion, String precio){
         this.inicializarAñosGuarderia(precio);
        this.embarcacionsYPAgos.put(embarcacion, abonosYTOtal);
    }
}
