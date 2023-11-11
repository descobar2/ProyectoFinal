public class Cliente {
    private int id;
    private String nit;
    private String nombre;
    private String direccion;

    public void setId(int id) {
        this.id = id;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }
    public String getNit() {
        return nit;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }
}
