node {

    // holds reference to docker image
    def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "0.0.0.0:8000"
    def dockerImageName = "restaurant-modules"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:${env.BUILD_NUMBER}"
    
    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/tomsonis/restaurant-modules.git'
      // Get the Maven tool.
      // ** NOTE: This 'maven-3.6.1' Maven tool must be configured
      // **       in the global configuration.
    }    
  
    stage('Build Project') {
      // build project via maven
      sh "mvn -Dmaven.test.failure.ignore clean package"
    }
}
