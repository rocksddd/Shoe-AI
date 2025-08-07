package com.ruoyi.web.controller.ware;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.AiModelQuestion;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.CustomMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/aiChatAll")
@Slf4j
public class OpenAiController {



    private final OllamaChatModel ollamaChatModel;

    public OpenAiController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }



//    @Operation(summary = "测试LangChain4j功能-ollama")
    @PostMapping("/ollamaTest")
    public AjaxResult chat(@RequestBody AiModelQuestion req) {
        // 向 模型 提问
//        ChatResponse chatResponse = ollamaChatModel.chat(ChatRequest.builder().messages(messages).build());
//
//        System.out.println(chatResponse.aiMessage().text());
        String answer = ollamaChatModel.chat(req.getQuestion());
        return AjaxResult.success(answer);
    }

    @PostMapping("chat")
    public AjaxResult chatNumber(@RequestBody AiModelQuestion req) {

        String retrievedContext = "One significant part of treaty making is that signing a treaty implies recognition that the other side is a sovereign state and that the agreement being considered is enforceable under international law. Hence, nations can be very careful about terming an agreement to be a treaty. For example, within the United States, agreements between states are compacts and agreements between states and the federal government or between agencies of the government are memoranda of understanding.";

        List<ChatMessage> messages = List.of(
                SystemMessage.from("context_relevance"),
                UserMessage.from(req.getQuestion()),
                CustomMessage.from(Map.of(
                        "role", "context",
                        "content", retrievedContext
                ))
        );

        ChatResponse chat = ollamaChatModel.chat(ChatRequest.builder().messages(messages).build());
        String answer = chat.toString();

        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(answer);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1)).append(" ");
        }

        // 去除末尾多余的空格（如果有）
        String extractedText = result.toString();
        //将\n替换为<br>
        extractedText = extractedText.replace("\n", "<br>");



        return AjaxResult .success(extractedText);
    }


        /**
     * SSE 流式响应接口
     * 访问地址：http://localhost:8080/stream
     */
    @PostMapping(value = "/stream")
    public ResponseEntity<Flux<String>> streamData(@RequestBody AiModelQuestion req) {
        String answer = ollamaChatModel.chat(req.getQuestion());

        log.info("jsonString:{}",answer);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_NDJSON)
                .body(Flux.just(answer));
    }


}
