def call(String imageName, String imageTag) {
    echo "جاري بناء صورة Docker باسم: ${imageName}:${imageTag} 🐳"
    sh "ls -lah" // هذا الأمر سيطبع لنا قائمة بجميع الملفات التي يراها جينكينز الآن
    sh "docker build -t ${imageName}:${imageTag} ."
}
