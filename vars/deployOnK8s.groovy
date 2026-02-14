def call(String imageName, String imageTag) {
    echo "جاري النشر باستخدام أمر minikube المدمج... ☸️"
    
    sh """
        # 1. تحديث الصورة في ملف الـ YAML
        sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
        
        # 2. النشر باستخدام minikube kubectl
        # ده بيغنينا عن البحث عن مسار kubectl أو ملف الـ config
        minikube kubectl -- apply -f k8s/deployment.yaml
    """
}
