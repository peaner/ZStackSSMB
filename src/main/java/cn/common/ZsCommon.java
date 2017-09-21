package cn.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

public class ZsCommon {
	
	/**
	 * 服务器的线程池
	 * 拒绝策略处理 [AbortPolicy, CallerRunsPolicy, DiscardOldestPolicy, DiscardPolicy]
	 */
	public static ThreadPoolExecutor zsThreadPool = new ThreadPoolExecutor(50, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.DiscardOldestPolicy());
	
	/**
	 * 用于缓存UUID与对应的处理结果 key:apiId+uuid value:处理结束后设置处理结果的json
	 */
	public static final Map<String, JSONObject> apiUuidAndQueryJsonResult = new ConcurrentHashMap<String, JSONObject>();	
	
	/**
	 * 设置列名的序号，排序使用
	 */
	@SuppressWarnings("serial")
	public static final Map<String, Integer> vmInstanceModelColumnMap = new ConcurrentHashMap<String, Integer>(){{    
	    put("name", 1);    
	    put("imageName", 2);  
	    put("ip", 3);    
	    put("state", 4);
	    put("createDate", 5);
	}};  
	
	/**
	 * 全局的变量设置，排序使用
	 */
	public static String selectVmInstanceModelColumn = new String();
}
