package fgo.saber.common.util;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

/**
 * @ClassName SnowflakeUtil
 * @Description TODO
 * @Author zhouQiang
 * @Date 2021/5/18 17:00
 * @Version 1.0.0
 */
public class SnowflakeUtil {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(SnowflakeUtil.next());
        }
    }

    // 机器标识位数
    private final static long WORKER_ID_BITS = 5L;

    // 机器ID最大值 31
    private final static long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

    private static SnowflakeGenerator snowflakeGenerator;

    @SneakyThrows
    private static SnowflakeGenerator instance() {
        if (snowflakeGenerator != null) {
            return snowflakeGenerator;
        }

        synchronized (SnowflakeUtil.class) {
            if (snowflakeGenerator != null) {
                return snowflakeGenerator;
            }
            snowflakeGenerator = new SnowflakeGenerator(getWorkerId(), getRandom());
            return snowflakeGenerator;
        }
    }

    public static Long next() {
        return instance().next();
    }

    private static long getWorkerId() throws SocketException, UnknownHostException, NullPointerException {

        NetworkInterface network = null;
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface nint = en.nextElement();
            if (!nint.isLoopback() && nint.getHardwareAddress() != null) {
                network = nint;
                break;
            }
        }

        @SuppressWarnings("ConstantConditions")
        byte[] mac = network.getHardwareAddress();

        Random rnd = new Random();
        byte rndByte = (byte) (rnd.nextInt() & 0x000000FF);

        // 取mac地址最后一位和随机数
        return (((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) rndByte) << 8))) >> 6) % 31;
    }

    /**
     * 生成1-31之间的随机数
     *
     * @return
     */
    private static long getRandom() {
        return RandomUtil.randomInt(1, 31);
    }

}
