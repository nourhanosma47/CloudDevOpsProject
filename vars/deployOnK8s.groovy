def call(String imageName, String imageTag) {
    echo "جاري تجهيز kubectl والنشر على Kubernetes... ☸️"
    
    sh """
        # 1. تحميل نسخة kubectl متوافقة داخل المجلد الحالي
        curl -LO "https://dl.k8s.io/release/\$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
        chmod +x ./kubectl
        
        # 2. تحديث الصورة في ملف الـ YAML
        sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
        
        # 3. استخدام النسخة المحلية للتنفيذ
        ./kubectl apply -f k8s/deployment.yaml --kubeconfig=\$HOME/.kube/config
    """
}
