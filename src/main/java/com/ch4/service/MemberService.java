package com.ch4.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch4.dto.MemberRequestDto;
import com.ch4.dto.MemberResponseDto;
import com.ch4.entity.Member;
import com.ch4.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public MemberResponseDto createMember(MemberRequestDto request) {
		Member member = new Member(request.getName(), request.getAge(), request.getMbti());
		Member savedMember = memberRepository.save(member);
		return new MemberResponseDto(savedMember.getId(), savedMember.getAge(), savedMember.getName(),
			savedMember.getMbti());
	}

	@Transactional
	public MemberResponseDto getMember(Long id) {
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 팀원입니다.")
		);
		return new MemberResponseDto(member.getId(), member.getAge(),member.getName(), member.getMbti());
	}
}
