def call(String imageName, String imageTag, String credsId) {
    echo "جاري رفع الصورة ${imageName}:${imageTag} إلى Docker Hub... 🚀"
    
    // استخدام credsId الذي نمرره من الـ Jenkinsfile
    withCredentials([usernamePassword(credentialsId: credsId, passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
        sh "docker push ${imageName}:${imageTag}"
    }
}
