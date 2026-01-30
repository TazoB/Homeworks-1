package BridgePattern.Devices;

public interface Device {
    boolean isEnabled();
    void disable();
    void enable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
    void printStatus();
}
