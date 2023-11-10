import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
//funcion para ingresar materiales
    public void nuevoMaterial(String nombre, String medida, float precio){
        try{
            String sql = "INSERT INTO Material (NombreMat, Medida, PrecioMat, CantDisp) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setString(2, medida);
            ps.setFloat(3, precio);
            ps.setInt(4,0);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar compra
    public void nuevaCompra(String nombre, int cantidad){
        try{
            String sql = "UPDATE Material SET CantDisp = ? WHERE NombreMat = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2,nombre);
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
//funcion para crear orden de compra.
    public void nuevaOrden(int clienteID, int productoID, int cantProd, int tipoID, String estado){
        try {
            String sql = "INSERT INTO Documento (ClienteID, ProductoID, CantPro, TipoID, Estado) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,clienteID);
            ps.setInt(2, productoID);
            ps.setInt(3, cantProd);
            ps.setInt(4, tipoID);
            ps.setString(5, estado);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getId(String nombre, int tipo) throws SQLException{
         try {
            String sql = "SELECT PersonaID FROM Persona WHERE Nombre = ? AND TipoID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setInt(2,tipo);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return rs.getInt("Id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int getDisponible(int materialID){
            try {
                String sql = "SELECT CantDisp FROM Material WHERE MaterialID = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,materialID);
                    try(ResultSet rs = ps.executeQuery();){
                        if(rs.next()){
                            return rs.getInt("CantDisp");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return -1;
    }
    public void updateInventario(int materialID,int nuevoDato){
        String sMaterialID = "" + materialID;
        String sNuevoDato = "" + nuevoDato;
        updateDato("Material","CantDisp","MaterialID",sNuevoDato, sMaterialID);
    }
    public void updateEstado(int documentoID, String nuevoEstado){
        String sDocumentoID = "" + documentoID;
        updateDato("Documento", "Estado", "DocumentoID", nuevoEstado, sDocumentoID);
    }
//----------------------------------------------------------------------    
    public void updateDato(String tabla, String campoUpdate, String campoBus, String cantidad, String materialID){
        try{
            String sql = "UPDATE "+ tabla +" SET "+ campoUpdate +" = ? WHERE " + campoBus + " = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cantidad);
            ps.setString(2,materialID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteDB(Connection con){
        try{
            String sql = "DELETE FROM Tarea.Proveedor WHERE ProveedorID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

