pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rakeshk205/RestRepo.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'  // Use this if you have mvnw wrapper
                // OR
                // sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'  // Run tests
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t springboot-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                   docker stop springboot-app || true
                   docker rm springboot-app || true
                   docker run -d -p 8080:8080 --name springboot-app --link my-postgres:postgres springboot-app
                '''
            }
        }
    }
}