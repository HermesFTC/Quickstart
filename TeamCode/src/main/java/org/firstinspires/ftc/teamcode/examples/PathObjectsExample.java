package org.firstinspires.ftc.teamcode.examples;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import gay.zharel.hermes.ftc.DisplacementFollower;
import gay.zharel.hermes.ftc.Follower;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import gay.zharel.hermes.geometry.Vector2d;
import gay.zharel.hermes.paths.BezierCurves;
import gay.zharel.hermes.paths.Line;
import gay.zharel.hermes.paths.PosePath;
import gay.zharel.hermes.trajectories.DisplacementTrajectory;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

@Autonomous
@Disabled
public class PathObjectsExample extends OpMode {
    private MecanumDrive drive;
    private Follower follower;

    @Override
    public void init() {
        drive = MecanumDriveFactory.build(hardwareMap, new Pose2d(0.0, 0.0, 0.0));

        PosePath path1 = new Line(
                new Vector2d(0.0, 0.0),
                new Vector2d(10.0, 10.0)
        ).withTangentHeading();

        PosePath path2 = BezierCurves.fromPoints(
                new Vector2d(10.0, 10.0),
                new Vector2d(20.0, 20.0),
                new Vector2d(10.0, 10.0)
        ).withLinearHeading(Math.PI/4, 0.0);

        DisplacementTrajectory trajectory = drive.createTrajectory(path1.plus(path2));

        follower = new DisplacementFollower(trajectory, drive);
    }

    @Override
    public void loop() {
        if (!follower.isDone()) {
            follower.follow();
        }
    }
}
