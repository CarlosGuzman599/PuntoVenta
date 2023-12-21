/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.PrintException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CARLITOS
 */
public class Principal extends javax.swing.JFrame implements Runnable{
    
    private final BaseDeDatos BaseDeDatos = new BaseDeDatos();
    
    Image icono = Toolkit.getDefaultToolkit().getImage("src\\Imagenes\\IconoSistema.png");
    Icon IconoHecho = new ImageIcon("src\\Imagenes\\notification_done.png");
    Icon IconoError = new ImageIcon("src\\Imagenes\\notification_error.png");
    String UsuarioActual = "Iniciar Sesion";
    
    String hora,minutos,segundos,ampm;
    String Dia,Mes,Ano;
    Month mes = LocalDate.now().getMonth();
    Calendar calendario;    
    Thread h1;

    /**
     * Creates new form Principal
     */
    public Principal() throws ParseException, IOException, PrintException {
        //########################## PRINCIPAL ##########################
        initComponents();
        this.setTitle("                                                                                                                                                                                                                ABARROTES Y CEREALES");
        this.setSize(1320, 1050);
        this.setLocationRelativeTo(null);
        this.setIconImage(icono);
        
        h1 = new Thread(this);
        h1.start();
        taPrincipalNotificaiones.setEnabled(Boolean.FALSE);
        lbPrincipalNoVenta.setText(BaseDeDatos.PrincipalVentaProceso());
        PrincipalLimpiatablaVentas();
        BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
        ActualizaNotificacion();
        PrincipalVerificaSesion();
        //BaseDeDatos.sacacosas();
        //########################## LOG IN ##########################
        LogIn.setTitle("LogIng");
        LogIn.setSize(450,550);
        LogIn.setLocationRelativeTo(null);
        LogIn.setIconImage(icono);
        tbPrincipalVenta.grabFocus();
        
        //########################## BUSCAR PRODUCTO ##########################
        BuscarProducto.setIconImage(icono);
        BuscarProducto.setTitle("Buscar Producto");
        BuscarProducto.setSize(800, 540);
        BuscarProducto.setLocationRelativeTo(null);
        BuscarProducto.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //######################### PRODUCTOS #################################
        Productos.setIconImage(icono);
        Productos.setTitle("Productos");
        Productos.setSize(1300, 660);
        Productos.setLocationRelativeTo(null);
        Productos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //######################### FINALIZAR COMPRA #################################
        FinalizaCompra.setIconImage(icono);
        FinalizaCompra.setTitle("FINALIZANDO COMPRA...");
        FinalizaCompra.setSize(400,480);
        FinalizaCompra.setLocationRelativeTo(null);
        
        //######################### USUARIOS #################################
        Usuarios.setIconImage(icono);
        Usuarios.setTitle("USUARIOS");
        Usuarios.setSize(375, 520);
        Usuarios.setLocationRelativeTo(null);  
        
        //######################### VALIDA ADMINISTRADOR #################################
        ValidaAdministrador.setSize(460, 210);
        ValidaAdministrador.setLocationRelativeTo(null);
        
        //######################### VENTAS #################################
        Ventas.setSize(930, 700);
        Ventas.setLocationRelativeTo(null);
        Ventas.setTitle("VENTAS");
        Ventas.setIconImage(icono);  
        
        //######################### NOTAS #################################
        Notas.setSize(560,210);
        Notas.setLocationRelativeTo(null);
        Notas.setIconImage(icono);
        Notas.setTitle("NOTAS");
        
        rgNotasSeleccion.add(rbNotasDia);
        rgNotasSeleccion.add(rbNotasMes);
        rgNotasSeleccion.add(rbNotasAno);
        
        //######################### CONFIGURACION #######################
        Configuracion.setSize(760, 490);
        Configuracion.setLocationRelativeTo(null);
        Configuracion.setIconImage(icono);
        Configuracion.setTitle("CONFIGURACION");
        
        //######################### AGREGA RAPIDO ########################
        AgregaProdutoRapido.setSize(680,250);
        AgregaProdutoRapido.setLocationRelativeTo(null);
        AgregaProdutoRapido.setIconImage(icono);
        AgregaProdutoRapido.setTitle("AGREGA RAPIDO");
        
        //######################### SALIDA ########################
        Salidas.setSize(600, 200);
        Salidas.setLocationRelativeTo(null);
        Salidas.setIconImage(icono);
        Salidas.setTitle("SALIDAS");
        
        //######################### GESTOR SALIDA ########################
        GestorSalidas.setSize(700, 570);
        GestorSalidas.setLocationRelativeTo(null);
        GestorSalidas.setIconImage(icono);
        GestorSalidas.setTitle("GESTOR SALIDA");
        
        //######################### INVENTARIO RAPIDO #########################
        InventarioRapido.setSize(860,750);
        InventarioRapido.setLocationRelativeTo(null);
        InventarioRapido.setIconImage(icono);
        InventarioRapido.setTitle("AGREGADO A INVENTARIO RAPIDO");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogIn = new javax.swing.JDialog();
        ImagenLogIn = new javax.swing.JLabel();
        cbLogIUsuarios = new javax.swing.JComboBox<>();
        pfLogInContraseña = new javax.swing.JPasswordField();
        btLogInIniciar = new javax.swing.JButton();
        BuscarProducto = new javax.swing.JDialog();
        tfBusscarProducto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbBuscar = new javax.swing.JTable();
        Productos = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        tfProductosBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfProductoCodigoBarras = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfProductoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfProductoPrecio = new javax.swing.JTextField();
        cbProductoCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        spProductosStock = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        btProductoAgregar = new javax.swing.JButton();
        btProductoEliminar = new javax.swing.JButton();
        btProductoModificar = new javax.swing.JButton();
        btProductoCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        spProductosProductosDisponibles = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfProductoDia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfProductoMes = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfProductoAno = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btProductoHabilitar = new javax.swing.JButton();
        btProductosDeshabilitar = new javax.swing.JButton();
        FinalizaCompra = new javax.swing.JDialog();
        lbfinalizarCompraTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfFinalizarPago = new javax.swing.JTextField();
        lbfinalizarCambio = new javax.swing.JLabel();
        btFinalizarTarjeta = new javax.swing.JButton();
        btFinalizarEfectivo = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lbFinalizarTicket = new javax.swing.JLabel();
        Usuarios = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        tfUsuarioNombreUsuario = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tfUsuarioContra = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfUsuarioNombreEmpleado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        taUsuarioDatosEmpleado = new javax.swing.JTextArea();
        btUsuarioNuevo = new javax.swing.JButton();
        btUsuarioModificar = new javax.swing.JButton();
        btUsuarioEliminar = new javax.swing.JButton();
        btUsuarioCancelarSeleccion = new javax.swing.JButton();
        ValidaAdministrador = new javax.swing.JDialog();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tfValidaAdministradorPass = new javax.swing.JPasswordField();
        lbValidaAdministradorModulo = new javax.swing.JLabel();
        btValidaAdministradorSalir = new javax.swing.JButton();
        btValidaAdministradorAcceder = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        Ventas = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        spVentasDia = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        cbVentasMes = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        spVentasAno = new javax.swing.JSpinner();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbVentas = new javax.swing.JTable();
        btventaBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbVentaId = new javax.swing.JLabel();
        lbVentaTotal = new javax.swing.JLabel();
        lbVentaEmpleado = new javax.swing.JLabel();
        lbVentaFecha = new javax.swing.JLabel();
        lbVentaHora = new javax.swing.JLabel();
        lbVentaStatus = new javax.swing.JLabel();
        lbVentaCambio = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbVentaContenido = new javax.swing.JTable();
        btVentaCancelarVenta = new javax.swing.JButton();
        btVentaImprimirTicket = new javax.swing.JButton();
        btventaBuscar1 = new javax.swing.JButton();
        btVentaReanudarVenta = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        lbVentaPago = new javax.swing.JLabel();
        Notas = new javax.swing.JDialog();
        rbNotasDia = new javax.swing.JRadioButton();
        rbNotasMes = new javax.swing.JRadioButton();
        rbNotasAno = new javax.swing.JRadioButton();
        btNotasGenerar = new javax.swing.JButton();
        cbNotasMes = new javax.swing.JComboBox<>();
        spNotasAno = new javax.swing.JSpinner();
        spNotasDia = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        tfNotasLimite = new javax.swing.JTextField();
        rgNotasSeleccion = new javax.swing.ButtonGroup();
        Configuracion = new javax.swing.JDialog();
        btConfiguracionGuardar = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        tfConfiguraPorcentaje = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tfConfiguracionDireccionRespaldo = new javax.swing.JTextField();
        btConfiguracionGuardarRespaldo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btConfiguracionGuardarNotas = new javax.swing.JButton();
        tfConfiguracionDireccionNotas = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        tfConfiguracionDireccionReportes = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        AgregaProdutoRapido = new javax.swing.JFrame();
        lbAgregarRapidoCodigo = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        tfAgregarRapidoPrecio = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        tfAgregarRapidoNombre = new javax.swing.JTextField();
        Salidas = new javax.swing.JDialog();
        tfSalidasMotivo = new javax.swing.JTextField();
        tfSalidasMonto = new javax.swing.JTextField();
        btSalidasSalir = new javax.swing.JButton();
        btSalidasAceptar = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        btSalidasGestorSalidas = new javax.swing.JButton();
        GestorSalidas = new javax.swing.JDialog();
        jLabel49 = new javax.swing.JLabel();
        btSalidaBuscar = new javax.swing.JButton();
        cbSalidaMes = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbSalidas = new javax.swing.JTable();
        spSalidaAno = new javax.swing.JSpinner();
        jLabel50 = new javax.swing.JLabel();
        InventarioRapido = new javax.swing.JDialog();
        tfInventarioRCodigoBarras = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        lbInventarioRNombreProducto = new javax.swing.JLabel();
        lbInventarioRCantidadProducto = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        tfInventarioRCantidadAgregar = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        lbInventarioRCantidadProductoTotal = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        btIniciar = new javax.swing.JButton();
        lbPrincipalUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPrincipalVenta = new javax.swing.JTable();
        lbHora = new javax.swing.JLabel();
        lbFecha = new javax.swing.JLabel();
        lbAP = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbPrincipalNoVenta = new javax.swing.JLabel();
        lbPrincipalTotal = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        taPrincipalNotificaiones = new javax.swing.JTextArea();
        lbPrincipalLogo = new javax.swing.JLabel();

        LogIn.setResizable(false);
        LogIn.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImagenLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UsersIcon.png"))); // NOI18N
        LogIn.getContentPane().add(ImagenLogIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 42, -1, -1));

        cbLogIUsuarios.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        cbLogIUsuarios.setPreferredSize(new java.awt.Dimension(80, 30));
        cbLogIUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLogIUsuariosItemStateChanged(evt);
            }
        });
        LogIn.getContentPane().add(cbLogIUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 330, 30));

        pfLogInContraseña.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        pfLogInContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfLogInContraseñaKeyPressed(evt);
            }
        });
        LogIn.getContentPane().add(pfLogInContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 330, 30));

        btLogInIniciar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btLogInIniciar.setText("Iniciar");
        btLogInIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogInIniciarActionPerformed(evt);
            }
        });
        LogIn.getContentPane().add(btLogInIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 140, 60));

        BuscarProducto.setResizable(false);

        tfBusscarProducto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfBusscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusscarProductoKeyReleased(evt);
            }
        });

        tbBuscar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador", "Producto", "Precio", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBuscar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbBuscar.getTableHeader().setReorderingAllowed(false);
        tbBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbBuscarKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbBuscar);
        if (tbBuscar.getColumnModel().getColumnCount() > 0) {
            tbBuscar.getColumnModel().getColumn(0).setResizable(false);
            tbBuscar.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbBuscar.getColumnModel().getColumn(1).setResizable(false);
            tbBuscar.getColumnModel().getColumn(1).setPreferredWidth(365);
            tbBuscar.getColumnModel().getColumn(2).setResizable(false);
            tbBuscar.getColumnModel().getColumn(2).setPreferredWidth(110);
            tbBuscar.getColumnModel().getColumn(3).setResizable(false);
            tbBuscar.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        javax.swing.GroupLayout BuscarProductoLayout = new javax.swing.GroupLayout(BuscarProducto.getContentPane());
        BuscarProducto.getContentPane().setLayout(BuscarProductoLayout);
        BuscarProductoLayout.setHorizontalGroup(
            BuscarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfBusscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        BuscarProductoLayout.setVerticalGroup(
            BuscarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarProductoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(tfBusscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Productos.setResizable(false);
        Productos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador", "Disponibilidad", "Producto", "Precio", "Categoria", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProductos.setToolTipText("");
        tbProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbProductos.getTableHeader().setResizingAllowed(false);
        tbProductos.getTableHeader().setReorderingAllowed(false);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbProductos);
        if (tbProductos.getColumnModel().getColumnCount() > 0) {
            tbProductos.getColumnModel().getColumn(0).setResizable(false);
            tbProductos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbProductos.getColumnModel().getColumn(1).setResizable(false);
            tbProductos.getColumnModel().getColumn(1).setPreferredWidth(76);
            tbProductos.getColumnModel().getColumn(2).setResizable(false);
            tbProductos.getColumnModel().getColumn(2).setPreferredWidth(290);
            tbProductos.getColumnModel().getColumn(3).setResizable(false);
            tbProductos.getColumnModel().getColumn(3).setPreferredWidth(120);
            tbProductos.getColumnModel().getColumn(4).setResizable(false);
            tbProductos.getColumnModel().getColumn(4).setPreferredWidth(138);
            tbProductos.getColumnModel().getColumn(5).setResizable(false);
            tbProductos.getColumnModel().getColumn(5).setPreferredWidth(108);
        }

        Productos.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 79, 880, 523));

        tfProductosBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfProductosBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfProductosBuscarKeyReleased(evt);
            }
        });
        Productos.getContentPane().add(tfProductosBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 44, 453, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel1.setText("Codigo de barras o identificador (Agranel):");
        Productos.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, -1, -1));

        tfProductoCodigoBarras.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Productos.getContentPane().add(tfProductoCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 330, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel2.setText("Producto:");
        Productos.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, -1, -1));

        tfProductoNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Productos.getContentPane().add(tfProductoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, 180, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel3.setText("Precio:");
        Productos.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, -1, 20));

        tfProductoPrecio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Productos.getContentPane().add(tfProductoPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 140, 160, -1));

        cbProductoCategoria.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Productos.getContentPane().add(cbProductoCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 340, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel4.setText("Categoria:");
        Productos.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 180, -1, -1));

        spProductosStock.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        spProductosStock.setModel(new javax.swing.SpinnerNumberModel(0, null, null, 3));
        Productos.getContentPane().add(spProductosStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1036, 280, 102, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel5.setText("Stock Minimo:");
        Productos.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, -1, -1));

        btProductoAgregar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProductoAgregar.setText("Agregar Producto");
        btProductoAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoAgregarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductoAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 430, 170, 60));

        btProductoEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProductoEliminar.setText("Eliminar Producto");
        btProductoEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoEliminarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 430, 170, 60));

        btProductoModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProductoModificar.setText("Modificar Producto");
        btProductoModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoModificarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductoModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 500, 170, 60));

        btProductoCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btProductoCancelar.setText("Cancelar Selección");
        btProductoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoCancelarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 500, 170, 60));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel6.setText("Productos Disponibles:");
        Productos.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, -1, -1));

        spProductosProductosDisponibles.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        spProductosProductosDisponibles.setModel(new javax.swing.SpinnerNumberModel());
        Productos.getContentPane().add(spProductosProductosDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 240, 125, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel7.setText("Buscar");
        Productos.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 46, -1, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel8.setText("$");
        Productos.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 130, -1, 40));

        jLabel13.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel13.setText("Fecha caducidad:");
        Productos.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(919, 338, -1, -1));

        tfProductoDia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfProductoDia.setText("00");
        tfProductoDia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfProductoDiaKeyReleased(evt);
            }
        });
        Productos.getContentPane().add(tfProductoDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(961, 364, 39, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel14.setText("/");
        Productos.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1018, 368, 12, -1));

        tfProductoMes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfProductoMes.setText("00");
        Productos.getContentPane().add(tfProductoMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1048, 364, 41, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel15.setText("/");
        Productos.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1121, 368, 12, -1));

        tfProductoAno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tfProductoAno.setText("0000");
        Productos.getContentPane().add(tfProductoAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1145, 364, 44, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel16.setText("dia (2 digitos) / mes (2 digitos) / año (4 digitos)");
        Productos.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 399, -1, -1));

        btProductoHabilitar.setText("Habilitar");
        btProductoHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoHabilitarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductoHabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1038, 334, 100, -1));

        btProductosDeshabilitar.setText("Deshabilitar");
        btProductosDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductosDeshabilitarActionPerformed(evt);
            }
        });
        Productos.getContentPane().add(btProductosDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 334, 100, -1));

        FinalizaCompra.setResizable(false);

        lbfinalizarCompraTotal.setFont(new java.awt.Font("Gill Sans MT", 1, 80)); // NOI18N
        lbfinalizarCompraTotal.setText("$0000.00");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel10.setText("Total:");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel11.setText("Pago:");

        tfFinalizarPago.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        tfFinalizarPago.setText("CantidadDePago");

        lbfinalizarCambio.setFont(new java.awt.Font("Gill Sans MT", 1, 80)); // NOI18N
        lbfinalizarCambio.setText("$0000.00");

        btFinalizarTarjeta.setText("Tarjeta");
        btFinalizarTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btFinalizarTarjetaKeyReleased(evt);
            }
        });

        btFinalizarEfectivo.setText("Efectivo");
        btFinalizarEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btFinalizarEfectivoKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel12.setText("Cambio:");

        lbFinalizarTicket.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
        lbFinalizarTicket.setText("NonSinTiket");

        javax.swing.GroupLayout FinalizaCompraLayout = new javax.swing.GroupLayout(FinalizaCompra.getContentPane());
        FinalizaCompra.getContentPane().setLayout(FinalizaCompraLayout);
        FinalizaCompraLayout.setHorizontalGroup(
            FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalizaCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinalizaCompraLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FinalizaCompraLayout.createSequentialGroup()
                        .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbfinalizarCambio)
                            .addGroup(FinalizaCompraLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFinalizarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbfinalizarCompraTotal))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FinalizaCompraLayout.createSequentialGroup()
                .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FinalizaCompraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FinalizaCompraLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FinalizaCompraLayout.createSequentialGroup()
                                .addComponent(lbFinalizarTicket)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(FinalizaCompraLayout.createSequentialGroup()
                                .addComponent(btFinalizarEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(btFinalizarTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        FinalizaCompraLayout.setVerticalGroup(
            FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalizaCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lbfinalizarCompraTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfFinalizarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FinalizaCompraLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lbfinalizarCambio))
                    .addGroup(FinalizaCompraLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(FinalizaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btFinalizarEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFinalizarTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbFinalizarTicket)
                .addGap(10, 10, 10))
        );

        Usuarios.setResizable(false);
        Usuarios.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbUsuarios);

        Usuarios.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 150, 469));

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel17.setText("Nombre de usuario:");
        Usuarios.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 20, -1, -1));

        tfUsuarioNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfUsuarioNombreUsuarioKeyReleased(evt);
            }
        });
        Usuarios.getContentPane().add(tfUsuarioNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 42, 190, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel18.setText("Contraseña:");
        Usuarios.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 79, -1, -1));
        Usuarios.getContentPane().add(tfUsuarioContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 101, 190, -1));

        jLabel19.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel19.setText("Nombre de empleado:");
        Usuarios.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 138, -1, -1));
        Usuarios.getContentPane().add(tfUsuarioNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 160, 190, -1));

        jLabel20.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel20.setText("Datos de empleado:*");
        Usuarios.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 197, -1, -1));

        taUsuarioDatosEmpleado.setColumns(20);
        taUsuarioDatosEmpleado.setRows(5);
        jScrollPane6.setViewportView(taUsuarioDatosEmpleado);

        Usuarios.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 219, 190, 100));

        btUsuarioNuevo.setText("Agregar");
        btUsuarioNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuarioNuevoActionPerformed(evt);
            }
        });
        Usuarios.getContentPane().add(btUsuarioNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 331, 190, -1));

        btUsuarioModificar.setText("Modificar");
        btUsuarioModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuarioModificarActionPerformed(evt);
            }
        });
        Usuarios.getContentPane().add(btUsuarioModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 367, 190, -1));

        btUsuarioEliminar.setText("Eliminar");
        btUsuarioEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuarioEliminarActionPerformed(evt);
            }
        });
        Usuarios.getContentPane().add(btUsuarioEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 403, 190, -1));

        btUsuarioCancelarSeleccion.setText("Cancelar Seleccion");
        btUsuarioCancelarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuarioCancelarSeleccionActionPerformed(evt);
            }
        });
        Usuarios.getContentPane().add(btUsuarioCancelarSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 439, 190, -1));

        ValidaAdministrador.setUndecorated(true);
        ValidaAdministrador.setResizable(false);
        ValidaAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Lock.png"))); // NOI18N
        ValidaAdministrador.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 36, -1, -1));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setText("Ingrese contraseña de administrador para acceder");
        ValidaAdministrador.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        tfValidaAdministradorPass.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfValidaAdministradorPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfValidaAdministradorPassKeyReleased(evt);
            }
        });
        ValidaAdministrador.getContentPane().add(tfValidaAdministradorPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 93, 201, -1));

        lbValidaAdministradorModulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbValidaAdministradorModulo.setText("Modulo");
        ValidaAdministrador.getContentPane().add(lbValidaAdministradorModulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 43, -1, -1));

        btValidaAdministradorSalir.setText("Cancelar");
        btValidaAdministradorSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValidaAdministradorSalirActionPerformed(evt);
            }
        });
        ValidaAdministrador.getContentPane().add(btValidaAdministradorSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 139, -1, -1));

        btValidaAdministradorAcceder.setText("Acceder");
        btValidaAdministradorAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValidaAdministradorAccederActionPerformed(evt);
            }
        });
        ValidaAdministrador.getContentPane().add(btValidaAdministradorAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 139, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoLock.png"))); // NOI18N
        ValidaAdministrador.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Ventas.setResizable(false);
        Ventas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel23.setText("Ventas del dia:");
        Ventas.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        spVentasDia.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        Ventas.getContentPane().add(spVentasDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 38, 50, 40));

        jLabel25.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel25.setText("de");
        Ventas.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        cbVentasMes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbVentasMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));
        Ventas.getContentPane().add(cbVentasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 45, 150, -1));

        jLabel26.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel26.setText("del 20");
        Ventas.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, 35));

        spVentasAno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Ventas.getContentPane().add(spVentasAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 38, 50, 40));

        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Venta", "Total", "Hora", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVentasMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbVentas);
        if (tbVentas.getColumnModel().getColumnCount() > 0) {
            tbVentas.getColumnModel().getColumn(0).setResizable(false);
            tbVentas.getColumnModel().getColumn(1).setResizable(false);
            tbVentas.getColumnModel().getColumn(2).setResizable(false);
            tbVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        Ventas.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 338, 470));

        btventaBuscar.setText("Buscar ventas en fecha");
        btventaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btventaBuscarActionPerformed(evt);
            }
        });
        Ventas.getContentPane().add(btventaBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 338, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Ventas.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 12, -1, 630));

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setText("Venta No°: ");
        Ventas.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 12, -1, -1));

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setText("Total: $ ");
        Ventas.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 43, -1, -1));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setText("Atendio: ");
        Ventas.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 74, -1, -1));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setText("Fecha: ");
        Ventas.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 105, -1, -1));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setText("Hora:");
        Ventas.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(661, 105, -1, -1));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setText("Cambio:");
        Ventas.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 74, -1, -1));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setText("Status de venta:");
        Ventas.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 12, -1, -1));

        lbVentaId.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaId.setText("0000000");
        Ventas.getContentPane().add(lbVentaId, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 12, 144, -1));

        lbVentaTotal.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaTotal.setText("00000000");
        Ventas.getContentPane().add(lbVentaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 43, -1, -1));

        lbVentaEmpleado.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaEmpleado.setText("Empleado");
        Ventas.getContentPane().add(lbVentaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 74, -1, -1));

        lbVentaFecha.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaFecha.setText("09 de PosSabraDios del 2020");
        Ventas.getContentPane().add(lbVentaFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 105, -1, -1));

        lbVentaHora.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaHora.setText("00:00:00 AP");
        Ventas.getContentPane().add(lbVentaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 105, -1, -1));

        lbVentaStatus.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaStatus.setText("StatusVenta");
        Ventas.getContentPane().add(lbVentaStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 12, -1, -1));

        lbVentaCambio.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaCambio.setText("Cambio");
        Ventas.getContentPane().add(lbVentaCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 74, -1, -1));

        tbVentaContenido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Producto", "Precio", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tbVentaContenido);
        if (tbVentaContenido.getColumnModel().getColumnCount() > 0) {
            tbVentaContenido.getColumnModel().getColumn(0).setResizable(false);
            tbVentaContenido.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbVentaContenido.getColumnModel().getColumn(1).setResizable(false);
            tbVentaContenido.getColumnModel().getColumn(1).setPreferredWidth(300);
            tbVentaContenido.getColumnModel().getColumn(2).setResizable(false);
            tbVentaContenido.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbVentaContenido.getColumnModel().getColumn(3).setResizable(false);
            tbVentaContenido.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        Ventas.getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 172, 530, 470));

        btVentaCancelarVenta.setText("Cancela Venta");
        btVentaCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVentaCancelarVentaActionPerformed(evt);
            }
        });
        Ventas.getContentPane().add(btVentaCancelarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 136, -1, -1));

        btVentaImprimirTicket.setText("Imprimir Ticket");
        btVentaImprimirTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVentaImprimirTicketActionPerformed(evt);
            }
        });
        Ventas.getContentPane().add(btVentaImprimirTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 136, -1, -1));

        btventaBuscar1.setText("Motrar todas las ventas");
        btventaBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btventaBuscar1ActionPerformed(evt);
            }
        });
        Ventas.getContentPane().add(btventaBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 338, -1));

        btVentaReanudarVenta.setText("Reanudar Venta");
        btVentaReanudarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVentaReanudarVentaActionPerformed(evt);
            }
        });
        Ventas.getContentPane().add(btVentaReanudarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 136, -1, -1));

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel42.setText("Forma de pago: ");
        Ventas.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 43, -1, -1));

        lbVentaPago.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        lbVentaPago.setText("FormaPago");
        Ventas.getContentPane().add(lbVentaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 43, -1, -1));

        Notas.setResizable(false);
        Notas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbNotasDia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rbNotasDia.setText("Dia");
        rbNotasDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNotasDiaMouseClicked(evt);
            }
        });
        Notas.getContentPane().add(rbNotasDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 21, -1, -1));

        rbNotasMes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rbNotasMes.setText("Mes");
        rbNotasMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNotasMesMouseClicked(evt);
            }
        });
        Notas.getContentPane().add(rbNotasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 21, -1, -1));

        rbNotasAno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rbNotasAno.setText("Año");
        rbNotasAno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNotasAnoMouseClicked(evt);
            }
        });
        Notas.getContentPane().add(rbNotasAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 21, -1, -1));

        btNotasGenerar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btNotasGenerar.setText("Generar Nota");
        btNotasGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotasGenerarActionPerformed(evt);
            }
        });
        Notas.getContentPane().add(btNotasGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 150, 64));

        cbNotasMes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbNotasMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));
        Notas.getContentPane().add(cbNotasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 55, 101, -1));

        spNotasAno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Notas.getContentPane().add(spNotasAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 58, 50, -1));

        spNotasDia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Notas.getContentPane().add(spNotasDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 55, 60, -1));

        jLabel34.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel34.setText("de");
        Notas.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jLabel35.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel35.setText("del 20");
        Notas.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 60, -1, -1));

        jLabel36.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel36.setText("Limite de importe total:");
        Notas.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 125, -1, -1));

        tfNotasLimite.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Notas.getContentPane().add(tfNotasLimite, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 123, 150, -1));

        Configuracion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btConfiguracionGuardar.setText("Guardar");
        btConfiguracionGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfiguracionGuardarActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(btConfiguracionGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jLabel37.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel37.setText("Comisión uso de tarjeta (%)");
        Configuracion.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 28, -1, -1));
        Configuracion.getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 37, 510, 10));

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel38.setText("Almacenamiento de reportes de usuario");
        Configuracion.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));
        Configuracion.getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 430, 11));

        tfConfiguraPorcentaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Configuracion.getContentPane().add(tfConfiguraPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 65, 81, -1));

        jLabel39.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel39.setText("%");
        Configuracion.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 67, 26, -1));

        tfConfiguracionDireccionRespaldo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Configuracion.getContentPane().add(tfConfiguracionDireccionRespaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 156, 571, -1));

        btConfiguracionGuardarRespaldo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btConfiguracionGuardarRespaldo.setText("Guardar en...");
        btConfiguracionGuardarRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfiguracionGuardarRespaldoActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(btConfiguracionGuardarRespaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 153, -1, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton1.setText("Generar respaldo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 200, -1, -1));

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel40.setText("Respaldo y restauracion de base de datos");
        Configuracion.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        Configuracion.getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 430, 10));

        btConfiguracionGuardarNotas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btConfiguracionGuardarNotas.setText("Guardar en...");
        btConfiguracionGuardarNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfiguracionGuardarNotasActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(btConfiguracionGuardarNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 292, -1, -1));

        tfConfiguracionDireccionNotas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Configuracion.getContentPane().add(tfConfiguracionDireccionNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 292, 571, -1));

        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setText("Restaurar base de datos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 200, 237, -1));

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel41.setText("Almacenamiento de notas");
        Configuracion.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 255, -1, -1));
        Configuracion.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 264, 520, 10));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton3.setText("Guardar en ...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        tfConfiguracionDireccionReportes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Configuracion.getContentPane().add(tfConfiguracionDireccionReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 550, 30));

        jButton7.setText("No tocar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Configuracion.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        AgregaProdutoRapido.setResizable(false);

        lbAgregarRapidoCodigo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbAgregarRapidoCodigo.setText("00000000000000000");

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel44.setText("ERROR: PRODUCTO NO ENCONTRADO                               PARA SALIR PRECIONE  \"ESC\"");

        jLabel43.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel43.setText("PUEDE AGREGARLO RAPIDAMENTE");

        tfAgregarRapidoPrecio.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tfAgregarRapidoPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAgregarRapidoPrecioKeyReleased(evt);
            }
        });

        jLabel45.setText("Nombre del producto:");

        jLabel46.setText("Precio:");

        jButton4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton4.setText("Agregar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tfAgregarRapidoNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tfAgregarRapidoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAgregarRapidoNombreKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout AgregaProdutoRapidoLayout = new javax.swing.GroupLayout(AgregaProdutoRapido.getContentPane());
        AgregaProdutoRapido.getContentPane().setLayout(AgregaProdutoRapidoLayout);
        AgregaProdutoRapidoLayout.setHorizontalGroup(
            AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgregaProdutoRapidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgregaProdutoRapidoLayout.createSequentialGroup()
                        .addComponent(lbAgregarRapidoCodigo)
                        .addGap(293, 293, 293))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AgregaProdutoRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(213, 213, 213))))
            .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
            .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                        .addComponent(tfAgregarRapidoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfAgregarRapidoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addContainerGap(47, Short.MAX_VALUE))))
        );
        AgregaProdutoRapidoLayout.setVerticalGroup(
            AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AgregaProdutoRapidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addGap(5, 5, 5)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAgregarRapidoCodigo)
                .addGap(8, 8, 8)
                .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAgregarRapidoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAgregarRapidoNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AgregaProdutoRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        tfSalidasMotivo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfSalidasMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSalidasMotivoKeyReleased(evt);
            }
        });

        tfSalidasMonto.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfSalidasMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSalidasMontoKeyReleased(evt);
            }
        });

        btSalidasSalir.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btSalidasSalir.setText("Salir");
        btSalidasSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalidasSalirActionPerformed(evt);
            }
        });

        btSalidasAceptar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btSalidasAceptar.setText("Aceptar");
        btSalidasAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalidasAceptarActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel47.setText("Descripcion de salida");

        jLabel48.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel48.setText("Monto");

        btSalidasGestorSalidas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btSalidasGestorSalidas.setText("Gestor Salidas");
        btSalidasGestorSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalidasGestorSalidasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SalidasLayout = new javax.swing.GroupLayout(Salidas.getContentPane());
        Salidas.getContentPane().setLayout(SalidasLayout);
        SalidasLayout.setHorizontalGroup(
            SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalidasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SalidasLayout.createSequentialGroup()
                        .addComponent(btSalidasGestorSalidas)
                        .addGap(34, 34, 34)
                        .addComponent(btSalidasSalir))
                    .addComponent(tfSalidasMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalidasLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btSalidasAceptar))
                    .addGroup(SalidasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(tfSalidasMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(SalidasLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(102, 102, 102))
        );
        SalidasLayout.setVerticalGroup(
            SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalidasLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSalidasMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSalidasMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalidasSalir)
                    .addComponent(btSalidasAceptar)
                    .addComponent(btSalidasGestorSalidas))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        GestorSalidas.setResizable(false);

        jLabel49.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel49.setText("Mostrar salidas de fecha:");

        btSalidaBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btSalidaBuscar.setText("Buscar");
        btSalidaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalidaBuscarActionPerformed(evt);
            }
        });

        cbSalidaMes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbSalidaMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));

        jLabel51.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel51.setText("/");

        jLabel53.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
        jLabel53.setText("Mes");

        jLabel54.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
        jLabel54.setText("Año");

        tbSalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Fecha", "Descripción", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbSalidas);
        if (tbSalidas.getColumnModel().getColumnCount() > 0) {
            tbSalidas.getColumnModel().getColumn(0).setResizable(false);
            tbSalidas.getColumnModel().getColumn(0).setPreferredWidth(85);
            tbSalidas.getColumnModel().getColumn(1).setResizable(false);
            tbSalidas.getColumnModel().getColumn(1).setPreferredWidth(170);
            tbSalidas.getColumnModel().getColumn(2).setResizable(false);
            tbSalidas.getColumnModel().getColumn(2).setPreferredWidth(170);
            tbSalidas.getColumnModel().getColumn(3).setResizable(false);
            tbSalidas.getColumnModel().getColumn(3).setPreferredWidth(85);
        }

        spSalidaAno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel50.setText("20");

        javax.swing.GroupLayout GestorSalidasLayout = new javax.swing.GroupLayout(GestorSalidas.getContentPane());
        GestorSalidas.getContentPane().setLayout(GestorSalidasLayout);
        GestorSalidasLayout.setHorizontalGroup(
            GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GestorSalidasLayout.createSequentialGroup()
                .addGroup(GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GestorSalidasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(GestorSalidasLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(18, 18, 18))
                            .addComponent(jLabel49))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel54))
                    .addGroup(GestorSalidasLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(cbSalidaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel50)
                        .addGap(5, 5, 5)
                        .addComponent(spSalidaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalidaBuscar)
                .addGap(29, 29, 29))
            .addGroup(GestorSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        GestorSalidasLayout.setVerticalGroup(
            GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GestorSalidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(9, 9, 9)
                .addGroup(GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GestorSalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalidaBuscar)
                    .addComponent(cbSalidaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(spSalidaAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        InventarioRapido.setResizable(false);

        tfInventarioRCodigoBarras.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        tfInventarioRCodigoBarras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfInventarioRCodigoBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfInventarioRCodigoBarrasKeyReleased(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel52.setText("Dispone de:");

        lbInventarioRNombreProducto.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lbInventarioRNombreProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInventarioRNombreProducto.setText("Nombre del Producto");

        lbInventarioRCantidadProducto.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lbInventarioRCantidadProducto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbInventarioRCantidadProducto.setText("0");

        jLabel56.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel56.setText(" Producto");

        jLabel55.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel55.setText("Piezas/Unidades");

        jLabel57.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel57.setText("Cantidad a agregar:");

        tfInventarioRCantidadAgregar.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        tfInventarioRCantidadAgregar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfInventarioRCantidadAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfInventarioRCantidadAgregarKeyReleased(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel58.setText("Cantidad total:");

        lbInventarioRCantidadProductoTotal.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        lbInventarioRCantidadProductoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbInventarioRCantidadProductoTotal.setText("0");

        jLabel59.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel59.setText("Piezas/Unidades");

        jButton6.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jButton6.setText("Agregar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabel60.setText("Piezas/Unidades");

        javax.swing.GroupLayout InventarioRapidoLayout = new javax.swing.GroupLayout(InventarioRapido.getContentPane());
        InventarioRapido.getContentPane().setLayout(InventarioRapidoLayout);
        InventarioRapidoLayout.setHorizontalGroup(
            InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventarioRapidoLayout.createSequentialGroup()
                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InventarioRapidoLayout.createSequentialGroup()
                        .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InventarioRapidoLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(tfInventarioRCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InventarioRapidoLayout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(jLabel56))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InventarioRapidoLayout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(lbInventarioRNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventarioRapidoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventarioRapidoLayout.createSequentialGroup()
                                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventarioRapidoLayout.createSequentialGroup()
                                            .addComponent(tfInventarioRCantidadAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel59))
                                        .addGroup(InventarioRapidoLayout.createSequentialGroup()
                                            .addComponent(lbInventarioRCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel55)))
                                    .addGroup(InventarioRapidoLayout.createSequentialGroup()
                                        .addComponent(lbInventarioRCantidadProductoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel60))))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(28, 28, 28))
            .addGroup(InventarioRapidoLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InventarioRapidoLayout.setVerticalGroup(
            InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventarioRapidoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(tfInventarioRCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbInventarioRNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInventarioRCantidadProducto)
                    .addComponent(jLabel55)
                    .addComponent(jLabel52))
                .addGap(22, 22, 22)
                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(tfInventarioRCantidadAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(25, 25, 25)
                .addGroup(InventarioRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(lbInventarioRCantidadProductoTotal)
                    .addComponent(jLabel60))
                .addGap(31, 31, 31)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btIniciar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btIniciar.setText("Iniciar");
        btIniciar.setPreferredSize(new java.awt.Dimension(62, 25));
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 60));

        lbPrincipalUsuario.setFont(new java.awt.Font("Eras Bold ITC", 1, 18)); // NOI18N
        lbPrincipalUsuario.setForeground(new java.awt.Color(0, 0, 0));
        lbPrincipalUsuario.setText("Iniciar Sesion");
        getContentPane().add(lbPrincipalUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        tbPrincipalVenta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tbPrincipalVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripcion Producto", "Precio Unitario", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPrincipalVenta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPrincipalVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbPrincipalVenta.setRowHeight(25);
        tbPrincipalVenta.setRowMargin(2);
        tbPrincipalVenta.setShowHorizontalLines(false);
        tbPrincipalVenta.setShowVerticalLines(false);
        tbPrincipalVenta.getTableHeader().setReorderingAllowed(false);
        tbPrincipalVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPrincipalVentaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbPrincipalVenta);
        if (tbPrincipalVenta.getColumnModel().getColumnCount() > 0) {
            tbPrincipalVenta.getColumnModel().getColumn(0).setResizable(false);
            tbPrincipalVenta.getColumnModel().getColumn(0).setPreferredWidth(60);
            tbPrincipalVenta.getColumnModel().getColumn(1).setResizable(false);
            tbPrincipalVenta.getColumnModel().getColumn(1).setPreferredWidth(550);
            tbPrincipalVenta.getColumnModel().getColumn(2).setResizable(false);
            tbPrincipalVenta.getColumnModel().getColumn(2).setPreferredWidth(105);
            tbPrincipalVenta.getColumnModel().getColumn(3).setResizable(false);
            tbPrincipalVenta.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 871, 900));

        lbHora.setFont(new java.awt.Font("Elephant", 1, 48)); // NOI18N
        lbHora.setText("HO:RA SS");
        getContentPane().add(lbHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        lbFecha.setFont(new java.awt.Font("Elephant", 3, 18)); // NOI18N
        lbFecha.setText("00 de mescompl de 0000");
        getContentPane().add(lbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 260, -1));

        lbAP.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        lbAP.setText("AP");
        getContentPane().add(lbAP, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Venta No°:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        lbPrincipalNoVenta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbPrincipalNoVenta.setText("000000");
        getContentPane().add(lbPrincipalNoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        lbPrincipalTotal.setFont(new java.awt.Font("Gill Sans MT", 1, 80)); // NOI18N
        lbPrincipalTotal.setText("$0000.00");
        getContentPane().add(lbPrincipalTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 420, 100));

        taPrincipalNotificaiones.setColumns(20);
        taPrincipalNotificaiones.setRows(5);
        jScrollPane4.setViewportView(taPrincipalNotificaiones);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 410, 790));

        lbPrincipalLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo8cm.png"))); // NOI18N
        getContentPane().add(lbPrincipalLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, -20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarActionPerformed
        LogInAvilitaSistema(btIniciar.getText().toString());
    }//GEN-LAST:event_btIniciarActionPerformed

    private void btLogInIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogInIniciarActionPerformed
        ValidaLogIn();
    }//GEN-LAST:event_btLogInIniciarActionPerformed

    private void cbLogIUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLogIUsuariosItemStateChanged
        pfLogInContraseña.setText("");
        pfLogInContraseña.grabFocus();
    }//GEN-LAST:event_cbLogIUsuariosItemStateChanged

    private void pfLogInContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfLogInContraseñaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            ValidaLogIn();
        }
    }//GEN-LAST:event_pfLogInContraseñaKeyPressed

    private void tbPrincipalVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPrincipalVentaKeyReleased
        PrincipalTablaVenta(evt);
        //ValidaInicio(evt);
    }//GEN-LAST:event_tbPrincipalVentaKeyReleased

    private void tfBusscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusscarProductoKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            BuscarProducto.dispose();
        }else if(evt.getKeyCode()== 40){
            tbBuscar.grabFocus();
        }
        else{
            BuscarProducto(tfBusscarProducto.getText());
        }
    }//GEN-LAST:event_tfBusscarProductoKeyReleased

    private void tbBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBuscarKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int fila = tbBuscar.getSelectedRow();
            CargarProductoVenta(fila-1);
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            BuscarProducto.dispose();
        }
    }//GEN-LAST:event_tbBuscarKeyReleased

    private void btProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoAgregarActionPerformed
        AgregarProducto();
    }//GEN-LAST:event_btProductoAgregarActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        if(evt.getButton() == 1){
            ProductoSeleccionTabla(tbProductos.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btProductoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoCancelarActionPerformed
        ProductoCancelarSeleccion();
    }//GEN-LAST:event_btProductoCancelarActionPerformed

    private void btProductoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoEliminarActionPerformed
        ProductosEliminar();
    }//GEN-LAST:event_btProductoEliminarActionPerformed

    private void btProductoModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoModificarActionPerformed
        try {
            ProductoModificar();
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btProductoModificarActionPerformed

    private void btFinalizarEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btFinalizarEfectivoKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            FinalizarVentaFinaliza("EFECTIVO");
        }else{
            FinalizarCambio(evt);
        }
    }//GEN-LAST:event_btFinalizarEfectivoKeyReleased

    private void btFinalizarTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btFinalizarTarjetaKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            FinalizarVentaFinaliza("TARJETA");
        }else{
            FinalizarCambio(evt);
        }
    }//GEN-LAST:event_btFinalizarTarjetaKeyReleased

    private void tfProductoDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfProductoDiaKeyReleased
    }//GEN-LAST:event_tfProductoDiaKeyReleased

    private void tfProductosBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfProductosBuscarKeyReleased
        ProductosBuscarVentanaProductos(tfProductosBuscar.getText());
    }//GEN-LAST:event_tfProductosBuscarKeyReleased

    private void btProductoHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoHabilitarActionPerformed
        tfProductoDia.setEnabled(Boolean.TRUE);
        tfProductoMes.setEnabled(Boolean.TRUE);
        tfProductoAno.setEnabled(Boolean.TRUE);
        
        tfProductoDia.setText(Dia);
        tfProductoMes.setText(Mes);
        tfProductoAno.setText(Ano);
    }//GEN-LAST:event_btProductoHabilitarActionPerformed

    private void btProductosDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductosDeshabilitarActionPerformed
        tfProductoDia.setEnabled(Boolean.FALSE);
        tfProductoMes.setEnabled(Boolean.FALSE);
        tfProductoAno.setEnabled(Boolean.FALSE);
        
        tfProductoDia.setText("00");
        tfProductoMes.setText("00");
        tfProductoAno.setText("0000");
    }//GEN-LAST:event_btProductosDeshabilitarActionPerformed

    private void tfUsuarioNombreUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfUsuarioNombreUsuarioKeyReleased
        UsuariosSoloLetrasMayusculas(evt);
        tfUsuarioNombreUsuario.setText(tfUsuarioNombreUsuario.getText().replace(" ", ""));
    }//GEN-LAST:event_tfUsuarioNombreUsuarioKeyReleased

    private void btUsuarioNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuarioNuevoActionPerformed
        UsuariosNuevoUsuario();
    }//GEN-LAST:event_btUsuarioNuevoActionPerformed

    private void btUsuarioCancelarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuarioCancelarSeleccionActionPerformed
        UsuarioCancelaSelaccion();
    }//GEN-LAST:event_btUsuarioCancelarSeleccionActionPerformed

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        if(evt.getButton() == 1){
            UsuarioSeleccionTabla(tbUsuarios.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void btUsuarioEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuarioEliminarActionPerformed
        UsuarioEliminar();
    }//GEN-LAST:event_btUsuarioEliminarActionPerformed

    private void btUsuarioModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuarioModificarActionPerformed
        UsuarioModificar();
    }//GEN-LAST:event_btUsuarioModificarActionPerformed

    private void btValidaAdministradorSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValidaAdministradorSalirActionPerformed
        ValidaAdministradorSalida();
    }//GEN-LAST:event_btValidaAdministradorSalirActionPerformed

    private void tfValidaAdministradorPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValidaAdministradorPassKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            ValidaAdministradorSalida();
        }
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            ValidaAdministradorAcceder();
        }
    }//GEN-LAST:event_tfValidaAdministradorPassKeyReleased

    private void btValidaAdministradorAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValidaAdministradorAccederActionPerformed
        ValidaAdministradorAcceder();
    }//GEN-LAST:event_btValidaAdministradorAccederActionPerformed

    private void btventaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btventaBuscarActionPerformed
        VentaFormatoFecha();
    }//GEN-LAST:event_btventaBuscarActionPerformed

    private void tbVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentasMouseClicked
        if(evt.getButton() == 1){
            VentaSeleccionTabla(tbVentas.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tbVentasMouseClicked

    private void btVentaCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVentaCancelarVentaActionPerformed
        int Respuesta = JOptionPane.showConfirmDialog(null, "Una vez cancelada la venta no se podrá ser considerada nuevamente como FINALIZADA  \n Confirme Eliminar \n", "Cancelar ",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(Respuesta == 0){
            try {
                if(lbVentaStatus.getText().equals("PAUSA")){
                    VentaCancelaPausa();
                }else{
                    VentaCancelarVenta();
                }
                ActualizaNotificacion();
            } catch (ParseException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btVentaCancelarVentaActionPerformed

    private void rbNotasDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNotasDiaMouseClicked
        NotasTipoConfiguracion();
    }//GEN-LAST:event_rbNotasDiaMouseClicked

    private void rbNotasMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNotasMesMouseClicked
        NotasTipoConfiguracion();
    }//GEN-LAST:event_rbNotasMesMouseClicked

    private void rbNotasAnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNotasAnoMouseClicked
        NotasTipoConfiguracion();
    }//GEN-LAST:event_rbNotasAnoMouseClicked

    private void btventaBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btventaBuscar1ActionPerformed
        LimpiarTablaVenta();
        BaseDeDatos.VentaLlenarTablaTodos(tbVentas);
    }//GEN-LAST:event_btventaBuscar1ActionPerformed

    private void btVentaImprimirTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVentaImprimirTicketActionPerformed
        BaseDeDatos.PreparaTicket(lbVentaId.getText());
    }//GEN-LAST:event_btVentaImprimirTicketActionPerformed

    private void btVentaReanudarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVentaReanudarVentaActionPerformed
        VentaReanudaVenta();
    }//GEN-LAST:event_btVentaReanudarVentaActionPerformed

    private void btNotasGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotasGenerarActionPerformed
        NotasGenerar();
    }//GEN-LAST:event_btNotasGenerarActionPerformed

    private void btConfiguracionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfiguracionGuardarActionPerformed
        ConfiguracionValidaPorcenaje(tfConfiguraPorcentaje.getText());
    }//GEN-LAST:event_btConfiguracionGuardarActionPerformed

    private void btConfiguracionGuardarRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfiguracionGuardarRespaldoActionPerformed
        ConfiguracionDireccionRespaldo();
    }//GEN-LAST:event_btConfiguracionGuardarRespaldoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ConfiguracionGenerarRespaldo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btConfiguracionGuardarNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfiguracionGuardarNotasActionPerformed
        ConfiguracionDireccionNotas();
    }//GEN-LAST:event_btConfiguracionGuardarNotasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ConfiguracionRestauraDB();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ConfiguracionDireccionReportes();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tfAgregarRapidoPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAgregarRapidoPrecioKeyReleased
        AgregarRapidoPrecio(evt);
    }//GEN-LAST:event_tfAgregarRapidoPrecioKeyReleased

    private void tfAgregarRapidoNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAgregarRapidoNombreKeyReleased
        AgregarRapidoNombre(evt);
    }//GEN-LAST:event_tfAgregarRapidoNombreKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AgregaProdutoRapido.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AgregarRapidoAgrega();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tfSalidasMotivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSalidasMotivoKeyReleased
        SalidaListenMotivo(evt);
    }//GEN-LAST:event_tfSalidasMotivoKeyReleased

    private void tfSalidasMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSalidasMontoKeyReleased
        SalidaListenMonto(evt);
    }//GEN-LAST:event_tfSalidasMontoKeyReleased

    private void btSalidasSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalidasSalirActionPerformed
        Salidas.dispose();
    }//GEN-LAST:event_btSalidasSalirActionPerformed

    private void btSalidasAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalidasAceptarActionPerformed
        SalidaFinaliza();
    }//GEN-LAST:event_btSalidasAceptarActionPerformed

    private void btSalidasGestorSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalidasGestorSalidasActionPerformed
        ValidaAdministradorVentana("SALIDAS");
    }//GEN-LAST:event_btSalidasGestorSalidasActionPerformed

    private void btSalidaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalidaBuscarActionPerformed
         SalidasBuscarSalidasFechas();
    }//GEN-LAST:event_btSalidaBuscarActionPerformed

    private void tfInventarioRCodigoBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfInventarioRCodigoBarrasKeyReleased
        InventarioRapido(evt);
    }//GEN-LAST:event_tfInventarioRCodigoBarrasKeyReleased

    private void tfInventarioRCantidadAgregarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfInventarioRCantidadAgregarKeyReleased
        int TeclaPrecionada = evt.getKeyCode();
        if(TeclaPrecionada == 10){
            InventarioRAgregarCantidad();
        }
        InventarioRapidoCantidad();
    }//GEN-LAST:event_tfInventarioRCantidadAgregarKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        InventarioRapidoCantidad();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        AgergarMasiba();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PrintException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame AgregaProdutoRapido;
    private javax.swing.JDialog BuscarProducto;
    private javax.swing.JDialog Configuracion;
    private javax.swing.JDialog FinalizaCompra;
    private javax.swing.JDialog GestorSalidas;
    private javax.swing.JLabel ImagenLogIn;
    private javax.swing.JDialog InventarioRapido;
    private javax.swing.JDialog LogIn;
    private javax.swing.JDialog Notas;
    private javax.swing.JDialog Productos;
    private javax.swing.JDialog Salidas;
    private javax.swing.JDialog Usuarios;
    private javax.swing.JDialog ValidaAdministrador;
    private javax.swing.JDialog Ventas;
    private javax.swing.JButton btConfiguracionGuardar;
    private javax.swing.JButton btConfiguracionGuardarNotas;
    private javax.swing.JButton btConfiguracionGuardarRespaldo;
    private javax.swing.JButton btFinalizarEfectivo;
    private javax.swing.JButton btFinalizarTarjeta;
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btLogInIniciar;
    private javax.swing.JButton btNotasGenerar;
    private javax.swing.JButton btProductoAgregar;
    private javax.swing.JButton btProductoCancelar;
    private javax.swing.JButton btProductoEliminar;
    private javax.swing.JButton btProductoHabilitar;
    private javax.swing.JButton btProductoModificar;
    private javax.swing.JButton btProductosDeshabilitar;
    private javax.swing.JButton btSalidaBuscar;
    private javax.swing.JButton btSalidasAceptar;
    private javax.swing.JButton btSalidasGestorSalidas;
    private javax.swing.JButton btSalidasSalir;
    private javax.swing.JButton btUsuarioCancelarSeleccion;
    private javax.swing.JButton btUsuarioEliminar;
    private javax.swing.JButton btUsuarioModificar;
    private javax.swing.JButton btUsuarioNuevo;
    private javax.swing.JButton btValidaAdministradorAcceder;
    private javax.swing.JButton btValidaAdministradorSalir;
    private javax.swing.JButton btVentaCancelarVenta;
    private javax.swing.JButton btVentaImprimirTicket;
    private javax.swing.JButton btVentaReanudarVenta;
    private javax.swing.JButton btventaBuscar;
    private javax.swing.JButton btventaBuscar1;
    private javax.swing.JComboBox<String> cbLogIUsuarios;
    private javax.swing.JComboBox<String> cbNotasMes;
    private javax.swing.JComboBox<String> cbProductoCategoria;
    private javax.swing.JComboBox<String> cbSalidaMes;
    private javax.swing.JComboBox<String> cbVentasMes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lbAP;
    private javax.swing.JLabel lbAgregarRapidoCodigo;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbFinalizarTicket;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbInventarioRCantidadProducto;
    private javax.swing.JLabel lbInventarioRCantidadProductoTotal;
    private javax.swing.JLabel lbInventarioRNombreProducto;
    private javax.swing.JLabel lbPrincipalLogo;
    private javax.swing.JLabel lbPrincipalNoVenta;
    private javax.swing.JLabel lbPrincipalTotal;
    private javax.swing.JLabel lbPrincipalUsuario;
    private javax.swing.JLabel lbValidaAdministradorModulo;
    private javax.swing.JLabel lbVentaCambio;
    private javax.swing.JLabel lbVentaEmpleado;
    private javax.swing.JLabel lbVentaFecha;
    private javax.swing.JLabel lbVentaHora;
    private javax.swing.JLabel lbVentaId;
    private javax.swing.JLabel lbVentaPago;
    private javax.swing.JLabel lbVentaStatus;
    private javax.swing.JLabel lbVentaTotal;
    private javax.swing.JLabel lbfinalizarCambio;
    private javax.swing.JLabel lbfinalizarCompraTotal;
    private javax.swing.JPasswordField pfLogInContraseña;
    private javax.swing.JRadioButton rbNotasAno;
    private javax.swing.JRadioButton rbNotasDia;
    private javax.swing.JRadioButton rbNotasMes;
    private javax.swing.ButtonGroup rgNotasSeleccion;
    private javax.swing.JSpinner spNotasAno;
    private javax.swing.JSpinner spNotasDia;
    private javax.swing.JSpinner spProductosProductosDisponibles;
    private javax.swing.JSpinner spProductosStock;
    private javax.swing.JSpinner spSalidaAno;
    private javax.swing.JSpinner spVentasAno;
    private javax.swing.JSpinner spVentasDia;
    private javax.swing.JTextArea taPrincipalNotificaiones;
    private javax.swing.JTextArea taUsuarioDatosEmpleado;
    private javax.swing.JTable tbBuscar;
    private javax.swing.JTable tbPrincipalVenta;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbSalidas;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTable tbVentaContenido;
    private javax.swing.JTable tbVentas;
    private javax.swing.JTextField tfAgregarRapidoNombre;
    private javax.swing.JTextField tfAgregarRapidoPrecio;
    private javax.swing.JTextField tfBusscarProducto;
    private javax.swing.JTextField tfConfiguraPorcentaje;
    private javax.swing.JTextField tfConfiguracionDireccionNotas;
    private javax.swing.JTextField tfConfiguracionDireccionReportes;
    private javax.swing.JTextField tfConfiguracionDireccionRespaldo;
    private javax.swing.JTextField tfFinalizarPago;
    private javax.swing.JTextField tfInventarioRCantidadAgregar;
    private javax.swing.JTextField tfInventarioRCodigoBarras;
    private javax.swing.JTextField tfNotasLimite;
    private javax.swing.JTextField tfProductoAno;
    private javax.swing.JTextField tfProductoCodigoBarras;
    private javax.swing.JTextField tfProductoDia;
    private javax.swing.JTextField tfProductoMes;
    private javax.swing.JTextField tfProductoNombre;
    private javax.swing.JTextField tfProductoPrecio;
    private javax.swing.JTextField tfProductosBuscar;
    private javax.swing.JTextField tfSalidasMonto;
    private javax.swing.JTextField tfSalidasMotivo;
    private javax.swing.JTextField tfUsuarioContra;
    private javax.swing.JTextField tfUsuarioNombreEmpleado;
    private javax.swing.JTextField tfUsuarioNombreUsuario;
    private javax.swing.JPasswordField tfValidaAdministradorPass;
    // End of variables declaration//GEN-END:variables

    
    //######################################## PRINCIPAL ########################################
    //######################################## PRINCIPAL ########################################
    //######################################## PRINCIPAL ########################################
    
    String CodigoBarras = "";
    
    private void PrincipalTablaVenta(KeyEvent evt){
        if(lbPrincipalUsuario.getText().equals("Iniciar Sesion")){
            JOptionPane.showMessageDialog(this," Error: Iniciar sesión", "Log In",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }else{
            Principal(evt);
        }
    }
    
    private void Principal(KeyEvent evt){
        int TeclaPrecionada = evt.getKeyCode();
        char ValorTecla = evt.getKeyChar();
        //System.out.print(TeclaPrecionada+"=");
        //System.out.println(ValorTecla);
        if(TeclaPrecionada == 112){//Buqueda de porductos F1
            LimpiarTabla();
            tfBusscarProducto.setText("");
            BuscarProducto.setVisible(Boolean.TRUE);
            CodigoBarras = "";
        }else if(TeclaPrecionada == 113 && !lbPrincipalTotal.getText().equals("$ 0.00")){//finalizar venta Sin tiket F2
            FinalizarVenta("Sin Ticket");
        }else if(TeclaPrecionada == 114 && !lbPrincipalTotal.getText().equals("$ 0.00")){//finalizar venta Con tiket F3
            FinalizarVenta("Con Ticket");
        }else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
            int toDelete = tbPrincipalVenta.getSelectedRow();
            PrincipalEliminarProductoVentas(toDelete);
        }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE && !lbPrincipalTotal.getText().equals("$ 0.00")){
            PrincipalPausaVenta();
        }else if(TeclaPrecionada == 115){//Modulo Ventas F4
            CodigoBarras = "";
            VentasVentana();
        }
        else if(TeclaPrecionada == 116){//Modulo Productos F5
            CodigoBarras = "";
            ValidaAdministradorVentana("PRODUCTOS");
        }else if(TeclaPrecionada == 117){//Modulo Usuarios F6
            CodigoBarras = "";
            ValidaAdministradorVentana("USUARIOS");
        }else if(TeclaPrecionada == 118){//Modulo Notas F7
            CodigoBarras = "";
            ValidaAdministradorVentana("NOTAS");
        }else if(TeclaPrecionada == 120){//Modulo Configuracion F9
            CodigoBarras = "";
            ValidaAdministradorVentana("CONFIGURACION");
        }else if(TeclaPrecionada == 121){//Modulo Salidas F10
            CodigoBarras = "";
            //ValidaAdministradorVentana("SALIDAS");
            Salidas();
        }else if(TeclaPrecionada == 122){//Modulo InventarioRapido F11
            CodigoBarras = "";
            InventarioRapido();
        }else if(TeclaPrecionada == 10 && CodigoBarras.length() > 4){
            String NombrePrecio[] = BaseDeDatos.AgregarCodigoBarras(CodigoBarras);
            if(NombrePrecio[0].equals("NO")){
                //JOptionPane.showMessageDialog(this," Error: Producto no encontrado \n", "Producto",JOptionPane.INFORMATION_MESSAGE,IconoError);
                AgregarProductoRapido();
                CodigoBarras = "";
            }else{
                AgregarProdutoVenta(CodigoBarras,NombrePrecio[0], NombrePrecio[1], NombrePrecio[2]);
                CodigoBarras = ""; 
            }
        }else if(TeclaPrecionada == 10){
            int Registros = tbPrincipalVenta.getRowCount()-1;
            PrincipalActualizaTabla(Registros);
            CodigoBarras = "";
        }else if (!(TeclaPrecionada == 37 | TeclaPrecionada == 38 | TeclaPrecionada == 39 | TeclaPrecionada == 40 | TeclaPrecionada == 8)){
            CodigoBarras = CodigoBarras+ValorTecla;
        }else if(TeclaPrecionada == 8){
            CodigoBarras = ""; 
        }  
    }
    
    private void AgregarProdutoVenta(String IDProducto,String Producto, String Precio, String Categoria){
        lbPrincipalNoVenta.setText(BaseDeDatos.PrincipalVentaProceso());
        BaseDeDatos.PrincipalVerificaVentas(lbPrincipalNoVenta.getText(), IDProducto, Producto, Precio, Categoria);
        PrincipalLimpiatablaVentas();
        BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
    }
    
    private void PrincipalLimpiatablaVentas(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbPrincipalVenta.getModel();
            int filas=tbPrincipalVenta.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void PrincipalEliminarProductoVentas(int RowToDelete){
        String IDVentasCategoria[] = BaseDeDatos.PrincipalIDVentas(lbPrincipalNoVenta.getText(), String.valueOf(tbPrincipalVenta.getValueAt(RowToDelete, 1)));
        BaseDeDatos.PrincipalEliminarVentas(IDVentasCategoria[0]);
        PrincipalLimpiatablaVentas();
        BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
        tbPrincipalVenta.grabFocus();
    }
    
    private void PrincipalActualizaTabla(int Registros){
        for(int i = 0; i <= Registros; i++){
            String IDVentasCategoria[] = BaseDeDatos.PrincipalIDVentas(lbPrincipalNoVenta.getText(), String.valueOf(tbPrincipalVenta.getValueAt(i, 1)));
            
            String IDVentas = IDVentasCategoria[0];
            String Cantidad = String.valueOf(tbPrincipalVenta.getValueAt(i, 0));
            String Precio = String.valueOf(tbPrincipalVenta.getValueAt(i, 2));
            String Categoria = IDVentasCategoria[1];
            if(Cantidad.equals("")){
                Cantidad = "0";
            }
            BaseDeDatos.PrincipalActualizaVenta(IDVentas, Cantidad, Precio, Categoria);
        }
        PrincipalLimpiatablaVentas();
        BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
    }
    
    private void PrincipalPausaVenta(){
        int Respuesta = JOptionPane.showConfirmDialog(null, "Confirme pausar venta No: \n"+ lbPrincipalNoVenta.getText(), "PAUSA VENTA",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(Respuesta == 0){
            String ID_Venta = lbPrincipalNoVenta.getText();
            String Total = FinalizarLimpiaTotal().concat("0");
            String Cajero = lbPrincipalUsuario.getText();
            String Fecha = lbFecha.getText();
            String Hora = lbHora.getText().concat(" "+lbAP.getText());
            String Status = "PAUSA";
            BaseDeDatos.PrincipalPausaVenta(ID_Venta, Total, Cajero, Fecha, Hora, Status);
            lbPrincipalNoVenta.setText(BaseDeDatos.PrincipalVentaProceso());
            PrincipalLimpiatablaVentas();
            BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
        }
    }
    
    private void PrincipalVerificaSesion(){
        String Sesion = BaseDeDatos.PrincipalEstadoSesion();
        if(Sesion.equals("Iniciar Sesion")){
            lbPrincipalUsuario.setText("Iniciar Sesion");
            btIniciar.setText("Iniciar");
        }else{
            lbPrincipalUsuario.setText(Sesion);
            btIniciar.setText("Salir");
        }
    }

    //######################################## LOG IN ########################################
    //######################################## LOG IN ########################################
    //######################################## LOG IN ########################################
    
    private void LogIn(){
        LogIn.setVisible(Boolean.TRUE);
        cbLogIUsuarios.removeAllItems();
        BaseDeDatos.LlenaComboBoxLogIn(cbLogIUsuarios);
        pfLogInContraseña.grabFocus();
    }
    
    private void ValidaLogIn(){
        if(pfLogInContraseña.getText().equals("")){
            JOptionPane.showMessageDialog(this, "COLOCAR CONTRASEÑA", "LonIn",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }else if(BaseDeDatos.ValidaLogIn(cbLogIUsuarios.getSelectedItem().toString(), pfLogInContraseña.getText()) | (cbLogIUsuarios.getSelectedItem().toString().equals("ADMINISTRADOR") && pfLogInContraseña.getText().equals("root"))){
            JOptionPane.showMessageDialog(this, "Bienvenido "+cbLogIUsuarios.getSelectedItem().toString(), "LonIn",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            lbPrincipalUsuario.setText(cbLogIUsuarios.getSelectedItem().toString());
            btIniciar.setText("Salir");
            LogIn.dispose();
            tbPrincipalVenta.grabFocus();
            BaseDeDatos.PrincipalActualizaSesion(lbPrincipalUsuario.getText());
        }else{
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta para "+cbLogIUsuarios.getSelectedItem().toString(), "LonIn",JOptionPane.INFORMATION_MESSAGE,IconoError);
            pfLogInContraseña.setText("");
            pfLogInContraseña.grabFocus();
        }
    }
    
    private void LogInAvilitaSistema(String EstadoSecion){
        if(EstadoSecion.equals("Iniciar")){
            LogIn();
        }else if(EstadoSecion.equals("Salir")){
            if(!lbPrincipalTotal.getText().equals("$ 0.00")){
                JOptionPane.showMessageDialog(this," ERROR: VENTA EN POCESO \n HAY PRODUCTOS MARCADOS PARA VENTA", "RestauraDB",JOptionPane.INFORMATION_MESSAGE,IconoError);
                tbPrincipalVenta.grabFocus();
            }else if(!BaseDeDatos.PrincipalVentasPausa()){
                JOptionPane.showMessageDialog(this," ERROR: VENTA EN POCESO \n HAY VENTAS EN PAUSA", "RestauraDB",JOptionPane.INFORMATION_MESSAGE,IconoError);
                tbPrincipalVenta.grabFocus();
            }else{
                LogInPreparaReporte();
                lbPrincipalUsuario.setText("Iniciar Sesion");
                btIniciar.setText("Iniciar");
                BaseDeDatos.PrincipalActualizaSesion(lbPrincipalUsuario.getText());
                LogIn();
            }
        }
    }
    
    private void LogInPreparaReporte(){
        String Usuario = lbPrincipalUsuario.getText();
        String Fecha = lbFecha.getText();
        String NombreArchivo = "Reporte"+Usuario+""+Fecha;
        BaseDeDatos.LongInReporteSalir(Usuario, Fecha, NombreArchivo);
    }
    
    //######################################## BUSCAR ########################################
    //######################################## BUSCAR ########################################
    //######################################## BUSCAR ########################################
    
    private void BuscarProducto(String ProductoBuscar){
        LimpiarTabla();
        Pattern pat = Pattern.compile("[0-9]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(ProductoBuscar);
        if (mat.matches()){
            BaseDeDatos.BurcarProductoId(tbBuscar,ProductoBuscar);
        }else if(ProductoBuscar.equals("")){
            LimpiarTabla();
        }
        else{
            BaseDeDatos.BurcarProductoNombre(tbBuscar, ProductoBuscar);
        }
    }
    
    public void LimpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbBuscar.getModel();
            int filas=tbBuscar.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void CargarProductoVenta(int Fila){
        String ID = String.valueOf(tbBuscar.getValueAt(Fila, 0));
        String Producto = String.valueOf(tbBuscar.getValueAt(Fila, 1));
        String Precio = String.valueOf(tbBuscar.getValueAt(Fila, 2));
        String Categoria = String.valueOf(tbBuscar.getValueAt(Fila, 3));;
        //System.out.println(Producto+","+Precio);
        AgregarProdutoVenta(ID, Producto, Precio, Categoria);
        BuscarProducto.dispose();
    }
    
    //######################################## PRODUCTOS ########################################
    //######################################## PRODUCTOS ########################################
    //######################################## PRODUCTOS ########################################
    
    private void VentanaProductos(){
        Productos.setVisible(Boolean.TRUE);
        
        ProductoLimpiarTabla();
        BaseDeDatos.ProductosLlenarTablaTodos(tbProductos);
        ProductoCancelarSeleccion();
        
        tfProductoDia.setEnabled(Boolean.FALSE);
        tfProductoMes.setEnabled(Boolean.FALSE);
        tfProductoAno.setEnabled(Boolean.FALSE);
        
        tfProductoDia.setText("00");
        tfProductoMes.setText("00");
        tfProductoAno.setText("0000");
    }
    
    private void ProductoCancelarSeleccion(){
        cbProductoCategoria.removeAllItems();
        BaseDeDatos.ProductosLlenarCBCategoria(cbProductoCategoria);
        
        btProductoCancelar.setEnabled(Boolean.FALSE);
        btProductoEliminar.setEnabled(Boolean.FALSE);
        btProductoModificar.setEnabled(Boolean.FALSE);
        btProductoAgregar.setEnabled(Boolean.TRUE);
        tfProductoCodigoBarras.setEnabled(Boolean.TRUE);
        
        tfProductoCodigoBarras.setText("");
        tfProductoNombre.setText("");
        tfProductoPrecio.setText("");
        tfProductosBuscar.setText("");
        
        spProductosProductosDisponibles.setValue(0);
        spProductosStock.setValue(0);
        
        tfProductoDia.setEnabled(Boolean.FALSE);
        tfProductoMes.setEnabled(Boolean.FALSE);
        tfProductoAno.setEnabled(Boolean.FALSE);
        
        tfProductoDia.setText("00");
        tfProductoMes.setText("00");
        tfProductoAno.setText("0000");
    }
    
    private void ProductoLimpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbProductos.getModel();
            int filas=tbProductos.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
            
    private void AgregarProducto(){
        String Status = ProductosValidaCampos();
        if(Status.equals("OK")){
            String ID = tfProductoCodigoBarras.getText().toUpperCase();
            String Nombre = tfProductoNombre.getText().toUpperCase();
            String Precio = tfProductoPrecio.getText();
            String Categoria = cbProductoCategoria.getSelectedItem().toString();
            String Disponiviliad = spProductosProductosDisponibles.getValue().toString();
            String Stock = spProductosStock.getValue().toString();
            String Fecha = tfProductoDia.getText() + tfProductoMes.getText() + tfProductoAno.getText();
            BaseDeDatos.ProductoAgregarNuevo(ID, Nombre, Precio, Categoria, Disponiviliad, Stock, Fecha);
            JOptionPane.showMessageDialog(this, tfProductoNombre.getText().toUpperCase() + "\n Agregado Exitosamente", "Agregar Producto",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            VentanaProductos();
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Agregar Producto",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private String ProductosValidaCampos(){
        String Status = "";
        
        if(tfProductoCodigoBarras.getText().equals("")){
           Status = Status + "Codigo de baras o identificador bacio \n"; 
        }else if(tfProductoCodigoBarras.getText().length()>16){
           Status = Status + "Identificador porducto demaciado largo 16 Caracteres \n";
        }else if(BaseDeDatos.ProductoExistente(tfProductoCodigoBarras.getText())){
            Status = Status + "Identificador ya registrado \n";
        }
        
        if(tfProductoNombre.getText().equals("")){
            Status = Status + "Nombre porducto bacio \n";
        }else if(tfProductoNombre.getText().length()>30){
            Status = Status + "Nombre porducto demaciado largo 30 Caracteres \n";
        }
        
        if(tfProductoPrecio.getText().equals("")){
            Status = Status + "Precio producto bacio \n";
        }else{
            if(!ProductoValidaPrecio(tfProductoPrecio.getText())){
                Status = Status + "Formato de precio incorrecto (8.00 , 99.50 , 10.50) \n";
            }
        }
        
        if(tfProductoDia.getText().equals("") | tfProductoMes .getText().equals("") | tfProductoAno.getText().equals("")){
            Status = Status + "Campos vacios de fecla \n";
        }else if(!(tfProductoAno.getText().length() == 4) | !(tfProductoMes.getText().length() == 2) | !(tfProductoDia.getText().length() == 2)){
            Status = Status + "Campos imcompletos de fecla Dia = 2 Digitos, Mes = 2 Digitos, Año = 4 Digitos\n";
        }else if(ProductoValidaFecha()){
            Status = Status + "Solo numero en campos de fecla \n";
        }else if(ProductoValidaFechaNumero()){
            Status = Status + "Valor de fecla inadecuado \n";
        }
        
        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    private boolean ProductoValidaFechaNumero(){
        boolean Status = false;
        int DiaValida = Integer.parseInt(tfProductoDia.getText());
        int MesValida = Integer.parseInt(tfProductoMes.getText());
        int AnoValida = Integer.parseInt(tfProductoAno.getText());
        //System.out.println(AnoValida);
        
        if(DiaValida>31){
            Status = true;
        }
        
        if(MesValida>12){
            Status = true;
        }
        
        if(AnoValida<Integer.parseInt(Ano) && !(AnoValida == 0)){
            Status = true;
        }
        
        return Status;
    }
    
    private boolean ProductoValidaFecha(){
        boolean Status = false;
        
        Pattern pat = Pattern.compile("[0-9]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(tfProductoDia.getText());
        Matcher mat1 = pat.matcher(tfProductoMes.getText());
        Matcher mat2 = pat.matcher(tfProductoAno.getText());
        
        if (!mat.matches()){
            Status = true;
        }
        
        if (!mat1.matches()){
            Status = true;
        }
        
        if (!mat2.matches()){
            Status = true;
        }
        
        return Status;
    }
    
    private boolean ProductoValidaPrecio(String Precio){
        //System.out.println(Precio);
        boolean Status = true;
        String PrecioFormato = "";
        try{
            float FormatoFloat = Float.parseFloat(Precio);
            PrecioFormato = ""+FormatoFloat;
            PrecioFormato = BaseDeDatos.PrincipalCorrijePrecioAgranel(PrecioFormato.concat("0"));
            tfProductoPrecio.setText(PrecioFormato);
        }catch(Exception e){
            Status = false;
        }
        return Status;
    }
    
    private void ProductoSeleccionTabla(int Seleccionado){
        tfProductoCodigoBarras.setText(String.valueOf(tbProductos.getValueAt(Seleccionado, 0)));
        spProductosProductosDisponibles.setValue(Integer.parseInt(String.valueOf(tbProductos.getValueAt(Seleccionado, 1))));
        tfProductoNombre.setText(String.valueOf(tbProductos.getValueAt(Seleccionado, 2)));
        tfProductoPrecio.setText(String.valueOf(tbProductos.getValueAt(Seleccionado, 3)));
        cbProductoCategoria.setSelectedItem(String.valueOf(tbProductos.getValueAt(Seleccionado,4)));
        spProductosStock.setValue(Integer.parseInt(String.valueOf(tbProductos.getValueAt(Seleccionado, 5))));
        
        String Fecha = BaseDeDatos.ProductoFecha(tfProductoCodigoBarras.getText());
        if(!(Fecha.equals("00000000"))){
            tfProductoDia.setEnabled(Boolean.TRUE);
            tfProductoMes.setEnabled(Boolean.TRUE);
            tfProductoAno.setEnabled(Boolean.TRUE);
        }else{
            tfProductoDia.setEnabled(Boolean.FALSE);
            tfProductoMes.setEnabled(Boolean.FALSE);
            tfProductoAno.setEnabled(Boolean.FALSE);
        }
        
        tfProductoDia.setText(""+Fecha.charAt(0)+Fecha.charAt(1));
        tfProductoMes.setText(""+Fecha.charAt(2)+Fecha.charAt(3));
        tfProductoAno.setText(""+Fecha.charAt(4)+Fecha.charAt(5)+Fecha.charAt(6)+Fecha.charAt(7));
        
        tfProductoCodigoBarras.setEnabled(Boolean.FALSE);
        btProductoAgregar.setEnabled(Boolean.FALSE);
        btProductoCancelar.setEnabled(Boolean.TRUE);
        btProductoEliminar.setEnabled(Boolean.TRUE);
        btProductoModificar.setEnabled(Boolean.TRUE);
    }
    
    private void ProductosEliminar(){
        int Respuesta = JOptionPane.showConfirmDialog(null, "Confirme Eliminar \n"+ tfProductoNombre.getText(), "Eliminar Producto",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(Respuesta == 0){
            BaseDeDatos.ProductosEliminar(tfProductoCodigoBarras.getText());
            ProductoLimpiarTabla();
            BaseDeDatos.ProductosLlenarTablaTodos(tbProductos);
            ProductoCancelarSeleccion();
        }
    }
    
    private void ProductoModificar() throws ParseException{
        String Status = ProductosModificarValidaCampos();
        if(Status.equals("OK")){
            int Respuesta = JOptionPane.showConfirmDialog(null, "Confirme Modificar \n"+ tfProductoCodigoBarras.getText(), "Modificar Producto",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(Respuesta == 0){
                String Fecha = tfProductoDia.getText() + tfProductoMes.getText() + tfProductoAno.getText();
                BaseDeDatos.ProductosModificar(tfProductoCodigoBarras.getText(), spProductosProductosDisponibles.getValue().toString(), tfProductoNombre.getText(), tfProductoPrecio.getText(), cbProductoCategoria.getSelectedItem().toString(), spProductosStock.getValue().toString(), Fecha);
                ProductoLimpiarTabla();
                BaseDeDatos.ProductosLlenarTablaTodos(tbProductos);
                ProductoCancelarSeleccion();
                VentanaProductos();
                ActualizaNotificacion();
            }
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Modificar Producto",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private String ProductosModificarValidaCampos(){
        String Status = "";
        tfProductoNombre.setText(tfProductoNombre.getText().toUpperCase());
        if(tfProductoNombre.getText().equals("")){
            Status = Status + "Nombre porducto bacio \n";
        }else if(tfProductoNombre.getText().length()>30){
            Status = Status + "Nombre porducto demaciado largo 30 Caracteres \n";
        }
        
        if(tfProductoPrecio.getText().equals("")){
            Status = Status + "Precio producto bacio \n";
        }else{
            if(!ProductoValidaPrecio(tfProductoPrecio.getText())){
                Status = Status + "Formato de precio incorrecto (8.00 , 99.50 , 10.50) \n";
            }
        }
        
        if(tfProductoDia.getText().equals("") | tfProductoMes .getText().equals("") | tfProductoAno.getText().equals("")){
            Status = Status + "Campos vacios de fecla \n";
        }else if(!(tfProductoAno.getText().length() == 4) | !(tfProductoMes.getText().length() == 2) | !(tfProductoDia.getText().length() == 2)){
            Status = Status + "Campos imcompletos de fecla Dia = 2 Digitos, Mes = 2 Digitos, Año = 4 Digitos\n";
        }else if(ProductoValidaFecha()){
            Status = Status + "Solo numero en campos de fecla \n";
        }else if(ProductoValidaFechaNumero()){
            Status = Status + "Valor de fecla inadecuado \n";
        }
        
        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    private void ProductosBuscarVentanaProductos(String ProductoBuscar){
        LimpiarTablaProducto();
        Pattern pat = Pattern.compile("[0-9]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(ProductoBuscar);
        if (mat.matches()){
            BaseDeDatos.ProductoBuscarId(tbProductos,ProductoBuscar);
        }else if(ProductoBuscar.equals("")){
            LimpiarTablaProducto();
        }
        else{
            BaseDeDatos.ProductoBuscarNombre(tbProductos,ProductoBuscar);
        }
    }
    
    public void LimpiarTablaProducto(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbProductos.getModel();
            int filas=tbProductos.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    //######################################## AUXILIARES ########################################
    //######################################## AUXILIARES ########################################
    //######################################## AUXILIARES ########################################
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        int h = calendario.get(Calendar.HOUR_OF_DAY);

        if (ampm.equals("PM") && h!=12) {
            h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
        
        Dia = new SimpleDateFormat("dd").format(fechaHoraActual);
        Mes = new SimpleDateFormat("MM").format(fechaHoraActual);
        Ano = new SimpleDateFormat("yyyy").format(fechaHoraActual);
    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lbHora.setText(hora + ":" + minutos + ":" + segundos);
            lbAP.setText(ampm);
            lbFecha.setText(Dia+" de "+mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"))+" del "+Ano);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    
    private void ActualizaNotificacion() throws ParseException{
        taPrincipalNotificaiones.setText("");
        taPrincipalNotificaiones.setEditable(false);
        
        taPrincipalNotificaiones.setFont(new Font("SansSerif",Font.BOLD, 12));
        taPrincipalNotificaiones.setForeground(Color.BLACK);
        
        taPrincipalNotificaiones.append("* * * * * * * * * * * * * *    PRODUCTOS AGOTADOS    * * * * * * * * * * * * * *\n");
        taPrincipalNotificaiones.append("\n");
        BaseDeDatos.PrincipalActualizaNotificacionesAgotados(taPrincipalNotificaiones);
        taPrincipalNotificaiones.append("\n");
        taPrincipalNotificaiones.append("* * * * * * * * * * * *     PRODUCTOS POR AGOTARSE     * * * * * * * * * * * *\n");
        taPrincipalNotificaiones.append("\n");
        BaseDeDatos.PrincipalActualizaNotificacionesPorAgotados(taPrincipalNotificaiones);
        taPrincipalNotificaiones.append("\n");
        taPrincipalNotificaiones.append("* * * * * * * * * * * * *     PRODUCTOS CADUCADOS     * * * * * * * * * * * * *\n");
        taPrincipalNotificaiones.append("\n");
        BaseDeDatos.PrincipalActualizaNotificacionesCaducos(taPrincipalNotificaiones,""+Dia+Mes+Ano);
        taPrincipalNotificaiones.append("\n");
        taPrincipalNotificaiones.append("* * * * * * * * * * * * *     PRODUCTOS POR CADUCAR     * * * * * * * * * * * * *\n");
        taPrincipalNotificaiones.append("\n");
        BaseDeDatos.PrincipalActualizaNotificacionesPorCaducar(taPrincipalNotificaiones,""+Dia+Mes+Ano);
    }


    //######################################## FINALIZAR VENTA ########################################
    //######################################## FINALIZAR VENTA ########################################
    //######################################## FINALIZAR VENTA ########################################
    
    private void FinalizarVenta(String Imprimir){
        FinalizaCompra.setVisible(Boolean.TRUE);
        btFinalizarEfectivo.grabFocus();
        tfFinalizarPago.setText("");
        lbfinalizarCambio.setText("$0000.00");
        lbfinalizarCompraTotal.setText(lbPrincipalTotal.getText());
        tfFinalizarPago.setEnabled(Boolean.FALSE);
        lbFinalizarTicket.setText(Imprimir);
    }
    
    private void FinalizarCambio(KeyEvent evt){
        char ValorTecla = evt.getKeyChar();
        if(ValorTecla == '9' || ValorTecla == '8' || ValorTecla == '7' || ValorTecla == '6' || ValorTecla == '5' || ValorTecla == '4' || ValorTecla == '3' || ValorTecla == '2' || ValorTecla == '1' || ValorTecla == '0' ){
            tfFinalizarPago.setText(tfFinalizarPago.getText()+ValorTecla);
            float Total = Float.parseFloat(FinalizarLimpiaTotal());
            float Pago = Float.parseFloat(tfFinalizarPago.getText());
            float Cambio = Pago - Total;
            String CambioLetra = ""+Cambio+"0";
            lbfinalizarCambio.setText("$ "+BaseDeDatos.PrincipalCorrijePrecioAgranel(CambioLetra));
        }else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
            btFinalizarTarjeta.grabFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
            btFinalizarEfectivo.grabFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            FinalizaCompra.dispose();
        }else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
            tfFinalizarPago.setText("");
            lbfinalizarCambio.setText("$0000.00");
        }else if(evt.getKeyCode() == 8){
            tfFinalizarPago.setText("");
            lbfinalizarCambio.setText("$0000.00");
        }
    }
    
    private String FinalizarLimpiaTotal(){
        String PrecioNoLimpio = lbPrincipalTotal.getText();
        String PrecioLimpio = "";
        for (int i = 0; i < lbPrincipalTotal.getText().length(); i++) {
            if(PrecioNoLimpio.charAt(i) == '9' || PrecioNoLimpio.charAt(i) == '8' || PrecioNoLimpio.charAt(i) == '7' || PrecioNoLimpio.charAt(i) == '6' || PrecioNoLimpio.charAt(i) == '5' || PrecioNoLimpio.charAt(i) == '4' || PrecioNoLimpio.charAt(i) == '3' || PrecioNoLimpio.charAt(i) == '2' || PrecioNoLimpio.charAt(i) == '1' || PrecioNoLimpio.charAt(i) == '0' || PrecioNoLimpio.charAt(i) == '.' ){
                PrecioLimpio = PrecioLimpio + PrecioNoLimpio.charAt(i);
            }
        }
        return PrecioLimpio;
    }
    
    private void FinalizarVentaFinaliza(String Pago){
        String ID_Venta = lbPrincipalNoVenta.getText();
        String Total = FinalizarLimpiaTotal().concat("0");
        String Cajero = lbPrincipalUsuario.getText();
        String Fecha = lbFecha.getText();
        String Hora = lbHora.getText().concat(" "+lbAP.getText());
        String Status = "FINALIZADA";
        
        
        if(Pago.equals("TARJETA")){
           Total = FinalizaVentaTarjeta();
        }
        
        FinalizaActualizaExistenciaProdustos();
        //BaseDeDatos.FinalizarFinalizaVenta(ID_Venta, Total, Cajero, Fecha, Hora, Pago, Status);
        if(tfFinalizarPago.getText().equals("")){
            BaseDeDatos.FinalizarFinalizaVenta(ID_Venta, Total, Cajero, Fecha, Hora, Pago, Status, "NA");
        }else{
            String Cambio= "PAGO:$"+tfFinalizarPago.getText()+" CAMBIO:"+lbfinalizarCambio.getText();
            BaseDeDatos.FinalizarFinalizaVenta(ID_Venta, Total, Cajero, Fecha, Hora, Pago, Status, Cambio);
        }
        
        try {
            ActualizaNotificacion();
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        lbPrincipalNoVenta.setText(BaseDeDatos.PrincipalVentaProceso());
        PrincipalLimpiatablaVentas();
        BaseDeDatos.PrincipalLlenaTablaVentas(lbPrincipalTotal, tbPrincipalVenta, lbPrincipalNoVenta.getText());
        if(lbFinalizarTicket.getText().equals("Con Ticket")){
            BaseDeDatos.PreparaTicket(ID_Venta);
        }else if(lbFinalizarTicket.getText().equals("Sin Ticket") && Pago.equals("TARJETA")){
            BaseDeDatos.PreparaTicket(ID_Venta);
        }
        FinalizaCompra.dispose();
        ConfiguracionGenerarRespaldo();
        NotasAutogenerar();
    }
    
    private void FinalizaActualizaExistenciaProdustos(){
        int Registros = tbPrincipalVenta.getRowCount()-1;
        
        for(int i = 0; i <= Registros; i++){
            String NombreProducto = String.valueOf(tbPrincipalVenta.getValueAt(i, 1));
            String Cantidad = String.valueOf(tbPrincipalVenta.getValueAt(i, 0));
            String Precio = String.valueOf(tbPrincipalVenta.getValueAt(i, 2));
            if(Cantidad.equals("")){
                Cantidad = "0";
            }
            BaseDeDatos.FinalizaExistenciaProductos(NombreProducto, Precio, Cantidad);
        }
    }
    
    private String FinalizaVentaTarjeta(){
        String Monto = JOptionPane.showInputDialog(null, "           Escriba el monto que recibe con tarjeta \n si el monto es el total de la comprar deje en blanco", "MONTO", JOptionPane.INFORMATION_MESSAGE);
        float MontoNumerico = 0;
        
        if(Monto.equals("")){
            Monto = FinalizarLimpiaTotal().concat("0");
        }
        
        try{
            MontoNumerico = Float.parseFloat(Monto);
        }catch(Exception e){
            FinalizaVentaTarjeta();
        }
        
        float Total = Float.parseFloat(FinalizarLimpiaTotal().concat("0"));
        if(MontoNumerico>Total){
            JOptionPane.showMessageDialog(null, "Monto a cobrar mayor al total", "MONTO", JOptionPane.WARNING_MESSAGE);
            FinalizaVentaTarjeta();
        }
        
        float ComicionPago = (MontoNumerico * BaseDeDatos.FinalizaPorcentajePagoTarjeta())/100;
        String Comicion4Digitos = BaseDeDatos.PrincipalCorrijePrecioAgranel(""+(ComicionPago)+"000");
        ComicionPago = Float.parseFloat(Comicion4Digitos);
        String Descripcion = ""+BaseDeDatos.FinalizaPorcentajePagoTarjeta()+"%Tarjeta$"+MontoNumerico;
        String TotalComicion = BaseDeDatos.PrincipalCorrijePrecioAgranel(""+(Total+ComicionPago)+"0");
        AgregarProdutoVenta("COMICION", Descripcion, ""+ComicionPago, "SERVICIO");
        return  TotalComicion;
    }
    
    //######################################## USUARIOS ########################################
    //######################################## USUARIOS ########################################
    //######################################## USUARIOS ########################################
    
    private void UsuariosVentana(){
        Usuarios.setVisible(Boolean.TRUE);
        UsuarioCancelaSelaccion();
    }
    
    private void UsuarioCancelaSelaccion(){
        tfUsuarioNombreUsuario.setText("");
        tfUsuarioContra.setText("");
        tfUsuarioNombreEmpleado.setText("");
        taUsuarioDatosEmpleado.setText("");
        tfUsuarioNombreUsuario.setEnabled(Boolean.TRUE);
        
        btUsuarioNuevo.setEnabled(Boolean.TRUE);
        btUsuarioModificar.setEnabled(Boolean.FALSE);
        btUsuarioEliminar.setEnabled(Boolean.FALSE);
        btUsuarioCancelarSeleccion.setEnabled(Boolean.FALSE);
        BaseDeDatos.UsuariosLlenarTabla(tbUsuarios);
        
        tfUsuarioNombreUsuario.grabFocus();
    }
    
    private void UsuariosSoloLetrasMayusculas(KeyEvent evt){
        int TeclaPrecionada = evt.getKeyCode();
        if((TeclaPrecionada > 64) | (TeclaPrecionada < 91)){
            tfUsuarioNombreUsuario.setText(tfUsuarioNombreUsuario.getText().toUpperCase());
        }
    }
    
    private void UsuariosNuevoUsuario(){
        String Status = UsuariosValidaCamposNuevos();
        if(Status.equals("OK")){
            BaseDeDatos.UsuarioNuevo(tfUsuarioNombreUsuario.getText(), tfUsuarioContra.getText(), tfUsuarioNombreEmpleado.getText(), taUsuarioDatosEmpleado.getText());
            UsuarioCancelaSelaccion();
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Agregar Usuario",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private String UsuariosValidaCamposNuevos(){
        String Status = "";
        
        if(tfUsuarioNombreUsuario.getText().equals("")){
            Status = Status+"Campo nombre de usuario bacio \n";
        }else if(UsuarioValidaUsuario()){
            Status = Status+"Campo nombre de usuario solo letras \n";
        }else if(tfUsuarioNombreUsuario.getText().length()>15){
            Status = Status+"Campo nombre no mas de 15  caracteres \n";
        }else if(BaseDeDatos.UsuarioExiste(tfUsuarioNombreUsuario.getText())){
            Status = Status+"Usuario existente \n";
        }
        
        if(tfUsuarioContra.getText().equals("")){
            Status = Status+"Campo contraseña bacio \n";
        }else if(UsuarioValidaContra()){
            Status = Status+"Campo contraseña solo letra y numeros \n";
        }else if(tfUsuarioContra.getText().length()>15){
            Status = Status+"Campo contraseña no mas de 15 caracteres \n";
        }
        
        if(tfUsuarioNombreEmpleado.getText().equals("")){
            Status = Status+"Campo empleado bacio \n";
        }else if(UsuarioValidaEmpleado()){
            Status = Status+"Campo empleado solo letras y espacios \n";
        }else if(tfUsuarioContra.getText().length()>50){
            Status = Status+"Campo empleado no mas de 50 caracteres \n";
        }

        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    private boolean UsuarioValidaUsuario(){
        boolean Status = Boolean.TRUE;
        Pattern pat = Pattern.compile("[A-Z]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(tfUsuarioNombreUsuario.getText());
        if (mat.matches()){
            Status = Boolean.FALSE;
        }
        return Status;
    }
    
    private boolean UsuarioValidaContra(){
        Boolean Status = Boolean.TRUE;
        Pattern pat = Pattern.compile("[A-Za-z\\d]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(tfUsuarioContra.getText());
        if (mat.matches()){
            Status = Boolean.FALSE;
        }
        return Status;
    }
    
    private boolean UsuarioValidaEmpleado(){
        Boolean Status = Boolean.TRUE;
        Pattern pat = Pattern.compile("[A-Za-z\\s]*");//<-----------Define numero entero
        Matcher mat = pat.matcher(tfUsuarioNombreEmpleado.getText());
        if (mat.matches()){
            Status = Boolean.FALSE;
        }
        return Status;
    }
    
    private void UsuarioSeleccionTabla(int Seleccion){
        String[] ContraEmpleadoContacto = BaseDeDatos.UsuariosContraEmpleadoContacto(String.valueOf(tbUsuarios.getValueAt(Seleccion, 0)));
        tfUsuarioNombreUsuario.setEnabled(Boolean.FALSE);
        tfUsuarioNombreUsuario.setText(String.valueOf(tbUsuarios.getValueAt(Seleccion, 0)));
        tfUsuarioContra.setText(ContraEmpleadoContacto[0]);
        tfUsuarioNombreEmpleado.setText(ContraEmpleadoContacto[1]);
        taUsuarioDatosEmpleado.setText(ContraEmpleadoContacto[2]);
        
        btUsuarioCancelarSeleccion.setEnabled(Boolean.TRUE);
        btUsuarioEliminar.setEnabled(Boolean.TRUE);
        btUsuarioModificar.setEnabled(Boolean.TRUE);
        btUsuarioNuevo.setEnabled(Boolean.FALSE);
        if(String.valueOf(tbUsuarios.getValueAt(Seleccion, 0)).equals("ADMINISTRADOR")){
            btUsuarioEliminar.setEnabled(Boolean.FALSE);
        }
    }
    
    private void UsuarioEliminar(){
        int Respuesta = JOptionPane.showConfirmDialog(null, "Confirme Eliminar \n"+ tfUsuarioNombreUsuario.getText(), "Eliminar Usuario",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(Respuesta == 0){
            BaseDeDatos.UsuarioEliminar(tfUsuarioNombreUsuario.getText());
            UsuarioCancelaSelaccion();
        }
    }
    
    private void UsuarioModificar(){
        String Status = UsuariosValidaModificar();
        if(Status.equals("OK")){
            int Respuesta = JOptionPane.showConfirmDialog(null, "Confirme Modificar \n"+ tfUsuarioNombreUsuario.getText(), "Modificar Usuario",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(Respuesta == 0){
                BaseDeDatos.UsuarioModificar(tfUsuarioNombreUsuario.getText(), tfUsuarioContra.getText(), tfUsuarioNombreEmpleado.getText(), taUsuarioDatosEmpleado.getText());
                UsuarioCancelaSelaccion();
            }
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Agregar Usuario",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private String UsuariosValidaModificar(){
        String Status = "";
        
        if(tfUsuarioContra.getText().equals("")){
            Status = Status+"Campo contraseña bacio \n";
        }else if(UsuarioValidaContra()){
            Status = Status+"Campo contraseña solo letra y numeros \n";
        }else if(tfUsuarioContra.getText().length()>10){
            Status = Status+"Campo contraseña no mas de 10 caracteres \n";
        }
        
        if(tfUsuarioNombreEmpleado.getText().equals("")){
            Status = Status+"Campo empleado bacio \n";
        }else if(UsuarioValidaEmpleado()){
            Status = Status+"Campo empleado solo letras y espacios \n";
        }else if(tfUsuarioContra.getText().length()>50){
            Status = Status+"Campo empleado no mas de 50 caracteres \n";
        }

        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    //######################### VALIDA ADMINISTRADOR #################################
    //######################### VALIDA ADMINISTRADOR #################################
    //######################### VALIDA ADMINISTRADOR #################################
    
    private void ValidaAdministradorSalida(){
        ValidaAdministrador.dispose();
    }
    
    private void ValidaAdministradorVentana(String Modulo){
        ValidaAdministrador.setVisible(Boolean.TRUE);
        lbValidaAdministradorModulo.setText(Modulo);
        tfValidaAdministradorPass.setText("");
    }
    
    private void ValidaAdministradorAcceder(){
        if(tfValidaAdministradorPass.getText().equals("")){
            JOptionPane.showMessageDialog(this, "COLOCAR CONTRASEÑA", "ACCESO",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }else if(BaseDeDatos.ValidaLogIn("ADMINISTRADOR", tfValidaAdministradorPass.getText()) || tfValidaAdministradorPass.getText().equals("root")){
            String Modulo = lbValidaAdministradorModulo.getText();
            if(Modulo.equals("PRODUCTOS")){
               VentanaProductos();
            }
            if(Modulo.equals("USUARIOS")){
                UsuariosVentana();
            }
            if(Modulo.equals("VENTAS")){
                VentasVentana();
            }
            if(Modulo.equals("NOTAS")){
                NotasVentana();
            }
            if(Modulo.equals("CONFIGURACION")){
                ConfiguracionVentana();
            }
            if(Modulo.equals("SALIDAS")){
                GestorSalidas();
            }
            ValidaAdministrador.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "ACCESO DENEGADO", "ACCESO",JOptionPane.INFORMATION_MESSAGE,IconoError);
            ValidaAdministrador.dispose();
        }
    }
    
    //######################################## VENTA ########################################
    //######################################## VENTA ########################################
    //######################################## VENTA ########################################
    
    private void VentasVentana(){
        Ventas.setVisible(Boolean.TRUE);
        spVentasDia.setValue(Integer.parseInt(Dia));
        cbVentasMes.setSelectedIndex(Integer.parseInt(Mes)-1);
        spVentasAno.setValue(Integer.parseInt(""+Ano.charAt(2)+Ano.charAt(3)));
        btVentaCancelarVenta.setEnabled(Boolean.FALSE);
        btVentaImprimirTicket.setEnabled(Boolean.FALSE);
        btVentaReanudarVenta.setEnabled(Boolean.FALSE);
        //VentaFormatoFecha();
        LimpiarTablaVenta();
        BaseDeDatos.VentaMuestraPuasa(tbVentas);
    }
    
    private void VentaFormatoFecha(){
        String Fecha = "";
        if(spVentasDia.getValue().toString().length() == 1){
            Fecha = "0"+spVentasDia.getValue().toString()+" de "+cbVentasMes.getSelectedItem().toString() + " del 20"+spVentasAno.getValue().toString();
        }else{
            Fecha = spVentasDia.getValue().toString()+" de "+cbVentasMes.getSelectedItem().toString() + " del 20"+spVentasAno.getValue().toString();
        }
        LimpiarTablaVenta();
        BaseDeDatos.VentaLlenarTabla(tbVentas, Fecha);
    }
    
    private void LimpiarTablaVenta(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbVentas.getModel();
            int filas=tbVentas.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void VentaSeleccionTabla(int Seleccion){
        String[] DetallesVenta = BaseDeDatos.VentaDetallesVenta(String.valueOf(tbVentas.getValueAt(Seleccion, 0)));
        lbVentaId.setText(String.valueOf(tbVentas.getValueAt(Seleccion, 0)));
        lbVentaTotal.setText(DetallesVenta[0]);
        lbVentaEmpleado.setText(DetallesVenta[1]);
        lbVentaFecha.setText(DetallesVenta[2]);
        lbVentaHora.setText(DetallesVenta[3]);
        lbVentaPago.setText(DetallesVenta[4]);
        lbVentaStatus.setText(DetallesVenta[5]);
        lbVentaCambio.setText(DetallesVenta[6]);
        LimpiarTablaVentaContenido();
        BaseDeDatos.VentaLlenarTablaContenido(tbVentaContenido, lbVentaId.getText());
        btVentaImprimirTicket.setEnabled(Boolean.TRUE);
        if(lbVentaStatus.getText().equals("FINALIZADA")){
            btVentaCancelarVenta.setEnabled(Boolean.TRUE);
            btVentaImprimirTicket.setEnabled(Boolean.TRUE);
        }else{
            btVentaCancelarVenta.setEnabled(Boolean.FALSE);
            btVentaImprimirTicket.setEnabled(Boolean.FALSE);
        }
        
        if(lbVentaStatus.getText().equals("PAUSA")){
            btVentaReanudarVenta.setEnabled(Boolean.TRUE);
            btVentaCancelarVenta.setEnabled(Boolean.TRUE);
        }
        DetallesVenta = null;
    }
    
    private void LimpiarTablaVentaContenido(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbVentaContenido.getModel();
            int filas=tbVentaContenido.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void VentaCancelarVenta(){
        int Registros = tbVentaContenido.getRowCount()-1;
        String TipoCancelacion = "CANCELADA";
        String ServicioContenido = "";
        for(int i = 0; i <= Registros; i++){
            String[] IdCategoria = BaseDeDatos.VentaIDProductoCategoria(String.valueOf(tbVentaContenido.getValueAt(i, 1)), String.valueOf(tbVentaContenido.getValueAt(i, 2)));
            
            if(String.valueOf(tbVentaContenido.getValueAt(i, 1)).contains("Tarjeta")){
                ServicioContenido = ServicioContenido + String.valueOf(tbVentaContenido.getValueAt(i, 1))+"\n";
            }else if(IdCategoria[0].equals("")){
                JOptionPane.showMessageDialog(null, "Producto "+String.valueOf(tbVentaContenido.getValueAt(i, 1))+" no existe, fue eliminado o modificado \n", "Cancelacion Servicio", JOptionPane.INFORMATION_MESSAGE);
            }else if(IdCategoria[1].equals("SERVICIO")){
                TipoCancelacion = "PARCIAL";
                ServicioContenido = ServicioContenido + String.valueOf(tbVentaContenido.getValueAt(i, 1))+"\n";
            }else{
                BaseDeDatos.VentaCancelaDevuelveProductos(IdCategoria[0], String.valueOf(tbVentaContenido.getValueAt(i, 0)));
            }
        }
        
        if(TipoCancelacion.equals("PARCIAL")){
            JOptionPane.showMessageDialog(null, "Cancelacion parcial por contener servicios en venta \n"+ ServicioContenido, "Cancelacion Servicio", JOptionPane.INFORMATION_MESSAGE);
        }
        BaseDeDatos.VentaCancela(lbVentaId.getText(), TipoCancelacion);
        btVentaCancelarVenta.setEnabled(Boolean.FALSE);
        lbVentaStatus.setText("CANCELADA");
        LimpiarTablaVenta();
        VentaFormatoFecha();
    }
    
    private void VentaCancelaPausa(){
        BaseDeDatos.VentaCancela(lbVentaId.getText(), "CANCELAR");
        btVentaCancelarVenta.setEnabled(Boolean.FALSE);
        btVentaReanudarVenta.setEnabled(Boolean.FALSE);
        lbVentaStatus.setText("CANCELADA");
        LimpiarTablaVenta();
        VentaFormatoFecha();
    }
    
    private void VentaReanudaVenta(){
        BaseDeDatos.VentaCancela(lbPrincipalNoVenta.getText(), "ESPERA");
        DefaultTableModel Tabla = (DefaultTableModel) tbPrincipalVenta.getModel();
        lbPrincipalNoVenta.setText(lbVentaId.getText());
        BaseDeDatos.VentaCancela(lbPrincipalNoVenta.getText(), "PROCESO");
        lbPrincipalTotal.setText(lbVentaTotal.getText());
        PrincipalLimpiatablaVentas();
        
        int Registros = tbVentaContenido.getRowCount()-1;
        for(int i = 0; i <= Registros; i++){            
            Tabla.addRow(new String[]{String.valueOf(tbVentaContenido.getValueAt(i, 0)),String.valueOf(tbVentaContenido.getValueAt(i, 1)),String.valueOf(tbVentaContenido.getValueAt(i, 2)),String.valueOf(tbVentaContenido.getValueAt(i, 3))});
        }
        
        tbPrincipalVenta.setModel(Tabla);
        tbPrincipalVenta.setEnabled(true);
        Ventas.dispose();
    }
    
    //######################################## NOTAS ########################################
    //######################################## NOTAS ########################################
    //######################################## NOTAS ########################################
    
    private void NotasVentana(){
        Notas.setVisible(Boolean.TRUE);
        btNotasGenerar.setEnabled(Boolean.FALSE);
        spNotasDia.setValue(Integer.parseInt(Dia));
        cbNotasMes.setSelectedIndex(Integer.parseInt(Mes)-1);
        spNotasAno.setValue(Integer.parseInt(""+Ano.charAt(2)+Ano.charAt(3)));
        spNotasDia.setEnabled(Boolean.FALSE);
        cbNotasMes.setEnabled(Boolean.FALSE);
        spNotasAno.setEnabled(Boolean.FALSE);
    }
    
    private void NotasTipoConfiguracion(){
        btNotasGenerar.setEnabled(Boolean.TRUE);
        if(rbNotasDia.isSelected()){
            spNotasDia.setEnabled(Boolean.TRUE);
            cbNotasMes.setEnabled(Boolean.TRUE);
            spNotasAno.setEnabled(Boolean.TRUE);
        }
        
        if(rbNotasMes.isSelected()){
            spNotasDia.setEnabled(Boolean.FALSE);
            cbNotasMes.setEnabled(Boolean.TRUE);
            spNotasAno.setEnabled(Boolean.TRUE);
        }
        
        if(rbNotasAno.isSelected()){
            spNotasDia.setEnabled(Boolean.FALSE);
            cbNotasMes.setEnabled(Boolean.FALSE);
            spNotasAno.setEnabled(Boolean.TRUE);
        }
    }
    
    private void NotasGenerar(){
        String Dia = spNotasDia.getValue().toString();
        String Mes = cbNotasMes.getSelectedItem().toString();
        String Ano = "20"+spNotasAno.getValue().toString();
        
        String NombreArchivo = "NotaGenerada_"+Dia+Mes+Ano+"_"+lbHora.getText().replace(":", "")+lbAP.getText().toLowerCase();
        
        if(Dia.length() == 1){
            String DiaTemp = "0"+Dia;
            Dia = DiaTemp;
        }
        
        if(rbNotasDia.isSelected()){
            String Fecha = Dia+" de "+Mes+" del "+Ano;
            String NotaNo = BaseDeDatos.NotaNumero(Fecha);
            String Sentencia = "SELECT * FROM `venta` WHERE `fecha_venta` like '"+Fecha+"' AND `status_venta` like 'FINALIZADA'";
            if(!NotaNo.equals("")){
                String Encabezado = Fecha +"                 No. "+NotaNo;
                if(tfNotasLimite.getText().equals("")){
                    BaseDeDatos.NotasVentaFinalizada(Sentencia, Encabezado, NombreArchivo,"Generador");
                }else if(!(tfNotasLimite.getText().equals("")) && NotaValidaLimite()){
                    int Limite = Integer.parseInt(tfNotasLimite.getText());
                    BaseDeDatos.NotasVentasFinalizadaLimite(Sentencia, Encabezado, Limite, NombreArchivo,"Generador");
                }
            }else{
                JOptionPane.showMessageDialog(null, "ERROR : Dia sin registro", "Generar Nota",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(rbNotasMes.isSelected()){
            String Encabezado = "VENTAS CORRESPONDIENTES AL MES:"+Mes+" AÑO:"+Ano;
            String Sentencia = "SELECT * FROM `venta` WHERE `fecha_venta` like '%"+Mes+" del "+Ano+"' AND `status_venta` like 'FINALIZADA'";
            if(tfNotasLimite.getText().equals("")){
                BaseDeDatos.NotasVentaFinalizada(Sentencia, Encabezado, NombreArchivo,"Generador");
            }else if(!(tfNotasLimite.getText().equals("")) && NotaValidaLimite()){
                int Limite = Integer.parseInt(tfNotasLimite.getText());
                BaseDeDatos.NotasVentasFinalizadaLimite(Sentencia, Encabezado, Limite, NombreArchivo,"Generador");
            }
        }
        
        if(rbNotasAno.isSelected()){
            String Encabezado = "VENTAS CORRESPONDIENTES AL AÑO:"+Ano;
            String Sentencia = "SELECT * FROM `venta` WHERE `fecha_venta` like '%"+Ano+"' AND `status_venta` like 'FINALIZADA'";
            if(tfNotasLimite.getText().equals("")){
                BaseDeDatos.NotasVentaFinalizada(Sentencia, Encabezado, NombreArchivo,"Generador");
            }
            if(!tfNotasLimite.getText().equals("") && NotaValidaLimite()){
                int Limite = Integer.parseInt(tfNotasLimite.getText());
                BaseDeDatos.NotasVentasFinalizadaLimite(Sentencia, Encabezado, Limite, NombreArchivo,"Generador");
            }
        }
    }
    
    private boolean NotaValidaLimite(){
        boolean Status = Boolean.FALSE;
        try{
            int Limite = Integer.parseInt(tfNotasLimite.getText());
            Status = Boolean.TRUE;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR : Solo valores numericos ", "LIMITE",JOptionPane.ERROR_MESSAGE);
        }
        return Status;
    }
    
    private void NotasAutogenerar(){
        String No = BaseDeDatos.NotaNumero(lbFecha.getText());
        if(No.equals("")){
            BaseDeDatos.NotaCreaNueva(lbFecha.getText());
            No = BaseDeDatos.NotaNumero(lbFecha.getText());
        }
        String Sentencia = "SELECT * FROM `venta` WHERE `fecha_venta` like '"+lbFecha.getText()+"' AND `status_venta` like 'FINALIZADA'";
        String Encabezado = lbFecha.getText()+"                 No. "+No;
        String NombreArchivo = "Autogenerado"+lbFecha.getText().replace(" ", "")+"No"+No;
        BaseDeDatos.NotasVentaFinalizada(Sentencia, Encabezado, NombreArchivo, "Auto");
    }
    
    //######################################## CONFIGURACION ########################################
    //######################################## CONFIGURACION ########################################
    //######################################## CONFIGURACION ########################################
    
    private void ConfiguracionVentana(){
        Configuracion.setVisible(Boolean.TRUE);
        String[] PRNU = BaseDeDatos.ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
        tfConfiguraPorcentaje.setText(PRNU[0]);
        tfConfiguracionDireccionRespaldo.setText(PRNU[1]);
        tfConfiguracionDireccionNotas.setText(PRNU[2]);
        tfConfiguracionDireccionReportes.setText(PRNU[3]);
        tfConfiguracionDireccionRespaldo.setEditable(Boolean.FALSE);
        tfConfiguracionDireccionNotas.setEditable(Boolean.FALSE);
        tfConfiguracionDireccionReportes.setEditable(Boolean.FALSE);
    }
    
    private void ConfiguracionValidaPorcenaje(String Porcentaje){
        try{
            float NumeroTest = Float.parseFloat(Porcentaje);
            JOptionPane.showMessageDialog(this, "Comisión actualizada correctamente", "Actualiza comisión",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            BaseDeDatos.ConfiguracionAtualizaComision(Porcentaje);
            String[] P = BaseDeDatos.ConfiguracionValores();
            tfConfiguraPorcentaje.setText(P[0]);
       }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error valor de Comisión", "Actualiza comisión",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private void ConfiguracionDireccionRespaldo(){
        JFileChooser FC = new JFileChooser();
        FC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int Op = FC.showOpenDialog(this);
        String Ruta = null;
        if(Op == JFileChooser.APPROVE_OPTION){
            Ruta = FC.getSelectedFile().getPath();
            //System.err.println(Ruta);
            Ruta = CambiaDiagonal(Ruta);
            //System.out.println(Ruta);
            tfConfiguracionDireccionRespaldo.setText(Ruta);
            BaseDeDatos.ConfiguracionDireccionRespaldo(Ruta);
            JOptionPane.showMessageDialog(this, "Salida de respaldo actualizada correctamente", "Actualiza Respaldo",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            String[] R = BaseDeDatos.ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
            tfConfiguracionDireccionRespaldo.setText(R[1]);
        }
    }
    
    private void ConfiguracionDireccionNotas(){
        JFileChooser FC = new JFileChooser();
        FC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int Op = FC.showOpenDialog(this);
        String Ruta = null;
        if(Op == JFileChooser.APPROVE_OPTION){
            Ruta = FC.getSelectedFile().getPath();
            Ruta = CambiaDiagonal(Ruta);
            tfConfiguracionDireccionNotas.setText(Ruta);
            BaseDeDatos.ConfiguracionDireccionNotas(Ruta);
            JOptionPane.showMessageDialog(this, "Salida de notas actualizada correctamente", "Actualiza Notas",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            String[] N = BaseDeDatos.ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
            tfConfiguracionDireccionRespaldo.setText(N[2]);
        }
    }
    
    private void ConfiguracionDireccionReportes(){
        JFileChooser FC = new JFileChooser();
        FC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int Op = FC.showOpenDialog(this);
        String Ruta = null;
        if(Op == JFileChooser.APPROVE_OPTION){
            Ruta = FC.getSelectedFile().getPath();
            Ruta = CambiaDiagonal(Ruta);
            tfConfiguracionDireccionReportes.setText(Ruta);
            BaseDeDatos.ConfiguracionDireccionReportes(Ruta);
            JOptionPane.showMessageDialog(this, "Salida de reportes actualizada correctamente", "Actualiza Reportes",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            String[] U = BaseDeDatos.ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
            tfConfiguracionDireccionReportes.setText(U[3]);
        }
    }
    
    private String CambiaDiagonal(String CadenaCambiada){
        String Tem = "";
        int Conver;
        char Dato;
        for(int x=0;x<CadenaCambiada.length();x++){            
            Dato = CadenaCambiada.charAt(x);
            Conver = Dato;
            if(Conver==92){
                Tem = Tem+'/';
            }else{
                Tem = Tem + CadenaCambiada.charAt(x);
            }
        }
        CadenaCambiada = Tem;
        return CadenaCambiada; 
    }
    
    private void ConfiguracionGenerarRespaldo(){
        String[] R = BaseDeDatos.ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
        try{
            String usuario = "root";
            String password = "root1234";
            String dbName = "puntoventa";
            String ruta = R[1]+"\\"; //Este codigo esta dentro de una funcion y ella recibe como parametro a ubicacion el cual obtendo de un chooser (ventana de seleccion de archivos similar a la de windows), que la utilizo para que el usuario seleccione la ruta donde desea guardar el archivo.
            ruta +="puntoventa";
            Runtime rt = Runtime.getRuntime();
            String command = "mysqldump --opt -c -u"+usuario+" -p"+password+" "+dbName+" -r "+ruta+".sql";
            rt.exec(command);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Salida de respaldo. Error: "+e, "Respaldo",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private void ConfiguracionRestauraDB(){
        //BaseDeDatos.ConfiguracionRestauracionDB();
        JFileChooser FC = new JFileChooser();
        FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int Op = FC.showOpenDialog(this);
        String Ruta = null;
        if(Op == JFileChooser.APPROVE_OPTION){
            Ruta = FC.getSelectedFile().getPath();
            Ruta = CambiaDiagonal(Ruta);
            if(!Ruta.endsWith("puntoventa.sql")){
                JOptionPane.showMessageDialog(this," Error: \n Archivo erroneo", "RestauraDB",JOptionPane.INFORMATION_MESSAGE,IconoError);
                ConfiguracionRestauraDB();
            }
            String usuario = "root";
            String password = "root1234";
            String dbName = "puntoventa";
            String command = "mysql -u"+usuario+" -p"+password+" "+dbName;
            System.err.println(command);
            try{
                Process Ps = Runtime.getRuntime().exec(command);
                BaseDeDatos.ConfiguracionLimpiaDB();
                OutputStream Os = Ps.getOutputStream();
                FileInputStream File = new FileInputStream(Ruta);
                byte [] Buffer = new byte[1000];
                int Leido = File.read(Buffer);
                while(Leido>0){
                    Os.write(Buffer, 0, Leido);
                    Leido = File.read(Buffer);
                }
                Os.flush();
                Os.close();
                File.close();
                JOptionPane.showMessageDialog(this," Restauracion exitosa", "RestauraDB",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this," Error: Archivo erroneo \n "+e, "RestauraDB",JOptionPane.INFORMATION_MESSAGE,IconoError);
            }
        }
    }
    
    //######################################## AGREGA RAPIDO ########################################
    //######################################## AGREGA RAPIDO ########################################
    //######################################## AGREGA RAPIDO ########################################
    
    private void AgregarProductoRapido(){
        AgregaProdutoRapido.setVisible(Boolean.TRUE);
        lbAgregarRapidoCodigo.setText(CodigoBarras);
        tfAgregarRapidoNombre.setText("");
        tfAgregarRapidoNombre.grabFocus();
        tfAgregarRapidoPrecio.setText("");
    }
    
    private void AgregarRapidoNombre(KeyEvent evt){
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                AgregaProdutoRapido.dispose();
                break;
            case KeyEvent.VK_ENTER:
                tfAgregarRapidoPrecio.grabFocus();
                break;
            default:
                break;
        }
    }
    
    private void AgregarRapidoPrecio(KeyEvent evt){
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                AgregaProdutoRapido.dispose();
                break;
            case KeyEvent.VK_ENTER:
                AgregarRapidoAgrega();
                break;
            default:
                break;
        }
    }
    
    private void AgregarRapidoAgrega(){
        String Status = AgregarRapidoValida();
        if(Status.equals("OK")){
            BaseDeDatos.ProductoAgregarNuevo(lbAgregarRapidoCodigo.getText(), tfAgregarRapidoNombre.getText().toUpperCase(), tfAgregarRapidoPrecio.getText(), "UNITARIO", "0", "0", "00000000");
            JOptionPane.showMessageDialog(this, tfAgregarRapidoNombre.getText().toUpperCase() + "\n Agregado Exitosamente", "Agregar Producto",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            AgregaProdutoRapido.dispose();
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Agregar Producto",JOptionPane.INFORMATION_MESSAGE,IconoError);
            tfAgregarRapidoNombre.grabFocus();
        }
    }
    
    private String AgregarRapidoValida(){
        String Status = "";
        
        if(tfAgregarRapidoNombre.getText().equals("")){
            Status = Status + "Nombre porducto bacio \n";
        }else if(tfAgregarRapidoNombre.getText().length()>30){
            Status = Status + "Nombre porducto demaciado largo 30 Caracteres \n";
        }
        
        if(tfAgregarRapidoPrecio.getText().equals("")){
            Status = Status + "Precio producto bacio \n";
        }else{
            if(!AgregaRapidoValidaPrecio(tfAgregarRapidoPrecio.getText())){
                Status = Status + "Formato de precio incorrecto (8.00 , 99.50 , 10.50) \n";
            }
        }
        
        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    private boolean AgregaRapidoValidaPrecio(String Precio){
        //System.out.println(Precio);
        boolean Status = true;
        String PrecioFormato = "";
        try{
            float FormatoFloat = Float.parseFloat(Precio);
            PrecioFormato = ""+FormatoFloat;
            PrecioFormato = BaseDeDatos.PrincipalCorrijePrecioAgranel(PrecioFormato.concat("0"));
            tfAgregarRapidoPrecio.setText(PrecioFormato);
        }catch(Exception e){
            Status = false;
        }
        return Status;
    }
    
    //######################################## SALIDA ########################################
    //######################################## SALIDA ########################################
    //######################################## SALIDA ########################################
    
    private void Salidas(){
        Salidas.setVisible(Boolean.TRUE);
        tfSalidasMonto.setText("");
        tfSalidasMotivo.setText("");
    }
    
    private void SalidaListenMotivo(KeyEvent evt){
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                Salidas.dispose();
                break;
            case KeyEvent.VK_ENTER:
                tfSalidasMonto.grabFocus();
                break;
            default:
                break;
        }
    }
    
    private void SalidaListenMonto(KeyEvent evt){
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                Salidas.dispose();
                break;
            case KeyEvent.VK_ENTER:
                tfSalidasMonto.grabFocus();
                SalidaFinaliza();
                break;
            default:
                break;
        } 
    }
    
    private void SalidaFinaliza(){
        String Status = SalidaValida();
        if(Status.equals("OK")){
            //BaseDeDatos.ProductoAgregarNuevo(lbAgregarRapidoCodigo.getText(), tfAgregarRapidoNombre.getText().toUpperCase(), tfAgregarRapidoPrecio.getText(), "UNITARIO", "0", "0", "00000000");
            String Empleado = lbPrincipalUsuario.getText();
            String Fecha = lbFecha.getText();
            String Motivo = tfSalidasMotivo.getText();
            String Monto = tfSalidasMonto.getText();
            BaseDeDatos.SalidaAgregar(Empleado, Fecha, Motivo, Monto);
            JOptionPane.showMessageDialog(this," Registro de salida exitoso", "Salida",JOptionPane.INFORMATION_MESSAGE,IconoHecho);
            Salidas.dispose();
        }else{
            JOptionPane.showMessageDialog(this," Error: \n"+Status, "Salida",JOptionPane.INFORMATION_MESSAGE,IconoError);
            tfSalidasMotivo.grabFocus();
        } 
    }
    
    private String SalidaValida(){
        String Status = "";
        
        if(tfSalidasMotivo.getText().equals("")){
            Status = Status + "Sin motivo bacio \n";
        }else if(tfSalidasMotivo.getText().length()>40){
            Status = Status + "descripcion del motivo demaciado largo 40 Caracteres \n";
        }
        
        if(tfSalidasMonto.getText().equals("")){
            Status = Status + "Monto bacio \n";
        }else{
            if(!SalidaValidaMonto(tfSalidasMonto.getText())){
                Status = Status + "Formato de monto incorrecto (8.00 , 99.50 , 10.50) \n";
            }
        }
        
        if(Status.equals("")){
            Status = "OK";
        }
        return Status;
    }
    
    private boolean SalidaValidaMonto(String Precio){
        //System.out.println(Precio);
        boolean Status = true;
        String PrecioFormato = "";
        try{
            float FormatoFloat = Float.parseFloat(Precio);
            PrecioFormato = ""+FormatoFloat;
            PrecioFormato = BaseDeDatos.PrincipalCorrijePrecioAgranel(PrecioFormato.concat("0"));
            tfSalidasMonto.setText(PrecioFormato);
        }catch(Exception e){
            Status = false;
        }
        return Status;
    }
    
    //######################################## GESTOR SALIDA ########################################
    //######################################## GESTOR SALIDA ########################################
    //######################################## GESTOR SALIDA ########################################
    
    private void GestorSalidas(){
        SalidasLimpiatablaSalidas();
        GestorSalidas.setVisible(Boolean.TRUE);
        Salidas.dispose();
        cbSalidaMes.setSelectedIndex(Integer.parseInt(Mes)-1);
        spSalidaAno.setValue(Integer.parseInt(""+Ano.charAt(2)+Ano.charAt(3)));
        BaseDeDatos.SalidasLlenarTablaSalidas(tbSalidas,"%"+cbSalidaMes.getSelectedItem().toString()+" del 20"+spSalidaAno.getValue().toString());
    }
    
    private void SalidasLimpiatablaSalidas(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tbSalidas.getModel();
            int filas=tbSalidas.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    private void SalidasBuscarSalidasFechas(){
        SalidasLimpiatablaSalidas();
        BaseDeDatos.SalidasLlenarTablaSalidas(tbSalidas,"%"+cbSalidaMes.getSelectedItem().toString()+" del 20"+spSalidaAno.getValue().toString());
    }
    
    //######################################## INVENTARIO RAPIDO ########################################
    //######################################## INVENTARIO RAPIDO ########################################
    //######################################## INVENTARIO RAPIDO ########################################
    
    private void InventarioRapido(){
        InventarioRapido.setVisible(Boolean.TRUE);
        tfInventarioRCodigoBarras.setText("");
        tfInventarioRCantidadAgregar.setText("");
        lbInventarioRCantidadProducto.setText("0");
        lbInventarioRNombreProducto.setText("Nombre del Producto");
        lbInventarioRCantidadProductoTotal.setText("0");
        tfInventarioRCodigoBarras.grabFocus();
        tfInventarioRCodigoBarras.setEditable(Boolean.TRUE);
        tfInventarioRCodigoBarras.setEnabled(Boolean.TRUE);
    }
    
    private void InventarioRapido(KeyEvent evt){
        int TeclaPrecionada = evt.getKeyCode();
        char ValorTecla = evt.getKeyChar();
        //System.out.print(TeclaPrecionada+"=");
        //System.out.println(ValorTecla);
        if(TeclaPrecionada == 10){
           String[] Valores = BaseDeDatos.InventarioRapido(tfInventarioRCodigoBarras.getText());
           if(!Valores[0].equals("")){
                lbInventarioRNombreProducto.setText(Valores[0]);
                lbInventarioRCantidadProducto.setText(Valores[1]);
                tfInventarioRCantidadAgregar.grabFocus();
                tfInventarioRCodigoBarras.setEditable(Boolean.FALSE);
                tfInventarioRCodigoBarras.setEnabled(Boolean.FALSE);
           }else{
               lbInventarioRNombreProducto.setText("Producto no localizado");
               tfInventarioRCodigoBarras.setText("");
               tfInventarioRCodigoBarras.grabFocus();
           }
        }
    }
    
    private void InventarioRapidoCantidad(){
        String Cadena = tfInventarioRCantidadAgregar.getText();
        String CadenaSustituir = "";
        for (int i = 0; i <= Cadena.length()-1; i++) {
            if(Cadena.charAt(i) == '0' |Cadena.charAt(i) == '1' |Cadena.charAt(i) == '2' |Cadena.charAt(i) == '3' |Cadena.charAt(i) == '4' |Cadena.charAt(i) == '5' |Cadena.charAt(i) == '6' |Cadena.charAt(i) == '7'|Cadena.charAt(i) == '8' |Cadena.charAt(i) == '9'){
                CadenaSustituir = CadenaSustituir + Cadena.charAt(i);
            }
        }
        tfInventarioRCantidadAgregar.setText(CadenaSustituir);        
        int CalcTotal = 0;
        if(tfInventarioRCantidadAgregar.getText().equals("")){
            CalcTotal = 0 + Integer.parseInt(lbInventarioRCantidadProducto.getText());
        }else{
            CalcTotal = Integer.parseInt(tfInventarioRCantidadAgregar.getText()) + Integer.parseInt(lbInventarioRCantidadProducto.getText());
        }
        lbInventarioRCantidadProductoTotal.setText(""+CalcTotal);
    }
    
    private void InventarioRAgregarCantidad(){
        BaseDeDatos.InventarioRAgregarCantidadProducto(tfInventarioRCodigoBarras.getText(), lbInventarioRCantidadProductoTotal.getText());
        InventarioRapido.dispose();
        InventarioRapido();
    }
    
    
    private void AgergarMasiba(){
                JFileChooser FC = new JFileChooser();
        FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int Op = FC.showOpenDialog(this);
        String Ruta = null;
        if(Op == JFileChooser.APPROVE_OPTION){
            Ruta = FC.getSelectedFile().getPath();
            Ruta = CambiaDiagonal(Ruta);
            BaseDeDatos.AgregarMasiba(Ruta);
        }
    }
    
    
}