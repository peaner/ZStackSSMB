package cn.springmvc.service;
import java.util.List;

import cn.springmvc.model.CreateL3NetworkModel;
import cn.springmvc.model.QueryL3NetworkModel;

public interface L3NetworkService {
	/*查询三层网络信息*/
	public List<QueryL3NetworkModel> queryL3Network(String loginuuid);
	/*添加IP范围*/
	public String addIpRange(String uuid);
	/*删除IP范围*/
	public String deleteIpRange(String uuid);
	/*添加DNS*/
	public String addDns(String uuid);
	/*删除DNS*/
	public String deleteDns(String uuid);
	/*删除三层网络*/
	public String deleteL3Network(String uuid);
	/*禁用三层网络*/
	public String disableL3Network(String uuid);
	/*启用三层网络*/
	public String enableL3Network(String uuid);
	/*创建三层网络*/
	public String createL3Network(CreateL3NetworkModel createL3NetworkModel);
}
