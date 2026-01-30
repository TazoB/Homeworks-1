package BridgePattern.Remotes;

import BridgePattern.Devices.Device;

public class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device d) {
        super(d);
    }

    public void mute() {
        super.getD().setVolume(0);
    }
}
