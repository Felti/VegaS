package com.vega.app.entities.front;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CostStats {

	private Double totalCosts;

	private Integer totalArticles;

	private Integer totalArticlesUnSold;
}
