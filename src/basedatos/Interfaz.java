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
import java.util.function.Consumer;

/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    //clientes
     HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
     HashMap<String ,Cliente> hashmapClientesBorrados= new HashMap<>();
     HashMap<String ,Orden> hashmapOrdenes= new HashMap<>();
     Cliente cliente= new Cliente();
     //contactos
     ArrayList<String> arrayListContactos=new ArrayList<>();
     ArrayList<String> arrayListContactosEliminados=new ArrayList<>();
     String [] listadoClientes = new String[1000];
     String [] listadoClientesEliminados = new String[1000];	
     
     File fNuevo = new File("Clientes.txt");

     //Embarcaciones
     Embarcacion embarcacion= new Embarcacion();
     public Interfaz() {
         
        this.setVisible(true);
        this.initComponents();
        this.cargarClientes();
        this.cargarClientesEliminados();
        this.cargarOrdenes();
        this.update();
        actualizarTXTContactos();
        //interfaz();
       
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
            String linea="";
            Orden or= new Orden();
            
            while(entrada.ready()){ 
                //linea = entrada.readLine();
                //System.out.println("linea pre inicio "+linea);
                linea = entrada.readLine();
               // System.out.println("********* INICIO *************");
                //System.out.println("linea ACTUAL "+linea);

                while(!"##".equals(linea)&& entrada.ready()){
                    
                    //System.out.println("ENTRANDO a orden: "+linea);
                    if("#".equals(linea)){
                        //creamos Orden
                        or= new Orden();
                        linea = entrada.readLine(); 
                        or.setNumeroOrden(linea);
                        //seteamos nombre
                        linea = entrada.readLine(); 
                        or.setTotalManoObra(linea);
                        //seteamos Celular
                        linea = entrada.readLine(); 
                        or.setTotalVarios(linea);
                        //seteamos TelFijo
                        linea = entrada.readLine(); 
                        or.setTotal(linea);
                        this.hashmapOrdenes.put(or.getNumeroOrden(),or);
                        linea = entrada.readLine(); 
                        //System.out.println("ORDEN: "+or.getNumeroOrden()+" Agregada ");
                    }
                    if("*".equals(linea)){
                        linea = entrada.readLine(); 
                        or.manoObra=new ArrayList<>();
                        while(!"**".equals(linea)){
                            //System.out.println("+++LINEA: "+linea);
                            or.addManoObra(linea);
                            linea = entrada.readLine(); 
                        }//System.out.println("Mano obra agregada");
                    }
                    String cant="";
                    //linea = entrada.readLine();
                    while(!"***".equals(cant)){
                        linea = entrada.readLine();
                        cant = linea;
                        linea = entrada.readLine();
                        if(!"***".equals(cant)){
                           or.addRepuesto(cant, linea);
                        }
                    }
                }
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
            //extrae contactos
            
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
    public void actualizarContactos(){
        listadoClientes=new String[1000];
        this.arrayListContactos=new  ArrayList<>();
        hashmapClientes.entrySet().forEach(new Consumer<Map.Entry<String, Cliente>>() {
            @Override
            public void accept(Map.Entry<String, Cliente> entry) {
                Interfaz.this.arrayListContactos.add(entry.getValue().getApellidoNombre());
                Collections.sort(Interfaz.this.arrayListContactos, String::compareTo);
            }
        });
        for (int g=0;g<this.arrayListContactos.size();g++) {
            listadoClientes[g]=this.arrayListContactos.get(g);
            //System.out.println("\nExistente: "+this.listadoClientes[g]);
        }
         //System.out.println("ArrayList contactos:"+this.arrayListContactos.toString());
         
       
        //this.contactos.add(cliente.getApellidoNombre());
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
    public void vaciarTablas(){
        String [] vacio = new String[1000];
        this.tablacontactosVisualizar.setListData(vacio);
        this.tablaModificarInformacion.setListData(vacio);
        this.tablacontactosEliminar.setListData(vacio);
        this.tablaEmbarcaciones.setListData(vacio);
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
        Clienteees = new javax.swing.JPanel();
        editaraClientes = new javax.swing.JTabbedPane();
        visualizar = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablacontactosVisualizar = new javax.swing.JList<>();
        seleccionarV = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        visualizarV = new javax.swing.JTextArea();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        visualizarContactoCreado = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
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
        tablacontactosVisualizar1 = new javax.swing.JList<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        visualizarV1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        seleccionarV2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        seleccionarV3 = new javax.swing.JButton();
        crear1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        bNombreC1 = new javax.swing.JTextField();
        bApellidoC1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        bCelularC1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        bTelFijoC1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        bCorreoC1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        bCuidadorC1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        bCelCuidadorC1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        contactosCrear1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        visualizarContactoCreado1 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
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
        seleccionBorrado8 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tablaEE1 = new javax.swing.JList<>();
        seleccionBorrado9 = new javax.swing.JButton();
        sel1 = new javax.swing.JLabel();
        seleccionBorrado10 = new javax.swing.JButton();
        estado5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servinautica v0.1");
        setBackground(java.awt.SystemColor.controlDkShadow);

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

        visualizarV.setEditable(false);
        visualizarV.setColumns(20);
        visualizarV.setRows(1000);
        visualizarV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane9.setViewportView(visualizarV);

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
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(337, 345, Short.MAX_VALUE))
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
                                .addContainerGap())
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)))
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))))
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

        visualizarContactoCreado.setEditable(false);
        visualizarContactoCreado.setColumns(20);
        visualizarContactoCreado.setRows(50);
        jScrollPane4.setViewportView(visualizarContactoCreado);

        jLabel1.setText("Ultimos Contactos Creados");

        javax.swing.GroupLayout crearLayout = new javax.swing.GroupLayout(crear);
        crear.setLayout(crearLayout);
        crearLayout.setHorizontalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(bApellidoC, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crearLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(12, 12, 12)
                        .addComponent(contactosCrear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crearLayout.setVerticalGroup(
            crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearLayout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(crearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(contactosCrear))
                        .addContainerGap())
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(390, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eliminarLayout.createSequentialGroup()
                        .addGroup(eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionBorrado)
                            .addComponent(eliminarEliminar))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addContainerGap(371, Short.MAX_VALUE))
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
                        .addContainerGap(166, Short.MAX_VALUE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(infoLayout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo)
                                .addContainerGap())))))
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
                .addContainerGap(356, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addContainerGap(144, Short.MAX_VALUE))))
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
                    .addComponent(jScrollPane15)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addGap(141, 164, Short.MAX_VALUE))))
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
                            .addComponent(estado1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))))
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
                    .addComponent(jScrollPane17)
                    .addGroup(modificarLancha1Layout.createSequentialGroup()
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
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
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
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

        tablaClienteLanchas.addTab("Lanchas", lanch);

        javax.swing.GroupLayout editar1Layout = new javax.swing.GroupLayout(editar1);
        editar1.setLayout(editar1Layout);
        editar1Layout.setHorizontalGroup(
            editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1118, Short.MAX_VALUE)
            .addGroup(editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar1Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas)
                    .addContainerGap()))
        );
        editar1Layout.setVerticalGroup(
            editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
            .addGroup(editar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar1Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas)
                    .addContainerGap()))
        );

        editaraClientes.addTab("Modificar", editar1);

        javax.swing.GroupLayout ClienteeesLayout = new javax.swing.GroupLayout(Clienteees);
        Clienteees.setLayout(ClienteeesLayout);
        ClienteeesLayout.setHorizontalGroup(
            ClienteeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ClienteeesLayout.setVerticalGroup(
            ClienteeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes)
        );

        Main.addTab("CLIENTES", Clienteees);

        tablacontactosVisualizar1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(tablacontactosVisualizar1);

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
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(337, 345, Short.MAX_VALUE))
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
                                .addContainerGap())
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)))
                    .addGroup(visualizar1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))))
        );

        editaraClientes1.addTab("Visualizar", visualizar1);

        jLabel11.setText("Nombre:");

        bNombreC1.setText(" ");
        bNombreC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNombreC1ActionPerformed(evt);
            }
        });

        bApellidoC1.setText(" ");
        bApellidoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bApellidoC1ActionPerformed(evt);
            }
        });

        jLabel19.setText("Apellido:");

        bCelularC1.setText(" ");

        jLabel20.setText("Celular: ");

        bTelFijoC1.setText(" ");
        bTelFijoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelFijoC1ActionPerformed(evt);
            }
        });

        jLabel23.setText("Tel. Fijo:");

        bCorreoC1.setText(" ");
        bCorreoC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorreoC1ActionPerformed(evt);
            }
        });

        jLabel24.setText("Correo: ");

        bCuidadorC1.setText(" ");

        jLabel29.setText("Cuidador:");

        jLabel30.setText("Cel. Cuidador :");

        bCelCuidadorC1.setText(" ");

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        contactosCrear1.setText("Crear");
        contactosCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactosCrear1ActionPerformed(evt);
            }
        });

        visualizarContactoCreado1.setEditable(false);
        visualizarContactoCreado1.setColumns(20);
        visualizarContactoCreado1.setRows(50);
        jScrollPane7.setViewportView(visualizarContactoCreado1);

        jLabel31.setText("Ultimos Contactos Creados");

        javax.swing.GroupLayout crear1Layout = new javax.swing.GroupLayout(crear1);
        crear1.setLayout(crear1Layout);
        crear1Layout.setHorizontalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(4, 4, 4)
                        .addComponent(bCelCuidadorC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel29)
                            .addComponent(jLabel11))
                        .addGap(31, 31, 31)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bNombreC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCorreoC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCuidadorC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bTelFijoC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCelularC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bApellidoC1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crear1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(12, 12, 12)
                        .addComponent(contactosCrear1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crear1Layout.setVerticalGroup(
            crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crear1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crear1Layout.createSequentialGroup()
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(bNombreC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bApellidoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(10, 10, 10)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(bCelularC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bTelFijoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCorreoC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCuidadorC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCelCuidadorC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(crear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(contactosCrear1))
                        .addContainerGap())
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        editaraClientes1.addTab("Crear", crear1);

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
                .addContainerGap(390, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
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
                    .addGroup(info1Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(info1Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionarModificarInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addComponent(bApellidoE1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        info1Layout.setVerticalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24))
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
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
                            .addComponent(jButton16))
                        .addContainerGap(166, Short.MAX_VALUE))
                    .addGroup(info1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(info1Layout.createSequentialGroup()
                                .addComponent(seleccionarModificarInfo1)
                                .addContainerGap())))))
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
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha1Layout.createSequentialGroup()
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarLanchaAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(agregarLancha1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(seleccionadoAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(añadiendolanchaa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(356, Short.MAX_VALUE))
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
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seleccionadoAgregar2)
                    .addComponent(añadiendolanchaa1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(agregarLancha1Layout.createSequentialGroup()
                        .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLancha1Layout.createSequentialGroup()
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agregarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(marca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62)))
                            .addComponent(seleccionarLanchaAgregar1))
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
                        .addComponent(estado3)
                        .addContainerGap(144, Short.MAX_VALUE))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha2Layout.createSequentialGroup()
                                .addComponent(seleccionBorrado7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
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
                            .addGroup(modificarLancha2Layout.createSequentialGroup()
                                .addComponent(estado4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton20)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(modificarLancha2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton20)
                                            .addComponent(jButton21)))
                                    .addGroup(modificarLancha2Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(estado4)))))
                        .addGap(141, 164, Short.MAX_VALUE))))
        );

        eliminarLanchaa1.addTab("Modificar", modificarLancha2);

        jLabel41.setText("Buscar:");

        jTextField21.setText(" ");

        jButton33.setText("Buscar");

        jButton34.setText("Seleccionar");
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

        seleccionBorrado8.setText("Cancelar");
        seleccionBorrado8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionBorrado8MouseClicked(evt);
            }
        });
        seleccionBorrado8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionBorrado8ActionPerformed(evt);
            }
        });

        jLabel76.setText("Embarcacion: ");

        tablaEE1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane25.setViewportView(tablaEE1);

        seleccionBorrado9.setText("Seleccionar");
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

        estado5.setText("Estado: Seleccionando Cliente");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(seleccionBorrado8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionBorrado10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estado5, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))))
                .addGap(186, 186, 186))
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
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addGroup(modificarLancha3Layout.createSequentialGroup()
                                .addGroup(modificarLancha3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton34)
                                    .addGroup(modificarLancha3Layout.createSequentialGroup()
                                        .addComponent(seleccionBorrado9)
                                        .addGap(14, 14, 14)
                                        .addComponent(seleccionBorrado8)))
                                .addGap(18, 18, 18)
                                .addComponent(seleccionBorrado10)
                                .addGap(54, 54, 54)
                                .addComponent(estado5)
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

        tablaClienteLanchas1.addTab("Lanchas", lanch1);

        javax.swing.GroupLayout editar2Layout = new javax.swing.GroupLayout(editar2);
        editar2.setLayout(editar2Layout);
        editar2Layout.setHorizontalGroup(
            editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1118, Short.MAX_VALUE)
            .addGroup(editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar2Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas1)
                    .addContainerGap()))
        );
        editar2Layout.setVerticalGroup(
            editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
            .addGroup(editar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editar2Layout.createSequentialGroup()
                    .addComponent(tablaClienteLanchas1)
                    .addContainerGap()))
        );

        editaraClientes1.addTab("Modificar", editar2);

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes1)
        );

        Main.addTab("ORDENES", Ordenes);

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addComponent(Main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
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
            this.visualizarV.setText("----------------------------------------------------------------------\n                                   DATOS"
                    +cliente.getInformacionCiente()+"\n----------------------------------------------------------------------"
                    +cliente.getInformacionEmbarcacuiones()+"\n----------------------------------------------------------------------");
            this.jLabel12.setText("Seleccionado: "+cliente.getApellidoNombre());
    }//GEN-LAST:event_seleccionarVActionPerformed
    }    public void vaciarBarras(){
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
    public void update(){
        this.vaciarTablas();
        this.vaciarBarras();
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
            this.visualizarContactoCreado.setText(cliente.getInformacionCiente()+this.visualizarContactoCreado.getText());
            this.update();
            this.actualizarTXTContactos();
       }
    }//GEN-LAST:event_contactosCrearActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.vaciarBarras();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void eliminarEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEliminarMouseClicked

    private void eliminarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEliminarActionPerformed
        
        if(this.tablacontactosEliminar.getSelectedValue()!=null){
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablacontactosEliminar.getSelectedValue());
        }
        this.update();
        
        if(cliente!=null){
            this.hashmapClientesBorrados.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientes.remove(cliente.getApellidoNombre(),cliente);
            this.update();
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
        this.update();
        if(cliente!=null){
            this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
            this.hashmapClientesBorrados.remove(cliente.getApellidoNombre(),cliente);
            this.update(); // TODO add your handling code here:
            System.out.println("Existentes: "+Arrays.toString(listadoClientes));
            System.out.println("Eliminados: "+Arrays.toString(listadoClientesEliminados));
            this.actualizarTXTContactos();
            this.actualizarTXTContactosEliminados();
        }
    }//GEN-LAST:event_seleccionBorradoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

       this.vaciarBarras();
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
            this.update();
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
            this.update();
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
        this.vaciarBarras();
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
            this.update();
            
        }   
        this.vaciarBarras();
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

    private void bNombreC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNombreC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNombreC1ActionPerformed

    private void bApellidoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bApellidoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bApellidoC1ActionPerformed

    private void bTelFijoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelFijoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bTelFijoC1ActionPerformed

    private void bCorreoC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorreoC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorreoC1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void contactosCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactosCrear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactosCrear1ActionPerformed

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

    private void seleccionBorrado8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado8MouseClicked

    private void seleccionBorrado8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado8ActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarV3ActionPerformed
       
        
        
    
    
    
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
    private javax.swing.JPanel Clienteees;
    private javax.swing.JPanel Fondo;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel agregarLancha;
    private javax.swing.JPanel agregarLancha1;
    private javax.swing.JLabel añadiendolanchaa;
    private javax.swing.JLabel añadiendolanchaa1;
    private javax.swing.JTextField bApellidoC;
    private javax.swing.JTextField bApellidoC1;
    private javax.swing.JTextField bApellidoE;
    private javax.swing.JTextField bApellidoE1;
    private javax.swing.JTextField bCelCuidadorC;
    private javax.swing.JTextField bCelCuidadorC1;
    private javax.swing.JTextField bCelCuidadorE;
    private javax.swing.JTextField bCelCuidadorE1;
    private javax.swing.JTextField bCelularC;
    private javax.swing.JTextField bCelularC1;
    private javax.swing.JTextField bCelularE;
    private javax.swing.JTextField bCelularE1;
    private javax.swing.JTextField bCorreoC;
    private javax.swing.JTextField bCorreoC1;
    private javax.swing.JTextField bCorreoE;
    private javax.swing.JTextField bCorreoE1;
    private javax.swing.JTextField bCuidadorC;
    private javax.swing.JTextField bCuidadorC1;
    private javax.swing.JTextField bCuidadorE;
    private javax.swing.JTextField bCuidadorE1;
    private javax.swing.JTextField bNombreC;
    private javax.swing.JTextField bNombreC1;
    private javax.swing.JTextField bNombreE;
    private javax.swing.JTextField bNombreE1;
    private javax.swing.JTextField bTelFijE;
    private javax.swing.JTextField bTelFijE1;
    private javax.swing.JTextField bTelFijoC;
    private javax.swing.JTextField bTelFijoC1;
    private javax.swing.JButton contactosCrear;
    private javax.swing.JButton contactosCrear1;
    private javax.swing.JPanel crear;
    private javax.swing.JPanel crear1;
    private javax.swing.JPanel editar1;
    private javax.swing.JPanel editar2;
    private javax.swing.JTabbedPane editaraClientes;
    private javax.swing.JTabbedPane editaraClientes1;
    private javax.swing.JPanel eliminar;
    private javax.swing.JPanel eliminar1;
    private javax.swing.JButton eliminarEliminar;
    private javax.swing.JButton eliminarEliminar1;
    private javax.swing.JTabbedPane eliminarLanchaa;
    private javax.swing.JTabbedPane eliminarLanchaa1;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel estado1;
    private javax.swing.JLabel estado2;
    private javax.swing.JLabel estado3;
    private javax.swing.JLabel estado4;
    private javax.swing.JLabel estado5;
    private javax.swing.JButton guardarLancha;
    private javax.swing.JButton guardarLancha1;
    private javax.swing.JPanel info;
    private javax.swing.JPanel info1;
    private javax.swing.JButton jButton10;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JPanel lanch;
    private javax.swing.JPanel lanch1;
    private javax.swing.JTextField m;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField marca1;
    private javax.swing.JTextField mo;
    private javax.swing.JTextField mo1;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField modelo1;
    private javax.swing.JPanel modificarLancha;
    private javax.swing.JPanel modificarLancha1;
    private javax.swing.JPanel modificarLancha2;
    private javax.swing.JPanel modificarLancha3;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField mot1;
    private javax.swing.JTextField motor;
    private javax.swing.JTextField motor1;
    private javax.swing.JTextField nSerie;
    private javax.swing.JTextField nSerie1;
    private javax.swing.JTextField nSerie2;
    private javax.swing.JTextField nSerie3;
    private javax.swing.JLabel sel;
    private javax.swing.JLabel sel1;
    private javax.swing.JButton seleccionBorrado;
    private javax.swing.JButton seleccionBorrado1;
    private javax.swing.JButton seleccionBorrado10;
    private javax.swing.JButton seleccionBorrado3;
    private javax.swing.JButton seleccionBorrado4;
    private javax.swing.JButton seleccionBorrado5;
    private javax.swing.JButton seleccionBorrado6;
    private javax.swing.JButton seleccionBorrado7;
    private javax.swing.JButton seleccionBorrado8;
    private javax.swing.JButton seleccionBorrado9;
    private javax.swing.JLabel seleccionadoAgregar1;
    private javax.swing.JLabel seleccionadoAgregar2;
    private javax.swing.JButton seleccionarLanchaAgregar;
    private javax.swing.JButton seleccionarLanchaAgregar1;
    private javax.swing.JButton seleccionarModificarInfo;
    private javax.swing.JButton seleccionarModificarInfo1;
    private javax.swing.JButton seleccionarV;
    private javax.swing.JButton seleccionarV2;
    private javax.swing.JButton seleccionarV3;
    private javax.swing.JTextField t;
    private javax.swing.JTextField t1;
    private javax.swing.JTabbedPane tablaClienteLanchas;
    private javax.swing.JTabbedPane tablaClienteLanchas1;
    public javax.swing.JList<String> tablaEE;
    public javax.swing.JList<String> tablaEE1;
    public javax.swing.JList<String> tablaEmbarcaciones;
    public javax.swing.JList<String> tablaEmbarcaciones1;
    public javax.swing.JList<String> tablaLanchasAgregar;
    public javax.swing.JList<String> tablaLanchasAgregar1;
    public javax.swing.JList<String> tablaLanchasEliminar;
    public javax.swing.JList<String> tablaLanchasEliminar1;
    public javax.swing.JList<String> tablaLanchasModificar;
    public javax.swing.JList<String> tablaLanchasModificar1;
    public javax.swing.JList<String> tablaModificarInformacion;
    public javax.swing.JList<String> tablaModificarInformacion1;
    public javax.swing.JList<String> tablacontactosEliminados;
    public javax.swing.JList<String> tablacontactosEliminados1;
    public javax.swing.JList<String> tablacontactosEliminar;
    public javax.swing.JList<String> tablacontactosEliminar1;
    public javax.swing.JList<String> tablacontactosVisualizar;
    public javax.swing.JList<String> tablacontactosVisualizar1;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextField tipo1;
    private javax.swing.JPanel visualizar;
    private javax.swing.JPanel visualizar1;
    private javax.swing.JTextArea visualizarContactoCreado;
    private javax.swing.JTextArea visualizarContactoCreado1;
    private javax.swing.JTextArea visualizarV;
    private javax.swing.JTextArea visualizarV1;
    // End of variables declaration//GEN-END:variables
}
