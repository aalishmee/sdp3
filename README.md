# Assignment 3 — Bridge Pattern
- **Name:** Medina Alish
- **Group:** SE-2538
- **Topic:** A — Drawing
- **Repository:** https://github.com/aalishmee/sdp3
- **Base commit:** 9a7249d
- **Submitted commit:** e7728c9

## Role Map
| Role | Class | Source |
|---|---|---|
| Abstraction | DrawingShape | src/DrawingShape.java |
| A1 | Circle | src/Circle.java |
| A2 | Square | src/Square.java |
| Implementor | Renderer | src/Renderer.java |
| I1 | VectorRenderer | src/VectorRenderer.java |
| I2 | RasterRenderer | src/RasterRenderer.java |
| I3 | AsciiRenderer | src/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Key Locations
- **Bridge field:** `DrawingShape.renderer` (protected `Renderer` reference, supplied via the `DrawingShape(int, Renderer)` constructor) — `src/DrawingShape.java`
- **`execute()`:** declared abstract in `DrawingShape`, implemented in `Circle` (calls `renderer.renderCircle(radius)`) and `Square` (calls `renderer.renderSquare(side)`)
- **`setImplementation(...)`:** `DrawingShape.setImplementation(Renderer)` — swaps the implementor reference on the same abstraction object at runtime
- **T5 runtime-switch check:** `Main.demo()` — creates `Circle c5 = new Circle(5, 2, vector)`, keeps `Circle sameCircle = c5`, calls `c5.setImplementation(raster)`, then verifies `c5 == sameCircle`, `c5.getId() == 5`, `c5.getRadius() == 2`, and that the result switched from `VECTOR circle radius=2` to `RASTER circle radius=2`

## Build & Run
From the project root:

    javac --release 17 -encoding UTF-8 -d out "sources.txt"
    java -cp out Main --demo

## Expected Results (T1–T7)

    T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
    T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
    T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
    T4 PASS | Square + RasterRenderer | result=RASTER square side=3
    T5 PASS | Circle + VectorRenderer -> RasterRenderer | sameObject=true | sameId=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2
    T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
    T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
    SUMMARY: 7/7 PASS
