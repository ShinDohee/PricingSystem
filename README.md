## 구현 범위

이 프로젝트는 **Musinsa Pricing System**으로, 주어진 가격 정보를 바탕으로 다양한 가격 관련 API를 제공하는 시스템입니다. 주요 기능은 다음과 같습니다:

### 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회 API
- 각 카테고리별로 최저 가격 상품과 해당 브랜드를 조회하는 API입니다.
- 응답 값은 카테고리명, 브랜드명, 최저 가격을 포함합니다.

### 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
- 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API입니다. 
- 응닶 값은 브랜드명, 카테고리별 금액, 총액을 포함합니다. 

### 3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
- 주어진 카테고리명으로 해당 카테고리의 최저가 및 최고가 상품과 브랜드를 조회하는 API입니다.
- 응닶 값은 카테고리명, 최저가 브랜드와 가격, 최고가 브랜드와 가격을 포함합니다. 

### 4. 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
- 브랜드, 상품 조회: 특정 상품을 ID로 조회할 수 있습니다.
- 전체 브랜드, 전체 상품 조회: 모든 상품을 조회할 수 있습니다.
- 브랜드, 상품 추가: 새로운 상품을 추가할 수 있습니다.
- 브랜드, 상품 수정: 기존 상품의 정보를 수정할 수 있습니다.
- 브랜드, 상품 삭제: 특정 상품을 삭제할 수 있습니다.
-------
## 코드 빌드, 테스트, 실행 방법

### 1. 프로젝트 클론
먼저 프로젝트를 GitHub에서 클론합니다.

```bash
git clone https://github.com/your-username/musinsa-pricing-system.git
cd musinsa-pricing-system
```
### 2. 빌드
프로젝트는 Gradle을 사용하여 빌드합니다. 다음 명령어로 프로젝트를 빌드할 수 있slek.
```bash
./gradlew clean build
```

### 3. 실행
   빌드가 완료되면, Spring Boot 애플리케이션을 실행할 수 있습니다. 다음 명령어를 사용하여 애플리케이션을 실행합니다:

``` bash
./gradlew bootRun
```

### 4. 테스트 방법 
```SH
./gradlew build
./gradlew test
./gradlew bootRun
```
------

## 기타 추가 정보

### 사용된 기술 스택

- **Java 17**  

- **Spring Boot 2.7.x**  

- **JPA (Hibernate)**  

- **H2 Database**  

- **Gradle (빌드 및 의존성 관리)**  

- **JUnit 5 (단위 테스트)**  

- **Lombok (코드 간결화)**  



