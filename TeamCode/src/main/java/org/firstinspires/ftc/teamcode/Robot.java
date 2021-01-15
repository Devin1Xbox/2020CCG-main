package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

//import static org.firstinspires.ftc.teamcode.EasyOpenCVExample.SkystoneDeterminationPipeline.REGION1_TOPLEFT_ANCHOR_POINT;
//import static org.firstinspires.ftc.teamcode.EasyOpenCVExample.SkystoneDeterminationPipeline.REGION_HEIGHT;
//import static org.firstinspires.ftc.teamcode.EasyOpenCVExample.SkystoneDeterminationPipeline.REGION_WIDTH;

public abstract class Robot extends LinearOpMode {


    ElapsedTime runtime = new ElapsedTime();

    ElapsedTime lockTimer = new ElapsedTime();

    Servo wobbleServo, armServoL, armServoR;

    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, armMotor, armWobble, ringGrabberArmMotor, verticalLiftMotor;

    @Override
    public void runOpMode() {
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        wobbleServo = hardwareMap.get(Servo.class, "servo");
        armWobble = hardwareMap.get(DcMotor.class, "armWobble");
        armServoL = hardwareMap.get(Servo.class, "armServoL");
        armServoR = hardwareMap.get(Servo.class, "armServoR");
        verticalLiftMotor = hardwareMap.get(DcMotor.class, "verticalLiftMotor");
        ringGrabberArmMotor = hardwareMap.get(DcMotor.class, "ringGrabberArmMotor");
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        ringGrabberArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


    void goForward(double power) {
        // func of making robot go forwards
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
    }

    void goBackward(double power) {
        // func of making robot go backwards
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
    }

    void turnLeft(double power) {
        // func of making robot go left
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(-power);
        backRightMotor.setPower(-power);
    }

    void turnRight(double power) {
        // func of making robot go right
        frontLeftMotor.setPower(-power);
        backLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
    }

    void strafeRight(double power) {
        // func of making robot strafe left **still questionable
        frontLeftMotor.setPower(-power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(-power);

    }

    void strafeLeft(double power) {
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(-power);
        frontRightMotor.setPower(-power);
        backRightMotor.setPower(power);
    }

    void stopMotors() {
        this.frontLeftMotor.setPower(0);
        this.backLeftMotor.setPower(0);
        this.frontRightMotor.setPower(0);
        this.backRightMotor.setPower(0);
    }

    void arm(double power) {
        armMotor.setPower(power);
    }

    void armWobble(double power) {
        this.armWobble.setPower(power);
    }

    void goForwardsInInches(double inches) {
        // we need to do some trial runs to find the real calculation
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime && opModeIsActive()) {
            telemetry.update();
            this.goForward(-0.75);
        }
        this.stopMotors();
        this.sleep(500);
    }

    void goBackwardsInInches(double inches) {
        // we need to do some trail runs to find the real calculation
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            telemetry.update();
            this.goBackward(0.5);
        }
        this.stopMotors();
        this.sleep(500);
    }

    void turnLeftInMilli(double milliseconds) {
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < milliseconds) {
            telemetry.update();
            this.turnLeft(0.75);
        }
        this.stopMotors();
    }

    void turnRightInMilli(double milliseconds) {
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < milliseconds) {
            telemetry.update();
            this.turnRight(0.75);
        }
        this.stopMotors();
    }

    void strafeLeftInInches(double inches) {
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            telemetry.update();
            this.strafeLeft(0.75);
        }
        this.stopMotors();
        this.sleep(500);
    }


    void strafeRightInInches(double inches) {
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime && opModeIsActive()) {
            telemetry.update();
            this.strafeRight(0.75);
        }
        this.stopMotors();
        this.sleep(2000);
    }


//this is stupid
    void toggleServoLock() {
        telemetry.addData("servo position: ", wobbleServo.getPosition());
        telemetry.update();


        if(lockTimer.seconds() > 0.25) {
            lockTimer.reset();
            if(this.wobbleServo.getPosition() > 0.2) {
                this.wobbleServo.setPosition(0.2);
            } else {
                this.wobbleServo.setPosition(0.6);
            }
        }
    }

    void openRingGrabber() {
        this.armServoR.setPosition(0.9);
        this.armServoL.setPosition(0.1);
    }

    void closeRingGrabber() {
        this.armServoR.setPosition(0.1);
        this.armServoL.setPosition(0.9);
    }

    void moveRingGrabberArm(double power) {
        this.ringGrabberArmMotor.setPower(power);
    }

    void moveVerticalLift(double power) {
        this.verticalLiftMotor.setPower(power);
    }

    void armLift(double power) {
        this.armLift(power);
    }
}