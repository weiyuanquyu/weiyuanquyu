package yuan.web.util;

import java.util.Locale;

public class ByteUtil {

    /**
     * bytes字符串转换为Byte值
     *
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
     * @return byte[]
     */
    public static final byte[] hexString2Bytes(String src) {
        /*对输入值进行规范化整理*/
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);
        if(src.length()%2>0){
            src="0"+src;
        }

        //处理值初始化
        int m, n;
        int iLen = src.length() / 2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytes2HexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 把单字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String byte2HexString(byte b) {
        StringBuffer sb = new StringBuffer("");
        String sTemp=Integer.toHexString(0xFF & b);
        if (sTemp.length() < 2)
            sb.append(0);
        sb.append(sTemp.toUpperCase());

        return sb.toString();
    }

    /**
     * 累计求和
     *
     * @param bytes 字节数组
     * @return  校验码
     */
    public static byte makeSum(byte[] bytes) {
        int sum = 0x00;

        for (int i = 0; i < bytes.length; i++) {
            sum+=bytes[i];
        }
        return (byte)sum;
    }

    /**
     * 合并多个byte数组
     * @param array 可变
     * @return 数组
     */
    public static byte[] mergeArray(byte[]... array){

        int length=0;
        for(byte[] bs :array){
            length+=bs.length;
        }
        byte[] res=new byte[length];

        int point=0;
        for(byte[] bs :array){
            System.arraycopy(bs,0,res,point,bs.length);
            point+=bs.length;
        }
        return res;
    }

    public static byte[] getModBusCrc16(byte[] data) {
        int len = data.length;

        int crc = 0xFFFF;//预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。
        int i, j;
        for (i = 0; i < len; i++) {
            //把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (data[i] & 0xFF));
            for (j = 0; j < 8; j++) {
                //把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位
                if ((crc & 0x0001) > 0) {
                    //如果移出位为 1, CRC寄存器与多项式A001进行异或
                    crc = crc >> 1;
                    crc = crc ^ 0xA001;
                } else
                    //如果移出位为 0,再次右移一位
                    crc = crc >> 1;
            }
        }
        return hexString2Bytes(Integer.toHexString(crc));
    }

    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     * 二进制字符串转byte
     */
    public static byte decodeBinaryString(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (byteStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {// 4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }


    /**
     * bytes数组转换成integer 最长只有4长度byte
     * @param bytes
     * @return 0
     */
    public static int bytes2int(byte[] bytes){
        int result = 0;
        byte[] data=new byte[4];

        if(bytes.length<4){
            for(int i=0;i<4;i++){
                if(bytes.length>i) {
                    data[i]=0;
                }else {
                    data[i] = bytes[i-bytes.length];
                }
            }
        }else{
            data=bytes;
        }


        if(data.length == 4){
            int a = (data[0] & 0xff) << 24;
            int b = (data[1] & 0xff) << 16;
            int c = (data[2] & 0xff) << 8;
            int d = (data[3] & 0xff);
            result = a | b | c | d;
        }
        return result;
    }


    public static byte[] bytesXor(byte[] data,byte[] key){
        byte[] cdata=new byte[8];

        boolean flag=true;
        if(data.length<8){
            for(int i=0;i<8;i++){
                if(data.length<i){
                    cdata[i]=data[i];
                }else {
                    if(flag){
                        cdata[i]= (byte) 0x80;
                    }else {
                        cdata[i]= (byte) 0x00;
                    }
                }

            }
        }else {
            cdata=data;
        }

        byte[] re=new byte[8];
        for(int i=0;i<8;i++){
            re[i]= (byte) (cdata[i] ^ key[i]);
        }

        return re;
    }

}
