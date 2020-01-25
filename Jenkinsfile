pipeline {

    agent any

    stages {
        stage('Get Directory Stage') {
            steps {
                bat 'cd C:/Users/omosneaga/Desktop/spring-boot-cucumber-master'
            }
        }

        stage('Clean Stage') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Test Stage') {
            steps {
                bat 'mvn test -Dspring.profiles.active=wgu -Denv.name=qa'
            }
        }

        stage('Cucumber reports') {
            steps {
                cucumber buildStatus: "UNSTABLE",
                fileIncludePattern: "**/cucumber.json",
                jsonReportDirectory: 'target'
            }
        }

    }
}