# space-project

## 프로젝트명
- Space Moon

## 구축 서비스
- 공간 대여 서비스

## 참고 사이트
- space cloud  https://www.spacecloud.kr/

## 개발환경

### IDE : 이클립스
### 프레임워크
- Spring : spring legacy project로 개발 구축

- 웹어플리케이션 프레임워크
  - SpringMVC 패턴 이용 
  - 메이븐 빌드툴 사용
- 데이타베이스 관련 프레임워크
  - myBatis 프레임워크
- 비지니스(처리) 관련 프레임워크
  - spring 프레임워크

### DB
- DATABASE : 오라클
- DBMS 접속기술 : JDBC 이용

### 웹서버 + 웹컨테이너(WAS)
- 아파치 톰캣

### View
- JSP(EL, JSTL),HTML,CSS,bootstrap,JAVASCRIPT,jQuery

### 협업 툴
- 형상관리 : svn, github
- 데이터모델링 : ERD cloud

### 빌드자동화도구
- maven

### 테스트도구
- junit4

## 배포 가이드

이 저장소에는 두 가지 앱이 있습니다.

- `space/`: Spring Legacy MVC (Oracle + 외부 Tomcat WAR 배포)
- `space-dev/`: Spring Boot 샘플 (WAR 배포용)

### 1) space-dev 배포 (빠르게 확인용)

```bash
cd space-dev
mysql -h 192.168.9.144 -P 13306 -u root -prootpass < db/init-ebpdb.sql
mvn clean package
```

생성 파일:
- `space-dev/target/space-dev-0.0.1-SNAPSHOT.war`

톰캣 배포:
1. WAR 파일을 `<TOMCAT_HOME>/webapps/` 에 복사
2. 톰캣 재기동
3. `http://<host>:<port>/space-dev-0.0.1-SNAPSHOT/` 접속

### 2) space(legacy) 배포

```bash
cd space
mvn clean package
```

생성 파일:
- `space/target/controller-1.0.0-BUILD-SNAPSHOT.war`

톰캣 배포:
1. WAR 파일을 `<TOMCAT_HOME>/webapps/` 에 복사
2. 톰캣 재기동
3. `http://<host>:<port>/controller-1.0.0-BUILD-SNAPSHOT/` 접속

> 참고: `space`는 Oracle DB 설정 및 스프링 XML 설정(데이터소스, MyBatis, 파일 업로드 경로 등)을
> 운영 환경에 맞게 반드시 점검해야 합니다.

### 개인 Controller 및 View 링크

Controller 링크

- https://github.com/Ggggggaaq/final/blob/master/space/src/main/java/xyz/itwill/controller/MemberHostBoardController.java

View 링크

메인 페이지
-  https://github.com/Ggggggaaq/final/tree/master/space/src/main/webapp/WEB-INF/views/main

멤버 Review, Q&A, Reserve 페이지
-  https://github.com/Ggggggaaq/final/tree/master/space/src/main/webapp/WEB-INF/views/member

필터 검색 및 헤더 페이지
-  https://github.com/Ggggggaaq/final/tree/master/space/src/main/webapp/WEB-INF/views/layout

호스트 Review, Q&A, Reserve 페이지
-  https://github.com/Ggggggaaq/final/tree/master/space/src/main/webapp/WEB-INF/views/host
