/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.File; 
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import static javax.swing.text.StyleConstants.Background;
/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    
    int numCliente=0;
    Embarcacion embarcacion= new Embarcacion();
    Cliente cliente= new Cliente();
    
    Cliente clienteModificar= new Cliente();
    Cliente clienteEliminar= new Cliente();
    Cliente clienteRecuperar= new Cliente();
    Cliente clienteCargar= new Cliente();
    String clienteLoad="";
    Orden orden= new Orden();
    Repuesto repuesto= new Repuesto();
    Guarderia guarderia=new Guarderia();
    HashMap<String ,String> hashmapClientesCodigo= new HashMap<>();
    
    
    //CLIENTES
    HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
    ArrayList<String> arrayListContactos=new ArrayList<>();
    String [] listadoClientes = new String[1000];
    
    //ELIMINADOS
    HashMap<String ,Cliente> hashmapClientesEliminados= new HashMap<>();
    ArrayList<String> arrayListContactosEliminados=new ArrayList<>();
    String [] listadoClientesEliminados = new String[1];	
    
    //ORDENES
    HashMap<String ,Orden> hashmapOrdenesEliminadas= new HashMap<>();
    ArrayList<String> arrayListOrdenesEliminadas=new ArrayList<>();
    String [] listadoOrdenesEliminadas = new String[1];
    //Crear ORden
    ArrayList<Repuesto> arrayListOrdenesAñadirRepuestosCliente=new ArrayList<>();
    String [] listadoOrdenesAñadirRepuestosCliente = new String[1];
    String [] listadoOrdenesAñadirRepuestosClienteCantidad = new String[1];
    //MANO OBRA
    ArrayList<String> arrayListOrdenesAñadirManoObra=new ArrayList<>();
    String [] listadoOrdenesAñadirManoObra= new String[1];
    //Clientes Guarderia
    HashMap<String ,Guarderia> hashmapClientesGuarderia= new HashMap<>();
    ArrayList<String> arrayListContactosGuarderia=new ArrayList<>();
    String [] listadoClientesGuarderia = new String[1];
    
    
    //REPUESTOS
    HashMap<String ,Repuesto> hashmapRepuestos= new HashMap<>();
    ArrayList<String> arrayListRepuestos=new ArrayList<>();
    String [] listadoRepuestos = new String[1];
        //ELIINADOS
    HashMap<String ,Repuesto> hashmapRepuestosEliminados= new HashMap<>();
    ArrayList<String> arrayListRepuestosEliminados=new ArrayList<>();
    String [] listadoRepuestosEliminados = new String[1];
    //Mano obra
    ArrayList<String> arrayListManoObra=new ArrayList<>();
    String [] listadoManoObra = new String[1];
     //Embarcaciones
    boolean clienteCrear = false;
    boolean cargarCliente=false;
    boolean modificarCliente=false;
    boolean eliminarCliente=false;
    boolean recuperarCliente=false;
    boolean selected=false; 
    boolean cargarClienteEliminado=false; 
    int totalRepuestos=0,totalManoObra=0;
    public Image fondoMetal;
    
     public Interfaz() {
         
        this.setVisible(true);
        this.initComponents();
        //this.setearFondos();
        File carpeta = new File("ClientesEliminados");
        File crear_CC = new File("Clientes");
        crear_CC.mkdirs();
        carpeta.mkdirs();
         this.cargarRepuestos();
        this.updateVRepuesto();
        this.cargarDatosClientes();
        this.cargarDatosClientesEliminados();
        this.cargarManoObra();
        this.updateVCliente();
       
        this.updateVOrden();  
        
        //guarderia=new Guarderia();
        //guarderia.agregarLanchaAGuarderia("Yamaha", "300000");
        //this.añadirGuarderia("Gamboa Felipe");
    }
    public void cargarDatosClientes() {
        this.hashmapClientes = new HashMap<>();
        this.arrayListContactos= new ArrayList<>();
        final File carpeta = new File("Clientes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarCliente=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarCarpetaEmbarcacion(ficheroEntrada);
               this.cargarCarpetaOrdenes(ficheroEntrada);
               
               //System.out.println(this.clienteLoad+" "+clienteCargar.getApellidoNombre());
               
               this.cargarCliente=false;
            }
        }
        this.updateVCliente();
    }
    public void cargarDatosClientesEliminados() {
        this.hashmapClientesEliminados = new HashMap<>();
        this.arrayListContactosEliminados= new ArrayList<>();
        final File carpeta = new File("ClientesEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarClienteEliminado=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarCarpetaEmbarcacion(ficheroEntrada);
               this.cargarCarpetaOrdenes(ficheroEntrada);
               this.cargarClienteEliminado=false;
            }
        }
        this.actualizarListadoContactosEliminados();
        this.updateVCliente();
    }
    public void moverCarpetaOArchivo(String origen, String destino) throws IOException {
        Path FROM = Paths.get(origen);
        Path TO = Paths.get(destino);
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
    public void moverCliente(File file) throws IOException{
        final File carpeta = file;
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if(this.eliminarCliente==true){
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Informacion";
                    String ori="Clientes/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Informacion/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(oritxt,destxt);
                            doctxt.delete();
                        }
                    }
                    ficheroEntrada.delete();
                    //this.moverCarpetaOArchivo("Clientes/"+file.getName()+"/"+ficheroEntrada.getName(), "ClientesEliminados/"+file.getName()+"/"+ficheroEntrada.getName());
                    //System.out.println(ori+"\n"+dest);
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Ordenes") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Ordenes";
                    String ori="Clientes/"+file.getName()+"/Ordenes";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(oritxt,destxt);
                            doctxt.delete();
                        }
                    }
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Ordenes");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Guarderia") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Guarderia";
                    String ori="Clientes/"+file.getName()+"/Guarderia";
                    this.moverCarpetaOArchivo(ori,dest);
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Guarderia");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Embarcaciones") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Embarcaciones";
                    String ori="Clientes/"+file.getName()+"/Embarcaciones";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(oritxt,destxt);
                            doctxt.delete();
                        }
                    }
                    
                    ficheroEntrada.delete();
                }
            }
            if(this.recuperarCliente==true){
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Informacion";
                    String ori="Clientes/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Informacion/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(destxt,oritxt);
                            doctxt.delete();
                        }
                    }
                    ficheroEntrada.delete();
                    //this.moverCarpetaOArchivo("Clientes/"+file.getName()+"/"+ficheroEntrada.getName(), "ClientesEliminados/"+file.getName()+"/"+ficheroEntrada.getName());
                    //System.out.println(ori+"\n"+dest);
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Ordenes") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Ordenes";
                    String ori="Clientes/"+file.getName()+"/Ordenes";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(destxt,oritxt);
                            doctxt.delete();
                        }
                    }
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Ordenes");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Guarderia") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Guarderia";
                    String ori="Clientes/"+file.getName()+"/Guarderia";
                    this.moverCarpetaOArchivo(dest,ori);
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Guarderia");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Embarcaciones") ) {
                    String dest="ClientesEliminados/"+file.getName()+"/Embarcaciones";
                    String ori="Clientes/"+file.getName()+"/Embarcaciones";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="ClientesEliminados/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            String oritxt="Clientes/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(destxt,oritxt);
                            doctxt.delete();
                        }
                    }
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Embarcaciones");
                    ficheroEntrada.delete();
                }
            }
        }
    }
    public void extraerArchivosCliente(Integer num) throws IOException {
        //System.out.println("Extrallendo archivos cliente: "+this.eliminarCliente+" num: "+num);
        final File carpeta = new File("Clientes");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(num.toString())) {
               //System.out.println("MATCH ELIMINAR: "+ficheroEntrada.getName());
               if(eliminarCliente==true){
                //System.out.println("Eliminando: "+ficheroEntrada.getAbsolutePath());
                String dest="ClientesEliminados/"+ficheroEntrada.getName();
                String ori="Clientes/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(ori,dest);
                this.moverCliente(ficheroEntrada);
                ficheroEntrada.delete();
               }
               if(this.cargarCliente==true ){
                this.cargarCarpetaInformacion(ficheroEntrada);
               }
                if(this.modificarCliente==true ){
                    //System.out.println("MODIFICANDO");
                this.modificarInformacionCliente();
               }
            }
        }
    }
    public void extraerArchivosClienteEliminado(Integer num) throws IOException {
        final File carpeta = new File("ClientesEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(num.toString())) {
               if(recuperarCliente==true){
                //System.out.println("Recuperando a: "+ficheroEntrada.getName());
                String dest="ClientesEliminados/"+ficheroEntrada.getName();
                String ori="Clientes/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(dest,ori);
                this.moverCliente(ficheroEntrada);
                ficheroEntrada.delete();
               }
            }
        }
    }

    /*
    Carga la carpeta con informacion de un cliente
    */
    public void cargarCarpetaInformacion(File carpetaCliente) {
        //System.out.println("\nLOADING CARPETA");
        for (final File ficheroEntrada : carpetaCliente.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                //tratamos de gestionar el txt con la informacion
                this.gestionarTxTInformacionCliente(ficheroEntrada);
            }
        }
    }
    public void cargarCarpetaOrdenes(File carpetaOrdenes) {
       //System.out.println("\nLOADING CARPETA EMBARCCIONES");
        for (final File ficheroEntrada : carpetaOrdenes.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Ordenes") ) {
                if(this.cargarCliente==true){
                    cliente=this.hashmapClientes.get(this.clienteLoad);
                    //System.out.println("Cliente añadir emb: "+cliente.getApellidoNombre());
                    this.gestionarTxTOrdenesCliente(ficheroEntrada);
                }
                if(this.cargarClienteEliminado==true){
                    cliente=this.hashmapClientesEliminados.get(this.clienteLoad);
                    //System.out.println("Cliente añadir emb: "+cliente.getApellidoNombre());
                    this.gestionarTxTOrdenesCliente(ficheroEntrada);
                }
               
            }
        }
    }
    public void cargarCarpetaEmbarcacion(File carpetaCliente) {
       //System.out.println("\nLOADING CARPETA EMBARCCIONES");
        for (final File ficheroEntrada : carpetaCliente.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Embarcaciones") ) {
                if(this.cargarCliente==true){
                    cliente=this.hashmapClientes.get(this.clienteLoad);
                    //System.out.println("Cliente añadir emb: "+cliente.getApellidoNombre());
                    this.gestionarTxTEmbarcacionesCliente(ficheroEntrada);
                }
                if(this.cargarClienteEliminado==true){
                    cliente=this.hashmapClientesEliminados.get(this.clienteLoad);
                    //System.out.println("Cliente añadir emb: "+cliente.getApellidoNombre());
                    this.gestionarTxTEmbarcacionesCliente(ficheroEntrada);
                }
               
            }
        }
    }
       public void modificarInformacionCliente() {
            //System.out.println("Modificando info cliente"+cliente.getNumCliente());
            File crear_TxTInfo = new File("Clientes/"+clienteModificar.getNumCliente()+"/Informacion/Info.txt");
            try{
            crear_TxTInfo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.actualizarTXTInfoContacto(crear_TxTInfo,clienteModificar);
             //System.out.println("INFO.txt CREADO");
            //this.actualizarTXTInfoContacto(crear_TxTInfo, clienteModificar);
               //this.cargarTXTInfo(ficheroEntrada);
               this.clienteModificar=null;
            }
    public void gestionarTxTInformacionCliente(File carpetainfo) {
        //System.out.println("LOADING INFO.txt");
        for (final File ficheroEntrada : carpetainfo.listFiles()) {
            //System.out.println("___________: "+ficheroEntrada);
            if (ficheroEntrada.isFile() && this.cargarCliente==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("modificar=False: "+ficheroEntrada);
               this.cargarCliente(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.cargarClienteEliminado==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("modificar=False: "+ficheroEntrada);
               this.cargarCliente(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.modificarCliente==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("Modificar=True"+ficheroEntrada);
               ficheroEntrada.delete();
               this.modificarInformacionCliente();
            }
        }
    }
    public void gestionarTxTOrdenesCliente(File carpetaEmb) {
        //System.out.println("LOADING INFO.txt");
        for (final File ficheroEntrada : carpetaEmb.listFiles()) {
            //System.out.println(" GESTIONANDO EMBARCACIONES: "+ficheroEntrada);
            if (ficheroEntrada.isFile()) {
               //System.out.println("Orden encontrada: "+ficheroEntrada.getName());
               this.cargarOrden(ficheroEntrada);
            }
          
        }
    }
    public void gestionarTxTEmbarcacionesCliente(File carpetaEmb) {
        //System.out.println("LOADING INFO.txt");
        for (final File ficheroEntrada : carpetaEmb.listFiles()) {
            //System.out.println(" GESTIONANDO EMBARCACIONES: "+ficheroEntrada);
            if (ficheroEntrada.isFile()) {
               //System.out.println("Emb encontrada: "+ficheroEntrada.getName());
               this.cargarEmbarcacion(ficheroEntrada);
            }
          
        }
    }
    public void modificarTXTInfo(File doc){
        try {
            File file = doc;
            Cliente cl=new Cliente();
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                        cl =this.hashmapClientes.get(cliente.getApellidoNombre());
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              
        } catch (IOException e) {
        }
    }
    public void cargarTXTInfo(File doc){
        try {
            File file = doc;
            Cliente cl=new Cliente();
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                        cl =this.hashmapClientes.get(cliente.getApellidoNombre());
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
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
        guarderia.setCliente(nombreApellido);
        this.hashmapClientesGuarderia.put(nombreApellido, guarderia);
        
     }
    
    public void añadirEmbAGuardria( String emba, String preci ){
        guarderia.inicializarAñosGuarderia(preci);
        for(int x=0;x<this.arrayListContactos.size();x++){
            cliente=this.hashmapClientes.get(this.arrayListContactos.get(x));
                for(int r=0;r<cliente.listadoEmbarcaciones.size();r++){
                    embarcacion=cliente.hashMapEmbarcaciones.get(cliente.listadoEmbarcaciones.get(r));
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
                    embarcacion=cliente.hashMapEmbarcaciones.get(cliente.listadoEmbarcaciones.get(r));
                    if(embarcacion.getEnGuarderia()==true){
                        guarderia=new Guarderia();
                        
                        guarderia.setCliente(cliente.getApellidoNombre());
                        
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
                        Cliente cl=cliente;
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                        bw.close();
              }
        } catch (IOException e) {
        }
    }
     
    public void actualizarTXTOrdenesEliminadas(){
           try {
            String ruta = "OrdenesEliminadas.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.hashmapOrdenesEliminadas.size();f++){
                        orden=this.hashmapOrdenesEliminadas.get(this.arrayListOrdenesEliminadas.get(f));
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
    public void crearTxTNumContactos(){
        File file = new File("NumClientes.txt");
            try {
           
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(Integer.toString(this.hashmapClientes.size()+this.hashmapClientesEliminados.size()));
            int j=this.hashmapClientes.size()+this.hashmapClientesEliminados.size();
            bw.newLine();
              }
        } catch (IOException e) {
        }
    }
    public void crearArchivosContacto( Cliente cliente){
        String dir="Clientes/"+cliente.getNumCliente();
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
        this.actualizarTXTInfoContacto(crear_TxTInfo,cliente);
    } 
    public void cargarOrden(File f){
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            orden= new Orden();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        linea = entrada.readLine();
                        orden.setNumeroOrden(linea);
                         linea = entrada.readLine();
                        orden.setTotalManoObra(linea);
                        linea = entrada.readLine();
                        orden.setTotalVarios(linea);
                        linea = entrada.readLine();
                        orden.setTotalRepuestos(linea);
                        linea = entrada.readLine();
                        orden.setTotal(linea);
                        linea = entrada.readLine();
                        orden.setCliente(linea);
                        linea = entrada.readLine();
                        orden.setEmbrcacion(linea);
                        linea = entrada.readLine();
                        orden.setFecha(linea);
                        linea = entrada.readLine();
                        if("NO".equals(linea)){
                            orden.setDeudaNo();
                        }
                        if("SI".equals(linea)){
                            orden.setDeudaSi();
                        }
                        linea = entrada.readLine();
                        String nota="";
                        while(!"*#".equals(linea)){
                            nota+=linea;
                            linea = entrada.readLine();
                        }
                        if(nota.length()!=0){
                            orden.setNota(nota);
                        }
                        if(nota.length()!=0){
                            orden.setNota(" - Sin anotaciones");
                        }
                        linea = entrada.readLine();
                        String varios="";
                        while(!"#*".equals(linea)){
                            varios+=linea;
                            linea = entrada.readLine();
                        }
                        if(varios.length()!=0){
                            orden.setVarios(nota);
                        }
                        if(varios.length()!=0){
                            orden.setVarios(" - Sin Varios");
                        }
                        linea = entrada.readLine();
                        //this.arrayListOrdenesAñadirManoObra=new ArrayList<>();
                        while(!linea.equals("**")){
                            orden.addManoObra(linea);
                            linea = entrada.readLine();
                        } 
                        //System.out.println("Saliendo while: "+linea);
//                        for(int x=0;x<this.arrayListOrdenesAñadirManoObra.size();x++){
//                            String mO=this.arrayListOrdenesAñadirManoObra.get(x);
//                            
//                        }
                        linea = entrada.readLine();
                        //this.arrayListOrdenesAñadirManoObra=new ArrayList<>();
                        Repuesto repAdd= new Repuesto();
                        //System.out.println("_________________-");
                        while(!"***".equals(linea)){
                            repAdd.setNombre(linea);
                            linea = entrada.readLine();
                            repAdd.setCodigo(linea);
                            linea = entrada.readLine();
                            repAdd.setPrecio(linea);
                            linea = entrada.readLine();
                            repAdd.setCantidad(linea);   
                            orden.addRepuesto(repAdd);
                            linea = entrada.readLine();
                        }
                        //System.out.println("_________________-");
                        //System.out.println("ANTES D");
                        linea = entrada.readLine();
                          
                    }
                }
                
            } 
            if(this.cargarCliente==true){
                 cliente=this.hashmapClientes.get(clienteLoad);
                 //System.out.println("AÑADIENDO LANCHA A : "+clienteLoadEmbarcacion);
                 cliente.addOrden(orden);
                }
                 if(this.cargarClienteEliminado==true){
                 cliente=this.hashmapClientesEliminados.get(clienteLoad);
                 //System.out.println("AÑADIENDO LANCHA A : "+clienteLoadEmbarcacion);
                 cliente.addOrden(orden);
                }
            }catch (IOException e) { 
            } 
            finally{ 
                try{ 
                    entrada.close(); 
                }
                catch(IOException e1){} 
            } 
    }
    public void cargarEmbarcacion(File f){
        //System.out.println("CARGANDO EMBARCACION: "+f.getName());
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Embarcacion embLoad= new Embarcacion();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    //System.out.println(linea);
                    if("#".equals(linea)){
                        linea = entrada.readLine();
                        embLoad.setCodigo(linea);
                        linea = entrada.readLine();
                        embLoad.setTipo(linea);
                        linea = entrada.readLine();
                        embLoad.setMarca(linea);
                        linea = entrada.readLine();
                        embLoad.setModelo(linea);
                        linea = entrada.readLine();
                        embLoad.setMotor(linea);
                        linea = entrada.readLine();
                        embLoad.setnSerie(linea);
                        linea = entrada.readLine();
                        embLoad.setClaveLancha(linea);
                        linea = entrada.readLine();
                    }
                }
                if(this.cargarCliente==true){
                 cliente=this.hashmapClientes.get(clienteLoad);
                 //System.out.println("AÑADIENDO LANCHA A : "+clienteLoadEmbarcacion);
                 cliente.addEmbarcacion(embLoad);
                }
                 if(this.cargarClienteEliminado==true){
                 cliente=this.hashmapClientesEliminados.get(clienteLoad);
                 //System.out.println("AÑADIENDO LANCHA A : "+clienteLoadEmbarcacion);
                 cliente.addEmbarcacion(embLoad);
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
    }
    public void cargarCliente(File f){
        //System.out.println("CARGANDO");
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Cliente cl= new Cliente();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        cl= new Cliente();
                        linea = entrada.readLine(); 
                        cl.setNumCliente(Integer.parseInt(linea));
                        linea = entrada.readLine(); 
                        cl.setNombe(linea);
                        linea = entrada.readLine(); 
                        cl.setApellido(linea);
                        linea = entrada.readLine(); 
                        cl.setCelular(linea);
                        linea = entrada.readLine(); 
                        cl.setTelFijo(linea);
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina1(linea);
                        linea = entrada.readLine(); 
                        cl.setTelFijoOficina2(linea);
                        linea = entrada.readLine(); 
                        cl.setCorreo(linea);
                        linea = entrada.readLine(); 
                        cl.setCuidador(linea);
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
                        if("SI".equals(linea)){
                            cl.setDeudaGuarderiaSI();
                        }
                        else{
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
                     if(this.clienteCrear==true){
                        this.hashmapClientes.put(cl.getApellidoNombre(),cl);
                        this.arrayListContactos.add(cl.getApellidoNombre());
                        this.actualizarListadoContactos();
                    }
                    if(this.cargarCliente==true){
                        clienteLoad=cl.getApellidoNombre();
                        this.hashmapClientes.put(cl.getApellidoNombre(),cl);
                        this.arrayListContactos.add(cl.getApellidoNombre());
                        this.actualizarListadoContactos();
                    }
                    if(this.recuperarCliente==true){
                         this.hashmapClientes.put(cl.getApellidoNombre(),cl);
                         this.arrayListContactos.add(cl.getApellidoNombre());
                     }
                    if(this.cargarClienteEliminado==true){
                        clienteLoad=cl.getApellidoNombre();
                        this.hashmapClientesEliminados.put(cl.getApellidoNombre(),cl);
                        this.arrayListContactosEliminados.add(cl.getApellidoNombre());
                        this.actualizarListadoContactosEliminados();
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
    }
    public void cargarRepuestos(){
            File f = new File( "Repuestos.txt" ); 
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Repuesto rep;
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        rep= new Repuesto();
                        linea = entrada.readLine(); 
                        rep.setNombre(linea);
                        linea = entrada.readLine(); 
                        rep.setCodigo(linea);
                        linea = entrada.readLine(); 
                        rep.setPrecio(linea);
                        linea = entrada.readLine(); 
                        rep.setCantidad(linea);
                        this.hashmapRepuestos.put(rep.getNombre(), rep);
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
    }
    
    public void cargarTXTEmbarcciones(){
        final File carpeta = new File("Clientes/Embarcaciones");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isFile()) {
               //System.out.println("Emb: "+ficheroEntrada.getName());
               this.cargarCliente=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarCliente=false;
            }
        }
        this.actualizarListadoContactos();
        this.setearTablasListadoClientes();
    }

    public void actualizarRepuestosEliminados(){
        listadoRepuestosEliminados=new String[1000];
        this.arrayListRepuestosEliminados=new  ArrayList<>();
        hashmapRepuestosEliminados.entrySet().forEach((Map.Entry<String, Repuesto> entry) -> {
            Interfaz.this.arrayListRepuestosEliminados.add(entry.getValue().getNombre());
        });
        Collections.sort(Interfaz.this.arrayListRepuestosEliminados, String::compareTo);
        for (int g=0;g<this.arrayListRepuestosEliminados.size();g++) {
            listadoRepuestosEliminados[g]=this.arrayListRepuestosEliminados.get(g);
        }
    }
    public void actualizarListadoRepuestos(){
        listadoRepuestos=new String[this.hashmapRepuestos.size()];
        this.arrayListRepuestos=new  ArrayList<>();
        hashmapRepuestos.entrySet().forEach((Map.Entry<String, Repuesto> entry) -> {
            Interfaz.this.arrayListRepuestos.add(entry.getValue().getNombre());
            
        });
        Collections.sort(Interfaz.this.arrayListRepuestos, String::compareTo);
        for (int g=0;g<this.arrayListRepuestos.size();g++) {
            listadoRepuestos[g]=this.arrayListRepuestos.get(g);
        }
    }
    public void actualizarListadoManoObra(){
        listadoManoObra=new String[this.arrayListManoObra.size()];
        Collections.sort(Interfaz.this.arrayListManoObra, String::compareTo);
        for (int g=0;g<this.arrayListManoObra.size();g++) {
            listadoManoObra[g]=this.arrayListManoObra.get(g);
        }
    }
    public void actualizarListadoContactos(){
        this.arrayListContactos = new  ArrayList<>();
        for (Map.Entry<String, Cliente> entry : hashmapClientes.entrySet()) {
            this.arrayListContactos.add(entry.getValue().getApellidoNombre());
        }
        Collections.sort(Interfaz.this.arrayListContactosEliminados, String::compareTo);
        listadoClientes=new String[this.arrayListContactos.size()];
        for (int g=0;g<this.arrayListContactos.size();g++) {
            listadoClientes[g]=this.arrayListContactos.get(g);
        }
        //System.out.println("Contactos Existentes: "+this.arrayListContactos.toString());
    }
    public void actualizarTXTRepuestosEliminados(){
        listadoRepuestosEliminados=new String[1000];
        this.arrayListRepuestosEliminados=new  ArrayList<>();
        for (Map.Entry<String, Repuesto> entry : hashmapRepuestosEliminados.entrySet()) {
            this.arrayListRepuestosEliminados.add(entry.getValue().getNombre());
        }
        for (int g=0;g<this.arrayListRepuestosEliminados.size();g++) {
            listadoRepuestosEliminados[g]=this.arrayListRepuestosEliminados.get(g);
        }
    }
    public void actualizarListadoContactosEliminados(){
        this.arrayListContactosEliminados= new  ArrayList<>();
        for (Map.Entry<String, Cliente> entry : hashmapClientesEliminados.entrySet()) {
            this.arrayListContactosEliminados.add(entry.getValue().getApellidoNombre());
        }
        Collections.sort(Interfaz.this.arrayListContactosEliminados, String::compareTo);
        listadoClientesEliminados=new String[this.arrayListContactosEliminados.size()];
        for (int g=0;g<this.arrayListContactosEliminados.size();g++) {
            listadoClientesEliminados[g]=this.arrayListContactosEliminados.get(g);
        }
         //System.out.println("Contactos Eliminados:"+this.arrayListContactosEliminados.toString());
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
        }
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
        this.tablaRepuestosVisualizar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminados.setListData(listadoRepuestosEliminados);
        this.tablaRepuestosModificar.setListData(listadoRepuestos);
    }
    public void setearTablasListadoOrdenes(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablacontactosEliminados1.setListData(listadoOrdenesEliminadas);
        this.tablaOrdenesVisualizar.setListData(listadoClientes);
        this.tablaOdenCrearAñadirRepuestos.setListData(listadoRepuestos);
        this.tablaOrdenesCrearVPRepuestos.setListData(listadoOrdenesAñadirRepuestosClienteCantidad);
        this.tablaOrdenesCrearManoObra.setListData(listadoManoObra);
        this.tablacontactosEliminados1.setListData(listadoOrdenesEliminadas);
        this.tablaOrdenesCrear.setListData(listadoClientes);
    }
    public void vaciarTablasO(){
        String [] vacio = new String[1];
        this.tablaOrdenesVisualizar.setListData(vacio);
        this.tablacontactosEliminar1.setListData(vacio);
        this.tablaOdenCrearAñadirRepuestos.setListData(vacio);
        this.tablaOrdenesCrearVPRepuestos.setListData(vacio);
        this.tablacontactosEliminados1.setListData(vacio);
        this.tablaOrdenesCrear.setListData(vacio);
        this.tablaOrdenesCrear2.setListData(vacio);
    }                              
    public void vaciarTablasC(){
        String [] vacio = new String[1];
        this.tablacontactosVisualizar.setListData    (vacio);
        this.tablaModificarInformacion.setListData(vacio);
        this.tablaContactosEliminar.setListData(vacio);
        this.tablaEmbarcaciones.setListData(vacio);
        this.tablaLanchasAgregar.setListData(vacio);
    }
    public void vaciarTablasR(){
        String [] vacio = new String[1];
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

        Fondo = new FondoPanel("3.jpg");
        Main = new javax.swing.JTabbedPane();
        Clientes = new FondoPanel("3.jpg");
        editaraClientes = new javax.swing.JTabbedPane();
        visualizar = new FondoPanel("3.jpg");
        jScrollPane8 = new javax.swing.JScrollPane();
        tablacontactosVisualizar = new javax.swing.JList<>();
        seleccionarV = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TablaContactosVsualizar = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        crear = new FondoPanel("3.jpg");
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
        jLabel40 = new javax.swing.JLabel();
        eliminar = new FondoPanel("3.jpg");
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
        ModificarC = new FondoPanel("3.jpg");
        tablaClienteLanchas = new javax.swing.JTabbedPane();
        info = new FondoPanel("3.jpg");
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
        jLabel38 = new javax.swing.JLabel();
        lanch = new FondoPanel("3.jpg");
        eliminarLanchaa = new javax.swing.JTabbedPane();
        agregarLancha = new FondoPanel("3.jpg");
        jLabel56 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        marca = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        modelo = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        motor = new javax.swing.JTextField();
        numeroDeSerie = new javax.swing.JTextField();
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
        jLabel61 = new javax.swing.JLabel();
        claveEmb = new javax.swing.JTextField();
        modificarLancha = new FondoPanel("3.jpg");
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
        jLabel62 = new javax.swing.JLabel();
        claveEmb1 = new javax.swing.JTextField();
        modificarLancha1 = new FondoPanel("3.jpg");
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
        Ordenes = new FondoPanel("3.jpg");
        editaraClientes1 = new javax.swing.JTabbedPane();
        VisualizarOrden = new FondoPanel("3.jpg");
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        visualizarV1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        seleccionarV3 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar1 = new javax.swing.JList<>();
        jLabel37 = new javax.swing.JLabel();
        seleccionarV4 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        CrearOrden = new FondoPanel("3.jpg");
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
        EliminarOrden = new FondoPanel("3.jpg");
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
        setTitle("Servinautica Vichuquén");
        setBackground(new java.awt.Color(0, 0, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Fondo.setBackground(new java.awt.Color(15, 15, 105));
        Fondo.setPreferredSize(new java.awt.Dimension(1300, 720));
        Fondo.setVerifyInputWhenFocusTarget(false);

        Main.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Main.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Main.setPreferredSize(new java.awt.Dimension(1700, 720));
        Main.setRequestFocusEnabled(false);

        editaraClientes.setBackground(new java.awt.Color(255, 255, 255));
        editaraClientes.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        tablacontactosVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(tablacontactosVisualizar);

        seleccionarV.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV.setText("Ver");
        seleccionarV.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        seleccionarV.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
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
        TablaContactosVsualizar.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        TablaContactosVsualizar.setRows(1);
        TablaContactosVsualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaContactosVsualizar.setMaximumSize(new java.awt.Dimension(700, 20000));
        jScrollPane9.setViewportView(TablaContactosVsualizar);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Seleccionado:");

        javax.swing.GroupLayout visualizarLayout = new javax.swing.GroupLayout(visualizar);
        visualizar.setLayout(visualizarLayout);
        visualizarLayout.setHorizontalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(564, Short.MAX_VALUE))
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
                            .addGroup(visualizarLayout.createSequentialGroup()
                                .addComponent(seleccionarV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visualizarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
        );

        editaraClientes.addTab("Visualizar", visualizar);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Celular: ");

        bTelFijoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoCActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tel. Fijo:");

        bCorreoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoCActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo: ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cuidador:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cel. Cuidador :");

        bCelCuidadorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCelCuidadorCActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        contactosCrear.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear.setText("Crear");
        contactosCrear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contactosCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrearActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tel. Fijo Oficina 1:");

        bTelFijoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Tel. Fijo Oficina 2:");

        bTelFijoC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC4ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Estado: Esperando ingreso de datos");

        javax.swing.GroupLayout crearLayout = new javax.swing.GroupLayout(crear);
        crear.setLayout(crearLayout);
        crearLayout.setHorizontalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40))
                            .addGroup(crearLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(contactosCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel24))
                .addContainerGap(852, Short.MAX_VALUE))
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
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactosCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addContainerGap(323, Short.MAX_VALUE))
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

        eliminarEliminar.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        eliminarEliminar.setText("Eliminar");
        eliminarEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Existentes");

        tablacontactosEliminados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(tablacontactosEliminados);

        seleccionBorrado.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado.setText("Recuperar");
        seleccionBorrado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(570, Short.MAX_VALUE))
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
                            .addComponent(seleccionBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(102, Short.MAX_VALUE))))
        );

        editaraClientes.addTab("Eliminar", eliminar);

        tablaClienteLanchas.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tablaClienteLanchas.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Apellido:");

        bCelularE.setText(" ");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Tel. Fijo:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Correo: ");

        bCuidadorE.setText(" ");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Cuidador:");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Cel. Cuidador :");

        bCelCuidadorE.setText(" ");

        jButton9.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton9.setText("Guardar");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton15.setText("Cancelar");
        jButton15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel25.setText("Buscar:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Seleccionado:");

        seleccionarModificarInfo.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo.setText("Seleccionar");
        seleccionarModificarInfo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Tel. Fijo Oficina 1:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel38.setText("jLabel38");

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(infoLayout.createSequentialGroup()
                                        .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(472, Short.MAX_VALUE))
                            .addGroup(infoLayout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel38)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109)
                        .addComponent(jLabel38))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        tablaClienteLanchas.addTab("Datos", info);

        eliminarLanchaa.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Marca:");

        modelo.setText(" ");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Modelo:");

        motor.setText(" ");
        motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorActionPerformed(evt);
            }
        });

        numeroDeSerie.setText(" ");
        numeroDeSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroDeSerieActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Motor:");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("N° Serie:");

        guardarLancha.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        guardarLancha.setText("Guardar");
        guardarLancha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardarLancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLanchaActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton17.setText("Cancelar");
        jButton17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel26.setText("Buscar:");

        seleccionadoAgregar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seleccionadoAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        seleccionadoAgregar1.setText("Seleccionado: ");

        seleccionarLanchaAgregar.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarLanchaAgregar.setText("Seleccionar");
        seleccionarLanchaAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        estado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        estado.setForeground(new java.awt.Color(255, 255, 255));
        estado.setText("Estado: Seleccionando Cliente");

        añadiendolanchaa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        añadiendolanchaa.setForeground(new java.awt.Color(255, 255, 255));
        añadiendolanchaa.setText("Añadiendo Lancha a:  ");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Clave lancha:");

        claveEmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveEmbActionPerformed(evt);
            }
        });

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
                            .addComponent(seleccionadoAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(estado, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                            .addComponent(añadiendolanchaa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(agregarLanchaLayout.createSequentialGroup()
                                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(guardarLancha, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(agregarLanchaLayout.createSequentialGroup()
                                    .addComponent(jLabel61)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(claveEmb))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, agregarLanchaLayout.createSequentialGroup()
                                    .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel57)
                                        .addComponent(jLabel58)
                                        .addComponent(jLabel59)
                                        .addComponent(jLabel60)
                                        .addComponent(jLabel56))
                                    .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(agregarLanchaLayout.createSequentialGroup()
                                            .addGap(32, 32, 32)
                                            .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(modelo, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                                .addComponent(numeroDeSerie)
                                                .addComponent(motor)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarLanchaLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(402, Short.MAX_VALUE))
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
                            .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(numeroDeSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveEmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addGap(16, 16, 16)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guardarLancha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(estado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );

        eliminarLanchaa.addTab("Agrega", agregarLancha);

        jLabel27.setText("Buscar:");

        jTextField16.setText(" ");

        jButton26.setText("Buscar");

        jButton27.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton27.setText("Seleccionar");
        jButton27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Seleccionado: ");

        tablaEmbarcaciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane16.setViewportView(tablaEmbarcaciones);

        seleccionBorrado3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado3.setText("Seleccionar");
        seleccionBorrado3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Motor: ");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Modelo:");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Tipo: ");

        nSerie1.setText(" ");
        nSerie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie1ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton12.setText("Guardar");
        jButton12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton18.setText("Cancelar");
        jButton18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("N° Serie: ");

        m.setText(" ");
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Embarcacion: ");

        estado2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        estado2.setForeground(new java.awt.Color(255, 255, 255));
        estado2.setText("Estado: Seleccionando Cliente");

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Clave lancha:");

        claveEmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveEmb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarLanchaLayout = new javax.swing.GroupLayout(modificarLancha);
        modificarLancha.setLayout(modificarLanchaLayout);
        modificarLanchaLayout.setHorizontalGroup(
            modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionBorrado3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                                        .addComponent(jLabel62)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(claveEmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel68)
                                            .addComponent(jLabel64)
                                            .addComponent(jLabel63)
                                            .addComponent(jLabel67)
                                            .addComponent(jLabel66))
                                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(mot, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(nSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(mo, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(0, 85, Short.MAX_VALUE))
                            .addComponent(estado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 796, Short.MAX_VALUE)))
                .addContainerGap())
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
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(seleccionBorrado3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(claveEmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62))))
                        .addGap(25, 25, 25)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado2)
                        .addGap(0, 285, Short.MAX_VALUE))
                    .addComponent(jScrollPane16)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        eliminarLanchaa.addTab("Modificar", modificarLancha);

        jLabel28.setText("Buscar:");

        jTextField17.setText(" ");

        jButton28.setText("Buscar");

        jButton29.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton29.setText("Seleccionar");
        jButton29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        seleccionBorrado4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado4.setText("Cancelar");
        seleccionBorrado4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Embarcacion: ");

        tablaEE.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane19.setViewportView(tablaEE);

        seleccionBorrado5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado5.setText("Seleccionar");
        seleccionBorrado5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        sel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sel.setForeground(new java.awt.Color(255, 255, 255));
        sel.setText("Seleccionado: ");

        seleccionBorrado6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado6.setText("Eliminar");
        seleccionBorrado6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        estado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        estado1.setForeground(new java.awt.Color(255, 255, 255));
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
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))))
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
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        tablaClienteLanchas.addTab("Embarcacion", lanch);

        javax.swing.GroupLayout ModificarCLayout = new javax.swing.GroupLayout(ModificarC);
        ModificarC.setLayout(ModificarCLayout);
        ModificarCLayout.setHorizontalGroup(
            ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
            .addGroup(ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ModificarCLayout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas)
                    .addContainerGap()))
        );
        ModificarCLayout.setVerticalGroup(
            ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
            .addGroup(ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaClienteLanchas))
        );

        editaraClientes.addTab("Modificar", ModificarC);

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
        );

        Main.addTab("CLIENTES", Clientes);

        editaraClientes1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

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
        visualizarV1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        visualizarV1.setRows(1);
        visualizarV1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane11.setViewportView(visualizarV1);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Cliente Seleccionado:");

        seleccionarV3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV3.setText("Seleccionar");
        seleccionarV3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        tablaOrdenesVisualizar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane20.setViewportView(tablaOrdenesVisualizar1);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Orden Seleccionada:");

        seleccionarV4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV4.setText("Seleccionar");
        seleccionarV4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        seleccionarV4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV4MouseClicked(evt);
            }
        });
        seleccionarV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV4ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Ordenes");

        javax.swing.GroupLayout VisualizarOrdenLayout = new javax.swing.GroupLayout(VisualizarOrden);
        VisualizarOrden.setLayout(VisualizarOrdenLayout);
        VisualizarOrdenLayout.setHorizontalGroup(
            VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarV4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarV3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(1049, 1049, Short.MAX_VALUE))
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
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11))
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionarV3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                                .addComponent(seleccionarV4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))))
                .addGap(86, 86, 86))
        );

        editaraClientes1.addTab("Visualizar", VisualizarOrden);

        dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Fecha:");

        contactosCrear1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear1.setText("Crear");
        contactosCrear1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(255, 255, 255));
        jLabel148.setText("Seleccionado:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mano de Obra");

        tablaOrdenesCrear.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane50.setViewportView(tablaOrdenesCrear);

        seleccionarModificarInfo5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo5.setText("Agregar");
        seleccionarModificarInfo5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel149.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setText("Añadir Mano de obra al listado");

        bFechaOrdenesCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo6.setText("Añadir->");
        seleccionarModificarInfo6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(255, 255, 255));
        jLabel150.setText("Repuestos");

        seleccionarModificarInfo7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo7.setText("Seleccionar");
        seleccionarModificarInfo7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        seleccionarModificarInfo8.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo8.setText("Añadir->");
        seleccionarModificarInfo8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel151.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(255, 255, 255));
        jLabel151.setText("VISTA PREVIA");

        tablaOrdenesCrearVPMO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane52.setViewportView(tablaOrdenesCrearVPMO);

        seleccionarModificarInfo9.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo9.setText("<- Quitar");
        seleccionarModificarInfo9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel152.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(255, 255, 255));
        jLabel152.setText("Mano de obra agegada");

        jLabel154.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setText("Agregar Nota");

        ordenesCrearNota.setToolTipText("");
        ordenesCrearNota.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane2.setViewportView(ordenesCrearNota);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Repuestos agregados ");

        pHTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pHTrabajoActionPerformed(evt);
            }
        });

        jLabel155.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 255, 255));
        jLabel155.setText("Precio H.Trabajo");

        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 255, 255));
        jLabel156.setText("Total Repuestos");

        tRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRepuestosActionPerformed(evt);
            }
        });

        jLabel157.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(255, 255, 255));
        jLabel157.setText("Total Varios");

        jLabel158.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setText("Agregar Varios");

        ordenesCrearVarios.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane3.setViewportView(ordenesCrearVarios);

        seleccionarModificarInfo13.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo13.setText("<-Quitar");
        seleccionarModificarInfo13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel163.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(255, 255, 255));
        jLabel163.setText("Horas de trabajo");

        jLabel165.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setText("Total Mano Obra");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("   Cantidad");

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

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("/");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("/");

        contactosCrear4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear4.setText("Calcular");
        contactosCrear4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contactosCrear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear4ActionPerformed(evt);
            }
        });

        calcularRepuestos.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        calcularRepuestos.setText("Calcular");
        calcularRepuestos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        calcularRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestosActionPerformed(evt);
            }
        });

        contactosCrear5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear5.setText("Vista Previa");
        contactosCrear5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel166.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(255, 255, 255));
        jLabel166.setText("TOTAL: ");

        jLabel167.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("N° Orden: ");

        bFechaOrdenesCrear8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear8ActionPerformed(evt);
            }
        });

        calcularRepuestos1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        calcularRepuestos1.setText("Calcular");
        calcularRepuestos1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        calcularRepuestos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestos1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo10.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo10.setText("Seleccionar");
        seleccionarModificarInfo10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Embarcacion: ");

        javax.swing.GroupLayout CrearOrdenLayout = new javax.swing.GroupLayout(CrearOrden);
        CrearOrden.setLayout(CrearOrdenLayout);
        CrearOrdenLayout.setHorizontalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(seleccionarModificarInfo7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel31)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane51, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(cantidad))))
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel152)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(pHTrabajo)
                                            .addComponent(jLabel163, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel165, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tManoObra)
                                            .addComponent(hTrabajo)
                                            .addComponent(contactosCrear4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(CrearOrdenLayout.createSequentialGroup()
                                            .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tRepuestos)
                                                .addComponent(calcularRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel158))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))
                                            .addComponent(tVarios))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(seleccionarModificarInfo10, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel154)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(509, 509, 509)))
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel151)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel20))
                            .addComponent(jLabel167))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addComponent(contactosCrear5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contactosCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel166)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcularRepuestos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(622, 622, 622))
        );
        CrearOrdenLayout.setVerticalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel151)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel20)))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calcularRepuestos1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contactosCrear5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactosCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seleccionarModificarInfo7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel155)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pHTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel163)
                                        .addGap(1, 1, 1)
                                        .addComponent(hTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(contactosCrear4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel165))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel152, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel31)
                                                .addComponent(jLabel148)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                        .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(148, 148, 148))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jScrollPane52)
                                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(tManoObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(32, 32, 32)))
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel149)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel150))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(CrearOrdenLayout.createSequentialGroup()
                                            .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel156)
                                        .addGap(2, 2, 2)
                                        .addComponent(tRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(calcularRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel154))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addComponent(jLabel158)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tVarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarModificarInfo10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
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

        eliminarEliminar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        eliminarEliminar1.setText("Eliminar");
        eliminarEliminar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        seleccionBorrado1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        seleccionBorrado1.setText("Recuperar");
        seleccionBorrado1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                        .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionBorrado1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34))))
                .addContainerGap(1001, Short.MAX_VALUE))
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
                    .addComponent(seleccionBorrado1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jScrollPane18))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        eliminarEliminar1.getAccessibleContext().setAccessibleName("");
        seleccionBorrado1.getAccessibleContext().setAccessibleName("");

        editaraClientes1.addTab("Eliminar", EliminarOrden);

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 1765, Short.MAX_VALUE)
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 711, Short.MAX_VALUE)
        );

        Main.addTab("ORDENES", Ordenes);

        editaraClientes2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N

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
                .addContainerGap(101, Short.MAX_VALUE))
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
                .addContainerGap(902, Short.MAX_VALUE))
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
                .addContainerGap(521, Short.MAX_VALUE))
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
                .addContainerGap(570, Short.MAX_VALUE))
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
                .addGap(0, 103, Short.MAX_VALUE))
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
                                        .addGap(0, 501, Short.MAX_VALUE))
                                    .addComponent(estado12, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
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
                            .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
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
                .addGap(0, 667, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane40, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
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
                .addContainerGap(570, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane41, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
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
                .addContainerGap(562, Short.MAX_VALUE))
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
                        .addContainerGap(350, Short.MAX_VALUE))
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
                .addContainerGap(548, Short.MAX_VALUE))
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
                        .addContainerGap(333, Short.MAX_VALUE))))
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
                        .addGap(141, 349, Short.MAX_VALUE))))
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
                            .addComponent(estado11, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))))
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
                            .addComponent(jScrollPane48, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
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
            .addGap(0, 660, Short.MAX_VALUE)
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
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 1300, Short.MAX_VALUE)
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, Short.MAX_VALUE)
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
            this.TablaContactosVsualizar.setText("  ===================================\n  INFORMACION\n  ==================================="
                    +cliente.getInformacionClienteVisualizar()
                    +cliente.getInformacionEmbarcaciones());
            this.jLabel12.setText("Seleccionado: "+cliente.getApellidoNombre());
            cliente=null;
        }
    }//GEN-LAST:event_seleccionarVActionPerformed
    
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
        this.claveEmb1.setText("");
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
        this.numeroDeSerie.setText("");
        this.claveEmb.setText("");
    }
    public void vaciarBarrasR(){
        this.bRepuestosModNombre.setText("");
        this.bRepuestosModCodigo.setText("");
        this.bRepuestosModCantidad.setText("");
        this.bRepuestosModPrecio.setText("");
        this.bCorreoC2.setText("");
        this.bCelularC2.setText("");
        this.bTelFijoC2.setText("");
        this.bCuidadorC2.setText("");
    }
    public void vaciarBarrasO(){
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
        this.actualizarTXTManoObra();
        this.actualizarListadoManoObra();
        
        this.setearTablasListadoOrdenes();
        this.repaint();
    }
    public void updateVCliente(){
        this.vaciarTablasC();
        this.vaciarBarrasC();
        this.actualizarListadoContactos();
        this.actualizarListadoContactosEliminados();
        this.setearTablasListadoClientes();
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
         
            cliente.setNumCliente(this.hashmapClientes.size()+this.hashmapClientesEliminados.size());
            this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientesCodigo.put(Integer.toString(cliente.getNumCliente()),cliente.getApellidoNombre());
            //System.out.println("Creando: "+cliente.getApellidoNombre()+" Num: "+cliente.getNumCliente());
            this.crearArchivosContacto(cliente);
            cliente= null;
            jLabel40.setText("Estado: Cliente Creado");
            this.updateVCliente();
            this.updateVOrden();
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
            clienteEliminar=new Cliente();
            clienteEliminar=this.hashmapClientes.get(this.tablaContactosEliminar.getSelectedValue());
            //System.out.println("______________________________\n Seleccionando eliminar a: "+clienteEliminar.getNumCliente());
        }
        if(clienteEliminar!=null){
            this.hashmapClientesEliminados.put(clienteEliminar.getApellidoNombre(),clienteEliminar);
            this.hashmapClientes.remove(clienteEliminar.getApellidoNombre(),clienteEliminar);
            this.eliminarCliente=true;
            try {
                this.extraerArchivosCliente(clienteEliminar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.eliminarCliente=false;
            this.updateVCliente();
            clienteEliminar=null;
        }
    }//GEN-LAST:event_eliminarEliminarActionPerformed

    private void seleccionBorradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorradoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorradoMouseClicked

    private void seleccionBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorradoActionPerformed
        if(this.tablacontactosEliminados.getSelectedValue()!=null){
            clienteRecuperar=this.hashmapClientesEliminados.get(this.tablacontactosEliminados.getSelectedValue());
        }
        if(clienteRecuperar!=null){
            this.hashmapClientes.put(clienteRecuperar.getApellidoNombre(),clienteRecuperar);
            this.hashmapClientesEliminados.remove(clienteRecuperar.getApellidoNombre(),clienteRecuperar);
            this.recuperarCliente=true;
            try {
                this.extraerArchivosClienteEliminado(clienteRecuperar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.recuperarCliente=false;
            this.updateVCliente(); // TODO add your handling code here:
        }
        clienteRecuperar=null;
    }//GEN-LAST:event_seleccionBorradoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       this.vaciarBarrasC();
       this.jLabel13.setText("Seleccionado: ");
       cliente=null;
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if(bNombreE.getText().length()!=0 && bApellidoE.getText().length()!=0 && bCorreoE.getText().length()!=0 && bCelularE.getText().length()!=0 
            && bTelFijE.getText().length()!=0 && bCuidadorE.getText().length()!=0 && bCelCuidadorE.getText().length()!=0 && clienteModificar!=null){ 
            String apNombrAnterior=clienteModificar.getApellidoNombre();
            clienteModificar.setNombe(this.bNombreE.getText());
            clienteModificar.setApellido(this.bApellidoE.getText());
            clienteModificar.setCorreo(this.bCorreoE.getText());
            clienteModificar.setCelular(this.bCelularE.getText());
            clienteModificar.setTelFijo(this.bTelFijE.getText());
            clienteModificar.setCuidador(this.bCuidadorE.getText());
            clienteModificar.setCelularCuidador(this.bCelCuidadorE.getText());
            this.hashmapClientes.remove(apNombrAnterior);
            this.hashmapClientes.put(clienteModificar.getApellidoNombre(), clienteModificar);
            this.modificarCliente=true;
           try {
               this.extraerArchivosCliente(clienteModificar.getNumCliente());
           } catch (IOException ex) {
               Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
           }
            this.modificarCliente=false;
            this.updateVCliente();
            this.updateVOrden();
            this.updateVRepuesto();
       }
       clienteModificar=null;
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
          clienteModificar=this.hashmapClientes.get(this.tablaModificarInformacion.getSelectedValue());
          this.jLabel13.setText("Seleccionado: "+clienteModificar.getApellidoNombre());
      }    
      if(this.tablaModificarInformacion.getSelectedValue()!=null){
            this.bNombreE.setText(clienteModificar.getNombre());
            this.bApellidoE.setText(clienteModificar.getApellido());
            this.bCorreoE.setText(clienteModificar.getCorreo());
            this.bCelularE.setText(clienteModificar.getCelular());
            this.bTelFijE.setText(clienteModificar.getTelFijo());
            this.bTelFijoC6.setText(clienteModificar.getTelFijoOficina1());
            this.bTelFijoC5.setText(clienteModificar.getTelFijoOficina2());
            this.bCuidadorE.setText(clienteModificar.getCuidador());
            this.bCelCuidadorE.setText(clienteModificar.getCelularCuidador());
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
            cliente.eliminarEmbarcacion(embarcacion);
            embarcacion.setTipo(t.getText());
            embarcacion.setMarca(m.getText());
            embarcacion.setModelo(mo.getText());
            embarcacion.setMotor(mot.getText());
            embarcacion.setnSerie(nSerie1.getText());
            embarcacion.setClaveLancha(claveEmb1.getText());
            cliente.addEmbarcacion( embarcacion);
            cliente.crearTxTEmbarcacion(embarcacion);
            this.hashmapClientes.remove(cliente.getApellidoNombre());
            this.hashmapClientes.put(cliente.getApellidoNombre(), cliente);
            cliente.actualizarListadoEmbarcaciones();
            //this.updateVCliente();
            this.tablaEmbarcaciones.setListData(cliente.getListadoEmbarcaciones());
            this.vaciarBarrasC();
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
            embarcacion=cliente.hashMapEmbarcaciones.get(this.tablaEmbarcaciones.getSelectedValue());
            this.jLabel22.setText("Embarcacion: "+embarcacion.getMotor());
            this.t.setText(embarcacion.getTipo());
            this.m.setText(embarcacion.getMarca());
            this.mot.setText(embarcacion.getMotor());
            this.mo.setText(embarcacion.getModelo());
            this.nSerie1.setText(embarcacion.getnSerie());
            this.claveEmb1.setText(embarcacion.getClaveLancha());
            this.estado2.setText("Estado: Editando Embarcacion ");
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
            && numeroDeSerie.getText().length()!=0 && cliente!=null){     
            embarcacion=new Embarcacion();
            embarcacion.setTipo(this.tipo.getText());
            embarcacion.setMarca(this.marca.getText());
            embarcacion.setModelo(this.modelo.getText());
            embarcacion.setMotor(this.motor.getText());
            embarcacion.setnSerie(this.numeroDeSerie.getText());
            embarcacion.setCodigo(Integer.toString(cliente.listadoEmbarcaciones.size()));
            embarcacion.setClaveLancha(this.claveEmb.getText());
            cliente.addEmbarcacion(embarcacion);
            cliente.crearTxTEmbarcacion(embarcacion);
            //System.out.println("Desopues de añadir: "+cliente.getInformacionE(embarcacion.getTipoMarca())+"/n "+embarcacion.codigo);
            this.estado.setText("Lancha: "+embarcacion.getMotor()+" añadida exitosamente");
            this.updateVCliente();  
        }   
        this.vaciarBarrasC();
        this.seleccionadoAgregar1.setText("  Seleccionado: ");
        this.añadiendolanchaa.setText("  Añadiendo Lancha a: ");
        cliente=null;
    }//GEN-LAST:event_guardarLanchaActionPerformed

    private void numeroDeSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroDeSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroDeSerieActionPerformed

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
            embarcacion=cliente.hashMapEmbarcaciones.get(this.tablaEE.getSelectedValue());
            this.jLabel21.setText("Embarcacion: "+embarcacion.getTipoMarca());
        }  
    }//GEN-LAST:event_seleccionBorrado5ActionPerformed

    private void seleccionBorrado6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado6MouseClicked

    private void seleccionBorrado6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado6ActionPerformed
        if(this.embarcacion!=null){
            cliente.eliminarEmbarcacion(embarcacion);
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

    }//GEN-LAST:event_eliminarEliminar1ActionPerformed

    private void seleccionBorrado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado1MouseClicked

    private void seleccionBorrado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado1ActionPerformed

    }//GEN-LAST:event_seleccionBorrado1ActionPerformed

    private void seleccionarV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV3MouseClicked

    private void seleccionarV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV3ActionPerformed
        if(this.tablaOrdenesVisualizar.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablaOrdenesVisualizar.getSelectedValue());
            cliente.actualizarListadoOrdenes();
            this.tablaOrdenesVisualizar1.setListData(cliente.getListadoOrdenes());
            this.repaint();
            
            this.jLabel15.setText("Seleccionado: "+cliente.getApellidoNombre());
        }
    }//GEN-LAST:event_seleccionarV3ActionPerformed

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
            this.updateVOrden();
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
    public void actualizarOrdenManoObraAdd(){
        this.listadoOrdenesAñadirManoObra= new String[this.arrayListOrdenesAñadirManoObra.size()];
        String [] vacio = new String[1000];
        this.tablaOrdenesCrearVPMO.setListData(vacio);
        Collections.sort(Interfaz.this.arrayListOrdenesAñadirManoObra, String::compareTo);
        for(int g=0;g<this.arrayListOrdenesAñadirManoObra.size();g++){
            this.listadoOrdenesAñadirManoObra[g]=this.arrayListOrdenesAñadirManoObra.get(g);
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

    private void bCelCuidadorCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCelCuidadorCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCelCuidadorCActionPerformed

    private void claveEmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveEmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claveEmbActionPerformed

    private void claveEmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveEmb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claveEmb1ActionPerformed

    private void seleccionarV4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV4MouseClicked

    private void seleccionarV4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV4ActionPerformed
        
        orden=cliente.hashMapOrdenes.get(this.tablaOrdenesVisualizar1.getSelectedValue());
        jLabel37.setText("Orden Seleccionada: "+orden.getOrdenID());
        this.visualizarV1.setText(orden.getInformacion());
       repaint();
    }//GEN-LAST:event_seleccionarV4ActionPerformed

    private void seleccionarModificarInfo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo10ActionPerformed
        if(cliente!=null && this.tablaOrdenesCrear2.getSelectedValue()!=null){
            embarcacion=cliente.hashMapEmbarcaciones.get(this.tablaOrdenesCrear2.getSelectedValue());
            this.jLabel36.setText("Embarcacion: "+embarcacion.getTipoMarca());

        }
    }//GEN-LAST:event_seleccionarModificarInfo10ActionPerformed

    private void seleccionarModificarInfo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo10MouseClicked

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

    private void bFechaOrdenesCrear8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFechaOrdenesCrear8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bFechaOrdenesCrear8ActionPerformed

    private void tTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTotalActionPerformed

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
            orden.setEmbrcacion(embarcacion.getTipoMarca());
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
                repuesto.setPrecio(Integer.toString((int)(repuesto.cantidad*repuesto.precio)));
                orden.addRepuesto(repuesto);
            }
            this.visualizarV4.setText(orden.getInformacion());
            //System.out.println(orden.getInformacion());
            //this.hashmapOrdenes.put(orden.getNumeroOrden(),orden);
        }
    }//GEN-LAST:event_contactosCrear5ActionPerformed

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

    private void contactosCrear4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear4ActionPerformed
        if(this.isNumericDouble(this.pHTrabajo.getText())==true && this.isNumericDouble(this.hTrabajo.getText())==true){
            Double tHT=Double.parseDouble(this.pHTrabajo.getText())*Double.parseDouble(this.hTrabajo.getText());
            this.tManoObra.setText(Integer.toString(tHT.intValue()));
        }
    }//GEN-LAST:event_contactosCrear4ActionPerformed

    private void añoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_añoActionPerformed

    private void mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mesActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void tVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tVariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tVariosActionPerformed

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

    private void seleccionarModificarInfo13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo13MouseClicked

    private void tRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRepuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRepuestosActionPerformed

    private void pHTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pHTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pHTrabajoActionPerformed

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

    private void seleccionarModificarInfo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo9MouseClicked

    private void seleccionarModificarInfo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8ActionPerformed
        //Double cant=Double.parseDouble(this.cantidad.getText());
        if(this.tablaOdenCrearAñadirRepuestos.getSelectedValue()!=null && this.isNumericDouble(this.cantidad.getText())==true){
            this.repuesto=new Repuesto();
            this.repuesto=this.hashmapRepuestos.get(this.tablaOdenCrearAñadirRepuestos.getSelectedValue());
            repuesto.setCantidad(this.cantidad.getText());
            this.arrayListOrdenesAñadirRepuestosCliente.add(repuesto);
            this.actualizarOrdenRepuestosAgregados();
            this.cantidad.setText("");
        }
    }//GEN-LAST:event_seleccionarModificarInfo8ActionPerformed

    private void seleccionarModificarInfo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo8MouseClicked

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

    private void bFechaOrdenesCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFechaOrdenesCrear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bFechaOrdenesCrear1ActionPerformed

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
            orden.setEmbrcacion(embarcacion.getTipoMarca());
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
                //System.out.println("Añadiendo: "+Integer.toString((int)(repuesto.cantidad*repuesto.precio)));
                //repuesto.setPrecio(Integer.toString((int)(repuesto.cantidad*repuesto.precio)));
                orden.addRepuesto(repuesto);
            }
            this.visualizarV4.setText(orden.getInformacion());
            //System.out.println(orden.getInformacion());
            //this.hashmapOrdenes.put(orden.getNumeroOrden(),orden);
            // orden.setNumTxT(Integer.toString(cliente.ordenes.size()));
            cliente.crearTxTOrdenes(orden);
            cliente.addOrden(orden);
            this.updateVOrden();
        }
    }//GEN-LAST:event_contactosCrear1ActionPerformed

    private void diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaActionPerformed
 
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clientes;
    private javax.swing.JPanel CrearOrden;
    private javax.swing.JPanel EliminarOrden;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Guarderias;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel ModificarC;
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
    private javax.swing.JTextField claveEmb;
    private javax.swing.JTextField claveEmb1;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
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
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
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
    private javax.swing.JScrollPane jScrollPane20;
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
    private javax.swing.JTextField nSerie1;
    private javax.swing.JTextField nSerie6;
    private javax.swing.JTextField nSerie7;
    private javax.swing.JTextField numeroDeSerie;
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
    private javax.swing.JButton seleccionarV3;
    private javax.swing.JButton seleccionarV4;
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
    public javax.swing.JList<String> tablaOrdenesVisualizar1;
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
