# TaxRadar Backend — CLAUDE.md

## Role
Code reviewer and mechanical task assistant. The user writes the code; Claude reviews and corrects. Do not write full implementations unless explicitly asked.

## Project
Czech OSVČ invoicing, expense tracking, and tax-threshold monitoring app.
- **Stack:** Spring Boot 4.1.0, Java 21, Maven, PostgreSQL (Railway)
- **Group ID:** `com.taxradar.backend` — never change this
- **Port:** 8081

## Architecture
Hexagonal (ports & adapters), enforced via package separation:

```
com.taxradar.backend
├── domain          # Entities, enums, BaseEntity — no Spring dependencies
├── application     # Services, ports (interfaces), DTOs (records)
├── infrastructure  # Spring Data JPA repositories, config (JpaConfig, SecurityConfig)
└── api             # Controllers, main application class
```

### Rules
- Application layer imports domain only — never infrastructure
- Services inject port interfaces (`UserRepositoryPort`), never `UserRepository` directly
- Infrastructure repositories extend both `JpaRepository<T, Long>` and the port interface — Spring Data provides the implementation automatically
- Controllers inject services only, never repositories or ports

## Conventions

### Entities
- Extend `BaseEntity` (`id`, `createdAt`, `updatedAt` via JPA auditing)
- `@Enumerated(EnumType.STRING)` on all enum fields
- `FetchType.LAZY` on all `@ManyToOne`
- No setters except for optional/nullable fields set after construction
- Protected no-arg constructor for JPA

### DTOs
- Java records only
- Request records: Bean Validation annotations (`@NotBlank`, `@NotNull`, `@Positive`, etc.)
- Response records: include `id`, `createdAt`, `updatedAt`; enums as `String` (`.name()`)
- AI-set fields (`aiSuggestedCategory`, `aiSuggestedDeductible`) excluded from request records

### Services
- `@Service @Transactional` at class level
- `@Transactional(readOnly = true)` on read methods
- `import org.springframework.transaction.annotation.Transactional` — never `jakarta`
- Throw `EntityNotFoundException` when entity not found
- Return `List<Response>` for multi-result queries — never `Optional<List<>>`

### Controllers
- Constructor injection only
- `@RequestBody @Valid` on all POST request bodies
- Method names: camelCase (not PascalCase)
- URL paths: plural nouns (`/api/invoices`, `/api/expenses`, `/api/users`)
- POST → `ResponseEntity.status(HttpStatus.CREATED).body(...)`
- GET → `ResponseEntity.ok(...)`

### Financial
- `BigDecimal` for all monetary amounts
- `RoundingMode.HALF_UP` with scale 2 for division
- VAT formula: `amountWithoutVat.multiply(BigDecimal.ONE.add(vatRate.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)))`

### Types
- `Instant` for audit timestamps (`createdAt`, `updatedAt`)
- `LocalDate` for date fields (`issueDate`, `dueDate`, `expenseDate`)
- `Boolean` (boxed) not `boolean` (primitive) when `@NotNull` is needed

## Package naming
All lowercase. Use `commands` not `Commands`, `services` not `Services`.

## Application entry point
`TaxRadarBackendApplication` lives in `com.taxradar.backend.api` — requires explicit:
```java
@EntityScan("com.taxradar.backend.domain.entities")
@EnableJpaRepositories("com.taxradar.backend.infrastructure.repositories")
@SpringBootApplication(scanBasePackages = "com.taxradar.backend")
```

## Infrastructure config
- `JpaConfig` — `@EnableJpaAuditing` (required for `createdAt`/`updatedAt`)
- `SecurityConfig` — CSRF disabled, all requests permitted (JWT auth pending)
- Database credentials via environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

## Pending
- TaxThresholdService and TaxThresholdController
- JWT authentication
- Flyway/Liquibase migrations (replace `ddl-auto=update`)
- Global exception handler (`@ControllerAdvice`) for `EntityNotFoundException`, `IllegalArgumentException`
- Tests
