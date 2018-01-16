package wang.congjun;

import java.io.IOException;
import java.net.InetAddress;

import jpcap.*;
import jpcap.packet.ARPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import lombok.extern.slf4j.Slf4j;
import wang.congjun.arp.ArpController;
import wang.congjun.dataobject.SendInfo;
import wang.congjun.utils.MacUtil;

@Slf4j
public class JpcapPacket {  



    public static void main(String[] args)  throws Exception
    {
        Boolean isSendRouter=false;
        SendInfo sendInfo = new SendInfo();
        sendInfo.setDeip("192.168.2.170");
        sendInfo.setDeMac("a0:18:28:d1:4:f2");
        sendInfo.setSrcIp("192.168.2.1");
        sendInfo.setSrcMac("e4:2:9b:f8:7b:90");
        sendInfo.setNetwork(4);
        sendInfo.setTime(30);
        /*--------------    第一步绑定网络设备       --------------*/   
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();  
          
        for(NetworkInterface n : devices)  
        {  
            log.info("【抓包测试】网卡接口 name:{},描述:{}",n.name ,n.description);
        }  
        System.out.println("-------------------------------------------");

        JpcapCaptor jpcap = null;  
        JpcapCaptor jpcapSender = null;
        int caplen = 1111;
        boolean promiscCheck = true;  
          
        try{
            jpcapSender = JpcapCaptor.openDevice(devices[4], 65535,false,20);
        }catch(IOException e)
        {  
            e.printStackTrace();  
        }  
          
        /*----------第二步抓包-----------------*/  
        int i = 0;  
        while(true)
        {  
            Packet packet  = jpcapSender.getPacket();
//            if(packet instanceof ARPPacket){
//                ARPPacket arpPacket = (ARPPacket) packet;
//                if(InetAddress.getByAddress(arpPacket.sender_protoaddr).getHostAddress().equals(sendInfo.getDeip())) {
//                    log.info("【抓包ARP】发送的mac地址:{}", MacUtil.mactos(arpPacket.sender_hardaddr));
//                    log.info("【抓包ARP】发送的ip地址:{}", InetAddress.getByAddress(arpPacket.sender_protoaddr).getHostAddress());
//                    log.info("【抓包ARP】目标的mac地址:{}", MacUtil.mactos(arpPacket.target_hardaddr));
//                    log.info("【抓包ARP】目标的ip地址:{}", InetAddress.getByAddress(arpPacket.target_protoaddr).getHostAddress());
//                }
//            }else{
//                log.info("【抓取数据】用户：{}发送的数据：{}",sendInfo.getDeip(),new String(ipPacket.data));
                    jpcapSender.getJpcapSenderInstance().sendPacket(packet);
//            }
        }  
          
          
          
          
    }  
  
}  