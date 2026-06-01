package com.pigeonkim.jobmatcheragent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    // url로 이미 저장된 공고가 있는지 확인
    // Spring Data JPA가 메서드 이름만 보고 쿼리 자동 생성
    Optional<JobPosting> findByUrl(String url);
}
