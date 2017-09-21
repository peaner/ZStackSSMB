package cn.utils;

import java.util.UUID;

/**
 * 生成UUID工具类
 * @author hzq
 *
 */
public class ZsCreatUuid {

	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
	}
	
}
