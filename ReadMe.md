# [Spring 6기] CH4 클라우드_아키택처 설계 & 배포

---


## LV 0 - 요금 폭탄 방지 AWS Budget 설정
![budget.png](images/budget.png)

---

## LV 1 - 네트워크 구축 및 핵심 기능 배포
![ec2publicIp.png](images/ec2publicIp.png)
![lv1healthcheck.png](images/lv1healthcheck.png)

---

## LV 2 - DB 분리 및 보안 연결하기

![InboundRule.png](images/InboundRule.png)
![securitygroup.png](images/securitygroup.png)

막힌 부분)
prod.yml optional 띄워쓰기
- Optional 키워드 삭제
rds인증방식 변경
- 실습 때는 로컬 환경에서 사용했기 때문에 jdbc:mysql://database-1.ch8u6oi82fqa.ap-northeast-2.rds.amazonaws.com:3306/ch4_db?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8 useSSL을 false로 설정해도 문제가 없었다
- jdbc:mysql://database-1.ch8u6oi82fqa.ap-northeast-2.rds.amazonaws.com:3306/ch4_db?sslMode=REQUIRED&serverTimezone=Asia/Seoul&characterEncoding=UTF-8 required로 수정
의존성 추가 
- implementation 'io.awspring.cloud:spring-cloud-aws-starter-parameter-store:4.1.1'
rds 스키마 생성
- mysql -h database-1.ch8u6oi82fqa.ap-northeast-2.rds.amazonaws.com -u admin -p{비밀번호} 로 mysql 진입 후 create table ch4_db 테이블 생성

rds 생성 후 스키마 생성 (ch4_db)

ParameterStore에 TEAM_NAME 설정 내용
![teamname.png](images/teamname.png)
http://13.125.176.162:8080/actuator/info 연결 시
![printteamname.png](images/printteamname.png)

---

## LV3 - 프로필 사진 기능 추가와 권한 관리
![imageupload.png](images/imageupload.png)
![imagedownload.png](images/imagedownload.png)
![imageduploadedsite.png](images/imageduploadedsite.png)

[presigned URL](https://ch4-myhyeoeo-files.s3.ap-northeast-2.amazonaws.com/bb6d7189-e667-43ec-8a8e-1a9d0ebd5192_images.png?X-Amz-Security-Token=IQoJb3JpZ2luX2VjECwaDmFwLW5vcnRoZWFzdC0yIkcwRQIhAIZQ2hI7evVdxa%2BcZvPJEV3CBfCK8Fshwl%2FOQTOWCyV2AiAy4qXJ180yc38M7GHU5DW%2BCBctDFK2OBXmIXTclVo0%2FCrTBQj1%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ0MzE1MzM4NjE3OCIMesaEoPX1W7q7e%2BKkKqcFRKseyRcEEOXLS0SYx2hmo7qJw59hFYDzvWXs71hcE0Dz%2BsiNy2GUc8iSkzFDHsv3J15TNTM9tSXwddifPDaWuqw%2F6fs5%2BwqOvBFUSfao4FsN1oNgJtJeRJCOT6TWOSuRG%2FiXIMivKVw9KA9C0Cd6XDXwrf1MwM4b6UqUmQ%2B3XSnOXn147lSp1QZ3I2hd5YELy1h4sqFaNJYig8ojHDVKB5U2ksckWSPBTEtKKGxLssQMbtr%2B5HGNFMSAfURtbmCw8qr6wIzBcgF%2FIMpKkRUueNlOdWLGgt8OSiPsbZeSANFTXDbTXcOO0Ny0fxZoHqVZEqqH%2F8KvIy3Msztc7t59qL%2F%2FO%2FS1Uj6TJV7Kt8xONyk8Dl81ngqHm21zMusPLlkXsWlel5QfbaH6KQ0UK8t9Dp4oO0YRAM8WvAwHrh%2BGI7hxjezkE8DereioU4sQhpdHQWT8pkfUGDK%2FZZ2fzNHsEWrLmOO3mTf6O8s6j4yEecKqrD1hNpl3a1gqkVwMXHukB0EjbjZ7r5ziqJnNiPxKCo8SB75hBm0CDObG5zNyyJ4AFcUaM11%2FWkaPM%2BXLMv%2B6Z2blsGGfrGMP%2FEMhFG7FADcBzm%2F7Tg5e2z0ppWX7Q4G%2FGGdTA35vEQNPL%2B6eLPDie3SzJSvH10MmQ%2Beny5vm8RQIP5zS2QGo7uP3qzJ4K%2BqVtXs2pQM6RP%2FwEOb%2Fi9Kf4%2BSqVRQQ1BtpZj%2FcQx6gmHEaLBDI6eBRbX%2BByA4umTrJgmKWSNui1zlTCMQbIpFqtz%2FGL7Tjh2OKzIvpYQQsoI0YBk2zev%2BkpUbwm%2BVV9nM6lpTfhSlPhZ2%2FJOi1y8Owlk5ZPmY0zVemLIIzCqKy1yjRilYhVUgFMQZrCW2DJlAgosRD%2BR1PJPrxkhca6NeJTAy%2BWEfVyzDZufTXBjqxAcyjs%2FC7msdgCmvE3N6Txdhqgn%2Bp%2FRtumAc79e7T1f219of%2BHhpVUjO1hMxA%2Bhsh6K0NkP5LDJEJqC%2Fkxs4ZriX6ROFb7pdLGTKeUFVMb5YHds2bsbVxwLzyJXTPuwfmQ9%2F3UMe%2Bzs1HkxJ%2FmDeW3SlNoYd26mrPywUgSxLC1KEAbz0YWw9zzEHo12NaA14vBHwg4vioP8%2BjoTF4I2nXoiij5GTuVUXQA2DbKsfc8aU1Yg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20261118T035523Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIAWOLQABLBNTVII55X%2F20261118%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=dfd41f197cc10db8acb7808dd3dc8bc2f1e2bf9c1ea4bf5a73b4425ceb2bc6bc)

## LV4 - Docker & CI/CD 파이프라인 구축

진행 방식 ) 

실습용 deploy.yml과 Dockerfile을 가져와서 사용

문제점)

1. OICD 인증 실패
-> 역할 -> 신뢰할 수 있는 엔티티의 신뢰 부분을 수정
-> repo:myhyeoeo@126792624/*
-> 나의 계정 깃헙에 있는 레포지토리는 어떤 것이든 신뢰하도록

2. Dokcerfile 수정
-> 기존코드 COPY app.jar app.jar -> COPY <복사할_파일_경로> <이미지_내_저장_경로>
-> jar 파일의 이름 변경(ch_....jar -> app.jar) + 경로 설정 COPY build/libs/app.jar app.jar

3. Deploy 테스트 과정 실패
-> 임의의 테스트를 통과하는데 테스트가 parameter store의 값을 불러오지 못하는 문제 발생
-> 테스트 폴더에 테스트용 application.yml 파일 생성 -> 인메모리 DB인 H2를 만들어 사용

![deploy.png](images/deploy.png)
![docker.png](images/docker.png)

## LV 5 - 고가용성 아키텍처와 보안 도메인 연결 (ALB + ASG + HTTPS) 
여기서 질문이 생겼다. PVC 내의 같은 프라이빗 서브넷인데 왜 보안그룹을 통해서 EC2와 RSD를 관리하는거지?

EC2와 RDS를 새로 생성하였습니다. 그리고 보안그룹(my-rds-sg)을 만들어서 3306 포트만 열었습니다
EC2만 DB에 접근 가능하도록 하기 위함입니다.

정리하고 가야할 것

서브넷 : 네트워크 구역 (IP 대역, 라우팅)
보안 그룹 : 실제 허용/차단 규칙
그러니까 프라이빗 서브넷 안에 있다고 해서 서브넷 내부 통신을 세밀하게 제한하거나 허용해주는 규칙이 자동으로 만들어지는 것은 아니다
EC2->RDS는 허용하지만 그 외의 것은 차단한다

-> 프라이빗 서브넷에 둔다는건 외부 인터넷에서 바로 들어오는 경로를 막는 효과가 있다. 서브넷 내부의 리소스들 끼리 무조건 허용해주거나 하진 않음

로드밸런서를 EC2 앞단에 두어 HTTP(80) → HTTPS(443) 리다이렉트와 ACM 인증서(myhyeoeo.click) 적용