package org.example.mcpclient;


import io.modelcontextprotocol.client.McpSyncClient;
import org.example.mcpclient.agents.AIAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class McpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(List<McpSyncClient> clients) {
		return args -> {
			clients.forEach(client -> {
						client.listTools().tools().forEach(tool -> {
							System.out.println("---------");
							System.out.println(tool.name());
							System.out.println(tool.description());
							System.out.println(tool.inputSchema());
							System.out.println("---------");
						});
					}
			);
		};
	}

	// Le bean CommandLineRunner pour tester l'AIAgent
	@Bean
	CommandLineRunner runAIAgent(AIAgent aiAgent) { // Injectez votre AIAgent
		return args -> {
			System.out.println("-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent : Informations sur l'entreprise GALP");
			String query1 = "Dis-moi tout sur l'entreprise nommée GALP, y compris son chiffre d'affaires et le nombre d'employés.";
			String response1 = aiAgent.askLLM(query1); // C'est ici que vous devriez obtenir la réponse finale
			System.out.println("Réponse de L. : " + response1);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent : Quelle est l'activité de Capgemini ?");
			String query2 = "Quelle est l'activité de Capgemini et combien d'employés ont-ils ?";
			String response2 = aiAgent.askLLM(query2);
			System.out.println("Réponse de L. : " + response2);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent : Cours de l'action Capgemini");
			String query3 = "Quel est le cours de l'action de Capgemini aujourd'hui ?";
			String response3 = aiAgent.askLLM(query3);
			System.out.println("Réponse de L. : " + response3);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent : Mon prenom");
			String query4 = "C'est quoi mon prenom et j'habite ou?";
			String response4 = aiAgent.askLLM(query4);
			System.out.println("Réponse de L. : " + response4);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent :Ton prenom");
			String query5 = "C'est quoi ton Prenom?";
			String response5 = aiAgent.askLLM(query5);
			System.out.println("Réponse de L. : " + response5);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent :Question general");
			String query6 = "Mon nom veut dire quoi?";
			String response6 = aiAgent.askLLM(query6);
			System.out.println("Réponse de L. : " + response6);
			System.out.println("-----------------------------------------------------");

			System.out.println("\n-----------------------------------------------------");
			System.out.println("Requête à l'AIAgent :Question general");
			String query7 = "Qui est le roi d'angleterre?";
			String response7 = aiAgent.askLLM(query7);
			System.out.println("Réponse de L. : " + response7);
			System.out.println("-----------------------------------------------------");
		};
	}

}
