package com.example.newsapi.controller;

import java.io.IOException;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

@RestController
public class NewsController {
	
	@Autowired
	ObjectMapper mpr;
	
	@GetMapping("/api/news/{keyword}")
	
	public JsonNode getNews(@PathVariable String keyword ) throws IOException{
		String s1 = "https://newsapi.org/v2/everything?q=";
		String s3 = "&from=2022-06-06&sortBy=publishedAt&apiKey=7df55e5419c446a19847f41add73728e";
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url(s1+keyword+s3)
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		return mpr.readValue(response.body().string(), JsonNode.class); 
	}

}
