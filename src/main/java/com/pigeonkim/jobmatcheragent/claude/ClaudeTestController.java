package com.pigeonkim.jobmatcheragent.claude;

import com.pigeonkim.jobmatcheragent.crawler.JobCrawlerService;
import com.pigeonkim.jobmatcheragent.domain.JobPosting;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JobCrawlerService jobCrawlerService;

    @GetMapping("/crawler/test")
    public String crawlerTest() throws Exception {
        JobPosting saved = jobCrawlerService.fetchAndSave(
                "https://www.wanted.co.kr/wd/365123"
        );
        return "저장 완료. ID: " + saved.getId();
    }
    @GetMapping("/crawler/save")
    public String crawlerSave() throws Exception {
        JobPosting saved = jobCrawlerService.fetchAndSave(
                "https://www.wanted.co.kr/wd/365123"
        );
        return "저장 완료. title: " + saved.getTitle()
                + " / company: " + saved.getCompany();
    }
}