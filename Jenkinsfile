pipeline {
    agent any

    tools {
        maven 'Maven-3.9.8'
        jdk 'JDK-21'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git url: 'https://github.com/abijith-suresh/api-gateway.git', branch: 'main'
            }
        }

        stage('Pre-Build') {
            steps {
                // Pre-build Docker cleanup steps
                bat '''
                docker stop api-gateway || true
                docker rm api-gateway || true
                docker rmi -f api-gateway:latest || true
                '''
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                bat 'mvn clean install'
            }
        }

        stage('Create Docker Network') {
            steps {
                // Create a Docker network if it doesn't exist
                bat 'docker network create my-network || echo "Network already exists"'
            }
        }

        stage('Post-Build') {
            steps {
                // Build and run the Docker container
                bat '''
                docker build -t api-gateway:latest .
                docker run -d --network my-network -p 9095:9095 --name api-gateway api-gateway:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
