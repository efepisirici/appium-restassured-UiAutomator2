# CI/CD Pipeline Overview

This document describes the CI/CD pipeline configured for our project. The pipeline is set up to run tests, generate reports, and publish test artifacts.

## Pipeline Configuration

- **Trigger**: The pipeline is triggered on any commit to the `main` branch.
- **Schedule**: Scheduled to run at 8:00 AM UTC, Monday to Friday.
- **Agent Pool**: Uses the `ubuntu-latest` virtual machine image.

## Pipeline Steps

1. **Docker Compose Server Startup**:
    - Starts the server using `docker-compose up`.
    - This ensures that all required services are up and running before tests are executed.

2. **Run Tests**:
    - Executes tests using Maven.
    - The `pom.xml` file at the root of the project specifies the project configuration.
    - Test results are published for visibility in the pipeline results.

3. **Allure Report Installation**:
    - Installs `allure-commandline` and `allure-combine`.
    - Allure is used for generating readable and interactive reports from test results.

4. **Allure Report Generation**:
    - Generates an Allure report from the test results.
    - The `--clean` option ensures that any previous reports are cleared before generating new ones.

5. **Allure Combine Report**:
    - Combines individual Allure reports into a single HTML file for easier access and readability.

6. **Copy Test Reports**:
    - Copies the `complete.html` file from the Allure report to a specific location in the project directory for later use.

7. **Archive Test Reports**:
    - Creates a ZIP archive of the Allure reports.
    - This archive is then stored in the `/src/test/resources/` directory.

8. **Publish Test Artifacts**:
    - Publishes the test artifacts (including the Allure report zip file) for access in subsequent pipeline tasks or for download post-build.

## Running the Pipeline

The pipeline runs automatically on commits to the `main` branch or as per the defined schedule. It can also be triggered manually if required.

## Artifacts

After the pipeline execution, the generated test artifacts, including the Allure reports, are available for download from the build page. These reports offer insights into the test execution and are useful for debugging and improving test suites.

---

