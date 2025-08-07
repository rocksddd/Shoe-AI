package com.ruoyi.common.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import java.time.Duration;

@Configuration
public class AiConfig {

    @Bean
    public RestTemplate ollamaRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30000); // 连接超时时间，单位为毫秒
        requestFactory.setReadTimeout(30000);    // 读取超时时间，单位为毫秒
        return new RestTemplate(requestFactory);
    }
//        @Bean
//        public OllamaChatModel ollamaChatModel(){
//            OllamaChatModel model = OllamaChatModel.builder()
//                    .modelName("deepseek-r1")
//                    .baseUrl("http://localhost:11434")
//                    .httpClientBuilder(new SpringRestClientBuilder())
//                    // 超时时间
//                    .timeout(Duration.ofSeconds(30))
//                    // 超时最大重试次数
//                    .maxRetries(3)
//                    .build();
//            return model;
//        }
    @Bean
    public OllamaChatModel ollamaChatModel() {
        return OllamaChatModel.builder()
                .modelName("deepseek-r1:1.5b")
                .baseUrl("http://localhost:11434")
                // 超时时间
//                .timeout(Duration.ofSeconds(3000))
                .timeout(Duration.ofSeconds(3600))
                // 超时最大重试次数
                .maxRetries(3)
                .build();
    }


//    @Bean
//    public OpenAiChatModel openAiChatModel() {
//        return OpenAiChatModel.builder()
//                // 从环境变量获取API密钥
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                // 控制输出的随机性
//                .temperature(0.7)
//                // 设置超时时间
//                .timeout(Duration.ofSeconds(30))
//                .build();
//      }
//    @Bean
//    public OpenAiChatModel openAiChatModel() {
//        // 从环境变量获取 OPENAI_API_KEY（确保已设置）
//        String apiKey = System.getenv("OPENAI_API_KEY");
//        if (apiKey == null || apiKey.isEmpty()) {
//            throw new IllegalStateException("OPENAI_API_KEY 环境变量未设置！");
//        }
//
//        return OpenAiChatModel.builder()
//                .apiKey(apiKey)          // 设置 API Key
//                .temperature(0.7)        // 控制随机性（0.0~1.0）
//                .timeout(Duration.ofSeconds(30)) // 超时时间
//                .build();
//    }
//
//@Bean
//public OpenAiChatModel openAiChatModelGpt4oMini() {
//    return OpenAiChatModel.builder()
//            .apiKey(System.getenv("OPENAI_API_KEY"))
//            .modelName("gpt-4o-mini")
//            .timeout(Duration.ofSeconds(30))
//            .build();
//}


    }






