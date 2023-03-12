package com.collibra.codingchallenge.demo;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import com.collibra.codingchallenge.demo.controllers.AssetController;
import com.collibra.codingchallenge.demo.services.AssetService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.containsString;


@RunWith(SpringRunner.class)
@WebMvcTest(AssetController.class)
@AutoConfigureMockMvc
class AssetApplicationTests {

	@Autowired
	AssetService assetService;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void givenAssets_whenGetAsset_thenStatus200()
			throws Exception {

		assetService.createAsset(new Asset("myAsset", AssetType.CASH,
				null, null, true, LocalDateTime.now()));

		mvc.perform(MockMvcRequestBuilders.get("/api/employees")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("myAsset")));
	}

}
