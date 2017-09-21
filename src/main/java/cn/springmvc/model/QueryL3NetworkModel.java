package cn.springmvc.model;
import org.springframework.stereotype.Repository;


/**
 * 创建L3查询模型
 * @author hzq
 *
 */
@Repository
public class QueryL3NetworkModel {
    private String uuid;
    private String state;
    private String name;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
