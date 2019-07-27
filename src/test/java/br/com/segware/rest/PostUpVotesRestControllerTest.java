package br.com.segware.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.segware.Application;
import br.com.segware.entity.Comentario;
/**
 * @author Anderson Virginio Martins
 * 
 * Claase responsável por testar a PostUpVotesRestController 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles(profiles = "test")
public class PostUpVotesRestControllerTest {
	
	@Test
	public void contextLoads() {
	}
	
	//adicionar novo post
	@Test
	public void testAdicionarPost() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		Comentario comentario = new Comentario();
		comentario.setDescricao("Novo Comentário");
		
		HttpEntity<Comentario> request = new HttpEntity<>(comentario, headers);

		final String baseUrl = "http://localhost:8090/api/v1/post";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.postForEntity(uri, request, Comentario.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testAdicionarPost #######################");
			System.out.println("Post ( Comentário ) Criado com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	//adicionar upvote a um post.
	@Test
	public void testAdicionarUpVote() {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
				
		Comentario comentario = new Comentario();
		comentario.setDescricao("Teste");
		comentario.setId(1L);
		
		HttpEntity<Comentario> request = new HttpEntity<>(comentario, headers);

		final String baseUrl = "http://localhost:8090/api/v1/postUp/1";

		try {
			URI uri = new URI(baseUrl);
			restTemplate.exchange(uri, HttpMethod.PUT, request,Comentario.class);
			assertNotNull(request.getBody());
			System.out.println("####################### testAdicionarUpVote #######################");
			System.out.println("Adicionado upvote a um Post com sucesso!");
		} catch (HttpClientErrorException ex) {
			assertEquals(400, ex.getRawStatusCode());
			assertEquals(true, ex.getResponseBodyAsString().contains("Erro não existe request ou header"));
		} catch (Exception ex) {
			assertEquals(500, ex.getMessage());
		}
	}
	
	//listar os posts atuais e seus upvotes recebidos	
	@Test
	public void testListar() {
		RestTemplate restTemplate = new RestTemplate();
		
		final String baseUrl = "http://localhost:8090/api/v1/posts";

		ResponseEntity<Iterable<Comentario>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Comentario>>() {});

		Iterable<Comentario> lista = response.getBody();
		
		System.out.println("####################### testListar #######################");

		for (Comentario comentario : lista) {
			System.out.println("Comentário : "+comentario.getDescricao() + " | " + comentario.getUp());
		}
		
		System.out.println("Listados todos os Post com sucesso!");

		assertNotNull(response.getBody());
	}

}

