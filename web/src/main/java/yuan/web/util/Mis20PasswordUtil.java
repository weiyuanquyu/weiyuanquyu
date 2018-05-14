package yuan.web.util;

/**
 * @author liaoyiying
 */
public class Mis20PasswordUtil {

    public Mis20PasswordUtil(){
        
    }
    
    private static String hexStrToPassword(String s) {

      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < s.length(); i = i + 2) {
        sb.append(String.valueOf((char)Integer.parseInt(s.substring(i, i + 2), 16)));

      }
      return sb.toString();
    }
    
    private static String PasswordToHexStr(String pass){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < pass.length(); i = i + 1) {
          sb.append(Integer.toHexString(pass.charAt(i)).toUpperCase());
        }
        return sb.toString();
    }
    
    private static String calcSubPass(String s) {
      int i,j,k,y;
      int[] ba = new int[8];
      String result = "";

      k = 0;
      ba[7] = 0;

      for (i=0;i<8;i++)
        ba[i] = (int)s.charAt(i);
      y = ba[0];

      if ((ba[7] % 2)==1)
          ba[7] = (byte)(ba[7]-49);

      for (i=1;i<=6;i++)
      {
        j = ba[i];
        if (( ba[7] & (2<<i)) >0 ){
          j-= 128;
          if (j<0) j+=256;
        }
        j = j - y / i - y % i;
        if (j<0) j += 256;
        j = (j^y) - k;
        if (j<0) j = j + 256;
        k = (k+j) % 256;
        if (j>0)
          result = result + String.valueOf((char)j);
        else
          break;
      }
      return result;
    }
    /**
     * 将加密的密码转换为原始密码（用户输入的文字）
     * @param s
     * @return
     */
    public static String calcPassword(String s){
    	String result = "";
    	String s1 = hexStrToPassword(s);
    	int i = s1.length();
    	if ((i-1)%8 > 0)
    		result =  "error password";
    	else{
    		result = "";
    		for (i=0;i+1<s1.length();i=i+8){
    			result += calcSubPass(s1.substring(i,i+8));
    		}
    	}
    	return result;
    }
    
    private static String makeSubPass(String s){
        int i,j,k,y;
        int [] ba = new int[8];

        k = 0;
        y = (int) Math.round(Math.random()*168) + 32;
        ba[0] = y;
        ba[7] = 0;
        
        
        for (i =1;i<=6;i++){
            if (i <= s.length()) {
                k = (k + s.charAt(i-1)) % 256;
            }
            else if (i > (s.length() + 1 ) ) {
                k = (int) Math.round(Math.random()*256);
            }
            j = (k^y) + (y / i) + (y % i);
            ba[i] =   (j % 256);
            if ( (ba[i] < 48) || (ba[i] >= 180) ) {
                ba[i] =  ( (ba[i] + 128) % 256 );
                ba[7] =   ( ba[7] | (2 << i) );
            }
        }
        if (ba[7] < 48) {
            ba[7] =  (ba[7] + 49 );
        }
        String result = "";
        for (i = 0 ; i <=7 ; i++) {
            result = result + String.valueOf((char)(ba[i]));
        }
        
        return result;
    }
    
    public static String makePassword(String s){
        String result = "";
        
        for (int i=0;i<s.length();){
            int j = i+6;
            if (j>s.length()) j = s.length();
            result = result + makeSubPass(s.substring(i,j));
            
            i = i + 6;
        }
        
        result = result + String.valueOf((char)(Math.round(Math.random()*26) + 97));
        result = PasswordToHexStr(result);
        
        return result;
    }

}
