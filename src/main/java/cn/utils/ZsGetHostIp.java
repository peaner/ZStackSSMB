package cn.utils;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZsGetHostIp {
	private static Logger logger = LoggerFactory.getLogger(ZsGetHostIp.class);

	/**
	 * @return 获取本机IP号
	 */
	public static String getHostIp() {
		String hostIp = "";
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
			hostIp = ia.getHostAddress();
		} catch (Exception e) {
			logger.error("获取本地IP异常：" + e.getMessage());
		}
		return hostIp;
	}

}