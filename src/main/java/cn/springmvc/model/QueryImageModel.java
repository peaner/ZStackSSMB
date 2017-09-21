package cn.springmvc.model;

//import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;

//import cn.springmvc.service.impl.QueryImageServiceImpl;

/**
 * @author di
 * 镜像模型
 */
@Repository
public class QueryImageModel {
	private String name;
	private String uuid;
	private String mediaType;
	private String format;
	private String state;
	private String status;
	private String size;
	
	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public static void main(String[] args) {
		QueryImageServiceImpl q = new QueryImageServiceImpl();
		JSONArray result = q.queryImageAll("2943f952f9fd454a95019339c1052d62");
		System.out.print(result.toString());
	}*/
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
