def call(String imageName, String imageTag) {
    echo "جاري بناء الصورة من داخل مجلد jenkins... 🐳"
    
    // قمنا بتغيير النقطة في النهاية إلى jenkins
    // هكذا سيبحث Docker عن requirements.txt داخل مجلد jenkins مباشرة
    sh "docker build -t ${imageName}:${imageTag} -f jenkins/Dockerfile jenkins"
}
