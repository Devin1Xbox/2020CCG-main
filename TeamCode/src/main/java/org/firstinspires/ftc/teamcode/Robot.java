package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;

public abstract class Robot extends LinearOpMode {


    ElapsedTime runtime = new ElapsedTime();

    ColorSensor colorSensor;

    Servo armServo, armServo1;

    DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, armMotor;

    int red, green, blue;

    @Override
    public void runOpMode() {
        System.out.println("This is the hardware map from ROOBT" + hardwareMap);
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        armServo = hardwareMap.get(ServoImpl.class, "armServo");
        armServo1 = hardwareMap.get(ServoImpl.class, "armServo1");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");

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

    void raiseArmHalf() {
        armMotor.setPower(-0.65);
        this.sleep(1000);
    }

    void resetArm() {
        armMotor.setPower(0);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        telemetry.addData("armMotor", armMotor.getPower());
        telemetry.update();
    }

    void openArm() {
        armServo.setPosition(1);
        armServo1.setPosition(0);
    }

    void closeArm() {
        armServo.setPosition(0);
        this.sleep(213);
        armServo1.setPosition(1);
    }

    void goForwardsInInches(double inches) {
        // we need to do some trail runs to find the real calculation
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime && opModeIsActive()) {
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
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
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.goBackward(0.375);
        }
        this.stopMotors();
        this.sleep(500);
    }

    void turnLeftInMilli(double milliseconds) {
        // we need to do some trail runs to find the real calculation
        double calculatedTime = milliseconds;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
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
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.turnRight(-0.75);
        }
        this.stopMotors();
    }

    void strafeLeftInInches(double inches) {
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.strafeLeft(0.75);
        }
        this.stopMotors();
        this.sleep(500);
    }

    void strafeLeftUntilDetectSkystone() {
        this.runtime.reset();

        while (
                this.opModeIsActive() && // user hasn't hit the stop button
                        this.runtime.milliseconds() < 3.5 && // strafe for 10 seconds max in case it doesn't find the skystone
                        !isSkystone()
        ) {
            // nested while loop for compensating for inconsistent strafing
            // every 1.6 seconds we turn slightly to the right because it doesn't strafe straight
//            ElapsedTime timer = new ElapsedTime();
//            timer.reset();
//            Log.i("OUTER-LOOP", "red: " + red);
//            while (timer.seconds() < 1) {
//                if(red <= 19 && red > 14) {
//                    break;
//                }
//                Log.i("INNER-LOOP", "red: " + red);
//                red = colorSensor.red();
//                blue = colorSensor.blue();
//                green = colorSensor.green();
//                telemetry.addData("currentBlueValue", blue);
//                telemetry.addData("currentRedValue", red);
//                telemetry.addData("currentGreenValue", green);
//                telemetry.update();
//                this.strafeLeft(0.25);
//            }
//            if (timer.seconds() >= 1) {
//                turnRightInMilli(50);
//                goForwardsInInches(.3);
//            }
            Log.i("STRAFING", "red: " + red);
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.strafeLeft(0.25);

        }
    }

    void strafeRightUntilDetectSkystone() {
        this.runtime.reset();

        while (
                this.opModeIsActive() && // user hasn't hit the stop button
                        this.runtime.milliseconds() < 5000 && // strafe for 10 seconds max in case it doesn't find the skystone
                        !isSkystone()
        ) {
            // nested while loop for compensating for inconsistent strafing
            // every 1.6 seconds we turn slightly to the right because it doesn't strafe straight
//            ElapsedTime timer = new ElapsedTime();
//            timer.reset();
//            Log.i("OUTER-LOOP", "red: " + red);
//            while (timer.seconds() < 1) {
//                if(red <= 19 && red > 14) {
//                    break;
//                }
//                Log.i("INNER-LOOP", "red: " + red);
//                red = colorSensor.red();
//                blue = colorSensor.blue();
//                green = colorSensor.green();
//                telemetry.addData("currentBlueValue", blue);
//                telemetry.addData("currentRedValue", red);
//                telemetry.addData("currentGreenValue", green);
//                telemetry.update();
//                this.strafeLeft(0.25);
//            }
//            if (timer.seconds() >= 1) {
//                turnRightInMilli(50);
//                goForwardsInInches(.3);
//            }
            Log.i("STRAFING", "red: " + red);
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.strafeRight(0.25);

        }
    }

    boolean isSkystone() {
        if (red < 19 && red > 14){
            Log.i("SKYSTONE-DETECT", "true");
            return true;
        }
        Log.i("SKYSTONE-DETECT", "false");
        return false;
    }

    void strafeRightInInches(double inches) {
        double calculatedTime = inches * 36.73469388;
        this.runtime.reset();

        while (this.opModeIsActive() && this.runtime.milliseconds() < calculatedTime) {
            red = colorSensor.red();
            blue = colorSensor.blue();
            green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
            this.strafeRight(0.75);
        }
        this.stopMotors();
        this.sleep(2000);
    }

    void encodedGoForwards(int position, double power) {

        int timeout = 2;

        this.runtime.reset();
        this.stopAndResetEncoder();
        this.setRunUsingEncoder();

        frontLeftMotor.setTargetPosition(-position);
        backLeftMotor.setTargetPosition(position);
        frontRightMotor.setTargetPosition(position);
        backRightMotor.setTargetPosition(-position);


        this.setRunToPosition();

        goForward(power);


        while (
                opModeIsActive()
                        && (Math.abs(this.frontLeftMotor.getCurrentPosition()) < position)
        ) {
            int positionFL = this.frontLeftMotor.getCurrentPosition();
            int positionBL = this.backLeftMotor.getCurrentPosition();
            int positionFR = this.frontRightMotor.getCurrentPosition();
            int positionBR = this.backRightMotor.getCurrentPosition();

            System.out.println("OMG is this even working:::::::" + positionFL);
            telemetry.addData("frontLeft Position", positionFL);
            telemetry.addData("backLeft position", positionBL);
            telemetry.addData("frontRight position", positionFR);
            telemetry.addData("backRight position", positionBR);
            telemetry.update();

        }
    }

    void whileLoopWait(double ms) {
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while (this.opModeIsActive() && time.milliseconds() < 2000) {
            // do nothing; wait for x seconds
        }
    }

    void stopAndResetEncoder() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    void setRunToPosition() {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    void setRunUsingEncoder() {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
