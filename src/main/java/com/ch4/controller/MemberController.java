package com.ch4.controller;

import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ch4.dto.MemberRequestDto;
import com.ch4.dto.MemberResponseDto;
import com.ch4.entity.Member;
import com.ch4.repository.MemberRepository;
import com.ch4.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/api/members")
	public ResponseEntity<MemberResponseDto> saveMember(@RequestBody @Valid MemberRequestDto request) {
		MemberResponseDto responseDto = memberService.createMember(request);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/api/members/{id}")
	public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
		MemberResponseDto responseDto = memberService.getMember(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PostMapping("/api/members/{id}/profile-image")
	public ResponseEntity<String> uploadProfileImage(
		@PathVariable Long id,
		@RequestParam("image") MultipartFile image
	) {
		memberService.uploadProfileImage(id, image);
		return ResponseEntity.ok("프로필 이미지 업로드 성공");
	}

	@GetMapping("/api/members/{id}/profile-image")
	public ResponseEntity<String> getProfileImagePresignedUrl(@PathVariable Long id) {
		String presignedUrl = memberService.getProfileImagePresignedUrl(id);

		return ResponseEntity.ok(presignedUrl);
	}
}
