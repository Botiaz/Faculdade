package Ponto;

public class Ponto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;
    // Construtor que inicializa x e y com zero
    public Ponto() {
        this.x = 0;
        this.y = 0;
        this.id = nextID;
        nextID++;
    }
    // Construtor que inicializa x e y com valores passados como parâmetros
    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID++;
    }
    // Métodos get e set para x
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    // Métodos get e set para y
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    // Método para calcular a distância entre dois pontos
    public double dist(Ponto other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    // Método para calcular a distância entre este ponto e um ponto com coordenadas (x, y)
    public double dist(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    // Método estático para calcular a distância entre dois pontos dados como coordenadas (x1, y1) e (x2, y2)
    public static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
    // Método para verificar se três pontos formam um triângulo
    public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3) {
        double d1 = p1.dist(p2);
        double d2 = p2.dist(p3);
        double d3 = p3.dist(p1);
        return (d1 + d2 > d3) && (d1 + d3 > d2) && (d2 + d3 > d1);
    }
    // Método para calcular a área de um retângulo formado por dois pontos
    public double getAreaRetangulo(Ponto other) {
        double largura = Math.abs(this.x - other.getX());
        double altura = Math.abs(this.y - other.getY());
        return largura * altura;
    }
    // Métodos get para o atributo id
    public int getID() {
        return id;
    }
    // Método estático para obter o valor corrente do atributo nextID
    public static int getNextID() {
        return nextID;
    }
}