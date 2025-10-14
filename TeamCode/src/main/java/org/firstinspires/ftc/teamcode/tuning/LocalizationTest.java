package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import gay.zharel.fateweaver.flight.FlightLogChannel;
import gay.zharel.fateweaver.flight.FlightRecorder;
import gay.zharel.hermes.actions.Drawing;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.geometry.Pose2d;
import gay.zharel.hermes.geometry.PoseVelocity2d;
import gay.zharel.hermes.geometry.Vector2d;
import gay.zharel.hermes.logs.PoseMessage;
import org.firstinspires.ftc.teamcode.MecanumDriveFactory;

public class LocalizationTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MecanumDrive drive = MecanumDriveFactory.build(hardwareMap, new Pose2d(0, 0, 0));
        FlightLogChannel<PoseMessage> poseChannel = FlightRecorder.createChannel("poses", PoseMessage.class);

        waitForStart();

        while (opModeIsActive()) {
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

            drive.updatePoseEstimate();

            Pose2d pose = drive.getLocalizer().getPose();
            telemetry.addData("x", pose.position.x);
            telemetry.addData("y", pose.position.y);
            telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
            telemetry.update();

            poseChannel.put(new PoseMessage(pose));
            FlightRecorder.timestamp();

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), pose, 9.0);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
