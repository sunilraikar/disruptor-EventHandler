package com.sample.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * WARNING: This is a mutable object which will be recycled by the RingBuffer. You must take a copy of data it holds
 * before the framework recycles it.
 */
public final class ValueEvent {
	private int value;
	private String producerIdentifier;
	private long recieveTime;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getProducerIdentifier() {
		return producerIdentifier;
	}

	public void setProducerIdentifier(String producerIdentifier) {
		this.producerIdentifier = producerIdentifier;
	}

	public long getRecieveTime() {
		return recieveTime;
	}

	public void setRecieveTime(long recieveTime) {
		this.recieveTime = recieveTime;
	}

	public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
		public ValueEvent newInstance() {
			return new ValueEvent();
		}
	};
}
