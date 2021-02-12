package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

import org.firstinspires.ftc.teamcode.EasyOpenCVExample.SkystoneDeterminationPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Autonomous_FailSafeRed", group = "Autonomous")
public class Autonomous_FailSafeRed extends Robot {

    ElapsedTime wobblePowerChange = new ElapsedTime();
    OpenCvInternalCamera phoneCam;
    SkystoneDeterminationPipeline pipeline;
    int ringNumber;

    @Override
    public void runOpMode() {
        super.runOpMode();

        waitForStart();

        runtime.reset();

        wobblePowerChange.reset();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);

        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
        });

        waitForStart();

        while (opModeIsActive()) {
            while(wobblePowerChange.milliseconds() < 7000) {
                this.stopMotors();
            }
            while(wobblePowerChange.milliseconds() < 7750) {
                this.armWobble(-1.0);
            }
            this.armWobble(-0.6);
            while(wobblePowerChange.milliseconds() < 8000) {
                this.stopMotors();
            }
            this.strafeLeftInInches(103);
            this.goForwardsInInches(90);
            this.stop();
        }
    }
}

