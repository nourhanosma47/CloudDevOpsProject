def call(String imageName, String imageTag) {
    echo "جاري بناء صورة Docker من المجلد الفرعي jenkins... 🏗️"
    
    // استخدام -f لتحديد مكان الـ Dockerfile
    sh "docker build -t ${imageName}:${imageTag} -f jenkins/Dockerfile ."
}
