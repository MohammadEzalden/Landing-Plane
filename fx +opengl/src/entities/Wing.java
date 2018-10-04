package entities;

import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Point;

public class Wing extends Entity{

    private static float RUN_SPEED = 500f;
    private static float lift;


    public void move() {
        checkInputs();

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
            increaseRotation(-0.9f,0,0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_P)){
            increaseRotation(0.9f,0,0);
        }
    }



    private static float mass=23813,w=mass*9.8f;
    private static float drag,thrust;


    public Wing(TexturedModel model, Vector3f position, float rotX,
                float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }





}














/*if(Keyboard.isKeyDown(Keyboard.KEY_0)&&b) {
            b=false;
            setRotX(-9.4f);
            Vector3f pos=getPosition();
            pos.y-=0.004002f;//9.995998f;
            pos.z+=0.1399982f;//0.6399982
            setPosition(pos);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_9)&&b){
            b=false;
            setRotX(-26.600065f);
            Vector3f pos=getPosition();
            pos.y+=0.028012f;//10.02401
            pos.z+=0.11299854f;//0.75299674
            setPosition(pos);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_8)&&b){
            b=false;
            setRotX(-9.4f);
            Vector3f pos=getPosition();
            pos.y-=0.028012f;
            pos.z-=0.11299854f;
            setPosition(pos);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_7)&&b){
            b=false;
            setRotX(10.900005f);
            Vector3f pos=getPosition();
            pos.y+=0.004002f;
            pos.z-=0.1399982f;
            setPosition(pos);
        }*/