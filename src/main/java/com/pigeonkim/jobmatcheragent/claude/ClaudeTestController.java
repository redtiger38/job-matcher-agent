package com.pigeonkim.jobmatcheragent.claude;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClaudeTestController {

    private final ClaudeClient claudeClient;

    @GetMapping("/claude/test")
    public String test(@RequestParam(defaultValue = "안녕! 한 줄로 인사해줘.") String prompt) {
        return claudeClient.sendMessage(prompt);
    }
}