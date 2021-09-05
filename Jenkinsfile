pipeline{
    agent any
    stages{
        stage('Buidl Backend'){
            steps{
                bat 'mvn clean package --DskipTests=true'
            }
        }
    }
}