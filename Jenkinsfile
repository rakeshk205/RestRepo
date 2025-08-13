pipeline {
    agent any

    parameters {
        choice(name: 'ENV', choices: ['dev', 'uat', 'prod'], description: 'Select target environment')
        string(name: 'VERSION', defaultValue: 'latest', description: 'Docker image version/tag')
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhubdesk-creds')
        IMAGE_NAME = "rakeshk459/restservice"
        DOCKER_HOST = "tcp://localhost:2375"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                script {
                    if (isUnix()) {
                        docker.image('maven:3.9.9-eclipse-temurin-17').inside('-v $HOME/.m2:/root/.m2') {
                            sh 'mvn clean package -Pdev -DskipTests || { echo "Maven build failed"; exit 1; }'
                        }
                    } else {
                        bat 'mvn clean package -Pdev -DskipTests'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:${params.VERSION}-${params.ENV}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhubdesk-creds') {
                        docker.image("${IMAGE_NAME}:${params.VERSION}-${params.ENV}").push()
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    withEnv(["VERSION=${params.VERSION}", "ENV=${params.ENV}"]) {
                        if (isUnix()) {
                            sh '''
                            docker compose down || true
                            docker compose up -d --build
                            '''
                        } else {
                            bat '''
                            docker compose down || exit 0
                            docker compose up -d --build
                            '''
                        }
                    }
                }
            }
        }
    }
}