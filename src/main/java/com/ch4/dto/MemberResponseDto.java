package com.ch4.dto;

import com.ch4.entity.Mbti;

import lombok.Getter;

@Getter
public class MemberResponseDto {
	private final Long id;
	private final Integer age;
	private final String name;
	private final Mbti mbti;

	public MemberResponseDto(Long id ,Integer age, String name, Mbti mbti){
		this.id = id;
		this.age = age;
		this.name = name;
		this.mbti = mbti;
	}
}
