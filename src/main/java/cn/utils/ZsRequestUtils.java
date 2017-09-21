package cn.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import cn.common.ZsConstant;

/**
 * @author hzq
 */
public class ZsRequestUtils {
	private static Logger logger = LoggerFactory.getLogger(ZsRequestUtils.class);
	private static final String contextType = "application/json;charset=UTF-8";
    private static HttpClient httpClient;
    private static int maxTotal = 300;
    private static int maxPerRout = 200;
    private static int connecttimeOut = 10000;
    private static int readTimeOut = 5000;
	
    static {
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRout); 
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, connecttimeOut);
        HttpConnectionParams.setSoTimeout(params, readTimeOut); 
        httpClient = new DefaultHttpClient(cm, params); 
    }

	/**
	 * 发送post请求
	 * @param jsonParam
	 * @return
	 */
	public static JSONObject httpPost(JSONObject jsonParam) {
		// post请求返回结果
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(ZsConstant.ZSTACK_API_POST_URL);
		try {
			if (null != jsonParam) {
				//请求报文日志输出
				logger.info("请求报文：" + ZsJSonUtils.jsonStringformat(jsonParam.toString()));
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType(contextType);
				httpPost.setEntity(entity);			
				HttpResponse result = httpClient.execute(httpPost);
				// 请求发送成功，并得到响应
				if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String strResult = "";
					try {
						// 读取服务器返回过来的json字符串数据
						strResult = EntityUtils.toString(result.getEntity(), "UTF-8");
						//请求返回报文日志输出
						logger.info("请求返回报文：" + ZsJSonUtils.jsonStringformat(strResult));
						// 把json字符串转换成json对象
						jsonResult = JSONObject.fromObject(strResult);
					} catch (Exception e) {
						logger.error("post请求提交失败:" + ZsConstant.ZSTACK_API_POST_URL, e);
					}
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + ZsConstant.ZSTACK_API_POST_URL, e);
		}
		
		return jsonResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		try {
			// 发送get请求
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				String strResult = EntityUtils.toString(response.getEntity());
				// 把json字符串转换成json对象
				jsonResult = JSONObject.fromObject(strResult);
				url = URLDecoder.decode(url, "UTF-8");
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		
		return jsonResult;
	}

	/**
	 * 返回字符串,删除了首尾空格,如果不存在则返回null
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getRequestKeyValue(HttpServletRequest request, String key) {
		String value = request.getParameter(key);
		if (StringUtils.isEmpty(value)) {
			return StringUtils.trimWhitespace(value);
		}
		return value;
	}

	/**
	 * 返回字符串,删除了首尾空格,如果不存在则返回null
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getSessionKeyValue(HttpServletRequest request,
			String key) {
		String value = null;
		HttpSession session = request.getSession();
		if (session != null) {
			Object loginUUid = session.getAttribute(key);
			if (!StringUtils.isEmpty(loginUUid)) {
				value = String.valueOf(loginUUid);
			}
		}
		return value;
	}
	
	/**
	 * bootstrapTable参数乱码问题解决
	 * @param key
	 * @return
	 */
	public static String formatBootstrapTableEncoding(String key) {
		String value = key;
		if (key != null) {
			try {
				value = new String(key.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				value = key;
			} 
		}
		return value;
	}
	
}