package com.ch4.service;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ch4.dto.MemberRequestDto;
import com.ch4.dto.MemberResponseDto;
import com.ch4.entity.Member;
import com.ch4.repository.MemberRepository;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final S3Template s3Template;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucketName;

	@Transactional
	public MemberResponseDto createMember(MemberRequestDto request) {
		log.info("[API - LOG] 팀원 생성 요청: name={}, age={}, mbti={}", request.getName(), request.getAge(), request.getMbti());
		Member member = new Member(request.getName(), request.getAge(), request.getMbti());
		Member savedMember = memberRepository.save(member);
		log.info("[API - LOG] 팀원 생성 완료: id={}", savedMember.getId());
		return new MemberResponseDto(savedMember.getId(), savedMember.getAge(), savedMember.getName(),
			savedMember.getMbti());
	}

	@Transactional
	public MemberResponseDto getMember(Long id) {
		log.info("[API - LOG] 팀원 조회 요청: id={}", id);
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 팀원입니다.")
		);
		log.info("[API - LOG] 팀원 조회 완료: id={}", member.getId());
		return new MemberResponseDto(member.getId(), member.getAge(),member.getName(), member.getMbti());
	}

	@Transactional
	public void uploadProfileImage(Long id, MultipartFile image) {
		log.info("[API - LOG] 프로필 이미지 업로드 요청: memberId={}", id);
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 팀원입니다.")
		);

		String originalFilename = image.getOriginalFilename();
		String storeFileName = UUID.randomUUID() + "_" + originalFilename;

		try {
			s3Template.upload(bucketName, storeFileName, image.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("S3 파일 업로드 실패", e);
		}

		member.updateProfileImageUrl(storeFileName);
		log.info("[API - LOG] 프로필 이미지 업로드 완료: fileKey={}", storeFileName);
	}

	@Transactional(readOnly = true)
	public String getProfileImagePresignedUrl(Long id) {
		log.info("[API - LOG] 프로필 이미지 Presigned URL 조회 요청: memberId={}", id);
		Member member = memberRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 팀원입니다.")
		);

		String fileKey = member.getProfileImageUrl();
		if (fileKey == null || fileKey.isBlank()) {
			throw new IllegalArgumentException("등록된 프로필 이미지가 없습니다.");
		}

		URL presignedUrl = s3Template.createSignedGetURL(bucketName, fileKey, Duration.ofDays(7));
		log.info("[API - LOG] Presigned URL 생성 완료 (7일 유효)");

		return presignedUrl.toString();
	}
}
