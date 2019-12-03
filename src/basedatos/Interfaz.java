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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    File fNuevo = new File("Clientes.txt");
    //clientes
    Embarcacion embarcacion= new Embarcacion();
    Cliente cliente= new Cliente();
    Orden orden= new Orden();
    //Clientes
    HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
    ArrayList<String> arrayListContactos=new ArrayList<>();
    String [] listadoClientes = new String[1000];
    //Clientes borrados
    HashMap<String ,Cliente> hashmapClientesBorrados= new HashMap<>();
    ArrayList<String> arrayListContactosEliminados=new ArrayList<>();
    String [] listadoClientesEliminados = new String[1000];	
    //Ordenes
    HashMap<String ,Orden> hashmapOrdenes= new HashMap<>();
    ArrayList<String> arrayListOrdenes=new ArrayList<>();
    String [] listadoOrdenes = new String[1000];
    
    HashMap<String ,Orden> hashmapOrdenesEliminadas= new HashMap<>();
    ArrayList<String> arrayListOrdenesEliminadas=new ArrayList<>();
    String [] listadoOrdenesEliminadas = new String[1000];	

     //Embarcaciones
     
     public Interfaz() {
         
        this.setVisible(true);
        this.initComponents();
        
        this.cargarClientes();
        this.cargarClientesEliminados();
        
        this.cargarOrdenes();
        this.updateVOrden();
        
        this.updateVCliente();
        actualizarTXTContactos();
    }
        
    /**
     * Creates new form Main
     */
    public void actualizarTXTContactos(){
          try {
            String ruta = "Clientes.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
              try (BufferedWriter bw = new BufferedWriter(fw)) {
                    for(int f=0;f<this.arrayListContactos.size();f++){
                        Cliente cl=this.hashmapClientes.get(this.arrayListContactos.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cl.getInformacionC().size();l++){
                            bw.write((String) cl.getInformacionC().get(l));
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
    public void cargarClientes(){
            File f = new File( "Clientes.txt" ); 
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
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cl.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cl.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cl.setCelularCuidador(linea);
                        this.hashmapClientes.put(cl.getApellidoNombre(),cl);
                        linea = entrada.readLine(); 
                    }
                    if("*".equals(linea)){
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
                        cl.addEmbarcacion(embarcacion.getMotor(), embarcacion);
                    }
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
            
            while(entrada.ready()){ 
                linea = entrada.readLine();
                while(!"##".equals(linea)&& entrada.ready()){
                    if("#".equals(linea)){
                        or= new Orden();
                        linea = entrada.readLine(); 
                        or.setNumeroOrden(linea);
                        linea = entrada.readLine(); 
                        or.setTotalManoObra(linea);
                        linea = entrada.readLine(); 
                        or.setTotalVarios(linea);
                        linea = entrada.readLine(); 
                        or.setTotal(linea);
                        linea = entrada.readLine(); 
                        or.setCliente(linea);
                        linea = entrada.readLine(); 
                        or.setFecha(linea);
                        linea = entrada.readLine(); 
                        String nota=""; 
                        System.out.println("lin: "+linea);
                        while(!"*".equals(linea)){
                            nota+=" "+linea+"\n";
                            linea = entrada.readLine();
                        }
                        System.out.println(nota);
                        or.setNota(nota);
                    }
                    if("*".equals(linea)){
                        linea = entrada.readLine(); 
                        or.manoObra=new ArrayList<>();
                        while(!"**".equals(linea)){
                            or.addManoObra(linea);
                            linea = entrada.readLine(); 
                        }
                    }
                    String cant="";
                    while(!"***".equals(cant)){
                        linea = entrada.readLine();
                        cant = linea;
                        linea = entrada.readLine();
                        if(!"***".equals(cant)){
                           or.addRepuesto(cant, linea);
                        }
                    }
                }
                this.hashmapOrdenes.put(or.getNumeroOrden(),or);
                this.arrayListOrdenes.add(or.getNumeroOrden());
                System.out.println(or.getInformacion());
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
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        //creamos cliente
                        Cliente cl= new Cliente();
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
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cl.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cl.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cl.setCelularCuidador(linea);
                        this.hashmapClientesBorrados.put(cl.getApellidoNombre(),cl);
                        linea = entrada.readLine(); 
                    }
                    if("*".equals(linea)){
                        Embarcacion emb=new Embarcacion();
                        linea = entrada.readLine(); 
                        emb.setTipo(linea);
                        linea = entrada.readLine(); 
                        emb.setMarca(linea);
                        linea = entrada.readLine(); 
                        emb.setModelo(linea);
                        linea = entrada.readLine(); 
                        emb.setMotor(linea);
                        linea = entrada.readLine(); 
                        emb.setnSerie(linea);
                        linea = entrada.readLine(); 
                        cliente.addEmbarcacion(emb.getMotor(), emb);
                    }
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
    
    public void actualizarOrdenes(){
        listadoOrdenes=new String[1000];
        this.arrayListOrdenes=new  ArrayList<>();
        hashmapOrdenes.entrySet().forEach((Map.Entry<String, Orden> entry) -> {
            Interfaz.this.arrayListOrdenes.add(entry.getValue().getNumeroOrden());
            Collections.sort(Interfaz.this.arrayListOrdenes, String::compareTo);
        });
        for (int g=0;g<this.arrayListOrdenes.size();g++) {
            listadoOrdenes[g]=this.arrayListOrdenes.get(g);
            System.out.println("\nExistente: "+this.listadoOrdenes[g]);
        }
    }

    public void actualizarContactos(){
        listadoClientes=new String[1000];
        this.arrayListContactos=new  ArrayList<>();
        hashmapClientes.entrySet().forEach((Map.Entry<String, Cliente> entry) -> {
            Interfaz.this.arrayListContactos.add(entry.getValue().getApellidoNombre());
            Collections.sort(Interfaz.this.arrayListContactos, String::compareTo);
        });
        for (int g=0;g<this.arrayListContactos.size();g++) {
            listadoClientes[g]=this.arrayListContactos.get(g);
            //System.out.println("\nExistente: "+this.listadoClientes[g]);
        }
    }
    public void actualizarContactosEliminados(){
        listadoClientesEliminados=new String[1000];
        this.arrayListContactosEliminados=new  ArrayList<>();
        for (Map.Entry<String, Cliente> entry : hashmapClientesBorrados.entrySet()) {
            this.arrayListContactosEliminados.add(entry.getValue().getApellidoNombre());
        }
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
        this.tablacontactosEliminar.setListData(listadoClientes);
        
    }
    public void setearTablasListadoOrdenes(){
        //this.tablacontactosEditarLanchas.setListData(listadoClientes);
        this.tablaLanchasEliminar1.setListData(listadoOrdenes);
        this.tablaLanchasModificar1.setListData(listadoOrdenes);
        this.tablaLanchasAgregar1.setListData(listadoOrdenes);
        this.tablacontactosEliminados1.setListData(listadoOrdenesEliminadas);
        this.tablaOrdenesVisualizar.setListData(listadoOrdenes);
        this.tablaModificarInformacion1.setListData(listadoOrdenes);
        this.tablacontactosEliminar1.setListData(listadoOrdenes);
        
    }
    public void vaciarTablasO(){
        String [] vacio = new String[1000];
        this.tablaOrdenesVisualizar.setListData(vacio);
        this.tablaModificarInformacion1.setListData(vacio);
        this.tablacontactosEliminar1.setListData(vacio);
        this.tablaEmbarcaciones1.setListData(vacio);
        this.tablaLanchasAgregar1.setListData(vacio);
    }
    public void vaciarTablasC(){
        String [] vacio = new String[1000];
        this.tablacontactosVisualizar.setListData(vacio);
        this.tablaModificarInformacion.setListData(vacio);
        this.tablacontactosEliminar.setListData(vacio);
        this.tablaEmbarcaciones.setListData(vacio);
        this.tablaLanchasAgregar.setListData(vacio);
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
        eliminar = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablacontactosEliminar = new javax.swing.JList<>();
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
        visualizar1 = new javax.swing.JPanel();
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
        eliminar1 = new javax.swing.JPanel();
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
        editar2 = new javax.swing.JPanel();
        tablaClienteLanchas1 = new javax.swing.JTabbedPane();
        info1 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        bNombreE1 = new javax.swing.JTextField();
        bApellidoE1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        bCelularE1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        bTelFijE1 = new javax.swing.JTextField();
        bCorreoE1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        bCuidadorE1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        bCelCuidadorE1 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        seleccionarModificarInfo1 = new javax.swing.JButton();
        jTextField18 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tablaModificarInformacion1 = new javax.swing.JList<>();
        lanch1 = new javax.swing.JPanel();
        eliminarLanchaa1 = new javax.swing.JTabbedPane();
        agregarLancha1 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        tipo1 = new javax.swing.JTextField();
        marca1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        modelo1 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        motor1 = new javax.swing.JTextField();
        nSerie2 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        guardarLancha1 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        seleccionadoAgregar2 = new javax.swing.JLabel();
        seleccionarLanchaAgregar1 = new javax.swing.JButton();
        jTextField19 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        tablaLanchasAgregar1 = new javax.swing.JList<>();
        estado3 = new javax.swing.JLabel();
        añadiendolanchaa1 = new javax.swing.JLabel();
        modificarLancha2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        tablaLanchasModificar1 = new javax.swing.JList<>();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tablaEmbarcaciones1 = new javax.swing.JList<>();
        seleccionBorrado7 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        nSerie3 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        m1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        mot1 = new javax.swing.JTextField();
        mo1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        estado4 = new javax.swing.JLabel();
        modificarLancha3 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        tablaLanchasEliminar1 = new javax.swing.JList<>();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tablaEE1 = new javax.swing.JList<>();
        seleccionBorrado9 = new javax.swing.JButton();
        sel1 = new javax.swing.JLabel();
        seleccionBorrado10 = new javax.swing.JButton();
        crear1 = new javax.swing.JPanel();
        bFechaOrdenesCrear = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        contactosCrear1 = new javax.swing.JButton();
        jScrollPane49 = new javax.swing.JScrollPane();
        tablaOrdenesCrearManoObra = new javax.swing.JList<>();
        jLabel148 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane50 = new javax.swing.JScrollPane();
        tablaOrdenesCrear1 = new javax.swing.JList<>();
        seleccionarModificarInfo5 = new javax.swing.JButton();
        jLabel149 = new javax.swing.JLabel();
        bFechaOrdenesCrear1 = new javax.swing.JTextField();
        seleccionarModificarInfo6 = new javax.swing.JButton();
        jScrollPane51 = new javax.swing.JScrollPane();
        tablaOdenCrearRepuestos = new javax.swing.JList<>();
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
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        bFechaOrdenesCrear2 = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        bFechaOrdenesCrear3 = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        seleccionarModificarInfo13 = new javax.swing.JButton();
        bFechaOrdenesCrear5 = new javax.swing.JTextField();
        jScrollPane26 = new javax.swing.JScrollPane();
        visualizarV4 = new javax.swing.JTextArea();
        Repuestos = new javax.swing.JPanel();
        editaraClientes2 = new javax.swing.JTabbedPane();
        visualizar2 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tablaOrdenesV1 = new javax.swing.JList<>();
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
        jLabel83 = new javax.swing.JLabel();
        bCelCuidadorC2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        contactosCrear2 = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        tablaRepuestosCrear = new javax.swing.JList<>();
        jLabel147 = new javax.swing.JLabel();
        eliminar2 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        tablacontactosEliminar4 = new javax.swing.JList<>();
        eliminarEliminar2 = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tablacontactosEliminados2 = new javax.swing.JList<>();
        seleccionBorrado2 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        editar3 = new javax.swing.JPanel();
        tablaClienteLanchas2 = new javax.swing.JTabbedPane();
        info2 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        bNombreE2 = new javax.swing.JTextField();
        bApellidoE2 = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        bCelularE2 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        bTelFijE2 = new javax.swing.JTextField();
        bCorreoE2 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        bCuidadorE2 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        bCelCuidadorE2 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        seleccionarModificarInfo2 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jScrollPane32 = new javax.swing.JScrollPane();
        tablaModificarInformacion2 = new javax.swing.JList<>();
        lanch2 = new javax.swing.JPanel();
        eliminarLanchaa2 = new javax.swing.JTabbedPane();
        agregarLancha2 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        tipo2 = new javax.swing.JTextField();
        marca2 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        modelo2 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        motor2 = new javax.swing.JTextField();
        nSerie4 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        guardarLancha2 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        seleccionadoAgregar3 = new javax.swing.JLabel();
        seleccionarLanchaAgregar2 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jScrollPane33 = new javax.swing.JScrollPane();
        tablaLanchasAgregar2 = new javax.swing.JList<>();
        estado6 = new javax.swing.JLabel();
        añadiendolanchaa2 = new javax.swing.JLabel();
        modificarLancha4 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jScrollPane34 = new javax.swing.JScrollPane();
        tablaLanchasModificar2 = new javax.swing.JList<>();
        jLabel103 = new javax.swing.JLabel();
        jScrollPane35 = new javax.swing.JScrollPane();
        tablaEmbarcaciones2 = new javax.swing.JList<>();
        seleccionBorrado11 = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        nSerie5 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jLabel107 = new javax.swing.JLabel();
        m2 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        t2 = new javax.swing.JTextField();
        mot2 = new javax.swing.JTextField();
        mo2 = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        estado7 = new javax.swing.JLabel();
        modificarLancha5 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jScrollPane36 = new javax.swing.JScrollPane();
        tablaLanchasEliminar2 = new javax.swing.JList<>();
        seleccionBorrado12 = new javax.swing.JButton();
        jLabel111 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        tablaEE2 = new javax.swing.JList<>();
        seleccionBorrado13 = new javax.swing.JButton();
        sel2 = new javax.swing.JLabel();
        seleccionBorrado14 = new javax.swing.JButton();
        estado8 = new javax.swing.JLabel();
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
        setPreferredSize(new java.awt.Dimension(1300, 720));

        Fondo.setBackground(new java.awt.Color(187, 187, 201));
        Fondo.setPreferredSize(new java.awt.Dimension(900, 700));

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
                .addGap(594, 594, Short.MAX_VALUE))
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
                                .addComponent(seleccionarV)
                                .addGap(0, 554, Short.MAX_VALUE))
                            .addComponent(jScrollPane8)))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        editaraClientes.addTab("Visualizar", visualizar);

        jLabel4.setText("Nombre:");

        bNombreC.setText(" ");
        bNombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreCActionPerformed(evt);
            }
        });

        bApellidoC.setText(" ");
        bApellidoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoCActionPerformed(evt);
            }
        });

        jLabel5.setText("Apellido:");

        bCelularC.setText(" ");

        jLabel6.setText("Celular: ");

        bTelFijoC.setText(" ");
        bTelFijoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoCActionPerformed(evt);
            }
        });

        jLabel7.setText("Tel. Fijo:");

        bCorreoC.setText(" ");
        bCorreoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoCActionPerformed(evt);
            }
        });

        jLabel8.setText("Correo: ");

        bCuidadorC.setText(" ");

        jLabel9.setText("Cuidador:");

        jLabel10.setText("Cel. Cuidador :");

        bCelCuidadorC.setText(" ");

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

        javax.swing.GroupLayout crearLayout = new javax.swing.GroupLayout(crear);
        crear.setLayout(crearLayout);
        crearLayout.setHorizontalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crearLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)
                                .addComponent(bCelCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(crearLayout.createSequentialGroup()
                                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(31, 31, 31)
                                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bCorreoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bCuidadorC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bTelFijoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bCelularC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(crearLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contactosCrear)))
                .addContainerGap(893, Short.MAX_VALUE))
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
                .addContainerGap(420, Short.MAX_VALUE))
        );

        editaraClientes.addTab("Crear", crear);

        jLabel14.setText("Buscar:");

        jTextField10.setText(" ");

        jButton7.setText("Buscar");

        tablacontactosEliminar.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(tablacontactosEliminar);

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
                .addContainerGap(562, Short.MAX_VALUE))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addComponent(jScrollPane6))
                        .addContainerGap())))
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

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarModificarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))
                            .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(infoLayout.createSequentialGroup()
                                    .addComponent(jLabel48)
                                    .addGap(4, 4, 4)
                                    .addComponent(bCelCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(infoLayout.createSequentialGroup()
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel44)
                                        .addComponent(jLabel45)
                                        .addComponent(jLabel46)
                                        .addComponent(jLabel47)
                                        .addComponent(jLabel42))
                                    .addGap(31, 31, 31)
                                    .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCuidadorE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bTelFijE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCelularE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bApellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(543, Short.MAX_VALUE))
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
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(10, 10, 10)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(bCelularE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
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
                            .addComponent(jButton9)
                            .addComponent(jButton15))
                        .addContainerGap(358, Short.MAX_VALUE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane13))
                        .addContainerGap())))
        );

        tablaClienteLanchas.addTab("Informacion ", info);

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

        estado.setText("Estado: ");

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
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(seleccionadoAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(528, Short.MAX_VALUE))
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
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel17)))
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
                            .addGroup(modificarLancha1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(sel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))))
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
            .addGap(0, 1290, Short.MAX_VALUE)
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N° Orden", "Cliente " }));
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

        javax.swing.GroupLayout visualizar1Layout = new javax.swing.GroupLayout(visualizar1);
        visualizar1.setLayout(visualizar1Layout);
        visualizar1Layout.setHorizontalGroup(
            visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(seleccionarV2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seleccionarV3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(594, 594, Short.MAX_VALUE))
        );
        visualizar1Layout.setVerticalGroup(
            visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visualizar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visualizar1Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(seleccionarV2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarV3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)))
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        editaraClientes1.addTab("Visualizar", visualizar1);

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

        javax.swing.GroupLayout eliminar1Layout = new javax.swing.GroupLayout(eliminar1);
        eliminar1.setLayout(eliminar1Layout);
        eliminar1Layout.setHorizontalGroup(
            eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eliminar1Layout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionBorrado1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        eliminar1Layout.setVerticalGroup(
            eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(12, 12, 12)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eliminar1Layout.createSequentialGroup()
                        .addGroup(eliminar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado1)
                            .addComponent(eliminarEliminar1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        editaraClientes1.addTab("Eliminar", eliminar1);

        jLabel49.setText("Nombre:");

        bNombreE1.setText(" ");
        bNombreE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreE1ActionPerformed(evt);
            }
        });

        bApellidoE1.setText(" ");
        bApellidoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoE1ActionPerformed(evt);
            }
        });

        jLabel50.setText("Apellido:");

        bCelularE1.setText(" ");

        jLabel51.setText("Celular: ");

        bTelFijE1.setText(" ");
        bTelFijE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijE1ActionPerformed(evt);
            }
        });

        bCorreoE1.setText(" ");
        bCorreoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoE1ActionPerformed(evt);
            }
        });

        jLabel52.setText("Tel. Fijo:");

        jLabel53.setText("Correo: ");

        bCuidadorE1.setText(" ");

        jLabel54.setText("Cuidador:");

        jLabel55.setText("Cel. Cuidador :");

        bCelCuidadorE1.setText(" ");

        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton16.setText("Cancelar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel35.setText("Buscar:");

        jLabel36.setText("Seleccionado:");

        seleccionarModificarInfo1.setText("Seleccionar");
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

        jTextField18.setText(" ");

        jButton24.setText("Buscar");

        tablaModificarInformacion1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane20.setViewportView(tablaModificarInformacion1);

        javax.swing.GroupLayout info1Layout = new javax.swing.GroupLayout(info1);
        info1.setLayout(info1Layout);
        info1Layout.setHorizontalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(info1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(info1Layout.createSequentialGroup()
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionarModificarInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(info1Layout.createSequentialGroup()
                                    .addComponent(jButton16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton10))
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(info1Layout.createSequentialGroup()
                                        .addComponent(jLabel55)
                                        .addGap(4, 4, 4)
                                        .addComponent(bCelCuidadorE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(info1Layout.createSequentialGroup()
                                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel50)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel52)
                                            .addComponent(jLabel53)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel49))
                                        .addGap(31, 31, 31)
                                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bNombreE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bCorreoE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bCuidadorE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bTelFijE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bCelularE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bApellidoE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(506, Short.MAX_VALUE))
        );
        info1Layout.setVerticalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24))
                .addGap(12, 12, 12)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarModificarInfo1)
                            .addGroup(info1Layout.createSequentialGroup()
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(bNombreE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bApellidoE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50))
                                .addGap(10, 10, 10)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(bCelularE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bTelFijE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bCorreoE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bCuidadorE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bCelCuidadorE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton10)
                                    .addComponent(jButton16))))
                        .addContainerGap())))
        );

        tablaClienteLanchas1.addTab("Informacion ", info1);

        jLabel61.setText("Tipo:");

        tipo1.setText(" ");
        tipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo1ActionPerformed(evt);
            }
        });

        marca1.setText(" ");
        marca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca1ActionPerformed(evt);
            }
        });

        jLabel62.setText("Marca:");

        modelo1.setText(" ");

        jLabel65.setText("Modelo:");

        motor1.setText(" ");
        motor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motor1ActionPerformed(evt);
            }
        });

        nSerie2.setText(" ");
        nSerie2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie2ActionPerformed(evt);
            }
        });

        jLabel69.setText("Motor:");

        jLabel70.setText("N° Serie:");

        guardarLancha1.setText("Guardar");
        guardarLancha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLancha1ActionPerformed(evt);
            }
        });

        jButton19.setText("Cancelar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel37.setText("Buscar:");

        seleccionadoAgregar2.setText("Seleccionado: ");

        seleccionarLanchaAgregar1.setText("Seleccionar");
        seleccionarLanchaAgregar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarLanchaAgregar1MouseClicked(evt);
            }
        });
        seleccionarLanchaAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarLanchaAgregar1ActionPerformed(evt);
            }
        });

        jTextField19.setText(" ");

        jButton30.setText("Buscar");

        tablaLanchasAgregar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane21.setViewportView(tablaLanchasAgregar1);

        estado3.setText("Estado: ");

        añadiendolanchaa1.setText("Añadiendo Lancha a:  ");

        javax.swing.GroupLayout agregarLancha1Layout = new javax.swing.GroupLayout(agregarLancha1);
        agregarLancha1.setLayout(agregarLancha1Layout);
        agregarLancha1Layout.setHorizontalGroup(
            agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seleccionarLanchaAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(seleccionadoAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(agregarLancha1Layout.createSequentialGroup()
                            .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel62)
                                .addComponent(jLabel65)
                                .addComponent(jLabel69)
                                .addComponent(jLabel70)
                                .addComponent(jLabel61))
                            .addGap(40, 40, 40)
                            .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(motor1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(modelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(marca1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(agregarLancha1Layout.createSequentialGroup()
                            .addGap(218, 218, 218)
                            .addComponent(jButton19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(guardarLancha1))
                        .addComponent(estado3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(añadiendolanchaa1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(497, Short.MAX_VALUE))
        );
        agregarLancha1Layout.setVerticalGroup(
            agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30))
                .addGap(12, 12, 12)
                .addComponent(seleccionadoAgregar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarLanchaAgregar1)
                            .addGroup(agregarLancha1Layout.createSequentialGroup()
                                .addComponent(añadiendolanchaa1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(marca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62))
                                .addGap(10, 10, 10)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel65)
                                    .addComponent(modelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(motor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton19)
                                    .addComponent(guardarLancha1))
                                .addGap(18, 18, 18)
                                .addComponent(estado3)))
                        .addContainerGap())))
        );

        eliminarLanchaa1.addTab("Agrega", agregarLancha1);

        jLabel38.setText("Buscar:");

        jTextField20.setText(" ");

        jButton31.setText("Buscar");

        jButton32.setText("Seleccionar");
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton32MouseClicked(evt);
            }
        });
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        tablaLanchasModificar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane22.setViewportView(tablaLanchasModificar1);

        jLabel39.setText("Seleccionado: ");

        tablaEmbarcaciones1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane23.setViewportView(tablaEmbarcaciones1);

        seleccionBorrado7.setText("Seleccionar");
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

        jLabel71.setText("Motor: ");

        jLabel72.setText("Modelo:");

        jLabel73.setText("Tipo: ");

        nSerie3.setText(" ");
        nSerie3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie3ActionPerformed(evt);
            }
        });

        jButton20.setText("Guardar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Cancelar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel74.setText("N° Serie: ");

        m1.setText(" ");
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        jLabel75.setText("Marca: ");

        t1.setText(" ");
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });

        mot1.setText(" ");
        mot1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mot1ActionPerformed(evt);
            }
        });

        mo1.setText(" ");

        jLabel40.setText("Embarcacion: ");

        estado4.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha2Layout = new javax.swing.GroupLayout(modificarLancha2);
        modificarLancha2.setLayout(modificarLancha2Layout);
        modificarLancha2Layout.setHorizontalGroup(
            modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel39)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionBorrado7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLancha2Layout.createSequentialGroup()
                                .addComponent(jButton21)
                                .addGap(18, 18, 18)
                                .addComponent(jButton20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLancha2Layout.createSequentialGroup()
                                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75)
                                    .addComponent(jLabel72)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel74)
                                    .addComponent(jLabel73))
                                .addGap(33, 33, 33)
                                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mot1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mo1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(estado4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        modificarLancha2Layout.setVerticalGroup(
            modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton32)
                            .addGroup(modificarLancha2Layout.createSequentialGroup()
                                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel73)
                                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel75))
                                        .addGap(10, 10, 10)
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel72)
                                            .addComponent(mo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel71))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nSerie3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel74)))
                                    .addComponent(seleccionBorrado7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton20)
                                    .addComponent(jButton21))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado4)
                        .addContainerGap(348, Short.MAX_VALUE))))
        );

        eliminarLanchaa1.addTab("Modificar", modificarLancha2);

        jLabel41.setText("Buscar:");

        jTextField21.setText(" ");

        jButton33.setText("Buscar");

        jButton34.setText("Eliminar");
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        tablaLanchasEliminar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane24.setViewportView(tablaLanchasEliminar1);

        jLabel76.setText("Embarcacion: ");

        tablaEE1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane25.setViewportView(tablaEE1);

        seleccionBorrado9.setText("Recuperar");
        seleccionBorrado9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado9MouseClicked(evt);
            }
        });
        seleccionBorrado9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado9ActionPerformed(evt);
            }
        });

        sel1.setText("Seleccionado: ");

        seleccionBorrado10.setText("Eliminar");
        seleccionBorrado10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado10MouseClicked(evt);
            }
        });
        seleccionBorrado10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarLancha3Layout = new javax.swing.GroupLayout(modificarLancha3);
        modificarLancha3.setLayout(modificarLancha3Layout);
        modificarLancha3Layout.setHorizontalGroup(
            modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha3Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha3Layout.createSequentialGroup()
                        .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha3Layout.createSequentialGroup()
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modificarLancha3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(595, Short.MAX_VALUE))
        );
        modificarLancha3Layout.setVerticalGroup(
            modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(sel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24)
                    .addGroup(modificarLancha3Layout.createSequentialGroup()
                        .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                            .addGroup(modificarLancha3Layout.createSequentialGroup()
                                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton34)
                                    .addComponent(seleccionBorrado9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionBorrado10)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        eliminarLanchaa1.addTab("Eliminar", modificarLancha3);

        javax.swing.GroupLayout lanch1Layout = new javax.swing.GroupLayout(lanch1);
        lanch1.setLayout(lanch1Layout);
        lanch1Layout.setHorizontalGroup(
            lanch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa1)
        );
        lanch1Layout.setVerticalGroup(
            lanch1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tablaClienteLanchas1.addTab("Orden", lanch1);

        javax.swing.GroupLayout editar2Layout = new javax.swing.GroupLayout(editar2);
        editar2.setLayout(editar2Layout);
        editar2Layout.setHorizontalGroup(
            editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
            .addGroup(editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(editar2Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas1)
                    .addContainerGap()))
        );
        editar2Layout.setVerticalGroup(
            editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaClienteLanchas1))
        );

        editaraClientes1.addTab("Modificar", editar2);

        bFechaOrdenesCrear.setText(" ");

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

        jLabel31.setText("Agregar Mano de Obra");

        tablaOrdenesCrear1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane50.setViewportView(tablaOrdenesCrear1);

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

        jLabel149.setText("Añadir Mano de obra");

        seleccionarModificarInfo6.setText("Añadir");
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

        tablaOdenCrearRepuestos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane51.setViewportView(tablaOdenCrearRepuestos);

        jLabel150.setText("Agregar Repuestos");

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

        seleccionarModificarInfo8.setText("Agregar");
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

        seleccionarModificarInfo9.setText("Eliminar");
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

        jTextPane1.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane2.setViewportView(jTextPane1);

        jLabel1.setText("Repuestos agregados ");

        bFechaOrdenesCrear2.setText(" ");

        jLabel155.setText("Total Mano Obra");

        jLabel156.setText("Total Repuestos");

        bFechaOrdenesCrear3.setText(" ");

        jLabel157.setText("Total Varios");

        jLabel158.setText("Agregar Varios");

        jTextPane2.setMaximumSize(new java.awt.Dimension(6, 22));
        jScrollPane3.setViewportView(jTextPane2);

        seleccionarModificarInfo13.setText("Eliminar");
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

        bFechaOrdenesCrear5.setText(" ");

        visualizarV4.setEditable(false);
        visualizarV4.setColumns(20);
        visualizarV4.setRows(1000);
        visualizarV4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane26.setViewportView(visualizarV4);

        javax.swing.GroupLayout crear1Layout = new javax.swing.GroupLayout(crear1);
        crear1.setLayout(crear1Layout);
        crear1Layout.setHorizontalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crear1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(contactosCrear1))
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crear1Layout.createSequentialGroup()
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel148, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(seleccionarModificarInfo7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crear1Layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(3, 3, 3)
                                                .addComponent(bFechaOrdenesCrear))
                                            .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel150))
                                    .addComponent(jScrollPane51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel149, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(2, 2, 2)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(seleccionarModificarInfo8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionarModificarInfo5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(crear1Layout.createSequentialGroup()
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel154)
                                        .addGap(127, 127, 127)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel158)
                                        .addGap(116, 116, 116))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel157)
                                    .addComponent(bFechaOrdenesCrear5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel152)
                                        .addGap(73, 73, 73))
                                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(seleccionarModificarInfo9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(crear1Layout.createSequentialGroup()
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(seleccionarModificarInfo13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bFechaOrdenesCrear3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel156)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crear1Layout.createSequentialGroup()
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bFechaOrdenesCrear2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel155))
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel151)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
        );
        crear1Layout.setVerticalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crear1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactosCrear1)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crear1Layout.createSequentialGroup()
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crear1Layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel156)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bFechaOrdenesCrear3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(seleccionarModificarInfo13))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crear1Layout.createSequentialGroup()
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel148)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(seleccionarModificarInfo7))
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(bFechaOrdenesCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(seleccionarModificarInfo6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel149)
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(bFechaOrdenesCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(seleccionarModificarInfo5))
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(crear1Layout.createSequentialGroup()
                                                .addComponent(jLabel150)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(crear1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(seleccionarModificarInfo8))))
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel152)
                                        .addGap(10, 10, 10)
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(crear1Layout.createSequentialGroup()
                                                .addComponent(jLabel155)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bFechaOrdenesCrear2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(crear1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(seleccionarModificarInfo9)))))
                                .addGap(18, 18, 18)
                                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel154)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(crear1Layout.createSequentialGroup()
                                        .addComponent(jLabel158)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(crear1Layout.createSequentialGroup()
                                                .addComponent(jLabel157)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bFechaOrdenesCrear5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(66, Short.MAX_VALUE))))
        );

        editaraClientes1.addTab("Crear", crear1);

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

        tablaOrdenesV1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane27.setViewportView(tablaOrdenesV1);

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
                .addGap(594, 594, Short.MAX_VALUE))
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
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                            .addGroup(visualizar2Layout.createSequentialGroup()
                                .addComponent(seleccionarV5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(visualizar2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        editaraClientes2.addTab("Visualizar", visualizar2);

        bCelularC2.setText(" ");

        jLabel79.setText("Fecha:");

        bTelFijoC2.setText(" ");
        bTelFijoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC2ActionPerformed(evt);
            }
        });

        jLabel80.setText("Tel. Fijo:");

        bCorreoC2.setText(" ");
        bCorreoC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC2ActionPerformed(evt);
            }
        });

        jLabel81.setText("Correo: ");

        bCuidadorC2.setText(" ");

        jLabel82.setText("Cuidador:");

        jLabel83.setText("Cel. Cuidador :");

        bCelCuidadorC2.setText(" ");

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

        tablaRepuestosCrear.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane29.setViewportView(tablaRepuestosCrear);

        jLabel147.setText("Seleccionado:");

        javax.swing.GroupLayout crear2Layout = new javax.swing.GroupLayout(crear2);
        crear2.setLayout(crear2Layout);
        crear2Layout.setHorizontalGroup(
            crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear2Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jButton5)
                .addGap(12, 12, 12)
                .addComponent(contactosCrear2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(crear2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear2Layout.createSequentialGroup()
                        .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crear2Layout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addGap(4, 4, 4)
                                .addComponent(bCelCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(bCelularC2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 658, Short.MAX_VALUE))
        );
        crear2Layout.setVerticalGroup(
            crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel147)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear2Layout.createSequentialGroup()
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
                            .addComponent(bCelCuidadorC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83)))
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addGroup(crear2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(contactosCrear2))
                .addContainerGap())
        );

        editaraClientes2.addTab("Crear", crear2);

        jLabel84.setText("Buscar:");

        jTextField23.setText(" ");

        jButton11.setText("Buscar");

        tablacontactosEliminar4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane30.setViewportView(tablacontactosEliminar4);

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

        tablacontactosEliminados2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane31.setViewportView(tablacontactosEliminados2);

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
                .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eliminar2Layout.createSequentialGroup()
                        .addGroup(eliminar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado2)
                            .addComponent(eliminarEliminar2))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        editaraClientes2.addTab("Eliminar", eliminar2);

        jLabel87.setText("Nombre:");

        bNombreE2.setText(" ");
        bNombreE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreE2ActionPerformed(evt);
            }
        });

        bApellidoE2.setText(" ");
        bApellidoE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoE2ActionPerformed(evt);
            }
        });

        jLabel88.setText("Apellido:");

        bCelularE2.setText(" ");

        jLabel89.setText("Celular: ");

        bTelFijE2.setText(" ");
        bTelFijE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijE2ActionPerformed(evt);
            }
        });

        bCorreoE2.setText(" ");
        bCorreoE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoE2ActionPerformed(evt);
            }
        });

        jLabel90.setText("Tel. Fijo:");

        jLabel91.setText("Correo: ");

        bCuidadorE2.setText(" ");

        jLabel92.setText("Cuidador:");

        jLabel93.setText("Cel. Cuidador :");

        bCelCuidadorE2.setText(" ");

        jButton35.setText("Guardar");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setText("Cancelar");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jLabel94.setText("Buscar:");

        jLabel95.setText("Seleccionado:");

        seleccionarModificarInfo2.setText("Seleccionar");
        seleccionarModificarInfo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarModificarInfo2MouseClicked(evt);
            }
        });
        seleccionarModificarInfo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarModificarInfo2ActionPerformed(evt);
            }
        });

        jTextField24.setText(" ");

        jButton37.setText("Buscar");

        tablaModificarInformacion2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane32.setViewportView(tablaModificarInformacion2);

        javax.swing.GroupLayout info2Layout = new javax.swing.GroupLayout(info2);
        info2.setLayout(info2Layout);
        info2Layout.setHorizontalGroup(
            info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info2Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(info2Layout.createSequentialGroup()
                        .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarModificarInfo2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(info2Layout.createSequentialGroup()
                                .addComponent(jButton36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton35))
                            .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(info2Layout.createSequentialGroup()
                                    .addComponent(jLabel93)
                                    .addGap(4, 4, 4)
                                    .addComponent(bCelCuidadorE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(info2Layout.createSequentialGroup()
                                    .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel88)
                                        .addComponent(jLabel89)
                                        .addComponent(jLabel90)
                                        .addComponent(jLabel91)
                                        .addComponent(jLabel92)
                                        .addComponent(jLabel87))
                                    .addGap(31, 31, 31)
                                    .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bNombreE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCorreoE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCuidadorE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bTelFijE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCelularE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bApellidoE2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(543, Short.MAX_VALUE))
        );
        info2Layout.setVerticalGroup(
            info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton37))
                .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(bNombreE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88))
                        .addGap(10, 10, 10)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(bCelularE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton35)
                            .addComponent(jButton36))
                        .addContainerGap(346, Short.MAX_VALUE))
                    .addGroup(info2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(info2Layout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo2)
                                .addContainerGap())))))
        );

        tablaClienteLanchas2.addTab("Informacion ", info2);

        jLabel96.setText("Tipo:");

        tipo2.setText(" ");
        tipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo2ActionPerformed(evt);
            }
        });

        marca2.setText(" ");
        marca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marca2ActionPerformed(evt);
            }
        });

        jLabel97.setText("Marca:");

        modelo2.setText(" ");

        jLabel98.setText("Modelo:");

        motor2.setText(" ");
        motor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motor2ActionPerformed(evt);
            }
        });

        nSerie4.setText(" ");
        nSerie4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie4ActionPerformed(evt);
            }
        });

        jLabel99.setText("Motor:");

        jLabel100.setText("N° Serie:");

        guardarLancha2.setText("Guardar");
        guardarLancha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarLancha2ActionPerformed(evt);
            }
        });

        jButton38.setText("Cancelar");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jLabel101.setText("Buscar:");

        seleccionadoAgregar3.setText("Seleccionado: ");

        seleccionarLanchaAgregar2.setText("Seleccionar");
        seleccionarLanchaAgregar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarLanchaAgregar2MouseClicked(evt);
            }
        });
        seleccionarLanchaAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarLanchaAgregar2ActionPerformed(evt);
            }
        });

        jTextField25.setText(" ");

        jButton39.setText("Buscar");

        tablaLanchasAgregar2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane33.setViewportView(tablaLanchasAgregar2);

        estado6.setText("Estado: ");

        añadiendolanchaa2.setText("Añadiendo Lancha a:  ");

        javax.swing.GroupLayout agregarLancha2Layout = new javax.swing.GroupLayout(agregarLancha2);
        agregarLancha2.setLayout(agregarLancha2Layout);
        agregarLancha2Layout.setHorizontalGroup(
            agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agregarLancha2Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLancha2Layout.createSequentialGroup()
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha2Layout.createSequentialGroup()
                                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarLanchaAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(agregarLancha2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(seleccionadoAgregar3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agregarLancha2Layout.createSequentialGroup()
                                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97)
                                    .addComponent(jLabel98)
                                    .addComponent(jLabel99)
                                    .addComponent(jLabel100)
                                    .addComponent(jLabel96))
                                .addGap(40, 40, 40)
                                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(motor2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelo2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(marca2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(agregarLancha2Layout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(jButton38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardarLancha2))
                            .addComponent(estado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(añadiendolanchaa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(528, Short.MAX_VALUE))
        );
        agregarLancha2Layout.setVerticalGroup(
            agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarLancha2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39))
                .addGap(12, 12, 12)
                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionadoAgregar3)
                    .addComponent(añadiendolanchaa2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(agregarLancha2Layout.createSequentialGroup()
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha2Layout.createSequentialGroup()
                                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel96)
                                    .addComponent(tipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(marca2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel97)))
                            .addComponent(seleccionarLanchaAgregar2))
                        .addGap(10, 10, 10)
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel98)
                            .addComponent(modelo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(motor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nSerie4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton38)
                            .addComponent(guardarLancha2))
                        .addGap(18, 18, 18)
                        .addComponent(estado6)
                        .addContainerGap(324, Short.MAX_VALUE))))
        );

        eliminarLanchaa2.addTab("Agrega", agregarLancha2);

        jLabel102.setText("Buscar:");

        jTextField26.setText(" ");

        jButton40.setText("Buscar");

        jButton41.setText("Seleccionar");
        jButton41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton41MouseClicked(evt);
            }
        });
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        tablaLanchasModificar2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane34.setViewportView(tablaLanchasModificar2);

        jLabel103.setText("Seleccionado: ");

        tablaEmbarcaciones2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane35.setViewportView(tablaEmbarcaciones2);

        seleccionBorrado11.setText("Seleccionar");
        seleccionBorrado11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado11MouseClicked(evt);
            }
        });
        seleccionBorrado11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado11ActionPerformed(evt);
            }
        });

        jLabel104.setText("Motor: ");

        jLabel105.setText("Modelo:");

        jLabel106.setText("Tipo: ");

        nSerie5.setText(" ");
        nSerie5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nSerie5ActionPerformed(evt);
            }
        });

        jButton42.setText("Guardar");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setText("Cancelar");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jLabel107.setText("N° Serie: ");

        m2.setText(" ");
        m2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m2ActionPerformed(evt);
            }
        });

        jLabel108.setText("Marca: ");

        t2.setText(" ");
        t2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ActionPerformed(evt);
            }
        });

        mot2.setText(" ");
        mot2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mot2ActionPerformed(evt);
            }
        });

        mo2.setText(" ");

        jLabel109.setText("Embarcacion: ");

        estado7.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha4Layout = new javax.swing.GroupLayout(modificarLancha4);
        modificarLancha4.setLayout(modificarLancha4Layout);
        modificarLancha4Layout.setHorizontalGroup(
            modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(modificarLancha4Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel103)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane35, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha4Layout.createSequentialGroup()
                                .addComponent(seleccionBorrado11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel108)
                                    .addComponent(jLabel105)
                                    .addComponent(jLabel104)
                                    .addComponent(jLabel107)
                                    .addComponent(jLabel106))
                                .addGap(33, 33, 33)
                                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nSerie5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mot2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mo2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modificarLancha4Layout.createSequentialGroup()
                                .addComponent(estado7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton42)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modificarLancha4Layout.setVerticalGroup(
            modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane34)
                    .addComponent(jScrollPane35, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton41)
                            .addGroup(modificarLancha4Layout.createSequentialGroup()
                                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel106)
                                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel108))
                                        .addGap(10, 10, 10)
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel105)
                                            .addComponent(mo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(mot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel104))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nSerie5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel107)))
                                    .addComponent(seleccionBorrado11))
                                .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(modificarLancha4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton42)
                                            .addComponent(jButton43)))
                                    .addGroup(modificarLancha4Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(estado7)))))
                        .addGap(141, 344, Short.MAX_VALUE))))
        );

        eliminarLanchaa2.addTab("Modificar", modificarLancha4);

        jLabel110.setText("Buscar:");

        jTextField27.setText(" ");

        jButton44.setText("Buscar");

        jButton45.setText("Seleccionar");
        jButton45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton45MouseClicked(evt);
            }
        });
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        tablaLanchasEliminar2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane36.setViewportView(tablaLanchasEliminar2);

        seleccionBorrado12.setText("Cancelar");
        seleccionBorrado12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado12MouseClicked(evt);
            }
        });
        seleccionBorrado12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado12ActionPerformed(evt);
            }
        });

        jLabel111.setText("Embarcacion: ");

        tablaEE2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane37.setViewportView(tablaEE2);

        seleccionBorrado13.setText("Seleccionar");
        seleccionBorrado13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado13MouseClicked(evt);
            }
        });
        seleccionBorrado13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado13ActionPerformed(evt);
            }
        });

        sel2.setText("Seleccionado: ");

        seleccionBorrado14.setText("Eliminar");
        seleccionBorrado14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado14MouseClicked(evt);
            }
        });
        seleccionBorrado14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado14ActionPerformed(evt);
            }
        });

        estado8.setText("Estado: Seleccionando Cliente");

        javax.swing.GroupLayout modificarLancha5Layout = new javax.swing.GroupLayout(modificarLancha5);
        modificarLancha5.setLayout(modificarLancha5Layout);
        modificarLancha5Layout.setHorizontalGroup(
            modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLancha5Layout.createSequentialGroup()
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modificarLancha5Layout.createSequentialGroup()
                        .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha5Layout.createSequentialGroup()
                                .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modificarLancha5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(sel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado8, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))))
                .addGap(186, 186, 186))
        );
        modificarLancha5Layout.setVerticalGroup(
            modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLancha5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton44))
                .addGap(12, 12, 12)
                .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(sel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane36)
                    .addGroup(modificarLancha5Layout.createSequentialGroup()
                        .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane37, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addGroup(modificarLancha5Layout.createSequentialGroup()
                                .addGroup(modificarLancha5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton45)
                                    .addGroup(modificarLancha5Layout.createSequentialGroup()
                                        .addComponent(seleccionBorrado13)
                                        .addGap(14, 14, 14)
                                        .addComponent(seleccionBorrado12)))
                                .addGap(18, 18, 18)
                                .addComponent(seleccionBorrado14)
                                .addGap(54, 54, 54)
                                .addComponent(estado8)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        eliminarLanchaa2.addTab("Eliminar", modificarLancha5);

        javax.swing.GroupLayout lanch2Layout = new javax.swing.GroupLayout(lanch2);
        lanch2.setLayout(lanch2Layout);
        lanch2Layout.setHorizontalGroup(
            lanch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa2)
        );
        lanch2Layout.setVerticalGroup(
            lanch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarLanchaa2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tablaClienteLanchas2.addTab("Orden", lanch2);

        javax.swing.GroupLayout editar3Layout = new javax.swing.GroupLayout(editar3);
        editar3.setLayout(editar3Layout);
        editar3Layout.setHorizontalGroup(
            editar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
            .addGroup(editar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar3Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas2)
                    .addContainerGap()))
        );
        editar3Layout.setVerticalGroup(
            editar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(editar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar3Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas2)
                    .addContainerGap()))
        );

        editaraClientes2.addTab("Modificar", editar3);

        javax.swing.GroupLayout RepuestosLayout = new javax.swing.GroupLayout(Repuestos);
        Repuestos.setLayout(RepuestosLayout);
        RepuestosLayout.setHorizontalGroup(
            RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        RepuestosLayout.setVerticalGroup(
            RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes2)
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
                .addGap(594, 594, Short.MAX_VALUE))
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
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
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
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablacontactosVisualizar.getSelectedValue());
            this.TablaContactosVsualizar.setText("----------------------------------------------------------------------\n                                   DATOS"
                    +cliente.getInformacionCiente()+"\n----------------------------------------------------------------------"
                    +cliente.getInformacionEmbarcacuiones()+"\n----------------------------------------------------------------------");
            this.jLabel12.setText("Seleccionado: "+cliente.getApellidoNombre());
    }//GEN-LAST:event_seleccionarVActionPerformed
    }    public void vaciarBarrasC(){
        //barras Editar
        this.bNombreE.setText("");
        this.bApellidoE.setText("");
        this.bCorreoE.setText("");
        this.bCelularE.setText("");
        this.bTelFijE.setText("");
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
        this.bTelFijoC.setText("");
        this.bCuidadorC.setText("");
        this.bCelCuidadorC.setText("");
        
        
        this.tipo.setText("");
        this.marca.setText("");
        this.modelo.setText("");
        this.motor.setText("");
        this.nSerie.setText("");
        
    }
    public void vaciarBarrasO(){
        //barras Editar
        this.bNombreE1.setText("");
        this.bApellidoE1.setText("");
        this.bCorreoE1.setText("");
        this.bCelularE1.setText("");
        this.bTelFijE1.setText("");
        this.bCuidadorE1.setText("");
        this.bCelCuidadorE1.setText("");
        
        this.t1.setText("");
        this.m1.setText("");
        this.nSerie2.setText("");
        this.mo1.setText("");
        this.mot1.setText("");
        
        //Barras Crear
        this.bFechaOrdenesCrear.setText("");
        
        
        this.tipo1.setText("");
        this.marca1.setText("");
        this.modelo1.setText("");
        this.motor1.setText("");
        this.nSerie3.setText("");
        
    }
    public void updateVOrden(){
        this.vaciarTablasO();
        this.vaciarBarrasO();
        this.actualizarOrdenes();
        this.actualizarContactosEliminados();
        this.setearTablasListadoOrdenes();
        this.repaint();
    
}
    public void updateVCliente(){
        this.vaciarTablasC();
        this.vaciarBarrasC();
        this.actualizarContactos();
        this.actualizarContactosEliminados();
        this.setearTablasListadoClientes();
        this.repaint();
    
}
    private void contactosCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrearActionPerformed
       if(bNombreC.getText().length()!=0 && bApellidoC.getText().length()!=0 && bCorreoC.getText().length()!=0 && bCelularC.getText().length()!=0 
            && bTelFijoC.getText().length()!=0 && bCuidadorC.getText().length()!=0 && bCelCuidadorC.getText().length()!=0){
            cliente=new Cliente();
            cliente.setNombe(this.bNombreC.getText());
            cliente.setApellido(this.bApellidoC.getText());
            cliente.setCorreo(this.bCorreoC.getText());
            cliente.setCelular(this.bCelularC.getText());
            cliente.setTelFijo(this.bTelFijoC.getText());
            cliente.setCuidador(this.bCuidadorC.getText());
            cliente.setCelularCuidador(this.bCelCuidadorC.getText());

            this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
            this.updateVCliente();
            this.actualizarTXTContactos();
       }
    }//GEN-LAST:event_contactosCrearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.vaciarBarrasC();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void eliminarEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminarMouseClicked

    private void eliminarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminarActionPerformed
        
        if(this.tablacontactosEliminar.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablacontactosEliminar.getSelectedValue());
        }
        this.updateVCliente();
        
        if(cliente!=null){
            this.hashmapClientesBorrados.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientes.remove(cliente.getApellidoNombre(),cliente);
            this.updateVCliente();
            System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTContactos();
            this.actualizarTXTContactosEliminados();
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
            System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTContactos();
            this.actualizarTXTContactosEliminados();
        }
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
            
            //this.actualizarTXTContactos();
            this.updateVCliente();
       }
       cliente=null;
       this.jLabel13.setText("------------------------------");
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
            this.actualizarTXTContactos();
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
            this.actualizarTXTContactos();
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
            this.actualizarTXTContactos();
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
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar1ActionPerformed

    private void seleccionBorrado1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado1MouseClicked

    private void seleccionBorrado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado1ActionPerformed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void seleccionarModificarInfo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo1MouseClicked

    private void seleccionarModificarInfo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo1ActionPerformed

    private void tipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo1ActionPerformed

    private void marca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marca1ActionPerformed

    private void motor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motor1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motor1ActionPerformed

    private void nSerie2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie2ActionPerformed

    private void guardarLancha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarLancha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarLancha1ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void seleccionarLanchaAgregar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar1MouseClicked

    private void seleccionarLanchaAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar1ActionPerformed

    private void jButton32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32MouseClicked

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void seleccionBorrado7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado7MouseClicked

    private void seleccionBorrado7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado7ActionPerformed

    private void nSerie3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie3ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m1ActionPerformed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void mot1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mot1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mot1ActionPerformed

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void seleccionBorrado9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado9MouseClicked

    private void seleccionBorrado9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado9ActionPerformed

    private void seleccionBorrado10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado10MouseClicked

    private void seleccionBorrado10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado10ActionPerformed

    private void seleccionarV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV2MouseClicked

    private void seleccionarV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV2ActionPerformed

    private void seleccionarV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarV3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV3MouseClicked

    private void seleccionarV3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarV3ActionPerformed
        if(this.tablaOrdenesVisualizar.getSelectedValue()!=null){
            orden=new Orden();
            orden=this.hashmapOrdenes.get(this.tablaOrdenesVisualizar.getSelectedValue());
            this.visualizarV1.setText("----------------------------------------------------------------------\n                                DETALLES\n"
                    +orden.getInformacion()+"\n----------------------------------------------------------------------");
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
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV5ActionPerformed

    private void bTelFijoC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC2ActionPerformed

    private void bCorreoC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoC2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoC2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void contactosCrear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactosCrear2ActionPerformed

    private void eliminarEliminar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar2MouseClicked

    private void eliminarEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminar2ActionPerformed

    private void seleccionBorrado2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado2MouseClicked

    private void seleccionBorrado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado2ActionPerformed

    private void bNombreE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreE2ActionPerformed

    private void bApellidoE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoE2ActionPerformed

    private void bTelFijE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijE2ActionPerformed

    private void bCorreoE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoE2ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void seleccionarModificarInfo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo2MouseClicked

    private void seleccionarModificarInfo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo2ActionPerformed

    private void tipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo2ActionPerformed

    private void marca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marca2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marca2ActionPerformed

    private void motor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motor2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_motor2ActionPerformed

    private void nSerie4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie4ActionPerformed

    private void guardarLancha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarLancha2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarLancha2ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void seleccionarLanchaAgregar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar2MouseClicked

    private void seleccionarLanchaAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregar2ActionPerformed

    private void jButton41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41MouseClicked

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void seleccionBorrado11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado11MouseClicked

    private void seleccionBorrado11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado11ActionPerformed

    private void nSerie5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie5ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void m2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m2ActionPerformed

    private void t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t2ActionPerformed

    private void mot2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mot2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mot2ActionPerformed

    private void jButton45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton45MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45MouseClicked

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void seleccionBorrado12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado12MouseClicked

    private void seleccionBorrado12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado12ActionPerformed

    private void seleccionBorrado13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado13MouseClicked

    private void seleccionBorrado13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado13ActionPerformed

    private void seleccionBorrado14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado14MouseClicked

    private void seleccionBorrado14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado14ActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo7ActionPerformed

    private void seleccionarModificarInfo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo7MouseClicked

    private void seleccionarModificarInfo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo6ActionPerformed

    private void seleccionarModificarInfo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo6MouseClicked

    private void seleccionarModificarInfo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo5ActionPerformed

    private void seleccionarModificarInfo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo5MouseClicked

    private void contactosCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactosCrear1ActionPerformed

    private void seleccionarModificarInfo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo8MouseClicked

    private void seleccionarModificarInfo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo8ActionPerformed

    private void seleccionarModificarInfo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo9MouseClicked

    private void seleccionarModificarInfo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo9ActionPerformed

    private void seleccionarModificarInfo13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo13MouseClicked

    private void seleccionarModificarInfo13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarModificarInfo13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarModificarInfo13ActionPerformed
       
        
        
    
    
    
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
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Guarderias;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel Repuestos;
    private javax.swing.JTextArea TablaContactosVsualizar;
    private javax.swing.JPanel agregarLancha;
    private javax.swing.JPanel agregarLancha1;
    private javax.swing.JPanel agregarLancha2;
    private javax.swing.JPanel agregarLancha3;
    private javax.swing.JLabel añadiendolanchaa;
    private javax.swing.JLabel añadiendolanchaa1;
    private javax.swing.JLabel añadiendolanchaa2;
    private javax.swing.JLabel añadiendolanchaa3;
    private javax.swing.JTextField bApellidoC;
    private javax.swing.JTextField bApellidoE;
    private javax.swing.JTextField bApellidoE1;
    private javax.swing.JTextField bApellidoE2;
    private javax.swing.JTextField bApellidoE3;
    private javax.swing.JTextField bCelCuidadorC;
    private javax.swing.JTextField bCelCuidadorC2;
    private javax.swing.JTextField bCelCuidadorC3;
    private javax.swing.JTextField bCelCuidadorE;
    private javax.swing.JTextField bCelCuidadorE1;
    private javax.swing.JTextField bCelCuidadorE2;
    private javax.swing.JTextField bCelCuidadorE3;
    private javax.swing.JTextField bCelularC;
    private javax.swing.JTextField bCelularC2;
    private javax.swing.JTextField bCelularC3;
    private javax.swing.JTextField bCelularE;
    private javax.swing.JTextField bCelularE1;
    private javax.swing.JTextField bCelularE2;
    private javax.swing.JTextField bCelularE3;
    private javax.swing.JTextField bCorreoC;
    private javax.swing.JTextField bCorreoC2;
    private javax.swing.JTextField bCorreoC3;
    private javax.swing.JTextField bCorreoE;
    private javax.swing.JTextField bCorreoE1;
    private javax.swing.JTextField bCorreoE2;
    private javax.swing.JTextField bCorreoE3;
    private javax.swing.JTextField bCuidadorC;
    private javax.swing.JTextField bCuidadorC2;
    private javax.swing.JTextField bCuidadorC3;
    private javax.swing.JTextField bCuidadorE;
    private javax.swing.JTextField bCuidadorE1;
    private javax.swing.JTextField bCuidadorE2;
    private javax.swing.JTextField bCuidadorE3;
    private javax.swing.JTextField bFechaOrdenesCrear;
    private javax.swing.JTextField bFechaOrdenesCrear1;
    private javax.swing.JTextField bFechaOrdenesCrear2;
    private javax.swing.JTextField bFechaOrdenesCrear3;
    private javax.swing.JTextField bFechaOrdenesCrear5;
    private javax.swing.JTextField bNombreC;
    private javax.swing.JTextField bNombreE;
    private javax.swing.JTextField bNombreE1;
    private javax.swing.JTextField bNombreE2;
    private javax.swing.JTextField bNombreE3;
    private javax.swing.JTextField bTelFijE;
    private javax.swing.JTextField bTelFijE1;
    private javax.swing.JTextField bTelFijE2;
    private javax.swing.JTextField bTelFijE3;
    private javax.swing.JTextField bTelFijoC;
    private javax.swing.JTextField bTelFijoC2;
    private javax.swing.JTextField bTelFijoC3;
    private javax.swing.JButton contactosCrear;
    private javax.swing.JButton contactosCrear1;
    private javax.swing.JButton contactosCrear2;
    private javax.swing.JButton contactosCrear3;
    private javax.swing.JPanel crear;
    private javax.swing.JPanel crear1;
    private javax.swing.JPanel crear2;
    private javax.swing.JPanel crear3;
    private javax.swing.JPanel editar1;
    private javax.swing.JPanel editar2;
    private javax.swing.JPanel editar3;
    private javax.swing.JPanel editar4;
    private javax.swing.JTabbedPane editaraClientes;
    private javax.swing.JTabbedPane editaraClientes1;
    private javax.swing.JTabbedPane editaraClientes2;
    private javax.swing.JTabbedPane editaraClientes3;
    private javax.swing.JPanel eliminar;
    private javax.swing.JPanel eliminar1;
    private javax.swing.JPanel eliminar2;
    private javax.swing.JPanel eliminar3;
    private javax.swing.JButton eliminarEliminar;
    private javax.swing.JButton eliminarEliminar1;
    private javax.swing.JButton eliminarEliminar2;
    private javax.swing.JButton eliminarEliminar3;
    private javax.swing.JTabbedPane eliminarLanchaa;
    private javax.swing.JTabbedPane eliminarLanchaa1;
    private javax.swing.JTabbedPane eliminarLanchaa2;
    private javax.swing.JTabbedPane eliminarLanchaa3;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel estado1;
    private javax.swing.JLabel estado10;
    private javax.swing.JLabel estado11;
    private javax.swing.JLabel estado2;
    private javax.swing.JLabel estado3;
    private javax.swing.JLabel estado4;
    private javax.swing.JLabel estado6;
    private javax.swing.JLabel estado7;
    private javax.swing.JLabel estado8;
    private javax.swing.JLabel estado9;
    private javax.swing.JButton guardarLancha;
    private javax.swing.JButton guardarLancha1;
    private javax.swing.JButton guardarLancha2;
    private javax.swing.JButton guardarLancha3;
    private javax.swing.JPanel info;
    private javax.swing.JPanel info1;
    private javax.swing.JPanel info2;
    private javax.swing.JPanel info3;
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
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
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
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
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
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel83;
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
    private javax.swing.JLabel jLabel96;
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
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
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
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPanel lanch;
    private javax.swing.JPanel lanch1;
    private javax.swing.JPanel lanch2;
    private javax.swing.JPanel lanch3;
    private javax.swing.JTextField m;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField m2;
    private javax.swing.JTextField m3;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField marca1;
    private javax.swing.JTextField marca2;
    private javax.swing.JTextField marca3;
    private javax.swing.JTextField mo;
    private javax.swing.JTextField mo1;
    private javax.swing.JTextField mo2;
    private javax.swing.JTextField mo3;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField modelo1;
    private javax.swing.JTextField modelo2;
    private javax.swing.JTextField modelo3;
    private javax.swing.JPanel modificarLancha;
    private javax.swing.JPanel modificarLancha1;
    private javax.swing.JPanel modificarLancha2;
    private javax.swing.JPanel modificarLancha3;
    private javax.swing.JPanel modificarLancha4;
    private javax.swing.JPanel modificarLancha5;
    private javax.swing.JPanel modificarLancha6;
    private javax.swing.JPanel modificarLancha7;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField mot1;
    private javax.swing.JTextField mot2;
    private javax.swing.JTextField mot3;
    private javax.swing.JTextField motor;
    private javax.swing.JTextField motor1;
    private javax.swing.JTextField motor2;
    private javax.swing.JTextField motor3;
    private javax.swing.JTextField nSerie;
    private javax.swing.JTextField nSerie1;
    private javax.swing.JTextField nSerie2;
    private javax.swing.JTextField nSerie3;
    private javax.swing.JTextField nSerie4;
    private javax.swing.JTextField nSerie5;
    private javax.swing.JTextField nSerie6;
    private javax.swing.JTextField nSerie7;
    private javax.swing.JLabel sel;
    private javax.swing.JLabel sel1;
    private javax.swing.JLabel sel2;
    private javax.swing.JLabel sel3;
    private javax.swing.JButton seleccionBorrado;
    private javax.swing.JButton seleccionBorrado1;
    private javax.swing.JButton seleccionBorrado10;
    private javax.swing.JButton seleccionBorrado11;
    private javax.swing.JButton seleccionBorrado12;
    private javax.swing.JButton seleccionBorrado13;
    private javax.swing.JButton seleccionBorrado14;
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
    private javax.swing.JButton seleccionBorrado7;
    private javax.swing.JButton seleccionBorrado9;
    private javax.swing.JLabel seleccionadoAgregar1;
    private javax.swing.JLabel seleccionadoAgregar2;
    private javax.swing.JLabel seleccionadoAgregar3;
    private javax.swing.JLabel seleccionadoAgregar4;
    private javax.swing.JButton seleccionarLanchaAgregar;
    private javax.swing.JButton seleccionarLanchaAgregar1;
    private javax.swing.JButton seleccionarLanchaAgregar2;
    private javax.swing.JButton seleccionarLanchaAgregar3;
    private javax.swing.JButton seleccionarModificarInfo;
    private javax.swing.JButton seleccionarModificarInfo1;
    private javax.swing.JButton seleccionarModificarInfo13;
    private javax.swing.JButton seleccionarModificarInfo2;
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
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTabbedPane tablaClienteLanchas;
    private javax.swing.JTabbedPane tablaClienteLanchas1;
    private javax.swing.JTabbedPane tablaClienteLanchas2;
    private javax.swing.JTabbedPane tablaClienteLanchas3;
    public javax.swing.JList<String> tablaEE;
    public javax.swing.JList<String> tablaEE1;
    public javax.swing.JList<String> tablaEE2;
    public javax.swing.JList<String> tablaEE3;
    public javax.swing.JList<String> tablaEmbarcaciones;
    public javax.swing.JList<String> tablaEmbarcaciones1;
    public javax.swing.JList<String> tablaEmbarcaciones2;
    public javax.swing.JList<String> tablaEmbarcaciones3;
    public javax.swing.JList<String> tablaLanchasAgregar;
    public javax.swing.JList<String> tablaLanchasAgregar1;
    public javax.swing.JList<String> tablaLanchasAgregar2;
    public javax.swing.JList<String> tablaLanchasAgregar3;
    public javax.swing.JList<String> tablaLanchasEliminar;
    public javax.swing.JList<String> tablaLanchasEliminar1;
    public javax.swing.JList<String> tablaLanchasEliminar2;
    public javax.swing.JList<String> tablaLanchasEliminar3;
    public javax.swing.JList<String> tablaLanchasModificar;
    public javax.swing.JList<String> tablaLanchasModificar1;
    public javax.swing.JList<String> tablaLanchasModificar2;
    public javax.swing.JList<String> tablaLanchasModificar3;
    public javax.swing.JList<String> tablaModificarInformacion;
    public javax.swing.JList<String> tablaModificarInformacion1;
    public javax.swing.JList<String> tablaModificarInformacion2;
    public javax.swing.JList<String> tablaModificarInformacion3;
    public javax.swing.JList<String> tablaOdenCrearRepuestos;
    public javax.swing.JList<String> tablaOrdenesCrear1;
    public javax.swing.JList<String> tablaOrdenesCrearManoObra;
    public javax.swing.JList<String> tablaOrdenesCrearVPMO;
    public javax.swing.JList<String> tablaOrdenesCrearVPRepuestos;
    public javax.swing.JList<String> tablaOrdenesV1;
    public javax.swing.JList<String> tablaOrdenesV2;
    public javax.swing.JList<String> tablaOrdenesVisualizar;
    public javax.swing.JList<String> tablaRepuestosCrear;
    public javax.swing.JList<String> tablacontactosEliminados;
    public javax.swing.JList<String> tablacontactosEliminados1;
    public javax.swing.JList<String> tablacontactosEliminados2;
    public javax.swing.JList<String> tablacontactosEliminados3;
    public javax.swing.JList<String> tablacontactosEliminar;
    public javax.swing.JList<String> tablacontactosEliminar1;
    public javax.swing.JList<String> tablacontactosEliminar4;
    public javax.swing.JList<String> tablacontactosEliminar5;
    public javax.swing.JList<String> tablacontactosEliminar6;
    public javax.swing.JList<String> tablacontactosVisualizar;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField tipo1;
    private javax.swing.JTextField tipo2;
    private javax.swing.JTextField tipo3;
    private javax.swing.JPanel visualizar;
    private javax.swing.JPanel visualizar1;
    private javax.swing.JPanel visualizar2;
    private javax.swing.JPanel visualizar3;
    private javax.swing.JTextArea visualizarV1;
    private javax.swing.JTextArea visualizarV2;
    private javax.swing.JTextArea visualizarV3;
    private javax.swing.JTextArea visualizarV4;
    // End of variables declaration//GEN-END:variables
}
