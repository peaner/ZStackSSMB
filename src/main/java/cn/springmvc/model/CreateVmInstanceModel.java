package cn.springmvc.model;

import org.springframework.stereotype.Repository;

/**
 * 创建虚拟机模型
 * @author hzq
 *
 */
@Repository
public class CreateVmInstanceModel {
	private String name;
	private String instanceOfferingUuid;
	private String imageUuid;
	private String[] l3NetworkUuids;
	private String defaultL3NetworkUuid;
	private String resourceUuid;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the instanceOfferingUuid
	 */
	public String getInstanceOfferingUuid() {
		return instanceOfferingUuid;
	}
	/**
	 * @param instanceOfferingUuid the instanceOfferingUuid to set
	 */
	public void setInstanceOfferingUuid(String instanceOfferingUuid) {
		this.instanceOfferingUuid = instanceOfferingUuid;
	}
	/**
	 * @return the imageUuid
	 */
	public String getImageUuid() {
		return imageUuid;
	}
	/**
	 * @param imageUuid the imageUuid to set
	 */
	public void setImageUuid(String imageUuid) {
		this.imageUuid = imageUuid;
	}
	/**
	 * @return the l3NetworkUuids
	 */
	/**
	 * @return the defaultL3NetworkUuid
	 */
	public String getDefaultL3NetworkUuid() {
		return defaultL3NetworkUuid;
	}
	/**
	 * @param defaultL3NetworkUuid the defaultL3NetworkUuid to set
	 */
	public void setDefaultL3NetworkUuid(String defaultL3NetworkUuid) {
		this.defaultL3NetworkUuid = defaultL3NetworkUuid;
	}
	/**
	 * @return the resourceUuid
	 */
	public String getResourceUuid() {
		return resourceUuid;
	}
	/**
	 * @param resourceUuid the resourceUuid to set
	 */
	public void setResourceUuid(String resourceUuid) {
		this.resourceUuid = resourceUuid;
	}
	public String[] getL3NetworkUuids() {
		return l3NetworkUuids;
	}
	public void setL3NetworkUuids(String l3NetworkUuids[]) {
		this.l3NetworkUuids = l3NetworkUuids;
	}
	
}
