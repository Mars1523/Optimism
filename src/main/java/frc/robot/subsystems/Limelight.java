
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  public Limelight() {

  }

  @Override
  public void periodic() {

  }

  public void limelightLightOff() {
    table.getEntry("ledMode").setNumber(1);
  }

  public void limelightLightOn() {
    table.getEntry("ledMode").setNumber(0);
  }

  public double getX() {
    double x = tx.getDouble(0.0);
    return x;
  }

  public double getY() {
    double y = ty.getDouble(0.0);

    return y;
  }

  public double getArea() {
    double area = ta.getDouble(0.0);
    return area;
  }

  public boolean getTV() {
    int target = tv.getNumber(0).intValue();
    return target == 1;
  }
}
