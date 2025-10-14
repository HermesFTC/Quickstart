package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import gay.zharel.hermes.actions.ActionRunner;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

public final class ManualFeedbackTuner extends LinearOpMode {
    public static double DISTANCE = 64;

    @Override
    public void runOpMode() {
        MecanumDrive drive = MecanumDriveFactory.build(hardwareMap, new Pose2d(0, 0, 0));

        waitForStart();

        while (opModeIsActive()) {
            ActionRunner.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .lineToX(DISTANCE)
                        .lineToX(0)
                        .build());
        }
    }
}
