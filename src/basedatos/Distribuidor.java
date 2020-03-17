
package basedatos;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Distribuidor extends Contactos{
    public String empresa="vacio";
    public String cuentaDeposito="vacio";

    public int getNumCliente() {
        return numCliente;
    }
    public ArrayList getInformacionDistribuidor(){
        ArrayList<String> in=new ArrayList<>();
        in.add(Integer.toString(this.numCliente));
        in.add(this.nombre);
        in.add(this.apellido);
        in.add(this.celular);
        in.add(this.TelFijo);
        in.add(this.TelFijoOficina1);
        in.add(this.TelFijoOficina2);
        in.add(this.correo);
        in.add(this.empresa);
        in.add(this.cuentaDeposito);
        return in;
    
    }
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCuentaDeposito() {
        return cuentaDeposito;
    }

    public void setCuentaDeposito(String cuentaDeposito) {
        this.cuentaDeposito = cuentaDeposito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public String getApellidoNombre() {
        return apellido+" "+nombre;
    }
    public String getNombreApellido() {
        return nombre+" "+apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelFijo() {
        return TelFijo;
    }

    public void setTelFijo(String TelFijo) {
        this.TelFijo = TelFijo;
    }

    public String getTelFijoOficina1() {
        return TelFijoOficina1;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
