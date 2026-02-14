def call(String imageName, String imageTag) {
    echo "جاري النشر عبر المنفذ الموجه 32771... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. تحديث الصورة في ملف الـ Deployment
            sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
            
            # 2. النشر باستخدام العنوان المحلي والمنفذ الصحيح
            ./kubectl apply -f k8s/deployment.yaml \
                --kubeconfig=\$KUBECONFIG_PATH \
                --server=https://127.0.0.1:32771 \
                --insecure-skip-tls-verify \
                --validate=false
        """
    }
}
