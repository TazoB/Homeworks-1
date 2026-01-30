package BridgePattern.Devices;

public class TV implements Device {
    private boolean onOff = false;
    private int volume;
    private int channel;

    public TV(boolean onOff, int volume, int channel) {
        this.onOff = onOff;
        this.volume = volume;
        this.channel = channel;
    }

    public TV() {

    }

    @Override
    public boolean isEnabled() {
        return onOff;
    }

    @Override
    public void disable() {
        onOff = true;
    }

    @Override
    public void enable() {
        onOff = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        if(volume >= 0) {
            if(volume <= 100) volume = percent;
            else volume = 100;
        }
        else volume = 0;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void printStatus() {
        String status = onOff ? "ON" : "OFF";
        System.out.println("The TV is " + status);
        System.out.println("Current Volume: " + volume);
        System.out.println("Current Channel: " + channel);
    }
}
