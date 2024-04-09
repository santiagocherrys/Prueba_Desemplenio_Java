package entity;

public class Vacante {
    private int id;
    private int empresa_id;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String tecnologia;
    private String clan;
    private String estado;
    private Empresa empresa;

    //Constructores
    public Vacante(){

    }

    public Vacante(int empresa_id, String titulo, String descripcion, String duracion, String estado,  String tecnologia, String clan, Empresa empresa) {
        this.empresa_id = empresa_id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
        this.clan = clan;
        this.empresa = empresa;
    }

    //Setters and Getters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    //ToString

    @Override
    public String toString() {
        return "Vacante{" +
                ", titulo='" + titulo + '\'' +
                ", duracion='" + duracion + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", clan='" + clan + '\'' +
                ", estado='" + estado + '\'' +
                ", empresa=" + empresa.getNombre() +
                '}';
    }

    public String imprimirTodo(){
        return "Vacante{" +
                "id=" + id +
                ", empresa_id=" + empresa_id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", clan='" + clan + '\'' +
                ", estado='" + estado + '\'' +
                ", empresa=" + empresa +
                '}';
    }
}
