pipeline {
    agent any

    parameters {
        choice(name: 'ENV', choices: ['dev', 'uat', 'prod'], description: 'Select target environment')
        string(name: 'VERSION', defaultValue: 'latest', description: 'Docker image version/tag')
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhubdesk-creds')
        IMAGE_NAME = "rakeshk459/restservice"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3.9.9-openjdk-17' // Use suitable Maven + JDK version
                    args '-v $HOME/.m2:/root/.m2'  // Optional: cache Maven dependencies
                }
            }
            steps {
                sh 'mvn clean package -Pdev'
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

        stage('Deploy') {
            steps {
                script {
                    if (params.ENV == 'prod') {
                        input message: 'Approve deployment to PRODUCTION?', ok: 'Deploy'
                    }
                    echo "Deploying to ${params.ENV} environment..."
                    // Add your deployment scripts here (kubectl, helm, ssh, etc)
                }
            }
        }
    }
}