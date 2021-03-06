package basedatos;

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
import javax.swing.JOptionPane;
/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    
    int numCliente=0;
    Embarcacion embarcacion= new Embarcacion();
    
    Distribuidor distribuidor= new Distribuidor();
    MisContactos misContactos=new MisContactos();
    
    //CLIENTES
    Cliente cliente= new Cliente();
    Cliente clienteModificar= new Cliente();
    Cliente clienteEliminar= new Cliente();
    Cliente clienteRecuperar= new Cliente();

    Cliente clienteCargar= new Cliente();
    String clienteLoad="";
    
    //DISTRIBUIDORES
    Distribuidor distribuidorCargar= new Distribuidor();
    Distribuidor distribuidorModificar= new Distribuidor();
    Distribuidor distribuidorEliminar= new Distribuidor();
    Distribuidor distribuidorRecuperar= new Distribuidor();
    String distribuidorLoad="";
    
    Orden orden= new Orden();
    Orden ordenAux=new Orden();
    Repuesto repuesto= new Repuesto();
    Guarderia guarderia=new Guarderia();
    HashMap<String ,String> hashmapClientesCodigo= new HashMap<>();
    
    
    //DISTRIBUIDORES
    HashMap<String ,Distribuidor> hashmapDistribuidores= new HashMap<>();
    ArrayList<String> arrayListDistribuidores=new ArrayList<>();
    String [] listadoDistribuidores = new String[1000];
    //DISTRIBUIDORES ELIMINADOS
    HashMap<String ,Distribuidor> hashmapDistribuidoresEliminados= new HashMap<>();
    ArrayList<String> arrayListDistribuidoresEliminados=new ArrayList<>();
    String [] listadoDistribuidoresEliminados = new String[1000];
    
    //CLIENTES
    HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
    ArrayList<String> arrayListContactos=new ArrayList<>();
    String [] listadoClientes = new String[1000];
    //CLIENTES ELIMINADOS
    HashMap<String ,Cliente> hashmapClientesEliminados= new HashMap<>();
    ArrayList<String> arrayListContactosEliminados=new ArrayList<>();
    String [] listadoClientesEliminados = new String[1];	
    
    //ORDENES
    HashMap<String ,Orden> hashmapOrdenesEliminadas= new HashMap<>();
    ArrayList<String> arrayListOrdenesEliminadas=new ArrayList<>();
    String [] listadoOrdenesEliminadas = new String[1];
    String [] listadoClientesDeudaOrden = new String[1];
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
    //REPUESTOS ELIMINADOS
    HashMap<String ,Repuesto> hashmapRepuestosEliminados= new HashMap<>();
    ArrayList<String> arrayListRepuestosEliminados=new ArrayList<>();
    String [] listadoRepuestosEliminados = new String[1];
    
    //Mano obra
    ArrayList<String> arrayListManoObra=new ArrayList<>();
    String [] listadoManoObra = new String[1];
     //Embarcaciones
    boolean ordenC = false;
    boolean ordenNC = false;
    
    boolean cargarCliente=false;
    boolean cargarClienteEliminado=false; 
    boolean modificarCliente=false;
    boolean eliminarCliente=false;
    boolean recuperarCliente=false;
    
    boolean cargarDistribuidor=false;
    boolean cargarDistribuidorEliminado=false;
    boolean modificarDistribuidor=false;
    boolean eliminarDistribuidor=false;
    boolean recuperarDistribuidor=false;
    
    boolean selected=false; 
  
    int totalRepuestos=0,totalManoObra=0;
    
     public Interfaz() throws IOException {
         
        this.setVisible(true);
        this.initComponents();
        //this.setearFondos();
        File carpetaContactos = new File("Contactos");
        
        File carpetaClientes = new File("Contactos/Clientes");
        File carpetaClientesActivos = new File("Contactos/Clientes/ClientesActivos");
        File carpetaClientesEliminados = new File("Contactos/Clientes/ClientesEliminados");
        
        File carpetaMisContactos = new File("Contactos/MisContactos");
        File carpetaMisContactosActivos = new File("Contactos/MisContactos/MisContactosActivos");
        File carpetaMisContactosEliminados = new File("Contactos/MisContactos/MisContactosEliminados");
        
        File carpetaDistribuidores = new File("Contactos/Distribuidores");
        File carpetaDistribuidoresActivos = new File("Contactos/Distribuidores/DistribuidoresActivos");
        File disttribuidoresEliinados = new File("Contactos/Distribuidores/DistribuidoresEliminados");
        
        File carpetaRepuestos = new File("Repuestos");
        
        
        carpetaContactos.mkdirs();
        carpetaClientes.mkdirs();
        carpetaClientesActivos.mkdirs();
        carpetaClientesEliminados.mkdirs();
        
        carpetaMisContactos.mkdirs();
        carpetaMisContactosActivos.mkdirs();
        carpetaMisContactosEliminados.mkdirs();
        
        carpetaDistribuidores.mkdirs();
        carpetaDistribuidoresActivos.mkdirs();
        disttribuidoresEliinados.mkdirs();
        
        carpetaRepuestos.mkdirs();
        
        
        
        File crearTxT_Rep = new File("Repuestos/Repuestos.txt");
        crearTxT_Rep.createNewFile();
        
        
        this.cargarRepuestos();
        this.updateVRepuesto();
        
        this.cargarDatosClientes();
        this.cargarDatosClientesEliminados();
        System.out.println("3"+arrayListContactosEliminados.toString());
        
        this.cargarDatosDistribuidores();
        System.out.println("4"+arrayListContactosEliminados.toString());
        this.cargarDatosDistribuidoresEliminados();
        System.out.println("5"+arrayListContactosEliminados.toString());
        
        this.obtenerClientesConDeuda();
        this.cargarManoObra();
    
        this.updateVCliente();
       
        this.updateVOrden();  
        this.updateVDistribuidor();
        
        //guarderia=new Guarderia();
        //guarderia.agregarLanchaAGuarderia("Yamaha", "300000");
        //this.añadirGuarderia("Gamboa Felipe");
    }
    public void cargarDatosClientes() {
        this.hashmapClientes = new HashMap<>();
        this.arrayListContactos= new ArrayList<>();
        final File carpeta = new File("Contactos/Clientes/ClientesActivos");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarCliente=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarCarpetaEmbarcacion(ficheroEntrada);
               this.cargarCarpetaOrdenes(ficheroEntrada);
               this.cargarCliente=false;
            }
        }
        this.updateVCliente();
    }
    public void cargarDatosDistribuidores() {
        this.hashmapDistribuidores = new HashMap<>();
        this.arrayListDistribuidores= new ArrayList<>();
        final File carpeta = new File("Contactos/Distribuidores/DistribuidoresActivos");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarDistribuidor=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               //System.out.println("___________: "+ this.distribuidorLoad+" "+distribuidorCargar.getApellidoNombre());
               
               this.cargarDistribuidor=false;
            }
        }
        this.updateVDistribuidor();
    }
    @SuppressWarnings("empty-statement")
    public void obtenerClientesConDeuda(){
        ArrayList<String> al=new ArrayList();
        for (Map.Entry<String, Cliente> entry : hashmapClientes.entrySet()) {
            if(entry.getValue().deudaOrden==true){
                al.add(entry.getValue().getApellidoNombre());
                //System.out.println(entry.getValue().getApellidoNombre());
            }
        }
        this.listadoClientesDeudaOrden=new String[al.size()];
        for(int g=0;g<al.size();g++){
           this.listadoClientesDeudaOrden[g]= al.get(g);
        }
        
    }
    public void cargarDatosClientesEliminados() {
        this.hashmapClientesEliminados = new HashMap<>();
        this.arrayListContactosEliminados= new ArrayList<>();
        final File carpeta = new File("Contactos/Clientes/ClientesEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarClienteEliminado=true;
               System.out.print("!!!!!!!!!!!!!!!11");
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarCarpetaEmbarcacion(ficheroEntrada);
               this.cargarCarpetaOrdenes(ficheroEntrada);
               this.cargarClienteEliminado=false;
            }
        }
        //System.out.println("1"+arrayListContactosEliminados.toString());
        this.actualizarListadoContactosEliminados();
        //System.out.println("2"+arrayListContactosEliminados.toString());
        this.updateVCliente();
        
    }
    public void cargarDatosDistribuidoresEliminados() {
        this.hashmapDistribuidoresEliminados = new HashMap<>();
        this.arrayListDistribuidoresEliminados= new ArrayList<>();
        final File carpeta = new File("Contactos/Distribuidores/DistribuidoresEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
               this.cargarDistribuidorEliminado=true;
               this.cargarCarpetaInformacion(ficheroEntrada);
               this.cargarDistribuidorEliminado=false;
            }
        }
        this.actualizarListadoDistribuidoresEliminados();
        this.updateVDistribuidor();
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
    public void moverFicherosCliente(File file) throws IOException{
        final File carpeta = file;
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if(this.eliminarCliente==true){
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Informacion";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Informacion/"+doctxt.getName();
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
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Ordenes";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Ordenes";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Ordenes/"+doctxt.getName();
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
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Guarderia";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Guarderia";
                    this.moverCarpetaOArchivo(ori,dest);
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Guarderia");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Embarcaciones") ) {
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Embarcaciones";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Embarcaciones";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
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
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Informacion";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Informacion/"+doctxt.getName();
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
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Ordenes";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Ordenes";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Ordenes/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Ordenes/"+doctxt.getName();
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
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Guarderia";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Guarderia";
                    this.moverCarpetaOArchivo(dest,ori);
                    //this.moverCarpeta();
                    //this.moverCarpeta("Clientes/"+ficheroEntrada.getName(), "ClientesEliminados/"+ficheroEntrada.getName());
                    //System.out.println("ClientesEliminados/"+file.getName()+"/Guarderia");
                    ficheroEntrada.delete();
                }
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Embarcaciones") ) {
                    String dest="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Embarcaciones";
                    String ori="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Embarcaciones";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Clientes/ClientesEliminados/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
                            String oritxt="Contactos/Clientes/ClientesActivos/"+file.getName()+"/Embarcaciones/"+doctxt.getName();
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
    public void moverFicherosDistribuidor(File file) throws IOException{
        final File carpeta = file;
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if(this.eliminarDistribuidor==true){
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                    String dest="Contactos/Distribuidores/DistribuidoresEliminados/"+file.getName()+"/Informacion";
                    String ori="Contactos/Distribuidores/DistribuidoresActivos/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(ori,dest);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Distribuidores/DistribuidoresEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Contactos/Distribuidores/DistribuidoresActivos/"+file.getName()+"/Informacion/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(oritxt,destxt);
                            doctxt.delete();
                        }
                    }
                    ficheroEntrada.delete();
                    //this.moverCarpetaOArchivo("Clientes/"+file.getName()+"/"+ficheroEntrada.getName(), "ClientesEliminados/"+file.getName()+"/"+ficheroEntrada.getName());
                    //System.out.println(ori+"\n"+dest);
                }
            }
            if(this.recuperarDistribuidor==true){
                System.out.println("sssssssssssssssssssssssssssss");
                if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals("Informacion") ) {
                    String dest="Contactos/Distribuidores/DistribuidoresEliminados/"+file.getName()+"/Informacion";
                    String ori="Contactos/Distribuidores/DistribuidoresActivos/"+file.getName()+"/Informacion";
                    this.moverCarpetaOArchivo(dest,ori);
                    final File txt = ficheroEntrada;
                    for (final File doctxt : txt.listFiles()) {
                        if (doctxt.isFile()){
                            String destxt="Contactos/Distribuidores/DistribuidoresEliminados/"+file.getName()+"/Informacion/"+doctxt.getName();
                            String oritxt="Contactos/Distribuidores/DistribuidoresActivos/"+file.getName()+"/Informacion/"+doctxt.getName();
                            //System.out.println("*"+oritxt+"\n*"+destxt);
                            this.moverCarpetaOArchivo(destxt,oritxt);
                            doctxt.delete();
                        }
                    }
                    ficheroEntrada.delete();
                    //this.moverCarpetaOArchivo("Clientes/"+file.getName()+"/"+ficheroEntrada.getName(), "ClientesEliminados/"+file.getName()+"/"+ficheroEntrada.getName());
                    //System.out.println(ori+"\n"+dest);
                }
            }
        }
    }
    public void moverCarpetasCliente(Integer num) throws IOException {
        System.out.println("Extrallendo archivos cliente: "+this.recuperarCliente+" num: "+num);
        if(recuperarCliente==true){
            final File carpeta = new File("Contactos/Clientes/ClientesEliminados");
            moveFolder(carpeta,num);
        }
        else{
            final File carpeta = new File("Contactos/Clientes/ClientesActivos");
             moveFolder(carpeta,num);
        }
    }
    public void moveFolder(File carpeta, Integer num) throws IOException{
        for (final File ficheroEntrada : carpeta.listFiles()) {
            System.out.println("Eliminando: "+ficheroEntrada.getName());
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(num.toString())) {
               System.out.println("MATCH: "+ficheroEntrada.getName());
               if(eliminarCliente==true){
                //System.out.println("Eliminando: "+ficheroEntrada.getAbsolutePath());
                String dest="Contactos/Clientes/ClientesEliminados/"+ficheroEntrada.getName();
                String ori="Contactos/Clientes/ClientesActivos/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(ori,dest);
                this.moverFicherosCliente(ficheroEntrada);
                ficheroEntrada.delete();
               }
               if(this.cargarCliente==true ){
                this.cargarCarpetaInformacion(ficheroEntrada);
                this.cargarCliente=false;
               }
                if(this.modificarCliente==true ){
                    //System.out.println("MODIFICANDO");
                this.modificarInformacionCliente(); 
                this.modificarCliente=false;
               }
               if(recuperarCliente==true){
                System.out.println("Recuperando a: "+ficheroEntrada.getName());
                String dest="Contactos/Clientes/ClientesEliminados/"+ficheroEntrada.getName();
                String ori="Contactos/Clientes/ClientesActivos/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(dest,ori);
                this.moverFicherosCliente(ficheroEntrada);
                ficheroEntrada.delete();
                this.recuperarCliente=false;
               }
            }
        }
    }
    public void moverCarpetasDistribuidor(Integer num) throws IOException {
        //System.out.println("******: - "+num);
        final File carpeta = new File("Contactos/Distribuidores/DistribuidoresEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            //System.out.println("******:  - "+ficheroEntrada.getName());
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(num.toString())) {
               //System.out.println("MATCH: "+ficheroEntrada.getName());
               if(eliminarDistribuidor==true){
                //System.out.println("Eliminando: "+ficheroEntrada.getAbsolutePath());
                String dest="Contactos/Distribuidores/DistribuidoresEliminados/"+ficheroEntrada.getName();
                String  ori="Contactos/Distribuidores/DistribuidoresActivos/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(ori,dest);
                this.moverFicherosDistribuidor(ficheroEntrada);
                ficheroEntrada.delete();
               }
               if(this.cargarDistribuidor==true ){
                this.cargarCarpetaInformacion(ficheroEntrada);
               }
                if(this.modificarDistribuidor==true ){
                    //System.out.println("MODIFICANDO");
                this.modificarInformacionCliente();
               }
                if(recuperarDistribuidor==true){
               //System.out.println("**********************____**");
                String dest="Contactos/Distribuidores/DistribuidoresEliminados/"+ficheroEntrada.getName();
                String ori="Contactos/Distribuidores/DistribuidoresActivos/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(dest,ori);
                this.moverFicherosDistribuidor(ficheroEntrada);
                ficheroEntrada.delete();
                this.recuperarCliente=false;
               }
            }
        }
    }
    public void extraerArchivosClienteEliminado(Integer num) throws IOException {
        final File carpeta = new File("Contactos/Clientes/ClientesEliminados");
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory() && ficheroEntrada.getName().equals(num.toString())) {
               if(recuperarCliente==true){
                //System.out.println("Recuperando a: "+ficheroEntrada.getName());
                String dest="Contactos/Clientes/ClientesEliminados/"+ficheroEntrada.getName();
                String ori="Contactos/Clientes/ClientesActivos/"+ficheroEntrada.getName();
                this.moverCarpetaOArchivo(dest,ori);
                this.moverFicherosCliente(ficheroEntrada);
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
                this.gestionarTxTInformacionContactos(ficheroEntrada);
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
            File crear_TxTInfo = new File("Contactos/Clientes/ClientesActivos/"+clienteModificar.getNumCliente()+"/Informacion/Info.txt");
            try{
            crear_TxTInfo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.actualizarTXTInfoContacto(crear_TxTInfo,clienteModificar);
              
            }
       public void modificarInformacionDistribuidor() {
            //System.out.println("Modificando info cliente"+cliente.getNumCliente());
            File crear_TxTInfo = new File("Contactos/Distribuidores/DistribuidoresActivos/"+distribuidorModificar.getNumCliente()+"/Informacion/Info.txt");
            try{
            crear_TxTInfo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.actualizarTXTInfoDistribuidor(crear_TxTInfo,distribuidorModificar);
               this.distribuidorModificar=null;
              
            }
       
    public void gestionarTxTInformacionContactos(File carpetainfo) {
        for (final File ficheroEntrada : carpetainfo.listFiles()) {
            //CLIENTES
            if (ficheroEntrada.isFile() && this.cargarCliente==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("modificar=False: "+ficheroEntrada);
               this.cargarCliente(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.cargarClienteEliminado==true && ficheroEntrada.getName().equals("Info.txt")) {
               System.out.println("CARGANDO ELIMINADO: "+ficheroEntrada);
               this.cargarCliente(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.modificarCliente==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("Modificar=True"+ficheroEntrada);
               ficheroEntrada.delete();
               this.modificarInformacionCliente();
            }
            
            //DISTRIBUIDORES
            if (ficheroEntrada.isFile() && this.cargarDistribuidor==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("modificar=False: "+ficheroEntrada);
               this.cargarDistribuidor(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.cargarDistribuidorEliminado==true && ficheroEntrada.getName().equals("Info.txt")) {
               //System.out.println("modificar=False: "+ficheroEntrada);
               this.cargarDistribuidor(ficheroEntrada);
            }
            if (ficheroEntrada.isFile() && this.modificarDistribuidor==true && ficheroEntrada.getName().equals("Info.txt")) {
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
    public void cargarTXTInfo(File doc){
        try {
            File file = doc;
            Cliente cl;
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
        public void actualizarTXTInfoDistribuidor(File info, Distribuidor dist){
          try {
            File file = info;
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                        Distribuidor di=dist;
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<di.getInformacionDistribuidor().size();l++){
                            bw.write((String) di.getInformacionDistribuidor().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                        bw.close();
              }
        } catch (IOException e) {
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
            String ruta = "Repuestos/Repuestos.txt";
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
        String dir="Contactos/Clientes/ClientesActivos/"+cliente.getNumCliente();
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
    public void crearArchivosDistribuidor( Distribuidor dist){
        String dir="Contactos/Distribuidores/DistribuidoresActivos/"+dist.getNumCliente();
        File crear_CC = new File(dir);
        File crear_CI = new File(dir+"/Informacion"); 
        File crear_TxTInfo = new File(dir+"/Informacion/Info.txt");
        
        
        crear_CC.mkdirs();
        crear_CI.mkdirs();
        
        try{
            crear_TxTInfo.createNewFile();
            
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.actualizarTXTInfoDistribuidor(crear_TxTInfo,distribuidor);
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
                 cliente.setDeudaOrdenSi();
                }
                 if(this.cargarClienteEliminado==true){
                 cliente=this.hashmapClientesEliminados.get(clienteLoad);
                 //System.out.println("AÑADIENDO LANCHA A : "+clienteLoadEmbarcacion);
                 cliente.addOrden(orden);
                 cliente.setDeudaOrdenSi();
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
                        embLoad.setHoras(linea);
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
    public void cargarDistribuidor(File f){
        //System.out.println("CARGANDO");
            BufferedReader entrada = null; 
            try { 
            entrada = new BufferedReader( new FileReader( f ) ); 
            String linea;
            Distribuidor dis= new Distribuidor();
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        linea = entrada.readLine(); 
                        dis.setNumCliente(Integer.parseInt(linea));
                        linea = entrada.readLine(); 
                        dis.setNombre(linea);
                        linea = entrada.readLine(); 
                        dis.setApellido(linea);
                        linea = entrada.readLine(); 
                        dis.setCelular(linea);
                        linea = entrada.readLine(); 
                        dis.setTelFijo(linea);
                        linea = entrada.readLine(); 
                        dis.setTelFijoOficina1(linea);
                        linea = entrada.readLine(); 
                        dis.setTelFijoOficina2(linea);
                        linea = entrada.readLine(); 
                        dis.setCorreo(linea);
                        linea = entrada.readLine(); 
                        dis.setEmpresa(linea);
                        linea = entrada.readLine(); 
                        dis.setCuentaDeposito(linea);
                        linea = entrada.readLine(); 
                    }
                    if(this.cargarDistribuidor==true){
                        distribuidorLoad=dis.getApellidoNombre();
                        this.hashmapDistribuidores.put(dis.getApellidoNombre(),dis);
                        this.arrayListDistribuidores.add(dis.getApellidoNombre());
                        this.actualizarListadoDistribuidores();
                    }
                    if(this.recuperarDistribuidor==true){
                         this.hashmapDistribuidores.put(dis.getApellidoNombre(),dis);
                         this.arrayListDistribuidores.add(dis.getApellidoNombre());
                     }
                    if(this.cargarDistribuidorEliminado==true){
                        distribuidorLoad=dis.getApellidoNombre();
                        this.hashmapDistribuidoresEliminados.put(dis.getApellidoNombre(),dis);
                        this.arrayListDistribuidoresEliminados.add(dis.getApellidoNombre());
                        this.actualizarListadoDistribuidoresEliminados();
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
            File f = new File( "Repuestos/Repuestos.txt" ); 
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
    
    public void cargarTXTEmbarcciones(String direccion){
        final File carpeta = new File("Clientes/ClientesActivos/Embarcaciones");
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
        Collections.sort(Interfaz.this.arrayListContactos);
        listadoClientes=new String[this.arrayListContactos.size()];
        for (int g=0;g<this.arrayListContactos.size();g++) {
            listadoClientes[g]=this.arrayListContactos.get(g);
        }
        //System.out.println("Contactos Existentes: "+this.arrayListContactos.toString());
    }
     public void actualizarListadoDistribuidores(){
        this.arrayListDistribuidores = new  ArrayList<>();
        for (Map.Entry<String, Distribuidor> entry : hashmapDistribuidores.entrySet()) {
            this.arrayListDistribuidores.add(entry.getValue().getApellidoNombre());
            
        }
        Collections.sort(Interfaz.this.arrayListDistribuidores);
        listadoDistribuidores=new String[this.arrayListDistribuidores.size()];
        for (int g=0;g<this.arrayListDistribuidores.size();g++) {
            listadoDistribuidores[g]=this.arrayListDistribuidores.get(g);
        }
        //System.out.println("Distribuidores Existentes: "+this.arrayListDistribuidores.toString());
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
         System.out.println("Contactos Eliminados:"+this.arrayListContactosEliminados.toString());
    }
        public void actualizarListadoDistribuidoresEliminados(){
        this.arrayListDistribuidoresEliminados= new  ArrayList<>();
        for (Map.Entry<String, Distribuidor> entry : hashmapDistribuidoresEliminados.entrySet()) {
            this.arrayListDistribuidoresEliminados.add(entry.getValue().getApellidoNombre());
        }
        Collections.sort(Interfaz.this.arrayListDistribuidoresEliminados, String::compareTo);
        listadoDistribuidoresEliminados=new String[this.arrayListDistribuidoresEliminados.size()];
        for (int g=0;g<this.arrayListDistribuidoresEliminados.size();g++) {
            listadoDistribuidoresEliminados[g]=this.arrayListDistribuidoresEliminados.get(g);
        }
         //System.out.println("Distribuidores Eliminados:"+this.arrayListDistribuidoresEliminados.toString());
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
    public void setearTablasListadoDistribuidores(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablacontactosEliminados2.setListData(listadoDistribuidoresEliminados);
        this.tablacontactosVisualizar1.setListData(listadoDistribuidores);
        this.tablaModificarInformacion1.setListData(listadoDistribuidores);
        this.tablaContactosEliminar1.setListData(listadoDistribuidores);
    }
    public void setearTablasListadoRepuestos(){
        this.tablaRepuestosVisualizar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminar.setListData(listadoRepuestos);
        this.tablaRepuestosEliminados.setListData(listadoRepuestosEliminados);
        this.tablaRepuestosModificar.setListData(listadoRepuestos);
    }
    public void setearTablasListadoOrdenes(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablaOrdenesVisualizar2.setListData(listadoClientesDeudaOrden);
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
        visualizarV4.setText(" ");
        this.tablaOrdenesCrearVPRepuestos.setListData(vacio);
        this.tablaOrdenesVisualizar.setListData(vacio);
        this.tablacontactosEliminar1.setListData(vacio);
        this.tablaOdenCrearAñadirRepuestos.setListData(vacio);
        this.tablaOrdenesCrearVPMO.setListData(vacio);
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
     public void vaciarTablasD(){
        String [] vacio = new String[1];
        this.tablacontactosVisualizar1.setListData (vacio);
        this.tablaModificarInformacion1.setListData(vacio);
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

        Fondo = new FondoPanel("6.jpg");
        Main = new javax.swing.JTabbedPane();
        Clientes2 = new FondoPanel("6.jpg");
        editaraClientes5 = new javax.swing.JTabbedPane();
        Clientes = new FondoPanel("7.jpg");
        editaraClientes = new javax.swing.JTabbedPane();
        visualizar = new FondoPanel("6.jpg");
        jScrollPane8 = new javax.swing.JScrollPane();
        tablacontactosVisualizar = new javax.swing.JList<>();
        seleccionarV = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TablaContactosVsualizar = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        crear = new FondoPanel("7.jpg");
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
        eliminar = new FondoPanel("7.jpg");
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
        ModificarC = new FondoPanel("7.jpg");
        info = new FondoPanel("7.jpg");
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
        lanch = new FondoPanel("6.jpg");
        eliminarLanchaa = new javax.swing.JTabbedPane();
        agregarLancha = new FondoPanel("7.jpg");
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
        horasAE = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        modificarLancha = new FondoPanel("7.jpg");
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
        añadiendolanchaa1 = new javax.swing.JLabel();
        horasAE1 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        modificarLancha1 = new FondoPanel("7.jpg");
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
        Distribuidores = new FondoPanel("6.jpg");
        editaraClientes4 = new javax.swing.JTabbedPane();
        visualizar1 = new FondoPanel("7.jpg");
        jScrollPane25 = new javax.swing.JScrollPane();
        tablacontactosVisualizar1 = new javax.swing.JList<>();
        seleccionarV1 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        TablaContactosVsualizar1 = new javax.swing.JTextArea();
        jLabel53 = new javax.swing.JLabel();
        crear1 = new FondoPanel("7.jpg");
        jLabel54 = new javax.swing.JLabel();
        bNombreC1 = new javax.swing.JTextField();
        bApellidoC1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        bCelularC1 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        bTelFijoC7 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        bCorreoC1 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        bEmpresaC1 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        bNumCuentaC1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        contactosCrear6 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        bTelFijoC8 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        bTelFijoC9 = new javax.swing.JTextField();
        eliminar1 = new FondoPanel("7.jpg");
        jLabel87 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaContactosEliminar1 = new javax.swing.JList<>();
        eliminarEliminar4 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tablacontactosEliminados2 = new javax.swing.JList<>();
        seleccionBorrado7 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        ModificarC1 = new FondoPanel("7.jpg");
        info1 = new FondoPanel("7.jpg");
        jLabel90 = new javax.swing.JLabel();
        bNombreE1 = new javax.swing.JTextField();
        bApellidoE1 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        bCelularE1 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        bTelFijE1 = new javax.swing.JTextField();
        bCorreoE1 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        bEmpresaE1 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        nCuentaDistribuidor = new javax.swing.JLabel();
        bNumCuentaDistribuidorE1 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        seleccionarModificarInfo1 = new javax.swing.JButton();
        jTextField21 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jScrollPane33 = new javax.swing.JScrollPane();
        tablaModificarInformacion1 = new javax.swing.JList<>();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        bTelFijoC10 = new javax.swing.JTextField();
        bTelFijoC11 = new javax.swing.JTextField();
        Ordenes = new FondoPanel("6.jpg");
        editaraClientes1 = new javax.swing.JTabbedPane();
        VisualizarOrden = new FondoPanel("7.jpg");
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
        OrdInpagas = new FondoPanel("7.jpg");
        jScrollPane21 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar2 = new javax.swing.JList<>();
        jLabel38 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        visualizarV5 = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        seleccionarV7 = new javax.swing.JButton();
        jScrollPane23 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar3 = new javax.swing.JList<>();
        jLabel49 = new javax.swing.JLabel();
        seleccionarV8 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        seleccionarV9 = new javax.swing.JButton();
        seleccionarV10 = new javax.swing.JButton();
        seleccionarV11 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tablaOrdenesVisualizar4 = new javax.swing.JList<>();
        seleccionarV12 = new javax.swing.JButton();
        seleccionarV13 = new javax.swing.JButton();
        CrearOrden = new FondoPanel("7.jpg");
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
        EliminarOrden = new FondoPanel("7.jpg");
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
        Repuestos =  new FondoPanel("6.jpg");
        editaraClientes2 = new javax.swing.JTabbedPane();
        visualizar2 =  new FondoPanel("7.jpg");
        jScrollPane27 = new javax.swing.JScrollPane();
        tablaRepuestosVisualizar = new javax.swing.JList<>();
        jLabel77 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        visualizarV2 = new javax.swing.JTextArea();
        jLabel78 = new javax.swing.JLabel();
        seleccionarV5 = new javax.swing.JButton();
        crear2 =  new FondoPanel("7.jpg");
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
        eliminar2 =  new FondoPanel("7.jpg");
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
        modificarLancha8 =  new FondoPanel("7.jpg");
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
        Guarderias = new FondoPanel("6.jpg");
        editaraClientes3 = new javax.swing.JTabbedPane();
        visualizar3 = new FondoPanel("7.jpg");
        jScrollPane38 = new javax.swing.JScrollPane();
        tablaOrdenesV2 = new javax.swing.JList<>();
        jLabel112 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jButton46 = new javax.swing.JButton();
        jScrollPane39 = new javax.swing.JScrollPane();
        visualizarV3 = new javax.swing.JTextArea();
        jLabel113 = new javax.swing.JLabel();
        seleccionarV6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servinautica Vichuquén");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 782));

        Fondo.setPreferredSize(new java.awt.Dimension(1270, 762));
        Fondo.setVerifyInputWhenFocusTarget(false);

        Main.setForeground(new java.awt.Color(255, 255, 255));
        Main.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Main.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N
        Main.setPreferredSize(new java.awt.Dimension(1270, 762));

        editaraClientes5.setBackground(new java.awt.Color(255, 255, 255));
        editaraClientes5.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes5.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        editaraClientes5.setPreferredSize(new java.awt.Dimension(1270, 762));

        editaraClientes.setBackground(new java.awt.Color(255, 255, 255));
        editaraClientes.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        editaraClientes.setMaximumSize(new java.awt.Dimension(1270, 32767));
        editaraClientes.setPreferredSize(new java.awt.Dimension(1270, 762));

        visualizar.setPreferredSize(new java.awt.Dimension(1268, 671));

        tablacontactosVisualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosVisualizar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablacontactosVisualizar.setForeground(new java.awt.Color(73, 73, 73));
        tablacontactosVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablacontactosVisualizar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablacontactosVisualizar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setViewportView(tablacontactosVisualizar);

        seleccionarV.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV.setText("Seleccionar");
        seleccionarV.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel16.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Buscar");

        jTextField12.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField12.setText(" ");
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton13.setBackground(new java.awt.Color(0, 0, 50));
        jButton13.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Buscar");
        jButton13.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton13.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        TablaContactosVsualizar.setEditable(false);
        TablaContactosVsualizar.setColumns(20);
        TablaContactosVsualizar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        TablaContactosVsualizar.setRows(1);
        TablaContactosVsualizar.setAutoscrolls(false);
        TablaContactosVsualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        TablaContactosVsualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaContactosVsualizar.setMaximumSize(new java.awt.Dimension(700, 20000));
        jScrollPane9.setViewportView(TablaContactosVsualizar);

        jLabel12.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Clientes");

        javax.swing.GroupLayout visualizarLayout = new javax.swing.GroupLayout(visualizar);
        visualizar.setLayout(visualizarLayout);
        visualizarLayout.setHorizontalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(seleccionarV, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(514, Short.MAX_VALUE))
        );
        visualizarLayout.setVerticalGroup(
            visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(seleccionarV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(61, 61, 61))
        );

        seleccionarV.getAccessibleContext().setAccessibleDescription("fffffff");

        editaraClientes.addTab("VER INFO CLIENTE", visualizar);

        crear.setPreferredSize(new java.awt.Dimension(1268, 671));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");

        bNombreC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNombreC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bNombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreCActionPerformed(evt);
            }
        });

        bApellidoC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bApellidoC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bApellidoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoCActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido");

        bCelularC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelularC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Celular");

        bTelFijoC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoCActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tel. Fijo");

        bCorreoC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCorreoC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bCorreoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoCActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo");

        bCuidadorC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCuidadorC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cuidador");

        jLabel10.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cel. Cuidador ");

        bCelCuidadorC.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelCuidadorC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bCelCuidadorC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCelCuidadorCActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 50));
        jButton3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Limpiar");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        contactosCrear.setBackground(new java.awt.Color(0, 0, 50));
        contactosCrear.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear.setText("Agregar");
        contactosCrear.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        contactosCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrearActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Tel. Fijo Ofi 1");

        bTelFijoC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC1ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Tel. Fijo Ofi 2");

        bTelFijoC4.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
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
                .addGap(16, 16, 16)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel24)
                    .addComponent(jLabel7)
                    .addComponent(jLabel29)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(bTelFijoC4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bTelFijoC1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bCelCuidadorC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bCorreoC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bCuidadorC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bTelFijoC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bCelularC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bApellidoC, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(crearLayout.createSequentialGroup()
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                            .addComponent(contactosCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(832, Short.MAX_VALUE))
        );
        crearLayout.setVerticalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(bNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCelularC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCorreoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCelCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactosCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        editaraClientes.addTab("agregar cLIENTE", crear);

        eliminar.setPreferredSize(new java.awt.Dimension(1268, 671));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Buscar");

        jTextField10.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField10.setText(" ");
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton7.setBackground(new java.awt.Color(0, 0, 50));
        jButton7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Buscar");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablaContactosEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaContactosEliminar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaContactosEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaContactosEliminar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaContactosEliminar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(tablaContactosEliminar);

        eliminarEliminar.setBackground(new java.awt.Color(0, 0, 50));
        eliminarEliminar.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        eliminarEliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEliminar.setText("Eliminar");
        eliminarEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Clientes");

        tablacontactosEliminados.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosEliminados.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablacontactosEliminados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablacontactosEliminados.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablacontactosEliminados.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(tablacontactosEliminados);

        seleccionBorrado.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado.setText("Recuperar");
        seleccionBorrado.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Papelera");

        javax.swing.GroupLayout eliminarLayout = new javax.swing.GroupLayout(eliminar);
        eliminar.setLayout(eliminarLayout);
        eliminarLayout.setHorizontalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(144, 144, 144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(eliminarLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(eliminarEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(eliminarLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seleccionBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(505, Short.MAX_VALUE))
        );
        eliminarLayout.setVerticalGroup(
            eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField10)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addComponent(seleccionBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        editaraClientes.addTab("Eliminar CLIENTE", eliminar);

        ModificarC.setPreferredSize(new java.awt.Dimension(1268, 671));

        info.setMaximumSize(new java.awt.Dimension(1270, 762));
        info.setPreferredSize(new java.awt.Dimension(1270, 732));

        jLabel42.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Nombre");

        bNombreE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNombreE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bNombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreEActionPerformed(evt);
            }
        });

        bApellidoE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bApellidoE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bApellidoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoEActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Apellido");

        bCelularE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelularE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Celular");

        bTelFijE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijEActionPerformed(evt);
            }
        });

        bCorreoE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCorreoE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bCorreoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoEActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Tel. Fijo");

        jLabel46.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Correo");

        bCuidadorE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCuidadorE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel47.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Cuidador");

        jLabel48.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Cel. Cuidador ");

        bCelCuidadorE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelCuidadorE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton9.setBackground(new java.awt.Color(0, 0, 50));
        jButton9.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Guardar");
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 0, 50));
        jButton15.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Limpiar");
        jButton15.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Buscar");

        jLabel13.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Clientes");

        seleccionarModificarInfo.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarModificarInfo.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo.setText("Seleccionar");
        seleccionarModificarInfo.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jTextField14.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField14.setText(" ");
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(0, 0, 50));
        jButton23.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Buscar");
        jButton23.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablaModificarInformacion.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaModificarInformacion.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaModificarInformacion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaModificarInformacion.setSelectionBackground(new java.awt.Color(151, 0, 0));
        jScrollPane13.setViewportView(tablaModificarInformacion);

        jLabel30.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Tel. Fijo Ofi 1");

        jLabel35.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Tel. Fijo Ofi 2");

        bTelFijoC5.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC5ActionPerformed(evt);
            }
        });

        bTelFijoC6.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC6.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
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
                .addGap(16, 16, 16)
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING))))
                                .addGap(13, 13, 13)
                                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(infoLayout.createSequentialGroup()
                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(bTelFijE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(447, 447, 447))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(bNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelularE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addGap(18, 18, 18)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ModificarCLayout = new javax.swing.GroupLayout(ModificarC);
        ModificarC.setLayout(ModificarCLayout);
        ModificarCLayout.setHorizontalGroup(
            ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1269, Short.MAX_VALUE)
            .addGroup(ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 1269, Short.MAX_VALUE))
        );
        ModificarCLayout.setVerticalGroup(
            ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(ModificarCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
        );

        editaraClientes.addTab("Modificar Datos CLIENTE", ModificarC);

        lanch.setPreferredSize(new java.awt.Dimension(1268, 671));

        eliminarLanchaa.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        eliminarLanchaa.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        eliminarLanchaa.setPreferredSize(new java.awt.Dimension(1765, 757));

        jLabel56.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Tipo");

        tipo.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tipo.setText(" ");
        tipo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        marca.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        marca.setText(" ");
        marca.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Marca");

        modelo.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        modelo.setText(" ");
        modelo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel58.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Modelo");

        motor.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        motor.setText(" ");
        motor.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorActionPerformed(evt);
            }
        });

        numeroDeSerie.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        numeroDeSerie.setText(" ");
        numeroDeSerie.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        numeroDeSerie.setPreferredSize(new java.awt.Dimension(6, 23));
        numeroDeSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroDeSerieActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Motor");

        jLabel60.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("N° Serie");

        guardarLancha.setBackground(new java.awt.Color(0, 0, 50));
        guardarLancha.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        guardarLancha.setForeground(new java.awt.Color(255, 255, 255));
        guardarLancha.setText("Agregar");
        guardarLancha.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        guardarLancha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLanchaActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 0, 50));
        jButton17.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Limpiar");
        jButton17.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Buscar");

        seleccionadoAgregar1.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        seleccionadoAgregar1.setForeground(new java.awt.Color(255, 255, 255));
        seleccionadoAgregar1.setText("Clientes");

        seleccionarLanchaAgregar.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarLanchaAgregar.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarLanchaAgregar.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarLanchaAgregar.setText("Seleccionar");
        seleccionarLanchaAgregar.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jTextField15.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField15.setText(" ");
        jTextField15.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton25.setBackground(new java.awt.Color(0, 0, 50));
        jButton25.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Buscar");
        jButton25.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablaLanchasAgregar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaLanchasAgregar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaLanchasAgregar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaLanchasAgregar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaLanchasAgregar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane14.setViewportView(tablaLanchasAgregar);

        estado.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        estado.setForeground(new java.awt.Color(255, 255, 255));
        estado.setText("Estado: Seleccionando Cliente");

        añadiendolanchaa.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        añadiendolanchaa.setForeground(new java.awt.Color(255, 255, 255));
        añadiendolanchaa.setText("DATOS DE EMBARCACION");

        jLabel61.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Clave");

        claveEmb.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        claveEmb.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        claveEmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveEmbActionPerformed(evt);
            }
        });

        horasAE.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        horasAE.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        horasAE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horasAEActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Horas");

        javax.swing.GroupLayout agregarLanchaLayout = new javax.swing.GroupLayout(agregarLancha);
        agregarLancha.setLayout(agregarLanchaLayout);
        agregarLanchaLayout.setHorizontalGroup(
            agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLanchaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15))
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seleccionadoAgregar1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(guardarLancha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(horasAE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveEmb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroDeSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(motor, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(añadiendolanchaa, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(476, Short.MAX_VALUE))
        );
        agregarLanchaLayout.setVerticalGroup(
            agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField15)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(añadiendolanchaa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(motor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroDeSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveEmb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horasAE, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardarLancha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado)
                        .addContainerGap(280, Short.MAX_VALUE))
                    .addGroup(agregarLanchaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionadoAgregar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        tipo.getAccessibleContext().setAccessibleName("");
        marca.getAccessibleContext().setAccessibleName("");
        modelo.getAccessibleContext().setAccessibleName("");
        motor.getAccessibleContext().setAccessibleName("");
        numeroDeSerie.getAccessibleContext().setAccessibleName("");
        claveEmb.getAccessibleContext().setAccessibleName("");
        horasAE.getAccessibleContext().setAccessibleName("");

        eliminarLanchaa.addTab("aGREGAR EMBARCACIÓN", agregarLancha);
        agregarLancha.getAccessibleContext().setAccessibleName("");

        jLabel27.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Buscar");

        jTextField16.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField16.setText(" ");
        jTextField16.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(0, 0, 50));
        jButton26.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Buscar");
        jButton26.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        jButton27.setBackground(new java.awt.Color(0, 0, 50));
        jButton27.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Seleccionar");
        jButton27.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        tablaLanchasModificar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaLanchasModificar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaLanchasModificar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaLanchasModificar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaLanchasModificar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane15.setViewportView(tablaLanchasModificar);

        jLabel17.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Clientes");

        tablaEmbarcaciones.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaEmbarcaciones.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaEmbarcaciones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaEmbarcaciones.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaEmbarcaciones.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane16.setViewportView(tablaEmbarcaciones);

        seleccionBorrado3.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado3.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado3.setText("Cargar");
        seleccionBorrado3.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel63.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Motor");

        jLabel64.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Modelo");

        jLabel66.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Tipo");

        nSerie1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nSerie1.setText(" ");
        nSerie1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        nSerie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie1ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 0, 50));
        jButton12.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Guardar");
        jButton12.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(0, 0, 50));
        jButton18.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Limpiar");
        jButton18.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("N° Serie");

        m.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m.setText(" ");
        m.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Marca");

        t.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        t.setText(" ");
        t.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });

        mot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mot.setText(" ");
        mot.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        mot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motActionPerformed(evt);
            }
        });

        mo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mo.setText(" ");
        mo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel22.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Embarcacion");

        estado2.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        estado2.setForeground(new java.awt.Color(255, 255, 255));
        estado2.setText("Estado: Esperando seleccion de cliente");

        jLabel62.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Clave");

        claveEmb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        claveEmb1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        claveEmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveEmb1ActionPerformed(evt);
            }
        });

        añadiendolanchaa1.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        añadiendolanchaa1.setForeground(new java.awt.Color(255, 255, 255));
        añadiendolanchaa1.setText("DATOS DE EMBARCACION");

        horasAE1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        horasAE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        horasAE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horasAE1ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Horas");
        jLabel69.setPreferredSize(new java.awt.Dimension(64, 17));

        javax.swing.GroupLayout modificarLanchaLayout = new javax.swing.GroupLayout(modificarLancha);
        modificarLancha.setLayout(modificarLanchaLayout);
        modificarLanchaLayout.setHorizontalGroup(
            modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLanchaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(seleccionBorrado3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(modificarLanchaLayout.createSequentialGroup()
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(modificarLanchaLayout.createSequentialGroup()
                            .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estado2)
                            .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modificarLanchaLayout.createSequentialGroup()
                                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(horasAE1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(claveEmb1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nSerie1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(mot, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(m, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(mo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(t, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addComponent(añadiendolanchaa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(473, Short.MAX_VALUE))
        );
        modificarLanchaLayout.setVerticalGroup(
            modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLanchaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField16)
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addComponent(añadiendolanchaa1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mot, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveEmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horasAE1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado2)
                        .addGap(282, 282, 282))
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel22))
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(seleccionBorrado3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        eliminarLanchaa.addTab("Modificar Datos EMBARCACIÓN", modificarLancha);

        jLabel28.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Buscar");

        jTextField17.setText(" ");
        jTextField17.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton28.setBackground(new java.awt.Color(0, 0, 50));
        jButton28.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Buscar");
        jButton28.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        jButton29.setBackground(new java.awt.Color(0, 0, 50));
        jButton29.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Eliminar");
        jButton29.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        tablaLanchasEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaLanchasEliminar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaLanchasEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaLanchasEliminar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaLanchasEliminar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane17.setViewportView(tablaLanchasEliminar);

        seleccionBorrado4.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado4.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado4.setText("Cancelar");
        seleccionBorrado4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel21.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Embarcaciones");

        tablaEE.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaEE.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaEE.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaEE.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaEE.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane19.setViewportView(tablaEE);

        seleccionBorrado5.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado5.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado5.setText("Seleccionar");
        seleccionBorrado5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        sel.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        sel.setForeground(new java.awt.Color(255, 255, 255));
        sel.setText("Clientes");

        seleccionBorrado6.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado6.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado6.setText("Eliminar");
        seleccionBorrado6.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        estado1.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        estado1.setForeground(new java.awt.Color(255, 255, 255));
        estado1.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha1Layout = new javax.swing.GroupLayout(modificarLancha1);
        modificarLancha1.setLayout(modificarLancha1Layout);
        modificarLancha1Layout.setHorizontalGroup(
            modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLancha1Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(seleccionBorrado5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seleccionBorrado4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        modificarLancha1Layout.setVerticalGroup(
            modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(sel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(estado1))
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jScrollPane19))
                .addGap(126, 126, 126))
        );

        eliminarLanchaa.addTab("Eliminar EMBARCACIÓN", modificarLancha1);

        javax.swing.GroupLayout lanchLayout = new javax.swing.GroupLayout(lanch);
        lanch.setLayout(lanchLayout);
        lanchLayout.setHorizontalGroup(
            lanchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa, javax.swing.GroupLayout.DEFAULT_SIZE, 1269, Short.MAX_VALUE)
        );
        lanchLayout.setVerticalGroup(
            lanchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );

        eliminarLanchaa.getAccessibleContext().setAccessibleName("");

        editaraClientes.addTab("Embarcaciones de Clientes", lanch);

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 734, Short.MAX_VALUE)
        );

        editaraClientes5.addTab("CLIENTES", Clientes);

        editaraClientes4.setBackground(new java.awt.Color(255, 255, 255));
        editaraClientes4.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes4.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        editaraClientes4.setPreferredSize(new java.awt.Dimension(1270, 762));

        visualizar1.setPreferredSize(new java.awt.Dimension(1268, 671));

        tablacontactosVisualizar1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosVisualizar1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablacontactosVisualizar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablacontactosVisualizar1.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablacontactosVisualizar1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane25.setViewportView(tablacontactosVisualizar1);

        seleccionarV1.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV1.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV1.setText("Ver");
        seleccionarV1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        seleccionarV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV1MouseClicked(evt);
            }
        });
        seleccionarV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV1ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Buscar");

        jTextField19.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField19.setText(" ");
        jTextField19.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(0, 0, 50));
        jButton19.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Buscar");
        jButton19.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        TablaContactosVsualizar1.setEditable(false);
        TablaContactosVsualizar1.setColumns(20);
        TablaContactosVsualizar1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        TablaContactosVsualizar1.setRows(1);
        TablaContactosVsualizar1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        TablaContactosVsualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaContactosVsualizar1.setMaximumSize(new java.awt.Dimension(700, 20000));
        jScrollPane29.setViewportView(TablaContactosVsualizar1);

        jLabel53.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Distribuidores");

        javax.swing.GroupLayout visualizar1Layout = new javax.swing.GroupLayout(visualizar1);
        visualizar1.setLayout(visualizar1Layout);
        visualizar1Layout.setHorizontalGroup(
            visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seleccionarV1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(513, Short.MAX_VALUE))
        );
        visualizar1Layout.setVerticalGroup(
            visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField19))
                .addGap(13, 13, 13)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane29)
                    .addComponent(seleccionarV1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        editaraClientes4.addTab("VER INFO Distribuidor", visualizar1);

        crear1.setPreferredSize(new java.awt.Dimension(1268, 671));

        jLabel54.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Nombre");

        bNombreC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNombreC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bNombreC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreC1ActionPerformed(evt);
            }
        });

        bApellidoC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bApellidoC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bApellidoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoC1ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Apellido");

        bCelularC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelularC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel70.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Celular");

        bTelFijoC7.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC7ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Tel. Fijo");

        bCorreoC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCorreoC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bCorreoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC1ActionPerformed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Correo");

        bEmpresaC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bEmpresaC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel73.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Empresa");

        jLabel74.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("N° Cuenta");

        bNumCuentaC1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNumCuentaC1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bNumCuentaC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNumCuentaC1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 50));
        jButton4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Limpiar");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        contactosCrear6.setBackground(new java.awt.Color(0, 0, 50));
        contactosCrear6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear6.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear6.setText("Agregar");
        contactosCrear6.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        contactosCrear6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear6ActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Tel. Fijo Ofi 1");

        bTelFijoC8.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC8.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC8ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Tel. Fijo Ofi 2");

        bTelFijoC9.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crear1Layout = new javax.swing.GroupLayout(crear1);
        crear1.setLayout(crear1Layout);
        crear1Layout.setHorizontalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73)
                            .addComponent(jLabel55)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71)
                            .addComponent(jLabel54)
                            .addComponent(jLabel76))
                        .addGap(16, 16, 16)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bTelFijoC9, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bTelFijoC8, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bNumCuentaC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bCorreoC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bEmpresaC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bTelFijoC7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bCelularC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(bApellidoC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addGroup(crear1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(contactosCrear6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bNombreC1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addComponent(jLabel75))
                .addContainerGap(832, Short.MAX_VALUE))
        );
        crear1Layout.setVerticalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(bNombreC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bApellidoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(bCelularC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCorreoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEmpresaC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNumCuentaC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74))
                .addGap(18, 18, 18)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactosCrear6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        editaraClientes4.addTab("agregar distribuidor", crear1);

        eliminar1.setPreferredSize(new java.awt.Dimension(1268, 671));

        jLabel87.setBackground(new java.awt.Color(255, 255, 255));
        jLabel87.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Buscar");

        jTextField20.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField20.setText(" ");
        jTextField20.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 0, 50));
        jButton10.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Buscar");
        jButton10.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablaContactosEliminar1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaContactosEliminar1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaContactosEliminar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaContactosEliminar1.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaContactosEliminar1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setViewportView(tablaContactosEliminar1);

        eliminarEliminar4.setBackground(new java.awt.Color(0, 0, 50));
        eliminarEliminar4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        eliminarEliminar4.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEliminar4.setText("Eliminar");
        eliminarEliminar4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        eliminarEliminar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEliminar4MouseClicked(evt);
            }
        });
        eliminarEliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEliminar4ActionPerformed(evt);
            }
        });

        jLabel88.setBackground(new java.awt.Color(255, 255, 255));
        jLabel88.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Existentes");

        tablacontactosEliminados2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosEliminados2.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablacontactosEliminados2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablacontactosEliminados2.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablacontactosEliminados2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane32.setViewportView(tablacontactosEliminados2);

        seleccionBorrado7.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado7.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado7.setText("Recuperar");
        seleccionBorrado7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionBorrado7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado7MouseClicked(evt);
            }
        });
        seleccionBorrado7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado7ActionPerformed(evt);
            }
        });

        jLabel89.setBackground(new java.awt.Color(255, 255, 255));
        jLabel89.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Papelera");

        javax.swing.GroupLayout eliminar1Layout = new javax.swing.GroupLayout(eliminar1);
        eliminar1.setLayout(eliminar1Layout);
        eliminar1Layout.setHorizontalGroup(
            eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarEliminar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel88)
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seleccionBorrado7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addContainerGap(505, Short.MAX_VALUE))
        );
        eliminar1Layout.setVerticalGroup(
            eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel87))
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jLabel88))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminarEliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionBorrado7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        editaraClientes4.addTab("Eliminar distribuidor", eliminar1);

        ModificarC1.setPreferredSize(new java.awt.Dimension(1268, 671));

        info1.setPreferredSize(new java.awt.Dimension(1265, 650));

        jLabel90.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Nombre");

        bNombreE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNombreE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bNombreE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreE1ActionPerformed(evt);
            }
        });

        bApellidoE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bApellidoE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bApellidoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoE1ActionPerformed(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Apellido");

        bCelularE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelularE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel92.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Celular");

        bTelFijE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijE1ActionPerformed(evt);
            }
        });

        bCorreoE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCorreoE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bCorreoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoE1ActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Tel. Fijo");

        jLabel94.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Correo");

        bEmpresaE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bEmpresaE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jLabel95.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("Empresa");

        nCuentaDistribuidor.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        nCuentaDistribuidor.setForeground(new java.awt.Color(255, 255, 255));
        nCuentaDistribuidor.setText("N° Cuenta");

        bNumCuentaDistribuidorE1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bNumCuentaDistribuidorE1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton20.setBackground(new java.awt.Color(0, 0, 50));
        jButton20.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Guardar");
        jButton20.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(0, 0, 50));
        jButton21.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Limpiar");
        jButton21.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Buscar");

        jLabel98.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Distribuidor");

        seleccionarModificarInfo1.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarModificarInfo1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo1.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo1.setText("Seleccionar");
        seleccionarModificarInfo1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo1MouseClicked(evt);
            }
        });
        seleccionarModificarInfo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo1ActionPerformed(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField21.setText(" ");
        jTextField21.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton24.setBackground(new java.awt.Color(0, 0, 50));
        jButton24.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Buscar");
        jButton24.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablaModificarInformacion1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaModificarInformacion1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaModificarInformacion1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaModificarInformacion1.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaModificarInformacion1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane33.setViewportView(tablaModificarInformacion1);

        jLabel99.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Tel. Fijo Ofi 1");

        jLabel100.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Tel. Fijo Ofi 2");

        bTelFijoC10.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC10ActionPerformed(evt);
            }
        });

        bTelFijoC11.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));
        bTelFijoC11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout info1Layout = new javax.swing.GroupLayout(info1);
        info1.setLayout(info1Layout);
        info1Layout.setHorizontalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane33, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(seleccionarModificarInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nCuentaDistribuidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bCorreoE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bEmpresaE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(bCelularE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bApellidoE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bTelFijoC10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bTelFijoC11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bNumCuentaDistribuidorE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bTelFijE1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(bNombreE1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(info1Layout.createSequentialGroup()
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(449, 449, 449))))
        );
        info1Layout.setVerticalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seleccionarModificarInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(bNombreE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelularE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bEmpresaE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bNumCuentaDistribuidorE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nCuentaDistribuidor))
                        .addGap(18, 18, 18)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ModificarC1Layout = new javax.swing.GroupLayout(ModificarC1);
        ModificarC1.setLayout(ModificarC1Layout);
        ModificarC1Layout.setHorizontalGroup(
            ModificarC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 1269, Short.MAX_VALUE)
        );
        ModificarC1Layout.setVerticalGroup(
            ModificarC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(info1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );

        editaraClientes4.addTab("Modificar datos distribuidor", ModificarC1);

        javax.swing.GroupLayout DistribuidoresLayout = new javax.swing.GroupLayout(Distribuidores);
        Distribuidores.setLayout(DistribuidoresLayout);
        DistribuidoresLayout.setHorizontalGroup(
            DistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes4, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
        );
        DistribuidoresLayout.setVerticalGroup(
            DistribuidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes4, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );

        editaraClientes5.addTab("Distribuidores ", Distribuidores);

        javax.swing.GroupLayout Clientes2Layout = new javax.swing.GroupLayout(Clientes2);
        Clientes2.setLayout(Clientes2Layout);
        Clientes2Layout.setHorizontalGroup(
            Clientes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Clientes2Layout.setVerticalGroup(
            Clientes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes5, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );

        Main.addTab("CONTACTOS", Clientes2);

        editaraClientes1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        editaraClientes1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N

        VisualizarOrden.setMaximumSize(new java.awt.Dimension(1270, 762));
        VisualizarOrden.setPreferredSize(new java.awt.Dimension(1270, 762));

        tablaOrdenesVisualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesVisualizar.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaOrdenesVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesVisualizar.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesVisualizar.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane10.setViewportView(tablaOrdenesVisualizar);

        jLabel18.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Buscar");

        jTextField13.setText(" ");
        jTextField13.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton14.setBackground(new java.awt.Color(0, 0, 50));
        jButton14.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("Buscar");
        jButton14.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));

        visualizarV1.setEditable(false);
        visualizarV1.setColumns(20);
        visualizarV1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        visualizarV1.setRows(1);
        visualizarV1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        visualizarV1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane11.setViewportView(visualizarV1);

        jLabel15.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Clientes");

        seleccionarV3.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV3.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV3.setText("Seleccionar");
        seleccionarV3.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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

        tablaOrdenesVisualizar1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesVisualizar1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaOrdenesVisualizar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesVisualizar1.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesVisualizar1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane20.setViewportView(tablaOrdenesVisualizar1);

        jLabel37.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Orden Seleccionada:");

        seleccionarV4.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV4.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV4.setText("Seleccionar");
        seleccionarV4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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

        jLabel39.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Ordenes");

        javax.swing.GroupLayout VisualizarOrdenLayout = new javax.swing.GroupLayout(VisualizarOrden);
        VisualizarOrden.setLayout(VisualizarOrdenLayout);
        VisualizarOrdenLayout.setHorizontalGroup(
            VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VisualizarOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jTextField13))
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 35, Short.MAX_VALUE)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarV3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionarV4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(414, 414, 414))
        );
        VisualizarOrdenLayout.setVerticalGroup(
            VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VisualizarOrdenLayout.createSequentialGroup()
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarV3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VisualizarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarV4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane11))
                .addGap(60, 60, 60))
        );

        editaraClientes1.addTab("VER INFO oRDEN", VisualizarOrden);

        tablaOrdenesVisualizar2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesVisualizar2.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaOrdenesVisualizar2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesVisualizar2.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesVisualizar2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane21.setViewportView(tablaOrdenesVisualizar2);

        jLabel38.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Buscar");

        jTextField18.setText(" ");
        jTextField18.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(153, 0, 0)));

        jButton16.setBackground(new java.awt.Color(0, 0, 50));
        jButton16.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Buscar");
        jButton16.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        visualizarV5.setEditable(false);
        visualizarV5.setColumns(20);
        visualizarV5.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        visualizarV5.setRows(1);
        visualizarV5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        visualizarV5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane22.setViewportView(visualizarV5);

        jLabel41.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Clientes Con Deuda");

        seleccionarV7.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV7.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV7.setText("Seleccionar");
        seleccionarV7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV7MouseClicked(evt);
            }
        });
        seleccionarV7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV7ActionPerformed(evt);
            }
        });

        tablaOrdenesVisualizar3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesVisualizar3.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaOrdenesVisualizar3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesVisualizar3.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesVisualizar3.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane23.setViewportView(tablaOrdenesVisualizar3);

        jLabel49.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Orden Seleccionada:");

        seleccionarV8.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV8.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV8.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV8.setText("Cargar Orden");
        seleccionarV8.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV8MouseClicked(evt);
            }
        });
        seleccionarV8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV8ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Ordenes No Canceladas");

        seleccionarV9.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV9.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV9.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV9.setText("Cancelar Orden");
        seleccionarV9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV9MouseClicked(evt);
            }
        });
        seleccionarV9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV9ActionPerformed(evt);
            }
        });

        seleccionarV10.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV10.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV10.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV10.setText("No Cancelada");
        seleccionarV10.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV10MouseClicked(evt);
            }
        });
        seleccionarV10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV10ActionPerformed(evt);
            }
        });

        seleccionarV11.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV11.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV11.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV11.setText("Guardar Cambios");
        seleccionarV11.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV11MouseClicked(evt);
            }
        });
        seleccionarV11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV11ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Ordenes Canceladas");

        tablaOrdenesVisualizar4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesVisualizar4.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablaOrdenesVisualizar4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesVisualizar4.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesVisualizar4.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane24.setViewportView(tablaOrdenesVisualizar4);

        seleccionarV12.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV12.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV12.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV12.setText("Cargar Orden");
        seleccionarV12.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV12MouseClicked(evt);
            }
        });
        seleccionarV12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV12ActionPerformed(evt);
            }
        });

        seleccionarV13.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV13.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV13.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV13.setText("Cancelar Cambios");
        seleccionarV13.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarV13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarV13MouseClicked(evt);
            }
        });
        seleccionarV13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarV13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrdInpagasLayout = new javax.swing.GroupLayout(OrdInpagas);
        OrdInpagas.setLayout(OrdInpagasLayout);
        OrdInpagasLayout.setHorizontalGroup(
            OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdInpagasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(OrdInpagasLayout.createSequentialGroup()
                        .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(seleccionarV7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(seleccionarV8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(seleccionarV12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(OrdInpagasLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrdInpagasLayout.createSequentialGroup()
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(OrdInpagasLayout.createSequentialGroup()
                        .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(OrdInpagasLayout.createSequentialGroup()
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(seleccionarV9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarV10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarV11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarV13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 80, Short.MAX_VALUE)))
                        .addGap(180, 180, 180))))
        );
        OrdInpagasLayout.setVerticalGroup(
            OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdInpagasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(OrdInpagasLayout.createSequentialGroup()
                            .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(seleccionarV7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel50)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(seleccionarV8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel51)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(OrdInpagasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(seleccionarV12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane22))
                    .addGroup(OrdInpagasLayout.createSequentialGroup()
                        .addComponent(seleccionarV9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarV11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(seleccionarV13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        editaraClientes1.addTab("cANCELAR orden", OrdInpagas);

        dia.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        dia.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Fecha");

        contactosCrear1.setBackground(new java.awt.Color(0, 0, 77));
        contactosCrear1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear1.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear1.setText("Crear");
        contactosCrear1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        contactosCrear1.setPreferredSize(new java.awt.Dimension(100, 32));
        contactosCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear1ActionPerformed(evt);
            }
        });

        tablaOrdenesCrearManoObra.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesCrearManoObra.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N
        tablaOrdenesCrearManoObra.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesCrearManoObra.setPreferredSize(null);
        tablaOrdenesCrearManoObra.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesCrearManoObra.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane49.setViewportView(tablaOrdenesCrearManoObra);

        jLabel148.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(255, 255, 255));
        jLabel148.setText("Seleccionado");

        jLabel31.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mano de Obra");

        tablaOrdenesCrear.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesCrear.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaOrdenesCrear.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesCrear.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesCrear.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane50.setViewportView(tablaOrdenesCrear);

        seleccionarModificarInfo5.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo5.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo5.setText("Agregar");
        seleccionarModificarInfo5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo5.setPreferredSize(new java.awt.Dimension(100, 32));
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

        jLabel149.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setText("Añadir Manualmente");

        bFechaOrdenesCrear1.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bFechaOrdenesCrear1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        bFechaOrdenesCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo6.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo6.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo6.setText("Añadir ->");
        seleccionarModificarInfo6.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo6.setPreferredSize(new java.awt.Dimension(100, 32));
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

        tablaOdenCrearAñadirRepuestos.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOdenCrearAñadirRepuestos.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N
        tablaOdenCrearAñadirRepuestos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOdenCrearAñadirRepuestos.setPreferredSize(null);
        tablaOdenCrearAñadirRepuestos.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOdenCrearAñadirRepuestos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane51.setViewportView(tablaOdenCrearAñadirRepuestos);

        jLabel150.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(255, 255, 255));
        jLabel150.setText("Repuestos");

        seleccionarModificarInfo7.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo7.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo7.setText("Seleccionar");
        seleccionarModificarInfo7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo7.setPreferredSize(new java.awt.Dimension(100, 32));
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

        seleccionarModificarInfo8.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo8.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo8.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo8.setText("Añadir ->");
        seleccionarModificarInfo8.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo8.setPreferredSize(new java.awt.Dimension(100, 32));
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

        jLabel151.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(255, 255, 255));
        jLabel151.setText("VISTA PREVIA");

        tablaOrdenesCrearVPMO.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesCrearVPMO.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N
        tablaOrdenesCrearVPMO.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesCrearVPMO.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesCrearVPMO.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane52.setViewportView(tablaOrdenesCrearVPMO);

        seleccionarModificarInfo9.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo9.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo9.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo9.setText("<- Quitar");
        seleccionarModificarInfo9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo9.setPreferredSize(new java.awt.Dimension(100, 32));
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

        tablaOrdenesCrearVPRepuestos.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesCrearVPRepuestos.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N
        tablaOrdenesCrearVPRepuestos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesCrearVPRepuestos.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesCrearVPRepuestos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane53.setViewportView(tablaOrdenesCrearVPRepuestos);

        jLabel152.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(255, 255, 255));
        jLabel152.setText("Mano de obra agegada");

        jLabel154.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setText("Agregar Nota");

        ordenesCrearNota.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        ordenesCrearNota.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        ordenesCrearNota.setToolTipText("");
        ordenesCrearNota.setMaximumSize(new java.awt.Dimension(6, 22));
        ordenesCrearNota.setPreferredSize(null);
        jScrollPane2.setViewportView(ordenesCrearNota);

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Repuestos agregados ");

        pHTrabajo.setEditable(false);
        pHTrabajo.setBackground(new java.awt.Color(255, 255, 255));
        pHTrabajo.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        pHTrabajo.setToolTipText("22000");
        pHTrabajo.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        pHTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pHTrabajoActionPerformed(evt);
            }
        });

        jLabel155.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 255, 255));
        jLabel155.setText("Precio Hora");

        jLabel156.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 255, 255));
        jLabel156.setText("Total Rep.");

        tRepuestos.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tRepuestos.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRepuestosActionPerformed(evt);
            }
        });

        jLabel157.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(255, 255, 255));
        jLabel157.setText("Total Varios");

        jLabel158.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setText("Agregar Varios");

        ordenesCrearVarios.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        ordenesCrearVarios.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        ordenesCrearVarios.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane3.setViewportView(ordenesCrearVarios);

        seleccionarModificarInfo13.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo13.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo13.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo13.setText("<- Quitar");
        seleccionarModificarInfo13.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo13.setPreferredSize(new java.awt.Dimension(100, 32));
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

        tVarios.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tVarios.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tVarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tVariosActionPerformed(evt);
            }
        });

        visualizarV4.setEditable(false);
        visualizarV4.setColumns(20);
        visualizarV4.setFont(new java.awt.Font("Lucida Bright", 0, 12)); // NOI18N
        visualizarV4.setRows(1);
        visualizarV4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        visualizarV4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane26.setViewportView(visualizarV4);

        hTrabajo.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        hTrabajo.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        hTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hTrabajoActionPerformed(evt);
            }
        });

        jLabel163.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(255, 255, 255));
        jLabel163.setText("H. de trabajo");

        tManoObra.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tManoObra.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));

        jLabel165.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setText("Total M.Obra");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("     Cantidad");

        cantidad.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        cantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        mes.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        mes.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesActionPerformed(evt);
            }
        });

        año.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        año.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añoActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("/");

        jLabel23.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("/");

        contactosCrear4.setBackground(new java.awt.Color(0, 0, 77));
        contactosCrear4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear4.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear4.setText("Calcular");
        contactosCrear4.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        contactosCrear4.setPreferredSize(new java.awt.Dimension(100, 32));
        contactosCrear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear4ActionPerformed(evt);
            }
        });

        calcularRepuestos.setBackground(new java.awt.Color(0, 0, 77));
        calcularRepuestos.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        calcularRepuestos.setForeground(new java.awt.Color(255, 255, 255));
        calcularRepuestos.setText("Calcular");
        calcularRepuestos.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        calcularRepuestos.setPreferredSize(new java.awt.Dimension(100, 32));
        calcularRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestosActionPerformed(evt);
            }
        });

        contactosCrear5.setBackground(new java.awt.Color(0, 0, 77));
        contactosCrear5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear5.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear5.setText("Vista Previa");
        contactosCrear5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        contactosCrear5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear5ActionPerformed(evt);
            }
        });

        tTotal.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tTotalActionPerformed(evt);
            }
        });

        jLabel166.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(255, 255, 255));
        jLabel166.setText("TOTAL:");

        jLabel167.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("N° Orden:");

        bFechaOrdenesCrear8.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bFechaOrdenesCrear8.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        bFechaOrdenesCrear8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFechaOrdenesCrear8ActionPerformed(evt);
            }
        });

        calcularRepuestos1.setBackground(new java.awt.Color(0, 0, 77));
        calcularRepuestos1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        calcularRepuestos1.setForeground(new java.awt.Color(255, 255, 255));
        calcularRepuestos1.setText("Calcular");
        calcularRepuestos1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        calcularRepuestos1.setPreferredSize(new java.awt.Dimension(100, 32));
        calcularRepuestos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRepuestos1ActionPerformed(evt);
            }
        });

        seleccionarModificarInfo10.setBackground(new java.awt.Color(0, 0, 77));
        seleccionarModificarInfo10.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarModificarInfo10.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarModificarInfo10.setText("Seleccionar");
        seleccionarModificarInfo10.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
        seleccionarModificarInfo10.setPreferredSize(new java.awt.Dimension(100, 32));
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

        tablaOrdenesCrear2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablaOrdenesCrear2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaOrdenesCrear2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        tablaOrdenesCrear2.setSelectionBackground(new java.awt.Color(151, 0, 0));
        tablaOrdenesCrear2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane56.setViewportView(tablaOrdenesCrear2);

        jLabel36.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Embarcaciones");

        javax.swing.GroupLayout CrearOrdenLayout = new javax.swing.GroupLayout(CrearOrden);
        CrearOrden.setLayout(CrearOrdenLayout);
        CrearOrdenLayout.setHorizontalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarModificarInfo7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionarModificarInfo10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel148, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel150, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel154, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cantidad, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bFechaOrdenesCrear1)
                            .addComponent(jLabel149, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jScrollPane49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel158)
                                .addComponent(jScrollPane53, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel157, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(calcularRepuestos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tRepuestos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel156, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tVarios)))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel152))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pHTrabajo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel163, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hTrabajo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(contactosCrear4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel165, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                .addComponent(tManoObra, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel167)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel23)
                                        .addGap(3, 3, 3)
                                        .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                            .addComponent(tTotal))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(calcularRepuestos1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                            .addComponent(jLabel166))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(contactosCrear5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel151, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(contactosCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))))
        );
        CrearOrdenLayout.setVerticalGroup(
            CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel151)
                                            .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(bFechaOrdenesCrear8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addComponent(calcularRepuestos1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel166))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrearOrdenLayout.createSequentialGroup()
                                                        .addGap(24, 24, 24)
                                                        .addComponent(pHTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel163)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(hTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(contactosCrear4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel165)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(tManoObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(26, 26, 26))
                                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrearOrdenLayout.createSequentialGroup()
                                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel31)
                                                            .addComponent(jLabel148))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrearOrdenLayout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addComponent(jLabel152)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane52))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel149)))
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel150, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel156)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(calcularRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(CrearOrdenLayout.createSequentialGroup()
                                            .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                        .addComponent(jScrollPane51, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarModificarInfo7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel154)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane56, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)))
                            .addGroup(CrearOrdenLayout.createSequentialGroup()
                                .addComponent(jLabel158)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CrearOrdenLayout.createSequentialGroup()
                                        .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tVarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearOrdenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(contactosCrear5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(contactosCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(seleccionarModificarInfo10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        editaraClientes1.addTab("Crear ORDEN", CrearOrden);

        jLabel32.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Buscar:");

        jTextField11.setText(" ");
        jTextField11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        jButton8.setBackground(new java.awt.Color(0, 0, 77));
        jButton8.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Buscar");
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));

        tablacontactosEliminar1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosEliminar1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablacontactosEliminar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(tablacontactosEliminar1);

        eliminarEliminar1.setBackground(new java.awt.Color(0, 0, 77));
        eliminarEliminar1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        eliminarEliminar1.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEliminar1.setText("Seleccionar");
        eliminarEliminar1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel33.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Clientes");

        tablacontactosEliminados1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(153, 0, 0)));
        tablacontactosEliminados1.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
        tablacontactosEliminados1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane18.setViewportView(tablacontactosEliminados1);

        seleccionBorrado1.setBackground(new java.awt.Color(0, 0, 77));
        seleccionBorrado1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado1.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado1.setText("Cargar");
        seleccionBorrado1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 0)));
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

        jLabel34.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Ordenes ");

        javax.swing.GroupLayout EliminarOrdenLayout = new javax.swing.GroupLayout(EliminarOrden);
        EliminarOrden.setLayout(EliminarOrdenLayout);
        EliminarOrdenLayout.setHorizontalGroup(
            EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(EliminarOrdenLayout.createSequentialGroup()
                        .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(seleccionBorrado1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EliminarOrdenLayout.createSequentialGroup()
                                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EliminarOrdenLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EliminarOrdenLayout.setVerticalGroup(
            EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EliminarOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(EliminarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionBorrado1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        eliminarEliminar1.getAccessibleContext().setAccessibleName("");
        seleccionBorrado1.getAccessibleContext().setAccessibleName("");

        editaraClientes1.addTab("Eliminar ORDEN", EliminarOrden);

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1)
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );

        Main.addTab("ORDENES", Ordenes);

        editaraClientes2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N

        tablaRepuestosVisualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(151, 0, 0)));
        tablaRepuestosVisualizar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaRepuestosVisualizar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane27.setViewportView(tablaRepuestosVisualizar);
        tablaRepuestosVisualizar.getAccessibleContext().setAccessibleName("");

        jLabel77.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Buscar");

        jTextField22.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField22.setText(" ");
        jTextField22.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        jButton22.setBackground(new java.awt.Color(0, 0, 50));
        jButton22.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Buscar");
        jButton22.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));

        visualizarV2.setEditable(false);
        visualizarV2.setColumns(20);
        visualizarV2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        visualizarV2.setRows(1);
        visualizarV2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(151, 0, 0)));
        visualizarV2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        visualizarV2.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        visualizarV2.setSelectionColor(new java.awt.Color(151, 0, 0));
        jScrollPane28.setViewportView(visualizarV2);

        jLabel78.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Seleccionado");

        seleccionarV5.setBackground(new java.awt.Color(0, 0, 50));
        seleccionarV5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionarV5.setForeground(new java.awt.Color(255, 255, 255));
        seleccionarV5.setText("VER");
        seleccionarV5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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
                .addGap(16, 16, 16)
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionarV5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(578, Short.MAX_VALUE))
        );
        visualizar2Layout.setVerticalGroup(
            visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel77))
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel78)
                .addGap(5, 5, 5)
                .addGroup(visualizar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(seleccionarV5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(jScrollPane28))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        editaraClientes2.addTab("VER INFO REPUESTO", visualizar2);

        bCelularC2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCelularC2.setText(" ");
        bCelularC2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        jLabel79.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Nombre");

        bTelFijoC2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bTelFijoC2.setText(" ");
        bTelFijoC2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        bTelFijoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC2ActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Codigo");

        bCorreoC2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCorreoC2.setText(" ");
        bCorreoC2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        bCorreoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC2ActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Precio");

        bCuidadorC2.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        bCuidadorC2.setText(" ");
        bCuidadorC2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        jLabel82.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Cantidad");

        jButton5.setBackground(new java.awt.Color(0, 0, 50));
        jButton5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Cancelar");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        contactosCrear2.setBackground(new java.awt.Color(0, 0, 50));
        contactosCrear2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        contactosCrear2.setForeground(new java.awt.Color(255, 255, 255));
        contactosCrear2.setText("Crear");
        contactosCrear2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82))
                .addGap(31, 31, 31)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(crear2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contactosCrear2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bCorreoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bTelFijoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCelularC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(862, Short.MAX_VALUE))
        );
        crear2Layout.setVerticalGroup(
            crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(bCelularC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTelFijoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCorreoC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addGap(18, 18, 18)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactosCrear2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(534, Short.MAX_VALUE))
        );

        editaraClientes2.addTab("cREAR REPUESTO", crear2);

        jLabel84.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Buscar");

        jTextField23.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField23.setText(" ");
        jTextField23.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        jTextField23.setSelectedTextColor(new java.awt.Color(151, 0, 0));
        jTextField23.setSelectionColor(new java.awt.Color(255, 255, 255));

        jButton11.setBackground(new java.awt.Color(0, 0, 50));
        jButton11.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Buscar");
        jButton11.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        tablaRepuestosEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(151, 0, 0)));
        tablaRepuestosEliminar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaRepuestosEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane30.setViewportView(tablaRepuestosEliminar);

        eliminarEliminar2.setBackground(new java.awt.Color(0, 0, 50));
        eliminarEliminar2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        eliminarEliminar2.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEliminar2.setText("Eliminar");
        eliminarEliminar2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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

        jLabel85.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Existentes");

        tablaRepuestosEliminados.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(151, 0, 0)));
        tablaRepuestosEliminados.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaRepuestosEliminados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane31.setViewportView(tablaRepuestosEliminados);

        seleccionBorrado2.setBackground(new java.awt.Color(0, 0, 50));
        seleccionBorrado2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        seleccionBorrado2.setForeground(new java.awt.Color(255, 255, 255));
        seleccionBorrado2.setText("Recuperar");
        seleccionBorrado2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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

        jLabel86.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Eliminados");

        javax.swing.GroupLayout eliminar2Layout = new javax.swing.GroupLayout(eliminar2);
        eliminar2.setLayout(eliminar2Layout);
        eliminar2Layout.setHorizontalGroup(
            eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(eliminarEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eliminar2Layout.createSequentialGroup()
                                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(seleccionBorrado2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel86))))
                .addContainerGap(508, Short.MAX_VALUE))
        );
        eliminar2Layout.setVerticalGroup(
            eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel84)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eliminar2Layout.createSequentialGroup()
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                            .addComponent(jScrollPane31))
                        .addGap(46, 46, 46))))
        );

        editaraClientes2.addTab("Eliminar REPUESTO", eliminar2);

        jLabel153.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(255, 255, 255));
        jLabel153.setText("Buscar");

        jTextField34.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        jTextField34.setText(" ");
        jTextField34.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        jButton59.setBackground(new java.awt.Color(0, 0, 50));
        jButton59.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton59.setForeground(new java.awt.Color(255, 255, 255));
        jButton59.setText("Buscar");
        jButton59.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));

        jButton60.setBackground(new java.awt.Color(0, 0, 50));
        jButton60.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton60.setForeground(new java.awt.Color(255, 255, 255));
        jButton60.setText("Seleccionar");
        jButton60.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
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

        tablaRepuestosModificar.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(151, 0, 0)));
        tablaRepuestosModificar.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        tablaRepuestosModificar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane54.setViewportView(tablaRepuestosModificar);

        jLabel159.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(255, 255, 255));
        jLabel159.setText("Seleccionado");

        jLabel160.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setText("Cantidad");

        jLabel161.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(255, 255, 255));
        jLabel161.setText("Precio");

        jLabel162.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(255, 255, 255));
        jLabel162.setText("Nombre");

        jButton61.setBackground(new java.awt.Color(0, 0, 50));
        jButton61.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton61.setForeground(new java.awt.Color(255, 255, 255));
        jButton61.setText("Guardar");
        jButton61.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton62.setBackground(new java.awt.Color(0, 0, 50));
        jButton62.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        jButton62.setForeground(new java.awt.Color(255, 255, 255));
        jButton62.setText("Cancelar");
        jButton62.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(151, 0, 0)));
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        bRepuestosModCodigo.setText(" ");
        bRepuestosModCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        bRepuestosModCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModCodigoActionPerformed(evt);
            }
        });

        jLabel164.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setText("Codigo");

        bRepuestosModNombre.setText(" ");
        bRepuestosModNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        bRepuestosModNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModNombreActionPerformed(evt);
            }
        });

        bRepuestosModCantidad.setText(" ");
        bRepuestosModCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));
        bRepuestosModCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRepuestosModCantidadActionPerformed(evt);
            }
        });

        bRepuestosModPrecio.setText(" ");
        bRepuestosModPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(151, 0, 0)));

        estado12.setFont(new java.awt.Font("Cambria Math", 1, 16)); // NOI18N
        estado12.setForeground(new java.awt.Color(255, 255, 255));
        estado12.setText("Estado: Seleccionando Repuesto");

        javax.swing.GroupLayout modificarLancha8Layout = new javax.swing.GroupLayout(modificarLancha8);
        modificarLancha8.setLayout(modificarLancha8Layout);
        modificarLancha8Layout.setHorizontalGroup(
            modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addComponent(jLabel153)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estado12, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel164)
                                    .addComponent(jLabel161)
                                    .addComponent(jLabel160)
                                    .addComponent(jLabel162))
                                .addGap(35, 35, 35)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bRepuestosModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bRepuestosModCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bRepuestosModPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bRepuestosModCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                                        .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        modificarLancha8Layout.setVerticalGroup(
            modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel153)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel159)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha8Layout.createSequentialGroup()
                        .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha8Layout.createSequentialGroup()
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel162)
                                    .addComponent(bRepuestosModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bRepuestosModCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel164))
                                .addGap(10, 10, 10)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel161)
                                    .addComponent(bRepuestosModPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bRepuestosModCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel160))
                                .addGap(18, 18, 18)
                                .addGroup(modificarLancha8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(estado12))
                    .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        editaraClientes2.addTab("Modificar REPUESTO", modificarLancha8);

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
                .addGap(578, 578, Short.MAX_VALUE))
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
                            .addComponent(jScrollPane38, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                            .addGroup(visualizar3Layout.createSequentialGroup()
                                .addComponent(seleccionarV6)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(visualizar3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        editaraClientes3.addTab("Visualizar", visualizar3);

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
            .addGroup(FondoLayout.createSequentialGroup()
                .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 1283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 782, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1283, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
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
            this.TablaContactosVsualizar.setText("  ==========================\n                       INFORMACION\n  =========================="
                    +cliente.getInformacionClienteVisualizar()
                    +cliente.getInformacionEmbarcaciones());
            this.jLabel12.setText("Viendo a: "+cliente.getApellidoNombre());
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
        this.horasAE1.setText("");
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
        this.horasAE.setText("");
    }
    public void vaciarBarrasD(){
        //barras Editar
        this.bNombreE1.setText("");
        this.bApellidoE1.setText("");
        this.bCorreoE1.setText("");
        this.bCelularE1.setText("");
        this.bTelFijE1.setText("");
        this.bTelFijoC10.setText("");
        this.bTelFijoC11.setText("");
        this.bEmpresaE1.setText("");
        this.bNumCuentaDistribuidorE1.setText("");
        
        //Barras Crear
        this.bNombreC1.setText("");
        this.bApellidoC1.setText("");
        this.bCorreoC1.setText("");
        this.bCelularC1.setText("");
        this.bTelFijoC7.setText("");
        this.bTelFijoC8.setText("");
        this.bTelFijoC9.setText("");
        this.bEmpresaC1.setText("");
        this.bNumCuentaC1.setText("");
        
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
        this.tTotal.setText("");
        this.bFechaOrdenesCrear8.setText("");
        this.mes.setText("");
        this.año.setText("");
        this.pHTrabajo.setText("");
        this.hTrabajo.setText("");
        this.tManoObra.setText("");
        this.tRepuestos.setText("");
        this.tVarios.setText("");
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
        this.obtenerClientesConDeuda();
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
    public void updateVDistribuidor(){
        this.vaciarTablasD();
        this.vaciarBarrasD();
        this.actualizarListadoDistribuidores();
        this.actualizarListadoDistribuidoresEliminados();
        this.setearTablasListadoDistribuidores();
        this.repaint();
    }
    private void contactosCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrearActionPerformed
       if(bNombreC.getText().length()!=0 && bApellidoC.getText().length()!=0 && (bCelularC.getText().length()!=0 || bCorreoC.getText().length()!=0)){ 
            String s=this.bNombreC.getText()+" "+this.bApellidoC.getText();
            if(!this.hashmapClientes.containsKey(s)){ 
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
            //this.hashmapClientesCodigo.put(Integer.toString(cliente.getNumCliente()),cliente.getApellidoNombre());
            //System.out.println("Creando: "+cliente.getApellidoNombre()+" Num: "+cliente.getNumCliente());
            this.crearArchivosContacto(cliente);
            JOptionPane.showMessageDialog(null,"Cliente: "+cliente.getNombreApellido()+" añadido exitosamente");
            cliente= null;
            this.updateVCliente();
            this.updateVOrden();
            }
            
       }
    }//GEN-LAST:event_contactosCrearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.vaciarBarrasC();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void eliminarEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminarMouseClicked

    private void eliminarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminarActionPerformed
        if(this.hashmapClientes.get(this.tablaContactosEliminar.getSelectedValue()) != null){
            clienteEliminar=this.hashmapClientes.get(this.tablaContactosEliminar.getSelectedValue());
            //System.out.println("______________________________\n Seleccionando eliminar a: "+clienteEliminar.getNumCliente());
            this.hashmapClientesEliminados.put(clienteEliminar.getApellidoNombre(),clienteEliminar);
            this.hashmapClientes.remove(clienteEliminar.getApellidoNombre(),clienteEliminar);
            this.eliminarCliente=true;
            try {
                this.moverCarpetasCliente(clienteEliminar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.eliminarCliente=false;
            this.updateVCliente();
            this.updateVOrden();
            this.updateVRepuesto();
             JOptionPane.showMessageDialog(null,"Cliente: "+clienteEliminar.getNombreApellido()+" eliminado exitosamente");
            clienteEliminar=null;
        }
    }//GEN-LAST:event_eliminarEliminarActionPerformed

    private void seleccionBorradoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorradoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorradoMouseClicked

    private void seleccionBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorradoActionPerformed
        if(this.hashmapClientesEliminados.get(this.tablacontactosEliminados.getSelectedValue()) != null){
            clienteRecuperar=this.hashmapClientesEliminados.get(this.tablacontactosEliminados.getSelectedValue());
            this.hashmapClientes.put(clienteRecuperar.getApellidoNombre(),clienteRecuperar);
            this.hashmapClientesEliminados.remove(clienteRecuperar.getApellidoNombre(),clienteRecuperar);
            this.recuperarCliente=true;
            try {
                this.moverCarpetasCliente(clienteRecuperar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.recuperarCliente=false;
            this.updateVCliente(); // TODO add your handling code here:
            this.updateVOrden();
            this.updateVRepuesto();
            JOptionPane.showMessageDialog(null,"Cliente: "+clienteRecuperar.getNombreApellido()+" recuperado exitosamente");
        }
        clienteRecuperar=null;
    }//GEN-LAST:event_seleccionBorradoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       this.vaciarBarrasC();
       this.jLabel13.setText("Seleccionado: ");
       cliente=null;
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       if(!bNombreE.getText().equals(clienteModificar.getNombre()) || !bApellidoE.getText().equals(clienteModificar.getApellido()) || !bCelularE.getText().equals(clienteModificar.getCelular()) 
            || !bTelFijE.getText().equals(clienteModificar.getTelFijo()) || !bCuidadorE.getText().equals(clienteModificar.getCuidador()) || !bCelCuidadorE.getText().equals(clienteModificar.getCelularCuidador()) || !bCorreoE.getText().equals(clienteModificar.getCorreo()) 
               || !bTelFijoC6.getText().equals(clienteModificar.getTelFijoOficina1()) || !bTelFijoC5.getText().equals(clienteModificar.getTelFijoOficina2())){ 
            String apNombrAnterior=clienteModificar.getApellidoNombre();
            Cliente aux=this.hashmapClientes.get(this.tablaModificarInformacion.getSelectedValue());
            clienteModificar.setNombe(this.bNombreE.getText());
            clienteModificar.setApellido(this.bApellidoE.getText());
            clienteModificar.setCorreo(this.bCorreoE.getText());
            clienteModificar.setCelular(this.bCelularE.getText());
            clienteModificar.setTelFijo(this.bTelFijE.getText());
            clienteModificar.setCuidador(this.bCuidadorE.getText());
            clienteModificar.setCelularCuidador(this.bCelCuidadorE.getText());
            System.out.println("1: "+this.hashmapClientes.get(this.tablaModificarInformacion.getSelectedValue()).getInformacionC());
            System.out.println("2: "+aux.getInformacionC());
            if(this.hashmapClientes.get(this.tablaModificarInformacion.getSelectedValue()).getInformacionC()!=clienteModificar.getInformacionC()){
                this.hashmapClientes.remove(apNombrAnterior);
                JOptionPane.showMessageDialog(null,"Cliente: "+clienteModificar.getNombreApellido()+" modificado exitosamente");
                this.hashmapClientes.put(clienteModificar.getApellidoNombre(), clienteModificar);
                this.modificarCliente=true;
                try {
                   this.moverCarpetasCliente(clienteModificar.getNumCliente());
               } catch (IOException ex) {
                   Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
               }
                this.modificarCliente=false;
                this.jLabel13.setText("Seleccionado: ");
                this.updateVCliente();
                this.updateVOrden();
                this.updateVRepuesto();
                clienteModificar=null;
       
            }
            
       }
       
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
      this.vaciarBarrasC();
      cliente=null;
      embarcacion=null;
      this.estado2.setText("Estado: Esperando que seleccione un cliente");
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
            embarcacion.setHoras(horasAE1.getText());
            cliente.addEmbarcacion( embarcacion);
            cliente.crearTxTEmbarcacion(embarcacion);
            this.hashmapClientes.remove(cliente.getApellidoNombre());
            this.hashmapClientes.put(cliente.getApellidoNombre(), cliente);
            cliente.actualizarListadoEmbarcaciones();
            //this.updateVCliente();
            this.tablaEmbarcaciones.setListData(cliente.getListadoEmbarcaciones());
            this.vaciarBarrasC();
            JOptionPane.showMessageDialog(null,"Embarcación: "+embarcacion.getTipoMarca()+" Editada Exitosamente");
              this.estado2.setText("Estado: Esperando que seleccione un cliente");
         }
         embarcacion=null;
         //this.seleccionado5.setText("-----------------------------");
         this.jLabel22.setText("Embarcacion: ");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void nSerie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie1ActionPerformed

    private void seleccionBorrado3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado3ActionPerformed
        if(this.tablaEmbarcaciones.getSelectedValue()!=null && cliente!=null){
            embarcacion=cliente.hashMapEmbarcaciones.get(this.tablaEmbarcaciones.getSelectedValue());
            this.jLabel22.setText("Embarcacion: "+embarcacion.getMotor());
            this.t.setText(embarcacion.getTipo());
            this.m.setText(embarcacion.getMarca());
            this.mot.setText(embarcacion.getMotor());
            this.mo.setText(embarcacion.getModelo());
            this.nSerie1.setText(embarcacion.getnSerie());
            this.claveEmb1.setText(embarcacion.getClaveLancha());
            this.horasAE1.setText(embarcacion.getHoras());
            this.estado2.setText("Estado: Editando Embarcacion ");
            this.añadiendolanchaa1.setText("Modificado Lancha a: "+cliente.getNombreApellido());
        }  
    }//GEN-LAST:event_seleccionBorrado3ActionPerformed

    private void seleccionBorrado3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado3MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
       if(this.tablaLanchasModificar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasModificar.getSelectedValue());
          this.añadiendolanchaa1.setText("Modificado Lancha a: "+cliente.getApellidoNombre());
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
           // this.añadiendolanchaa.setText("Añadiendo Lancha a: "+cliente.getApellidoNombre());
            this.estado.setText("Estado: Añadiendo Embarcación ");
        }
    }//GEN-LAST:event_seleccionarLanchaAgregarActionPerformed

    private void seleccionarLanchaAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregarMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.vaciarBarrasC();
        this.seleccionadoAgregar1.setText("Seleccionado: ");
        this.estado.setText("Estado: Seleccionando Cliente");
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
            embarcacion.setHoras(this.horasAE.getText());
            cliente.addEmbarcacion(embarcacion);
            cliente.crearTxTEmbarcacion(embarcacion);
            //System.out.println("Desopues de añadir: "+cliente.getInformacionE(embarcacion.getTipoMarca())+"/n "+embarcacion.codigo);
            this.estado.setText("Lancha: "+embarcacion.getMotor()+" añadida exitosamente");
            this.updateVCliente();  
            JOptionPane.showMessageDialog(null,"Embarcacion: "+embarcacion.getTipoMarca()+" añadida exitosamente");
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
            cliente.eliminarTxTEmbarcación(embarcacion.getCodigo());
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
        JOptionPane.showMessageDialog(null,"Embarcación: "+embarcacion.getTipoMarca()+" eliminada exitosamente");
        this.tablaEE.setListData(cliente.getListadoEmbarcaciones());
        embarcacion=null;
        cliente=null;
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
            
            //this.jLabel15.setText("Seleccionado: "+cliente.getApellidoNombre());
        }
    }//GEN-LAST:event_seleccionarV3ActionPerformed

    private void seleccionarV5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV5MouseClicked

    private void seleccionarV5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV5ActionPerformed
        if(this.tablaRepuestosVisualizar.getSelectedValue()!=null){
            repuesto=new Repuesto();
            repuesto=this.hashmapRepuestos.get(this.tablaRepuestosVisualizar.getSelectedValue());
            this.visualizarV2.setText("  ============================\n                                DETALLES\n  ============================"
                    +repuesto.getInformacionVisualizar()+"\n  ============================");
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

    private void seleccionarV7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV7MouseClicked

    private void seleccionarV7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV7ActionPerformed
        if(this.tablaOrdenesVisualizar2.getSelectedValue()!=null){
           
            cliente=this.hashmapClientes.get(this.tablaOrdenesVisualizar2.getSelectedValue());
            cliente.actualizarListadoOrdenes();
            this.tablaOrdenesVisualizar3.setListData(cliente.getOrdenesNoCanceladas());
            this.tablaOrdenesVisualizar4.setListData(cliente.getOrdenesCanceladas());
            this.repaint();
            
            //this.jLabel15.setText("Seleccionado: "+cliente.getApellidoNombre());
        }
        
        
    }//GEN-LAST:event_seleccionarV7ActionPerformed

    private void seleccionarV8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV8MouseClicked

    private void seleccionarV8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV8ActionPerformed
        orden=cliente.hashMapOrdenes.get(this.tablaOrdenesVisualizar3.getSelectedValue());
        this.ordenNC=true;
        this.ordenC=false;
        jLabel49.setText("Orden Seleccionada: "+orden.getOrdenID());
        this.visualizarV5.setText(orden.getInformacion());
        repaint();
    }//GEN-LAST:event_seleccionarV8ActionPerformed

    private void seleccionarV9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV9MouseClicked

    private void seleccionarV9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV9ActionPerformed
        //orden.setCancelado();
        //ordenAux=cliente.hashMapOrdenes.get(orden.getOrdenID());
        //orden=cliente.hashMapOrdenes.get(orden.getOrdenID());
        cliente.hashMapOrdenes.get(orden.getOrdenID()).setCanceladoTrue();
        //orden.setCanceladoTrue();
        cliente.actualizarListadoOrdenes();
        this.tablaOrdenesVisualizar3.setListData(cliente.getOrdenesNoCanceladas());
        this.tablaOrdenesVisualizar4.setListData(cliente.getOrdenesCanceladas());
        this.visualizarV5.setText(cliente.hashMapOrdenes.get(orden.getOrdenID()).getInformacion());
        repaint();
        
    }//GEN-LAST:event_seleccionarV9ActionPerformed

    private void seleccionarV10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV10MouseClicked

    private void seleccionarV10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV10ActionPerformed
        //cliente.hashMapOrdenes.put(this.ordenAux.getOrdenID(),this.ordenAux);
        cliente.hashMapOrdenes.get(orden.getOrdenID()).setCanceladoFalse();
        cliente.actualizarListadoOrdenes();
        this.tablaOrdenesVisualizar3.setListData(cliente.getOrdenesNoCanceladas());
        this.tablaOrdenesVisualizar4.setListData(cliente.getOrdenesCanceladas());
        this.visualizarV5.setText(cliente.hashMapOrdenes.get(orden.getOrdenID()).getInformacion());
        repaint();
        
        
        
    }//GEN-LAST:event_seleccionarV10ActionPerformed

    private void seleccionarV11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV11MouseClicked

    private void seleccionarV11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV11ActionPerformed
        //cliente.hashMapOrdenes.put(this.orden.getOrdenID(),this.ordenAux);
        //cliente.actualizarListadoOrdenes();
        //this.tablaOrdenesVisualizar3.setListData(cliente.getOrdenesNoCanceladas());
        System.out.println(cliente.hashMapOrdenes.get(orden.getOrdenID()).cancelado+"  "+orden.cancelado);
        if(true==ordenC && cliente.hashMapOrdenes.get(orden.getOrdenID()).cancelado==false){
            File file = new File("Clientes/"+cliente.getNumCliente()+"/Ordenes/"+cliente.hashMapOrdenes.get(orden.getOrdenID()).getNumeroOrden()+".txt");
            System.out.println("Clientes/"+cliente.getNumCliente()+"/Ordenes/"+cliente.hashMapOrdenes.get(orden.getOrdenID()).getNumeroOrden()+".txt");
            file.delete();
            cliente.crearTxTOrdenes(cliente.hashMapOrdenes.get(orden.getOrdenID()));
            repaint();
            JOptionPane.showMessageDialog(null,"Cambios guardados exitosamente");
            ordenC=false;
        }
        if(true==ordenNC && cliente.hashMapOrdenes.get(orden.getOrdenID()).cancelado==true){
            File file = new File("Clientes/"+cliente.getNumCliente()+"/Ordenes/"+cliente.hashMapOrdenes.get(orden.getOrdenID()).getNumeroOrden()+".txt");
            System.out.println("Clientes/"+cliente.getNumCliente()+"/Ordenes/"+cliente.hashMapOrdenes.get(orden.getOrdenID()).getNumeroOrden()+".txt");
            file.delete();
            cliente.crearTxTOrdenes(cliente.hashMapOrdenes.get(orden.getOrdenID()));
            repaint();
            JOptionPane.showMessageDialog(null,"Cambios guardados exitosamente");
            ordenC=false;
        }
        else{
            //JOptionPane.showMessageDialog(null,"No hay cambios que guardar");
        }
    }//GEN-LAST:event_seleccionarV11ActionPerformed

    private void seleccionarV12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV12MouseClicked

    private void seleccionarV12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV12ActionPerformed
        this.ordenNC=false;
        this.ordenC=true;
        orden=cliente.hashMapOrdenes.get(this.tablaOrdenesVisualizar4.getSelectedValue());
        jLabel49.setText("Orden Seleccionada: "+orden.getOrdenID());
        this.visualizarV5.setText(orden.getInformacion());
        repaint();
    }//GEN-LAST:event_seleccionarV12ActionPerformed

    private void seleccionarV13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV13MouseClicked

    private void seleccionarV13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV13ActionPerformed

    private void horasAEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horasAEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horasAEActionPerformed

    private void horasAE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horasAE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horasAE1ActionPerformed

    private void seleccionarV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV1MouseClicked

    private void seleccionarV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV1ActionPerformed
       if(this.tablacontactosVisualizar1.getSelectedValue()!=null){
           distribuidor=this.hashmapDistribuidores.get(this.tablacontactosVisualizar1.getSelectedValue());
            this.TablaContactosVsualizar1.setText("  ============================\n                         INFORMACION\n  ============================"
                    +distribuidor.getInformacionDistribuidorVisualizar());
            this.jLabel53.setText("Seleccionado: "+distribuidor.getApellidoNombre());
            distribuidor=null;
        }
    }//GEN-LAST:event_seleccionarV1ActionPerformed

    private void bNombreC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreC1ActionPerformed

    private void bApellidoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoC1ActionPerformed

    private void bTelFijoC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC7ActionPerformed

    private void bCorreoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoC1ActionPerformed

    private void bNumCuentaC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNumCuentaC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNumCuentaC1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void contactosCrear6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear6ActionPerformed
       if(bNombreC1.getText().length()!=0 && bApellidoC1.getText().length()!=0 && (bCelularC1.getText().length()!=0 || bCorreoC1.getText().length()!=0)){ 
            String s=this.bNombreC1.getText()+" "+this.bApellidoC1.getText();
            if(!this.hashmapDistribuidores.containsKey(s)){
                distribuidor=new Distribuidor();
                distribuidor.setNombre(this.bNombreC1.getText());
                distribuidor.setApellido(this.bApellidoC1.getText());
                distribuidor.setCelular(this.bCelularC1.getText());

                if(this.bTelFijoC6.getText().length()!=0){
                distribuidor.setTelFijo(this.bTelFijoC6.getText());}
                if(this.bTelFijoC7.getText().length()!=0){
                distribuidor.setTelFijoOficina1(this.bTelFijoC7.getText());}
                if(this.bTelFijoC8.getText().length()!=0){
                distribuidor.setTelFijoOficina2(this.bTelFijoC8.getText());}
                if(this.bEmpresaC1.getText().length()!=0){
                distribuidor.setEmpresa(this.bEmpresaC1.getText());}
                if(this.bNumCuentaC1.getText().length()!=0){
                distribuidor.setCuentaDeposito(this.bNumCuentaC1.getText());}
                if(this.bCorreoC1.getText().length()!=0){
                distribuidor.setCorreo(this.bCorreoC1.getText());}

                distribuidor.setNumCliente(this.hashmapDistribuidores.size()+this.hashmapDistribuidoresEliminados.size());
                this.hashmapDistribuidores.put(distribuidor.getApellidoNombre(),distribuidor);
                //this.hashmapClientesCodigo.put(Integer.toString(cliente.getNumCliente()),cliente.getApellidoNombre());
                //System.out.println("Creando: "+cliente.getApellidoNombre()+" Num: "+cliente.getNumCliente());
                this.crearArchivosDistribuidor(distribuidor);
                JOptionPane.showMessageDialog(null,"Distribuidor: "+distribuidor.getNombreApellido()+" añadido exitosamente");
                distribuidor= null;
                this.updateVDistribuidor();
                }
            }
    }//GEN-LAST:event_contactosCrear6ActionPerformed

    private void bTelFijoC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC8ActionPerformed

    private void bTelFijoC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC9ActionPerformed

    private void eliminarEliminar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminar4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar4MouseClicked

    private void eliminarEliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminar4ActionPerformed
       if(this.tablaContactosEliminar1.getSelectedValue()!=null){
            distribuidorEliminar=this.hashmapDistribuidores.get(this.tablaContactosEliminar1.getSelectedValue());
            //System.out.println("______________________________\n Seleccionando eliminar a: "+clienteEliminar.getNumCliente());
        }
        if(distribuidorEliminar!=null){
            this.hashmapDistribuidoresEliminados.put(distribuidorEliminar.getApellidoNombre(),distribuidorEliminar);
            this.hashmapDistribuidores.remove(distribuidorEliminar.getApellidoNombre(),distribuidorEliminar);
            this.eliminarDistribuidor=true;
            try {
                this.moverCarpetasDistribuidor(distribuidorEliminar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.eliminarDistribuidor=false;
            this.updateVDistribuidor();
             JOptionPane.showMessageDialog(null,"Distribuidor: "+distribuidorEliminar.getNombreApellido()+" eliminado exitosamente");
            distribuidorEliminar=null;
        }
    }//GEN-LAST:event_eliminarEliminar4ActionPerformed

    private void seleccionBorrado7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado7MouseClicked

    private void seleccionBorrado7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado7ActionPerformed
      if(this.tablacontactosEliminados2.getSelectedValue()!=null){
            distribuidorRecuperar=this.hashmapDistribuidoresEliminados.get(this.tablacontactosEliminados2.getSelectedValue());
        }
        if(distribuidorRecuperar!=null){
            this.hashmapDistribuidores.put(distribuidorRecuperar.getApellidoNombre(),distribuidorRecuperar);
            this.hashmapDistribuidoresEliminados.remove(distribuidorRecuperar.getApellidoNombre(),distribuidorRecuperar);
            this.recuperarDistribuidor=true;
            try {
                this.moverCarpetasDistribuidor(distribuidorRecuperar.getNumCliente());
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.recuperarDistribuidor=false;
            this.updateVDistribuidor(); // TODO add your handling code here:
            JOptionPane.showMessageDialog(null,"Distribuidor: "+distribuidorRecuperar.getNombreApellido()+" recuperado exitosamente");
        }
        distribuidorRecuperar=null;
    }//GEN-LAST:event_seleccionBorrado7ActionPerformed

    private void bNombreE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreE1ActionPerformed

    private void bApellidoE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoE1ActionPerformed

    private void bTelFijE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijE1ActionPerformed

    private void bCorreoE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoE1ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void seleccionarModificarInfo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo1MouseClicked

    private void seleccionarModificarInfo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo1ActionPerformed

    private void bTelFijoC10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC10ActionPerformed

    private void bTelFijoC11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC11ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void seleccionarModificarInfo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo10ActionPerformed
        if(cliente!=null && this.tablaOrdenesCrear2.getSelectedValue()!=null){
            embarcacion=cliente.hashMapEmbarcaciones.get(this.tablaOrdenesCrear2.getSelectedValue());
            this.jLabel36.setText(embarcacion.getTipoMarca());

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
                orden.setNota(" - Sin Nota");
            }
            if(this.ordenesCrearVarios.getText().length()!=0){
                orden.setVarios(this.ordenesCrearVarios.getText());
            }
            else{
                orden.setVarios(" - Sin Varios");
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

        }
    }//GEN-LAST:event_contactosCrear5ActionPerformed

    private void calcularRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRepuestosActionPerformed
        int repu=0;
        this.totalRepuestos=0;
        if(!this.arrayListOrdenesAñadirRepuestosCliente.isEmpty() ){
            for(int h=0;h<this.arrayListOrdenesAñadirRepuestosCliente.size();h++){
                repuesto=this.arrayListOrdenesAñadirRepuestosCliente.get(h);
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
            this.jLabel148.setText(cliente.getApellidoNombre());
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
            //this.hashMapOrdenes.put(orden.getNumeroOrden(),orden);
            // orden.setNumTxT(Integer.toString(cliente.ordenes.size()));
            cliente.crearTxTOrdenes(orden);
            cliente.addOrden(orden);
            cliente.setDeudaOrdenSi();
            JOptionPane.showMessageDialog(null,"Orden:"+orden.getOrdenID()+" añadida exitosamente");
            this.vaciarTablasO();
            this.vaciarBarrasO();
            this.setearTablasListadoOrdenes();
            //this.updateVOrden();
        }
    }//GEN-LAST:event_contactosCrear1ActionPerformed

    private void diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaActionPerformed

    private void hTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hTrabajoActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
 
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
            try {
                new Interfaz().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clientes;
    private javax.swing.JPanel Clientes2;
    private javax.swing.JPanel CrearOrden;
    private javax.swing.JPanel Distribuidores;
    private javax.swing.JPanel EliminarOrden;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Guarderias;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel ModificarC;
    private javax.swing.JPanel ModificarC1;
    private javax.swing.JPanel OrdInpagas;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel Repuestos;
    private javax.swing.JTextArea TablaContactosVsualizar;
    private javax.swing.JTextArea TablaContactosVsualizar1;
    private javax.swing.JPanel VisualizarOrden;
    private javax.swing.JPanel agregarLancha;
    private javax.swing.JLabel añadiendolanchaa;
    private javax.swing.JLabel añadiendolanchaa1;
    private javax.swing.JTextField año;
    private javax.swing.JTextField bApellidoC;
    private javax.swing.JTextField bApellidoC1;
    private javax.swing.JTextField bApellidoE;
    private javax.swing.JTextField bApellidoE1;
    private javax.swing.JTextField bCelCuidadorC;
    private javax.swing.JTextField bCelCuidadorE;
    private javax.swing.JTextField bCelularC;
    private javax.swing.JTextField bCelularC1;
    private javax.swing.JTextField bCelularC2;
    private javax.swing.JTextField bCelularE;
    private javax.swing.JTextField bCelularE1;
    private javax.swing.JTextField bCorreoC;
    private javax.swing.JTextField bCorreoC1;
    private javax.swing.JTextField bCorreoC2;
    private javax.swing.JTextField bCorreoE;
    private javax.swing.JTextField bCorreoE1;
    private javax.swing.JTextField bCuidadorC;
    private javax.swing.JTextField bCuidadorC2;
    private javax.swing.JTextField bCuidadorE;
    private javax.swing.JTextField bEmpresaC1;
    private javax.swing.JTextField bEmpresaE1;
    private javax.swing.JTextField bFechaOrdenesCrear1;
    private javax.swing.JTextField bFechaOrdenesCrear8;
    private javax.swing.JTextField bNombreC;
    private javax.swing.JTextField bNombreC1;
    private javax.swing.JTextField bNombreE;
    private javax.swing.JTextField bNombreE1;
    private javax.swing.JTextField bNumCuentaC1;
    private javax.swing.JTextField bNumCuentaDistribuidorE1;
    private javax.swing.JTextField bRepuestosModCantidad;
    private javax.swing.JTextField bRepuestosModCodigo;
    private javax.swing.JTextField bRepuestosModNombre;
    private javax.swing.JTextField bRepuestosModPrecio;
    private javax.swing.JTextField bTelFijE;
    private javax.swing.JTextField bTelFijE1;
    private javax.swing.JTextField bTelFijoC;
    private javax.swing.JTextField bTelFijoC1;
    private javax.swing.JTextField bTelFijoC10;
    private javax.swing.JTextField bTelFijoC11;
    private javax.swing.JTextField bTelFijoC2;
    private javax.swing.JTextField bTelFijoC4;
    private javax.swing.JTextField bTelFijoC5;
    private javax.swing.JTextField bTelFijoC6;
    private javax.swing.JTextField bTelFijoC7;
    private javax.swing.JTextField bTelFijoC8;
    private javax.swing.JTextField bTelFijoC9;
    private javax.swing.JButton calcularRepuestos;
    private javax.swing.JButton calcularRepuestos1;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField claveEmb;
    private javax.swing.JTextField claveEmb1;
    private javax.swing.JButton contactosCrear;
    private javax.swing.JButton contactosCrear1;
    private javax.swing.JButton contactosCrear2;
    private javax.swing.JButton contactosCrear4;
    private javax.swing.JButton contactosCrear5;
    private javax.swing.JButton contactosCrear6;
    private javax.swing.JPanel crear;
    private javax.swing.JPanel crear1;
    private javax.swing.JPanel crear2;
    private javax.swing.JTextField dia;
    private javax.swing.JTabbedPane editaraClientes;
    private javax.swing.JTabbedPane editaraClientes1;
    private javax.swing.JTabbedPane editaraClientes2;
    private javax.swing.JTabbedPane editaraClientes3;
    private javax.swing.JTabbedPane editaraClientes4;
    private javax.swing.JTabbedPane editaraClientes5;
    private javax.swing.JPanel eliminar;
    private javax.swing.JPanel eliminar1;
    private javax.swing.JPanel eliminar2;
    private javax.swing.JButton eliminarEliminar;
    private javax.swing.JButton eliminarEliminar1;
    private javax.swing.JButton eliminarEliminar2;
    private javax.swing.JButton eliminarEliminar4;
    private javax.swing.JTabbedPane eliminarLanchaa;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel estado1;
    private javax.swing.JLabel estado12;
    private javax.swing.JLabel estado2;
    private javax.swing.JButton guardarLancha;
    private javax.swing.JTextField hTrabajo;
    private javax.swing.JTextField horasAE;
    private javax.swing.JTextField horasAE1;
    private javax.swing.JPanel info;
    private javax.swing.JPanel info1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
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
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
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
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
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
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
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
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JPanel lanch;
    private javax.swing.JTextField m;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField mes;
    private javax.swing.JTextField mo;
    private javax.swing.JTextField modelo;
    private javax.swing.JPanel modificarLancha;
    private javax.swing.JPanel modificarLancha1;
    private javax.swing.JPanel modificarLancha8;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField motor;
    private javax.swing.JLabel nCuentaDistribuidor;
    private javax.swing.JTextField nSerie1;
    private javax.swing.JTextField numeroDeSerie;
    private javax.swing.JTextPane ordenesCrearNota;
    private javax.swing.JTextPane ordenesCrearVarios;
    private javax.swing.JTextField pHTrabajo;
    private javax.swing.JLabel sel;
    private javax.swing.JButton seleccionBorrado;
    private javax.swing.JButton seleccionBorrado1;
    private javax.swing.JButton seleccionBorrado2;
    private javax.swing.JButton seleccionBorrado3;
    private javax.swing.JButton seleccionBorrado4;
    private javax.swing.JButton seleccionBorrado5;
    private javax.swing.JButton seleccionBorrado6;
    private javax.swing.JButton seleccionBorrado7;
    private javax.swing.JLabel seleccionadoAgregar1;
    private javax.swing.JButton seleccionarLanchaAgregar;
    private javax.swing.JButton seleccionarModificarInfo;
    private javax.swing.JButton seleccionarModificarInfo1;
    private javax.swing.JButton seleccionarModificarInfo10;
    private javax.swing.JButton seleccionarModificarInfo13;
    private javax.swing.JButton seleccionarModificarInfo5;
    private javax.swing.JButton seleccionarModificarInfo6;
    private javax.swing.JButton seleccionarModificarInfo7;
    private javax.swing.JButton seleccionarModificarInfo8;
    private javax.swing.JButton seleccionarModificarInfo9;
    private javax.swing.JButton seleccionarV;
    private javax.swing.JButton seleccionarV1;
    private javax.swing.JButton seleccionarV10;
    private javax.swing.JButton seleccionarV11;
    private javax.swing.JButton seleccionarV12;
    private javax.swing.JButton seleccionarV13;
    private javax.swing.JButton seleccionarV3;
    private javax.swing.JButton seleccionarV4;
    private javax.swing.JButton seleccionarV5;
    private javax.swing.JButton seleccionarV6;
    private javax.swing.JButton seleccionarV7;
    private javax.swing.JButton seleccionarV8;
    private javax.swing.JButton seleccionarV9;
    private javax.swing.JTextField t;
    private javax.swing.JTextField tManoObra;
    private javax.swing.JTextField tRepuestos;
    private javax.swing.JTextField tTotal;
    private javax.swing.JTextField tVarios;
    public javax.swing.JList<String> tablaContactosEliminar;
    public javax.swing.JList<String> tablaContactosEliminar1;
    public javax.swing.JList<String> tablaEE;
    public javax.swing.JList<String> tablaEmbarcaciones;
    public javax.swing.JList<String> tablaLanchasAgregar;
    public javax.swing.JList<String> tablaLanchasEliminar;
    public javax.swing.JList<String> tablaLanchasModificar;
    public javax.swing.JList<String> tablaModificarInformacion;
    public javax.swing.JList<String> tablaModificarInformacion1;
    public javax.swing.JList<String> tablaOdenCrearAñadirRepuestos;
    public javax.swing.JList<String> tablaOrdenesCrear;
    public javax.swing.JList<String> tablaOrdenesCrear2;
    public javax.swing.JList<String> tablaOrdenesCrearManoObra;
    public javax.swing.JList<String> tablaOrdenesCrearVPMO;
    public javax.swing.JList<String> tablaOrdenesCrearVPRepuestos;
    public javax.swing.JList<String> tablaOrdenesV2;
    public javax.swing.JList<String> tablaOrdenesVisualizar;
    public javax.swing.JList<String> tablaOrdenesVisualizar1;
    public javax.swing.JList<String> tablaOrdenesVisualizar2;
    public javax.swing.JList<String> tablaOrdenesVisualizar3;
    public javax.swing.JList<String> tablaOrdenesVisualizar4;
    public javax.swing.JList<String> tablaRepuestosEliminados;
    public javax.swing.JList<String> tablaRepuestosEliminar;
    public javax.swing.JList<String> tablaRepuestosModificar;
    public javax.swing.JList<String> tablaRepuestosVisualizar;
    public javax.swing.JList<String> tablacontactosEliminados;
    public javax.swing.JList<String> tablacontactosEliminados1;
    public javax.swing.JList<String> tablacontactosEliminados2;
    public javax.swing.JList<String> tablacontactosEliminar1;
    public javax.swing.JList<String> tablacontactosVisualizar;
    public javax.swing.JList<String> tablacontactosVisualizar1;
    private javax.swing.JTextField tipo;
    private javax.swing.JPanel visualizar;
    private javax.swing.JPanel visualizar1;
    private javax.swing.JPanel visualizar2;
    private javax.swing.JPanel visualizar3;
    private javax.swing.JTextArea visualizarV1;
    private javax.swing.JTextArea visualizarV2;
    private javax.swing.JTextArea visualizarV3;
    private javax.swing.JTextArea visualizarV4;
    private javax.swing.JTextArea visualizarV5;
    // End of variables declaration//GEN-END:variables
}
