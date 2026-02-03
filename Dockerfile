# Start from Playwright image (already has browsers installed)
FROM mcr.microsoft.com/playwright:v1.57.0

# Install Java and Maven
RUN apt-get update
RUN apt-get install -y openjdk-17-jdk maven
RUN apt-get clean

# Set environment so Playwright uses pre-installed browsers
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PLAYWRIGHT_BROWSERS_PATH=/ms-playwright
ENV PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1

# Set working directory inside container
WORKDIR /app

# Copy your project files into the container
COPY . .

# Run tests by default
CMD ["mvn", "clean", "verify", "-Dheadless=true"]


# Build a Docker image from the Dockerfile
# docker build -t <image-name> .

# Run the Docker image with password
# docker run -e APP_PASSWORD=nustseecs@2K13 <image-name>