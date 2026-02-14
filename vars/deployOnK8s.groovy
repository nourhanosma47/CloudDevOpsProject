def call(String imageName, String imageTag) {
    echo "خلاص.. هننشر بالطريقة المباشرة والأكيدة 🚀"
    
    sh """
        # 1. تحديث الصورة
        sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
        
        # 2. النشر باستخدام الـ Config بتاع السيستم نفسه
        # هنكتب المسار الكامل للـ config عشان نضمن إنه شافه
        KUBECONFIG=/home/nourhan/.kube/config kubectl apply -f k8s/deployment.yaml --insecure-skip-tls-verify
    """
}
