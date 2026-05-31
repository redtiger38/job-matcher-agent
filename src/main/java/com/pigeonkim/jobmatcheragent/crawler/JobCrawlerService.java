package com.pigeonkim.jobmatcheragent.crawler;

import com.pigeonkim.jobmatcheragent.domain.JobPosting;
import com.pigeonkim.jobmatcheragent.domain.JobPostingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobCrawlerService {

    // JobPostingRepository를 주입 받음
    // C#의 생성자 주입 방식과 동일한 개념
    private final JobPostingRepository jobPostingRepository;

    public JobCrawlerService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    // 기존 메서드 — 텍스트만 반환
    public String fetchPageContent(String url) throws Exception {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();
        return doc.text();
    }

    // 새 메서드 — 크롤링 + DB 저장까지
    public JobPosting fetchAndSave(String url) throws Exception {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10000)
                .get();

        JobPosting posting = new JobPosting();
        posting.setUrl(url);
        posting.setTitle(doc.title());        // 페이지 <title> 태그
        posting.setDescription(doc.text());   // 전체 텍스트
        posting.setFetchedAt(LocalDateTime.now());

        return jobPostingRepository.save(posting); // DB 저장 후 저장된 객체 반환
    }
}