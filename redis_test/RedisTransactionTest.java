import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class RedisTransactionTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(20);
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.118");
		Jedis jedis = pool.getResource();
		jedis.set("buyedTickets", "0");
		jedis.disconnect();
		pool.destroy();
		for(int i = 0;i<1000;i++){
			es.submit(new TicketsRunnable());
		}
		es.shutdown();
		
	}
}

class TicketsRunnable implements Runnable{

	JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.118");
	Jedis jedis = pool.getResource();
	@Override
	public void run() {
		jedis.watch("buyedTickets");
		int buyedTickets = Integer.parseInt(jedis.get("buyedTickets"));
		String userInfo = UUID.randomUUID().toString();
		if(buyedTickets < 1000){
			Transaction tx = jedis.multi();
			tx.set("buyedTickets", String.valueOf(buyedTickets+1));
			List<Object> result = tx.exec();
			if(result != null){
				System.out.println("用户:"+userInfo+"成功抢得一张票");
			}else{
				System.out.println("用户:"+userInfo+"抢票失败");
				System.out.println("原因：发生冲突");
			}
		}else{
			System.out.println("用户:"+userInfo+"抢票失败");
			System.out.println("原因：没票了");
			System.out.println("buyedTickets:"+buyedTickets);
		}
		jedis.disconnect();
		pool.destroy();
	}
	
}
