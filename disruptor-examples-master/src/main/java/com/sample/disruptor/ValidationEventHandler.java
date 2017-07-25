package com.sample.disruptor;

import com.lmax.disruptor.EventHandler;

public class ValidationEventHandler<T> implements EventHandler<T> {

	public void onEvent(T event, long sequence, boolean endOfBatch)
			throws Exception {
    	process(event, sequence);
	}

	private void process(T event,long sequence) throws InterruptedException{
		int add=((ValueEvent) event).getValue()+100;
		((ValueEvent) event).setValue(add);
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Consumer --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Producer :"+((ValueEvent) event).getProducerIdentifier());
//		System.out.println("ValueEvent: " +  ((ValueEvent) event).getValue());
		((ValueEvent) event).setRecieveTime(System.currentTimeMillis());
		System.out.println("Completed CustomEvent Handler For sequence: "+sequence +"\nTime complete: "+((ValueEvent) event).getRecieveTime());
		System.out.println("----------------------------------------------------------------------------------------");
		/*if(null!=event)
			System.out.println("Consumer --> Sequence: "+sequence +" ValueEvent: " +  ((ValueEvent) event).getValue()+" Passed Validation Layer at Time :"+System.currentTimeMillis());*/
	}
}
