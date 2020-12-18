package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

@Autonomous(name = "AutonomousPrototype", group = "Autonomous")
public class AutonomousPrototype extends Robot {

    @Override
    public void runOpMode() {
        super.runOpMode();

        double time = runtime.time(); //something tracking time but i know this aint it chief

        waitForStart();

        runtime.reset();

        while(opModeIsActive()) {
            telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
            telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
            telemetry.addData("rings: ", isRing());
            telemetry.update();
            //alright so we're gonna detect the amount of rings in a fixed position--if it's 0, then we'll have to set a time to stop moving by
//            while (isRing() == 0 && time <= 3000) {
////                this.goForward(0.5);
////                if (time >= 3000) {
////                    //ok i think it might be square A
////                    this.strafeLeftInInches(6);//who knows the exact amount we'll see
////                    //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
////                    break;
////                }
////            }
////
////            //1 ring
////            if(isRing() == 1) {
////                //go to square C
////                this.goForwardsInInches(34); //who knows the exact amount we'll see
////                this.strafeLeftInInches(6);
////                //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
////            } else if(isRing() == 3) {
////                //go to square B
////                this.goForwardsInInches(22); //who knows the exact amount we'll see
////                //something with arm extending, then dropping servo, then turning armWobble until it drops ----MUCH TESTING NEEDED BUT WILL BE UNIVERSAL
////            } else {
////                this.strafeLeftInInches(5); //yeah idk but i dont wanna be a noob and write break;
////            }

            this.goForwardsInInches(3);
            this.strafeRightInInches(5);
            stopMotors();
            if(isRing() == 4) {

            }
//            if(isRing() == 0) {
//                this.goForwardsInInches(14);
//            } else if(isRing() == 1) {
//
//            }
            while(opModeIsActive()) {
                telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
                telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
                telemetry.addData("rings: ", isRing());
                telemetry.update();
            }
        }
    }
}