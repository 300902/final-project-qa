# Automation Testing Project

This project provides comprehensive automation testing for both API and Web UI using Java, Cucumber BDD, RestAssured, and Selenium WebDriver with **Gradle** build system.

## Project Structure

```
automation-testing/
├── src/test/java/com/automation/
│   ├── runner/
│   │   ├── TestRunner.java          # Main test runner (all tests)
│   │   ├── ApiTestRunner.java       # API tests only
│   │   └── WebTestRunner.java       # Web UI tests only
│   └── steps/
│       ├── api/
│       │   └── UserApiSteps.java    # API step definitions
│       └── web/
│           ├── LoginSteps.java      # Login step definitions
│           └── CartSteps.java       # Cart step definitions
├── src/test/resources/features/
│   ├── api/
│   │   └── user.feature             # API test scenarios
│   └── web/
│       ├── login.feature            # Login test scenarios
│       └── cart.feature             # Cart test scenarios
├── .github/workflows/
│   └── main.yml                     # GitHub Actions pipeline
├── build.gradle                     # Gradle build configuration
└── gradle/wrapper/                  # Gradle wrapper files
```

## Test Coverage

### API Testing (DummyAPI.io)
- **GET User by ID**: Valid and invalid ID scenarios
- **POST Create User**: Valid payload and empty payload scenarios
- **PUT Update User**: Valid and invalid ID scenarios
- **DELETE User**: Valid and invalid ID scenarios
- **GET Tags**: Basic functionality test

### Web UI Testing (SauceDemo.com)
- **Login**: Valid credentials, invalid credentials, empty fields
- **Add to Cart**: Successful addition, cart validation
- **Checkout**: Cart management and validation

## Technologies Used

- **Java 11**: Programming language
- **Gradle 8.4**: Build and dependency management
- **Cucumber 7.14.0**: BDD framework for test scenarios
- **RestAssured 5.3.2**: API testing library
- **Selenium WebDriver 4.15.0**: Web UI automation
- **JUnit 5.10.0**: Test framework
- **GitHub Actions**: CI/CD pipeline

## Prerequisites

- Java 11 or higher
- Gradle 8.4+ (or use included wrapper)
- Chrome browser (for Web UI tests)
- Internet connection (for API and Web UI tests)

## Running Tests Locally

### Run All Tests
```bash
./gradlew allTests
```

### Run API Tests Only
```bash
./gradlew apiTest
```

### Run Web UI Tests Only
```bash
./gradlew webTest
```

### Clean and Build
```bash
./gradlew clean build
```

### Generate Reports
Reports are automatically generated in `build/reports/cucumber/` directory

## GitHub Actions Pipeline

The pipeline automatically runs on:
- Push to `main` or `develop` branches
- Pull requests to `main` or `develop` branches
- **Manual trigger** with test type selection (all/api/web)

### Pipeline Jobs

1. **API Tests**: Runs all API-related tests using `./gradlew apiTest`
2. **Web Tests**: Runs all Web UI-related tests with Chrome browser using `./gradlew webTest`
3. **Full Test Suite**: Combines results and generates comprehensive reports using `./gradlew allTests`

### Manual Workflow Trigger

You can manually trigger the workflow from GitHub Actions tab with options:
- **all**: Run all tests (API + Web)
- **api**: Run only API tests
- **web**: Run only Web UI tests

### Artifacts Generated

- **API Test Results**: JSON, XML, and HTML reports (`build/reports/cucumber/api/`)
- **Web Test Results**: JSON, XML, HTML reports (`build/reports/cucumber/web/`)
- **Combined Results**: Merged test reports and logs (`build/reports/cucumber/all/`)

## Test Reports

After test execution, reports are available in:
- `build/reports/cucumber/`: HTML, JSON, XML reports for each test type
- `build/reports/tests/`: Gradle test reports
- GitHub Actions artifacts (for CI/CD runs)

## Configuration

### Environment Variables
- Tests use default configurations for DummyAPI.io and DemoBlaze.com
- Chrome runs in headless mode for CI/CD

### Parallel Execution
- API and UI tests run in separate jobs for better performance
- Tests can be tagged for selective execution

## Test Tags

- `@api`: API-related tests
- `@web`: Web UI-related tests (formerly `@ui`)
- `@positive`: Positive test scenarios
- `@negative`: Negative test scenarios

## Gradle Tasks

### Custom Test Tasks
```bash
# API tests only (includes @api tagged scenarios)
./gradlew apiTest

# Web tests only (includes @web tagged scenarios)  
./gradlew webTest

# All tests (both API and Web)
./gradlew allTests

# Standard Gradle tasks
./gradlew clean
./gradlew build
./gradlew test
```

## Contributing

1. Clone the repository
2. Create a feature branch
3. Add your tests following the existing pattern
4. Run tests locally to ensure they pass
5. Create a pull request

## Troubleshooting

### Common Issues

1. **Chrome Driver Issues**: WebDriverManager automatically handles Chrome driver setup
2. **API Rate Limits**: DummyAPI.io has rate limits; tests include appropriate waits
3. **UI Element Changes**: DemoBlaze website elements may change; update selectors accordingly

### Debug Mode

For Web UI tests with visible browser (local development):
```java
// Remove headless option in LoginSteps.java and CartSteps.java
// options.addArguments("--headless");
```

### Gradle Build Issues

1. **Permission Issues**: Make gradlew executable with `chmod +x gradlew`
2. **Dependency Issues**: Run `./gradlew clean build --refresh-dependencies`
3. **Test Failures**: Check `build/reports/tests/` for detailed reports

## API Documentation

- **DummyAPI Base URL**: https://dummyapi.io/data/v1
- **Required Header**: app-id (configured in tests)
- **Endpoints Tested**:
  - GET /user/{id}
  - POST /user/create
  - PUT /user/{id}
  - DELETE /user/{id}
  - GET /tag

## Web UI Test Target

- **Website**: https://www.saucedemo.com/
- **Features Tested**:
  - User login functionality
  - Product cart management
  - Cart validation

## License

This project is for educational and testing purposes.
