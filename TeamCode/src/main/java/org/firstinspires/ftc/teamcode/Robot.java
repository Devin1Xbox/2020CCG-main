package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public abstract class Robot extends LinearOpMode {


    ElapsedTime runtime = new ElapsedTime();

    ElapsedTime lockTimer = new ElapsedTime();

    DistanceSensor distanceSensor;

    Servo wobbleServo;

    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, armMotor, armWobble;

    @Override
    public void runOpMode() {
        System.out.println("This is the hardware map from ROOBT" + hardwareMap);
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        wobbleServo = hardwareMap.get(Servo.class, "servo");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
        armWobble = hardwareMap.get(DcMotor.class, "armWobble");
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
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
        frontLeftMotor.setPower(-power);
        backLeftMotor.setPower(-power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
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
        telemetry.addData("armMotor", power);
        telemetry.update();
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
        // we need to do some trail runs to find the real calculation
        double calculatedTime = milliseconds;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            telemetry.update();
            this.turnLeft(-0.75);
        }
        this.stopMotors();
    }

    void turnRightInMilli(double milliseconds) {
        // we need to do some trail runs to find the real calculation
        double calculatedTime = milliseconds;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            telemetry.update();
            this.turnRight(-0.75);
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

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            telemetry.update();
            this.strafeRight(0.75);
        }
        this.stopMotors();
        this.sleep(2000);
    }

    int isRing() {
        try {
            if (distanceSensor.getDistance(DistanceUnit.MM) >= 165) {
                //its a ring yay          fix the distance it needs to be
                return 0;
            } else if (distanceSensor.getDistance(DistanceUnit.MM) <= 163 && distanceSensor.getDistance(DistanceUnit.MM) >= 130) {
                return 1;
            } else if (distanceSensor.getDistance(DistanceUnit.MM) <= 120 && distanceSensor.getDistance(DistanceUnit.MM) >= 90) {
                return 3;
            } else {
                return 69420;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Your mom", e.getMessage());
            throw e;
        }
    }

    void toggleServoLock() {
        telemetry.addData("servo position: ", wobbleServo.getPosition());
        telemetry.update();


        if(lockTimer.seconds() > 0.25) {
            lockTimer.reset();
            if(this.wobbleServo.getPosition() > 0) {
                this.wobbleServo.setPosition(-1.0);
            } else {
                this.wobbleServo.setPosition(1.0);
            }
        }
    }
}