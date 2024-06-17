/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


public class iReport {
    
   private static String Usuario = "sa";
    private static String pass = "";
    private static String jdbcdir = "jdbc:hsqldb:hsql://127.0.0.1/";
    private static Connection connection;

    /**
     * Conectar con la base de datos
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException 
     */
    private static void crearConexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver").newInstance();
        connection = DriverManager.getConnection(jdbcdir, Usuario, pass);
    }

    /**
     * Generea un informe que no necesita parametros, solo indicar en donde se encutra el fichero *.jasper y donde guardaremos el fichero *.pdf
     * @param dirJasper ruta del ficher *.jasper
     * @param dirPdf    ruta del archivo que crearemos *.pdf
     */
    public static void generarInforme(String dirJasper, String dirPdf) {

        try {
            crearConexion();

            Map parametros = new HashMap();

            JasperPrint print = JasperFillManager.fillReport(dirJasper, parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, dirPdf);

        } catch (Exception e) {

            System.out.println(e.getCause());

        }
    }

    /**
      * Generea un informe que  necesita parametros, solo indicar en donde se encutra el fichero *.jasper y donde guardaremos el fichero *.pdf
     * @param dirJasper ruta del ficher *.jasper
     * @param dirPdf    ruta del archivo que crearemos *.pdf
     * @param parametros fichero tipo Map que tiene guardado de donde recoje los parametros.
     */
    public static void generarInforme(String dirJasper, String dirPdf, Map parametros) {

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            Connection connection = DriverManager.getConnection(jdbcdir, Usuario, pass);

            JasperPrint print = JasperFillManager.fillReport(dirJasper, parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, dirPdf);

        } catch (Exception e) {

            System.out.println(e.getCause());
        }
    }

    /**
     * devuelve el resutado (ResulSet) de una consulta a la base de datos. 
     * @param sql
     * @return 
     */
    public static ResultSet consulta(String sql) {
        ResultSet rs = null;
        try {
            crearConexion();

            Statement stm = connection.createStatement();
            rs = stm.executeQuery(sql);
        } catch (Exception e) {

        }

        return rs;
    }
}
