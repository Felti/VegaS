package com.vega.app.restcontrollers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vega.app.dtos.CategoryDTO;
import com.vega.app.dtos.PostDTO;
import com.vega.app.dtos.SimpleUserDTO;
import com.vega.app.services.impl.PostServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostController.class)
@WithMockUser
@AutoConfigureMockMvc
class PostControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	PostServiceImpl postService;

	@Test
	void shouldReturnPostObject() throws Exception {

		PostDTO post = generatePost();

		when(postService.create(post)).thenReturn(post);

		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/posts").header("Authorization", "123")
				.contentType(MediaType.APPLICATION_JSON).content(toJson(post))).andDo(print()).andExpect(status().isOk());

		
		verify(postService.create(post));
		
		// Assert
		//assertEquals(post, post);
	}

	private String toJson(PostDTO post) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(post);
	}

	private PostDTO generatePost() {
		String title = "Awesome facts";
		String content = "This is a list of all the awesome facts i know";
		CategoryDTO category = new CategoryDTO(1L, "Facts");
		SimpleUserDTO user = new SimpleUserDTO(1L, "mouad", "rakkata");

		PostDTO post = new PostDTO(1L, false, title, content, category.getId(), category.getName(), user.getId(),
				user.getFirstName(), user.getLastName());
		return post;
	}

}
