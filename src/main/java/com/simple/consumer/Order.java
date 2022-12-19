package com.simple.consumer;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@With
	private UUID id;
	@With
	private String owner;
	@With
	private boolean outland;
}
