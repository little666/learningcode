package day07;

public class RealData implements Data{

	private String result;

	public RealData(String queryString) {
		System.out.println("����"+queryString+"���в�ѯ������һ���ܺ�ʱ�Ĳ���..");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������ϣ���ȡ���");
		result = "��ѯ���";
	}
	
	public String getRequest(){
		return result;
	}
}
