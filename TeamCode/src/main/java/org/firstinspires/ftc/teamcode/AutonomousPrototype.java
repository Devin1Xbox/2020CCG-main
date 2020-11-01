package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "AutonomousPrototype", group = "LinearOpMode")
public class AutonomousPrototype extends Robot {

    private DcMotor motor = null;

    private DistanceSensor distanceSensor = null;

    @Override

    public void runOpMode() {

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
            telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
            telemetry.update();
            //uh what now

        }
    }
}
