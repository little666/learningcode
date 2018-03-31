 package day04;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UseCopyOnWrite {

	public static void main(String[] args) {
		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
		CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
	}

}
