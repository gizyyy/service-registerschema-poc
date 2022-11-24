package com.simple.producer;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	@With
	private UUID id;
	@With
	private String owner;
	@With
	private boolean outland;
}
