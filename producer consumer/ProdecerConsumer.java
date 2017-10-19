import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProdecerConsumer {
	public static void main(String[] args) {
		ProdecerConsumer pc = new ProdecerConsumer();
		
		Storage s = new Storage();
		
		//线程池大小可变
		//ExecutorService service = Executors.newCachedThreadPool();
		//固定长度的线程池大小
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		Producer p = new Producer("张三",s);
		Producer p2 = new Producer("李四",s);
		Consumer c = new Consumer("王五", s);
		Consumer c2 = new Consumer("老刘", s);
		Consumer c3 = new Consumer("老林", s);
		
		service.submit(p);
		service.submit(c);
		service.submit(p2);
		service.submit(c2);
		service.submit(c3);
	}
}

class Consumer implements Runnable {

	private String name;
	private Storage s = null;
	
	public Consumer(String name,Storage s){
		this.name = name;
		this.s = s;
	}
	
	@Override
	public void run() {
		while(true){
			System.out.println(name+"准备消费产品");
			try {
				Product product = s.pop();
				System.out.println(name+"已消费("+product.toString()+").");
				System.out.println("===============");
				Thread.sleep(500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Producer implements Runnable {

	private String name;
	private Storage s = null;
	
	public Producer(String name,Storage s){
		this.name = name;
		this.s = s;
	}
	
	@Override
	public void run() {
		while(true){
			Product product = new Product((int)(Math.random()*10000));
			
			//System.out.println(name + "准备生产("+product.toString()+").");
			
			try {
				s.push(product);
				System.out.println(name+"已生产("+product.toString()+").");
				System.out.println("=============");
				Thread.sleep(1500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
