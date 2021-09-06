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
                     bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=Deploy-bakc -Dsonar.host.url=http://localhost:9000 -Dsonar.login=0f4977a5d4d17a0fc25e19a717dabb53cef75c7a -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage('Quality guate'){
            steps{
                sleep(20)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true                
                }
            }
        }
        stage('Deploy Ambiente Teste'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'toncatlogin', path: '', url: 'http://localhost:8102/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        /*stage('API Test'){
            steps{
                dir('api-test') {
                git credentialsId: 'githublogin', url: 'URL'
                bat 'mvn test'
                }
            }
        }*/
        stage('Build front-end'){
            steps{
                dir('front-end'){
                    git credentialsId: 'githublogin', url: 'https://github.com/murilloalbernaz/tasks-frontend'
                    bat 'mvn clean package'
                }
            }
        }
        stage('Deploy Front-end'){
            steps{
              deploy adapters: [tomcat8(credentialsId: 'toncatlogin', path: '', url: 'http://localhost:8102/')], contextPath: 'tasks', war: 'front-end/target/tasks.war'
            }
        }
        /*stage('Selenium Test'){
            steps{
                dir('api-test') {
                git credentialsId: 'githublogin', url: 'URL'
                bat 'mvn test'
                }
            }
        }*/
        stage('Deploy Produção'){
            steps{
                bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
    }
}




