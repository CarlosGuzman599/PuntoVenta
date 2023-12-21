/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author CARLITOS
 */
public class BaseDeDatos {
    
    private static final ClaseConexionMySQL ClaseConMySQL = new ClaseConexionMySQL();
    private static Connection Con = null;
    Icon IconoHecho = new ImageIcon("src\\Imagenes\\notification_done.png");
    Icon IconoError = new ImageIcon("src\\Imagenes\\notification_error.png");
   
    //######################################## LOG IN ########################################
    //######################################## LOG IN ########################################
    //######################################## LOG IN ########################################
    
    public void LlenaComboBoxLogIn(JComboBox cbLogIUsuarios){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `usuarios`");
            while(Rt.next()){
                cbLogIUsuarios.addItem(Rt.getString(2));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
        }
    }
    
    public Boolean ValidaLogIn(String Usuario, String Contra){
        Boolean Estado = Boolean.FALSE;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `usuarios`");
            while(Rt.next()){
                if(Rt.getString(2).equals(Usuario) && Rt.getString(3).equals(Contra)){
                    Estado = Boolean.TRUE;
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
        }
        return Estado;
    }
    
    float TotalVenta = 0;
    float TotalComisiones = 0;
    float TotalPagoTarjeta = 0;
    float TotalEfectivo = 0;
    float Salidas = 0;
    
    public void LongInReporteSalir(String Ususario, String Fecha, String NombreArchivo){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `venta`.`status_venta` = 'FINALIZADA' AND `venta`.`fecha_venta` = '"+Fecha+"' AND `venta`.`cajero_venta` = '"+Ususario+"'");
            while(Rt.next()){
                //System.out.println(Rt.getString("id_venta") +". $ "+Rt.getString("total_venta"));
                LogInCalculosReporte(Rt.getString("id_venta"));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        //System.out.println("Total Pago Tarjeta = $"+NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalPagoTarjeta+"0")));
        //System.out.println("Total Comisiones = $"+NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalComisiones+"0")));
        TotalEfectivo = TotalVenta - TotalPagoTarjeta;
        //System.out.println("Total Efectivo = $"+NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalEfectivo+"0")));
        //System.out.println("Total = $"+NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalVenta+"0")));
        logInSalidas(Ususario, Fecha);
        ImprimirTicketReporteUsuario(Fecha, Ususario, NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalPagoTarjeta+"0"))
                , NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalComisiones+"0"))
                , NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalVenta+"0"))
                , NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalEfectivo+"0"))
                , NombreArchivo);
        TotalComisiones = 0;
        TotalEfectivo = 0;
        TotalPagoTarjeta = 0;
        TotalVenta = 0;
        Salidas = 0;
    }
    
    private void logInSalidas(String Usuario, String Fecha){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `salidas` WHERE `salidas`.`empleado_salida` = '"+Usuario+"' AND `salidas`.`fecha_salida` = '"+Fecha+"' AND `salidas`.`status_salida` = 'FINALIZADO';");
            while(Rt.next()){
                Salidas = Salidas + Float.parseFloat(Rt.getNString("monto_salida"));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - logInSalidas:"+e);
        }
    }
    
    private void LogInCalculosReporte(String Venta){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas`.`ventas_venta` = '"+Venta+"';");
            while(Rt.next()){
                if(Rt.getString("ventas_productonombre").contains("%Tarjeta$")){
                    //System.out.println(">"+Rt.getString("ventas_productonombre"));
                    TotalComisiones = TotalComisiones + Float.parseFloat(Rt.getString("ventas_importe"));
                    LogInCalculaPagoTargeta(Rt.getString("ventas_productonombre"));
                }else{
                    TotalVenta = TotalVenta + Float.parseFloat(Rt.getString("ventas_importe"));
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
    }
    
    private void LogInCalculaPagoTargeta(String NombreProducto){
        String FloatRelativo = "";
        int ban = 0;
        for (int i = 0; i < NombreProducto.length()-1; i++) {
            if(ban == 1){
                FloatRelativo = FloatRelativo + NombreProducto.charAt(i);
            }
            if(NombreProducto.charAt(i) == '$'){
                ban = 1;
            }
        }
        TotalPagoTarjeta = TotalPagoTarjeta + Float.parseFloat(FloatRelativo);
    }
    
    private void ImprimirTicketReporteUsuario(String Fecha,String Usuario, String PagoTarjeta, String PagoComiciones,String PagoTotal, String TotalEfectivo, String NombreArchivo){
        String[] U = ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas  U[3]
        String Salidas2 = NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+this.Salidas+"0"));
        float EtS = Float.parseFloat(TotalEfectivo) - this.Salidas;
        String EtS2 = NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+EtS+"0"));      
        String Dir = U[3]+"\\"+NombreArchivo+".txt";
        PrinterMatrix printer = new PrinterMatrix();
        Extenso e = new Extenso();
        printer.setOutSize(25, 10);
        //System.out.println("-----------"+CuentaProductosVenta(NoVenta));
        printer.printTextWrap(1, 1, 0, 80, "      ABARROTES Y CEREALES");
        printer.printTextWrap(3, 1, 0, 80, "             REPORTE      ");
        printer.printTextWrap(4, 1, 0, 80, "   ---------------------------");
        printer.printTextWrap(5, 1, 0, 80, " Fecha: "+Fecha);
        printer.printTextWrap(6, 1, 0, 80, " Usuario: "+Usuario);
        printer.printTextWrap(7, 1, 0, 80, "   ---------------------------");
        
        printer.printTextWrap(9, 1, 0, 80, "Pagos con tarjeta: $" + PagoTarjeta);
        printer.printTextWrap(10, 1, 0, 80,"*Comisiones:$" + PagoComiciones);
        
        printer.printTextWrap(12, 1, 0, 80, "Pagos con efectivo:$" + TotalEfectivo);
        printer.printTextWrap(13, 1, 0, 80, "Salidas:          -$" + Salidas2);
        
        printer.printTextWrap(15, 1, 0, 80, "Total Efectivo:    $" + EtS2);
        printer.printTextWrap(16, 1, 0, 80,"Ventas de usuario: $" + PagoTotal);
        
        printer.printTextWrap(18, 1, 0, 80, "   ---------------------------");
        printer.printTextWrap(19, 1, 0, 80, "       *** HASTA LUEGO ***");
        
        printer.toFile(Dir);
        FileInputStream inputStream = null;
        
        try {
            inputStream = new FileInputStream(Dir);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null," Error: No se pudo guardar .txt", "Reporte Usuario",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
        if (inputStream == null) {
            return;
        }
        
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        
        System.err.println("ImprimirTicketReporteUsuario---");
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null," Error: No existen impresoras instaladas", "IMPRESORA",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }

    //######################################## BUSCAR ########################################
    //######################################## BUSCAR ########################################
    //######################################## BUSCAR ########################################
    
    public void BurcarProductoId(JTable tbBuscar, String Producto){
        DefaultTableModel Tabla = (DefaultTableModel) tbBuscar.getModel();
        Producto = Producto.concat("%");
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` where id_producto like "+'"'+Producto+'"'+" ORDER BY `producto`.`nombre_producto` ASC");
            while(Rt.next()){
                if(!(Rt.getString("id_producto").equals("COMICION"))){
                    Tabla.addRow(new String[]{Rt.getString("id_producto"),Rt.getString("nombre_producto"),Rt.getString("precio_producto"),Rt.getString("categoria_producto")});
                }
            }
            Tabla.addRow(new String[]{"",""});
            Rt.close();
            St.close();
            Con.close();
            tbBuscar.setModel(Tabla);
            tbBuscar.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoId :"+e);
        }
    }
    
    public void BurcarProductoNombre(JTable tbBuscar, String Producto){
        DefaultTableModel Tabla = (DefaultTableModel) tbBuscar.getModel();
        Producto = "%"+Producto.concat("%");
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` where nombre_producto like "+'"'+Producto+'"'+" ORDER BY `producto`.`nombre_producto` ASC");
            while(Rt.next()){
                if(!(Rt.getString("id_producto").equals("COMICION"))){
                    Tabla.addRow(new String[]{Rt.getString("id_producto"),Rt.getString("nombre_producto"),Rt.getString("precio_producto"),Rt.getString("categoria_producto")});
                }
            }
            Tabla.addRow(new String[]{"",""});
            Rt.close();
            St.close();
            Con.close();
            tbBuscar.setModel(Tabla);
            tbBuscar.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    //######################################## PRODUCTOS ########################################
    //######################################## PRODUCTOS ########################################
    //######################################## PRODUCTOS ########################################
    
    public void ProductosLlenarTablaTodos(JTable tbProductos){
        DefaultTableModel Tabla = (DefaultTableModel) tbProductos.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` ORDER BY `producto`.`nombre_producto` ASC");
            while(Rt.next()){
                if(!(Rt.getString("id_producto").equals("COMICION"))){
                    Tabla.addRow(new String[]{Rt.getString("id_producto"),Rt.getString("disponibilidad_producto"),Rt.getString("nombre_producto"),Rt.getString("precio_producto"),Rt.getString("categoria_producto"),Rt.getString("stock_producto")});
                }
                
            }
            tbProductos.setModel(Tabla);
            tbProductos.setEnabled(true);
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        }
    }
    
    public boolean ProductoExistente(String Producto){
        boolean Stattus = false;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `id_producto` LIKE '"+Producto+"'");
            if(Rt.first()){
                Stattus = Boolean.TRUE;
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        }
        return  Stattus;
    }
    
    public void ProductosLlenarCBCategoria(JComboBox cbProductoCategoria){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `categoria`");
            while(Rt.next()){
                cbProductoCategoria.addItem(Rt.getString(1));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
        }
    }
    
    public void ProductoAgregarNuevo(String ID, String Nombre, String Precio, String Categoria, String Disponiviliad, String Stock, String Fecha){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `producto` (`id_producto`, `disponibilidad_producto`, `nombre_producto`, `precio_producto`, `categoria_producto`, `stock_producto`, `fecha_producto`) VALUES ('"+ID+"', '"+Disponiviliad+"', '"+Nombre+"', '"+Precio+"', '"+Categoria+"', '"+Stock+"', '"+Fecha+"');");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("CrearPoblacion - ERROR: "+e);
        }
    }
    
    public void ProductosEliminar(String IDProducto){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("DELETE FROM `producto` WHERE `producto`.`id_producto` = '"+IDProducto+"'");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosEliminar - Error: "+e);
        }
    }
    
    public void ProductosModificar(String ID, String Disponivilidad, String Nombre, String Precio,String Categoria, String Stock,String Fecha){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `producto` SET `disponibilidad_producto` = '"+Disponivilidad+"', `nombre_producto` = '"+Nombre+"', `precio_producto` = '"+Precio+"', `categoria_producto` = '"+Categoria+"', `stock_producto` = '"+Stock+"', `fecha_producto` = '"+Fecha+"' WHERE `producto`.`id_producto` = '"+ID+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
    }
    
    public void ProductoBuscarId(JTable tbProductos,String Producto){
        DefaultTableModel Tabla = (DefaultTableModel) tbProductos.getModel();
        Producto = Producto.concat("%");
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` where id_producto like "+'"'+Producto+'"'+" ORDER BY `producto`.`nombre_producto` ASC");
            while(Rt.next()){
                if(!(Rt.getString("id_producto").equals("COMICION"))){
                    Tabla.addRow(new String[]{Rt.getString("id_producto"),Rt.getString("disponibilidad_producto"),Rt.getString("nombre_producto"),Rt.getString("precio_producto"),Rt.getString("categoria_producto"),Rt.getString("stock_producto")});
                } 
            }
            Tabla.addRow(new String[]{"",""});
            Rt.close();
            St.close();
            Con.close();
            tbProductos.setModel(Tabla);
            tbProductos.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    public void ProductoBuscarNombre(JTable tbProductos, String Producto){
        DefaultTableModel Tabla = (DefaultTableModel) tbProductos.getModel();
        Producto ="%"+Producto.concat("%");
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` where nombre_producto like "+'"'+Producto+'"');
            while(Rt.next()){
                if(!(Rt.getString("id_producto").equals("COMICION"))){
                    Tabla.addRow(new String[]{Rt.getString("id_producto"),Rt.getString("disponibilidad_producto"),Rt.getString("nombre_producto"),Rt.getString("precio_producto"),Rt.getString("categoria_producto"),Rt.getString("stock_producto")});
                } 
            }
            Tabla.addRow(new String[]{"",""});
            Rt.close();
            St.close();
            Con.close();
            tbProductos.setModel(Tabla);
            tbProductos.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    public String ProductoFecha(String ID){
        String Feccha = "";
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `id_producto` LIKE '"+ID+"'");
            if(Rt.first()){
                Feccha = Rt.getString("fecha_producto");
            }
            Rt.close();
            St.close();
            Con.close();
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar ruta, informacion no obtenia","CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("ProductoFecha - ERROR SIN RESPUESTA: "+e);
        } 
        return Feccha;
    }
    
    //######################################## PRINCIPAL ########################################
    //######################################## PRINCIPAL ########################################
    //######################################## PRINCIPAL ########################################
    
    public String[] AgregarCodigoBarras(String Codigo){
        String Nombre = "NO";
        String Precio = "NO";
        String Categoria = "NO";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` where id_producto = '"+Codigo+"';");
            while(Rt.next()){
                Nombre = Rt.getString("nombre_producto");
                Precio = Rt.getString("precio_producto");
                Categoria = Rt.getString("categoria_producto");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - AgregarCodigoBarras :"+e);
        }
        return new String[]{Nombre,Precio,Categoria};
    }
    
    public String PrincipalVentaProceso(){
        String Numero = "";
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `status_venta` LIKE 'PROCESO'");
            if(Rt.first()){
                Numero = Rt.getString("id_venta");
            }
            Rt.close();
            St.close();
            Con.close();
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar ruta, informacion no obtenia","CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("PrincipalVentaProceso - ERROR SIN RESPUESTA: "+e);
        }
        return Numero;
    }
    
    public void PrincipalVerificaVentas(String Venta, String ID, String Producto, String Precio, String Categoria){
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas_venta` LIKE '"+Venta+"' AND `ventas_productoid` LIKE '"+ID+"'");
            if(Rt.first()){
                String Cantidad = ""+(Integer.parseInt(Rt.getString("cantidad_ventas"))+1);
                PrincipalActualizaVenta(Rt.getString("id_ventas"),Cantidad, Precio, Categoria);
            }else{
                PrincipalAgregaVentas(Venta, ID, Producto, Precio, Categoria);
            }
            Rt.close();
            St.close();
            Con.close();
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar ruta, informacion no obtenia","CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("PrincipalVerificaVentas - ERROR SIN RESPUESTA: "+e);
        }  
    }
    
    private void PrincipalAgregaVentas(String Venta, String ID, String Producto, String Precio, String Categoria){
        String Importe = "";
        if(Categoria.equals("AGRANEL")){
            float PrecioNumerico = Float.parseFloat(Precio) / 1000;
            Importe = ""+PrecioNumerico+"0";
        }else{
            Importe = Precio;
        }
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `ventas` (`ventas_venta`,`ventas_productoid`,`cantidad_ventas`,`ventas_productonombre`,`ventas_precio`,`ventas_importe`,`ventas_categoria`) VALUES ('"+Venta+"', '"+ID+"','1','"+Producto+"','"+Precio+"','"+Importe+"','"+Categoria+"')");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("PrincipalAgregaVentas - ERROR: "+e);
        }
    }
    
    public void PrincipalActualizaVenta(String IDVentas, String Cantidad, String Precio, String Categoria){
        int CantidadNumero = Integer.parseInt(Cantidad);
        Cantidad = String.valueOf(CantidadNumero);
        float PrecioNuemero = Float.parseFloat(Precio);
        float ImporteNumero = CantidadNumero * PrecioNuemero;
        String Importe = String.valueOf(ImporteNumero).concat("0");
        
        if(Categoria.equals("AGRANEL")){
            ImporteNumero = (PrecioNuemero/1000) * CantidadNumero;
            Importe = PrincipalCorrijePrecioAgranel(String.valueOf(ImporteNumero).concat("0"));
        }
        
        
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `ventas` SET `cantidad_ventas` = '"+Cantidad+"', `ventas_importe` = '"+Importe+"' WHERE `ventas`.`id_ventas` = "+IDVentas+";");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("PrincipalAgregaVentas - ERROR: "+e);
        }
    }
    
    public void PrincipalLlenaTablaVentas(JLabel lbPrincipalTotal, JTable tbPrincipalVenta, String Venta){
        DefaultTableModel Tabla = (DefaultTableModel) tbPrincipalVenta.getModel();
        float TotalVenta = 0; 
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` where ventas_venta like "+'"'+Venta+'"');
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("cantidad_ventas"),Rt.getString("ventas_productonombre"),Rt.getString("ventas_precio"),Rt.getString("ventas_importe")});
                TotalVenta = TotalVenta + Float.parseFloat(Rt.getString("ventas_importe"));
            }
            Rt.close();
            St.close();
            Con.close();
            tbPrincipalVenta.setModel(Tabla);
            tbPrincipalVenta.setEnabled(true);
            lbPrincipalTotal.setText("$ "+PrincipalCorrijePrecioAgranel(TotalVenta+"0"));
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - PrincipalLlenaTablaVentas :"+e);
        }
    }
    
    public String[] PrincipalIDVentas(String IDVenta, String Producto){
        String IDVentas = "";
        String Categoria = "";
        
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas_venta` LIKE '"+IDVenta+"' and `ventas_productonombre` LIKE '"+Producto+"';");
            if(Rt.first()){
                IDVentas = Rt.getString("id_ventas");
                Categoria = Rt.getString("ventas_categoria");
            }
            Rt.close();
            St.close();
            Con.close();
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar ruta, informacion no obtenia","CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("PrincipalIDVentas - ERROR SIN RESPUESTA: "+e);
        }
        return  new String[]{IDVentas,Categoria};
    }
    
    public String PrincipalCorrijePrecioAgranel(String Precio){
        String Correcion = "";
        
        for (int i = 0; i < Precio.length()-1; i++) {
            if(Precio.charAt(i) == '.'){
                Correcion = Correcion + Precio.charAt(i)+Precio.charAt(i+1)+Precio.charAt(i+2);
                break;
            }else{
                Correcion = Correcion + Precio.charAt(i);
            }
        }
        return Correcion;
    }
    
    public void PrincipalEliminarVentas(String IDVentas){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("DELETE FROM `ventas` WHERE `ventas`.`id_ventas` = '"+IDVentas+"'");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("PrincipalEliminarVentas - Error: "+e);
        }
    }
    
    public void PrincipalActualizaNotificacionesCaducos(JTextArea taPrincipalNotificaiones, String FechaActual) throws ParseException{
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto`");
            while(Rt.next()){
                if(!(Rt.getString("fecha_producto").equals("00000000")) && !(Rt.getString("disponibilidad_producto").equals("0"))){
                    //"yyyy-MM-dd"
                    String FechaCaducidadSinFormato = Rt.getString("fecha_producto");
                    
                    String FechaCaducidadConFormato = ""+FechaCaducidadSinFormato.charAt(4)+FechaCaducidadSinFormato.charAt(5)+FechaCaducidadSinFormato.charAt(6)+FechaCaducidadSinFormato.charAt(7)+"-"+FechaCaducidadSinFormato.charAt(2)+FechaCaducidadSinFormato.charAt(3)+"-"+FechaCaducidadSinFormato.charAt(0)+FechaCaducidadSinFormato.charAt(1);
                    String FechaActualConFormato = ""+FechaActual.charAt(4)+FechaActual.charAt(5)+FechaActual.charAt(6)+FechaActual.charAt(7)+"-"+FechaActual.charAt(2)+FechaActual.charAt(3)+"-"+FechaActual.charAt(0)+FechaActual.charAt(1);
                    
                    int DiasParaCaducar = DiasHataCaducidad(FechaActualConFormato,FechaCaducidadConFormato);
                    
                    if(DiasParaCaducar<1){
                        taPrincipalNotificaiones.append("       - "+Rt.getString("nombre_producto") + "\n");
                        taPrincipalNotificaiones.append("         PRODUCTO CADUCO CON "+DiasParaCaducar + " DIAS \n");
                        taPrincipalNotificaiones.append("\n");
                    }
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        } 
    }
    
    public void PrincipalActualizaNotificacionesPorCaducar(JTextArea taPrincipalNotificaiones, String FechaActual) throws ParseException{
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto`");
            while(Rt.next()){
                if(!(Rt.getString("fecha_producto").equals("00000000")) && !(Rt.getString("disponibilidad_producto").equals("0"))){
                    //"yyyy-MM-dd"
                    String FechaCaducidadSinFormato = Rt.getString("fecha_producto");
                    
                    String FechaCaducidadConFormato = ""+FechaCaducidadSinFormato.charAt(4)+FechaCaducidadSinFormato.charAt(5)+FechaCaducidadSinFormato.charAt(6)+FechaCaducidadSinFormato.charAt(7)+"-"+FechaCaducidadSinFormato.charAt(2)+FechaCaducidadSinFormato.charAt(3)+"-"+FechaCaducidadSinFormato.charAt(0)+FechaCaducidadSinFormato.charAt(1);
                    String FechaActualConFormato = ""+FechaActual.charAt(4)+FechaActual.charAt(5)+FechaActual.charAt(6)+FechaActual.charAt(7)+"-"+FechaActual.charAt(2)+FechaActual.charAt(3)+"-"+FechaActual.charAt(0)+FechaActual.charAt(1);
                    
                    int DiasParaCaducar = DiasHataCaducidad(FechaActualConFormato,FechaCaducidadConFormato);
                    
                    if(DiasParaCaducar<3 && DiasParaCaducar>0){
                        taPrincipalNotificaiones.append("       - "+Rt.getString("nombre_producto") + "\n");
                        taPrincipalNotificaiones.append("         PRODUCTO CADUCO CON "+DiasParaCaducar + " DIAS \n");
                        taPrincipalNotificaiones.append("\n");
                    }
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        } 
    }
    
    public void PrincipalActualizaNotificacionesPorAgotados(JTextArea taPrincipalNotificaiones){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto`");
            while(Rt.next()){
                if(!(Rt.getString("stock_producto").equals("0"))){
                    int Stock = Integer.parseInt(Rt.getString("stock_producto"));
                    int ProductoDisponible = Integer.parseInt(Rt.getString("disponibilidad_producto"));
                    if(ProductoDisponible<Stock && ProductoDisponible>0){
                        taPrincipalNotificaiones.append("       - "+Rt.getString("nombre_producto") + "\n");
                        taPrincipalNotificaiones.append("         PRODUCTO DISPONIBLE "+ProductoDisponible + "\n");
                        taPrincipalNotificaiones.append("\n");
                    }
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        }
    }
    
    public void PrincipalActualizaNotificacionesAgotados(JTextArea taPrincipalNotificaiones){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto`");
            while(Rt.next()){
                if(!(Rt.getString("stock_producto").equals("0"))){
                    int ProductoDisponible = Integer.parseInt(Rt.getString("disponibilidad_producto"));
                    if(ProductoDisponible == 0){
                        taPrincipalNotificaiones.append("       - "+Rt.getString("nombre_producto") + "\n");
                        taPrincipalNotificaiones.append("         PRODUCTO DISPONIBLE "+ProductoDisponible + "\n");
                        taPrincipalNotificaiones.append("\n");
                    }
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        }
    }
    
    public void PrincipalPausaVenta(String ID_Venta, String Total, String Cajero,String Fecha,String Hora,String Status){
        Total = PrincipalCorrijePrecioAgranel(Total);
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `venta` SET `total_venta` = '"+Total+"', `cajero_venta` = '"+Cajero+"', `fecha_venta` = '"+Fecha+"', `hora_venta` = '"+Hora+"', `pago_venta` = '"+Status+"', `status_venta` = '"+Status+"' WHERE `venta`.`id_venta` = "+ID_Venta+";");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("FinalizarFinalizaVenta - Error: "+e);
        }
        FinalizarLanzaNuevaVenta();
    }
    
    public Boolean PrincipalVentasPausa(){
        Boolean Status = Boolean.FALSE;
        //SELECT COUNT(*) FROM venta WHERE status_venta like 'PAUSA';
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT COUNT(*) FROM venta WHERE status_venta like 'PAUSA';");
            Rt.beforeFirst();
            Rt.next();
            if(Rt.getInt("count(*)") == 0){
                Status = Boolean.TRUE;
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("PrincipalVentasPausa "+e);
        }
        return Status;
    }
    
    public void PrincipalActualizaSesion(String TUsuario){
        System.err.println("PrincipalActualizaSesion");
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `sesion_config` = '"+TUsuario+"' WHERE `configuracion`.`id_config` = '1';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("PrincipalActualizaSesion - Error: "+e);
        }
    }
    
    public String PrincipalEstadoSesion(){
        String User = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `configuracion` WHERE `id_config` LIKE '1';");
            if(Rt.first()){
                User = Rt.getString("sesion_config");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return User;
    }
    
    //######################################## FINALIZAR VENTA ########################################
    //######################################## FINALIZAR VENTA ########################################
    //######################################## FINALIZAR VENTA ########################################
    
    public void FinalizarFinalizaVenta(String ID_Venta,String Total,String Cajero,String Fecha,String Hora,String Pago,String Status, String Cambio){
        Total = PrincipalCorrijePrecioAgranel(Total);
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `venta` SET `total_venta` = '"+Total+"', `cajero_venta` = '"+Cajero+"', `fecha_venta` = '"+Fecha+"', `hora_venta` = '"+Hora+"', `pago_venta` = '"+Pago+"', `status_venta` = '"+Status+"', `cambio_venta` = '"+Cambio+"' WHERE `venta`.`id_venta` = "+ID_Venta+";");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("FinalizarFinalizaVenta - Error: "+e);
        }
        
        String Espera = VerificaEspera();
        if(!(Espera.equals(""))){
            VentaCancela(Espera, "PROCESO");
        }
        
        if(PrincipalVentaProceso().equals("")){
           FinalizarLanzaNuevaVenta(); 
        } 
    }
    
    private String VerificaEspera(){
        String Espera = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `status_venta` LIKE 'ESPERA'");
            while(Rt.next()){
                if(Rt.getString("status_venta").equals("ESPERA")){
                    Espera = Rt.getString("id_venta");
                    break;
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - ProductosLlenarTablaTodos :"+e);
        }
        
        return  Espera;
    }
    
    private void FinalizarLanzaNuevaVenta(){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `venta` (`status_venta`) VALUES ('PROCESO');");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("FinalizarLanzaNuevaVenta - ERROR: "+e);
        }
    }
    
    public void FinalizaExistenciaProductos(String NombreProducto, String Precio, String Cantidad){
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `nombre_producto` LIKE '"+NombreProducto+"' AND `precio_producto` LIKE '"+Precio+"'");
            if(Rt.first()){
                String IDProducto = Rt.getString("id_producto");
                int CantidadDisponible = Integer.parseInt(Rt.getString("disponibilidad_producto"));
                int CantidadVendida = Integer.parseInt(Cantidad);
                CantidadDisponible = CantidadDisponible - CantidadVendida;
                
                if(CantidadDisponible < 0){
                    CantidadDisponible = 0;
                }
                
                FinalizaActualizaExistenciaProductos( IDProducto, ""+CantidadDisponible);
            }
            Rt.close();
            St.close();
            Con.close();
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar ruta, informacion no obtenia","CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("FinalizaExistenciaProductos - ERROR SIN RESPUESTA: "+e);
        } 
    }
    
    private void FinalizaActualizaExistenciaProductos(String IDProducto, String CantidadDisponible){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `producto` SET `disponibilidad_producto` = '"+CantidadDisponible+"' WHERE `producto`.`id_producto` = '"+IDProducto+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
    }
    
    public float FinalizaPorcentajePagoTarjeta(){
        float Porcentaje = 0;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `configuracion`;");
            if(Rt.first()){
                if(Rt.getString("tarjeta_config").equals("")){
                    SolicitaPorcentaje();
                }
                Porcentaje = Float.parseFloat(Rt.getString("tarjeta_config"));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - FinalizaPorcentajePagoTarjeta :"+e);
        }
        return Porcentaje;
    }
    
    public void FinalizaActualizaComicion(String Descripcion, String Costo){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `producto` SET  `nombre_producto` = '"+Descripcion+"', `precio_producto` = '"+Costo+"' WHERE `producto`.`id_producto` = 'COMICION';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
    }
    
    //######################################## AUXILIARES ########################################
    //######################################## AUXILIARES ########################################
    //######################################## AUXILIARES ########################################
    
    private int DiasHataCaducidad(String FechaActual, String FechaCaducidad) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date fechaInicial=dateFormat.parse(FechaActual);
	Date fechaFinal=dateFormat.parse(FechaCaducidad);
 
	int Dias=(int)((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
        
        return Dias;
    }
    
    public static class ModeloTablaNoEditable extends DefaultTableModel{
        public boolean isCellEditable (int row, int column){
            if (column == 5)
                return true;
                return false;
        }
    }
    
    private void SolicitaPorcentaje(){
        String Porcentage = JOptionPane.showInputDialog(null, "Escriba el porcentaje de comision por uso de tarjeta", "PORCENTAJE", JOptionPane.INFORMATION_MESSAGE);
        if(Porcentage.equals("")){
            JOptionPane.showMessageDialog(null, "Porcentaje vacion \n NO ES POCIBLE PORCEDER", "MONTO", JOptionPane.WARNING_MESSAGE);
            SolicitaPorcentaje();
        }
        
        float PorcentajeNumerico = 0;
        try{
            PorcentajeNumerico = Float.parseFloat(Porcentage);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Porcentaje con formato erroneo \n NO ES POCIBLE PORCEDER", "MONTO", JOptionPane.WARNING_MESSAGE);
            SolicitaPorcentaje();
        }
        
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `tarjeta_config` = '"+Porcentage+"';");
            St.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("UsuarioModificar - Error: "+e);
        }
    }
    
    public void PreparaTicket(String NoVenta){
        String Fecha = "";
        String Hora = "";
        String Atedio = "";
        String Total = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `id_venta` LIKE '"+NoVenta+"';");
            if(Rt.first()){
                Fecha = Rt.getString("fecha_venta");
                Hora = Rt.getString("hora_venta");
                Atedio = Rt.getNString("cajero_venta");
                Total = Rt.getNString("total_venta");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        
        String DiaT = ""+Fecha.charAt(0)+Fecha.charAt(1);
        String MesT = "";
        if(Fecha.contains("enero")){
           MesT = "01"; 
        }if(Fecha.contains("febrero")){
           MesT = "02"; 
        }if(Fecha.contains("marzo")){
           MesT = "03"; 
        }if(Fecha.contains("abril")){
           MesT = "04"; 
        }if(Fecha.contains("mayo")){
           MesT = "05"; 
        }if(Fecha.contains("junio")){
           MesT = "06"; 
        }if(Fecha.contains("julio")){
           MesT = "07"; 
        }if(Fecha.contains("agosto")){
           MesT = "08"; 
        }if(Fecha.contains("septiembre")){
           MesT = "09"; 
        }if(Fecha.contains("octubre")){
           MesT = "10"; 
        }if(Fecha.contains("noviembre")){
           MesT = "11"; 
        }if(Fecha.contains("diciembre")){
           MesT = "12"; 
        }
        int LargoFecha = Fecha.length()-1;
        String AnoT = ""+Fecha.charAt(LargoFecha-3)+Fecha.charAt(LargoFecha-2)+Fecha.charAt(LargoFecha-1)+Fecha.charAt(LargoFecha);
        String FechaFormatT = DiaT+"/"+MesT+"/"+AnoT;
        
        String HoraFormatoT = ""+Hora.charAt(0)+Hora.charAt(1)+Hora.charAt(2)+Hora.charAt(3)+Hora.charAt(4)+Hora.charAt(9)+Hora.charAt(10);
        ImprimirTicket(NoVenta, FechaFormatT, HoraFormatoT, Atedio, Total.replace(" ", ""));
    }
    
    private void ImprimirTicket(String NoVenta, String Date, String Time, String Atendio, String Total){
        PrinterMatrix printer = new PrinterMatrix();
        Extenso e = new Extenso();
        printer.setOutSize(CuentaProductosVenta(NoVenta)+18, 10);
        //System.out.println("-----------"+CuentaProductosVenta(NoVenta));
        printer.printTextWrap(1, 1, 0, 80, "      ABARROTES Y CEREALES");
        printer.printTextWrap(3, 1, 0, 80, "    Av.Carlos Paez Stille #307");
        printer.printTextWrap(4, 1, 0, 80, "       Ciudad Guzman. Jal");
        printer.printTextWrap(5, 1, 0, 80, "      Fecha:             Hora:");
        printer.printTextWrap(6, 1, 0, 80, "   "+Date+"          "+Time);
        printer.printTextWrap(7, 1, 0, 80, "   ---------------------------");
        printer.printTextWrap(8, 1, 0, 80, "No."+NoVenta+"   Atendio: "+Atendio);
        printer.printTextWrap(9, 1, 0, 80, "   ---------------------------");
        printer.printTextWrap(10, 1, 0, 80, "Cat.    Des.             Impo.");
        int Pos = 11;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas_venta` LIKE '"+NoVenta+"'");
            
            while(Rt.next()){
                String ToAdd = AcomodaImprecion(Rt.getString("cantidad_ventas"), Rt.getString("ventas_productonombre"), Rt.getString("ventas_importe"));
                printer.printTextWrap(Pos, 1, 0, 80, ToAdd);
                Pos++;
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e2){
            
        }

        String[] DetallesVenta = VentaDetallesVenta(NoVenta);
        if(!DetallesVenta[6].equals("NA")){
            printer.printTextWrap(Pos, 1, 0, 80,"    *"+DetallesVenta[6]+"*");
        }
        
        printer.printTextWrap(Pos+1, 1, 0, 80,"      Tota $"+Total);
        printer.printTextWrap(Pos+2, 1, 0, 80, "   ---------------------------");
        printer.printTextWrap(Pos+3, 1, 0, 80, "   GRACIAS POR SU PREFERENCIA");
        
        printer.toFile("src\\puntoventa\\Ticket.txt");
        FileInputStream inputStream = null;
        
        try {
            inputStream = new FileInputStream("src\\puntoventa\\Ticket.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null," Error: NO FUE POSIBLE GUARDAR GUARDAR Ticket.txt", "Ticket",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
        if (inputStream == null) {
            return;
        }
        
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null," Error: No existen impresoras instaladas", "IMPRESORA",JOptionPane.INFORMATION_MESSAGE,IconoError);
        }
    }
    
    private String AcomodaImprecion(String Cantidad, String Producto, String Importe){
        String ToAdd = "";
        
        if(Cantidad.length() == 1){
            ToAdd = ToAdd+"   "+Cantidad;
        }else if(Cantidad.length() == 2){
            ToAdd = ToAdd+"  "+Cantidad;
        }else if(Cantidad.length() == 3){
            ToAdd = ToAdd+" "+Cantidad;
        }else if(Cantidad.length() == 4){
            ToAdd = ToAdd+""+Cantidad;
        }
        
        String ProToAdd = "";
        if(Producto.length()>18){
            for (int i = 0; i < 18; i++) {
                ProToAdd = ProToAdd + Producto.charAt(i);
            }
        }else{
            ProToAdd = Producto;
            while(ProToAdd.length()< 18){
                ProToAdd = ProToAdd + " ";
            }
        }
        
        ToAdd = ToAdd+" "+ProToAdd+" "+Importe;
        System.out.println(ToAdd.length());
        return ToAdd;
    }
    
    private int CuentaProductosVenta(String NoVenta){
        int Productos = 0;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas_venta` LIKE '"+NoVenta+"'");
            while(Rt.next()){
                Productos = Productos+1;
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return Productos;
    }
    
    private String NumeroFormatoSalida(String Numero){
        String CFormato = "";
        if(Numero.length() > 6){
            int Cont = 0;
            boolean neg = false;
            if(Numero.contains("-")){
                neg = true;
                Numero = Numero.replace("-", "");
            }
            
            String Centabos ="" + Numero.charAt(Numero.length()-1) + Numero.charAt(Numero.length()-2);
            
            for (int x=Numero.length()-4;x>=0;x--){
                CFormato = CFormato + Numero.charAt(x);
                Cont ++;
                if(x == 0){
                    break;
                }
                if(Cont == 3){
                    CFormato = CFormato + ',';
                    Cont = 0;
                }
            }
            

            
            Numero = "";
            for (int x=CFormato.length()-1;x>=0;x--){
                Numero = Numero + CFormato.charAt(x);
            }
            Numero = Numero + "." + Centabos;
            
            if(neg){
                Numero = "-"+Numero;
            }
        }
        return Numero;
    }
    
    //######################################## USUARIOS ########################################
    //######################################## USUARIOS ########################################
    //######################################## USUARIOS ########################################
    
    public void UsuariosLlenarTabla(JTable tbUsuarios){
        ModeloTablaNoEditable Tabla = new ModeloTablaNoEditable();
        try {
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `usuarios`");
            Tabla.setColumnIdentifiers(new Object[]{"Nombre Usuario"});
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("nombre_usuario")});
            }
            Rt.close();
            St.close();
            Con.close();
            tbUsuarios.setModel(Tabla);
            tbUsuarios.setEnabled(true);
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("UsuariosLlenarTabla - Error: "+e);
        }
    }
    
    public boolean UsuarioExiste(String Usuario){
        Boolean Status = Boolean.FALSE;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `usuarios` WHERE `nombre_usuario` LIKE '"+Usuario+"';");
            if(Rt.first()){
                Status = Boolean.TRUE;
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return Status;
    }
    
    public void UsuarioNuevo(String Usuario, String Contra, String Empleado, String Contacto){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `contra_usuario`, `empleado_usuario`, `contacto_usuario`) VALUES (NULL, '"+Usuario+"', '"+Contra+"', '"+Empleado+"', '"+Contacto+"');");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("CrearPoblacion - ERROR: "+e);
        }
    }
    
    public String[] UsuariosContraEmpleadoContacto(String Usuario){
        String Contra = "",Empleado = "",Contacto = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `usuarios` WHERE `nombre_usuario` LIKE '"+Usuario+"';");
            if(Rt.first()){
                Contra = Rt.getString("contra_usuario");
                Empleado = Rt.getString("empleado_usuario");
                Contacto = Rt.getString("contacto_usuario");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return  new String[]{Contra,Empleado,Contacto};
    }
    
    public void UsuarioEliminar(String Usuario){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("DELETE FROM `usuarios` WHERE `usuarios`.`nombre_usuario` = '"+Usuario+"'");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("UsuarioEliminar - Error: "+e);
        }
    }
    
    public void UsuarioModificar(String Usuario, String Contra, String Empleado, String Contacto){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `usuarios` SET `contra_usuario` = '"+Contra+"', `empleado_usuario` = '"+Empleado+"', `contacto_usuario` = '"+Contacto+"' WHERE `nombre_usuario` = '"+Usuario+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("UsuarioModificar - Error: "+e);
        }
    }
    
    //######################################## VENTA ########################################
    //######################################## VENTA ########################################
    //######################################## VENTA ########################################
    
    public void VentaLlenarTabla(JTable tbVentas, String Fecha){
        DefaultTableModel Tabla = (DefaultTableModel) tbVentas.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `fecha_venta` LIKE '"+Fecha+"'");
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("id_venta"),Rt.getString("total_venta"),Rt.getString("hora_venta"),Rt.getString("status_venta")});
            }
            Rt.close();
            St.close();
            Con.close();
            tbVentas.setModel(Tabla);
            tbVentas.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    public String[] VentaDetallesVenta(String Venta){
        String Total = "";
        String Cajero = "";
        String Fecha = "";
        String Hora = "";
        String Pago = "";
        String Status = "";
        String Cambio = "";
        
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `id_venta` LIKE '"+Venta+"';");
            if(Rt.first()){
                
                Total = Rt.getString("total_venta");
                Cajero = Rt.getString("cajero_venta");
                Fecha = Rt.getString("fecha_venta");
                Hora = Rt.getString("hora_venta");
                Pago = Rt.getString("pago_venta");
                Status = Rt.getString("status_venta");
                Cambio = Rt.getString("cambio_venta");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return new String[]{Total,Cajero,Fecha,Hora,Pago,Status,Cambio};
    }
    
    public void VentaLlenarTablaContenido(JTable tbVentaContenido, String IDVenta){
        DefaultTableModel Tabla = (DefaultTableModel) tbVentaContenido.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` where ventas_venta like "+'"'+IDVenta+'"');
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("cantidad_ventas"),Rt.getString("ventas_productonombre"),Rt.getString("ventas_precio"),Rt.getString("ventas_importe")});
            }
            Rt.close();
            St.close();
            Con.close();
            tbVentaContenido.setModel(Tabla);
            tbVentaContenido.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - VentaLlenarTablaContenido :"+e);
        }
    }
    
    public String[] VentaIDProductoCategoria(String NombreProducto, String Precio){
        String IdProducto = "";
        String Categoria = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `nombre_producto` LIKE '"+NombreProducto+"' AND `precio_producto` LIKE '"+Precio+"';");
            if(Rt.first()){
                IdProducto = Rt.getString("id_producto");
                Categoria = Rt.getString("categoria_producto");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - VentaIDProducto :"+e);
        }
        return new String[]{IdProducto,Categoria};
    }
    
    public void VentaCancelaDevuelveProductos(String IdProducto, String CantidadDeVolucion){
        int ProductoDiponible = Integer.parseInt(VentaDisponivilidadProducto(IdProducto));
        int ProdutoTotal = ProductoDiponible + Integer.parseInt(CantidadDeVolucion);
        String Total = ""+ProdutoTotal;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `producto` SET `disponibilidad_producto` = '"+Total+"' WHERE `producto`.`id_producto` = '"+IdProducto+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
    }
    
    private String VentaDisponivilidadProducto(String IdProducto){
        String Disponibilidad = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `id_producto` LIKE '"+IdProducto+"';");
            if(Rt.first()){
                Disponibilidad = Rt.getString("disponibilidad_producto");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - VentaIDProducto :"+e);
        }
        return Disponibilidad;
    }
    
    public void VentaCancela(String IdVenta, String Status){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `venta` SET `status_venta` = '"+Status+"' WHERE `venta`.`id_venta` = '"+IdVenta+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("VentaCancela - Error: "+e);
        }
    }
    
    public void VentaLlenarTablaTodos(JTable tbVentas){
        DefaultTableModel Tabla = (DefaultTableModel) tbVentas.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta`");
            while(Rt.next()){
                if(!Rt.getString("status_venta").equals("PROCESO")){
                    Tabla.addRow(new String[]{Rt.getString("id_venta"),Rt.getString("total_venta"),Rt.getString("hora_venta"),Rt.getString("status_venta")});
                }
            }
            Rt.close();
            St.close();
            Con.close();
            tbVentas.setModel(Tabla);
            tbVentas.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - VentaLlenarTablaTodos :"+e);
        }
    }
    
    public void VentaMuestraPuasa(JTable tbVentas){
        DefaultTableModel Tabla = (DefaultTableModel) tbVentas.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `venta` WHERE `status_venta` LIKE 'PAUSA'");
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("id_venta"),Rt.getString("total_venta"),Rt.getString("hora_venta"),Rt.getString("status_venta")});
            }
            Rt.close();
            St.close();
            Con.close();
            tbVentas.setModel(Tabla);
            tbVentas.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    //########################################  NOTAS ########################################
    //########################################  NOTAS ########################################
    //########################################  NOTAS ########################################

    //--------- LISTAS ---------Inicio
    private static nodo nuevo = null,inicio = null,p = null,q,n;
    private int ban = 0;
    
    private class nodo{
        String NID;
        String NCantidad;
        String NProducto;
        String NPrecio;
        String NImporte;
        nodo enlace;
    }
        
    private void NotasAgregarLista(String ID, String Cantidad, String Producto, String Precio, String Importe){
        nuevo = new nodo();
        nuevo.NID = ID;
        nuevo.NCantidad = Cantidad;
        nuevo.NProducto = Producto;
        nuevo.NPrecio = Precio;
        nuevo.NImporte = Importe;
        nuevo.enlace = null;
        if (inicio==null){
            inicio = nuevo;
        }else {
            p.enlace = nuevo;
        }
        p=nuevo;
    }
    
    private void NotasBuscar(String ID, String Cantidad, String Producto, String Precio, String Importe){
        n=inicio;
        int ban=0;
        while (n!=null&&ban==0){
            if(n.NID.equals(ID)){
                
                int CantidadTotal = Integer.parseInt(n.NCantidad) + Integer.parseInt(Cantidad);
                float ImporteTotal = 0;
                if(NotasCategoriaProducto(ID).equals("AGRANEL")){
                    ImporteTotal = (Float.parseFloat(n.NPrecio)/1000) * CantidadTotal;
                }else{
                    ImporteTotal = Float.parseFloat(n.NPrecio) * CantidadTotal;
                }
                
                n.NCantidad = ""+CantidadTotal;
                n.NImporte = ""+ImporteTotal;
                
                ban=1;
            }else{
                q=n;
                n=n.enlace;
            }
        }
        
        if (ban==0){
            NotasAgregarLista(ID, Cantidad, Producto, Precio, Importe);
        }
   }
    
    private void NotasConsutar(){
        float TotalNota = 0;
        q=inicio;
        while(q!=null){
//            System.out.print(q.NID+" - ");
//            System.out.print(q.NCantidad+" - ");
//            System.out.print(q.NProducto+" - ");
//            System.out.print(q.NPrecio+" - ");
//            System.out.println(q.NImporte);
            TotalNota = TotalNota + Float.parseFloat(q.NImporte);
            q=q.enlace;
        }
        System.err.println("TotalNota: $"+TotalNota);
        nuevo = null;
        inicio = null;
        p = null;
    }
    
    //--------- LISTAS ---------Fin
    
    public void NotasVentasFinalizadaLimite(String Consulta, String Encabezado, int Limite, String NonbreArchivo, String Origen){
        float Cuenta = 0;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery(Consulta);
            while(Rt.next() & Cuenta < Limite ){
                Cuenta = Cuenta + Float.parseFloat(Rt.getString("total_venta"));
                NotasVentasVetnta(Rt.getString("id_venta"));
                //System.out.println(Rt.getString("id_venta")+" - "+Rt.getString("total_venta")+" - "+Cuenta);
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
        //NotasConsutar();
        NotaGeneraPDF(Encabezado,NonbreArchivo, Origen);
    }
    
    public void NotasVentaFinalizada(String Consulta, String Encabezado, String NombreArchivo,String Origen){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery(Consulta);
            while(Rt.next()){
                //System.err.println("Venta "+Rt.getString("id_venta")+" Status:"+Rt.getString("status_venta")+ " Total: "+Rt.getString("total_venta"));
                NotasVentasVetnta(Rt.getString("id_venta"));
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
        //NotasConsutar();
        NotaGeneraPDF(Encabezado,NombreArchivo, Origen);
    }
    
    private void NotasVentasVetnta(String ID){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `ventas` WHERE `ventas_venta` LIKE '"+ID+"';");
            while(Rt.next()){
                if(!(Rt.getString("ventas_productoid").equals("COMICION"))){
                   //System.out.println("IdProducto "+Rt.getString("ventas_productoid")+" Cantidad "+Rt.getString("cantidad_ventas")+" Producto:"+Rt.getString("ventas_productonombre")+" Precio:"+Rt.getString("ventas_precio")+ " Importe: "+Rt.getString("ventas_importe")); 
                    NotasBuscar(Rt.getString("ventas_productoid"), Rt.getString("cantidad_ventas"), Rt.getString("ventas_productonombre"), Rt.getString("ventas_precio"), Rt.getString("ventas_importe"));
                }
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoNombre :"+e);
        }
    }
    
    private String NotasCategoriaProducto(String ID){
        String Categoria = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `id_producto` LIKE '"+ID+"';");
            if(Rt.first()){
                Categoria = Rt.getString("categoria_producto");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return Categoria;
    }

    private void NotaGeneraPDF(String Encabezado, String NombreArchivo, String Origen){
        float TotalNota = 0;
        q=inicio;
        String[] N = ConfiguracionValores();//P = Porcentage comicion R = Respado N = Notas
        try{
            //--------------declaramos cosas
            FileOutputStream Archivo = new FileOutputStream(N[2]+"\\"+NombreArchivo+".pdf");
            Document Documento = new Document();
            Image Imagen = Image.getInstance("src\\Imagenes\\Encabezado.jpg");
            Image Separador = Image.getInstance("src\\Imagenes\\Separador.jpg");
            Imagen.scaleToFit(600, 200);
            Separador.scaleToFit(600, 200);
	    Imagen.setAlignment(Chunk.ALIGN_CENTER);
            Separador.setAlignment(Chunk.ALIGN_CENTER);
            PdfWriter.getInstance(Documento, Archivo);
            //--------------Abrimos el documento
            Documento.open();
            //--------------Comenzamos a Escribir
            Documento.add(Imagen);
            Documento.add(new Paragraph(Encabezado.toUpperCase(),FontFactory.getFont("arial",14,Font.ITALIC,BaseColor.BLACK)));
            Documento.add(Separador);
            PdfPTable table = new PdfPTable(4);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            float[] columnWidths = new float[]{100, 280, 100, 100}; 
            table.setWidths(columnWidths); 
            table.addCell(new Paragraph("CANTIDAD",FontFactory.getFont("Calibri",11,Font.BOLD,BaseColor.BLACK)));
            table.addCell(new Paragraph("PRODUCTO",FontFactory.getFont("Calibri",11,Font.BOLD,BaseColor.BLACK)));
            table.addCell(new Paragraph("PRECIO",FontFactory.getFont("Calibri",11,Font.BOLD,BaseColor.BLACK)));
            table.addCell(new Paragraph("IMPORTE",FontFactory.getFont("Calibri",11,Font.BOLD,BaseColor.BLACK)));
            
            while(q!=null){
                table.addCell(new Paragraph(NumeroFormatoSalida(q.NCantidad),FontFactory.getFont("Calibri",10,Font.ITALIC,BaseColor.BLACK)));
                table.addCell(new Paragraph(q.NProducto,FontFactory.getFont("Calibri",10,Font.ITALIC,BaseColor.BLACK)));
                table.addCell(new Paragraph(NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(q.NPrecio.concat("0"))),FontFactory.getFont("Calibri",10,Font.ITALIC,BaseColor.BLACK)));
                table.addCell(new Paragraph(NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(q.NImporte.concat("0"))),FontFactory.getFont("Calibri",10,Font.ITALIC,BaseColor.BLACK)));
                TotalNota = TotalNota + Float.parseFloat(q.NImporte);
                q=q.enlace;
            }
            
            Documento.add(table);
            Documento.add(Separador);
            Documento.add(new Paragraph("IMPORTE TOTAL: $"+NumeroFormatoSalida(PrincipalCorrijePrecioAgranel(""+TotalNota+"0").toUpperCase()),FontFactory.getFont("Calibri",11,Font.BOLD,BaseColor.BLACK)));
            //--------------cerramos el documenton
            Documento.close();
            if(Origen.equals("Generador")){
                JOptionPane.showMessageDialog(null, "NOTA GENERADA CORECTAMENTE \n Nombre: "+NombreArchivo+"\n Ubicacion: "+N[2], "PDF",JOptionPane.INFORMATION_MESSAGE);
            }
            Documento.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: \n: "+e,"PDF",JOptionPane.INFORMATION_MESSAGE);
        }
        nuevo = null;
        inicio = null;
        p = null;
    }
    
    public String NotaNumero(String Fecha){
        String NoNota = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `notas` WHERE `fecha_nota` LIKE '"+Fecha+"';");
            if(Rt.first()){
                NoNota = ""+Rt.getInt("id_nota");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return  NoNota;
    }
    
    public void NotaCreaNueva(String Fecha){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `notas` (`id_nota`, `fecha_nota`) VALUES (NULL, '"+Fecha+"');");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("NotaCreaNueva - ERROR: "+e);
        }
    }
    
    //########################################  CONFIGURACION ########################################
    //########################################  CONFIGURACION ########################################
    //########################################  CONFIGURACION ########################################
    
    public String[] ConfiguracionValores(){
        String Porcentage = "";
        String Respaldo = "";
        String Notas = "";
        String Reportes = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `configuracion` WHERE `id_config` LIKE '1';");
            if(Rt.first()){
                Porcentage = Rt.getString("tarjeta_config");
                Respaldo = Rt.getString("respaldo_config");
                Notas = Rt.getString("notas_config");
                Reportes =Rt.getString("reportes_config");
            }
            Rt.close();
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error - UsuarioExiste :"+e);
        }
        return new String[]{Porcentage,Respaldo,Notas,Reportes};
    }
    
    public void ConfiguracionAtualizaComision(String Valor){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `tarjeta_config` = '"+Valor+"' WHERE `configuracion`.`id_config` = 1;");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
    }
    
    public void ConfiguracionDireccionRespaldo(String Direccion){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `respaldo_config` = '"+Direccion+"' WHERE `configuracion`.`id_config` = 1;");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        } 
    }
    
    public void  ConfiguracionDireccionNotas(String Direccion){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `notas_config` = '"+Direccion+"' WHERE `configuracion`.`id_config` = 1;");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        } 
    }
    
    public void  ConfiguracionDireccionReportes(String Direccion){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `configuracion` SET `reportes_config` = '"+Direccion+"' WHERE `configuracion`.`id_config` = 1;");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        } 
    }
    
    public void ConfiguracionLimpiaDB(){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("drop table categoria,configuracion,producto,usuarios,venta,ventas");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("ConfiguracionLimpiaDB - ERROR: "+e);
        } 
    }
    
    //######################################## SALIDA ########################################
    //######################################## SALIDA ########################################
    //######################################## SALIDA ########################################
    
    public void SalidaAgregar(String Empleado, String Fecha, String Motivo, String Monto){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("INSERT INTO `salidas` (`id_salida`, `empleado_salida`, `fecha_salida`, `motivo_salida`, `monto_salida`, `status_salida`) VALUES (NULL, '"+Empleado+"', '"+Fecha+"', '"+Motivo+"', '"+Monto+"', 'FINALIZADO');");
            St.close();
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al crear poblacion Nombre = "+poblacion+"\n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("CrearPoblacion - ERROR: "+e);
        }
    }
    
    public void SalidasLlenarTablaSalidas(JTable tbSalidas,String Fecha){
        DefaultTableModel Tabla = (DefaultTableModel) tbSalidas.getModel();
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            ResultSet Rt = St.executeQuery("SELECT * FROM `salidas` WHERE `fecha_salida` LIKE '"+Fecha+"';");
            System.out.println("SELECT * FROM `salidas` WHERE `fecha_salida` LIKE '"+Fecha+"';");
            while(Rt.next()){
                Tabla.addRow(new String[]{Rt.getString("empleado_salida"),Rt.getString("fecha_salida"),Rt.getString("motivo_salida"),Rt.getString("monto_salida")});
            }
            Rt.close();
            St.close();
            Con.close();
            tbSalidas.setModel(Tabla);
            tbSalidas.setEnabled(true);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Erro - BurcarProductoId :"+e);
        }
    }
    
    //######################################## INVENTARIO RAPIDO ########################################
    //######################################## INVENTARIO RAPIDO ########################################
    //######################################## INVENTARIO RAPIDO ########################################
    
    public String[] InventarioRapido(String CodigoBarras){
        String NombreProducto = "";
        String DisponivilidadProducto = "";
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            try (Statement St = Con.createStatement(); ResultSet Rt = St.executeQuery("SELECT * FROM `producto` WHERE `id_producto` LIKE '"+CodigoBarras+"'")) {
                if(Rt.first()){
                    NombreProducto = Rt.getString("nombre_producto");
                    DisponivilidadProducto = Rt.getString("disponibilidad_producto");
                }
            }
            Con.close();
        }catch (ClassNotFoundException | SQLException e){
            //JOptionPane.showMessageDialog(null, "Ups! Error al consultar poblacion \n"+e,"CONEXION DB",JOptionPane.ERROR_MESSAGE);
            System.out.println("ClienteConsutaCliente - ERROR: "+e);
        }
        return new String[]{NombreProducto,DisponivilidadProducto};
    }
    
    public void InventarioRAgregarCantidadProducto(String CodigoBarras, String Cantidad){
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            St.executeUpdate("UPDATE `producto` SET `disponibilidad_producto` = '"+Cantidad+"' WHERE `producto`.`id_producto` = '"+CodigoBarras+"';");
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }  
    }
    
    
    public void AgregarMasiba(String RutaArchivo){
        int Si = 0;
        int No = 0;
        try{
            Con = ClaseConMySQL.ClaseConexionMySQL();
            Statement St = Con.createStatement();
            try{
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
            
            
                archivo = new File (RutaArchivo);
            
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                
                String linea;
                while((linea=br.readLine())!=null){
                    try{
                        St.executeUpdate(linea);
                        Si = Si + 1;
                    }catch(SQLException e){
                        No = No +1;
                    }
                }
                
                fr.close();
            }catch(Exception e){}
            
            St.close();
            Con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ProductosModificar - Error: "+e);
        }
        
        System.err.println("si = "+Si);
        System.err.println("no = "+No);
        
        JOptionPane.showMessageDialog(null," si = "+Si+"no = "+No, "Salida",JOptionPane.INFORMATION_MESSAGE,IconoError);
    }
}