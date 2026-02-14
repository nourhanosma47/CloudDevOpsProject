def call(String imageName, String imageTag) {
    echo "جاري حذف الصورة المحلية: ${imageName}:${imageTag} لتوفير المساحة... 🗑️"
    
    // أمر حذف الصورة من على سيرفر جينكينز بعد الرفع
    sh "docker rmi ${imageName}:${imageTag}"
}
