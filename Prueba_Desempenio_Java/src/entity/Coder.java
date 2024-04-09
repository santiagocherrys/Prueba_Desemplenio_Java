package entity;

public class Coder {
    private int id;
    private String nombre;
    private String apellidos;
    private String documento;
    private int cohorte;
    private String cv;

    //Constructores
    public Coder(){

    }

    public Coder(String nombre, String apellidos, String documento, int cohorte, String cv) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.cohorte = cohorte;
        this.cv = cv;
    }

    //Setter and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCohorte() {
        return cohorte;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    //ToString


    @Override
    public String toString() {
        return "Coder: " +
                nombre +  apellidos +
                " cohorte=" + cohorte;
    }

    public String imprimirTodo(){
        return "Coder{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documento='" + documento + '\'' +
                ", cohorte=" + cohorte +
                ", cv='" + cv + '\'' +
                '}';
    }
}
