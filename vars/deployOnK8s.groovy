def call(String imageName, String imageTag) {
    echo "جاري البدء في عملية النشر على Kubernetes... ☸️"
    
    sh """
        # تحديث الصورة في ملف الـ YAML
        sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
        
        # تنفيذ الأمر باستخدام المسار الكامل
        /usr/local/bin/kubectl apply -f k8s/deployment.yaml
    """
}
