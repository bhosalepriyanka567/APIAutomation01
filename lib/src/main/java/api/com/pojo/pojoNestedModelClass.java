package api.com.pojo;

import java.util.List;

public class pojoNestedModelClass {
	private String job;
	private List<cityModel> citiModels;
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public List<cityModel> getCitiModels() {
		return citiModels;
	}
	public void setCitiModels(List<cityModel> citiModels) {
		this.citiModels = citiModels;
	}
}
