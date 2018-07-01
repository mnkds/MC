package com.example.routes.demo;

import com.example.routes.demo.controller.DemoController;
import com.example.routes.demo.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = DemoController.class, secure = false)
public class DemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DemoService svc;

	private String origin = "New York", destination = "Boston";


	@Before
	public void setUp(){}

	@Test
	public void testRequestParam() throws Exception	{
		mockMvc.perform(get("/connected?origin=null&destination=")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testProcessRouteYES() throws Exception {
		given(svc.processConnectedRoute(origin, destination)).willReturn(true);
		mockMvc.perform(get("/connected?origin=New York&destination=Boston"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string("YES"));
	}

	@Test
	public void testProcessRouteNO() throws Exception {
		destination = "Maryland";
		given(svc.processConnectedRoute(origin, destination)).willReturn(false);
		mockMvc.perform(get("/connected?origin=New York&destination=Maryland").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string("NO"));
	}

}
