package org.firstinspires.ftc.teamcode.tuning;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import gay.zharel.hermes.actions.ActionRunner;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import gay.zharel.hermes.geometry.Vector2d;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(0, 0, 0);
        MecanumDrive drive = MecanumDriveFactory.build(hardwareMap, beginPose);

        waitForStart();

        ActionRunner.runBlocking(
            drive.actionBuilder(beginPose)
                    .splineTo(new Vector2d(30, 30), Math.PI / 2)
                    .splineTo(new Vector2d(0, 60), Math.PI)
                    .build());

    }
}
