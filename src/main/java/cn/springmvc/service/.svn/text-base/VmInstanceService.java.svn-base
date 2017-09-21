package cn.springmvc.service;

import java.util.List;

import cn.springmvc.model.CreateVmInstanceModel;
import cn.springmvc.model.QueryVmInstanceModel;

public interface VmInstanceService {
	/**
	 * 查询所有虚拟机
	 * @param loginuuid  注册uuid
	 * @return  查询的虚拟机信息
	 */
	public List<QueryVmInstanceModel> queryVmStance(String loginuuid);
	public QueryVmInstanceModel queryVmStanceByUuid(String loginuuid,String vmUuid);
	/**
	 * 启动虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  启动的虚拟机uuid
	 * @return  启动是否成功
	 */
	public boolean startVmInstance(String loginUuid,String VmUuid);
	/**
	 * 停止虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  停止的虚拟机uuid
	 * @return  停止是否成功
	 */
	public boolean stopVmInstance(String loginUuid,String VmUuid);
	/**
	 * 恢复虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  恢复的虚拟机uuid
	 * @return  恢复是否成功
	 */
	public boolean recoverVmInstance(String loginUuid,String VmUuid);
	/**
	 * 重启虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  重启的虚拟机uuid
	 * @return  重启是否成功
	 */
	public boolean rebootVmInstance(String loginUuid,String VmUuid);
	/**
	 * 删除虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  删除的虚拟机uuid
	 * @return  删除是否成功
	 */
	public boolean destroyVmInstance(String loginUuid,String VmUuid);
	/**
	 * 彻底删除虚拟机
	 * @param loginUuid  注册uuid
	 * @param VmUuid  彻底删除的虚拟机uuid
	 * @return  彻底删除是否成功
	 */
	public boolean expungeVmInstance(String loginUuid,String VmUuid);
	public boolean createVmStance(String loginuuid,CreateVmInstanceModel createVmInstanceModel);
	public String migrateVmStance(String uuid);
	public String mountVolume(String uuid);
	public String umountVolume(String uuid);
	public String mountL3Network(String uuid);
	public String umountL3Network(String uuid);
	public String updateOffering(String uuid);
	public boolean updateVmInstanceName(String loginUuid,String vmUuid,String vmName);
	/*根据虚拟机UUID查询虚拟机的端口号*/
	public String getVmConsoleAddress(String loginuuid,String VmUuid);
}
