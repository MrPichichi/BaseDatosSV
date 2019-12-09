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
public class Pago {
    int total=0;
    int abono1=0,abono2=0,abono3=0; 

    public Pago(String tot) {
        this.total=Integer.parseInt(tot);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
