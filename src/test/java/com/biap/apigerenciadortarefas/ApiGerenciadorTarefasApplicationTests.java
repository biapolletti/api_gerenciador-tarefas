package com.biap.apigerenciadortarefas;

import com.biap.apigerenciadortarefas.entity.todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGerenciadorTarefasApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testSuccess() {
		var task = new todo("Tarefa 1", "Desc Tarefa 1", false, 1);
		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(task.getNome())
				.jsonPath("$[0].descricao").isEqualTo(task.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(task.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(task.getPrioridade());
	}

	@Test
	void testFailure() {
		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(
						new todo("", "", false, 0)
				).exchange()
				.expectStatus().isBadRequest();
	}

}
