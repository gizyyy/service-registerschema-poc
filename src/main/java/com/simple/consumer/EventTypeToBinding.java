package com.simple.consumer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventTypeToBinding {

	UNPAID_ORDER_EVENT("com.simple.producer.UnpaidOrder", "unpaidOrder"),
	PREPAID_ORDER_EVENT("com.simple.producer.PrepaidOrder", "prepaidOrder"), UNKNOWN("unknown", "unknownEvent");

	public final String type;
	public final String binding;

	public static String findByKey(String type) {
		EventTypeToBinding[] values = EventTypeToBinding.values();
		for (EventTypeToBinding value : values) {
			if ( value.getType().equals(type)) {
				return value.getBinding();
			}
		}
		return UNKNOWN.getBinding();
	}

}