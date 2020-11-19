package club.bluetroy.websocket;

import club.bluetroy.OutputHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author heyixin 2019-05-17 09:24.
 */
public class WebsocketOutputHandler implements OutputHandler {
    @Override
    public void handle(Object input, OutputStream outputStream) {
        try {
            Frame frame = (Frame) input;
            byte[] outputByte = parseFrame(frame);
            outputStream.write(outputByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] parseFrame(Frame frame) {
        byte[] frameByte = new byte[12];
        parseFrame0(frameByte, frame);
        parseFrameMessage(frameByte, frame);
        return frameByte;
    }

    private void parseFrameMessage(byte[] frameByte, Frame frame) {
        String message = frame.getMessage();

        //todo 先不使用掩码 若后续有加密需求在增加使用掩码的功能
        char mask = '0';

        //todo 先假设发送的消息长度短于126
        //懒得指定utf-8了 windows的若不指定字符集可能会运行不了
        int playLoadLength = message.getBytes().length;
        // mask 1bit 与playLoad 7bit 共同构成frameByte[1]
        //需要构造一个七位的bit
        String playLoadLengthString = Integer.toBinaryString(playLoadLength).substring(1);

        //这里是2进制的frameByte1
        String byte1String = mask + playLoadLengthString;
        //转换成10进制的frameByte1的byte
        frameByte[1] = (byte) Integer.parseInt(byte1String, 10);

        

    }

    private void parseFrame0(byte[] frameByte, Frame frame) {
        //简单的认为每次的数据都是一个frame,因此fin设置为true
        //简单的忽略rsv拓展
        //简单的认为发送的消息就是文本格式
        char fin = '1';
        char rsv1 = '0';
        char rsv2 = '0';
        char rsv3 = '0';
        String opcode = "0001";
        String frameBinaryString = String.valueOf(fin) + rsv1 + rsv2 + rsv3 + opcode;
        int frameDecimal = Integer.parseInt(frameBinaryString, 2);
        frameByte[0] = (byte) frameDecimal;
    }
}
