pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Vedantkulkarni9/SeleliumJavaCucumber.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                bat 'mvn test -Dbrowser=chromeheadless || exit 0'
            }
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
            echo "Pipeline completed"

            // Keep junit here (single place)
            junit '**/target/surefire-reports/*.xml'
        }

        success {
            emailext (
                subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build Status: SUCCESS

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

URL: ${env.BUILD_URL}
""",
                attachmentsPattern: '**/ExtentReport.html',
                to: "vedantkulkarni9@gmail.com"
            )
        }

        failure {
            emailext (
                subject: "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build Status: FAILURE

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

Check logs: ${env.BUILD_URL}
""",
                to: "vedantkulkarni9@gmail.com"
            )
        }
 unstable {   // ✅ ADD THIS
        emailext (
            subject: "⚠️ UNSTABLE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
Tests are failing but pipeline continued.

Check report:
${env.BUILD_URL}
""",
            attachmentsPattern: '**/reports/ExtentReport.html',
            to: "vedantkulkarni9@gmail.com"
        )
    }        
    }
}
