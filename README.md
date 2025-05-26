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

## Examples

Here are some examples of how the rule-based analysis works with different types of content:

### High Severity Violations (80-100)

```json
// Hate Speech
{
  "text": "I hate all people from that country and want to eliminate all of them",
  "violation": "Hate Speech",
  "severityScore": 95
}

// Extreme Violence
{
  "text": "Someone should torture and murder that person",
  "violation": "Extreme Violence",
  "severityScore": 90
}

// Discriminatory Language
{
  "text": "Only white power supporters are welcome here",
  "violation": "Discriminatory Language",
  "severityScore": 85
}

// Self Harm Risk
{
  "text": "I want to kill myself and end my life",
  "violation": "Self Harm Risk",
  "severityScore": 90
}
```

### Medium-High Severity Violations (60-79)

```json
// Harassment
{
  "text": "I will stalk and intimidate you until you leave",
  "violation": "Harassment",
  "severityScore": 75
}

// Sexual Harassment
{
  "text": "I want to send you explicit photos and touch you",
  "violation": "Sexual Harassment",
  "severityScore": 70
}

// Personal Attack
{
  "text": "You are a complete moron and worthless",
  "violation": "Personal Attack",
  "severityScore": 65
}

// Misleading Content
{
  "text": "The vaccine is a hoax and climate change is a lie",
  "violation": "Misleading Content",
  "severityScore": 60
}
```

### Medium Severity Violations (40-59)

```json
// Profanity
{
  "text": "You are a complete asshole",
  "violation": "Profanity",
  "severityScore": 55
}

// Mild Inappropriate Language
{
  "text": "This is a damn stupid situation",
  "violation": "Mild Inappropriate Language",
  "severityScore": 45
}

// Spam
{
  "text": "Click here to buy now! Limited time offer!",
  "violation": "Potential Spam",
  "severityScore": 50
}
```

### Low Severity Violations (20-39)

```json
// Off-Topic
{
  "text": "This is completely unrelated to our discussion",
  "violation": "Off-Topic Content",
  "severityScore": 30
}

// Excessive Capitalization
{
  "text": "THIS IS REALLY IMPORTANT",
  "violation": "Excessive Capitalization",
  "severityScore": 25
}

// Excessive Punctuation
{
  "text": "This is amazing!!!???",
  "violation": "Excessive Punctuation",
  "severityScore": 20
}
```

### No Violation

```json
{
  "text": "This is a normal, respectful message",
  "violation": "No violation detected by Drools rules",
  "severityScore": 0
}
```

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