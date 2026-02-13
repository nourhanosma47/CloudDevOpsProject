def call(String imageName, String credentialsId) {
    echo "جاري رفع الصورة إلى Docker Hub... 🚀"
    // استخدام credentialsId لتسجيل الدخول بشكل آمن
    withCredentials([usernamePassword(credentialsId: "${credentialsId}", passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
        sh "docker push ${imageName}"
    }
}
