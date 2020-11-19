package club.bluetroy.websocket;

import club.bluetroy.InputHandler;

import java.io.InputStream;

/**
 * @author heyixin 2019-05-16 16:42.
 */
public class WebsocketInputHandler implements InputHandler {
    @Override
    public Frame handle(InputStream inputStream) throws Exception {
        byte[] bytes = inputStream.readNBytes(inputStream.available());
        Frame frame = new Frame();
        parseByte0(bytes[0], frame);
        parseByte1(bytes[1], frame);
        parseMessage(bytes, frame);
        return frame;
    }

    private void parseByte1(byte aByte, Frame frame) {
        //todo  完成解析
        //134:
        //
        //If the second byte minus 128 is between 0 and 125, this is the length of the message. If it is 126, the following 2 bytes (16-bit unsigned integer), if 127, the following 8 bytes (64-bit unsigned integer, the most significant bit MUST be 0) are the length.
        //MASK: 掩码，0 表示不使用掩码，1 表示使用 Masking-key 对负载数据进行掩码运算 Payload len:0-125: 实际负载数据长度 126: 接下来的两字节对应的无符号整数作为负载长度 127: 扩展的 8 字节对应的无符号帧数作为负载长度
        String s = Integer.toBinaryString(aByte & 0xFF);
    }

    /**
     * FIN RSV1	RSV2	RSV3	Opcode
     * 1	0	0	    0	    0x1=0001
     */
    private void parseByte0(byte aByte, Frame frame) {
        String s = Integer.toBinaryString(aByte & 0xFF);
        //FIN: 表示帧是否结束，1 结束，0 没结束
        char fin = s.charAt(0);
        if (fin == '1') {
            frame.setFin(true);
        } else {
            frame.setFin(false);
        }
        //RSV[1-3]: 通常来说置零即可，但可以根据扩展协商非零值的具体含义 因此当前不处理
        frame.setRsv1(false);
        frame.setRsv2(false);
        frame.setRsv3(false);
        //opcode: 操作码，0, 1, 2 属于数据帧，8, 9, 10 属于控制帧，具体含义如下 0: 附加帧 1: 文本帧 2: 二进制帧 3-7: 保留作为未来的非控制帧 8: 关闭帧 9: ping 帧 10: pong 帧 11-15: 保留作为未来的控制帧
        //todo 当前不处理 统一当作文本
        frame.setOpcode(Opcode.text);
    }

    private void parseMessage(byte[] bytes, Frame frame) {
        byte[] messageByte = new byte[6];
        System.arraycopy(bytes, 6, messageByte, 0, 6);
        byte[] key = new byte[4];
        System.arraycopy(bytes, 2, key, 0, 4);
        byte[] decoded = new byte[6];
        for (int i = 0; i < messageByte.length; i++) {
            decoded[i] = (byte) (messageByte[i] ^ key[i & 0x3]);
        }
        frame.setMessage(new String(decoded));
    }
}
