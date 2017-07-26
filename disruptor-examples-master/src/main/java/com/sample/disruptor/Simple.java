package com.sample.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.ProducerType;

public class Simple {
	public static void main(String[] args) throws InterruptedException {
		ThreadFactory customThreadfactory = new CustomThreadFactoryBuilder()
												.setNamePrefix("DemoPool-Thread").setDaemon(false)
												.setPriority(Thread.MAX_PRIORITY).build();
		
		ExecutorService exec = Executors.newFixedThreadPool(4,customThreadfactory);
		ExecutorService prod_exec = Executors.newFixedThreadPool(2);
		// Preallocate RingBuffer with 1024 ValueEvents
		final EventHandler<ValueEvent> handler = new ValidationEventHandler<ValueEvent>("Initial Event 1");
		final BusinessLogicEventHandler<ValueEvent> sleepEventHandler=new BusinessLogicEventHandler<ValueEvent>("Initial Event 2");
		
		DisruptorQueue<ValueEvent> disruptor = new DisruptorQueue<ValueEvent>(
												ValueEvent.EVENT_FACTORY, 4, customThreadfactory, ProducerType.MULTI,
												new BlockingWaitStrategy(), handler,sleepEventHandler);

		RingBuffer<ValueEvent> ringBuffer = disruptor.start();
		Producer<ValueEvent> producer_2 = new Producer<ValueEvent>(ringBuffer,"FTL",1,1,0);
		prod_exec.execute(producer_2);


	}
}
