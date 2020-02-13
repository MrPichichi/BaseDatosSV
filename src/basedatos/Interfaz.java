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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    
    
    Embarcacion embarcacion= new Embarcacion();
    Cliente cliente= new Cliente();
    Orden orden= new Orden();
    Repuesto repuesto= new Repuesto();
    Guarderia guarderia=new Guarderia();
    //CLIENTES
    HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
    ArrayList<String> arrayListContactos=new ArrayList<>();
    String [] listadoClientes = new String[1000];
    //ELIMINADOS
    HashMap<String ,Cliente> hashmapClientesBorrados= new HashMap<>();
    ArrayList<String> arrayListContactosEliminados=new ArrayList<>();
    String [] listadoClientesEliminados = new String[1000];	
    
    
    //ORDENES
    HashMap<String ,Orden> hashmapOrdenes= new HashMap<>();
    ArrayList<String> arrayListOrdenes=new ArrayList<>();
    String [] listadoOrdenes = new String[1000];
    
    HashMap<String ,Orden> hashmapOrdenesEliminadas= new HashMap<>();
    ArrayList<String> arrayListOrdenesEliminadas=new ArrayList<>();
    String [] listadoOrdenesEliminadas = new String[1000];
    //Crear ORden
    ArrayList<Repuesto> arrayListOrdenesAñadirRepuestosCliente=new ArrayList<>();
    String [] listadoOrdenesAñadirRepuestosCliente = new String[1000];
    String [] listadoOrdenesAñadirRepuestosClienteCantidad = new String[1000];
        //MANO OBRA
    ArrayList<String> arrayListOrdenesAñadirManoObra=new ArrayList<>();
    String [] listadoOrdenesAñadirManoObra= new String[1000];
    
    HashMap<String ,Guarderia> hashmapClientesGuarderia= new HashMap<>();
    ArrayList<String> arrayListContactosGuarderia=new ArrayList<>();
    String [] listadoClientesGuarderia = new String[1000];
    
    
    //REPUESTOS
    HashMap<String ,Repuesto> hashmapRepuestos= new HashMap<>();
    ArrayList<String> arrayListRepuestos=new ArrayList<>();
    String [] listadoRepuestos = new String[1000];
        //ELIINADOS
    HashMap<String ,Repuesto> hashmapRepuestosEliminados= new HashMap<>();
    ArrayList<String> arrayListRepuestosEliminados=new ArrayList<>();
    String [] listadoRepuestosEliminados = new String[1000];
    //Mano obra
    ArrayList<String> arrayListManoObra=new ArrayList<>();
    String [] listadoManoObra = new String[1000];
     //Embarcaciones
    
    
    
    boolean selected=false; 
    int totalRepuestos=0,totalManoObra=0;
    
     public Interfaz() {
         
        this.setVisible(true);
        this.initComponents();
        
        this.cargarTxTClientes();
        //this.crearArchivosContacto("Gamboa Francisco");
        //this.actualizarListadoContactos();
        //this.setearTablasListadoClientes();
        //this.cargarManoObra();
        //this.actualizarListadoManoObra();
        
        //this.cargarClientes();
        //this.cargarClientesEliminados();
        //this.updateVCliente();
        //this.cargarRepuestos();
        
        //this.cargarOrdenes();
        //this.updateVRepuesto();
        //actualizamos listas
        //this.updateVOrden();  
        
        //guarderia=new Guarderia();
        
        //guarderia.agregarLanchaAGuarderia("Yamaha", "300000");
        //this.añadirGuarderia("Gamboa Felipe");
    }
    public void listarClientes() {
        this.arrayListContactos= new ArrayList<>();
        final File carpeta = new File("Clientes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.arrayListContactos.add(ficheroEntrada.getName());
            }
        }
        this.actualizarListadoContactos();
        this.setearTablasListadoClientes();
    }
    public void cargarTxTClientes() {
        this.hashmapClientes = new HashMap<>();
        this.arrayListContactos= new ArrayList<>();
        final File carpeta = new File("Clientes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarCarpetaCliente(ficheroEntrada);
               this.arrayListContactos.add(ficheroEntrada.getName());
            }
        }
        this.actualizarListadoContactos();
        this.setearTablasListadoClientes();
    }
    public void cargarArchivosCliente(String apellidoNombre) {
        final File carpeta = new File("Clientes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(apellidoNombre)) {
               //System.out.println(ficheroEntrada.getName());
               this.cargarCarpetaCliente(ficheroEntrada);
            }
        }
    }
  
    public ArrayList cargarOrdenesCliente(File ficheroEntrada) {
        ArrayList<String> ordenesCliente=new ArrayList<>();
        final File carpeta = ficheroEntrada;
        for (final File archivo : carpeta.listFiles()) {
            if(archivo.isFile()) {
               ordenesCliente.add(archivo.getName());
               System.out.println("Orden"+archivo.getName());
            }
        }
        System.out.println(ordenesCliente);
        return ordenesCliente;
    }
    public void cargarCarpetaCliente(File carpetaCliente) {
        System.out.println("\nLOADING CARPETA");
        for (final File ficheroEntrada : carpetaCliente.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
               this.cargarInformacionCliente(ficheroEntrada);
            }
        }
    }
    public void cargarInformacionCliente(File carpetainfo) {
        System.out.println("LOADING INFO");
        for (final File ficheroEntrada : carpetainfo.listFiles()) {
            if (ficheroEntrada.isFile()) {
               System.out.println("Cliente: "+ficheroEntrada.getName());
               this.cargarCliente(ficheroEntrada);
            }
        }
    }
    public void cargarTXTInfo(String cliente){
        try {
            String ruta = "ClientesEliminados.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListContactosEliminados.size();f++){
                        Cliente cl =this.hashmapClientesBorrados.get(this.arrayListContactosEliminados.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        for(int l=0;l<cl.getInformacionE().size();l++){
                            bw.write((String) cl.getInformacionE().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        
    } 
        
    }
    public void actualizarTXTManoObra(){
          try {
            String ruta = "ManoObra.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListManoObra.size();f++){
                        bw.write("#");
                        bw.newLine();
                        bw.write(this.arrayListManoObra.get(f));
                        bw.newLine();
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        }
    
      
    }    
    /**
     * Creates new form Main
     */
    public void añadirGuarderia(String nombreApellido){
        this.actualizarListadoContactos();
        guarderia=new Guarderia();
        guarderia.setCliente(this.hashmapClientes.get(nombreApellido));
        this.hashmapClientesGuarderia.put(nombreApellido, guarderia);
        
     }
    
    public void añadirEmbAGuardria( String emba, String preci ){
        guarderia.inicializarAñosGuarderia(preci);
        for(int x=0;x<this.arrayListContactos.size();x++){
            cliente=this.hashmapClientes.get(this.arrayListContactos.get(x));
                for(int r=0;r<cliente.listadoEmbarcaciones.size();r++){
                    embarcacion=cliente.embarcaciones.get(cliente.listadoEmbarcaciones.get(r));
                    if(embarcacion.getMotor().equals(emba)){
                        
                        
                    }
                }
        }
        guarderia.agregarLanchaAGuarderia(emba, preci);
    }
    public void actualizarGuarderias(){
        this.actualizarListadoContactos();
        for(int x=0;x<this.arrayListContactos.size();x++){
            cliente=this.hashmapClientes.get(this.arrayListContactos.get(x));
            if("SI".equals(cliente.getGuarderia())){
                for(int r=0;r<cliente.listadoEmbarcaciones.size();r++){
                    embarcacion=cliente.embarcaciones.get(cliente.listadoEmbarcaciones.get(r));
                    if(embarcacion.getEnGuarderia()==true){
                        guarderia=new Guarderia();
                        
                        guarderia.setCliente(cliente);
                        
                        //this.hashmapClientesGuarderia.put(cliente.getNombre(),);
                    }
                }
                //
            }
        }
    }
    public void actualizarTXTInfoContacto(File info, Cliente cliente){
          try {
            File file = info;
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListContactos.size();f++){
                        Cliente cl=cliente;
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            //System.out.println(cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        if(!cl.getInformacionE().isEmpty()){
                            for(int l=0;l<cl.getInformacionE().size();l++){
                                bw.write((String) cl.getInformacionE().get(l));
                                bw.newLine();
                            }
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        }
    
      
    }
     public void actualizarTXTOrdenes(){
            try {
            String ruta = "Ordenes.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            //System.out.println("ORDENES cantidad :"+this.hashmapOrdenes.size());
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.hashmapOrdenes.size();f++){
                        orden=this.hashmapOrdenes.get(this.arrayListOrdenes.get(f));
                        //System.out.println("ACTUALIZAR:"+orden.getInformacion());
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
                        if(!this.arrayListOrdenesAñadirRepuestosCliente.isEmpty()){
                            for(int l=0;l<this.arrayListOrdenesAñadirRepuestosCliente.size();l++){
                                //or.listadoRepuestos.get(l);
                                //or.repuestos.get(or.listadoRepuestos.get(l));
                                bw.newLine();
                                bw.write((String)this.arrayListOrdenesAñadirRepuestosCliente.get(l).getCantidad());
                                bw.newLine();
                                bw.write((String)this.arrayListOrdenesAñadirRepuestosCliente.get(l).getNombre());
                                
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
    public void actualizarTXTOrdenesEliminadas(){
           try {
            String ruta = "OrdenesEliminadas.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            //System.out.println("ORDENES cantidad :"+this.hashmapOrdenesEliminadas.size());
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.hashmapOrdenesEliminadas.size();f++){
                        orden=this.hashmapOrdenesEliminadas.get(this.arrayListOrdenesEliminadas.get(f));
                        //System.out.println("ACTUALIZAR ELIMINADA:"+orden.getInformacion());
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
                        if(!orden.listadoRepuestos.isEmpty()){
                            for(int l=0;l<orden.listadoRepuestos.size();l++){
                                //or.listadoRepuestos.get(l);
                                //or.repuestos.get(or.listadoRepuestos.get(l));
                                bw.newLine();
                                bw.write((String)orden.listadoRepuestos.get(l).getCantidad());
                                bw.newLine();
                                bw.write((String)orden.listadoRepuestos.get(l).getNombre());
                                
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
    
    public void actualizarTXTRepuestos(){
          try {
            String ruta = "Repuestos.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListRepuestos.size();f++){
                        Repuesto rep=this.hashmapRepuestos.get(this.arrayListRepuestos.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<rep.getInformacionR().size();l++){
                            bw.write((String) rep.getInformacionR().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        }
    
      
    }
    public void crearArchivosContacto(String apellidoNombre, Cliente cliente){
        String dir="Clientes/"+apellidoNombre;
        File crear_CC = new File(dir);
        File crear_CO = new File(dir+"/Ordenes");
        File crear_CI = new File(dir+"/Informacion"); 
        File crear_CG = new File(dir+"/Guarderia"); 
        File crear_CE = new File(dir+"/Embarcaciones"); 
        File crear_TxTInfo = new File(dir+"/Informacion/Info.txt");
        
        crear_CC.mkdirs();
        crear_CO.mkdirs();
        crear_CI.mkdirs();
        crear_CG.mkdirs();
        crear_CE.mkdirs();
        
        try{
            crear_TxTInfo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        File info = new File(dir+"/Informacion/Info.txt");
        this.actualizarTXTInfoContacto(info,cliente);
        
    } 
    public void cargarCliente(File f){
        System.out.println("CARGANDO");
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Cliente cl= new Cliente();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        //creamos cliente
                        cl= new Cliente();
                        linea = entrada.readLine(); 
                        cl.setNombe(linea);
                        //seteamos nombre
                        linea = entrada.readLine(); 
                        cl.setApellido(linea);
                        //seteamos Celular
                        linea = entrada.readLine(); 
                        cl.setCelular(linea);
                        //seteamos TelFijo
                        linea = entrada.readLine(); 
                        cl.setTelFijo(linea);
                        
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina1(linea);
                        
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina2(linea);
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cl.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cl.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cl.setCelularCuidador(linea);
                        linea = entrada.readLine(); 
                        if("SI".equals(linea)){
                            cl.setGuarderiaSi();
                        }
                        else{
                            cl.setGuarderiaNO();
                        }
                        linea = entrada.readLine(); 
                        //System.out.println("Deuda G: "+linea);
                        if("SI".equals(linea)){
                            cl.setDeudaGuarderiaSI();
                            //System.out.println("seteando: "+linea);
                        }
                        else{
                            //System.out.println("seteando: "+linea);
                            cl.setDeudaGuarderiaNO();
                        }
                        linea = entrada.readLine(); 
                        if("SI".equals(linea)){
                            cl.setDeudaOrdenSi();
                        }
                        else{
                            cl.setDeudaOrdenNo();
                        }
                        linea = entrada.readLine(); 
                        //System.out.println(cl.getInformacionClienteVisualizar());
                    }
                    while(!"##".equals(linea)){
                        embarcacion=new Embarcacion();
                        linea = entrada.readLine(); 
                        embarcacion.setTipo(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setMarca(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setModelo(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setMotor(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setnSerie(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setCodigo(linea);
                        linea = entrada.readLine(); 
                        cl.addEmbarcacion(embarcacion.getMotor(), embarcacion);
                        
                    }
                    System.out.println(cl.getInformacionClienteVisualizar());
                    this.hashmapClientes.put(cl.getApellidoNombre(),cl);
                    
                }
                
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
            //extrae contactos
            
    }
    public void cargarRepuestos(){
            File f = new File( "Repuestos.txt" ); 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Repuesto rep= new Repuesto();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                //System.out.println("Linea entrante: "+rep.getNombre());
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        rep= new Repuesto();
                        linea = entrada.readLine(); 
                        rep.setNombre(linea);
                        //System.out.println("nombre: "+rep.getNombre());
                        linea = entrada.readLine(); 
                        rep.setCodigo(linea);
                        //System.out.println("codigo: "+rep.getCodigo());
                        linea = entrada.readLine(); 
                        rep.setPrecio(linea);
                        //System.out.println("precio: "+rep.getPrecio());
                        linea = entrada.readLine(); 
                        rep.setCantidad(linea);
                        //System.out.println("cant: "+rep.getCantidad());
                        this.hashmapRepuestos.put(rep.getNombre(), rep);
                        //System.out.println("Repuesto Agregado: "+rep.getNombre());
                        linea = entrada.readLine(); 
                    }
                }
            } 
            }catch (IOException e) { 
            } 
            finally{ 
                try{ 
                    entrada.close(); 
                }
                catch(IOException e1){} 
            } 
            
            //extrae contactos
            
    }
    public void cargarManoObra(){
            File f = new File( "ManoObra.txt" ); 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            this.arrayListManoObra= new ArrayList<>();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                //System.out.println("Linea entrante: "+linea);
                if("#".equals(linea)){
                    linea = entrada.readLine(); 
                    while(!"##".equals(linea)){
                            if(!"##".equals(linea)){
                                this.arrayListManoObra.add(linea);
                                //System.out.println("Linea Manoobra: "+linea);
                            }
                    linea = entrada.readLine(); 
                    }
                }
            } 
            }catch (IOException e) { 
            } 
            finally{ 
                try{ 
                    entrada.close(); 
                }
                catch(IOException e1){} 
            } 
            
            //extrae contactos
            
    }
    
    public void actualizarTXTContactosEliminados(){
          try {
            String ruta = "ClientesEliminados.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListContactosEliminados.size();f++){
                        Cliente cl =this.hashmapClientesBorrados.get(this.arrayListContactosEliminados.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        for(int l=0;l<cl.getInformacionE().size();l++){
                            bw.write((String) cl.getInformacionE().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
        }
    } 
    public void cargarOrdenes(){
            File f = new File( "Ordenes.txt" ); 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Orden or= new Orden();
            String cant="";
            while(entrada.ready()){ 
                linea = entrada.readLine();
                while(!"##".equals(cant)&& entrada.ready()){
                    if("#".equals(linea)){
                        or= new Orden();
                        linea = entrada.readLine(); 
                        or.setNumeroOrden(linea);
                        linea = entrada.readLine(); 
                        or.setTotalManoObra(linea);
                        linea = entrada.readLine(); 
                        or.setTotalVarios(linea);
                        linea = entrada.readLine(); 
                        or.setTotalRepuestos(linea);
                        linea = entrada.readLine(); 
                        or.setTotal(linea);
                        linea = entrada.readLine(); 
                        or.setCliente(linea);
                        linea = entrada.readLine(); 
                        or.setEmbrcacion(linea);
                        linea = entrada.readLine(); 
                        or.setFecha(linea);
                        linea = entrada.readLine();
                        if("SI".equals(linea)){
                            or.setDeudaSi();
                        }
                        if("NO".equals(linea)){
                            or.setDeudaNo();
                        }
                        linea = entrada.readLine(); 
                        String nota=""; 
                        //System.out.println("lin: "+linea);
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
                        //System.out.println(nota);
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
                    cant=entrada.readLine();
                    //System.out.println("Entrando: "+cant);
                    if("##".equals(cant)){
                        break;
                    }
                    while(!"***".equals(cant)){
                        if(!"***".equals(cant)){
                            linea = entrada.readLine();
                            //System.out.println("linea: "+linea+" Cant: "+cant);
                            or.addRepuesto(cant,linea);
                            cant=entrada.readLine();
                            
                        }
                    }
                }
                this.hashmapOrdenes.put(or.getNumeroOrden(),or);
                this.arrayListOrdenes.add(or.getNumeroOrden());
                //System.out.println(or.getInformacion());
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
    public void cargarClientesEliminados(){
            File f = new File( "ClientesEliminados.txt" ); 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Cliente cl= new Cliente();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        //creamos cliente
                        
                        linea = entrada.readLine(); 
                        cl.setNombe(linea);
                        //seteamos nombre
                        linea = entrada.readLine(); 
                        cl.setApellido(linea);
                        //seteamos Celular
                        linea = entrada.readLine(); 
                        cl.setCelular(linea);
                        //seteamos TelFijo
                        linea = entrada.readLine(); 
                        cl.setTelFijo(linea);
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina1(linea);
                        
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina2(linea);
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cl.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cl.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cl.setCelularCuidador(linea);
                        linea = entrada.readLine(); 
                        if("SI".equals(linea)){
                            cl.setGuarderiaSi();
                        }
                        else{
                            cl.setGuarderiaNO();
                        }
                        linea = entrada.readLine(); 
                        //System.out.println("Deuda G: "+linea);
                        if("SI".equals(linea)){
                            cl.setDeudaGuarderiaSI();
                            //System.out.println("seteando: "+linea);
                        }
                        else{
                            //System.out.println("seteando: "+linea);
                            cl.setDeudaGuarderiaNO();
                        }
                        linea = entrada.readLine(); 
                        if("SI".equals(linea)){
                            cl.setDeudaOrdenSi();
                        }
                        else{
                            cl.setDeudaOrdenNo();
                        }
                        
                        linea = entrada.readLine(); 
                    }
                   while(!"##".equals(linea)){
                        embarcacion=new Embarcacion();
                        linea = entrada.readLine(); 
                        embarcacion.setTipo(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setMarca(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setModelo(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setMotor(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setnSerie(linea);
                        linea = entrada.readLine(); 
                        embarcacion.setCodigo(linea);
                        linea = entrada.readLine(); 
                        cl.addEmbarcacion(embarcacion.getMotor(), embarcacion);
                        
                    }this.hashmapClientesBorrados.put(cl.getApellidoNombre(),cl);
                }
                
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
            //extrae contactos
            
    }
    
    public void actualizarListadoOrdenes(){
        listadoOrdenes=new String[1000];
        this.arrayListOrdenes=new  ArrayList<>();
        hashmapOrdenes.entrySet().forEach((Map.Entry<String, Orden> entry) -> {
            Interfaz.this.arrayListOrdenes.add(entry.getValue().getNumeroOrden());
            //Collections.sort(Interfaz.this.arrayListOrdenes, String::compareTo);
        });
        //Collections.sort(Interfaz.this.arrayListOrdenes, String::compareTo);
        for (int g=0;g<this.arrayListOrdenes.size();g++) {
            listadoOrdenes[g]=this.arrayListOrdenes.get(g);
            //System.out.println("\nExistente: "+this.listadoOrdenes[g]);
        }
    }
    public void actualizarRepuestosEliminados(){
        listadoRepuestosEliminados=new String[1000];
        this.arrayListRepuestosEliminados=new  ArrayList<>();
        hashmapRepuestosEliminados.entrySet().forEach((Map.Entry<String, Repuesto> entry) -> {
            Interfaz.this.arrayListRepuestosEliminados.add(entry.getValue().getNombre());
            //System.out.println("\n Repuesto hash: "+entry.getValue().getNombre());
            
        });
        Collections.sort(Interfaz.this.arrayListRepuestosEliminados, String::compareTo);
        for (int g=0;g<this.arrayListRepuestosEliminados.size();g++) {
            listadoRepuestosEliminados[g]=this.arrayListRepuestosEliminados.get(g);
           // System.out.println("\n Repuesto: "+this.listadoRepuestosEliminados[g]);
        }
    }
    public void actualizarListadoRepuestos(){
        listadoRepuestos=new String[1000];
        this.arrayListRepuestos=new  ArrayList<>();
        hashmapRepuestos.entrySet().forEach((Map.Entry<String, Repuesto> entry) -> {
            Interfaz.this.arrayListRepuestos.add(entry.getValue().getNombre());
            //System.out.println("\n Repuesto hash: "+entry.getValue().getNombre());
            
        });
        Collections.sort(Interfaz.this.arrayListRepuestos, String::compareTo);
        for (int g=0;g<this.arrayListRepuestos.size();g++) {
            listadoRepuestos[g]=this.arrayListRepuestos.get(g);
            //System.out.println("\n Repuesto: "+this.listadoRepuestos[g]);
        }
    }
    public void actualizarListadoManoObra(){
        listadoManoObra=new String[1000];
        Collections.sort(Interfaz.this.arrayListManoObra, String::compareTo);
        for (int g=0;g<this.arrayListManoObra.size();g++) {
            listadoManoObra[g]=this.arrayListManoObra.get(g);
            
        }
    }
    public void actualizarListadoContactos(){
        listadoClientes=new String[this.arrayListContactos.size()];
        for (int g=0;g<this.arrayListContactos.size();g++) {
            listadoClientes[g]=this.arrayListContactos.get(g);
            //System.out.println("\nExistente: "+this.listadoClientes[g]);
        }
    }
    public void actualizarTXTRepuestosEliminados(){
        listadoRepuestosEliminados=new String[1000];
        this.arrayListRepuestosEliminados=new  ArrayList<>();
        for (Map.Entry<String, Repuesto> entry : hashmapRepuestosEliminados.entrySet()) {
            this.arrayListRepuestosEliminados.add(entry.getValue().getNombre());
        }
        for (int g=0;g<this.arrayListRepuestosEliminados.size();g++) {
            listadoRepuestosEliminados[g]=this.arrayListRepuestosEliminados.get(g);
            //System.out.println("\nEliminado: "+this.listadoClientesEliminados[g]);
        }
         //System.out.println("Contactos Eliminados:"+this.arrayListContactosEliminados.toString());
        //this.contactos.add(cliente.getApellidoNombre());
    }
    public void actualizarListadoContactosEliminados(){
        listadoClientesEliminados=new String[1000];
        this.arrayListContactosEliminados=new  ArrayList<>();
        for (Map.Entry<String, Cliente> entry : hashmapClientesBorrados.entrySet()) {
            this.arrayListContactosEliminados.add(entry.getValue().getApellidoNombre());
        }
        Collections.sort(Interfaz.this.arrayListContactosEliminados, String::compareTo);
        for (int g=0;g<this.arrayListContactosEliminados.size();g++) {
            listadoClientesEliminados[g]=this.arrayListContactosEliminados.get(g);
            //System.out.println("\nEliminado: "+this.listadoClientesEliminados[g]);
        }
        
         //System.out.println("Contactos Eliminados:"+this.arrayListContactosEliminados.toString());
        //this.contactos.add(cliente.getApellidoNombre());
    }
        public void actualizarOrdenesEliminadas(){
        listadoOrdenesEliminadas=new String[1000];
        this.arrayListOrdenesEliminadas=new  ArrayList<>();
        for (Map.Entry<String, Orden> entry : hashmapOrdenesEliminadas.entrySet()) {
            this.arrayListOrdenesEliminadas.add(entry.getValue().getNumeroOrden());
        }
        Collections.sort(Interfaz.this.arrayListOrdenesEliminadas, String::compareTo);
        for (int g=0;g<this.arrayListOrdenesEliminadas.size();g++) {
            listadoOrdenesEliminadas[g]=this.arrayListOrdenesEliminadas.get(g);
            //System.out.println("\nEliminado: "+this.listadoClientesEliminados[g]);
        }
         //System.out.println("Contactos Eliminados:"+this.arrayListContactosEliminados.toString());
        //this.contactos.add(cliente.getApellidoNombre());
    }
    public void setearTablasListadoClientes(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablaLanchasEliminar.setListData(listadoClientes);
        this.tablaLanchasModificar.setListData(listadoClientes);
        this.tablaLanchasAgregar.setListData(listadoClientes);
        this.tablacontactosEliminados.setListData(listadoClientesEliminados);
        this.tablacontactosVisualizar.setListData(listadoClientes);
        this.tablaModificarInformacion.setListData(listadoClientes);
        this.tablaContactosEliminar.setListData(listadoClientes);
        
    }
    public void setearTablasListadoRepuestos(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablaRepuestosVisualizar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminados.setListData(listadoRepuestosEliminados);
        this.tablaRepuestosModificar.setListData(listadoRepuestos);
        
    }
    public void setearTablasListadoOrdenes(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablacontactosEliminados1.setListData(listadoOrdenesEliminadas);
        this.tablaOrdenesVisualizar.setListData(listadoOrdenes);
        this.tablacontactosEliminar1.setListData(listadoOrdenes);
        this.tablaOdenCrearAñadirRepuestos.setListData(listadoRepuestos);
        this.tablaOrdenesCrearVPRepuestos.setListData(listadoOrdenesAñadirRepuestosClienteCantidad);
        this.tablaOrdenesCrearManoObra.setListData(listadoManoObra);
        this.tablacontactosEliminados1.setListData(listadoOrdenesEliminadas);
         this.tablaOrdenesCrear.setListData(listadoClientes);
        
        
    }
    public void vaciarTablasO(){
        String [] vacio = new String[1000];
        this.tablaOrdenesVisualizar.setListData(vacio);
        this.tablacontactosEliminar1.setListData(vacio);
        this.tablaOdenCrearAñadirRepuestos.setListData(vacio);
        this.tablaOrdenesCrearVPRepuestos.setListData(vacio);
        this.tablacontactosEliminados1.setListData(vacio);
        this.tablaOrdenesCrear.setListData(vacio);
         this.tablaOrdenesCrear2.setListData(vacio);
        
    }
    public void vaciarTablasC(){
        String [] vacio = new String[1000];
        this.tablacontactosVisualizar.setListData(vacio);
        this.tablaModificarInformacion.setListData(vacio);
        this.tablaContactosEliminar.setListData(vacio);
        this.tablaEmbarcaciones.setListData(vacio);
        this.tablaLanchasAgregar.setListData(vacio);
    }
    public void vaciarTablasR(){
        String [] vacio = new String[1000];
        this.tablaRepuestosVisualizar.setListData(vacio);
        this.tablaRepuestosEliminar.setListData(vacio);
        this.tablaRepuestosEliminados.setListData(vacio);
        this.tablaRepuestosModificar.setListData(vacio);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        Main = new javax.swing.JTabbedPane();
        Clientes = new javax.swing.JPanel();
        editaraClientes = new javax.swing.JTabbedPane();
        visualizar = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablacontactosVisualizar = new javax.swing.JList<>();
        seleccionarV = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TablaContactosVsualizar = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        crear = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        bNombreC = new javax.swing.JTextField();
        bApellidoC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bCelularC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bTelFijoC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bCorreoC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        bCuidadorC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bCelCuidadorC = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        contactosCrear = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        bTelFijoC1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        bTelFijoC4 = new javax.swing.JTextField();
        eliminar = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaContactosEliminar = new javax.swing.JList<>();
        eliminarEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablacontactosEliminados = new javax.swing.JList<>();
        seleccionBorrado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        editar1 = new javax.swing.JPanel();
        tablaClienteLanchas = new javax.swing.JTabbedPane();
        info = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        bNombreE = new javax.swing.JTextField();
        bApellidoE = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        bCelularE = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        bTelFijE = new javax.swing.JTextField();
        bCorreoE = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        bCuidadorE = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        bCelCuidadorE = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        seleccionarModificarInfo = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaModificarInformacion = new javax.swing.JList<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        bTelFijoC5 = new javax.swing.JTextField();
        bTelFijoC6 = new javax.swing.JTextField();
        lanch = new javax.swing.JPanel();
        eliminarLanchaa = new javax.swing.JTabbedPane();
        agregarLancha = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        marca = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        modelo = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        motor = new javax.swing.JTextField();
        nSerie = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        guardarLancha = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        seleccionadoAgregar1 = new javax.swing.JLabel();
        seleccionarLanchaAgregar = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablaLanchasAgregar = new javax.swing.JList<>();
        estado = new javax.swing.JLabel();
        añadiendolanchaa = new javax.swing.JLabel();
        modificarLancha = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tablaLanchasModificar = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tablaEmbarcaciones = new javax.swing.JList<>();
        seleccionBorrado3 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        nSerie1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        m = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        t = new javax.swing.JTextField();
        mot = new javax.swing.JTextField();
        mo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        estado2 = new javax.swing.JLabel();
        modificarLancha1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tablaLanchasEliminar = new javax.swing.JList<>();
        seleccionBorrado4 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tablaEE = new javax.swing.JList<>();
        seleccionBorrado5 = new javax.swing.JButton();
        sel = new javax.swing.JLabel();
        seleccionBorrado6 = new javax.swing.JButton();
        estado1 = new javax.swing.JLabel();
        Ordenes = new javax.swing.JPanel();
        editaraClientes1 = new javax.swing.JTabbedPane();
        VisualizarOrden = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        visualizarV1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        seleccionarV2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        seleccionarV3 = new javax.swing.JButton();
        CrearOrden = new javax.swing.JPanel();
        dia = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        contactosCrear1 = new javax.swing.JButton();
        jScrollPane49 = new javax.swing.JScrollPane();
        tablaOrdenesCrearManoObra = new javax.swing.JList<>();
        jLabel148 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane50 = new javax.swing.JScrollPane();
        tablaOrdenesCrear = new javax.swing.JList<>();
        seleccionarModificarInfo5 = new javax.swing.JButton();
        jLabel149 = new javax.swing.JLabel();
        bFechaOrdenesCrear1 = new javax.swing.JTextField();
        seleccionarModificarInfo6 = new javax.swing.JButton();
        jScrollPane51 = new javax.swing.JScrollPane();
        tablaOdenCrearAñadirRepuestos = new javax.swing.JList<>();
        jLabel150 = new javax.swing.JLabel();
        seleccionarModificarInfo7 = new javax.swing.JButton();
        seleccionarModificarInfo8 = new javax.swing.JButton();
        jLabel151 = new javax.swing.JLabel();
        jScrollPane52 = new javax.swing.JScrollPane();
        tablaOrdenesCrearVPMO = new javax.swing.JList<>();
        seleccionarModificarInfo9 = new javax.swing.JButton();
        jScrollPane53 = new javax.swing.JScrollPane();
        tablaOrdenesCrearVPRepuestos = new javax.swing.JList<>();
        jLabel152 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ordenesCrearNota = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        pHTrabajo = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        tRepuestos = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordenesCrearVarios = new javax.swing.JTextPane();
        seleccionarModificarInfo13 = new javax.swing.JButton();
        tVarios = new javax.swing.JTextField();
        jScrollPane26 = new javax.swing.JScrollPane();
        visualizarV4 = new javax.swing.JTextArea();
        hTrabajo = new javax.swing.JTextField();
        jLabel163 = new javax.swing.JLabel();
        tManoObra = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        mes = new javax.swing.JTextField();
        año = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        contactosCrear4 = new javax.swing.JButton();
        calcularRepuestos = new javax.swing.JButton();
        contactosCrear5 = new javax.swing.JButton();
        tTotal = new javax.swing.JTextField();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        bFechaOrdenesCrear8 = new javax.swing.JTextField();
        calcularRepuestos1 = new javax.swing.JButton();
        seleccionarModificarInfo10 = new javax.swing.JButton();
        jScrollPane56 = new javax.swing.JScrollPane();
        tablaOrdenesCrear2 = new javax.swing.JList<>();
        jLabel36 = new javax.swing.JLabel();
        EliminarOrden = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablacontactosEliminar1 = new javax.swing.JList<>();
        eliminarEliminar1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tablacontactosEliminados1 = new javax.swing.JList<>();
        seleccionBorrado1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        Repuestos = new javax.swing.JPanel();
        editaraClientes2 = new javax.swing.JTabbedPane();
        visualizar2 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tablaRepuestosVisualizar = new javax.swing.JList<>();
        jLabel77 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        visualizarV2 = new javax.swing.JTextArea();
        jLabel78 = new javax.swing.JLabel();
        seleccionarV5 = new javax.swing.JButton();
        crear2 = new javax.swing.JPanel();
        bCelularC2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        bTelFijoC2 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        bCorreoC2 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        bCuidadorC2 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        contactosCrear2 = new javax.swing.JButton();
        eliminar2 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        tablaRepuestosEliminar = new javax.swing.JList<>();
        eliminarEliminar2 = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tablaRepuestosEliminados = new javax.swing.JList<>();
        seleccionBorrado2 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        modificarLancha8 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jScrollPane54 = new javax.swing.JScrollPane();
        tablaRepuestosModificar = new javax.swing.JList<>();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        bRepuestosModCodigo = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        bRepuestosModNombre = new javax.swing.JTextField();
        bRepuestosModCantidad = new javax.swing.JTextField();
        bRepuestosModPrecio = new javax.swing.JTextField();
        estado12 = new javax.swing.JLabel();
        Guarderias = new javax.swing.JPanel();
        editaraClientes3 = new javax.swing.JTabbedPane();
        visualizar3 = new javax.swing.JPanel();
        jScrollPane38 = new javax.swing.JScrollPane();
        tablaOrdenesV2 = new javax.swing.JList<>();
        jLabel112 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jButton46 = new javax.swing.JButton();
        jScrollPane39 = new javax.swing.JScrollPane();
        visualizarV3 = new javax.swing.JTextArea();
        jLabel113 = new javax.swing.JLabel();
        seleccionarV6 = new javax.swing.JButton();
        crear3 = new javax.swing.JPanel();
        bCelularC3 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        bTelFijoC3 = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        bCorreoC3 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        bCuidadorC3 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        bCelCuidadorC3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        contactosCrear3 = new javax.swing.JButton();
        jScrollPane40 = new javax.swing.JScrollPane();
        tablacontactosEliminar5 = new javax.swing.JList<>();
        eliminar3 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jButton47 = new javax.swing.JButton();
        jScrollPane41 = new javax.swing.JScrollPane();
        tablacontactosEliminar6 = new javax.swing.JList<>();
        eliminarEliminar3 = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        jScrollPane42 = new javax.swing.JScrollPane();
        tablacontactosEliminados3 = new javax.swing.JList<>();
        seleccionBorrado15 = new javax.swing.JButton();
        jLabel121 = new javax.swing.JLabel();
        editar4 = new javax.swing.JPanel();
        tablaClienteLanchas3 = new javax.swing.JTabbedPane();
        info3 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        bNombreE3 = new javax.swing.JTextField();
        bApellidoE3 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        bCelularE3 = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        bTelFijE3 = new javax.swing.JTextField();
        bCorreoE3 = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        bCuidadorE3 = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        bCelCuidadorE3 = new javax.swing.JTextField();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        seleccionarModificarInfo3 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jButton50 = new javax.swing.JButton();
        jScrollPane43 = new javax.swing.JScrollPane();
        tablaModificarInformacion3 = new javax.swing.JList<>();
        lanch3 = new javax.swing.JPanel();
        eliminarLanchaa3 = new javax.swing.JTabbedPane();
        agregarLancha3 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        tipo3 = new javax.swing.JTextField();
        marca3 = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        modelo3 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        motor3 = new javax.swing.JTextField();
        nSerie6 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        guardarLancha3 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jLabel136 = new javax.swing.JLabel();
        seleccionadoAgregar4 = new javax.swing.JLabel();
        seleccionarLanchaAgregar3 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        jButton52 = new javax.swing.JButton();
        jScrollPane44 = new javax.swing.JScrollPane();
        tablaLanchasAgregar3 = new javax.swing.JList<>();
        estado9 = new javax.swing.JLabel();
        añadiendolanchaa3 = new javax.swing.JLabel();
        modificarLancha6 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jScrollPane45 = new javax.swing.JScrollPane();
        tablaLanchasModificar3 = new javax.swing.JList<>();
        jLabel138 = new javax.swing.JLabel();
        jScrollPane46 = new javax.swing.JScrollPane();
        tablaEmbarcaciones3 = new javax.swing.JList<>();
        seleccionBorrado16 = new javax.swing.JButton();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        nSerie7 = new javax.swing.JTextField();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jLabel142 = new javax.swing.JLabel();
        m3 = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        mot3 = new javax.swing.JTextField();
        mo3 = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        estado10 = new javax.swing.JLabel();
        modificarLancha7 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jScrollPane47 = new javax.swing.JScrollPane();
        tablaLanchasEliminar3 = new javax.swing.JList<>();
        seleccionBorrado17 = new javax.swing.JButton();
        jLabel146 = new javax.swing.JLabel();
        jScrollPane48 = new javax.swing.JScrollPane();
        tablaEE3 = new javax.swing.JList<>();
        seleccionBorrado18 = new javax.swing.JButton();
        sel3 = new javax.swing.JLabel();
        seleccionBorrado19 = new javax.swing.JButton();
        estado11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servinautica v0.1");
        setBackground(java.awt.SystemColor.controlDkShadow);

        Fondo.setBackground(new java.awt.Color(187, 187, 201));
        Fondo.setPreferredSize(new java.awt.Dimension(1300, 720));
        Fondo.setVerifyInputWhenFocusTarget(false);

        tablacontactosVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(tablacontactosVisualizar);

        seleccionarV.setText("Ver");
        seleccionarV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarVMouseClicked(evt);
            }
        });
        seleccionarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarVActionPerformed(evt);
            }
        });

        jLabel16.setText("Buscar:");

        jTextField12.setText(" ");

        jButton13.setText("Buscar");

        TablaContactosVsualizar.setEditable(false);
        TablaContactosVsualizar.setColumns(20);
        TablaContactosVsualizar.setRows(1000);
        TablaContactosVsualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane9.setViewportView(TablaContactosVsualizar);

        jLabel12.setText("Seleccionado:");

        javax.swing.GroupLayout visualizarLayout = new javax.swing.GroupLayout(visualizar);
        visualizar.setLayout(visualizarLayout);
        visualizarLayout.setHorizontalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(726, 726, Short.MAX_VALUE))
        );
        visualizarLayout.setVerticalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13))
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarV)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visualizarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        editaraClientes.addTab("Visualizar", visualizar);

        jLabel4.setText("Nombre:");

        bNombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreCActionPerformed(evt);
            }
        });

        bApellidoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoCActionPerformed(evt);
            }
        });

        jLabel5.setText("Apellido:");

        jLabel6.setText("Celular: ");

        bTelFijoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoCActionPerformed(evt);
            }
        });

        jLabel7.setText("Tel. Fijo:");

        bCorreoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoCActionPerformed(evt);
            }
        });

        jLabel8.setText("Correo: ");

        jLabel9.setText("Cuidador:");

        jLabel10.setText("Cel. Cuidador :");

        bCelCuidadorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCelCuidadorCActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        contactosCrear.setText("Crear");
        contactosCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrearActionPerformed(evt);
            }
        });

        jLabel24.setText("Tel. Fijo Oficina 1:");

        bTelFijoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC1ActionPerformed(evt);
            }
        });

        jLabel29.setText("Tel. Fijo Oficina 2:");

        bTelFijoC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crearLayout = new javax.swing.GroupLayout(crear);
        crear.setLayout(crearLayout);
        crearLayout.setHorizontalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contactosCrear))
                    .addGroup(crearLayout.createSequentialGroup()
                        .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bTelFijoC4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bTelFijoC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bCelCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bCorreoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bTelFijoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bCelularC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel24))
                .addContainerGap(985, Short.MAX_VALUE))
        );
        crearLayout.setVerticalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(bNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(bCelularC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(5, 5, 5)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCorreoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCelCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(contactosCrear))
                .addContainerGap(369, Short.MAX_VALUE))
        );

        editaraClientes.addTab("Crear", crear);

        jLabel14.setText("Buscar:");

        jTextField10.setText(" ");

        jButton7.setText("Buscar");

        tablaContactosEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(tablaContactosEliminar);

        eliminarEliminar.setText("Eliminar");
        eliminarEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEliminarMouseClicked(evt);
            }
        });
        eliminarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEliminarActionPerformed(evt);
            }
        });

        jLabel2.setText("Existentes");

        tablacontactosEliminados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(tablacontactosEliminados);

        seleccionBorrado.setText("Recuperar");
        seleccionBorrado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorradoMouseClicked(evt);
            }
        });
        seleccionBorrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorradoActionPerformed(evt);
            }
        });

        jLabel3.setText("Eliminados");

        javax.swing.GroupLayout eliminarLayout = new javax.swing.GroupLayout(eliminar);
        eliminar.setLayout(eliminarLayout);
        eliminarLayout.setHorizontalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eliminarLayout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))))
                .addContainerGap(694, Short.MAX_VALUE))
        );
        eliminarLayout.setVerticalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(12, 12, 12)
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado)
                            .addComponent(eliminarEliminar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(102, Short.MAX_VALUE))))
        );

        editaraClientes.addTab("Eliminar", eliminar);

        jLabel42.setText("Nombre:");

        bNombreE.setText(" ");
        bNombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreEActionPerformed(evt);
            }
        });

        bApellidoE.setText(" ");
        bApellidoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoEActionPerformed(evt);
            }
        });

        jLabel43.setText("Apellido:");

        bCelularE.setText(" ");

        jLabel44.setText("Celular: ");

        bTelFijE.setText(" ");
        bTelFijE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijEActionPerformed(evt);
            }
        });

        bCorreoE.setText(" ");
        bCorreoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoEActionPerformed(evt);
            }
        });

        jLabel45.setText("Tel. Fijo:");

        jLabel46.setText("Correo: ");

        bCuidadorE.setText(" ");

        jLabel47.setText("Cuidador:");

        jLabel48.setText("Cel. Cuidador :");

        bCelCuidadorE.setText(" ");

        jButton9.setText("Guardar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton15.setText("Cancelar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel25.setText("Buscar:");

        jLabel13.setText("Seleccionado:");

        seleccionarModificarInfo.setText("Seleccionar");
        seleccionarModificarInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfoMouseClicked(evt);
            }
        });
        seleccionarModificarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfoActionPerformed(evt);
            }
        });

        jTextField14.setText(" ");

        jButton23.setText("Buscar");

        tablaModificarInformacion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(tablaModificarInformacion);

        jLabel30.setText("Tel. Fijo Oficina 1:");

        jLabel35.setText("Tel. Fijo Oficina 2:");

        bTelFijoC5.setText(" ");
        bTelFijoC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC5ActionPerformed(evt);
            }
        });

        bTelFijoC6.setText(" ");
        bTelFijoC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 36, Short.MAX_VALUE)
                                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bCorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bCelularE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bApellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(bTelFijoC5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bTelFijoC6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bCelCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bTelFijE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(infoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)))
                        .addContainerGap(619, Short.MAX_VALUE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23))
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(bNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bApellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(8, 8, 8)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelularE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(jButton9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        tablaClienteLanchas.addTab("Datos", info);

        jLabel56.setText("Tipo:");

        tipo.setText(" ");
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        marca.setText(" ");
        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });

        jLabel57.setText("Marca:");

        modelo.setText(" ");

        jLabel58.setText("Modelo:");

        motor.setText(" ");
        motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorActionPerformed(evt);
            }
        });

        nSerie.setText(" ");
        nSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerieActionPerformed(evt);
            }
        });

        jLabel59.setText("Motor:");

        jLabel60.setText("N° Serie:");

        guardarLancha.setText("Guardar");
        guardarLancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLanchaActionPerformed(evt);
            }
        });

        jButton17.setText("Cancelar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel26.setText("Buscar:");

        seleccionadoAgregar1.setText("Seleccionado: ");

        seleccionarLanchaAgregar.setText("Seleccionar");
        seleccionarLanchaAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarLanchaAgregarMouseClicked(evt);
            }
        });
        seleccionarLanchaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarLanchaAgregarActionPerformed(evt);
            }
        });

        jTextField15.setText(" ");

        jButton25.setText("Buscar");

        tablaLanchasAgregar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane14.setViewportView(tablaLanchasAgregar);

        estado.setText("Estado: Seleccionando Cliente");

        añadiendolanchaa.setText("Añadiendo Lancha a:  ");

        javax.swing.GroupLayout agregarLanchaLayout = new javax.swing.GroupLayout(agregarLancha);
        agregarLancha.setLayout(agregarLanchaLayout);
        agregarLanchaLayout.setHorizontalGroup(
            agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(seleccionadoAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel56))
                                .addGap(40, 40, 40)
                                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(motor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardarLancha))
                            .addComponent(estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(añadiendolanchaa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(660, Short.MAX_VALUE))
        );
        agregarLanchaLayout.setVerticalGroup(
            agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25))
                .addGap(12, 12, 12)
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionadoAgregar1)
                    .addComponent(añadiendolanchaa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57)))
                            .addComponent(seleccionarLanchaAgregar))
                        .addGap(10, 10, 10)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton17)
                            .addComponent(guardarLancha))
                        .addGap(18, 18, 18)
                        .addComponent(estado)
                        .addGap(0, 324, Short.MAX_VALUE))
                    .addComponent(jScrollPane14))
                .addContainerGap())
        );

        eliminarLanchaa.addTab("Agrega", agregarLancha);

        jLabel27.setText("Buscar:");

        jTextField16.setText(" ");

        jButton26.setText("Buscar");

        jButton27.setText("Seleccionar");
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        tablaLanchasModificar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane15.setViewportView(tablaLanchasModificar);

        jLabel17.setText("Seleccionado: ");

        tablaEmbarcaciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane16.setViewportView(tablaEmbarcaciones);

        seleccionBorrado3.setText("Seleccionar");
        seleccionBorrado3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado3MouseClicked(evt);
            }
        });
        seleccionBorrado3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado3ActionPerformed(evt);
            }
        });

        jLabel63.setText("Motor: ");

        jLabel64.setText("Modelo:");

        jLabel66.setText("Tipo: ");

        nSerie1.setText(" ");
        nSerie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie1ActionPerformed(evt);
            }
        });

        jButton12.setText("Guardar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton18.setText("Cancelar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel67.setText("N° Serie: ");

        m.setText(" ");
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });

        jLabel68.setText("Marca: ");

        t.setText(" ");
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });

        mot.setText(" ");
        mot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motActionPerformed(evt);
            }
        });

        mo.setText(" ");

        jLabel22.setText("Embarcacion: ");

        estado2.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLanchaLayout = new javax.swing.GroupLayout(modificarLancha);
        modificarLancha.setLayout(modificarLanchaLayout);
        modificarLanchaLayout.setHorizontalGroup(
            modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(seleccionBorrado3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel67)
                                    .addComponent(jLabel66))
                                .addGap(33, 33, 33)
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mot, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(estado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton12)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modificarLanchaLayout.setVerticalGroup(
            modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26))
                .addGap(12, 12, 12)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton27)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel66)
                                            .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel68))
                                        .addGap(10, 10, 10)
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel64)
                                            .addComponent(mo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel63))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel67)))
                                    .addComponent(seleccionBorrado3))
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton12)
                                            .addComponent(jButton18)))
                                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(estado2)))))
                        .addGap(141, 356, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15))
                        .addContainerGap())))
        );

        eliminarLanchaa.addTab("Modificar", modificarLancha);

        jLabel28.setText("Buscar:");

        jTextField17.setText(" ");

        jButton28.setText("Buscar");

        jButton29.setText("Seleccionar");
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton29MouseClicked(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        tablaLanchasEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane17.setViewportView(tablaLanchasEliminar);

        seleccionBorrado4.setText("Cancelar");
        seleccionBorrado4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado4MouseClicked(evt);
            }
        });
        seleccionBorrado4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado4ActionPerformed(evt);
            }
        });

        jLabel21.setText("Embarcacion: ");

        tablaEE.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane19.setViewportView(tablaEE);

        seleccionBorrado5.setText("Seleccionar");
        seleccionBorrado5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado5MouseClicked(evt);
            }
        });
        seleccionBorrado5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado5ActionPerformed(evt);
            }
        });

        sel.setText("Seleccionado: ");

        seleccionBorrado6.setText("Eliminar");
        seleccionBorrado6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado6MouseClicked(evt);
            }
        });
        seleccionBorrado6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado6ActionPerformed(evt);
            }
        });

        estado1.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha1Layout = new javax.swing.GroupLayout(modificarLancha1);
        modificarLancha1.setLayout(modificarLancha1Layout);
        modificarLancha1Layout.setHorizontalGroup(
            modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha1Layout.createSequentialGroup()
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))))
                .addGap(186, 186, 186))
        );
        modificarLancha1Layout.setVerticalGroup(
            modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(sel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton29)
                            .addGroup(modificarLancha1Layout.createSequentialGroup()
                                .addComponent(seleccionBorrado5)
                                .addGap(14, 14, 14)
                                .addComponent(seleccionBorrado4)))
                        .addGap(18, 18, 18)
                        .addComponent(seleccionBorrado6)
                        .addGap(54, 54, 54)
                        .addComponent(estado1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane17))
                .addContainerGap())
        );

        eliminarLanchaa.addTab("Eliminar", modificarLancha1);

        javax.swing.GroupLayout lanchLayout = new javax.swing.GroupLayout(lanch);
        lanch.setLayout(lanchLayout);
        lanchLayout.setHorizontalGroup(
            lanchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa)
        );
        lanchLayout.setVerticalGroup(
            lanchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tablaClienteLanchas.addTab("Lancha", lanch);

        javax.swing.GroupLayout editar1Layout = new javax.swing.GroupLayout(editar1);
        editar1.setLayout(editar1Layout);
        editar1Layout.setHorizontalGroup(
            editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1422, Short.MAX_VALUE)
            .addGroup(editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editar1Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas)
                    .addContainerGap()))
        );
        editar1Layout.setVerticalGroup(
            editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaClienteLanchas))
        );

        editaraClientes.addTab("Modificar", editar1);

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes)
        );

        Main.addTab("CLIENTES", Clientes);

        tablaOrdenesVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(tablaOrdenesVisualizar);

        jLabel18.setText("Buscar:");

        jTextField13.setText(" ");

        jButton14.setText("Buscar");

        visualizarV1.setEditable(false);
        visualizarV1.setColumns(20);
        visualizarV1.setRows(1000);
        visualizarV1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane11.setViewportView(visualizarV1);

        jLabel15.setText("Seleccionado:");

        seleccionarV2.setText("Aceptar");
        seleccionarV2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV2MouseClicked(evt);
            }
        });
        seleccionarV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N° Orden", "Cliente" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        seleccionarV3.setText("Seleccionar");
        seleccionarV3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV3MouseClicked(evt);
            }
        });
        seleccionarV3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VisualizarOrdenLayout = new javax.swing.GroupLayout(VisualizarOrden);
        VisualizarOrden.setLayout(VisualizarOrdenLayout);
        VisualizarOrdenLayout.setHorizontalGroup(
            VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seleccionarV2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seleccionarV3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(842, 1058, Short.MAX_VALUE))
        );
        VisualizarOrdenLayout.setVerticalGroup(
            VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(seleccionarV2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarV3))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VisualizarOrdenLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        editaraClientes1.addTab("Visualizar", VisualizarOrden);

        dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaActionPerformed(evt);
            }
        });

        jLabel20.setText("Fecha:");

        contactosCrear1.setText("Crear");
        contactosCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear1ActionPerformed(evt);
            }
        });

        tablaOrdenesCrearManoObra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane49.setViewportView(tablaOrdenesCrearManoObra);

        jLabel148.setText("Seleccionado:");

        jLabel31.setText("Mano de Obra");

        tablaOrdenesCrear.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane50.setViewportView(tablaOrdenesCrear);

        seleccionarModificarInfo5.setText("Agregar");
        seleccionarModificarInfo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo5MouseClicked(evt);
            }
        });
        seleccionarModificarInfo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo5ActionPerformed(evt);
            }
        });

        jLabel149.setText("Añadir Mano de obra al listado");

        bFechaOrdenesCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo6.setText("Añadir->");
        seleccionarModificarInfo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo6MouseClicked(evt);
            }
        });
        seleccionarModificarInfo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo6ActionPerformed(evt);
            }
        });

        tablaOdenCrearAñadirRepuestos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane51.setViewportView(tablaOdenCrearAñadirRepuestos);

        jLabel150.setText("Repuestos");

        seleccionarModificarInfo7.setText("Seleccionar");
        seleccionarModificarInfo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo7MouseClicked(evt);
            }
        });
        seleccionarModificarInfo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo7ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo8.setText("Añadir->");
        seleccionarModificarInfo8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo8MouseClicked(evt);
            }
        });
        seleccionarModificarInfo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo8ActionPerformed(evt);
            }
        });

        jLabel151.setText("VISTA PREVIA");

        tablaOrdenesCrearVPMO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane52.setViewportView(tablaOrdenesCrearVPMO);

        seleccionarModificarInfo9.setText("<- Quitar");
        seleccionarModificarInfo9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo9MouseClicked(evt);
            }
        });
        seleccionarModificarInfo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo9ActionPerformed(evt);
            }
        });

        tablaOrdenesCrearVPRepuestos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane53.setViewportView(tablaOrdenesCrearVPRepuestos);

        jLabel152.setText("Mano de obra agegada");

        jLabel154.setText("Agregar Nota");

        ordenesCrearNota.setToolTipText("");
        ordenesCrearNota.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane2.setViewportView(ordenesCrearNota);

        jLabel1.setText("Repuestos agregados ");

        pHTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pHTrabajoActionPerformed(evt);
            }
        });

        jLabel155.setText("Precio H.Trabajo");

        jLabel156.setText("Total Repuestos");

        tRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRepuestosActionPerformed(evt);
            }
        });

        jLabel157.setText("Total Varios");

        jLabel158.setText("Agregar Varios");

        ordenesCrearVarios.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane3.setViewportView(ordenesCrearVarios);

        seleccionarModificarInfo13.setText("<-Quitar");
        seleccionarModificarInfo13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo13MouseClicked(evt);
            }
        });
        seleccionarModificarInfo13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo13ActionPerformed(evt);
            }
        });

        tVarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tVariosActionPerformed(evt);
            }
        });

        visualizarV4.setEditable(false);
        visualizarV4.setColumns(20);
        visualizarV4.setRows(1000);
        visualizarV4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane26.setViewportView(visualizarV4);

        jLabel163.setText("Horas de trabajo");

        jLabel165.setText("Total Mano Obra");

        jLabel11.setText("     Cantidad");

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesActionPerformed(evt);
            }
        });

        año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añoActionPerformed(evt);
            }
        });

        jLabel19.setText("/");

        jLabel23.setText("/");

        contactosCrear4.setText("Calcular");
        contactosCrear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear4ActionPerformed(evt);
            }
        });

        calcularRepuestos.setText("Calcular");
        calcularRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestosActionPerformed(evt);
            }
        });

        contactosCrear5.setText("Vista Previa");
        contactosCrear5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear5ActionPerformed(evt);
            }
        });

        tTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTotalActionPerformed(evt);
            }
        });

        jLabel166.setText("TOTAL: ");

        jLabel167.setText("N° Orden: ");

        bFechaOrdenesCrear8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear8ActionPerformed(evt);
            }
        });

        calcularRepuestos1.setText("Calcular");
        calcularRepuestos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestos1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo10.setText("Seleccionar");
        seleccionarModificarInfo10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo10MouseClicked(evt);
            }
        });
        seleccionarModificarInfo10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo10ActionPerformed(evt);
            }
        });

        tablaOrdenesCrear2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane56.setViewportView(tablaOrdenesCrear2);

        jLabel36.setText("Embarcacion: ");

        javax.swing.GroupLayout CrearOrdenLayout = new javax.swing.GroupLayout(CrearOrden);
        CrearOrden.setLayout(CrearOrdenLayout);
        CrearOrdenLayout.setHorizontalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(contactosCrear5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contactosCrear1))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(seleccionarModificarInfo7)
                                        .addGroup(CrearOrdenLayout.createSequentialGroup()
                                            .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(1, 1, 1)))
                                    .addComponent(seleccionarModificarInfo10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel31)
                                .addGroup(CrearOrdenLayout.createSequentialGroup()
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(CrearOrdenLayout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel154))
                                        .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane51, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2))
                                    .addGap(13, 13, 13)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cantidad)
                                        .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                    .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGap(12, 12, 12)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(CrearOrdenLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addComponent(jLabel158)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel152)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tManoObra)
                                    .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pHTrabajo)
                                    .addComponent(jLabel163, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hTrabajo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(contactosCrear4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel165, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tRepuestos)
                                    .addComponent(calcularRepuestos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel157, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)))
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel151)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel166)
                                    .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(calcularRepuestos1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(484, 484, 484))
        );
        CrearOrdenLayout.setVerticalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel152)
                                .addComponent(jLabel151))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(jLabel148)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel19)
                                                        .addComponent(jLabel23))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(calcularRepuestos1)
                                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jLabel155))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(pHTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel163)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(hTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(contactosCrear4)
                                                    .addGap(28, 28, 28)
                                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel149)
                                                        .addComponent(jLabel165)))
                                                .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(62, 62, 62)
                                                .addComponent(seleccionarModificarInfo13)
                                                .addGap(43, 43, 43)))
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(jLabel156)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(calcularRepuestos))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel158)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tVarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(102, 102, 102))
                                            .addComponent(jScrollPane3)))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel154)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGap(170, 170, 170)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(seleccionarModificarInfo5)
                                            .addComponent(tManoObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel150))
                                    .addComponent(jScrollPane50))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                            .addComponent(seleccionarModificarInfo8)
                                            .addGap(98, 98, 98)))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(seleccionarModificarInfo7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarModificarInfo10))))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactosCrear1)
                    .addComponent(contactosCrear5))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        editaraClientes1.addTab("Crear", CrearOrden);

        jLabel32.setText("Buscar:");

        jTextField11.setText(" ");

        jButton8.setText("Buscar");

        tablacontactosEliminar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(tablacontactosEliminar1);

        eliminarEliminar1.setText("Eliminar");
        eliminarEliminar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEliminar1MouseClicked(evt);
            }
        });
        eliminarEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEliminar1ActionPerformed(evt);
            }
        });

        jLabel33.setText("Existentes");

        tablacontactosEliminados1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane18.setViewportView(tablacontactosEliminados1);

        seleccionBorrado1.setText("Recuperar");
        seleccionBorrado1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado1MouseClicked(evt);
            }
        });
        seleccionBorrado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado1ActionPerformed(evt);
            }
        });

        jLabel34.setText("Eliminados");

        javax.swing.GroupLayout EliminarOrdenLayout = new javax.swing.GroupLayout(EliminarOrden);
        EliminarOrden.setLayout(EliminarOrdenLayout);
        EliminarOrdenLayout.setHorizontalGroup(
            EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EliminarOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EliminarOrdenLayout.createSequentialGroup()
                        .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionBorrado1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34))))
                .addContainerGap(1023, Short.MAX_VALUE))
        );
        EliminarOrdenLayout.setVerticalGroup(
            EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(12, 12, 12)
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(seleccionBorrado1)
                    .addComponent(eliminarEliminar1)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jScrollPane18))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        editaraClientes1.addTab("Eliminar", EliminarOrden);

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1)
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        Main.addTab("ORDENES", Ordenes);

        tablaRepuestosVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane27.setViewportView(tablaRepuestosVisualizar);

        jLabel77.setText("Buscar:");

        jTextField22.setText(" ");

        jButton22.setText("Buscar");

        visualizarV2.setEditable(false);
        visualizarV2.setColumns(20);
        visualizarV2.setRows(1000);
        visualizarV2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane28.setViewportView(visualizarV2);

        jLabel78.setText("Seleccionado:");

        seleccionarV5.setText("Seleccionar");
        seleccionarV5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV5MouseClicked(evt);
            }
        });
        seleccionarV5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visualizar2Layout = new javax.swing.GroupLayout(visualizar2);
        visualizar2.setLayout(visualizar2Layout);
        visualizar2Layout.setHorizontalGroup(
            visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        visualizar2Layout.setVerticalGroup(
            visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22))
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarV5)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visualizar2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        editaraClientes2.addTab("Visualizar", visualizar2);

        bCelularC2.setText(" ");

        jLabel79.setText("Nombre:");

        bTelFijoC2.setText(" ");
        bTelFijoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC2ActionPerformed(evt);
            }
        });

        jLabel80.setText("Codigo:");

        bCorreoC2.setText(" ");
        bCorreoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC2ActionPerformed(evt);
            }
        });

        jLabel81.setText("Precio:");

        bCuidadorC2.setText(" ");

        jLabel82.setText("Cantidad:");

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        contactosCrear2.setText("Crear");
        contactosCrear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crear2Layout = new javax.swing.GroupLayout(crear2);
        crear2.setLayout(crear2Layout);
        crear2Layout.setHorizontalGroup(
            crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crear2Layout.createSequentialGroup()
                        .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80)
                            .addComponent(jLabel81)
                            .addComponent(jLabel82))
                        .addGap(31, 31, 31)
                        .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bCorreoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bTelFijoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCelularC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(crear2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contactosCrear2)))
                .addContainerGap(894, Short.MAX_VALUE))
        );
        crear2Layout.setVerticalGroup(
            crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(bCelularC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCorreoC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(contactosCrear2))
                .addContainerGap(522, Short.MAX_VALUE))
        );

        editaraClientes2.addTab("Crear", crear2);

        jLabel84.setText("Buscar:");

        jTextField23.setText(" ");

        jButton11.setText("Buscar");

        tablaRepuestosEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane30.setViewportView(tablaRepuestosEliminar);

        eliminarEliminar2.setText("Eliminar");
        eliminarEliminar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEliminar2MouseClicked(evt);
            }
        });
        eliminarEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEliminar2ActionPerformed(evt);
            }
        });

        jLabel85.setText("Existentes");

        tablaRepuestosEliminados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane31.setViewportView(tablaRepuestosEliminados);

        seleccionBorrado2.setText("Recuperar");
        seleccionBorrado2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado2MouseClicked(evt);
            }
        });
        seleccionBorrado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado2ActionPerformed(evt);
            }
        });

        jLabel86.setText("Eliminados");

        javax.swing.GroupLayout eliminar2Layout = new javax.swing.GroupLayout(eliminar2);
        eliminar2.setLayout(eliminar2Layout);
        eliminar2Layout.setHorizontalGroup(
            eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eliminar2Layout.createSequentialGroup()
                                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionBorrado2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel86))))
                .addContainerGap(562, Short.MAX_VALUE))
        );
        eliminar2Layout.setVerticalGroup(
            eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addGap(12, 12, 12)
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(seleccionBorrado2)
                    .addComponent(eliminarEliminar2)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jScrollPane31))
                .addGap(0, 102, Short.MAX_VALUE))
        );

        editaraClientes2.addTab("Eliminar", eliminar2);

        jLabel153.setText("Buscar:");

        jTextField34.setText(" ");

        jButton59.setText("Buscar");

        jButton60.setText("Seleccionar");
        jButton60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton60MouseClicked(evt);
            }
        });
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        tablaRepuestosModificar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane54.setViewportView(tablaRepuestosModificar);

        jLabel159.setText("Seleccionado: ");

        jLabel160.setText("Cantidad");

        jLabel161.setText("Precio:");

        jLabel162.setText("Nombre:");

        jButton61.setText("Guardar");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton62.setText("Cancelar");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        bRepuestosModCodigo.setText(" ");
        bRepuestosModCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModCodigoActionPerformed(evt);
            }
        });

        jLabel164.setText("Codigo:");

        bRepuestosModNombre.setText(" ");
        bRepuestosModNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModNombreActionPerformed(evt);
            }
        });

        bRepuestosModCantidad.setText(" ");
        bRepuestosModCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModCantidadActionPerformed(evt);
            }
        });

        bRepuestosModPrecio.setText(" ");

        estado12.setText("Estado: Seleccionando Repuesto");

        javax.swing.GroupLayout modificarLancha8Layout = new javax.swing.GroupLayout(modificarLancha8);
        modificarLancha8.setLayout(modificarLancha8Layout);
        modificarLancha8Layout.setHorizontalGroup(
            modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addComponent(jLabel153)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel164)
                                            .addComponent(jLabel161)
                                            .addComponent(jLabel160)
                                            .addComponent(jLabel162))
                                        .addGap(35, 35, 35)
                                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bRepuestosModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bRepuestosModCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bRepuestosModPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bRepuestosModCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 490, Short.MAX_VALUE))
                                    .addComponent(estado12, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addGap(331, 331, 331)
                                .addComponent(jButton62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton61)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        modificarLancha8Layout.setVerticalGroup(
            modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel153)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel159)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton60)
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel162)
                                    .addComponent(bRepuestosModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bRepuestosModCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel164))
                                .addGap(10, 10, 10)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel161)
                                    .addComponent(bRepuestosModPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bRepuestosModCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel160))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton62)
                                    .addComponent(jButton61))))
                        .addGap(18, 18, 18)
                        .addComponent(estado12))
                    .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        editaraClientes2.addTab("Modificar", modificarLancha8);

        javax.swing.GroupLayout RepuestosLayout = new javax.swing.GroupLayout(Repuestos);
        Repuestos.setLayout(RepuestosLayout);
        RepuestosLayout.setHorizontalGroup(
            RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        RepuestosLayout.setVerticalGroup(
            RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        Main.addTab("REPUESTOS", Repuestos);

        tablaOrdenesV2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane38.setViewportView(tablaOrdenesV2);

        jLabel112.setText("Buscar:");

        jTextField28.setText(" ");

        jButton46.setText("Buscar");

        visualizarV3.setEditable(false);
        visualizarV3.setColumns(20);
        visualizarV3.setRows(1000);
        visualizarV3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane39.setViewportView(visualizarV3);

        jLabel113.setText("Seleccionado:");

        seleccionarV6.setText("Seleccionar");
        seleccionarV6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV6MouseClicked(evt);
            }
        });
        seleccionarV6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visualizar3Layout = new javax.swing.GroupLayout(visualizar3);
        visualizar3.setLayout(visualizar3Layout);
        visualizar3Layout.setHorizontalGroup(
            visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar3Layout.createSequentialGroup()
                        .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar3Layout.createSequentialGroup()
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        visualizar3Layout.setVerticalGroup(
            visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton46))
                .addGroup(visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addGroup(visualizar3Layout.createSequentialGroup()
                                .addComponent(seleccionarV6)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(visualizar3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        editaraClientes3.addTab("Visualizar", visualizar3);

        bCelularC3.setText(" ");

        jLabel114.setText("Fecha:");

        bTelFijoC3.setText(" ");
        bTelFijoC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC3ActionPerformed(evt);
            }
        });

        jLabel115.setText("Tel. Fijo:");

        bCorreoC3.setText(" ");
        bCorreoC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC3ActionPerformed(evt);
            }
        });

        jLabel116.setText("Correo: ");

        bCuidadorC3.setText(" ");

        jLabel117.setText("Cuidador:");

        jLabel118.setText("Cel. Cuidador :");

        bCelCuidadorC3.setText(" ");

        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        contactosCrear3.setText("Crear");
        contactosCrear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear3ActionPerformed(evt);
            }
        });

        tablacontactosEliminar5.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane40.setViewportView(tablacontactosEliminar5);

        javax.swing.GroupLayout crear3Layout = new javax.swing.GroupLayout(crear3);
        crear3.setLayout(crear3Layout);
        crear3Layout.setHorizontalGroup(
            crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear3Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jButton6)
                .addGap(12, 12, 12)
                .addComponent(contactosCrear3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(crear3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear3Layout.createSequentialGroup()
                        .addComponent(jLabel118)
                        .addGap(4, 4, 4)
                        .addComponent(bCelCuidadorC3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crear3Layout.createSequentialGroup()
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114)
                            .addComponent(jLabel115)
                            .addComponent(jLabel116)
                            .addComponent(jLabel117))
                        .addGap(31, 31, 31)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bCorreoC3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCuidadorC3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bTelFijoC3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCelularC3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 658, Short.MAX_VALUE))
        );
        crear3Layout.setVerticalGroup(
            crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(bCelularC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel115))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel117))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane40, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(contactosCrear3))
                .addContainerGap())
        );

        editaraClientes3.addTab("Crear", crear3);

        jLabel119.setText("Buscar:");

        jTextField29.setText(" ");

        jButton47.setText("Buscar");

        tablacontactosEliminar6.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane41.setViewportView(tablacontactosEliminar6);

        eliminarEliminar3.setText("Eliminar");
        eliminarEliminar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEliminar3MouseClicked(evt);
            }
        });
        eliminarEliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEliminar3ActionPerformed(evt);
            }
        });

        jLabel120.setText("Existentes");

        tablacontactosEliminados3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane42.setViewportView(tablacontactosEliminados3);

        seleccionBorrado15.setText("Recuperar");
        seleccionBorrado15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado15MouseClicked(evt);
            }
        });
        seleccionBorrado15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado15ActionPerformed(evt);
            }
        });

        jLabel121.setText("Eliminados");

        javax.swing.GroupLayout eliminar3Layout = new javax.swing.GroupLayout(eliminar3);
        eliminar3.setLayout(eliminar3Layout);
        eliminar3Layout.setHorizontalGroup(
            eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar3Layout.createSequentialGroup()
                        .addComponent(jLabel119)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eliminar3Layout.createSequentialGroup()
                        .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarEliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eliminar3Layout.createSequentialGroup()
                                .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionBorrado15, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel121))))
                .addContainerGap(562, Short.MAX_VALUE))
        );
        eliminar3Layout.setVerticalGroup(
            eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton47))
                .addGap(12, 12, 12)
                .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel120)
                    .addComponent(jLabel121))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane41, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jScrollPane42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eliminar3Layout.createSequentialGroup()
                        .addGroup(eliminar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado15)
                            .addComponent(eliminarEliminar3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        editaraClientes3.addTab("Eliminar", eliminar3);

        jLabel122.setText("Nombre:");

        bNombreE3.setText(" ");
        bNombreE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreE3ActionPerformed(evt);
            }
        });

        bApellidoE3.setText(" ");
        bApellidoE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoE3ActionPerformed(evt);
            }
        });

        jLabel123.setText("Apellido:");

        bCelularE3.setText(" ");

        jLabel124.setText("Celular: ");

        bTelFijE3.setText(" ");
        bTelFijE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijE3ActionPerformed(evt);
            }
        });

        bCorreoE3.setText(" ");
        bCorreoE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoE3ActionPerformed(evt);
            }
        });

        jLabel125.setText("Tel. Fijo:");

        jLabel126.setText("Correo: ");

        bCuidadorE3.setText(" ");

        jLabel127.setText("Cuidador:");

        jLabel128.setText("Cel. Cuidador :");

        bCelCuidadorE3.setText(" ");

        jButton48.setText("Guardar");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setText("Cancelar");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jLabel129.setText("Buscar:");

        jLabel130.setText("Seleccionado:");

        seleccionarModificarInfo3.setText("Seleccionar");
        seleccionarModificarInfo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo3MouseClicked(evt);
            }
        });
        seleccionarModificarInfo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo3ActionPerformed(evt);
            }
        });

        jTextField30.setText(" ");

        jButton50.setText("Buscar");

        tablaModificarInformacion3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane43.setViewportView(tablaModificarInformacion3);

        javax.swing.GroupLayout info3Layout = new javax.swing.GroupLayout(info3);
        info3.setLayout(info3Layout);
        info3Layout.setHorizontalGroup(
            info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info3Layout.createSequentialGroup()
                        .addComponent(jLabel129)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(info3Layout.createSequentialGroup()
                        .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarModificarInfo3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(info3Layout.createSequentialGroup()
                                .addComponent(jButton49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton48))
                            .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(info3Layout.createSequentialGroup()
                                    .addComponent(jLabel128)
                                    .addGap(4, 4, 4)
                                    .addComponent(bCelCuidadorE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(info3Layout.createSequentialGroup()
                                    .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel123)
                                        .addComponent(jLabel124)
                                        .addComponent(jLabel125)
                                        .addComponent(jLabel126)
                                        .addComponent(jLabel127)
                                        .addComponent(jLabel122))
                                    .addGap(31, 31, 31)
                                    .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bNombreE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCorreoE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCuidadorE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bTelFijE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCelularE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bApellidoE3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(543, Short.MAX_VALUE))
        );
        info3Layout.setVerticalGroup(
            info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton50))
                .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel122)
                            .addComponent(bNombreE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel123))
                        .addGap(10, 10, 10)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel124)
                            .addComponent(bCelularE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel126))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel127))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel128))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton48)
                            .addComponent(jButton49))
                        .addContainerGap(346, Short.MAX_VALUE))
                    .addGroup(info3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel130)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(info3Layout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo3)
                                .addContainerGap())))))
        );

        tablaClienteLanchas3.addTab("Informacion ", info3);

        jLabel131.setText("Tipo:");

        tipo3.setText(" ");
        tipo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo3ActionPerformed(evt);
            }
        });

        marca3.setText(" ");
        marca3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca3ActionPerformed(evt);
            }
        });

        jLabel132.setText("Marca:");

        modelo3.setText(" ");

        jLabel133.setText("Modelo:");

        motor3.setText(" ");
        motor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motor3ActionPerformed(evt);
            }
        });

        nSerie6.setText(" ");
        nSerie6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie6ActionPerformed(evt);
            }
        });

        jLabel134.setText("Motor:");

        jLabel135.setText("N° Serie:");

        guardarLancha3.setText("Guardar");
        guardarLancha3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLancha3ActionPerformed(evt);
            }
        });

        jButton51.setText("Cancelar");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jLabel136.setText("Buscar:");

        seleccionadoAgregar4.setText("Seleccionado: ");

        seleccionarLanchaAgregar3.setText("Seleccionar");
        seleccionarLanchaAgregar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarLanchaAgregar3MouseClicked(evt);
            }
        });
        seleccionarLanchaAgregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarLanchaAgregar3ActionPerformed(evt);
            }
        });

        jTextField31.setText(" ");

        jButton52.setText("Buscar");

        tablaLanchasAgregar3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane44.setViewportView(tablaLanchasAgregar3);

        estado9.setText("Estado: ");

        añadiendolanchaa3.setText("Añadiendo Lancha a:  ");

        javax.swing.GroupLayout agregarLancha3Layout = new javax.swing.GroupLayout(agregarLancha3);
        agregarLancha3.setLayout(agregarLancha3Layout);
        agregarLancha3Layout.setHorizontalGroup(
            agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLancha3Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLancha3Layout.createSequentialGroup()
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha3Layout.createSequentialGroup()
                                .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarLanchaAgregar3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(agregarLancha3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(seleccionadoAgregar4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agregarLancha3Layout.createSequentialGroup()
                                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel132)
                                    .addComponent(jLabel133)
                                    .addComponent(jLabel134)
                                    .addComponent(jLabel135)
                                    .addComponent(jLabel131))
                                .addGap(40, 40, 40)
                                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipo3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(motor3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelo3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(marca3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(agregarLancha3Layout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(jButton51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardarLancha3))
                            .addComponent(estado9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(añadiendolanchaa3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(528, Short.MAX_VALUE))
        );
        agregarLancha3Layout.setVerticalGroup(
            agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton52))
                .addGap(12, 12, 12)
                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionadoAgregar4)
                    .addComponent(añadiendolanchaa3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(agregarLancha3Layout.createSequentialGroup()
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha3Layout.createSequentialGroup()
                                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel131)
                                    .addComponent(tipo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(marca3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel132)))
                            .addComponent(seleccionarLanchaAgregar3))
                        .addGap(10, 10, 10)
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel133)
                            .addComponent(modelo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(motor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel134))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nSerie6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel135))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton51)
                            .addComponent(guardarLancha3))
                        .addGap(18, 18, 18)
                        .addComponent(estado9)
                        .addContainerGap(324, Short.MAX_VALUE))))
        );

        eliminarLanchaa3.addTab("Agrega", agregarLancha3);

        jLabel137.setText("Buscar:");

        jTextField32.setText(" ");

        jButton53.setText("Buscar");

        jButton54.setText("Seleccionar");
        jButton54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton54MouseClicked(evt);
            }
        });
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        tablaLanchasModificar3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane45.setViewportView(tablaLanchasModificar3);

        jLabel138.setText("Seleccionado: ");

        tablaEmbarcaciones3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane46.setViewportView(tablaEmbarcaciones3);

        seleccionBorrado16.setText("Seleccionar");
        seleccionBorrado16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado16MouseClicked(evt);
            }
        });
        seleccionBorrado16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado16ActionPerformed(evt);
            }
        });

        jLabel139.setText("Motor: ");

        jLabel140.setText("Modelo:");

        jLabel141.setText("Tipo: ");

        nSerie7.setText(" ");
        nSerie7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie7ActionPerformed(evt);
            }
        });

        jButton55.setText("Guardar");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jButton56.setText("Cancelar");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jLabel142.setText("N° Serie: ");

        m3.setText(" ");
        m3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m3ActionPerformed(evt);
            }
        });

        jLabel143.setText("Marca: ");

        t3.setText(" ");
        t3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t3ActionPerformed(evt);
            }
        });

        mot3.setText(" ");
        mot3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mot3ActionPerformed(evt);
            }
        });

        mo3.setText(" ");

        jLabel144.setText("Embarcacion: ");

        estado10.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha6Layout = new javax.swing.GroupLayout(modificarLancha6);
        modificarLancha6.setLayout(modificarLancha6Layout);
        modificarLancha6Layout.setHorizontalGroup(
            modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                        .addComponent(jLabel137)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel138)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel144, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha6Layout.createSequentialGroup()
                                .addComponent(seleccionBorrado16, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel143)
                                    .addComponent(jLabel140)
                                    .addComponent(jLabel139)
                                    .addComponent(jLabel142)
                                    .addComponent(jLabel141))
                                .addGap(33, 33, 33)
                                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mot3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mo3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modificarLancha6Layout.createSequentialGroup()
                                .addComponent(estado10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton55)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modificarLancha6Layout.setVerticalGroup(
            modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel137)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton53))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel144)
                    .addComponent(jLabel138))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane45)
                    .addComponent(jScrollPane46, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton54)
                            .addGroup(modificarLancha6Layout.createSequentialGroup()
                                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel141)
                                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel143))
                                        .addGap(10, 10, 10)
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel140)
                                            .addComponent(mo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel139))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nSerie7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel142)))
                                    .addComponent(seleccionBorrado16))
                                .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(modificarLancha6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton55)
                                            .addComponent(jButton56)))
                                    .addGroup(modificarLancha6Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(estado10)))))
                        .addGap(141, 344, Short.MAX_VALUE))))
        );

        eliminarLanchaa3.addTab("Modificar", modificarLancha6);

        jLabel145.setText("Buscar:");

        jTextField33.setText(" ");

        jButton57.setText("Buscar");

        jButton58.setText("Seleccionar");
        jButton58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton58MouseClicked(evt);
            }
        });
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        tablaLanchasEliminar3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane47.setViewportView(tablaLanchasEliminar3);

        seleccionBorrado17.setText("Cancelar");
        seleccionBorrado17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado17MouseClicked(evt);
            }
        });
        seleccionBorrado17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado17ActionPerformed(evt);
            }
        });

        jLabel146.setText("Embarcacion: ");

        tablaEE3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane48.setViewportView(tablaEE3);

        seleccionBorrado18.setText("Seleccionar");
        seleccionBorrado18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado18MouseClicked(evt);
            }
        });
        seleccionBorrado18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado18ActionPerformed(evt);
            }
        });

        sel3.setText("Seleccionado: ");

        seleccionBorrado19.setText("Eliminar");
        seleccionBorrado19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado19MouseClicked(evt);
            }
        });
        seleccionBorrado19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado19ActionPerformed(evt);
            }
        });

        estado11.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha7Layout = new javax.swing.GroupLayout(modificarLancha7);
        modificarLancha7.setLayout(modificarLancha7Layout);
        modificarLancha7Layout.setHorizontalGroup(
            modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha7Layout.createSequentialGroup()
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha7Layout.createSequentialGroup()
                        .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha7Layout.createSequentialGroup()
                                .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modificarLancha7Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(sel3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel146, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane48, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado18, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado11, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))))
                .addGap(186, 186, 186))
        );
        modificarLancha7Layout.setVerticalGroup(
            modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton57))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(sel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane47)
                    .addGroup(modificarLancha7Layout.createSequentialGroup()
                        .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane48, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addGroup(modificarLancha7Layout.createSequentialGroup()
                                .addGroup(modificarLancha7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton58)
                                    .addGroup(modificarLancha7Layout.createSequentialGroup()
                                        .addComponent(seleccionBorrado18)
                                        .addGap(14, 14, 14)
                                        .addComponent(seleccionBorrado17)))
                                .addGap(18, 18, 18)
                                .addComponent(seleccionBorrado19)
                                .addGap(54, 54, 54)
                                .addComponent(estado11)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        eliminarLanchaa3.addTab("Eliminar", modificarLancha7);

        javax.swing.GroupLayout lanch3Layout = new javax.swing.GroupLayout(lanch3);
        lanch3.setLayout(lanch3Layout);
        lanch3Layout.setHorizontalGroup(
            lanch3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa3)
        );
        lanch3Layout.setVerticalGroup(
            lanch3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tablaClienteLanchas3.addTab("Orden", lanch3);

        javax.swing.GroupLayout editar4Layout = new javax.swing.GroupLayout(editar4);
        editar4.setLayout(editar4Layout);
        editar4Layout.setHorizontalGroup(
            editar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
            .addGroup(editar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar4Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas3)
                    .addContainerGap()))
        );
        editar4Layout.setVerticalGroup(
            editar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(editar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar4Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas3)
                    .addContainerGap()))
        );

        editaraClientes3.addTab("Modificar", editar4);

        javax.swing.GroupLayout GuarderiasLayout = new javax.swing.GroupLayout(Guarderias);
        Guarderias.setLayout(GuarderiasLayout);
        GuarderiasLayout.setHorizontalGroup(
            GuarderiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        GuarderiasLayout.setVerticalGroup(
            GuarderiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes3)
        );

        Main.addTab("GUARDERIAS", Guarderias);

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCorreoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoCActionPerformed

    private void bTelFijoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoCActionPerformed

    private void bApellidoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoCActionPerformed

    private void bNombreCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreCActionPerformed

    private void seleccionarVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarVMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarVMouseClicked

    private void seleccionarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarVActionPerformed
        if(this.tablacontactosVisualizar.getSelectedValue()!=null){
            cliente=this.hashmapClientes.get(this.tablacontactosVisualizar.getSelectedValue());
            this.TablaContactosVsualizar.setText("----------------------------------------------------------------------\n                                   DATOS"
                    +cliente.getInformacionClienteVisualizar()+"\n----------------------------------------------------------------------"
                    +cliente.getInformacionEmbarcaciones()+"\n----------------------------------------------------------------------");
            this.jLabel12.setText("Seleccionado: "+cliente.getApellidoNombre());
    }//GEN-LAST:event_seleccionarVActionPerformed
    }
    public void vaciarBarrasC(){
        //barras Editar
        this.bNombreE.setText("");
        this.bApellidoE.setText("");
        this.bCorreoE.setText("");
        this.bCelularE.setText("");
        this.bTelFijE.setText("");
        this.bTelFijoC6.setText("");
        this.bTelFijoC5.setText("");
        this.bCuidadorE.setText("");
        this.bCelCuidadorE.setText("");
        
        this.t.setText("");
        this.m.setText("");
        this.nSerie1.setText("");
        this.mo.setText("");
        this.mot.setText("");
        
        //Barras Crear
        this.bNombreC.setText("");
        this.bApellidoC.setText("");
        this.bCorreoC.setText("");
        this.bCelularC.setText("");
        this.bTelFijoC1.setText("");
        this.bTelFijoC.setText("");
        this.bTelFijoC4.setText("");
        this.bCuidadorC.setText("");
        this.bCelCuidadorC.setText("");
        
        
        this.tipo.setText("");
        this.marca.setText("");
        this.modelo.setText("");
        this.motor.setText("");
        this.nSerie.setText("");
        
    }
    public void vaciarBarrasR(){
        //barras Editar
        this.bRepuestosModNombre.setText("");
        this.bRepuestosModCodigo.setText("");
        this.bRepuestosModCantidad.setText("");
        this.bRepuestosModPrecio.setText("");
        //Barras Crear
        this.bCorreoC2.setText("");
        this.bCelularC2.setText("");
        this.bTelFijoC2.setText("");
        this.bCuidadorC2.setText("");
        
        
    }
    public void vaciarBarrasO(){
        
        //Barras Crear
        this.dia.setText("");
        this.bFechaOrdenesCrear1.setText("");
        this.ordenesCrearNota.setText("");
        this.ordenesCrearVarios.setText("");
        this.bTelFijoC1.setText("");
        this.bTelFijoC4.setText("");
    }
    public void updateVRepuesto(){
        this.vaciarTablasR();
        this.vaciarBarrasR();
        this.actualizarListadoRepuestos();
        this.actualizarRepuestosEliminados();
        this.setearTablasListadoRepuestos();
        this.repaint();
    
    }
    public void updateVOrden(){
        this.vaciarTablasO();
        this.vaciarBarrasO();
        this.actualizarListadoOrdenes();
        this.actualizarOrdenesEliminadas();
        this.setearTablasListadoOrdenes();
        this.repaint();
    
    }
    public void updateVCliente(){
        this.vaciarTablasC();
        this.vaciarBarrasC();
        //this.actualizarListadoContactos();
        //this.actualizarListadoContactosEliminados();
        //this.setearTablasListadoClientes();
        this.repaint();
    
}
    private void contactosCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrearActionPerformed
       if(bNombreC.getText().length()!=0 && bApellidoC.getText().length()!=0 && (bCelularC.getText().length()!=0 || bCorreoC.getText().length()!=0)){ 
            cliente=new Cliente();
            cliente.setNombe(this.bNombreC.getText());
            cliente.setApellido(this.bApellidoC.getText());
            if(this.bCorreoC.getText().length()!=0){
            cliente.setCorreo(this.bCorreoC.getText());}
            if(this.bCelularC.getText().length()!=0){
            cliente.setCelular(this.bCelularC.getText());}
            if(this.bTelFijoC.getText().length()!=0){
            cliente.setTelFijo(this.bTelFijoC.getText());}
            if(this.bTelFijoC1.getText().length()!=0){
            cliente.setTelFijoOficina1(this.bTelFijoC1.getText());}
            if(this.bTelFijoC4.getText().length()!=0){
            cliente.setTelFijoOficina2(this.bTelFijoC4.getText());}
            if(this.bCuidadorC.getText().length()!=0){
            cliente.setCuidador(this.bCuidadorC.getText());}
            if(this.bCelCuidadorC.getText().length()!=0){
            cliente.setCelularCuidador(this.bCelCuidadorC.getText());}
            
            this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
            this.crearArchivosContacto(cliente.getApellidoNombre(),cliente);
            cliente= null;
            this.updateVCliente();
            this.listarClientes();
            this.setearTablasListadoClientes();
            repaint();
            
            
           
       }
    }//GEN-LAST:event_contactosCrearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.vaciarBarrasC();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void eliminarEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminarMouseClicked

    private void eliminarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminarActionPerformed
        
        if(this.tablaContactosEliminar.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablaContactosEliminar.getSelectedValue());
        }
        this.updateVCliente();
        
        if(cliente!=null){
            this.hashmapClientesBorrados.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientes.remove(cliente.getApellidoNombre(),cliente);
            this.updateVCliente();
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            //this.actualizarTXTInfoContacto();
            this.actualizarTXTContactosEliminados();
             this.updateVCliente();
              this.updateVOrden();
               this.updateVRepuesto();
            cliente=null;
        }
    }//GEN-LAST:event_eliminarEliminarActionPerformed

    private void seleccionBorradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorradoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorradoMouseClicked

    private void seleccionBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorradoActionPerformed
        if(this.tablacontactosEliminados.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientesBorrados.get(this.tablacontactosEliminados.getSelectedValue());
        }
        this.updateVCliente();
        if(cliente!=null){
            this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientesBorrados.remove(cliente.getApellidoNombre(),cliente);
            this.updateVCliente(); // TODO add your handling code here:
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            //this.actualizarTXTInfoContacto();
            this.actualizarTXTContactosEliminados();
        }
        cliente=null;
    }//GEN-LAST:event_seleccionBorradoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

       this.vaciarBarrasC();
       this.jLabel13.setText("Seleccionado: ");
       cliente=null;
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if(bNombreE.getText().length()!=0 && bApellidoE.getText().length()!=0 && bCorreoE.getText().length()!=0 && bCelularE.getText().length()!=0 
            && bTelFijE.getText().length()!=0 && bCuidadorE.getText().length()!=0 && bCelCuidadorE.getText().length()!=0 && cliente!=null){ 
            String apNombrAnterior=cliente.getApellidoNombre();
            cliente.setNombe(this.bNombreE.getText());
            cliente.setApellido(this.bApellidoE.getText());
            cliente.setCorreo(this.bCorreoE.getText());
            cliente.setCelular(this.bCelularE.getText());
            cliente.setTelFijo(this.bTelFijE.getText());
            cliente.setCuidador(this.bCuidadorE.getText());
            cliente.setCelularCuidador(this.bCelCuidadorE.getText());

            this.hashmapClientes.remove(apNombrAnterior);
            this.hashmapClientes.put(cliente.getApellidoNombre(), cliente);
            
            this.cargarArchivosCliente(apNombrAnterior);
            //this.actualizarTXTInfoContacto(info, cliente);
            
            this.updateVCliente();
            this.updateVOrden();
            this.updateVRepuesto();
            //this.actualizarTXTInfoContacto();
       }
       cliente=null;
       this.jLabel13.setText("Seleccionado: ");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void bTelFijEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijEActionPerformed

    private void bApellidoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoEActionPerformed

    private void bNombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreEActionPerformed

    private void seleccionarModificarInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfoMouseClicked

    private void seleccionarModificarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfoActionPerformed
      if(this.tablaModificarInformacion.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaModificarInformacion.getSelectedValue());
          this.jLabel13.setText("Seleccionado: "+cliente.getApellidoNombre());
      }    
      if(this.tablaModificarInformacion.getSelectedValue()!=null){
            this.bNombreE.setText(cliente.getNombre());
            this.bApellidoE.setText(cliente.getApellido());
            this.bCorreoE.setText(cliente.getCorreo());
            this.bCelularE.setText(cliente.getCelular());
            this.bTelFijE.setText(cliente.getTelFijo());
            this.bTelFijoC6.setText(cliente.getTelFijoOficina1());
            this.bTelFijoC5.setText(cliente.getTelFijoOficina2());
            this.bCuidadorE.setText(cliente.getCuidador());
            this.bCelCuidadorE.setText(cliente.getCelularCuidador());
        }
      
    }//GEN-LAST:event_seleccionarModificarInfoActionPerformed
    
    private void bCorreoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoEActionPerformed

    private void seleccionBorrado4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado4ActionPerformed
        if(this.embarcacion!=null){
            this.jLabel21.setText("Embarcacion: ");
         }
         embarcacion=null;
    }//GEN-LAST:event_seleccionBorrado4ActionPerformed

    private void seleccionBorrado4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado4MouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        if(this.tablaLanchasEliminar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasEliminar.getSelectedValue());
          this.sel.setText("Seleccionado: "+cliente.getApellidoNombre());
          this.estado1.setText("Estado: Seleccionando Embarcacion ");
      }    
      this.tablaEE.setListData(cliente.getListadoEmbarcaciones());
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29MouseClicked

    private void motActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motActionPerformed

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tActionPerformed

    private void mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         if(t.getText().length()!=0 && m.getText().length()!=0 && nSerie1.getText().length()!=0 && mo.getText().length()!=0 
            && mot.getText().length()!=0 && embarcacion!=null){ 
            cliente.eliminarEmbarcacion(embarcacion.getMotor());
            
            embarcacion.setTipo(t.getText());
            embarcacion.setMarca(m.getText());
            embarcacion.setModelo(mo.getText());
            embarcacion.setMotor(mot.getText());
            embarcacion.setnSerie(nSerie1.getText());
           
            cliente.addEmbarcacion(embarcacion.getMotor(), embarcacion);
            this.hashmapClientes.remove(cliente.getApellidoNombre());
            this.hashmapClientes.put(cliente.getApellidoNombre(), cliente);
            //this.actualizarTXTInfoContacto();
            this.updateVCliente();
            this.tablaEmbarcaciones.setListData(cliente.getListadoEmbarcaciones());
              this.estado2.setText("Estado: Edición realizada con exito");
         }
         embarcacion=null;
         //this.seleccionado5.setText("-----------------------------");
         this.jLabel22.setText("Embarcacion: ");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void nSerie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie1ActionPerformed

    private void seleccionBorrado3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado3ActionPerformed
        if(this.tablaEmbarcaciones.getSelectedValue()!=null){
            
            embarcacion=cliente.embarcaciones.get(this.tablaEmbarcaciones.getSelectedValue());
            this.jLabel22.setText("Embarcacion: "+embarcacion.getMotor());
            this.t.setText(embarcacion.getTipo());
            this.m.setText(embarcacion.getMarca());
            this.mot.setText(embarcacion.getMotor());
            this.mo.setText(embarcacion.getModelo());
            this.nSerie1.setText(embarcacion.getnSerie());
            this.estado2.setText("Estado: Editanco Embarcacion ");
        }  
        
    }//GEN-LAST:event_seleccionBorrado3ActionPerformed

    private void seleccionBorrado3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado3MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
       if(this.tablaLanchasModificar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasModificar.getSelectedValue());
          this.jLabel17.setText("Seleccionado: "+cliente.getApellidoNombre());
          this.estado2.setText("Estado: Seleccionado Embarcacion ");
      }    
      this.tablaEmbarcaciones.setListData(cliente.getListadoEmbarcaciones());
            
      
      
    }//GEN-LAST:event_jButton27ActionPerformed
    public void setearEmbarcaciones(Cliente cliente){
        
    }
    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27MouseClicked

    private void seleccionarLanchaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregarActionPerformed
        if(this.tablaLanchasAgregar.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablaLanchasAgregar.getSelectedValue());
            this.seleccionadoAgregar1.setText("Seleccionado: "+cliente.getApellidoNombre());
        }
        if(cliente!=null){
            this.añadiendolanchaa.setText("Añadiendo Lancha a: "+cliente.getApellidoNombre());
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablaLanchasAgregar.getSelectedValue());
            this.estado.setText("Estado: Editando");
        }
    }//GEN-LAST:event_seleccionarLanchaAgregarActionPerformed

    private void seleccionarLanchaAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregarMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.vaciarBarrasC();
        this.seleccionadoAgregar1.setText("Seleccionado: ");
        this.añadiendolanchaa.setText("Añadiendo Lancha a: ");
        this.estado.setText("Estado: ");
        cliente=null;
    }//GEN-LAST:event_jButton17ActionPerformed

    private void guardarLanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarLanchaActionPerformed
        if(tipo.getText().length()!=0 && marca.getText().length()!=0 && modelo.getText().length()!=0 && motor.getText().length()!=0 
            && nSerie.getText().length()!=0 && cliente!=null){     
            embarcacion=new Embarcacion();
            embarcacion.setTipo(this.tipo.getText());
            embarcacion.setMarca(this.marca.getText());
            embarcacion.setModelo(this.modelo.getText());
            embarcacion.setMotor(this.motor.getText());
            embarcacion.setnSerie(this.nSerie.getText());
            cliente.addEmbarcacion(embarcacion.getMotor(), embarcacion);
            this.estado.setText("Lancha: "+embarcacion.getMotor()+" añadida exitosamente");
            //this.actualizarTXTInfoContacto();
            this.updateVCliente();
            
        }   
        this.vaciarBarrasC();
        this.seleccionadoAgregar1.setText("  Seleccionado: ");
        this.añadiendolanchaa.setText("  Añadiendo Lancha a: ");
        cliente=null;

    }//GEN-LAST:event_guardarLanchaActionPerformed

    private void nSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerieActionPerformed

    private void motorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motorActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    private void seleccionBorrado5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado5MouseClicked

    private void seleccionBorrado5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado5ActionPerformed
        if(this.tablaEE.getSelectedValue()!=null){
            embarcacion=cliente.embarcaciones.get(this.tablaEE.getSelectedValue());
            this.jLabel21.setText("Embarcacion: "+embarcacion.getMotor());
        }  
    }//GEN-LAST:event_seleccionBorrado5ActionPerformed

    private void seleccionBorrado6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado6MouseClicked

    private void seleccionBorrado6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado6ActionPerformed
        if(this.embarcacion!=null){
            cliente.eliminarEmbarcacion(embarcacion.getMotor());
            this.hashmapClientes.remove(cliente.getApellidoNombre());
            this.hashmapClientes.put(cliente.getApellidoNombre(), cliente);
            //this.actualizarTXTInfoContacto();
            this.jLabel21.setText("Embarcacion: ");
            this.estado1.setText("Estado: Embarcacion Eliminada Exitosamente ");
         }
        if(this.tablaLanchasEliminar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasEliminar.getSelectedValue());
        }
        this.tablaEE.setListData(cliente.getListadoEmbarcaciones());
        
    }//GEN-LAST:event_seleccionBorrado6ActionPerformed

    private void eliminarEliminar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar1MouseClicked

    private void eliminarEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminar1ActionPerformed
        if(this.tablacontactosEliminar1.getSelectedValue()!=null){
            orden=new Orden();
            orden=this.hashmapOrdenes.get(this.tablacontactosEliminar1.getSelectedValue());
        }
       
        
        if(orden!=null){
            this.hashmapOrdenesEliminadas.put(orden.getNumeroOrden(),orden);
            this.hashmapOrdenes.remove(orden.getNumeroOrden(),orden);
            this.updateVOrden();
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTOrdenes();
            this.actualizarTXTOrdenesEliminadas();
            repuesto=null;
        }
        
        
        
        
        
    }//GEN-LAST:event_eliminarEliminar1ActionPerformed

    private void seleccionBorrado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado1MouseClicked

    private void seleccionBorrado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado1ActionPerformed
         if(this.tablacontactosEliminados1.getSelectedValue()!=null){
            orden=new Orden();
            orden=this.hashmapOrdenesEliminadas.get(this.tablacontactosEliminados1.getSelectedValue());
        }
       
        
        if(orden!=null){
            this.hashmapOrdenes.put(orden.getNumeroOrden(),orden);
            this.hashmapOrdenesEliminadas.remove(orden.getNumeroOrden(),orden);
            this.updateVOrden();
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTOrdenes();
            this.actualizarTXTOrdenesEliminadas();
            repuesto=null;
        }
    }//GEN-LAST:event_seleccionBorrado1ActionPerformed

    private void seleccionarV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV2MouseClicked

    private void seleccionarV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV2ActionPerformed
      
        
    }//GEN-LAST:event_seleccionarV2ActionPerformed

    private void seleccionarV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV3MouseClicked

    private void seleccionarV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV3ActionPerformed
        if(this.tablaOrdenesVisualizar.getSelectedValue()!=null){
            orden=new Orden();
            orden=this.hashmapOrdenes.get(this.tablaOrdenesVisualizar.getSelectedValue());
            this.visualizarV1.setText(orden.getInformacion());
            this.jLabel15.setText("Seleccionado: "+orden.getNumeroOrden());
        }
    }//GEN-LAST:event_seleccionarV3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void seleccionarV5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV5MouseClicked

    private void seleccionarV5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV5ActionPerformed
        if(this.tablaRepuestosVisualizar.getSelectedValue()!=null){
            repuesto=new Repuesto();
            repuesto=this.hashmapRepuestos.get(this.tablaRepuestosVisualizar.getSelectedValue());
            this.visualizarV2.setText("----------------------------------------------------------------------\n                                DETALLES\n"
                    +repuesto.getInformacionVisualizar()+"\n----------------------------------------------------------------------");
            this.jLabel78.setText("Seleccionado: "+repuesto.getNombre());
        }
    }//GEN-LAST:event_seleccionarV5ActionPerformed

    private void bTelFijoC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC2ActionPerformed

    private void bCorreoC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoC2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       this.vaciarBarrasR();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void contactosCrear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear2ActionPerformed
        if(bCelularC2.getText().length()!=0 && bTelFijoC2.getText().length()!=0 && bCorreoC2.getText().length()!=0 && 
           bCuidadorC2.getText().length()!=0){
            repuesto=new Repuesto();
            repuesto.setNombre(bCelularC2.getText());
            repuesto.setCodigo(bTelFijoC2.getText());
            repuesto.setPrecio(bCorreoC2.getText());
            repuesto.setCantidad(bCuidadorC2.getText());
            this.hashmapRepuestos.put(repuesto.getNombre(),repuesto);
            this.updateVRepuesto();
            this.actualizarTXTRepuestos();
            
            
       }
    }//GEN-LAST:event_contactosCrear2ActionPerformed

    private void eliminarEliminar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar2MouseClicked

    private void eliminarEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminar2ActionPerformed
                
        if(this.tablaRepuestosEliminar.getSelectedValue()!=null){
            repuesto=new Repuesto();
            repuesto=this.hashmapRepuestos.get(this.tablaRepuestosEliminar.getSelectedValue());
        }
        this.updateVRepuesto();
        
        if(repuesto!=null){
            this.hashmapRepuestosEliminados.put(repuesto.getNombre(),repuesto);
            this.hashmapRepuestos.remove(repuesto.getNombre(),repuesto);
            this.updateVRepuesto();
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTRepuestos();
            this.actualizarTXTRepuestosEliminados();
            repuesto=null;
        }
    }//GEN-LAST:event_eliminarEliminar2ActionPerformed

    private void seleccionBorrado2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado2MouseClicked

    private void seleccionBorrado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado2ActionPerformed
        if(this.tablaRepuestosEliminados.getSelectedValue()!=null){
            repuesto=new Repuesto();
            repuesto=this.hashmapRepuestosEliminados.get(this.tablaRepuestosEliminados.getSelectedValue());
        }
        this.updateVRepuesto();
        
        if(repuesto!=null){
            this.hashmapRepuestos.put(repuesto.getNombre(),repuesto);
            this.hashmapRepuestosEliminados.remove(repuesto.getNombre(),repuesto);
            this.updateVRepuesto();
            //System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            //System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTRepuestos();
            this.actualizarTXTRepuestosEliminados();
            repuesto=null;
        }
    }//GEN-LAST:event_seleccionBorrado2ActionPerformed

    private void seleccionarV6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV6MouseClicked

    private void seleccionarV6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV6ActionPerformed

    private void bTelFijoC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC3ActionPerformed

    private void bCorreoC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoC3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoC3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void contactosCrear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactosCrear3ActionPerformed

    private void eliminarEliminar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminar3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar3MouseClicked

    private void eliminarEliminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar3ActionPerformed

    private void seleccionBorrado15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado15MouseClicked

    private void seleccionBorrado15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado15ActionPerformed

    private void bNombreE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreE3ActionPerformed

    private void bApellidoE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoE3ActionPerformed

    private void bTelFijE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijE3ActionPerformed

    private void bCorreoE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoE3ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton49ActionPerformed

    private void seleccionarModificarInfo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo3MouseClicked

    private void seleccionarModificarInfo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo3ActionPerformed

    private void tipo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo3ActionPerformed

    private void marca3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marca3ActionPerformed

    private void motor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motor3ActionPerformed

    private void nSerie6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie6ActionPerformed

    private void guardarLancha3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarLancha3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarLancha3ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void seleccionarLanchaAgregar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar3MouseClicked

    private void seleccionarLanchaAgregar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar3ActionPerformed

    private void jButton54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton54MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton54MouseClicked

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton54ActionPerformed

    private void seleccionBorrado16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado16MouseClicked

    private void seleccionBorrado16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado16ActionPerformed

    private void nSerie7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie7ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void m3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m3ActionPerformed

    private void t3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t3ActionPerformed

    private void mot3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mot3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mot3ActionPerformed

    private void jButton58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton58MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton58MouseClicked

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton58ActionPerformed

    private void seleccionBorrado17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado17MouseClicked

    private void seleccionBorrado17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado17ActionPerformed

    private void seleccionBorrado18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado18MouseClicked

    private void seleccionBorrado18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado18ActionPerformed

    private void seleccionBorrado19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado19MouseClicked

    private void seleccionBorrado19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado19ActionPerformed

    private void seleccionarModificarInfo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo7ActionPerformed
        if(this.tablaOrdenesCrear.getSelectedValue()!=null){
            cliente = new Cliente();
            cliente=this.hashmapClientes.get(this.tablaOrdenesCrear.getSelectedValue());
            this.jLabel148.setText("Seleccionado: "+cliente.getApellidoNombre());
            this.selected=true;
            cliente.actualizarListadoEmbarcaciones();
            this.tablaOrdenesCrear2.setListData(cliente.getListadoDeembarcaciones());
        }

    }//GEN-LAST:event_seleccionarModificarInfo7ActionPerformed

    private void seleccionarModificarInfo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo7MouseClicked

    private void seleccionarModificarInfo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo6ActionPerformed
        if(this.tablaOrdenesCrearManoObra.getSelectedValue()!=null ){
            
           for(int d=0; d<this.tablaOrdenesCrearManoObra.getSelectedValuesList().size();d++){
               String mO=this.tablaOrdenesCrearManoObra.getSelectedValuesList().get(d);
               this.arrayListOrdenesAñadirManoObra.add(mO);
           } 
          this.actualizarOrdenManoObraAdd();          
      }
    }//GEN-LAST:event_seleccionarModificarInfo6ActionPerformed

    private void seleccionarModificarInfo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo6MouseClicked

    private void seleccionarModificarInfo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo5ActionPerformed
       if(!"".equals(this.bFechaOrdenesCrear1.getText())){
           this.arrayListManoObra.add(this.bFechaOrdenesCrear1.getText());
           this.actualizarTXTManoObra();
           this.actualizarListadoManoObra();
           this.tablaOrdenesCrearManoObra.setListData(this.listadoManoObra);
           this.bFechaOrdenesCrear1.setText("");
       }
    }//GEN-LAST:event_seleccionarModificarInfo5ActionPerformed

    private void seleccionarModificarInfo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo5MouseClicked

    private void contactosCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear1ActionPerformed
        if(this.selected==true && this.bFechaOrdenesCrear8.getText().length()!=0 && !this.arrayListOrdenesAñadirManoObra.isEmpty() && !this.arrayListOrdenesAñadirRepuestosCliente.isEmpty()
                && tRepuestos.getText().length()!=0 && tManoObra.getText().length()!=0 && this.tTotal.getText().length()!=0
                && this.dia.getText().length()!=0 && this.mes.getText().length()!=0 && this.año.getText().length()!=0  ){
            orden=new Orden();
            orden.setNumeroOrden(bFechaOrdenesCrear8.getText());
            orden.setTotalManoObra(this.tManoObra.getText());
            if(this.tVarios.getText().length()!=0 && this.ordenesCrearVarios.getText().length()!=0){
                orden.setTotalVarios(this.tVarios.getText());
            }
                else{
                    orden.setTotalVarios("0");
                    this.ordenesCrearVarios.setText("  Sin Varios");
                }
            orden.setTotalRepuestos(this.tRepuestos.getText());
            orden.setTotal(this.tTotal.getText() );
            orden.setCliente(cliente.getNombre()+" "+cliente.getApellido());
            orden.setEmbrcacion(embarcacion.getMotor());
            String fcha=this.dia.getText()+"/"+this.mes.getText()+"/"+this.año.getText();
            //fecha
            orden.setFecha(fcha);
            if(this.ordenesCrearNota.getText().length()!=0){
                orden.setNota(this.ordenesCrearNota.getText());
            }
                else{
                    orden.setNota("  Sin Nota");
                }
            if(this.ordenesCrearVarios.getText().length()!=0){
                orden.setVarios(this.ordenesCrearVarios.getText());
            }
            for(int x=0; x<this.arrayListOrdenesAñadirManoObra.size();x++){
                String mO=this.arrayListOrdenesAñadirManoObra.get(x);
                orden.addManoObra(mO);
            }
            for(int x=0; x<this.arrayListOrdenesAñadirRepuestosCliente.size();x++){
                repuesto=this.arrayListOrdenesAñadirRepuestosCliente.get(x);
                orden.addRepuesto(repuesto.getNombre(),repuesto.getCantidad());
            }
            this.visualizarV4.setText(orden.getInformacion());
            //System.out.println(orden.getInformacion());
            this.hashmapOrdenes.put(orden.getNumeroOrden(),orden);
            this.updateVOrden();
            this.actualizarTXTOrdenes();
            this.hashmapClientes.get(cliente.getApellidoNombre()).listadoOrdenes.add(orden.getNumeroOrden());
        }
        
    }//GEN-LAST:event_contactosCrear1ActionPerformed

    private void seleccionarModificarInfo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo8MouseClicked
    public void actualizarOrdenManoObraAdd(){
        this.listadoOrdenesAñadirManoObra= new String[1000];
        String [] vacio = new String[1000];
        this.tablaOrdenesCrearVPMO.setListData(vacio);
        Collections.sort(Interfaz.this.arrayListOrdenesAñadirManoObra, String::compareTo);
        for(int g=0;g<this.arrayListOrdenesAñadirManoObra.size();g++){
            this.listadoOrdenesAñadirManoObra[g]=this.arrayListOrdenesAñadirManoObra.get(g);
            //System.out.println("\n Actualizando MO: "+this.listadoOrdenesAñadirManoObra[g]);
        }
        this.tablaOrdenesCrearVPMO.setListData(this.listadoOrdenesAñadirManoObra);
    }
    public void actualizarOrdenRepuestosAgregados(){
        this.listadoOrdenesAñadirRepuestosCliente= new String[1000];
        this.listadoOrdenesAñadirRepuestosClienteCantidad= new String[1000];
        String [] vacio = new String[1000];
        this.tablaOrdenesCrearVPRepuestos.setListData(vacio);
        
        for(int g=0;g<this.arrayListOrdenesAñadirRepuestosCliente.size();g++){
            this.listadoOrdenesAñadirRepuestosCliente[g]=this.arrayListOrdenesAñadirRepuestosCliente.get(g).getNombre();
            this.listadoOrdenesAñadirRepuestosClienteCantidad[g]=this.arrayListOrdenesAñadirRepuestosCliente.get(g).getCantidad()+" "+this.arrayListOrdenesAñadirRepuestosCliente.get(g).getNombre();
        }
        this.tablaOrdenesCrearVPRepuestos.setListData(this.listadoOrdenesAñadirRepuestosClienteCantidad);
    }
    public boolean isNumericInteger(String cant) {

        boolean resultado;

        try {
            Integer.parseInt(cant);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    public boolean isNumericDouble(String cant) {

        boolean resultado;

        try {
            Double.parseDouble(cant);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    private void seleccionarModificarInfo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8ActionPerformed
       
        //Double cant=Double.parseDouble(this.cantidad.getText());
        
        if(this.tablaOdenCrearAñadirRepuestos.getSelectedValue()!=null && this.isNumericInteger(this.cantidad.getText())==true){
          this.repuesto=new Repuesto();
          this.repuesto=this.hashmapRepuestos.get(this.tablaOdenCrearAñadirRepuestos.getSelectedValue());
          repuesto.setCantidad(this.cantidad.getText());
          this.arrayListOrdenesAñadirRepuestosCliente.add(repuesto);
          this.actualizarOrdenRepuestosAgregados();
          this.cantidad.setText("");
      }
        
    }//GEN-LAST:event_seleccionarModificarInfo8ActionPerformed

    private void seleccionarModificarInfo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo9MouseClicked

    private void seleccionarModificarInfo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo9ActionPerformed
        ArrayList<String> rC=new ArrayList<>();
        if(this.tablaOrdenesCrearVPRepuestos.getSelectedValue()!=null ){   
            for(int g=0;g<this.tablaOrdenesCrearVPRepuestos.getSelectedValuesList().size();g++){
               String rp=this.tablaOrdenesCrearVPRepuestos.getSelectedValuesList().get(g);
               rC.add(rp);
               
            }
            for(int d=0; d<rC.size();d++){
                for(int g=0;g<this.arrayListOrdenesAñadirRepuestosCliente.size();g++){
                   if(this.listadoOrdenesAñadirRepuestosClienteCantidad[g].equals(rC.get(d))){
                           this.arrayListOrdenesAñadirRepuestosCliente.remove(g);
                           this.actualizarOrdenRepuestosAgregados();
                   }
                }
            }
        }
           this.actualizarOrdenRepuestosAgregados();
           this.tablaOrdenesCrearVPRepuestos.setListData(listadoOrdenesAñadirRepuestosClienteCantidad);
           //this.actualizarOrdenManoObraAdd();
           //this.arrayListOrdenesAñadirRepuestosCliente.remove(this.arrayListOrdenesAñadirRepuestosCliente.get(this.tablaOrdenesCrearVPRepuestos.getSelectedValue()));
    }//GEN-LAST:event_seleccionarModificarInfo9ActionPerformed

    private void seleccionarModificarInfo13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo13MouseClicked

    private void seleccionarModificarInfo13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo13ActionPerformed
        ArrayList<String> moc=new ArrayList<>();
        if(this.tablaOrdenesCrearVPMO.getSelectedValue()!=null){
        for(int d=0; d<this.tablaOrdenesCrearVPMO.getSelectedValuesList().size();d++){
               String mO=this.tablaOrdenesCrearVPMO.getSelectedValuesList().get(d);
               moc.add(mO);
           }
       for(int d=0; d<moc.size();d++){
            for(int g=0;g<this.arrayListOrdenesAñadirManoObra.size();g++){
               if(this.listadoOrdenesAñadirManoObra[g].equals(moc.get(d))){
                       this.arrayListOrdenesAñadirManoObra.remove(g);
                       this.actualizarOrdenManoObraAdd();
               }
           }
       }
           this.actualizarOrdenManoObraAdd();
           //this.arrayListOrdenesAñadirRepuestosCliente.remove(this.arrayListOrdenesAñadirRepuestosCliente.get(this.tablaOrdenesCrearVPRepuestos.getSelectedValue()));
       }
    }//GEN-LAST:event_seleccionarModificarInfo13ActionPerformed

    private void jButton60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton60MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton60MouseClicked

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        if(this.tablaRepuestosModificar.getSelectedValue()!=null){
            repuesto=new Repuesto();
            repuesto=this.hashmapRepuestos.get(this.tablaRepuestosModificar.getSelectedValue());
            this.bRepuestosModNombre.setText(repuesto.getNombre());
            this.bRepuestosModCodigo.setText(repuesto.getCodigo());
            this.bRepuestosModPrecio.setText(repuesto.getPrecio());
            this.bRepuestosModCantidad.setText(repuesto.getCantidad());
            this.jLabel159.setText("Seleccionado: "+repuesto.getNombre());
            this.estado12.setText("Estado: Editando Repuesto");
        }
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
       if(bRepuestosModNombre.getText().length()!=0 && bRepuestosModCodigo.getText().length()!=0 && 
            bRepuestosModPrecio.getText().length()!=0 && bRepuestosModCantidad.getText().length()!=0 && repuesto!=null){ 
            String repAntesdemodificar=repuesto.getNombre();
            repuesto.setNombre(this.bRepuestosModNombre.getText());
            repuesto.setCodigo(this.bRepuestosModCodigo.getText());
            repuesto.setPrecio(this.bRepuestosModPrecio.getText());
            repuesto.setCantidad(this.bRepuestosModCantidad.getText());

            this.hashmapRepuestos.remove(repAntesdemodificar);
            this.hashmapRepuestos.put(repuesto.getNombre(), repuesto);
            
            //this.actualizarTXTContactos();
            this.updateVRepuesto();
            this.actualizarTXTRepuestos();
       }
       repuesto=null;
       this.estado12.setText("Estado: Repuesto modificado exitosamente");
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        repuesto=null;
        this.vaciarBarrasR();
    }//GEN-LAST:event_jButton62ActionPerformed

    private void bRepuestosModCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRepuestosModCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bRepuestosModCodigoActionPerformed

    private void bRepuestosModNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRepuestosModNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bRepuestosModNombreActionPerformed

    private void bRepuestosModCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRepuestosModCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bRepuestosModCantidadActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaActionPerformed

    private void bFechaOrdenesCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFechaOrdenesCrear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bFechaOrdenesCrear1ActionPerformed

    private void tVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tVariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tVariosActionPerformed

    private void mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mesActionPerformed

    private void añoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_añoActionPerformed

    private void contactosCrear4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear4ActionPerformed
        if(this.isNumericDouble(this.pHTrabajo.getText())==true && this.isNumericDouble(this.hTrabajo.getText())==true){
            Double tHT=Double.parseDouble(this.pHTrabajo.getText())*Double.parseDouble(this.hTrabajo.getText());
            this.tManoObra.setText(Integer.toString(tHT.intValue()));
        }
                
    }//GEN-LAST:event_contactosCrear4ActionPerformed

    private void pHTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pHTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pHTrabajoActionPerformed

    private void tRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRepuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRepuestosActionPerformed

    private void calcularRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRepuestosActionPerformed
        int repu=0;
        this.totalRepuestos=0;
        if(!this.arrayListOrdenesAñadirRepuestosCliente.isEmpty() ){
            for(int h=0;h<this.arrayListOrdenesAñadirRepuestosCliente.size();h++){
                //System.out.println(this.arrayListOrdenesAñadirRepuestosCliente.get(h).getNombre());
                repuesto=this.arrayListOrdenesAñadirRepuestosCliente.get(h);
                //System.out.println(repuesto.getNombre());
                repu+=repuesto.precio*repuesto.cantidad;
            }
            this.totalRepuestos=repu;
            this.tRepuestos.setText(Integer.toString(repu));
            
        }
        
    }//GEN-LAST:event_calcularRepuestosActionPerformed

    private void contactosCrear5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear5ActionPerformed
        if(this.selected==true && this.bFechaOrdenesCrear8.getText().length()!=0 && !this.arrayListOrdenesAñadirManoObra.isEmpty() && !this.arrayListOrdenesAñadirRepuestosCliente.isEmpty()
                && tRepuestos.getText().length()!=0 && tManoObra.getText().length()!=0 && this.tTotal.getText().length()!=0
                && this.dia.getText().length()!=0 && this.mes.getText().length()!=0 && this.año.getText().length()!=0  ){
            orden=new Orden();
            orden.setNumeroOrden(bFechaOrdenesCrear8.getText());
            orden.setTotalManoObra(this.tManoObra.getText());
            if(this.tVarios.getText().length()!=0 && this.ordenesCrearVarios.getText().length()!=0){
                orden.setTotalVarios(this.tVarios.getText());
            }
                else{
                    orden.setTotalVarios("0");
                    this.ordenesCrearVarios.setText("  Sin Varios");
                }
            orden.setTotalRepuestos(this.tRepuestos.getText());
            orden.setTotal(this.tTotal.getText() );
            orden.setCliente(cliente.getNombre()+" "+cliente.getApellido());
            orden.setEmbrcacion(embarcacion.getMotor());
            String fcha=this.dia.getText()+"/"+this.mes.getText()+"/"+this.año.getText();
            //fecha
            orden.setFecha(fcha);
            if(this.ordenesCrearNota.getText().length()!=0){
                orden.setNota(this.ordenesCrearNota.getText());
            }
                else{
                    orden.setNota("  Sin Nota");
                }
            if(this.ordenesCrearVarios.getText().length()!=0){
                orden.setVarios(this.ordenesCrearVarios.getText());
            }
            for(int x=0; x<this.arrayListOrdenesAñadirManoObra.size();x++){
                String mO=this.arrayListOrdenesAñadirManoObra.get(x);
                orden.addManoObra(mO);
            }
            for(int x=0; x<this.arrayListOrdenesAñadirRepuestosCliente.size();x++){
                repuesto=this.arrayListOrdenesAñadirRepuestosCliente.get(x);
                orden.addRepuesto(repuesto.getNombre(),repuesto.getCantidad());
            }
            this.visualizarV4.setText(orden.getInformacion());
            System.out.println(orden.getInformacion());
            //this.hashmapOrdenes.put(orden.getNumeroOrden(),orden);
        }
    }//GEN-LAST:event_contactosCrear5ActionPerformed

    private void tTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTotalActionPerformed

    private void bFechaOrdenesCrear8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFechaOrdenesCrear8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bFechaOrdenesCrear8ActionPerformed

    private void calcularRepuestos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRepuestos1ActionPerformed
        if(tManoObra.getText().length()!=0){
            Double tHT=0.0;
            if(this.tRepuestos.getText().length()!=0){
                tHT+=Double.parseDouble(tRepuestos.getText());
            }
            else{
                tHT+=0.0;
            }
            if(this.tVarios.getText().length()!=0){
                tHT+=Double.parseDouble(tVarios.getText());
            }
            else{
                tHT+=0.0;
            }
            tHT+=Double.parseDouble(tManoObra.getText());
            tTotal.setText(Integer.toString(tHT.intValue()));
        }
    }//GEN-LAST:event_calcularRepuestos1ActionPerformed

    private void bTelFijoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC1ActionPerformed

    private void bTelFijoC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC4ActionPerformed

    private void bTelFijoC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC5ActionPerformed

    private void bTelFijoC6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC6ActionPerformed

    private void seleccionarModificarInfo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo10MouseClicked

    private void seleccionarModificarInfo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo10ActionPerformed
       if(cliente!=null && this.tablaOrdenesCrear2.getSelectedValue()!=null){
           embarcacion=cliente.embarcaciones.get(this.tablaOrdenesCrear2.getSelectedValue());
           this.jLabel36.setText("Embarcacion: "+embarcacion.getMotor());
           
       }
    }//GEN-LAST:event_seleccionarModificarInfo10ActionPerformed

    private void bCelCuidadorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCelCuidadorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCelCuidadorCActionPerformed
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clientes;
    private javax.swing.JPanel CrearOrden;
    private javax.swing.JPanel EliminarOrden;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Guarderias;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel Repuestos;
    private javax.swing.JTextArea TablaContactosVsualizar;
    private javax.swing.JPanel VisualizarOrden;
    private javax.swing.JPanel agregarLancha;
    private javax.swing.JPanel agregarLancha3;
    private javax.swing.JLabel añadiendolanchaa;
    private javax.swing.JLabel añadiendolanchaa3;
    private javax.swing.JTextField año;
    private javax.swing.JTextField bApellidoC;
    private javax.swing.JTextField bApellidoE;
    private javax.swing.JTextField bApellidoE3;
    private javax.swing.JTextField bCelCuidadorC;
    private javax.swing.JTextField bCelCuidadorC3;
    private javax.swing.JTextField bCelCuidadorE;
    private javax.swing.JTextField bCelCuidadorE3;
    private javax.swing.JTextField bCelularC;
    private javax.swing.JTextField bCelularC2;
    private javax.swing.JTextField bCelularC3;
    private javax.swing.JTextField bCelularE;
    private javax.swing.JTextField bCelularE3;
    private javax.swing.JTextField bCorreoC;
    private javax.swing.JTextField bCorreoC2;
    private javax.swing.JTextField bCorreoC3;
    private javax.swing.JTextField bCorreoE;
    private javax.swing.JTextField bCorreoE3;
    private javax.swing.JTextField bCuidadorC;
    private javax.swing.JTextField bCuidadorC2;
    private javax.swing.JTextField bCuidadorC3;
    private javax.swing.JTextField bCuidadorE;
    private javax.swing.JTextField bCuidadorE3;
    private javax.swing.JTextField bFechaOrdenesCrear1;
    private javax.swing.JTextField bFechaOrdenesCrear8;
    private javax.swing.JTextField bNombreC;
    private javax.swing.JTextField bNombreE;
    private javax.swing.JTextField bNombreE3;
    private javax.swing.JTextField bRepuestosModCantidad;
    private javax.swing.JTextField bRepuestosModCodigo;
    private javax.swing.JTextField bRepuestosModNombre;
    private javax.swing.JTextField bRepuestosModPrecio;
    private javax.swing.JTextField bTelFijE;
    private javax.swing.JTextField bTelFijE3;
    private javax.swing.JTextField bTelFijoC;
    private javax.swing.JTextField bTelFijoC1;
    private javax.swing.JTextField bTelFijoC2;
    private javax.swing.JTextField bTelFijoC3;
    private javax.swing.JTextField bTelFijoC4;
    private javax.swing.JTextField bTelFijoC5;
    private javax.swing.JTextField bTelFijoC6;
    private javax.swing.JButton calcularRepuestos;
    private javax.swing.JButton calcularRepuestos1;
    private javax.swing.JTextField cantidad;
    private javax.swing.JButton contactosCrear;
    private javax.swing.JButton contactosCrear1;
    private javax.swing.JButton contactosCrear2;
    private javax.swing.JButton contactosCrear3;
    private javax.swing.JButton contactosCrear4;
    private javax.swing.JButton contactosCrear5;
    private javax.swing.JPanel crear;
    private javax.swing.JPanel crear2;
    private javax.swing.JPanel crear3;
    private javax.swing.JTextField dia;
    private javax.swing.JPanel editar1;
    private javax.swing.JPanel editar4;
    private javax.swing.JTabbedPane editaraClientes;
    private javax.swing.JTabbedPane editaraClientes1;
    private javax.swing.JTabbedPane editaraClientes2;
    private javax.swing.JTabbedPane editaraClientes3;
    private javax.swing.JPanel eliminar;
    private javax.swing.JPanel eliminar2;
    private javax.swing.JPanel eliminar3;
    private javax.swing.JButton eliminarEliminar;
    private javax.swing.JButton eliminarEliminar1;
    private javax.swing.JButton eliminarEliminar2;
    private javax.swing.JButton eliminarEliminar3;
    private javax.swing.JTabbedPane eliminarLanchaa;
    private javax.swing.JTabbedPane eliminarLanchaa3;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel estado1;
    private javax.swing.JLabel estado10;
    private javax.swing.JLabel estado11;
    private javax.swing.JLabel estado12;
    private javax.swing.JLabel estado2;
    private javax.swing.JLabel estado9;
    private javax.swing.JButton guardarLancha;
    private javax.swing.JButton guardarLancha3;
    private javax.swing.JTextField hTrabajo;
    private javax.swing.JPanel info;
    private javax.swing.JPanel info3;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JPanel lanch;
    private javax.swing.JPanel lanch3;
    private javax.swing.JTextField m;
    private javax.swing.JTextField m3;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField marca3;
    private javax.swing.JTextField mes;
    private javax.swing.JTextField mo;
    private javax.swing.JTextField mo3;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField modelo3;
    private javax.swing.JPanel modificarLancha;
    private javax.swing.JPanel modificarLancha1;
    private javax.swing.JPanel modificarLancha6;
    private javax.swing.JPanel modificarLancha7;
    private javax.swing.JPanel modificarLancha8;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField mot3;
    private javax.swing.JTextField motor;
    private javax.swing.JTextField motor3;
    private javax.swing.JTextField nSerie;
    private javax.swing.JTextField nSerie1;
    private javax.swing.JTextField nSerie6;
    private javax.swing.JTextField nSerie7;
    private javax.swing.JTextPane ordenesCrearNota;
    private javax.swing.JTextPane ordenesCrearVarios;
    private javax.swing.JTextField pHTrabajo;
    private javax.swing.JLabel sel;
    private javax.swing.JLabel sel3;
    private javax.swing.JButton seleccionBorrado;
    private javax.swing.JButton seleccionBorrado1;
    private javax.swing.JButton seleccionBorrado15;
    private javax.swing.JButton seleccionBorrado16;
    private javax.swing.JButton seleccionBorrado17;
    private javax.swing.JButton seleccionBorrado18;
    private javax.swing.JButton seleccionBorrado19;
    private javax.swing.JButton seleccionBorrado2;
    private javax.swing.JButton seleccionBorrado3;
    private javax.swing.JButton seleccionBorrado4;
    private javax.swing.JButton seleccionBorrado5;
    private javax.swing.JButton seleccionBorrado6;
    private javax.swing.JLabel seleccionadoAgregar1;
    private javax.swing.JLabel seleccionadoAgregar4;
    private javax.swing.JButton seleccionarLanchaAgregar;
    private javax.swing.JButton seleccionarLanchaAgregar3;
    private javax.swing.JButton seleccionarModificarInfo;
    private javax.swing.JButton seleccionarModificarInfo10;
    private javax.swing.JButton seleccionarModificarInfo13;
    private javax.swing.JButton seleccionarModificarInfo3;
    private javax.swing.JButton seleccionarModificarInfo5;
    private javax.swing.JButton seleccionarModificarInfo6;
    private javax.swing.JButton seleccionarModificarInfo7;
    private javax.swing.JButton seleccionarModificarInfo8;
    private javax.swing.JButton seleccionarModificarInfo9;
    private javax.swing.JButton seleccionarV;
    private javax.swing.JButton seleccionarV2;
    private javax.swing.JButton seleccionarV3;
    private javax.swing.JButton seleccionarV5;
    private javax.swing.JButton seleccionarV6;
    private javax.swing.JTextField t;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField tManoObra;
    private javax.swing.JTextField tRepuestos;
    private javax.swing.JTextField tTotal;
    private javax.swing.JTextField tVarios;
    private javax.swing.JTabbedPane tablaClienteLanchas;
    private javax.swing.JTabbedPane tablaClienteLanchas3;
    public javax.swing.JList<String> tablaContactosEliminar;
    public javax.swing.JList<String> tablaEE;
    public javax.swing.JList<String> tablaEE3;
    public javax.swing.JList<String> tablaEmbarcaciones;
    public javax.swing.JList<String> tablaEmbarcaciones3;
    public javax.swing.JList<String> tablaLanchasAgregar;
    public javax.swing.JList<String> tablaLanchasAgregar3;
    public javax.swing.JList<String> tablaLanchasEliminar;
    public javax.swing.JList<String> tablaLanchasEliminar3;
    public javax.swing.JList<String> tablaLanchasModificar;
    public javax.swing.JList<String> tablaLanchasModificar3;
    public javax.swing.JList<String> tablaModificarInformacion;
    public javax.swing.JList<String> tablaModificarInformacion3;
    public javax.swing.JList<String> tablaOdenCrearAñadirRepuestos;
    public javax.swing.JList<String> tablaOrdenesCrear;
    public javax.swing.JList<String> tablaOrdenesCrear2;
    public javax.swing.JList<String> tablaOrdenesCrearManoObra;
    public javax.swing.JList<String> tablaOrdenesCrearVPMO;
    public javax.swing.JList<String> tablaOrdenesCrearVPRepuestos;
    public javax.swing.JList<String> tablaOrdenesV2;
    public javax.swing.JList<String> tablaOrdenesVisualizar;
    public javax.swing.JList<String> tablaRepuestosEliminados;
    public javax.swing.JList<String> tablaRepuestosEliminar;
    public javax.swing.JList<String> tablaRepuestosModificar;
    public javax.swing.JList<String> tablaRepuestosVisualizar;
    public javax.swing.JList<String> tablacontactosEliminados;
    public javax.swing.JList<String> tablacontactosEliminados1;
    public javax.swing.JList<String> tablacontactosEliminados3;
    public javax.swing.JList<String> tablacontactosEliminar1;
    public javax.swing.JList<String> tablacontactosEliminar5;
    public javax.swing.JList<String> tablacontactosEliminar6;
    public javax.swing.JList<String> tablacontactosVisualizar;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField tipo3;
    private javax.swing.JPanel visualizar;
    private javax.swing.JPanel visualizar2;
    private javax.swing.JPanel visualizar3;
    private javax.swing.JTextArea visualizarV1;
    private javax.swing.JTextArea visualizarV2;
    private javax.swing.JTextArea visualizarV3;
    private javax.swing.JTextArea visualizarV4;
    // End of variables declaration//GEN-END:variables
}
