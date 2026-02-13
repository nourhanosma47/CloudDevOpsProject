def call(String imageName, String deploymentFile) {
    echo "جاري النشر على Kubernetes باستخدام الملف: ${deploymentFile} ⛵"
    
    // تحديث الصورة داخل الملف
    sh "sed -i 's|image:.*|image: ${imageName}|g' ${deploymentFile}"
    
    // تطبيق التغييرات
    sh "kubectl apply -f ${deploymentFile}"
}
