package com.vega.app.entities.front;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RevenuStats {

	private Double totalNetRevenu;

	private Double totalRevenu;

	private Integer totalArticles;

	private Integer totalArticlesUnSold;

}
