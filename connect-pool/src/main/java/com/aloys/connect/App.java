package com.aloys.connect;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.aloys.connect.pool.DataSource;
import com.aloys.connect.pool.PoolConnection;
import com.aloys.connect.pool.impl.DataSourceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	DataSource source = new DataSourceImpl();
    	
    	CountDownLatch latch = new CountDownLatch(20);
    	
    	for(int i = 0; i < 20; i++){
    		
    		new Thread(new Runnable() {
    			
    			@Override
    			public void run() {
    				try {
    					PoolConnection connect = source.getDataSource();
    					TimeUnit.SECONDS.sleep(1);
    					connect.releaseConnect();
    					latch.countDown();
    					
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    				
    			}
    		}).start();
    	}
    	
    	try {
			latch.await();
			System.out.println("-------结束-----------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }
}
