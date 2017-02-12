package com.octus.test.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.octus.common.config.AppConfig;
import com.octus.common.controller.OctusRestController;
import com.octus.common.model.Octus;
import com.octus.common.service.OctusService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class OctusRestControllerUnitTest {

	@Mock
	private OctusService octusService;

	@InjectMocks
	private OctusRestController octusController;

	private MockMvc mockMvc;

	static final int QTY = 5;

	private Gson gson;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(octusController).build();
		gson = new Gson();
	}

	@Test
	public void test_get_all_success() throws Exception {
		List<Octus> octusList = Arrays.asList(new Octus("1", "Mockito 1"), new Octus("2", "Mockito 2"));

		when(octusService.readAll()).thenReturn(octusList);

		mockMvc.perform(get("/octus")).andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		        .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is("1")))
		        .andExpect(jsonPath("$[0].test", is("Mockito 1"))).andExpect(jsonPath("$[1].id", is("2")))
		        .andExpect(jsonPath("$[1].test", is("Mockito 2")));
	}

	@Test
	public void test_create_octus_success() throws Exception {
		Octus octus = new Octus("Mockito 3");

		doNothing().when(octusService).create(octus);

		mockMvc.perform(post("/octus").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(octus)))
		        .andExpect(status().isCreated());

		verify(octusService, times(1)).create(octus);
		verifyNoMoreInteractions(octusService);
	}

	@Test
    public void test_update_octus_success() throws Exception {
		Octus octus = new Octus("Mockito 4");
		String id = "1";

        when(octusService.update(id, octus)).thenReturn(octus);

        mockMvc.perform(
                put("/octus/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(octus)))
                .andExpect(status().isOk());

        verify(octusService, times(1)).update(id, octus);
        verifyNoMoreInteractions(octusService);
    }

	@Test
    public void test_delete_user_success() throws Exception {
		Octus octus = new Octus("1", "Mockito 5");

        doNothing().when(octusService).delete(octus.getId());

        mockMvc.perform(
                delete("/users/{id}", octus.getId()))
                .andExpect(status().isOk());

        verify(octusService, times(1)).delete(octus.getId());
        verifyNoMoreInteractions(octusService);
    }
}
