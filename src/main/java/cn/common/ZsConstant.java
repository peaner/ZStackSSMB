package cn.common;

public class ZsConstant {
	/**虚拟机相关状态*/
	public static enum vmType{Stopped, Stopping, Running, Strating, Destroying, Destroyed, Rebooting}
	/**处理状态*/
	public static enum manageType {Done, Processing}
	/**虚拟机默认密码*/
	public static final String VM_DEFAULT_PASSWORD = "888888";	
	/**日期格式*/
	public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**ZstackAPI的POST请求URL*/
	public static final String ZSTACK_API_POST_URL = "http://10.177.2.241:8080/zstack/api";	
	/**ZstackAPI的GET请求URL*/
	public static final String ZSTACK_API_GET_URL = "http://10.177.2.241:8080/zstack/api/result/";	
	/**创建虚拟机API*/
	public static final String API_CREATEVMINSTANCE_MSG = "org.zstack.header.vm.APICreateVmInstanceMsg";
	/**创建虚拟机API*/
	public static final String API_CREATEVMINSTANCE_EVENT = "org.zstack.header.vm.APICreateVmInstanceEvent";
	/**启动虚拟机API*/
	public static final String API_StartVmInstance_MSG =  "org.zstack.header.vm.APIStartVmInstanceMsg";
	/**启动虚拟机API*/
	public static final String API_STARTVMINSTANCE_EVENT =  "org.zstack.header.vm.APIStartVmInstanceEvent";
	/**停止虚拟机API*/
	public static final String API_StopVmInstance_MSG = "org.zstack.header.vm.APIStopVmInstanceMsg";
	/**停止虚拟机API*/
	public static final String API_STOPVMINSTANCE_EVENT = "org.zstack.header.vm.APIStopVmInstanceEvent";
	/**重启虚拟机API*/
	public static final String API_RebootVmInstance_MSG = "org.zstack.header.vm.APIRebootVmInstanceMsg";
	/**重启虚拟机API*/
	public static final String API_REBOOTVMINSTANCE_EVENT = "org.zstack.header.vm.APIRebootVmInstanceEvent";
	/**删除虚拟机API*/
	public static final String API_DestroyVmInstance_Msg = "org.zstack.header.vm.APIDestroyVmInstanceMsg";
	/**删除虚拟机API*/
	public static final String API_DESTROYVMINSTANCE_EVENT = "org.zstack.header.vm.APIDestroyVmInstanceEvent";
	/**彻底删除虚拟机API*/
	public static final String API_ExpungeVmInstance_Msg = "org.zstack.header.vm.APIExpungeVmInstanceMsg";
	/**彻底删除虚拟机API*/
	public static final String API_EXPUNGEVMINSTANCE_EVENT = "org.zstack.header.vm.APIExpungeVmInstanceEvent";
	/**恢复虚拟机API*/
	public static final String API_RecoverVmInstance_Msg = "org.zstack.header.vm.APIRecoverVmInstanceMsg";
	/**恢复虚拟机API*/
	public static final String API_RECOVERVMINSTANCE_EVENT = "org.zstack.header.vm.APIRecoverVmInstanceEvent";
	/**登陆API*/
	public static final String API_LOGINBYACCOUNT_MSG = "org.zstack.header.identity.APILogInByAccountMsg";
	/**登陆API结果*/
	public static final String API_LOGIN_REPLY = "org.zstack.header.identity.APILogInReply";
	/**登出API*/
	public static final String API_LOGOUT_MSG = "org.zstack.header.identity.APILogOutMsg";	
	/**登出API结果*/
	public static final String API_LOGOUT_REPLY = "org.zstack.header.identity.APILogOutReply";	
	/**修改密码API*/
	public static final String API_UpdateAccount_MSG = "org.zstack.header.identity.APIUpdateAccountMsg";
	/**修改密码API结果*/
	public static final String API_UPDATEACCOUNT_EVENT = "org.zstack.header.identity.APIUpdateAccountEvent";
	/**查询计算规格*/
	public static final String API_QueryInstance_MSG = "org.zstack.header.configuration.APIQueryInstanceOfferingMsg";
	/**查询计算规格结果*/
	public static final String API_QUERYINSTANCEOFFERING_REPLY = "org.zstack.header.configuration.APIQueryInstanceOfferingReply";
	/**查询镜像*/
	public static final String API_QueryImage_MSG = "org.zstack.header.image.APIQueryImageMsg";	
	/**查询镜像结果*/
	public static final String API_QUERYIMAGE_REPLY = "org.zstack.header.image.APIQueryImageReply";
	/**查询虚拟机API*/
	public static final String API_QUERYVMINSTANCE_MSG = "org.zstack.header.vm.APIQueryVmInstanceMsg";
	/**查询虚拟机API结果*/
	public static final String API_QUERYVMINSTANCE_REPLY = "org.zstack.header.vm.APIQueryVmInstanceReply";
	/**查询磁盘规格API*/
	public static final String API_QUERYDISKOFFERING_MSG = "org.zstack.header.configuration.APIQueryDiskOfferingMsg";
	/**查询磁盘规格API结果*/
	public static final String API_QUERYDISKOFFERING_REPLY = "org.zstack.header.configuration.APIQueryDiskOfferingReply";
	/**查询三层网络API*/
	public static final String API_QUERYL3NETWORK_MSG = "org.zstack.header.network.l3.APIQueryL3NetworkMsg";
	/**查询三层网络API结果*/
	public static final String API_QUERYL3NETWORK_REPLY = "org.zstack.header.network.l3.APIQueryL3NetworkReply";
	/**查询主存储API*/
	public static final String API_QUERYPRIMARYSTORAGE_MSG = "org.zstack.header.storage.primary.APIQueryPrimaryStorageMsg";
	/**查询主存储API结果*/
	public static final String API_QUERYPRIMARYSTORAGE_REPLY = "org.zstack.header.storage.primary.APIQueryPrimaryStorageReply";
	/**查询备份存储*/
	public static final String API_QUERYBACKUPSTORAGE_MSG = "org.zstack.header.storage.backup.APIQueryBackupStorageMsg";
	/**查询备份存储结果*/
	public static final String API_QUERYBACKUPSTORAGE_REPLY = "org.zstack.header.storage.backup.APIQueryBackupStorageReply";
    /**查询cpu和内存容量*/
	public static final String API_GETCPUMEMORYCAPACITY_MSG = "org.zstack.header.allocator.APIGetCpuMemoryCapacityMsg";
	/**查询cpu和内存容量结果*/
	public static final String API_GETCPUMEMORYCAPACITY_REPLY = "org.zstack.header.allocator.APIGetCpuMemoryCapacityReply";
	/**查询cpu和内存容量*/
	public static final String API_QUERYZONE_MSG = "org.zstack.header.zone.APIQueryZoneMsg";
	/**查询cpu和内存容量结果*/
	public static final String API_QUERYZONE_REPLY = "org.zstack.header.zone.APIQueryZoneReply";
	/**查询虚拟机端口号*/
	public static final String API_GETVMCONSOLEADDRESS_MSG = "org.zstack.header.vm.APIGetVmConsoleAddressMsg";
	/**查询虚拟机端口号*/
	public static final String API_GETVMCONSOLEADDRESS_REPLY = "org.zstack.header.vm.APIGetVmConsoleAddressReply";
	/**查询IP地址容量*/
	public static final String API_GETIPADDRESSCAPACITY_MSG = "org.zstack.header.network.l3.APIGetIpAddressCapacityMsg";
	/**查询IP地址容量结果*/
	public static final String API_GETIPADDRESSCAPACITY_REPLY = "org.zstack.header.network.l3.APIGetIpAddressCapacityReply";	
	/**修改用户名称*/
	public static final String API_UPDATEVMINSTANCE_MSG = "org.zstack.header.vm.APIUpdateVmInstanceMsg";
	/**修改用户名称结果*/
	public static final String API_UPDATEVMINSTANCE_EVENT = "org.zstack.header.vm.APIUpdateVmInstanceEvent"; 
	/**数据源1*/
	public final static String DATASOURCE_ONE = "dataSource1";
	/**数据源2*/
    public final static String DATASOURCE_TWO = "dataSource2";
    /**操作员*/
    public final static String OPERATE_NAME = "admin";
    /**默认密码*/
    public final static String DEFAULT_PASSWORD = "888888";
}
