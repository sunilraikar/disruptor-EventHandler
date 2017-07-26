package com.sample.disruptor;

import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorQueue<T> {

	private Disruptor<T> disruptor;
	@SuppressWarnings("unchecked")
	DisruptorQueue(EventFactory<T> eventFactory,int bufferSize,ThreadFactory customThreadfactory,ProducerType producerType,WaitStrategy waitStrategy,EventHandler<T> validationHandler,EventHandler<T> businesshandler){
		disruptor=new Disruptor<T>(eventFactory, bufferSize,customThreadfactory, producerType,waitStrategy );
		disruptor.handleEventsWith(validationHandler).then(businesshandler);
	}
	
	public RingBuffer<T> start(){
		return disruptor.start();
	}
	
	public void shutdown(){
		disruptor.shutdown();
	}
}
