def call(String imageName, String imageTag) {
    echo "جاري بناء صورة Docker باسم: ${imageName}:${imageTag} 🐳"
    sh "docker build -t ${imageName}:${imageTag} ."
}
