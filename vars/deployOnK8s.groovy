def call(String imageName, String imageTag) {
    echo "جاري النشر مباشرة إلى Minikube عبر الشبكة المفتوحة... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. تحديث الصورة في ملف الـ Deployment
            sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
            
            # 2. النشر المباشر
            ./kubectl apply -f k8s/deployment.yaml \
                --kubeconfig=\$KUBECONFIG_PATH \
                --insecure-skip-tls-verify
        """
    }
}
