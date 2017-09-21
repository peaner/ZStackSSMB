package cn.springmvc.model;

/**
 * @author hzq
 * @date 2017年4月20日
 */
public class QueryCpuAndMemoryModel {
    private String availableCpu;
    private String availableMemory;
    private String totalCpu;
    private String totalMemory;
    private String success;
    /**
	 * @return the availableCpu
	 */
	public String getAvailableCpu() {
		return availableCpu;
	}
	/**
	 * @param availableCpu the availableCpu to set
	 */
	public void setAvailableCpu(String availableCpu) {
		this.availableCpu = availableCpu;
	}
	/**
	 * @return the availableMemory
	 */
	public String getAvailableMemory() {
		return availableMemory;
	}
	/**
	 * @param availableMemory the availableMemory to set
	 */
	public void setAvailableMemory(String availableMemory) {
		this.availableMemory = availableMemory;
	}
	/**
	 * @return the totalCpu
	 */
	public String getTotalCpu() {
		return totalCpu;
	}
	/**
	 * @param totalCpu the totalCpu to set
	 */
	public void setTotalCpu(String totalCpu) {
		this.totalCpu = totalCpu;
	}
	/**
	 * @return the totalMemory
	 */
	public String getTotalMemory() {
		return totalMemory;
	}
	/**
	 * @param totalMemory the totalMemory to set
	 */
	public void setTotalMemory(String totalMemory) {
		this.totalMemory = totalMemory;
	}
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
}
