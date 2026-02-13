def call(String imageName) {
    echo "جاري تنظيف الجهاز من الصورة: ${imageName} 🧹"
    sh "docker rmi ${imageName}"
}
