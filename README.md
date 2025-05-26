# Code of Conduct Rule-Based Analysis

A Spring Boot application that analyzes user-submitted prompts for Code of Conduct violations using Drools rules engine and optionally integrates with OpenAI's Moderation API for enhanced content analysis.

## Features

- Rule-based analysis of text content using Drools
- Integration with OpenAI Moderation API (optional)
- RESTful API endpoints for content analysis
- Swagger/OpenAPI documentation
- Configurable rules through Drools decision tables
- Detailed violation reporting with severity levels

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- OpenAI API key (optional, for enhanced moderation)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/coc-rule-based.git
cd coc-rule-based
```

### 2. Configuration

Create an `application.properties` file in `src/main/resources` with the following properties:

```properties
# OpenAI Configuration (Optional)
openai.api.key=your-api-key-here
openai.api.url=https://api.openai.com/v1/moderations

# Server Configuration
server.port=8080
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Available Endpoints

- `POST /api/analyze`: Analyze text content for Code of Conduct violations
  - Request body: `{"text": "content to analyze"}`
  - Response: Analysis results with violations and severity levels

## Project Structure

```
src/main/java/com/example/cocrulebased/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── model/          # Data models
├── service/        # Business logic
└── rules/          # Drools rules files
```

## Customizing Rules

The application uses Drools decision tables for rule configuration. You can modify the rules by editing the Excel file located at:
```
src/main/resources/rules/code-of-conduct-rules.xlsx
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot
- Drools Rules Engine
- OpenAI API
- SpringDoc OpenAPI 