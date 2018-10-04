package sample.controller;


import com.jfoenix.controls.JFXTextField;
import entities.Plane;
import entities.Wing;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.LwjglMain;

public class Controller {


    public JFXTextField Lift_id,Drag_id,Thrust_id,Weigh_id,Speed_id,Acceleration_id,Mass_id,Wing_Area_id,Elevatorsr_id,Rudder_id,Gravity_id,Ailerons_id;
    public Label Wing_Area_L,Thrust_L,Lift_L,Drag_L,Weigh_L,Gravity_L,Mass_L,Acceleration_L,Speed_L,Ailerons_L,Elevators_L,Rudder_L;
    LwjglMain lwjglMain=new LwjglMain();
    public Controller(){

        Thread t=new Thread((Runnable) lwjglMain);
        t.start();

    }

    public void Lift_F(ActionEvent actionEvent) {
        if(!Lift_id.getText().isEmpty()){
            Plane.setLift(Float.parseFloat(Lift_id.getText()));
        }
    }

    public void Drag_F(ActionEvent actionEvent) {
        if(!Drag_id.getText().isEmpty()){
            Plane.setDrag(Float.parseFloat(Drag_id.getText()));
        }
    }

    public void Thrust_F(ActionEvent actionEvent) {
        if(!Thrust_id.getText().isEmpty()){
            Plane.setThrust(Float.parseFloat(Thrust_id.getText()));
        }
    }

    public void Weigh_F(ActionEvent actionEvent) {
        if(!Weigh_id.getText().isEmpty()){
            Plane.setWeigh(Float.parseFloat(Weigh_id.getText()));
        }
    }

    public void Speed_F(ActionEvent actionEvent) {
        if(!Speed_id.getText().isEmpty()){
            Plane.setSPEED(Float.parseFloat(Speed_id.getText()));
        }
    }

    public void Acceleration_F(ActionEvent actionEvent) {
        if(!Acceleration_id.getText().isEmpty()){
            Plane.setAcceleration(Float.parseFloat(Acceleration_id.getText()));
        }
    }

    public void Mass_F(ActionEvent actionEvent) {
        if(!Mass_id.getText().isEmpty()){
            Plane.setMass(Float.parseFloat(Mass_id.getText()));
        }
    }

    public void Wing_Area_F(ActionEvent actionEvent) {
        if(!Wing_Area_id.getText().isEmpty()){
            Plane.setWing_Area(Float.parseFloat(Wing_Area_id.getText()));
        }
    }


    public void Gravity_F(ActionEvent actionEvent) {
        if(!Gravity_id.getText().isEmpty()){
            Plane.setGravity(Float.parseFloat(Gravity_id.getText()));
        }
    }

    public void Ailerons_F(ActionEvent actionEvent) {
    }

    public void Rudder_F(ActionEvent actionEvent) {
    }

    public void Elevators_F(ActionEvent actionEvent) {

        Plane.setAngel(Float.parseFloat(Elevatorsr_id.getText()));
    }


    public void Initialize(MouseEvent mouseEvent) {
        Lift_L.setText(Plane.getLift()+"");
        Drag_L.setText(Plane.getDrag()+"");
        Thrust_L.setText(Plane.getThrust()+"");
        Wing_Area_L.setText(Plane.getWing_Area()+"");
        Speed_L.setText(Plane.getSPEED()+"");
        Wing_Area_L.setText(Plane.getWing_Area()+"");
        Acceleration_L.setText(Plane.getAcceleration()+"");
        Mass_L.setText(Plane.getMass()+"");
        Weigh_L.setText(Plane.getWeigh()+"");
        Gravity_L.setText(Plane.getGravity()+"");
    }




}
