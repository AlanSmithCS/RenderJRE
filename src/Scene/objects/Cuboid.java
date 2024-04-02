package Scene.objects;
import Scene.objects.dependencies.*;

import org.json.simple.JSONObject;
import rMath.*;
import java.awt.Color;

public class Cuboid extends Shape {
    private void generateVertices(float width, float length, float height) {
        Vertex A = new Vertex(1, 1, 1);
        Vertex B = new Vertex(1, 1, -1);
        Vertex C = new Vertex(1, -1, 1);
        Vertex D = new Vertex(1, -1, -1);

        Vertex E = new Vertex(-1, 1, 1);
        Vertex F = new Vertex(-1, 1, -1);
        Vertex G = new Vertex(-1, -1, 1);
        Vertex H = new Vertex(-1, -1, -1);

        vertices = new Vertex[]{A,B,C,D,E,F,G,H};

        for (Vertex v : vertices) { // change to fill 1x1 space
            v.x *= width/2;
            v.y *= height/2;
            v.z *= length/2;
        }

        Vertex[][] faces = { // triangles
                {E, A, B}, {B, F, E},
                {D, H, F}, {F, B, D},
                {B, A, C}, {C, D, B},
                {A, E, G}, {G, C, A},
                {E, F, H}, {H, G, E},
                {C, G, H}, {H, D, C}
        };

        for (Vertex[] face : faces) {
            triangles.add(new Triangle(face[0], face[1], face[2]));
        }
    }
    public Cuboid(float x, float y, float z, float width, float length, float height, float scale, Color colour) {
        super(x, y, z, scale, colour);

        generateVertices(width, length, height);
    }

    public Cuboid(float x, float y, float z, JSONObject size, Color color) {
        super(x, y, z, (float) size.get("scale"), color);

        generateVertices((float) size.get("width"), (float) size.get("length"), (float) size.get("height"));
    }
}
