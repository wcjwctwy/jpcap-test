package wang.congjun.arp;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;

/**
 * 网卡工具类View
 * Created by duke on 2016/11/16.
 * arp-client
 */
public class NetWorkUtil
{
    public static void main(String[] args) {
        getDevice(0);
        System.out.println(">>>>>>>>>>>>>>>>>>>");
        getDevice(1);
    }

    /**
     * 使用String的startsWith函数判断IP相同的开始部分相同即可
     * @param segment 例如：192.168.1
     * @return
     */
    public static NetworkInterface getDevice(String segment)
    {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (int i =0;i<devices.length;i++)
        {
            NetworkInterfaceAddress[] addresses = devices[i].addresses;
            if(addresses[1].address.toString().startsWith(segment)){
                return  devices[i];
            }
        }
        return devices[0];
    }
    public static NetworkInterface getDevice(int network)
    {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        NetworkInterfaceAddress[] addresses = devices[network].addresses;
        NetworkInterface device = devices[network];
        return device;
    }
}

