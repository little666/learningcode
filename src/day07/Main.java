package day07;

public class Main {

	public static void main(String[] args) {
		
		FutureClient fc = new FutureClient();
		Data data = fc.ruquest("�������");
		
		System.out.println("�����ͳɹ���");
		System.out.println("������������...");
		
		String result = data.getRequest();
		System.out.println(result);
	}

}
