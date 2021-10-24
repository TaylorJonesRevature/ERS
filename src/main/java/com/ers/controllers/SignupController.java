package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.models.user;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SignupController {

	private static userDao uDao = new userDaoDB();
	private static UserService uServ = new UserService(uDao);

	public static void signUp(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {

		// To read in stringified JSON data from a POST request is a little more
		// complicated than reading form data
		StringBuilder buffer = new StringBuilder();

		// The buffered reader will read the json data line by line
		BufferedReader reader = req.getReader();

		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}

		String data = buffer.toString();
		System.out.println(data);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);

		
		
		String firstname = parsedObj.get("firstname").asText();
		String lastname = parsedObj.get("lastname").asText();
		String email = parsedObj.get("email").asText();
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		

		try {
			System.out.println("In the post handler");
			System.out.println(username);
			user u = uServ.signUp(firstname,lastname,email,username,password);
			int retrievalCode = uServ.sendInvite(email);
			uServ.postInvite(username, retrievalCode);
			// We will keep track of if a user is signed in by storing their id in the
			// session
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		} catch (Exception e) {
			res.setStatus(403);
		}

	}
}
