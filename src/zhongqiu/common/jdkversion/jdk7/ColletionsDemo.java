package zhongqiu.common.jdkversion.jdk7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//�﷨��֧�ּ��ϣ�����һ�������� 
public class ColletionsDemo {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("item");
		list.add("item1");
		list.add("item2");
		System.out.println(list.get(0));

		Set<String> set = new HashSet<>();
		set.add("item");
		set.add("item1");
		set.add("item2");
		System.out.println(set);

		Map<String, Integer> map = new HashMap<>();
		map.put("key", 1);
		map.put("key1", 2);
		map.put("key2", 3);
		System.out.println(map.get("key"));

		// List<Integer> piDigits = [ 1,2,3,4,5,8 ];
		// List<String> list1 = ["1"];
		//
		// Set<String> set1 = {"item"};
		//
		// Map<String, Integer> map1 = {"key" : 1};
		// int value = map1["key"];
	}
}