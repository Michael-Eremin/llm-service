package com.example.llm_service.interfaces.rest;


import com.example.llm_service.services.LLMServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/llm")
@Tag(name = "LLM API", description = "Endpoints for interacting with the LLM service")
public class LLMController {
    private static final Logger logger = LoggerFactory.getLogger(LLMController.class);


    private final LLMServiceImpl llmServiceImpl;

    public LLMController(LLMServiceImpl llmServiceImpl) {
        this.llmServiceImpl = llmServiceImpl;
    }

    @Operation(summary = "Получить ответ от LLM", description = "Отправляет запрос языковой модели")
    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) {

        logger.info("question : {}", question);
        return llmServiceImpl.askQuestion(question);

    }


}


//    private final LLMService llmService;
////    private final AnswerRepository answerRepository;
//
//    public LLMController(
//            LLMService llmService
////            ,
////            AnswerRepository answerRepository
//    ) {
//        this.llmService = llmService;
////        this.answerRepository = answerRepository;
//
//    }
//
//    @Operation(summary = "Get completion from LLM")
//    @PostMapping("/completion")
//    public AnswerEntity getCompletion(@RequestBody String question){
//
//        String response = llmService.askQuestion(question);
//
//        AnswerEntity answer = new AnswerEntity();
//        answer.setQuestion(question);
//        answer.setResponse(response);
//
////        return answerRepository.save(answer);
//        return answer;
//    }

//}
