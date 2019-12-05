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
public class Guarderia {
    int precio; 
    int abono1,abono2,abono3;
    ArrayList<Integer> pagos=new ArrayList<>();
    HashMap<String ,ArrayList> hashmapClientes= new HashMap<>();

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getAbono1() {
        return abono1;
    }

    public void setAbono1(int abono1) {
        this.abono1 = abono1;
    }

    public int getAbono2() {
        return abono2;
    }

    public void setAbono2(int abono2) {
        this.abono2 = abono2;
    }

    public int getAbono3() {
        return abono3;
    }

    public void setAbono3(int abono3) {
        this.abono3 = abono3;
    }
    
}
