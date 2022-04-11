package com.vega.app.services;

import java.util.Set;


import com.vega.app.dtos.ReactionDTO;
import com.vega.app.entities.Reaction;

public interface ReactionService {

	Set<ReactionDTO> getAll();

	Reaction getById(Long id);

	ReactionDTO create(ReactionDTO reactionDTO);

	ReactionDTO edit(Long id);

	void delete(Long id);
}
