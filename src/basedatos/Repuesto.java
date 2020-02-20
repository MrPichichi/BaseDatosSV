/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;
import java.util.ArrayList;
/**
 *
 * @author Psche
 */
public class Repuesto {
    String nombre;
    String codigo="vacio";;
    int precio;
    double cantidad=0;
    public String getInformacionVisualizar(){
        String s="\n Repuesto: "+this.nombre+"\n Precio: "+this.getPrecio()+"\n Codigo: "+this.getCodigo()+"\n Disponibles: "+this.getCantidad();
        return s;
    }
    public ArrayList getInformacionR(){
        ArrayList<String> in=new ArrayList<>();
        in.add(this.nombre);
        in.add(this.codigo);
        in.add(this.getPrecio());
        in.add(this.getCantidad());
        return in;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombe) {
        this.nombre = nombe;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getPrecio() {
        
        return Integer.toString(precio);
    }
    public void setPrecio(String precio) {
        this.precio = Integer.parseInt(precio);
    }
    public String getCantidad() {
        return Double.toString(cantidad);
    }
    public void setCantidad(String cantidad) {
        this.cantidad = Double.parseDouble(cantidad);
    }
}
