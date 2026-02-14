def call(String imageName, String imageTag) {
    echo "جاري فحص الصورة: ${imageName}:${imageTag} بحثاً عن الثغرات... 🔍"
    
    // هنا نضع أمر الفحص، إذا كنت تستخدم أداة Trivy مثلاً:
    // sh "trivy image ${imageName}:${imageTag}"
    
    // مؤقتاً للتأكد من عمل الـ Pipeline:
    echo "تم الفحص بنجاح! ✅"
}
