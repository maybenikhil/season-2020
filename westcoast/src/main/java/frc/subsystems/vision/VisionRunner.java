package frc.subsystems.vision;

public interface VisionRunner {
    public void init();
    public void periodic();
    public boolean hasTarget();
    public double xOffset();
    public double getAlignX();
    public double yOffset();
    public double height();
    public double getDistance();
}
