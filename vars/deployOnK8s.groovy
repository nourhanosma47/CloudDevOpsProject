def call(String imageName, String imageTag) {
    echo "محاولة حل مشكلة التوجيه (Routing) والنشر... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. محاولة إضافة مسار لشبكة Minikube (قد يتطلب صلاحيات، لكن لنجرب)
            # إذا كان Jenkins يملك صلاحية sudo بدون كلمة سر
            sudo ip route add 192.168.49.0/24 dev docker0 || echo "تعذر إضافة المسار تلقائياً"

            # 2. تحديث ملف الـ YAML
            sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
            
            # 3. استخدام kubectl مع زيادة مدة الانتظار وتجاهل التحقق
            ./kubectl apply -f k8s/deployment.yaml \
                --kubeconfig=\$KUBECONFIG_PATH \
                --insecure-skip-tls-verify \
                --validate=false \
                --request-timeout='2m'
        """
    }
}
