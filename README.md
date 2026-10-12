# ecommerce-patterns: Week 4 base project (ICSI304)

Small E-Commerce subsystem used as the starting point (`main`) for the five
creational-pattern refactorings.

## Requirements
- JDK 21, Maven 3.9+, Git

## Run the tests
    mvn clean test

(If you later add the Maven Wrapper with `mvn wrapper:wrapper`, you can use `./mvnw clean test` instead.)

## What is on `main` (the "smells" you will fix)
| Class | Smell | Fixed by branch |
|---|---|---|
| `OrderService.createOrder` | `switch` on tier string | `feature/factory-method-fix` |
| `Order` | 6-argument public constructor, no validation | `feature/builder-fix` |
| `NotificationService` | `if/else` picks channel and format separately | `feature/abstract-factory-fix` |
| `Order.applyAuditTimestamp` | calls `Instant.now()` directly | `feature/singleton-fix` |
| (none yet) | re-orders rebuild carts by hand | `feature/prototype-fix` |

## Branch workflow
    git checkout main
    git checkout -b feature/<name>
    # write code + test
    mvn clean test
    git add . && git commit -m "feat(...): ..."
