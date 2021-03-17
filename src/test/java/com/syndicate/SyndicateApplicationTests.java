package com.syndicate;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.syndicate.master.user.UserDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SyndicateApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiZXhwIjoxNjE1MTI3Mzg5fQ.OEYIaS7eSs3C2qykPaH2AGBsvQdoxRFL8IaHpnJWAJxVGfZpaAJ4Ou9EYROL6dA0nxxikhuUrnrkDFOVGtOckw";

	@Test
	public void testSaveUSer() throws Exception {
		UserDTO userDTO = new UserDTO();
		Set<String> s = new HashSet<String>();
		s.add("1");
		userDTO.setName("fromTest");
		userDTO.setPassword("fromTest");
		userDTO.setRoles(s);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/user/register"),
				HttpMethod.POST, entity, String.class);

		assertEquals("My Message", response.getBody(), "User Created");
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void testFindOneWithNull() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("fromTest1");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);

		ResponseEntity<UserDTO> response = restTemplate.exchange(createURLWithPort("/api/user/find"),
				HttpMethod.POST, entity, UserDTO.class);

		UserDTO u = new UserDTO();
		u.setLogin_failuer_message("User not found.!");
		assertEquals("User status", response.getBody(), u);
		
	}
	
	@Test
	public void testFindOneWithPassword() throws Exception {
		UserDTO userDTO = new UserDTO();
		Set<String> s = new HashSet<String>();
		s.add("USER");
		s.add("ADMIN");
		userDTO.setName("root");
		userDTO.setPassword("root123");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);

		ResponseEntity<UserDTO> response = restTemplate.exchange(createURLWithPort("/api/user/find"),
				HttpMethod.POST, entity, UserDTO.class);

		UserDTO ud = response.getBody();
		ud.setFound(true);
		assertEquals("User status", response.getBody(), ud);
	}
	
	@Test
	public void testFindOneWithWrongPassword() throws Exception {
		UserDTO userDTO = new UserDTO();
		Set<String> s = new HashSet<String>();
		s.add("USER");
		s.add("ADMIN");
		userDTO.setName("root");
		userDTO.setPassword("root123sdfsf");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);

		ResponseEntity<UserDTO> response = restTemplate.exchange(createURLWithPort("/api/user/find"),
				HttpMethod.POST, entity, UserDTO.class);

		UserDTO ud = response.getBody();
		ud.setLogin_failuer_message("User name or password is incorrect.!");
		assertEquals("User status", response.getBody(), ud);
	}
	
	@Test
	public void testFindUserByName() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("fromTest1");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);

		ResponseEntity<UserDTO> response = restTemplate.exchange(createURLWithPort("/api/user/find"),
				HttpMethod.POST, entity, UserDTO.class);

		UserDTO u = new UserDTO();
		u.setLogin_failuer_message("User not found.!");
		assertEquals("User status", response.getBody(), u);
		
	}

}
