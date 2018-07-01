def CONTAINER_NAME="capstoneproject"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="pariveshdocker"
def HTTP_PORT="8080"

def userInput = true
def didTimeout = false

node {
    currentBuild.result = "SUCCESS"
    try{
	    stage('Initialize'){
	        MAVEN_HOME = tool('myMaven')
	    }
	
	    stage('Checkout') {
	        checkout scm
	    }
	    
		stage('Build and Test'){
	        bat "${MAVEN_HOME}/bin/mvn clean verify"
	    }
	    
		stage('Sonar SCA'){
	        try {
	            bat "${MAVEN_HOME}/bin/mvn sonar:sonar"
	        } catch(error){
	            echo "The sonar server could not be reached ${error}"
	        }
	     }
	     
	    stage('Publish to JFrog Artifactory'){
	        bat "${MAVEN_HOME}/bin/mvn clean -Dmaven.test.skip=true -Dmaven.tomcat.skip=true -DskipTests=false deploy"
	    }
	    
	    stage('Taking Admin Approval'){
	        approval()
	        
	   //Taking action on approval     
	       if (didTimeout) {
                echo "no input was received before timeout"
                
                mail bcc: '', body: 'no input was received before timeout.', cc: '', from: '', replyTo: '', subject: 'Pipeline build failed', to: 'pariveshkurmi.mit@gmail.com'
           } else if (userInput == true) {
                echo "this was successful"
           } else {
                echo "this was not successful"
                currentBuild.result = 'FAILURE'
                mail bcc: '', body: 'Admin has not given Approval for furthur build process.', cc: '', from: '', replyTo: '', subject: 'Pipeline build failed', to: 'pariveshkurmi.mit@gmail.com'
           } 
	    }
	    
	    stage("Docker Release and Deployment"){
	    	withDockerServer([uri: 'tcp://192.168.99.100:2376']) {
	    	echo "connected"
    			imagePrune(CONTAINER_NAME)
    			imageBuild(CONTAINER_NAME, CONTAINER_TAG)
    			withCredentials([usernamePassword(credentialsId: 'dockerHubAccount', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                	pushToImage(CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)
            	}
            	removeExistingContaier(CONTAINER_NAME)
	        	runApp(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER, HTTP_PORT)
		 	}
	    
	    }
	    
	    stage('Build Result'){
	    	mail bcc: '', body: 'Test Success', cc: '', from: '', replyTo: '', subject: 'The Pipeline Success :-)', to: 'pariveshkurmi.mit@gmail.com'
	    }
    
    }
    catch(caughtError){
      println "caught error :" + caughtError
      err = caughtError
      currentBuild.result = "FAILURE"
      mail bcc: '', body: 'Pipeline error: ${err}\nFix me.', cc: '', from: '', replyTo: '', subject: 'Pipeline build failed', to: 'pariveshkurmi.mit@gmail.com'
    }
    
}


def approval(){
    
    try {
            timeout(time: 15, unit: 'SECONDS') { // change to a convenient timeout for you
            userInput = input(
            id: 'Proceed1', message: 'Was this successful?', parameters: [
            [$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm you agree with this']
            ])
        }
    } catch(err) { // timeout reached or input false
            def user = err.getCauses()[0].getUser()
            if('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            didTimeout = true
            } 
            else {
                   userInput = false
                   echo "Aborted by: [${user}]"
            }
        }
}

def imagePrune(containerName){
    try {
        bat "docker image prune -f"
        bat "docker stop $containerName"
    } catch(error){}
}

def removeExistingContaier(containerName){
	try {
    	bat "docker rm -f $containerName"
    	echo "Remove Container complete"
   } catch(error){}
}

def imageBuild(containerName, tag){
    bat "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerUser, dockerPassword){
    bat "docker login -u $dockerUser -p $dockerPassword"
    bat "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    bat "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort){
    bat "docker pull $dockerHubUser/$containerName"
    bat "docker run -d --rm -p $httpPort:$httpPort --name $containerName $containerName:$tag"
    echo "Application started on port: ${httpPort} (http)"
}