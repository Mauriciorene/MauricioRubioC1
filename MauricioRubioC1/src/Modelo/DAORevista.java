/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FAREMB4_7
 */
public class DAORevista {
    //Método para insertar datos en la BD
    public Revista Insertar(String titulo, int ayo, String issn, float precio,
            java.sql.Time Horaventa){
        String transaccion = "INSERT INTO Revista VALUES('"
                + titulo + "', '"
                + ayo + "', '"
                + issn + "', '"
                + precio + "', '"
                + Horaventa + "')";
        //Llama al método Actualizar ubicado en DataBase.java
        if (new DataBase().Actualizar(transaccion) > 0) {
            return new Revista(titulo, ayo, issn, precio, Horaventa);
        }
        return null;
    }
    
    //Método para actualizar un registro en la BD
    public int Actualizar(int numero, String titulo, int ayo, String issn,
            float precio, java.sql.Time Horaventa) {
        
        String transaccion = "UPDATE Revista SET titulo='"
                + titulo + "', ayo='"
                + ayo + "', issn='"
                + issn + "',precio='"
                + precio + "',Horaventa='"
                + Horaventa + "' WHERE numero='"
                + numero;
        
        return new DataBase().Actualizar(transaccion);
    }
    
    //Método para seleccionar todos los registros de la tabla
    public List ObtenerDatos() {
        String transaccion = "SELECT * FROM Revista";
        //Llama a método Listar de DataBase.java
        List<Map> registros = new DataBase().Listar(transaccion);
        List<Revista> revistas = new ArrayList(); //Arreglo de Revistas
        //Ciclo que recorre cada registro y los arregla al arreglo Revista
        for (Map registro : registros) {
            Revista re = new Revista((int) registro.get("numero"),
                    (String) registro.get("numero"),
                    (int) registro.get("ayo"),
                    (String) registro.get("issn"),
                    (Float.parseFloat(registro.get("precio").toString())),
                    (java.sql.Time.valueOf( registro.get("Horaventa").toString())));
            revistas.add(re);
        }
        return revistas; //Retorna todos los registros ubicados en la tabla de BD
    }
    
    //Método para eliminar un registro de la tabla en la BD
    public int Eliminar(int numero) {
        String transaccion = "DELETE FROM Revista WHERE numero='"+ numero +"'";
     
     return new DataBase().Actualizar(transaccion);
    }
}
