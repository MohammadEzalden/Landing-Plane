package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Elevators extends Entity{

    private static float angleElevators ;

    public static float getAngleElevators() {
        return angleElevators;
    }

    public void move() {
        angleElevators=getRotX();
        checkInputs();


        //setPosition(new Vector3f(Plane.getPos().x,Plane.getPos().y,Plane.getPos().z+3.55f));
        setPosition(Plane.getPos());




    }


    private void checkInputs() {

        if(Keyboard.isKeyDown((Keyboard.KEY_W)))
            super.increasePosition(0,0,-0.001f);
        if(Keyboard.isKeyDown((Keyboard.KEY_S)))
            super.increasePosition(0,0,+0.001f);

        if(Keyboard.isKeyDown((Keyboard.KEY_D)))
            super.increasePosition(0.001f,0,0);
        if(Keyboard.isKeyDown((Keyboard.KEY_A)))
            super.increasePosition(-0.001f,0,0);

        if(Keyboard.isKeyDown((Keyboard.KEY_Q)))
            super.increasePosition(0,0.001f,0);
        if(Keyboard.isKeyDown((Keyboard.KEY_E)))
            super.increasePosition(0,-0.001f,-0);




        if(Keyboard.isKeyDown(Keyboard.KEY_O)){
            increaseRotation(-0.09f,0,0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_P)){
            increaseRotation(0.09f,0,0);
        }
    }





    public Elevators(TexturedModel model, Vector3f position, float rotX,
                float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }
}
