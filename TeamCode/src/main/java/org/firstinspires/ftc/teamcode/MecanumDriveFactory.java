package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import gay.zharel.hermes.ftc.MecanumDrive;
import gay.zharel.hermes.ftc.MecanumDriveBuilder;
import gay.zharel.hermes.geometry.Pose2d;

@Config
public class MecanumDriveFactory {
    // path profile parameters (in inches)
    public static double maxWheelVel = 50;
    public static double minProfileAccel = -30;
    public static double maxProfileAccel = 50;

    // turn profile parameters (in radians)
    public static double maxAngVel = Math.PI; // shared with path
    public static double maxAngAccel = Math.PI;

    // path controller gains
    public static double axialPosGain = 0.0;
    public static double lateralPosGain = 0.0;
    public static double headingPosGain = 0.0; // shared with turn

    public static double axialVelGain = 0.0;
    public static double lateralVelGain = 0.0;
    public static double headingVelGain = 0.0; // shared with turn

    public static MecanumDriveBuilder builder() {
        return new MecanumDriveBuilder()
                .withAxialGains(axialPosGain, axialVelGain)
                .withLateralGains(lateralPosGain, lateralVelGain)
                .withHeadingGains(headingPosGain, headingVelGain)
                .withMaxWheelVel(maxWheelVel)
                .withTransAccelLimits(minProfileAccel, maxProfileAccel)
                .withMaxAngVel(maxAngVel)
                .withMaxAngAccel(maxAngAccel);
    }

    public static MecanumDrive build(HardwareMap hardwareMap, Pose2d startPose) {
        return builder().build(hardwareMap, startPose);
    }
}