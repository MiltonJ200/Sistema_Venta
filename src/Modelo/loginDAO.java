
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author MiltonJ
 */
public class loginDAO {

       Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String correo, String pass){
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs= ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol"));
                
            }
        } 
        
        catch (SQLException e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
    public int idRegistro(){
      int id = 0;
      String sql = "SELECT MAX(id) FROM usuarios";
      try{
          con = cn.getConnection();
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
          if(rs.next()){
              id = rs.getInt(1);
          }
      } catch (SQLException e){
         System.out.println(e.toString());
      }
      return id;
 }
    
    public boolean Registrar(login reg){
        String sql = "INSERT INTO usuarios (nombre, correo, pass, rol, id) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());
            ps.setInt(5, reg.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
            
        }
        
    }
    
    
}
