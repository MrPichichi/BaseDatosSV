/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Psche
 */
public class Cliente {
    HashMap<String ,Orden> hashMapOrdenes= new HashMap<>();
    ArrayList<String> arrayListOrdenes=new ArrayList<>();
    String[] listadoOrdenes =new String[1];
    
    HashMap<String ,Embarcacion> hashMapEmbarcaciones= new HashMap<>();
    ArrayList<String> listadoEmbarcaciones =new ArrayList<>();
    String [] listadoDeembarcaciones=new String[1000];
    
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
    int numCliente=0;
    Interfaz interfaz;

    public String[] getListadoOrdenes() {
        return listadoOrdenes;
    }
        public void actualizarListadoOrdenes(){
            this.arrayListOrdenes=new  ArrayList<>();
            hashMapOrdenes.entrySet().forEach((Map.Entry<String, Orden> entry) -> {
                this.arrayListOrdenes.add(entry.getValue().getNumeroOrden());
            });
            Collections.sort(this.arrayListOrdenes, String::compareTo);
            listadoOrdenes=new String[arrayListOrdenes.size()];
            Collections.sort(this.arrayListOrdenes, String::compareTo);
            for (int g=0;g<this.arrayListOrdenes.size();g++) {
                listadoOrdenes[g]=this.arrayListOrdenes.get(g);
        }
    }
    public void actualizarTXTOrden(Orden or){
            try {
            String ruta = "Ordenes.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.hashMapOrdenes.size();f++){
                        Orden orden=this.hashMapOrdenes.get(this.arrayListOrdenes.get(f));
                        bw.write("#");
                        bw.newLine();
                        bw.write(orden.getNumeroOrden());
                        bw.newLine();
                        bw.write(Integer.toString(orden.getTotalManoObra()));
                        bw.newLine();
                        bw.write(Integer.toString(orden.getTotalVarios()));
                        bw.newLine();
                        bw.write(Integer.toString(orden.getTotalRepuestos()));
                        bw.newLine();
                        bw.write(Integer.toString(orden.getTotal()));
                        bw.newLine();
                        bw.write(orden.getCliente());
                        bw.newLine();
                        bw.write(orden.getEmbrcacion());
                        bw.newLine();
                        bw.write(orden.getFecha());
                        bw.newLine();
                        bw.write(orden.getCancelado());
                        bw.newLine();
                        bw.write(orden.getNota());
                        bw.newLine();
                        bw.write("*#");
                        bw.newLine();
                        bw.write(orden.getVarios());
                        bw.newLine();
                        bw.write("#*");
                        for(int l=0;l<orden.getManoObra().size();l++){
                            bw.newLine();
                            bw.write((String) orden.getManoObra().get(l));
                        }
                        bw.newLine();
                        bw.write("**");
                        if(!or.listadoRepuestos.isEmpty()){
                            for(int l=0;l<or.listadoRepuestos.size();l++){
                                bw.newLine();
                                bw.write((String)or.listadoRepuestos.get(l).getCantidad());
                                bw.newLine();
                                bw.write((String)or.listadoRepuestos.get(l).getNombre());
                            }
                            bw.newLine();
                            bw.write("***");
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        }
    }
    public int getNumCliente() {
        return numCliente;
    }
    public void loadOrdenes(){
         final File carpeta = new File("Clientes/"+this.getNumCliente()+"/Ordenes");
         System.out.println("Clientes/"+this.getNumCliente()+"/Ordenes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isFile()) {
                System.out.println("\nTrtando de cargar: "+ficheroEntrada.getName());
                this.cargarOrdenes(ficheroEntrada);
                
            }
        }
        this.actualizarListadoOrdenes();
    }
    public void imprimirOrdenes(){
    Iterator it = this.hashMapOrdenes.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry e = (Map.Entry)it.next();
        System.out.println(e.getKey() + " " + e.getValue());
    }
    
    }
    public void cargarOrdenes(File o){
            File f = o; 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Orden or= new Orden();
            while(entrada.ready()){ 
                linea = entrada.readLine();
                while(!"##".equals(linea)&& entrada.ready()){
                    System.out.println(linea);
                    if("#".equals(linea)){
                        or= new Orden();
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setNumeroOrden(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setTotalManoObra(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setTotalVarios(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setTotalRepuestos(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setTotal(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setCliente(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setEmbrcacion(linea);
                        linea = entrada.readLine(); 
                        System.out.println(linea);
                        or.setFecha(linea);
                        linea = entrada.readLine();
                        System.out.println(linea);
                        if("SI".equals(linea)){
                            or.setDeudaSi();
                        }
                        if("NO".equals(linea)){
                            or.setDeudaNo();
                        }
                        linea = entrada.readLine(); 
                        String nota=""; 
                        while(!"*#".equals(linea)){
                            nota+=" "+linea+"\n";
                            linea = entrada.readLine();
                        }
                        or.setNota(nota);
                        linea = entrada.readLine();
                        String varios=""; 
                         while(!"#*".equals(linea)){
                            varios+=" "+linea+"\n";
                            linea = entrada.readLine();
                        }
                        or.setVarios(varios);
                    }
                    if("#*".equals(linea)){
                        linea = entrada.readLine(); 
                        or.manoObra=new ArrayList<>();
                        while(!"**".equals(linea)){
                            or.addManoObra(linea);
                            linea = entrada.readLine(); 
                        }
                    }
                    String cant=entrada.readLine();
                    if("##".equals(cant)){
                        break;
                    }
                    while(!"***".equals(cant)){
                        System.out.println("Linea 1: "+cant);
                        Repuesto rep=new Repuesto();
                        if(!"***".equals(cant)){
                            linea = entrada.readLine();
                             System.out.println("Linea 2: "+linea);
                            //rep.setCantidad(cant);
                            //rep.setPrecio(linea);
                            //or.addRepuesto(rep);
                            System.out.println(or.listadoRepuestos.toString());
                            cant=entrada.readLine();
                            
                            
                        }
                    }
                    //System.out.println(or.getInformacion());
                    this.hashMapOrdenes.put(or.getOrdenID(),or);
                    this.actualizarListadoOrdenes();
                }
                //this.hashmapOrdenes.put(or.getNumeroOrden(),or);
                //this.arrayListOrdenes.add(or.getNumeroOrden());
            } 
            }catch (IOException e) { 
                e.printStackTrace(); 
            } 
            finally{ 
                try{ 
                    entrada.close(); 
                }
                catch(IOException e1){} 
            } 
    }
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }
    public String getTelFijoOficina1() {
        return TelFijoOficina1;
    }
    public void crearTxTEmbarcacion(Embarcacion emb){
    try {   System.out.println("\n Creadno EMbarcacion\n");
            File file = new File("Clientes/"+Integer.toString(this.getNumCliente())+"/Embarcaciones/"+emb.getCodigo()+".txt");
            Cliente cl= this;
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            //this.actualizarListadoEmbarcaciones();
            System.out.println("Antes de crear: "+emb.getInformacion());
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                        bw.write("#");
                        bw.newLine();
                        bw.write(emb.codigo);
                        bw.newLine();
                        bw.write(emb.tipo);
                        bw.newLine();
                        bw.write(emb.marca);
                        bw.newLine();
                        bw.write(emb.modelo);
                        bw.newLine();
                        bw.write(emb.motor);
                        bw.newLine();
                        bw.write(emb.nSerie);
                        bw.newLine();
                        bw.write(emb.llave);
                        bw.newLine();
                        bw.write("##");
                    }
                    
        } catch (IOException e) {
        }
        
    }
    public void crearTxTOrdenes(Orden ord){
        try {   
            ord.setNumTxT(Integer.toString(this.arrayListOrdenes.size()));
            File file = new File("Clientes/"+this.getNumCliente()+"/Ordenes/"+ord.getNumTxT()+".txt");
            Cliente cl= this;
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            //this.actualizarListadoEmbarcaciones();
            //System.out.println("Antes de crear: "+emb.getInformacion());
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                        bw.write("#");
                        bw.newLine();
                        bw.write(ord.getNumeroOrden());
                        bw.newLine();
                        bw.write(Integer.toString(ord.getTotalManoObra()));
                        bw.newLine();
                        bw.write(Integer.toString(ord.getTotalVarios()));
                        bw.newLine();
                        bw.write(Integer.toString(ord.getTotalRepuestos()));
                        bw.newLine();
                        bw.write(Integer.toString(ord.getTotal()));
                        bw.newLine();
                        bw.write(ord.getCliente());
                        bw.newLine();
                        bw.write(ord.getEmbrcacion());
                        bw.newLine();
                        bw.write(ord.getFecha());
                        bw.newLine();
                        bw.write(ord.getCancelado());
                        bw.newLine();
                        bw.write(ord.getNota());
                        bw.newLine();
                        bw.write("*#");
                        bw.newLine();
                        bw.write(ord.getVarios());
                        bw.newLine();
                        bw.write("#*");
                        for(int l=0;l<ord.getManoObra().size();l++){
                            bw.newLine();
                            bw.write((String) ord.getManoObra().get(l));
                        }
                        bw.newLine();
                        bw.write("**");
                        if(!ord.listadoRepuestos.isEmpty()){
                            for(int l=0;l<ord.listadoRepuestos.size();l++){
                                bw.newLine();
                                bw.write((String)ord.listadoRepuestos.get(l).getCantidad());
                                bw.newLine();
                                bw.write((String)ord.listadoRepuestos.get(l).getNombre());
                            }
                            bw.newLine();
                            bw.write("***");
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
                    
        } catch (IOException e) {
        }
        
    }
    public void actualizarListadoEmbarcaciones(){
        this.listadoDeembarcaciones=new String[this.listadoEmbarcaciones.size()];
        for(int x=0;x<this.listadoEmbarcaciones.size();x++ ){
            listadoDeembarcaciones[x]=this.listadoEmbarcaciones.get(x);
        }
    }
//    public void actualizarListadoOrdenes(){
//        this.listadoOrdenes=new String[this.arrayListOrdenes.size()];
//        for(int x=0;x<this.arrayListOrdenes.size();x++ ){
//            listadoOrdenes[x]=this.arrayListOrdenes.get(x);
//        }
//    }
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
    public void eliminarEmbarcacion(Embarcacion emb){
        this.hashMapEmbarcaciones.remove(emb.getTipoMarca());
        this.listadoEmbarcaciones.remove(emb.getTipoMarca());
        final File carpeta = new File("Clientes/"+this.numCliente+"/Embarcaciones");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isFile() && ficheroEntrada.getName().equals(emb.getCodigo()+".txt")) {
                System.out.println("ELIMINANDO TXT EMBARCACION");
                ficheroEntrada.delete();
            }
        }
        System.out.println(this.listadoEmbarcaciones.toString());
        this.actualizarListadoEmbarcaciones();
    }
   

    public String[] getListadoEmbarcaciones() {
        this.listadoDeembarcaciones=new String[this.listadoEmbarcaciones.size()];
        for (int g=0;g<this.listadoEmbarcaciones.size();g++) {
            listadoDeembarcaciones[g]=this.listadoEmbarcaciones.get(g);
            System.out.println("\nExistente: "+this.listadoDeembarcaciones[g]);
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
    public void addEmbarcacion(Embarcacion emb){
        emb.setCodigo(Integer.toString(listadoEmbarcaciones.size()));
        this.hashMapEmbarcaciones.put(emb.getTipoMarca(),emb);
        this.listadoEmbarcaciones.add(emb.getTipoMarca());
        
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
        in.add(Integer.toString(this.numCliente));
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
    public ArrayList getInformacionE(String nomEmb){
        ArrayList<String> in=new ArrayList<>();
        emb=this.hashMapEmbarcaciones.get(nomEmb);
        in.add(emb.codigo);
        in.add(emb.tipo);
        in.add(emb.marca);
        in.add(emb.modelo);
        in.add(emb.motor);
        in.add(emb.nSerie);
        in.add(emb.llave);
         
        
        return in;
    }
    public void addOrden(Orden ord){
        this.hashMapOrdenes.put(ord.getOrdenID(), ord);
        this.actualizarListadoOrdenes();
    }
    public String getInformacionClienteVisualizar(){
        return ("                                       \n\n  Numero Cliente: "+this.numCliente+"\n  Nombre: "+this.nombre+"\n  Apellido: "+this.apellido+"\n  Correo: "+this.correo+"\n  Celular: "+this.celular+
                "\n  Telefono fijo: "+this.TelFijo+"\n  Telefono fijo Oficina 1: "+this.TelFijoOficina1+"\n  Telefono fijo Oficina 2: "+this.TelFijoOficina2+"\n  Cuidador: "+this.cuidador+"\n  Numero Celular de Cuidador: "+
                this.celularCuidador+"\n  Guarderia: "+this.getGuarderia()+"\n  Deuda Guarderia: "+this.getDeudaGuarderia()+"\n  Deuda Orden: "+this.getDeudaOrden());
    }
    public String getInformacionEmbarcaciones(){
        String infoEmb="\n                             EMBARCACIONES";
        for(int x=0;x<this.listadoEmbarcaciones.size();x++){
            this.emb=this.hashMapEmbarcaciones.get(this.listadoEmbarcaciones.get(x));
            infoEmb+="\n----------------------------------------------------------------------"
                    + "\n  Tipo: "+emb.tipo+"\n  Marca: "+emb.marca+"\n  Modelo: "+
                    emb.modelo+"\n  Motor: "+emb.motor+"\n  Numero Serie: "+emb.nSerie+
                    "\n  Numero Embarcacion: "+emb.codigo+"\n  Clave Embarcacion: "+emb.llave+"\n----------------------------------------------------------------------";
        }
        return infoEmb;
    }
    public void setCelularCuidador(String celularCuidador) {
        this.celularCuidador = celularCuidador;
    }
   
}
