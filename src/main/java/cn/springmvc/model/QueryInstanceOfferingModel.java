package cn.springmvc.model;

import org.springframework.stereotype.Repository;

/**
 * @author di
 * 计算规格模型
 */
@Repository
public class QueryInstanceOfferingModel {
	private String name;
	private String cpuNum;
	private String memorySize;
	private String uuid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpuNum() {
		return cpuNum;
	}
	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}
	public String getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
/*	public static void main(String[] args) {
		QueryInstanceOfferingServiceImpl q = new QueryInstanceOfferingServiceImpl();
		JSONArray result = q.queryInstanceOfferingAll("1efdd07cca484265ad0535653d3492f3");
		System.out.print(result.toString());
	}*/
}


