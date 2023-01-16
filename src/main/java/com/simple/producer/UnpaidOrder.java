package com.simple.producer;

import java.time.LocalDate;
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
public class UnpaidOrder implements Order{
	@With
	private UUID id;
	@With
	private String owner;
	@With
	private boolean outland;
	@With
	private LocalDate dueDate;
	
}
