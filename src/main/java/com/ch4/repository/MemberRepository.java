package com.ch4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch4.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
