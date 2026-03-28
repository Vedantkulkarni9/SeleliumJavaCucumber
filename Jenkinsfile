pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/YOUR-REPO.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            emailext (
                subject: "Extent Report - ${env.JOB_NAME}",
                body: """
Execution completed.

Build: ${env.JOB_NAME} #${env.BUILD_NUMBER}
URL: ${env.BUILD_URL}
""",
                attachmentsPattern: '**/ExtentReport.html',
                to: "yourmail@gmail.com"
            )
        }
    }
}
