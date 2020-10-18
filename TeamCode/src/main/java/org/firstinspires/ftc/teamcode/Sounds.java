package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.lynx.commands.core.LynxSetMotorConstantPowerCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.io.File;

@TeleOp(name = "Sounds", group = "LinearOpMode")
public class Sounds extends LinearOpMode {

    private File goldFile = new File("C:/Users/first/Downloads/movie_1.mp3");

    public void runOpMode() throws InterruptedException {

        waitForStart();

        while(opModeIsActive()) {
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldFile);
            wait(1000);
        }
    }
}
