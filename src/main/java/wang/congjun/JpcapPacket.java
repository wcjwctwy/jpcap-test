package wang.congjun;

import java.io.IOException;
import java.net.InetAddress;

import jpcap.*;
import jpcap.packet.ARPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import lombok.extern.slf4j.Slf4j;
import wang.congjun.utils.MacUtil;

@Slf4j
public class JpcapPacket {  
      
    public static void main(String[] args)  throws Exception
    {  
        /*--------------    第一步绑定网络设备       --------------*/   
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();  
          
        for(NetworkInterface n : devices)  
        {  
            System.out.println(n.name + "     |     " + n.description);  
        }  
        System.out.println("-------------------------------------------");  
          
        JpcapCaptor jpcap = null;  
        int caplen = 1512;  
        boolean promiscCheck = true;  
          
        try{  
            jpcap = JpcapCaptor.openDevice(devices[0], caplen, promiscCheck, 50);
        }catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
          
        /*----------第二步抓包-----------------*/  
        int i = 0;  
        while(i < 1000)
        {  
            Packet packet  = jpcap.getPacket();
            if(packet instanceof ARPPacket){
                ARPPacket arpPacket = (ARPPacket) packet;
                log.info("【抓包测试】发送的mac地址:{}", MacUtil.mactos(arpPacket.sender_hardaddr));
                log.info("【抓包测试】发送的ip地址:{}",InetAddress.getByAddress(arpPacket.sender_protoaddr).getHostAddress());
                log.info("【抓包测试】目标的mac地址:{}",MacUtil.mactos(arpPacket.target_hardaddr));
                log.info("【抓包测试】目标的ip地址:{}",InetAddress.getByAddress(arpPacket.target_protoaddr).getHostAddress());
            }
//            if(packet instanceof IPPacket && ((IPPacket)packet).version == 4)
//            {
//                i++;
//                IPPacket ip = (IPPacket)packet;//强转
//
//                log.info("版本：IPv4");
//                System.out.println("优先权：" + ip.priority);
//                System.out.println("区分服务：最大的吞吐量： " + ip.t_flag);
//                System.out.println("区分服务：最高的可靠性：" + ip.r_flag);
//                System.out.println("长度：" + ip.length);
//                System.out.println("标识：" + ip.ident);
//                System.out.println("DF:Don't Fragment: " + ip.dont_frag);
//                System.out.println("NF:Nore Fragment: " + ip.more_frag);
//                System.out.println("片偏移：" + ip.offset);
//                System.out.println("生存时间："+ ip.hop_limit);
//
//                String protocol ="";
//                switch(new Integer(ip.protocol))
//                {
//                case 1:protocol = "ICMP";break;
//                case 2:protocol = "IGMP";break;
//                case 6:protocol = "TCP";break;
//                case 8:protocol = "EGP";break;
//                case 9:protocol = "IGP";break;
//                case 17:protocol = "UDP";break;
//                case 41:protocol = "IPv6";break;
//                case 89:protocol = "OSPF";break;
//                default : break;
//                }
//                System.out.println("协议：" + protocol);
//                System.out.println("源IP " + ip.src_ip.getHostAddress());
//                System.out.println("目的IP " + ip.dst_ip.getHostAddress());
//                System.out.println("源主机名： " + ip.src_ip);
//                System.out.println("目的主机名： " + ip.dst_ip);
//                System.out.println("----------------------------------------------");
//            }
        }  
          
          
          
          
    }  
  
}  