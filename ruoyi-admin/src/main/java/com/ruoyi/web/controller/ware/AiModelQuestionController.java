//package com.ruoyi.web.controller.ware;
//
//import com.alibaba.fastjson.JSON;
//import com.hm.project.req.AiChatInitReq;
//import com.hm.project.req.AiModelQuestionReq;
//import com.hm.project.rsp.HttpResult;
//import com.hm.project.service.AiModelQuestionService;
//import com.ruoyi.common.core.domain.AjaxResult;
//import com.ruoyi.system.domain.AiModelQuestion;
//import com.ruoyi.system.service.OpenAiModelQuestionService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//
//
//@RestController
//@RequestMapping("/aiModelQuestion")
//@Slf4j
//public class AiModelQuestionController {
//
//    @Resource
//    private OpenAiModelQuestionService openAiModelQuestionService;
//
//
//
//    /**
//     * 聊天
//     * @param question
//     * @return
//     */
//    @PostMapping("chat")
//    public AjaxResult chat(@RequestBody AiModelQuestion question) {
//        if (question.getQuestionTitle() == null ||
//                question.getQuestionTitle().equals("")) {
//            return AjaxResult.error("请输入你的问题");
//        }
//        if (question.getIsExpert() == 1) {
//            // 专家问答
//            return AjaxResult.success(openAiModelQuestionService.chat(question));
//        }else {
//            // 普通问答
//            return AjaxResult.success(openAiModelQuestionService.chat2(question));
//        }
//    }
//
//    /**
//     * 分享内容
//     * @return
//     */
////    @PostMapping("shareQuestion")
////    public AjaxResult shareContent(@RequestBody Map<String,List<Integer>> map) {
////        if (map == null) {
////            return AjaxResult.error("参数不能为空");
////        }
////        List<Integer> ids = map.get("ids");
////        return AjaxResult.success(aiModelQuestionService.shareContent(ids));
////    }
//
//    /**
//     * 会话历史
//     * @return
//     */
////    @PostMapping("getQuestionList")
////    public AjaxResult getQuestionList(@RequestBody AiModelQuestionReq question) {
////        return AjaxResult.success(aiModelQuestionService.getQuestionList(question));
////    }
//
//    /**
//     * 聊天
//     * @param question
//     * @return
//     */
////    @PostMapping("chat")
////    public AjaxResult chat(@RequestBody AiModelQuestionReq question) {
////        if (question.getQuestionTitle() == null || question.getQuestionTitle().equals("")) {
////            return AjaxResult.error("请输入你的问题");
////        }
////        if (question.getIsExpert() == 1) {
////            // 专家问答
////            return AjaxResult.success(openAiModelQuestionService.chat(question));
////        }else {
////            // 普通问答
////            return AjaxResult.success(openAiModelQuestionService.chat2(question));
////        }
////    }
//
////    @PostMapping("stopGeneration")
////    public HttpResult stopGeneration(@RequestBody AiModelQuestionReq question) {
////        aiModelQuestionService.stopGeneration(question);
////        return HttpResult.ok();
////    }
////
////    @PostMapping("chatInit")
////    public HttpResult chatInit(@RequestBody AiChatInitReq req) {
////        return HttpResult.ok(aiModelQuestionService.chatInit(req));
////    }
////
////
////    /**
////     * SSE 流式响应接口
////     * 访问地址：http://localhost:8080/stream-data
////     */
////    @PostMapping(value = "/stream-data")
////    public ResponseEntity<Flux<String>> streamData(@RequestBody AiModelQuestionReq question) {
////        Map<String, String> dataStream = aiModelQuestionService.chat2(question);
////        String jsonString = JSON.toJSONString(dataStream);
////        log.info("jsonString:{}",jsonString);
////        return ResponseEntity.ok().contentType(MediaType.APPLICATION_NDJSON)
////                .body(Flux.just(jsonString));
////    }
//
//
//}
