pipeline{
    agent any
    stages{
        stage('Buidl Backend'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests'){
            steps{
                bat 'mvn test'
            }
        }
        stage('sonar Analise'){
            environment{
                    scannerHome = tool 'Sonar_Scanner'
                }
            steps{
                withSonarQubeEnv('Sonar_Local'){
                     bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=Deploy-bakc -Dsonar.host.url=http://localhost:9000 -Dsonar.login=0f4977a5d4d17a0fc25e19a717dabb53cef75c7a -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**, **/model/**,**Application.java"
                }
            }
        }
    }
}




