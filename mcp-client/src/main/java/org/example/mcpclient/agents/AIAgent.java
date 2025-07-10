package org.example.mcpclient.agents;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIAgent {
    private ChatClient chatClient;

    @Value("classpath:/system_prompt.txt")
    private String systemPromptContent;

    public AIAgent(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider, ResourceLoader resourceLoader) {

        Resource resource = resourceLoader.getResource("classpath:/system_prompt.txt");

        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider) // This is where the magic happens!
                .defaultSystem(resource)
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(MessageWindowChatMemory.builder().build()).build())
                .build();
    }

    public String askLLM(String query){
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }


}
