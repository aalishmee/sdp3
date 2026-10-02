public class Circle extends Shape {
    private final int radius;
    public Circle(int id, int radius, Renderer renderer) {
        super(id, renderer);
        this.radius = radius;
    }

    @Override
    public String execute() {
        return renderer.renderCircle(radius);
    }
}