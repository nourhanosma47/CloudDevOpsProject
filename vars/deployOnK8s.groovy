def call(String imageName, String imageTag) {
    echo "جاري النشر المباشر... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. تحديث الصورة
            sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
            
            # 2. النشر مع تعطيل التحقق لضمان تجاوز مشكلة المسار
            ./kubectl apply -f k8s/deployment.yaml \
                --kubeconfig=\$KUBECONFIG_PATH \
                --server=https://192.168.49.2:8443 \
                --insecure-skip-tls-verify \
                --validate=false
        """
    }
}
