package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ColorSensorTest", group = "LinearOpMode")
public class ColorSensorTest extends Robot {

    @Override
    public void runOpMode() {
        super.runOpMode();

        waitForStart();

        while(opModeIsActive()) {
            int red = colorSensor.red();
            int blue = colorSensor.blue();
            int green = colorSensor.green();

            telemetry.addData("currentBlueValue", blue);
            telemetry.addData("currentRedValue", red);
            telemetry.addData("currentGreenValue", green);
            telemetry.update();
        }
    }
}
