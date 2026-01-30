package BridgePattern;

import BridgePattern.Devices.Device;
import BridgePattern.Devices.Radio;
import BridgePattern.Devices.TV;
import BridgePattern.Remotes.AdvancedRemote;
import BridgePattern.Remotes.BasicRemote;

public class Main {
    public static void main(String[] args) {
        test(new TV());
        test(new Radio());
    }

    public static void test(Device device) {
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        basicRemote.channelUp();
        basicRemote.channelDown();
        device.printStatus();

        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.mute();
        advancedRemote.channelUp();
        advancedRemote.power();
        advancedRemote.volumeUp();
        device.printStatus();
    }
}
