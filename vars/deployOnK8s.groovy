def call(String imageName, String imageTag) {
    echo "جاري النشر باستخدام الإعدادات المحلية للنظام... ☸️"
    
    sh """
        # 1. تحديث الصورة في ملف الـ YAML
        sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
        
        # 2. النشر المباشر باستخدام kubectl المثبت على النظام
        # سنحاول التنفيذ مباشرة دون استخدام kubeconfig مرفوع
        kubectl apply -f k8s/deployment.yaml
    """
}
