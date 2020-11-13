package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "AutonomousPrototype", group = "LinearOpMode")
public class AutonomousPrototype extends Robot {

    private DcMotor motor = null;

    private DistanceSensor distanceSensor = null;

    @Override

    public void runOpMode() {

        double time = runtime.time(); //something tracking time but i know this aint it chief

        waitForStart();

        runtime.reset();

        while(opModeIsActive()) {
            telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
            telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
            telemetry.update();
            //uh what now     also tweak the isRing in Robot.java
            //alright so we're gonna detect the amount of rings in a fixed position--if it's 0, then we'll have to set a time to stop moving by
            outerloop:
            while (!isRing()) {
                this.goForward(0.5);
                if (time == 3) {
                    break outerloop;
                }
            }
            
            if(time == 3) {
                //ok i think it might be square A
                this.strafeLeftInInches(6);//who knows the exact amount we'll see
                //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
            }
            //1 ring
            if(distanceSensor.getDistance(DistanceUnit.MM) <= 25) {
                //go to square C
                this.goForwardsInInches(34); //who knows the exact amount we'll see
                this.strafeLeftInInches(6);
                //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
            } else if(distanceSensor.getDistance(DistanceUnit.MM) >= 26 && distanceSensor.getDistance(DistanceUnit.MM) <= 58) {
                //go to square B
                this.goForwardsInInches(22); //who knows the exact amount we'll see
                //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
            } else {
                this.strafeLeftInInches(5); //yeah idk but i dont wanna be a noob and write break;
            }
        }
    }
}