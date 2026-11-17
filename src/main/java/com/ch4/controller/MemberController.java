package com.ch4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ch4.dto.MemberRequestDto;
import com.ch4.dto.MemberResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	@PostMapping("/api/members")
	public ResponseEntity<MemberResponseDto> saveMember(@RequestBody @Valid MemberRequestDto request){
		MemberResponseDto responseDto = memberService.createMember(request);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/api/members/{id}")
	public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id){
		MemberResponseDto responseDto = memberService.getMember(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
