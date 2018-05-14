package yuan.web.base;

import java.util.List;
import java.util.Map;

import org.nutz.dao.pager.Pager;
import yuan.web.util.json.JsonBinder;


/**
 * 用户返回管理页面的jsonbean
 * 
 * @author dim
 *
 */
public class WebStoreBean {

	private String status;
	private String message;

	private List<?> items;
	private Map<String, Object> mapItems;
	private Pager page;
	private Object content;
	
	private int count;
	private long totalCount;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public Map<String, Object> getMapItems() {
		return mapItems;
	}

	public void setMapItems(Map<String, Object> mapItems) {
		this.mapItems = mapItems;
	}

	public WebStoreBean() {
	}

	public WebStoreBean(String bz, String message) {
		this.status = bz;
		this.message = message;
	}

	public static WebStoreBean succ() {
		return new WebStoreBean(StatusCode.SUCCESS, StatusCode.getErrorMessage(StatusCode.SUCCESS));
	}

	public static WebStoreBean list(String message, List<?> items) {
		WebStoreBean bean = new WebStoreBean(StatusCode.SUCCESS, message);
		bean.setItems(items);
		bean.count = items.size();
		return bean;
	}

	public static WebStoreBean list(String message, List<?> items,long totalCount) {
		WebStoreBean bean = list(message, items);
		bean.totalCount = totalCount;
		return bean;
	}

	public static WebStoreBean one(String message, Object content) {
		WebStoreBean bean = new WebStoreBean(StatusCode.SUCCESS, message);
		bean.setContent(content);
		bean.count = 1;
		return bean;
	}

	public static WebStoreBean fail(String failureCode) {
		return new WebStoreBean(failureCode, StatusCode.getErrorMessage(failureCode));
	}
	
	public static WebStoreBean unknown(String message) {
		return new WebStoreBean(StatusCode.UNKNOW, message);
	}
	
	public String toJson () {
		return new JsonBinder().toJson(this);
	}

	public Pager getPage() {
		return page;
	}

	public WebStoreBean setPage(Pager page) {
		this.page = page;
		return this;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
