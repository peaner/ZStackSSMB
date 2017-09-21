package cn.springmvc.service;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import cn.springmvc.model.QueryBackupStorageModel;
import cn.springmvc.model.QueryCpuAndMemoryModel;
import cn.springmvc.model.QueryIpAddressModel;
import cn.springmvc.model.QueryPrimaryStorageModel;

@Service
public interface ResourcePoolService {
	  /**查询资源池中的磁盘信息，对应界面的磁盘利用率*/
      public List<QueryPrimaryStorageModel> queryPrimaryStorage(String loginuuid);
      /**查询备份存储信息，也就是镜像服务器大小*/
      public List<QueryBackupStorageModel> queryBackupStorage(String loginuuid);
      /**查询资源池中的CPU和内存信息，对应界面的CPU和内存利用率*/
      public QueryCpuAndMemoryModel queryCpuAndMemory(String loginuuid, JSONArray zoneUuids);
      /**查询资源池中的IP资源信息*/
      public QueryIpAddressModel queryIpAddress(String loginuuid,JSONArray zoneUuids);
}
