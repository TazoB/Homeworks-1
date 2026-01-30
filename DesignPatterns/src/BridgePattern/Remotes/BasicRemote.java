package BridgePattern.Remotes;

import BridgePattern.Devices.Device;

public class BasicRemote implements Remote {
    private Device d;

    public BasicRemote(Device d) {
        this.d = d;
    }

    @Override
    public void power() {
        d.enable();
    }

    @Override
    public void volumeUp() {
        d.setVolume(d.getVolume() + 10);
    }

    @Override
    public void volumeDown() {
        d.setVolume(d.getVolume() - 10);
    }

    @Override
    public void channelUp() {
        d.setChannel(d.getChannel() + 1);
    }

    @Override
    public void channelDown() {
        d.setChannel(d.getChannel() - 1);
    }

    public Device getD() {
        return d;
    }

    public void setD(Device d) {
        this.d = d;
    }
}
