package com.ch4.dto;

import com.ch4.entity.Mbti;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
	@NotNull(message = "나이는 필수입니다")
	@Min(value = 1)
	@Max(value = 200)
	private Integer age;

	@NotNull(message = "이름은 필수입니다")
	private String name;

	@NotNull(message = "MBTI는 필수입니다")
	private Mbti mbti;

}
