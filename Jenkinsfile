def CONTAINER_NAME="capstoneproject"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="pariveshdocker"
def HTTP_PORT="8080"

node {
    currentBuild.result = "SUCCESS"
    try{
	    stage('Initialize'){
	        def mavenHome  = tool 'myMaven'
	        env.PATH = "${mavenHome}/bin"
	    }
	
	    stage('Checkout') {
	        checkout scm
	    }
	
	    stage('Build and Test'){
	        bat "mvn clean verify"
	    }
	    stage("DockerBuild"){
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

def imagePrune(containerName){
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

def removeExistingContaier(containerName){
	try {
    	sh "docker rm -f $containerName"
    	echo "Remove Container complete"
   } catch(error){}
}

def imageBuild(containerName, tag){
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerUser, dockerPassword){
    sh "docker login -u $dockerUser -p $dockerPassword"
    sh "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    sh "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort){
    sh "docker pull $dockerHubUser/$containerName"
    sh "docker run -d --rm -p $httpPort:$httpPort --name $containerName $containerName:$tag"
    echo "Application started on port: ${httpPort} (http)"
}