import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB{
    final String HOST = "proyecto2.ctlvgikixphm.us-east-1.rds.amazonaws.com:3306/PARQUE";
    final String DB_URL = String.format("jdbc:mysql://%s", HOST);
    private Connection con;
    Statement stmt;

    public ConnectionDB(){
        try{
        con = DriverManager.getConnection(DB_URL,"progra","Guate2021+");
        stmt = con.createStatement();         
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
//funcion para ingresar nuevo juego
    public void newGame(String nombre, String precio){
        try{
            String sql = "INSERT INTO Juego (Nombre, Precio) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, precio);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar boletos
    public void setBoleto(String serie, boolean adulto, boolean nino, String cantidad, String nomJuego, String nit){
        try{
            String sql = "INSERT INTO Boleto (Serie, Adulto, Nino, Cantidad, NombreJuego, NitCliente) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,serie);
            ps.setBoolean(2, adulto);
            ps.setBoolean(3, nino);
            ps.setString(4,cantidad);
            ps.setString(5,nomJuego);
            ps.setString(6,nit);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar cliente
    public void setCliente(String nit, String nombre, String direccion){
        try{
            String sql = "INSERT INTO Cliente (Nit, Nombre, Direccion) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nit);
            ps.setString(2,nombre);
            ps.setString(3,direccion);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar nuevo producto
    public void nuevoProducto(String nombre, Float precio){
        try {
            String sql = "INSERT INTO Producto (NombrePro, PrecioProd) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setFloat(2, precio);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void asociarMaterial(int productoId, int materialID, int cantida) throws SQLException{
        String sql = "INSERT INTO ProductoMaterial (ProductoID, MaterialID, Cantidad) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productoId);
        ps.setInt(2,materialID);
        ps.setInt(3,cantida);
        ps.executeUpdate();
    }

    public void updateGameName(String nombre, String nuevoNombre){
        updateDato("Juego","Nombre","Nombre",nombre,nuevoNombre);
    }
    public void updateGamePrice(String nombre, String nuevoPrecio){
        updateDato("Juego", "Precio", "Nombre", nombre, nuevoPrecio);
    }
//Actualizar datos------------------------------------------------------    
    public void updateDato(String tabla, String campoUpdate, String campoBus, String nombre, String nuevoDato){
        try{
            String sql = "UPDATE "+ tabla +" SET "+ campoUpdate +" = ? WHERE " + campoBus + " = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nuevoDato);
            ps.setString(2,nombre);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Eliminar datos--------------------------------------------------------    
    public void eraseGame(String nombre){
        deleteGame(nombre, con);
    }
    public void deleteGame(String nombre, Connection con){
        try{
            String sql = "DELETE FROM PARQUE.Juego WHERE Nombre = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Consultas-------------------------------------------------------------
    public String getGame(String nombre){
        String dato = getDato("Juego","Precio","Nombre",nombre);
        return dato;
    }
    public String getNit(String nit){
        String dato = getDato("Cliente","Nit","Nit",nit);
        return dato;
    }

    public String getDato(String tabla, String campoObtener, String campoBuscar, String busqueda){
        String respuesta="-1";
        try{
            String sql = "SELECT "+ campoObtener +" FROM "+ tabla +" WHERE "+ campoBuscar +" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, busqueda);
            ResultSet rs =ps.executeQuery();
            if (rs.next()) {
                respuesta = rs.getString(campoObtener);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return respuesta;
    }
    public ArrayList<Juego> getJuegos() throws SQLException{
        ArrayList<Juego> juegos = new ArrayList<Juego>();
        String sql = "SELECT * FROM Juego";                    
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs =ps.executeQuery();
        while(rs.next()){
            Juego juego = new Juego();
            juego.setId(rs.getInt("Id"));
            juego.setNombre(rs.getString("Nombre"));
            juego.setPrecio("Precio");
            juegos.add(juego);
        } 
        return juegos;
    }
    public ArrayList<Boleto> getBoleto() throws SQLException{
        ArrayList<Boleto> boletos = new ArrayList<Boleto>();
        String sql = "SELECT * FROM Boleto";                    
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs =ps.executeQuery();
        while(rs.next()){
            Boleto boleto = new Boleto();
            boleto.setId(rs.getInt("Id"));
            boleto.setSerie(rs.getInt("Serie"));
            boleto.setAdulto(rs.getByte("Adulto"));
            boleto.setNino(rs.getByte("Nino"));
            boleto.setCantida(rs.getInt("Cantidad"));
            boleto.setFecha(rs.getDate("Fecha"));
            boleto.setNombreJuego(rs.getString("NombreJuego"));
            boleto.setNitCliente(rs.getString("NitCliente"));
            boletos.add(boleto);    
        } 
        return boletos;
    } 

    public ArrayList<Cliente> getCliente() throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String sql = "SELECT * FROM Juego";                    
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs =ps.executeQuery();
        while(rs.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("Id"));
            cliente.setNit(rs.getString("Nit"));
            cliente.setNombre(rs.getString("Nombre"));
            cliente.setDireccion("Direccion");
            clientes.add(cliente);
        } 
        return clientes;
    }

    public int getUltimoID(String nombreCampo, String tabla) {
        try {
            String sql = "SELECT MAX(" + nombreCampo + ") AS UltimoID FROM " + tabla;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("UltimoID"); // Obtiene el valor del último ID
            } else {
                return -1; // Si no hay resultados
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si hay un error
    }
    //funcion para validar si existe un elemento
    public boolean validarDato(String dato){
        try {
            String sql = "SELECT COUNT(*) FROM Material WHERE NombreMat = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, dato);              
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int rowCount = rs.getInt(1);
                        return rowCount > 0;  // Si rowCount es mayor que 0, el valor existe.
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

