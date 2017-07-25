package com.sample.disruptor;

import com.lmax.disruptor.EventHandler;

public class BusinessLogicEventHandler<T> implements EventHandler<T> {

	public void onEvent(T event, long sequence, boolean endOfBatch)
			throws Exception {
		Thread.sleep(60000);
		System.out.println("Business Logic.Seq= "+sequence+"\t  ((ValueEvent) event).getValue()= "+((ValueEvent) event).getValue()+"\nTime Recieved :"+System.currentTimeMillis() );
		applyBusinessLogic(event,sequence);
	}

	private void applyBusinessLogic(T event, long sequence){
		int add=((ValueEvent) event).getValue()+1000;
		((ValueEvent) event).setValue(add);
		System.out.println("After Logic Event Value: "+ ((ValueEvent) event).getValue() );
		
	}
}
