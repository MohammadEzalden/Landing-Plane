package entities;

import models.TexturedModel;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import sample.Main;
import terrains.Terrain;

import java.math.MathContext;

public class Plane extends Entity{
	
	private static float SPEED = 125f;
	private static float dt=0.009f;
	private static float lift;
	private static float Mass=11644,Gravity=9.8f,Weigh;
	private static float Wing_Area=77*2;
	private static float Lift_Coefficient=0.52f;
	//private static float Drag_Coefficient=0.04f;
	private static float Drag_Coefficient=0.0265f;
	private static float Acceleration;
	private static float Density=1.1939f;
	private static float drag,thrust=100000;
	private static float angle;
	private static Vector3f pos;
	/**Rolling_Resistance*/
	private static float Rolling_Resistance;
	private static float Acceleration_Rolling_Resistance;
	private static float coefficient_of_rolling_resistance = 0.014f;
	/**   Elevators */
	private static float Torque_Elevators ;
	private static float Wing_Area_Elevators=100 ;
	private static float lift_Elevators ;
	private static float distance=50 ;
	private static float anglePitch;
	private static float Lift_Coefficient_Elevators ;

	public void move() {
		checkInputs();
		//calc_Pitch();


		if(Run){
			if(!landing) {
				calc_Force();
				increasePosition(0, distanceY(), distanceZ());
			}
			if(getPosition().y<=1.3f){
				landing=true;
				calc_Force();
				setPosition(new Vector3f(getPosition().x,1.3f,getPosition().z));
				increasePosition(0,0,distanceZ());
				if(getRotX()<0){
					isDestroyed=true;
					SPEED=0;
					thrust=0;
					setRotX(0);
				}
				if(SPEED>20) {
					setRotX(10);
					Lift_Coefficient=2f;
				}
				else if(SPEED>15)
					setRotX(8);
				else if(SPEED>10)
					setRotX(6);
				else if(SPEED>5)
					setRotX(3);
				else setRotX(0);
			}
		}
		if(getRotX()<-20||getRotX()>20)Lift_Coefficient=0;
		else Lift_Coefficient = 0.52f;
		pos=getPosition();
	}

	private void checkInputs() {



		/*if(Keyboard.isKeyDown((Keyboard.KEY_W))) {
			SPEED += 1;
			Acceleration=(SPEED)/(dt*1000);
			thrust=Mass*Acceleration;
		}
		if(Keyboard.isKeyDown((Keyboard.KEY_S))) {
			SPEED -= 1;
			Acceleration=(SPEED)/(dt*1000);
			thrust=Mass*Acceleration;
		}*/
		if(Keyboard.isKeyDown((Keyboard.KEY_W))&&thrust<161722) {
			thrust += 1000;
			SPEED=thrust*(dt*1000)/Mass;
		}
		if(Keyboard.isKeyDown((Keyboard.KEY_S))&&thrust>=0) {
			thrust -= 1000;
			SPEED=thrust*(dt*1000)/Mass;
		}



		if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			increaseRotation(-1,0,0);

		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2))
			increaseRotation(1,0,0);

		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			Run=true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			SPEED=0;
		}

	}

	private void calc_Force(){
		/** Rolling Resistance */
		SPEED=thrust*dt*1000/Mass;
		if(SPEED<=0)SPEED=0;
		if(getPosition().y<=1.3f&&SPEED>0) {
			Rolling_Resistance = coefficient_of_rolling_resistance * Weigh;
			Acceleration_Rolling_Resistance = Rolling_Resistance / Mass;
			SPEED -= Acceleration_Rolling_Resistance / (dt * 1000);
			//Acceleration=(SPEED)/(dt*1000);
			//thrust=Mass*Acceleration;
			if (SPEED <= 0) SPEED = 0;
		}

		/** Lift*/
		lift=0.5f * Wing_Area * Lift_Coefficient * Density *(SPEED * SPEED);

		/** Drag*/
		drag=0.5f * Drag_Coefficient * Density * SPEED* Wing_Area;
		SPEED-=drag*(dt*1000)/Mass;
		if(SPEED<=0)SPEED=0;
		/** Thrust*/
		Acceleration=(SPEED)/(dt*1000);
		thrust=Mass*Acceleration;

		/** Weigh*/
		Weigh=Mass*Gravity;


	}
	private void calc_Pitch(){
		Lift_Coefficient_Elevators= (float) (2*Math.PI*Elevators.getAngleElevators());
		lift_Elevators=0.5f * Wing_Area_Elevators * Lift_Coefficient_Elevators * Density *(SPEED * SPEED);
		Torque_Elevators=lift_Elevators *distance;
		anglePitch=Torque_Elevators*(dt*dt*1000)/Mass;
		//System.out.println(anglePitch);
		//setRotX(anglePitch);
	}


	private float distanceY() {
		if(getRotX()<0)
			lift= (float) (lift*Math.cos(getRotX()));
		if(getRotX()>0)
			Weigh= (float) (Weigh*Math.cos(getRotX()));
		return ((lift-Weigh)*dt*dt*1000)/Mass;
	}
	private float distanceZ(){
		return -SPEED/(dt*1000);
		//return -(((thrust-drag) *dt*dt*1000)/Mass);
	}

	public static void setSPEED(float SPEED) {
		Plane.SPEED = SPEED;
	}

	public static void setDt(float dt) {
		Plane.dt = dt;
	}

	public static void setLift(float lift) {
		Plane.lift = lift;

		SPEED= (float) Math.sqrt(lift/(0.5f * Wing_Area * Lift_Coefficient * Density));

	}

	public static void setMass(float mass) {
		Mass = mass;
	}

	public static void setWeigh(float weigh) {
		Weigh = weigh;
		Mass=Weigh/Gravity;
	}

	public static void setWing_Area(float wing_Area) {
		Wing_Area = wing_Area;
	}

	public static void setLift_Coefficient(float lift_Coefficient) {
		Lift_Coefficient = lift_Coefficient;
	}

	public static void setDrag_Coefficient(float drag_Coefficient) {
		Drag_Coefficient = drag_Coefficient;
	}

	public static void setAcceleration(float acceleration) {
		Acceleration = acceleration;

		SPEED=Acceleration*dt*1000;
	}

	public static void setDensity(float density) {
		Density = density;
	}

	public static void setDrag(float drag) {
		Plane.drag = drag;

		SPEED=drag/(0.5f * Drag_Coefficient * Density *  Wing_Area);
	}

	public static void setThrust(float thrust) {
		Plane.thrust = thrust;

		Acceleration=thrust/Mass;
		SPEED=Acceleration*dt*1000;
	}

	public static void setAngel(float angel) {
		Plane.angle = angel;
	}

	public static void setPos(Vector3f pos) {
		Plane.pos = pos;
	}

	public static float getSPEED() {
		return SPEED;
	}

	public static void setGravity(float gravity) {
		Gravity = gravity;
	}

	/****************
	 * getter
	 * @return
	 */
	public static float getGravity() {
		return Gravity;
	}

	public static float getDt() {
		return dt;
	}

	public static float getLift() {
		return lift;
	}

	public static float getMass() {
		return Mass;
	}

	public static float getWeigh() {
		return Weigh;
	}

	public static float getWing_Area() {
		return Wing_Area;
	}

	public static float getLift_Coefficient() {
		return Lift_Coefficient;
	}

	public static float getDrag_Coefficient() {
		return Drag_Coefficient;
	}

	public static float getAcceleration() {
		return Acceleration;
	}

	public static float getDensity() {
		return Density;
	}

	public static float getDrag() {
		return drag;
	}

	public static float getThrust() {
		return thrust;
	}

	public static float getAngel() {
		return angle;
	}

	public static Vector3f getPos() {
		return pos;
	}

	public static boolean isIsDestroyed() {
		return isDestroyed;
	}

	public static void setIsDestroyed(boolean isDestroyed) {
		Plane.isDestroyed = isDestroyed;
	}

	private static boolean isDestroyed=false;
	private boolean Run = false,landing=false;

	public Plane(TexturedModel model, Vector3f position, float rotX,
				 float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

}
