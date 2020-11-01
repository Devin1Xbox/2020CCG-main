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

        int count = 1;

        while(opModeIsActive()) {
            telemetry.addData("range: ", String.format("%.01f mm", distanceSensor.getDistance(DistanceUnit.MM)));
            telemetry.addData("range: ", String.format("%.01f in", distanceSensor.getDistance(DistanceUnit.INCH)));
            telemetry.update();
            //uh what now
            if(isRing()) {
                this.goForwardsInInches(6);
            } else {
                if(count == 1) {
                    this.strafeLeftInInches(4);
                    this.goForwardsInInches(6);
                    count++;
                } else if(count == 2) {
                    this.strafeRightInInches(4);
                    this.goForwardsInInches(6);
                    count++;
                } else {
                    goForwardsInInches(6);
                }
            }
        }
    }
}
