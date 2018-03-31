package day11.nio.test;

import java.nio.IntBuffer;

public class TestBuffer {

	public static void main(String[] args) {

		//put方法
		
		/*IntBuffer buf = IntBuffer.allocate(10);
		buf.put(13);
		buf.put(21);
		buf.put(35);
		
		buf.flip(); 
		System.out.println("使用flip复位："+bu f);
		System.out.println("容量为："+buf.capacity());
		System.out.println("限制为："+buf.limit());
		
		System.out.println("获取下标1的元素："+buf.get(1));
		System.out.println("get(index)方法,position位置不改变："+buf);
		buf.put(1,4);
		System.out.println("put(index,change)方法,position位置不变："+buf);
		
		for(int i=0;i<buf.limit();i++){
			System.out.println(buf.get()+"\t ");
		}
		System.out.println("buf对象遍历后："+buf);*/
		
		//wrap方法
		/*int[] arr = new int[]{1,2,5};
		IntBuffer buf1 = IntBuffer.wrap(arr);
		System.out.println(buf1);
		
		IntBuffer buf2 = IntBuffer.wrap(arr,0,2);
		System.out.println(buf2);*/
		
		//其他方法
		IntBuffer buf1 = IntBuffer.allocate(10);
		int[] arr = new int[]{1,2,5};
		buf1.put(arr);
		System.out.println(buf1);
		
		IntBuffer buf3 = buf1.duplicate();
		System.out.println(buf3);
		
		buf1.position(1);
		buf1.flip();
		System.out.println(buf1);
		
		System.out.println("可读数据为："+buf1.remaining());
		
		int [] arr2 = new int[buf1.remaining()];
		buf1.get(arr2);
		for(int i:arr2){
			System.out.print(Integer.toString(i)+",");
		}
		
	}

}
