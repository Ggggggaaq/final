# space-dev (WAR 배포용 샘플)

기존 프로젝트와 유사하게 **공간 등록 + 예약 등록**을 할 수 있는 간단한 Spring Boot 프로젝트입니다.

## DB 정보
- Host: `192.168.9.144:13306`
- DB: `ebpdb`
- Username: `root`
- Password: `rootpass`

`db/init-ebpdb.sql` 파일로 DB/테이블을 먼저 생성하세요.

```bash
mysql -h 192.168.9.144 -P 13306 -u root -prootpass < db/init-ebpdb.sql
```

## 실행
```bash
mvn spring-boot:run
```

## WAR 빌드
```bash
mvn clean package
```

빌드 결과물:
- `target/space-dev-0.0.1-SNAPSHOT.war`

## 외부 톰캣 배포
1. 톰캣 `webapps/` 디렉터리에 WAR 파일 복사
2. 톰캣 재기동
3. `http://<tomcat-host>:<port>/space-dev-0.0.1-SNAPSHOT/` 접속

## 구성
- `venue` 테이블: 공간 정보
- `reservation` 테이블: 예약 정보
