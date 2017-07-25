package com.sample.disruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer<T> implements Runnable{
	private RingBuffer<T> ringBuffer;
	private int startingSeq;
	private int incrementalValue;
	private long wait;
	private String producerIdentifier;
	
	Producer(RingBuffer<T> ringBuffer,String producerIdentifier,int startingSeq,int incrementalValue,long wait){
		this.ringBuffer = ringBuffer;
		this.startingSeq=startingSeq;
		this.incrementalValue=incrementalValue;
		this.wait=wait;
		this.producerIdentifier=producerIdentifier;
	}
	
	public void produce() throws InterruptedException{
		for (int i = startingSeq;;i+=incrementalValue) {
//			System.out.println("Produced "+i);
            long seq = ringBuffer.next();
            ValueEvent valueEvent = (ValueEvent) ringBuffer.get(seq);
            valueEvent.setValue(i);
            valueEvent.setProducerIdentifier(producerIdentifier);
            ringBuffer.publish(seq);
//            System.out.println("Publisher--> Sequence :"+seq+" Value :"+i);
            Thread.sleep(wait);
        }
	}

	public void run() {
		try {
			produce();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
