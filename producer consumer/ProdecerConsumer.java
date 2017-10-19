import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProdecerConsumer {
	public static void main(String[] args) {
		ProdecerConsumer pc = new ProdecerConsumer();
		
		Storage s = new Storage();
		
		//�̳߳ش�С�ɱ�
		//ExecutorService service = Executors.newCachedThreadPool();
		//�̶����ȵ��̳߳ش�С
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		Producer p = new Producer("����",s);
		Producer p2 = new Producer("����",s);
		Consumer c = new Consumer("����", s);
		Consumer c2 = new Consumer("����", s);
		Consumer c3 = new Consumer("����", s);
		
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
			System.out.println(name+"׼�����Ѳ�Ʒ");
			try {
				Product product = s.pop();
				System.out.println(name+"������("+product.toString()+").");
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
			
			//System.out.println(name + "׼������("+product.toString()+").");
			
			try {
				s.push(product);
				System.out.println(name+"������("+product.toString()+").");
				System.out.println("=============");
				Thread.sleep(1500);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
