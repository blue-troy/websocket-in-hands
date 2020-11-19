import org.junit.Test;

/**
 * @author heyixin 2019-05-16 16:47.
 */
public class TestEncode {
    @Test
    public void testDecode() {
        byte[] decoded = new byte[6];
        byte[] encoded = new byte[]{(byte) 198, (byte) 131, (byte) 130, (byte) 182, (byte) 194, (byte) 135};
        byte[] key = new byte[]{(byte) 167, (byte) 225, (byte) 225, (byte) 210};
        for (int i = 0; i < encoded.length; i++) {
            decoded[i] = (byte) (encoded[i] ^ key[i & 0x3]);
        }
        System.out.println(new String(decoded));
    }

    @Test
    public void testByte2String() {
        byte a = (byte)129;
        System.out.println(Integer.toBinaryString(a& 0xFF));
    }
}
