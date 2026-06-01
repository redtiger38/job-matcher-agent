package com.pigeonkim.jobmatcheragent.crawler;

import com.pigeonkim.jobmatcheragent.domain.JobPosting;
import com.pigeonkim.jobmatcheragent.domain.JobPostingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

        Element companyLink = doc.selectFirst("a[data-company-name]");

        // selectFirst()는 요소 없으면 null 반환 → null 체크 필수
        String title   = companyLink != null ? companyLink.attr("data-position-name") : doc.title();
        String company = companyLink != null ? companyLink.attr("data-company-name")  : "알 수 없음";

        JobPosting posting = new JobPosting();
        posting.setUrl(url);
        posting.setTitle(title);
        posting.setCompany(company);
        posting.setDescription(doc.text());
        posting.setFetchedAt(LocalDateTime.now());

        return jobPostingRepository.save(posting);
    }
}