def call(String imageName) {
    echo "جاري فحص الصورة أمنياً: ${imageName} 🔍"
    sh "trivy image ${imageName} || true"
}
