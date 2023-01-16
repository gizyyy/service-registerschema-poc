package com.simple.consumer;

import java.math.BigDecimal;
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
public class PrepaidOrder implements Order {
	@With
	public UUID id;
	@With
	public String owner;
	@With
	public boolean outland;
	@With
	private BigDecimal paidAmount;
}
