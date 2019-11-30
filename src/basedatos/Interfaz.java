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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Psche
 */
public final class Interfaz extends javax.swing.JFrame {
    //clientes
     HashMap<String ,Cliente> hashmapClientes= new HashMap<>();
     HashMap<String ,Cliente> hashmapClientesBorrados= new HashMap<>();
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
        this.update();
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
                        cliente=this.hashmapClientes.get(this.arrayListContactos.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cliente.getInformacionC().size();l++){
                            bw.write((String) cliente.getInformacionC().get(l));
                            bw.newLine();
                        }
                        if(cliente.getInformacionE().size()!=0){
                            for(int l=0;l<cliente.getInformacionE().size();l++){
                                bw.write((String) cliente.getInformacionE().get(l));
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
                        cliente=this.hashmapClientesBorrados.get(this.arrayListContactosEliminados.get(f));
                        bw.write("#");
                        bw.newLine();
                        for(int l=0;l<cliente.getInformacionC().size();l++){
                            bw.write((String) cliente.getInformacionC().get(l));
                            bw.newLine();
                        }
                        for(int l=0;l<cliente.getInformacionE().size();l++){
                            bw.write((String) cliente.getInformacionE().get(l));
                            bw.newLine();
                        }
                        bw.write("##");
                        bw.newLine();
                    }
              }
        } catch (IOException e) {
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
                        cliente= new Cliente();
                        linea = entrada.readLine(); 
                        cliente.setNombe(linea);
                        //seteamos nombre
                        linea = entrada.readLine(); 
                        cliente.setApellido(linea);
                        //seteamos Celular
                        linea = entrada.readLine(); 
                        cliente.setCelular(linea);
                        //seteamos TelFijo
                        linea = entrada.readLine(); 
                        cliente.setTelFijo(linea);
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cliente.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cliente.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cliente.setCelularCuidador(linea);
                        this.hashmapClientesBorrados.put(cliente.getApellidoNombre(),cliente);
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
                        cliente.addEmbarcacion(embarcacion.getMotor(), embarcacion);
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
            while(entrada.ready()){ 
                linea = entrada.readLine(); 
                while(!"##".equals(linea)){
                    if("#".equals(linea)){
                        //creamos cliente
                        cliente= new Cliente();
                        linea = entrada.readLine(); 
                        cliente.setNombe(linea);
                        //seteamos nombre
                        linea = entrada.readLine(); 
                        cliente.setApellido(linea);
                        //seteamos Celular
                        linea = entrada.readLine(); 
                        cliente.setCelular(linea);
                        //seteamos TelFijo
                        linea = entrada.readLine(); 
                        cliente.setTelFijo(linea);
                        //seteamos Correo
                        linea = entrada.readLine(); 
                        cliente.setCorreo(linea);
                        //seteamos Cuidador
                        linea = entrada.readLine(); 
                        cliente.setCuidador(linea);
                        //seteamos Celular Cuidador
                        linea = entrada.readLine(); 
                        cliente.setCelularCuidador(linea);
                        this.hashmapClientes.put(cliente.getApellidoNombre(),cliente);
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
                        cliente.addEmbarcacion(embarcacion.getMotor(), embarcacion);
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
        hashmapClientes.entrySet().forEach((entry) -> {
            this.arrayListContactos.add(entry.getValue().getApellidoNombre());
            Collections.sort(this.arrayListContactos, new Comparator<String>() {
            public int compare(String n1  , String n2) {
               return n1.compareTo(n2);
   }
});
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
        panelPrincipalContactos = new javax.swing.JPanel();
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
        seleccionadoV = new javax.swing.JLabel();
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
        seleccionadoInfo = new javax.swing.JLabel();
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
        estadoAgregar = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        seleccionarLanchaAgregar = new javax.swing.JButton();
        jTextField15 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablaLanchasAgregar = new javax.swing.JList<>();
        jLabel19 = new javax.swing.JLabel();
        seleccionadoAgregar = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        añadiendo = new javax.swing.JLabel();
        modificarLancha = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        tablaLanchasModificar = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        seleccionado5 = new javax.swing.JLabel();
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
        embarcacionSeleccionada = new javax.swing.JLabel();
        modificarLancha1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        tablaLanchasEliminar = new javax.swing.JList<>();
        seleccionBorrado4 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        seleccionado8 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tablaEE = new javax.swing.JList<>();
        seleccionBorrado5 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        seleccionado6 = new javax.swing.JLabel();
        seleccionBorrado6 = new javax.swing.JButton();
        Ordenes = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();

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

        seleccionadoV.setText("--------------------------------------");

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
                    .addGroup(visualizarLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionadoV, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(seleccionadoV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visualizarLayout.createSequentialGroup()
                                .addComponent(seleccionarV)
                                .addContainerGap())
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)))
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
                .addContainerGap(430, Short.MAX_VALUE))
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

        seleccionadoInfo.setText("------------------------------");

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
                    .addGroup(infoLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seleccionadoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(411, Short.MAX_VALUE))
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
                        .addContainerGap(314, Short.MAX_VALUE))
                    .addGroup(infoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(seleccionadoInfo))
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

        estadoAgregar.setText("---------------------------------------------------------------------------");

        jLabel26.setText("Buscar:");

        jLabel15.setText("Seleccionado: ");

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

        jLabel19.setText("Estado: ");

        seleccionadoAgregar.setText("-----------------------------");

        jLabel18.setText("Añadiendo Lancha a:  ");

        añadiendo.setText("---------------------------------------");

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
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionadoAgregar))
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionarLanchaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agregarLanchaLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(añadiendo))
                            .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(agregarLanchaLayout.createSequentialGroup()
                                    .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel19)
                                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel57)
                                            .addComponent(jLabel58)
                                            .addComponent(jLabel59)
                                            .addComponent(jLabel60)
                                            .addComponent(jLabel56)))
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
                                .addComponent(estadoAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(349, Short.MAX_VALUE))
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
                    .addComponent(jLabel15)
                    .addComponent(seleccionadoAgregar)
                    .addComponent(jLabel18)
                    .addComponent(añadiendo))
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
                        .addGap(19, 19, 19)
                        .addGroup(agregarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(estadoAgregar))
                        .addContainerGap(143, Short.MAX_VALUE))))
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

        seleccionado5.setText("-----------------------------");

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

        jLabel22.setText("Embarcacion");

        embarcacionSeleccionada.setText("-----------------------------------------------");

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
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionado5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(embarcacionSeleccionada))
                            .addGroup(modificarLanchaLayout.createSequentialGroup()
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                                        .addComponent(jButton18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton12)))))))
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
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(embarcacionSeleccionada)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarLanchaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(seleccionado5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton27)
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
                                    .addComponent(jLabel67))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modificarLanchaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton12)
                                    .addComponent(jButton18))))
                        .addContainerGap(178, Short.MAX_VALUE))
                    .addGroup(modificarLanchaLayout.createSequentialGroup()
                        .addComponent(seleccionBorrado3)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        jLabel21.setText("Embarcacion");

        seleccionado8.setText("-----------------------------------------------");

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

        jLabel23.setText("Seleccionado: ");

        seleccionado6.setText("-----------------------------");

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
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionado6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modificarLancha1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seleccionado8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modificarLancha1Layout.createSequentialGroup()
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modificarLancha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(seleccionBorrado4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(seleccionBorrado5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(seleccionBorrado6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(459, 459, Short.MAX_VALUE))
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
                    .addComponent(seleccionado8)
                    .addComponent(jLabel23)
                    .addComponent(seleccionado6))
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
            .addGap(0, 1158, Short.MAX_VALUE)
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

        javax.swing.GroupLayout panelPrincipalContactosLayout = new javax.swing.GroupLayout(panelPrincipalContactos);
        panelPrincipalContactos.setLayout(panelPrincipalContactosLayout);
        panelPrincipalContactosLayout.setHorizontalGroup(
            panelPrincipalContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelPrincipalContactosLayout.setVerticalGroup(
            panelPrincipalContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editaraClientes)
        );

        Main.addTab("CLIENTES", panelPrincipalContactos);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Visualizar", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Crear", jPanel10);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Eliminar", jPanel11);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Editar", jPanel12);

        javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
        Ordenes.setLayout(OrdenesLayout);
        OrdenesLayout.setHorizontalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        OrdenesLayout.setVerticalGroup(
            OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        Main.addTab("ORDENES", Ordenes);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Visualizar", jPanel13);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Crear", jPanel14);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Eliminar", jPanel15);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Editar", jPanel16);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        Main.addTab("Guarderias", jPanel3);

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Main)
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
            this.seleccionadoV.setText(cliente.getApellidoNombre());
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
       this.seleccionadoInfo.setText("------------------------------");
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
       this.seleccionadoInfo.setText("------------------------------");
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
          this.seleccionadoInfo.setText(cliente.getApellidoNombre());
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
            this.seleccionado8.setText("-----------------------------------------------");
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
          this.seleccionado6.setText(cliente.getApellidoNombre());
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
         }
         embarcacion=null;
         cliente=null;
         this.seleccionado5.setText("-----------------------------");
         this.embarcacionSeleccionada.setText("-----------------------------------------------");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void nSerie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nSerie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nSerie1ActionPerformed

    private void seleccionBorrado3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionBorrado3ActionPerformed
        if(this.tablaEmbarcaciones.getSelectedValue()!=null){
            
            embarcacion=cliente.embarcaciones.get(this.tablaEmbarcaciones.getSelectedValue());
            this.embarcacionSeleccionada.setText(embarcacion.getMotor());
            this.t.setText(embarcacion.getTipo());
            this.m.setText(embarcacion.getMarca());
            this.mot.setText(embarcacion.getMotor());
            this.mo.setText(embarcacion.getModelo());
            this.nSerie1.setText(embarcacion.getnSerie());
             System.out.println(embarcacion.getInformacion());
        }  
        
    }//GEN-LAST:event_seleccionBorrado3ActionPerformed

    private void seleccionBorrado3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionBorrado3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionBorrado3MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
       if(this.tablaLanchasModificar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasModificar.getSelectedValue());
          this.seleccionado5.setText(cliente.getApellidoNombre());
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
            this.seleccionadoAgregar.setText(cliente.getApellidoNombre());
        }
        if(cliente!=null){
            this.añadiendo.setText(cliente.getApellidoNombre());
            cliente=new Cliente();
            cliente=this.hashmapClientes.get(this.tablaLanchasAgregar.getSelectedValue());
            this.estadoAgregar.setText("Editando");
        }
    }//GEN-LAST:event_seleccionarLanchaAgregarActionPerformed

    private void seleccionarLanchaAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccionarLanchaAgregarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_seleccionarLanchaAgregarMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.vaciarBarras();
        this.seleccionadoAgregar.setText("-------------------------------");
        this.añadiendo.setText("---------------------------------------");
        this.estadoAgregar.setText("---------------------------------------------------------------------------");
        cliente=null;
    }//GEN-LAST:event_jButton17ActionPerformed

    private void guardarLanchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarLanchaActionPerformed
        if(tipo.getText().length()!=0 && marca.getText().length()!=0 && modelo.getText().length()!=0 && motor.getText().length()!=0 
            && nSerie.getText().length()!=0){     
            embarcacion=new Embarcacion();
            embarcacion.setTipo(this.tipo.getText());
            embarcacion.setMarca(this.marca.getText());
            embarcacion.setModelo(this.modelo.getText());
            embarcacion.setMotor(this.motor.getText());
            embarcacion.setnSerie(this.nSerie.getText());
            cliente.addEmbarcacion(embarcacion.getMotor(), embarcacion);
            this.estadoAgregar.setText("Lancha: "+embarcacion.getMotor()+" añadida exitosamente");
            this.actualizarTXTContactos();
            this.update();
            
        }   
        this.vaciarBarras();
        this.seleccionadoAgregar.setText("-------------------------------");
        this.añadiendo.setText("---------------------------------------");
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
            this.seleccionado8.setText(embarcacion.getMotor());
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
            //this.update();
         }
        if(this.tablaLanchasEliminar.getSelectedValue()!=null){
          cliente=new Cliente();
          cliente=this.hashmapClientes.get(this.tablaLanchasEliminar.getSelectedValue());
        }
        this.tablaEE.setListData(cliente.getListadoEmbarcaciones());
        
    }//GEN-LAST:event_seleccionBorrado6ActionPerformed
       
        
        
    
    
    
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
    private javax.swing.JPanel Fondo;
    private javax.swing.JTabbedPane Main;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel agregarLancha;
    private javax.swing.JLabel añadiendo;
    private javax.swing.JTextField bApellidoC;
    private javax.swing.JTextField bApellidoE;
    private javax.swing.JTextField bCelCuidadorC;
    private javax.swing.JTextField bCelCuidadorE;
    private javax.swing.JTextField bCelularC;
    private javax.swing.JTextField bCelularE;
    private javax.swing.JTextField bCorreoC;
    private javax.swing.JTextField bCorreoE;
    private javax.swing.JTextField bCuidadorC;
    private javax.swing.JTextField bCuidadorE;
    private javax.swing.JTextField bNombreC;
    private javax.swing.JTextField bNombreE;
    private javax.swing.JTextField bTelFijE;
    private javax.swing.JTextField bTelFijoC;
    private javax.swing.JButton contactosCrear;
    private javax.swing.JPanel crear;
    private javax.swing.JPanel editar1;
    private javax.swing.JTabbedPane editaraClientes;
    private javax.swing.JPanel eliminar;
    private javax.swing.JButton eliminarEliminar;
    private javax.swing.JTabbedPane eliminarLanchaa;
    private javax.swing.JLabel embarcacionSeleccionada;
    private javax.swing.JLabel estadoAgregar;
    private javax.swing.JButton guardarLancha;
    private javax.swing.JPanel info;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JPanel lanch;
    private javax.swing.JTextField m;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField mo;
    private javax.swing.JTextField modelo;
    private javax.swing.JPanel modificarLancha;
    private javax.swing.JPanel modificarLancha1;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField motor;
    private javax.swing.JTextField nSerie;
    private javax.swing.JTextField nSerie1;
    private javax.swing.JPanel panelPrincipalContactos;
    private javax.swing.JButton seleccionBorrado;
    private javax.swing.JButton seleccionBorrado3;
    private javax.swing.JButton seleccionBorrado4;
    private javax.swing.JButton seleccionBorrado5;
    private javax.swing.JButton seleccionBorrado6;
    private javax.swing.JLabel seleccionado5;
    private javax.swing.JLabel seleccionado6;
    private javax.swing.JLabel seleccionado8;
    private javax.swing.JLabel seleccionadoAgregar;
    private javax.swing.JLabel seleccionadoInfo;
    private javax.swing.JLabel seleccionadoV;
    private javax.swing.JButton seleccionarLanchaAgregar;
    private javax.swing.JButton seleccionarModificarInfo;
    private javax.swing.JButton seleccionarV;
    private javax.swing.JTextField t;
    private javax.swing.JTabbedPane tablaClienteLanchas;
    public javax.swing.JList<String> tablaEE;
    public javax.swing.JList<String> tablaEmbarcaciones;
    public javax.swing.JList<String> tablaLanchasAgregar;
    public javax.swing.JList<String> tablaLanchasEliminar;
    public javax.swing.JList<String> tablaLanchasModificar;
    public javax.swing.JList<String> tablaModificarInformacion;
    public javax.swing.JList<String> tablacontactosEliminados;
    public javax.swing.JList<String> tablacontactosEliminar;
    public javax.swing.JList<String> tablacontactosVisualizar;
    private javax.swing.JTextField tipo;
    private javax.swing.JPanel visualizar;
    private javax.swing.JTextArea visualizarContactoCreado;
    private javax.swing.JTextArea visualizarV;
    // End of variables declaration//GEN-END:variables
}
