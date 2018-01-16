package wang.congjun.utils;

public class MacUtil {



    public static byte [] stomac(String mac){
        byte []macBytes = new byte[6];
        String [] strArr = mac.split(":");

        for(int i = 0;i < strArr.length; i++){
            int value = Integer.parseInt(strArr[i],16);
            macBytes[i] = (byte) value;
        }
        return macBytes;
    }

    public static String mactos(byte[] mac)
    {
        StringBuilder value = new StringBuilder();
        for(int i = 0;i < mac.length; i++){
            String sTemp = Integer.toHexString(0xFF &  mac[i]);
            value.append(sTemp).append(":");
        }

        value = new StringBuilder(value.substring(0, value.lastIndexOf(":")));
        return value.toString();
    }


}
