package cn.springmvc.model;

import org.springframework.stereotype.Repository;

import cn.common.ZsCommon;

/**
 * 创建虚拟机模型
 * 
 * @author hzq
 * 
 */
@Repository
public class QueryVmInstanceModel implements Comparable<QueryVmInstanceModel> {
	private int index;
	private String uuid;
	private String name;
	private String platform;
	private String state;
	private String ip;
	private String createDate;
	private String imageName;
	private String imageUuid;
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
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int compareTo(QueryVmInstanceModel queryVmInstanceModel) {
		int result = 0;

		// 获取选中排序的列
		int select = ZsCommon.vmInstanceModelColumnMap
				.get(ZsCommon.selectVmInstanceModelColumn);

		switch (select) {
		case 1:
			// 按电脑名称进行排序
			if (queryVmInstanceModel.getName() != null
					&& this.getName() != null) {
				result = queryVmInstanceModel.getName().compareTo(
						this.getName());
			}
			break;
		case 2:
			// 按操作系统进行排序
			if (queryVmInstanceModel.getPlatform() != null
					&& this.getPlatform() != null) {
				result = queryVmInstanceModel.getPlatform().compareTo(
						this.getPlatform());
			}
			break;
		case 3:
			// 按访问IP进行排序
			if (queryVmInstanceModel.getIp() != null && this.getIp() != null) {
				String[] newIp = queryVmInstanceModel.getIp().split("\\.");
				String[] oldIp = this.getIp().split("\\.");
				for (int i = 0; i < newIp.length; i++) {
					if (Integer.valueOf(newIp[i]) < Integer.valueOf(oldIp[i])) {
						result = -1;
						break;
					} else if (Integer.valueOf(newIp[i]) > Integer
							.valueOf(oldIp[i])) {
						result = 1;
						break;
					}
				}
			}
			break;
		case 4:
			// 按可用状态进行排序
			if (queryVmInstanceModel.getState() != null
					&& this.getState() != null) {
				result = queryVmInstanceModel.getState().compareTo(
						this.getState());
			}
			break;
		case 5:
			// 按创建时间进行排序
			if (queryVmInstanceModel.getCreateDate() != null
					&& this.getCreateDate() != null) {
				result = queryVmInstanceModel.getCreateDate().compareTo(
						this.getCreateDate());
			}
			break;
		default:
			break;
		}

		return result;
	}
}
