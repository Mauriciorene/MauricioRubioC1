/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.*;

/**
 *
 * @author FAREMB4_7
 */
public class DataBase {
    //cadena de conexion de la BD
    private final String URL="jdbc:sqlserver://localhost:1433;databaseName=Publicaciones;"+
            "integratedSecurity=true;"+"encryp=true;trustServerCertificate=true;";
    
    private Connection conexion; //Para conexion con la BD

    public DataBase() { //Construtor de la clase
        try{
            //Usando Driver conector y cadena de conexion para conectar BD
            conexion=DriverManager.getConnection(URL);
            System.out.println("Conexión Establecida");
            
        }catch(SQLException e){
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }
    
    //Método para actualizar datos en la BD
    public int Actualizar(String consulta){
        
        try{ //Para manejar errores al realizar la conexion y transaccion en BD
            Statement st=conexion.createStatement();
            return st.executeUpdate(consulta);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    private List OrganizarDatos(ResultSet rs){
        List filas=new ArrayList(); //Arreglo de elementos
        try{
            
            int numColumnas=rs.getMetaData().getColumnCount();
            while(rs.next()){ //recorre cada registro de la tabla
                Map<String, Object> renglon=new HashMap();
                for(int i=1; i<=numColumnas; i++){
                    //se obtiene nombre de campo,
                    String nombreCampo=rs.getMetaData().getColumnName(i);
                    Object valor=rs.getObject(nombreCampo);
                    //por cada campo, se obtiene el nombre y el valor del mismo
                    renglon.put(nombreCampo,valor);
                }
                filas.add(renglon);//Se agrega al arreglo cada registro
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return filas;
    }
    
    public List Listar(String consulta){
        ResultSet rs=null;
        List resultado=new ArrayList();
        try{
            Statement st=conexion.createStatement();
            rs=st.executeQuery(consulta);
            resultado=OrganizarDatos(rs);
            
        }catch(SQLException e){
            System.out.println("No se realizó la consulta");
            e.printStackTrace();
        }
        return resultado;
    }
    
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}

