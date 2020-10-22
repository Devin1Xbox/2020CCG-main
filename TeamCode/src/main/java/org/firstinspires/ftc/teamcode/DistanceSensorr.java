package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="DistanceSensor", group="LinearOpmode")
public class DistanceSensorr extends LinearOpMode {

    private DistanceSensor distanceSensor = null;

    @Override
    public void runOpMode() {

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
            telemetry.addData("range: ", String.format("%.01f cm", distanceSensor.getDistance(DistanceUnit.CM)));
            telemetry.addData("range: ", String.format("%.01f m", distanceSensor.getDistance(DistanceUnit.METER)));
            telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
            telemetry.update();
        }
    }
}
