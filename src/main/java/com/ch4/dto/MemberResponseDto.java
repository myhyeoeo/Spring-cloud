package com.ch4.dto;

import com.ch4.entity.Mbti;

import lombok.Getter;

@Getter
public class MemberResponseDto {
	private final Integer age;
	private final String name;
	private final Mbti mbti;

	public MemberResponseDto(Integer age, String name, Mbti mbti){
		this.age = age;
		this.name = name;
		this.mbti = mbti;
	}
}
