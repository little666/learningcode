package day11.nio.test;

import java.nio.IntBuffer;

public class TestBuffer {

	public static void main(String[] args) {

		//put����
		
		/*IntBuffer buf = IntBuffer.allocate(10);
		buf.put(13);
		buf.put(21);
		buf.put(35);
		
		buf.flip(); 
		System.out.println("ʹ��flip��λ��"+bu f);
		System.out.println("����Ϊ��"+buf.capacity());
		System.out.println("����Ϊ��"+buf.limit());
		
		System.out.println("��ȡ�±�1��Ԫ�أ�"+buf.get(1));
		System.out.println("get(index)����,positionλ�ò��ı䣺"+buf);
		buf.put(1,4);
		System.out.println("put(index,change)����,positionλ�ò��䣺"+buf);
		
		for(int i=0;i<buf.limit();i++){
			System.out.println(buf.get()+"\t ");
		}
		System.out.println("buf���������"+buf);*/
		
		//wrap����
		/*int[] arr = new int[]{1,2,5};
		IntBuffer buf1 = IntBuffer.wrap(arr);
		System.out.println(buf1);
		
		IntBuffer buf2 = IntBuffer.wrap(arr,0,2);
		System.out.println(buf2);*/
		
		//��������
		IntBuffer buf1 = IntBuffer.allocate(10);
		int[] arr = new int[]{1,2,5};
		buf1.put(arr);
		System.out.println(buf1);
		
		IntBuffer buf3 = buf1.duplicate();
		System.out.println(buf3);
		
		buf1.position(1);
		buf1.flip();
		System.out.println(buf1);
		
		System.out.println("�ɶ�����Ϊ��"+buf1.remaining());
		
		int [] arr2 = new int[buf1.remaining()];
		buf1.get(arr2);
		for(int i:arr2){
			System.out.print(Integer.toString(i)+",");
		}
		
	}

}
