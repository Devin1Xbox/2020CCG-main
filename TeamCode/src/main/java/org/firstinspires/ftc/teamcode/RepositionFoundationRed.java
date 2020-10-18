package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "RepositionFoundationRed", group = "LinearOpMode")
public class RepositionFoundationRed extends Robot {


        @Override
        public void runOpMode() {
//                colorSensor.enableLed(true);
                super.runOpMode();

                waitForStart();
                armMotor.setDirection(DcMotor.Direction.REVERSE);
                telemetry.addData("frontLeft Position", frontLeftMotor.getCurrentPosition());
                telemetry.addData("backLeft position", backLeftMotor.getCurrentPosition());
                telemetry.addData("frontRight position", frontRightMotor.getCurrentPosition());
                telemetry.addData("backRight position", backRightMotor.getCurrentPosition());
                telemetry.update();


                this.armMotor.setPower(1.0);
                this.whileLoopWait(500);
                this.openArm();
                this.goForwardsInInches(32.6);
                this.strafeRightInInches(11);
                this.resetArm();
                whileLoopWait(1000);
                this.closeArm();
                this.whileLoopWait(375);
                this.openArm();
                this.whileLoopWait(375);
                this.goBackwardsInInches(45);
                this.armMotor.setPower(1.0);
                this.whileLoopWait(500);
                this.strafeLeftInInches(38);
                this.resetArm();
                this.closeArm();
                turnRightInMilli(32);
                this.goForwardsInInches(50);
                this.strafeRightInInches(27);
                this.goBackwardsInInches(60);
                this.strafeLeftInInches(52);
                this.closeArm();
                stop();
        }
}