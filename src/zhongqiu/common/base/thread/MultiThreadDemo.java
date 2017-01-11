package zhongqiu.common.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//���߳�    http://www.mamicode.com/info-detail-517008.html
//     http://www.runoob.com/java/java-multithreading.html

//��java�У�ÿ�γ���������������2���̡߳�һ����main�̣߳�һ���������ռ��̡߳�
//��Ϊÿ��ʹ��java����ִ��һ�����ʱ��ʵ���϶�������һ���ʣ֣ͣ�ÿһ����֣�ʵϰ�ھ����ڲ���ϵͳ��������һ�����̡�
//�߳����һЩ���÷����� 
//sleep(): ǿ��һ���߳�˯�ߣκ��롣 
//isAlive(): �ж�һ���߳��Ƿ�� 
//join(): �ȴ��߳���ֹ�� 
//activeCount(): �����л�Ծ���߳����� 
//enumerate(): ö�ٳ����е��̡߳� 
//currentThread(): �õ���ǰ�̡߳� 
//isDaemon(): һ���߳��Ƿ�Ϊ�ػ��̡߳� 
//setDaemon(): ����һ���߳�Ϊ�ػ��̡߳�(�û��̺߳��ػ��̵߳��������ڣ��Ƿ�ȴ����߳����������߳̽���������) 
//setName(): Ϊ�߳�����һ�����ơ� 
//wait(): ǿ��һ���̵߳ȴ��� 
//notify(): ֪ͨһ���̼߳������С� 
//setPriority(): ����һ���̵߳����ȼ���
public class MultiThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		// start()�����ĵ��ú󲢲�������ִ�ж��̴߳��룬����ʹ�ø��̱߳�Ϊ������̬��Runnable����ʲôʱ���������ɲ���ϵͳ�����ġ�
		// new ThreadDemo("A").start();
		// new ThreadDemo("B").start();

		// ���߳���Դ����
		// RunnableDemo my = new RunnableDemo();
		// new Thread(my, "C").start();
		// new Thread(my, "D").start();

		// ����������
		// SYNPo a = new SYNPo("a");
		// SYNPo b = new SYNPo("b");
		// SYNPo c = new SYNPo("c");
		// MyThreadPrinter pa = new MyThreadPrinter("A", c, a);
		// MyThreadPrinter pb = new MyThreadPrinter("B", a, b);
		// MyThreadPrinter pc = new MyThreadPrinter("C", b, c);
		// new Thread(pa).start();
		// Thread.sleep(100); // ȷ����˳��A��B��Cִ��
		// new Thread(pb).start();
		// Thread.sleep(100);
		// new Thread(pc).start();
		// Thread.sleep(100);

		// ͨ�� Callable �� Future �����߳�
		// 1. ���� Callable �ӿڵ�ʵ���࣬��ʵ�� call() �������� call() ��������Ϊ�߳�ִ���壬�����з���ֵ��
		// 2. ���� Callable ʵ�����ʵ����ʹ�� FutureTask ������װ Callable ���󣬸� FutureTask
		// �����װ�˸� Callable ����� call() �����ķ���ֵ��
		// 3. ʹ�� FutureTask ������Ϊ Thread ����� target �������������̡߳�
		// 4. ���� FutureTask ����� get() ������������߳�ִ�н�����ķ���ֵ��
		CallableDemo cDemo = new CallableDemo();
		FutureTask<Integer> fTask = new FutureTask<>(cDemo);
		// FutureTask�����漸����Ҫ�ķ�����
		// 1.get()
		// ����һֱ�ȴ�ִ������õ����
		// 2.get(int timeout, TimeUnit timeUnit)
		// ����һֱ�ȴ�ִ������õ����������ڳ�ʱʱ���ڣ�û���õ��׳��쳣
		// 3.isCancelled()
		// �Ƿ�ȡ��
		// 4.isDone()
		// �Ƿ��Ѿ����
		// 5.cancel(boolean mayInterruptIfRunning)
		// ��ͼȡ������ִ�е�����
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " ��ѭ������i��ֵ" + i);
			if (i == 20) {
				new Thread(fTask, "�з���ֵ���߳�").start();
			}
		}
		try {
			System.out.println("���̵߳ķ���ֵ��" + fTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	// �̳�Thread��ʵ�ֶ��߳�
	public static class ThreadDemo extends Thread {
		private String name;
		private int count = 5;

		public ThreadDemo(String name) {
			this.name = name;
		}

		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(name + "����  count= " + count--);
				try {
					// Thread.sleep()��������Ŀ���ǲ��õ�ǰ�̶߳��԰�ռ�ý�������ȡ��CPU��Դ��������һ��ʱ��������߳�ִ�еĻ��ᡣ
					sleep((int) Math.random() * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ʵ��Runnable�ӿڡ� ������Դ����
	public static class RunnableDemo implements Runnable {
		private int count = 5;

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				if (count > 0) {
					System.out.println(Thread.currentThread().getName() + "����  count= " + count--);
				}
				try {
					Thread.sleep((int) Math.random() * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ʵ��Callable �ӿڡ���ʵ�� call() �������� call() ��������Ϊ�߳�ִ���壬�����з���ֵ��
	public static class CallableDemo implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int i = 0;
			for (; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			return i;
		}
	}

	public static class SYNPo {
		public SYNPo(String name) {
			setName(name);
		}

		public String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// ����������
	// ���������̣߳�A�̴߳�ӡ10��A��B�̴߳�ӡ10��B,C�̴߳�ӡ10��C��Ҫ���߳�ͬʱ���У������ӡ10��ABC��
	// ��Ҫ����obj.wait()��obj.notify() ���÷�
	public static class MyThreadPrinter implements Runnable {

		private String name;
		private SYNPo prev;
		private SYNPo self;

		private MyThreadPrinter(String name, SYNPo prev, SYNPo self) {
			this.name = name;
			this.prev = prev;
			this.self = self;
		}

		@Override
		public void run() {
			int count = 10;
			while (count > 0) {
				System.out.println("�߳�" + name + "�����ö���" + prev.getName() + "����");
				synchronized (prev) {
					System.out.println("�߳�" + name + "����˶���" + prev.getName() + "����");
					System.out.println("�߳�" + name + "�����ö���" + self.getName() + "����");
					synchronized (self) {
						System.out.println("�߳�" + name + "����˶���" + self.getName() + "����");
						System.out.println(name);
						count--;

						// notify()���ú󣬲��������Ͼ��ͷŶ������ģ���������Ӧ��synchronized(){}����ִ�н�����
						// �Զ��ͷ�����,JVM����wait()���������߳������ѡȡһ�̣߳�������������������̣߳�����ִ�С�
						self.notify();
						System.out.println("����" + self.getName() + "��������˵ȴ��Ľ���" + "�����Ѳ���!!!��");
					}
					System.out.println("�߳�" + name + "�ͷ��˶���" + self.getName() + "����");
					try {
						// Thread.sleep()��Object.wait()���߶�������ͣ��ǰ�̣߳��ͷ�CPU����Ȩ��
						// ��Ҫ����������Object.wait()���ͷ�CPUͬʱ���ͷ��˶������Ŀ��ơ�
						System.out.println("�߳�" + name + "�ͷ��˶���" + prev.getName() + "����");
						System.out.println("�߳�" + name + "��������״̬���ȴ���ȡ" + prev.getName() + "����" + "���ȴ��С�������");
						prev.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}
}