package clases_base_datos;

public class actualizar_registro {
    Double nota_1;
    Double nota_2;

    public actualizar_registro() {
    }

    public actualizar_registro(String nombre, Double nota_1, Double nota_2) {
        this.nota_1 = nota_1;
        this.nota_2 = nota_2;
    }


    public Double getNota_1() {
        return nota_1;
    }

    public void setNota_1(Double nota_1) {
        this.nota_1 = nota_1;
    }

    public Double getNota_2() {
        return nota_2;
    }

    public void setNota_2(Double nota_2) {
        this.nota_2 = nota_2;
    }
}
