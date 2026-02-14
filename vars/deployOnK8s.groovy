def call(String imageName, String imageTag) {
    echo "جاري النشر باستخدام المسارات المطلقة لضمان النجاح... 🚀"
    
    sh """
        # 1. تحديث الصورة (استخدام المسار الكامل للملف)
        sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" ${WORKSPACE}/k8s/deployment.yaml
        
        # 2. تنفيذ الأمر باستخدام المسار الكامل لـ kubectl والـ config
        KUBECONFIG=/home/nourhan/.kube/config /usr/local/bin/kubectl apply -f ${WORKSPACE}/k8s/deployment.yaml --insecure-skip-tls-verify
    """
}
