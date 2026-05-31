package com.pigeonkim.jobmatcheragent.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class JobCrawlerService {

    // URL을 받아서 해당 페이지의 HTML 전체를 가져오는 메서드
    // 지금은 가장 단순한 형태 — 나중에 파싱 로직 추가 예정
    public String fetchPageContent(String url) throws Exception {

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0") // 봇 차단 우회용 브라우저 헤더
                .timeout(10000)           // 10초 타임아웃
                .get();

        return doc.text(); // HTML 파싱해서 텍스트만 추출
    }
}