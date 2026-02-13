def call(String imageName) {
    echo "جاري بناء صورة Docker باسم: ${imageName} 🐳"
    sh "docker build -t ${imageName} ."
}
