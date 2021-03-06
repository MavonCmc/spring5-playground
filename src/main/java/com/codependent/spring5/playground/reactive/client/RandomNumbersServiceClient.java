package com.codependent.spring5.playground.reactive.client;

import static org.springframework.web.client.reactive.ClientWebRequestBuilders.get;
import static org.springframework.web.client.reactive.ResponseExtractors.bodyStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.reactive.WebClient;

import reactor.core.publisher.Flux;

@Component
public class RandomNumbersServiceClient {

	@Autowired
	private WebClient webClient;
	
	public Flux<Double> getRandomNumbers(String serviceBaseUrl){
		Flux<Double> response = webClient
				.perform(get(serviceBaseUrl+"/randomNumbers"))
				.extract(bodyStream(Double.class));
		return response;
	}
	
	public Flux<Object> getRandomNumbersStreaming(String serviceBaseUrl){
		Flux<Object> response = webClient
				.perform(get(serviceBaseUrl+"/randomNumbersStreaming").header("Accept", "text/event-stream"))
				.extract(bodyStream(Object.class));
		return response;
	}
	
}
