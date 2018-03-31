package day07;

public class RealData implements Data{

	private String result;

	public RealData(String queryString) {
		System.out.println("根据"+queryString+"进行查询，这是一个很耗时的操作..");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("操作完毕，获取结果");
		result = "查询结果";
	}
	
	public String getRequest(){
		return result;
	}
}
