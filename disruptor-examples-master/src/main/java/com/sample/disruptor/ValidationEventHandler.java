package com.sample.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;

public class ValidationEventHandler<T> implements EventHandler<T>,LifecycleAware {
	
	private String name;

	public ValidationEventHandler(String name) {
		super();
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void onEvent(T event, long sequence, boolean endOfBatch)
			throws Exception {
		process(event, sequence);
	}

	private void process(T event,long sequence) throws InterruptedException{
		int add=((ValueEvent) event).getValue()+100;
		((ValueEvent) event).setValue(add);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("EventHandler 1 --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Producer :"+((ValueEvent) event).getProducerIdentifier());
		//		System.out.println("ValueEvent: " +  ((ValueEvent) event).getValue());
		((ValueEvent) event).setRecieveTime(System.currentTimeMillis());
		System.out.println("Completed EventHandler 1 For sequence: "+sequence +"\nTime complete: "+((ValueEvent) event).getRecieveTime());
		System.out.println("----------------------------------------------------------------------------------------");
		/*if(null!=event)
			System.out.println("Consumer --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Passed Validation Layer at Time :"+System.currentTimeMillis());*/
	}

	public void onStart() {
		System.out.println("Thread Start in Event1 : " +Thread.currentThread());

	}

	public void onShutdown() {
		System.out.println("Shut Down of EventHandler 1 Thread: "+Thread.currentThread());		
	}
}
