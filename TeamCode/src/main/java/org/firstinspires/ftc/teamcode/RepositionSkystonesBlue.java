package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RepositionSkystonesBlue", group = "LinearOpMode")
public class RepositionSkystonesBlue extends Robot {


    @Override
    public void runOpMode() {
        super.runOpMode();

        waitForStart();
        while(opModeIsActive()) {
//            colorSensor.enableLed(true);
            armMotor.setDirection(DcMotor.Direction.REVERSE);
            int red = colorSensor.red();
            int blue = colorSensor.blue();
            int green = colorSensor.green();
            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();

            this.armMotor.setPower(1.0);
            this.whileLoopWait(1000);
            this.openArm();
            ElapsedTime time = new ElapsedTime();
            runtime.reset();
            while (this.opModeIsActive() && time.milliseconds() < 1000) {
                this.armMotor.setPower(0);
                this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }

            goForwardsInInches(18);
            strafeLeftInInches(13);
            goForwardsInInches(1);
            turnLeftInMilli(10);
            Log.i("REPOSITION-CLASS", "red: " + this.red);
            strafeRightUntilDetectSkystone();
            this.stopMotors();
//            runtime.reset();
            this.strafeRightInInches(0.66);
//            goForwardsInInches(14);
            time.reset();
            while(time.milliseconds() < 2000) {
                this.goForward(-0.2);
            }
            this.stopMotors();
            closeArm();
            goBackwardsInInches(38);
            strafeLeftInInches(70);
            openArm();
            goForwardsInInches(5);
            goBackwardsInInches(7);
            strafeRightInInches(26);
//            colorSensor.enableLed(false);
            stop();
        }
    }
}