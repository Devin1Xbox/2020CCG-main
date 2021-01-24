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

@Autonomous(name = "Autonomous_2", group = "Autonomous")
public class Autonomous_2 extends Robot {

    ElapsedTime wobblePowerChange = new ElapsedTime();
    OpenCvInternalCamera phoneCam;
    SkystoneDeterminationPipeline pipeline;
    int ringNumber;

    @Override
    public void runOpMode() {
        super.runOpMode();

        waitForStart();

        runtime.reset();

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
            wobblePowerChange.reset();
            while(wobblePowerChange.milliseconds() < 750) {
                this.armWobble(-1.0);
            }
            this.armWobble(-0.6);
            this.goForwardsInInches(6);
            this.turnLeftInMilli(125);
            ElapsedTime ringDetectionTimeLimit = new ElapsedTime();
            while(ringDetectionTimeLimit.milliseconds() < 2000) {
                detectRingNumber();
            }
            this.turnRightInMilli(150);
            this.strafeRightInInches(4);
            //alright so we're gonna detect the amount of rings in a fixed position--if it's 0, then we'll have to set a time to stop moving by
            if(ringNumber == 0) {
                //no rings, go to A on the bottom
                this.goForwardsInInches(80);
                this.strafeLeftInInches(20);
                //drop wobble boi
                this.armWobble(0.0);
                this.wobbleServo.setPosition(0.6);
                this.sleep(1000);
                this.stopMotors();
            } else if(ringNumber == 1) {
                //1 ring, go to B in the middle
                this.goForwardsInInches(120);
                this.strafeRightInInches(4);
                //drop wobble boi
                this.armWobble(0.0);
                this.wobbleServo.setPosition(0.6);
                this.sleep(1000);                                  //  𝘣𝘳𝘦𝘢𝘬𝘥𝘢𝘯𝘤𝘦𝘴
                this.goBackwardsInInches(25);
                this.stopMotors();
            } else {
                //4 rings, go to C on the top
                this.goForwardsInInches(144);
                this.strafeLeftInInches(16);
                this.turnLeftInMilli(50);
                this.strafeLeftInInches(10);
                //drop wobble boi
                this.armWobble(0.0);
                this.wobbleServo.setPosition(0.6);
                this.sleep(2000);
                this.goBackwardsInInches(65);
                this.stopMotors();
            }
            this.stop();
        }
    }

//all motivation is gone

    void detectRingNumber() {
        if(pipeline.getAnalysis() > 146) {
            ringNumber = 4;
        } else if(pipeline.getAnalysis() < 146 && pipeline.getAnalysis() >= 136) {
            ringNumber = 1;
        } else {
            ringNumber = 0;
        }
        telemetry.addData("Number of Rings", ringNumber);
        telemetry.addData("Color Analysis", pipeline.getAnalysis());
        telemetry.update();
    }
}