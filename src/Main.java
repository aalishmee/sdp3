public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            demo();
        }
    }

    public static void demo() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        int passed = 0;
        Circle c1 = new Circle(1, 2, vector);
        passed += test("T1", c1, "VECTOR circle radius=2");

        Circle c2 = new Circle(1, 2, raster);
        passed += test("T2", c2, "RASTER circle radius=2");

        Square s1 = new Square(2, 3, vector);
        passed += test("T3", s1, "VECTOR square side=3");

        Square s2 = new Square(2, 3, raster);
        passed += test("T4", s2, "RASTER square side=3");

        Circle c5 = new Circle(5, 2, vector);
        Circle sameCircle = c5;

        String before = c5.execute();
        c5.setImplementation(raster);
        String after = c5.execute();

        boolean sameObject = c5 == sameCircle;
        boolean sameId = c5.getId() == 5;
        boolean correct = before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2");

        if (sameObject && sameId && correct) {
            System.out.println("T5 PASS | sameObject=" + sameObject
                    + " | sameId=" + sameId
                    + " | before=" + before
                    + " | after=" + after);
            passed++;
        } else {
            System.out.println("T5 FAIL");
        }

        System.out.println("SUMMARY: " + passed + "/5 PASS");
    }

    public static int test(String name, Shape shape, String expected) {
        String result = shape.execute();
        if (result.equals(expected)) {
            System.out.println(name + " PASS | " + result);
            return 1;
        }

        System.out.println(name + " FAIL | result=" + result
                + " | expected=" + expected);

        return 0;
    }
}