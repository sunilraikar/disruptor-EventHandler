package com.sample.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;

public class BusinessLogicEventHandler<T> implements EventHandler<T>,LifecycleAware  {

	private String name;

	public BusinessLogicEventHandler(String name) {
		super();
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void onEvent(T event, long sequence, boolean endOfBatch)
			throws Exception {
		Thread.sleep(20000);
		System.out.println("EventHandler 2 --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Producer :"+((ValueEvent) event).getProducerIdentifier()+"\nTime Recieved :"+System.currentTimeMillis() );
		applyBusinessLogic(event,sequence);
	}

	private void applyBusinessLogic(T event, long sequence){
		int add=((ValueEvent) event).getValue()+1000;
		((ValueEvent) event).setValue(add);
		System.out.println("After Logic Event Value: "+ ((ValueEvent) event).getValue() );

	}

	public void onStart() {
		System.out.println("Thread Start in Event2 : " +Thread.currentThread() );

	}

	public void onShutdown() {
		System.out.println("Shut Down of EventHandler 2 Thread: "+Thread.currentThread());
	}
}
