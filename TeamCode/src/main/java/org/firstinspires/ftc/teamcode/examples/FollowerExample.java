package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import gay.zharel.hermes.ftc.DisplacementFollower;
import gay.zharel.hermes.ftc.Follower;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import gay.zharel.hermes.geometry.Vector2d;
import gay.zharel.hermes.math.Arclength;
import gay.zharel.hermes.trajectories.Trajectory;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

@Autonomous
@Disabled
public class FollowerExample extends OpMode {
    private MecanumDrive drive;
    private Follower follower;

    @Override
    public void init() {
        drive = MecanumDriveFactory.build(hardwareMap, new Pose2d(0.0, 0.0, 0.0));
        Trajectory<Arclength> traj = drive.trajectoryBuilder()
                .forward(10.0)
                .splineTo(new Vector2d(10.0, 10.0), Math.toRadians(90.0))
                .buildToComposite();

        follower = new DisplacementFollower(traj, drive);
    }

    @Override
    public void loop() {
        if (!follower.isDone()) {
            follower.follow();
        }
    }
}
