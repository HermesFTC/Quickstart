package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import gay.zharel.hermes.actions.Action;
import gay.zharel.hermes.actions.ActionRunner;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import gay.zharel.hermes.geometry.Vector2d;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

@Autonomous
@Disabled
public class ActionBuilderExample extends LinearOpMode {
    MecanumDrive drive;
    Action action;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = MecanumDriveFactory.build(hardwareMap, new Pose2d(0.0, 0.0, 0.0));
        action = drive.actionBuilder()
                .forward(10.0)
                .splineTo(new Vector2d(10.0, 10.0), Math.toRadians(90.0))
                .build();

        waitForStart();

        ActionRunner.runBlocking(action);
    }
}
