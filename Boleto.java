import java.sql.Date;

public class Boleto {
    private int id;
    private int serie;
    private byte adulto;
    private byte nino;
    private int cantidad;
    private Date fecha;
    private String nombreJuego;
    private String nombreCliente;

//Setters Boletos
    public void setId(int id){
        this.id = id;
    }
    public void setSerie(int serie){
        this.serie = serie;
    }
    public void setAdulto(byte adulto){
        this.adulto = adulto;
    }
    public void setNino(int cantida){
        this.cantidad = cantida;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public void setNombreJuego(String nombreJuego){
        this.nombreJuego = nombreJuego;
    }
    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

//Getters Boletos
    public int getId(){
        return id;
    }
    public int getSerie(){
        return serie;
    }
    public byte setAdulto(){
        return adulto;
    }
    public byte setNino(){
        return nino;
    }
    public int getCantidad(){
        return cantidad;
    }
    public Date getFecha(){
        return fecha;
    }
    public String getNombreJuego(){
        return nombreJuego;
    }
    public String getNombreCliente(){
        return nombreCliente;
    }
}
