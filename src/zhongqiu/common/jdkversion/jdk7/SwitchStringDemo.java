package zhongqiu.common.jdkversion.jdk7;

//switch中可以使用字串了。这个新特性是在编译器这个层次上实现的
//一旦case匹配,就会顺序执行后面的程序代码,而不管后面的case是否匹配,直到遇见break,利用这一特性可以让好几个case执行统一语句.
public class SwitchStringDemo {
	public static void main(String[] args) {
		String s = "test";
		switch (s) {
		case "test":
			System.out.println("test");
			break;
		case "a":
			System.out.println("a");
			break;
		case "test1":
			System.out.println("test1");
			break;
		default:
			System.out.println("break");
			break;
		}
	}
}
