package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RepositionFoundationBlue", group = "LinearOpMode")
public class RepositionFoundationBlue extends Robot {


    @Override
    public void runOpMode() {
        super.runOpMode();
        colorSensor.enableLed(true);

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
        this.strafeLeftInInches(9);
        this.resetArm();
        whileLoopWait(1000);
//        ElapsedTime time = new ElapsedTime();
//        time.reset();
//        while (this.opModeIsActive() && time.milliseconds() < 1000) {
//            this.armMotor.setPower(0);
//            this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        }
        this.closeArm();
        this.whileLoopWait(375);
        this.openArm();
        this.whileLoopWait(375);
//        this.goBackwardsInInches(45);
        ElapsedTime Time = new ElapsedTime();
        Time.reset();
        while(opModeIsActive() && Time.milliseconds() < 4000){
            this.goBackward(0.2);
        }
        this.armMotor.setPower(0.78);
        this.strafeRightInInches(38);
        this.resetArm();
        this.turnLeftInMilli(42);
        this.closeArm();
        this.goForwardsInInches(50);
        this.strafeLeftInInches(27);
        this.goBackwardsInInches(50);
        this.strafeRightInInches(57);
        this.closeArm();
        colorSensor.enableLed(false);
        stop();
    }
}