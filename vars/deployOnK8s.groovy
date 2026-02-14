def call(String imageName, String imageTag) {
    echo "جاري محاولة النشر مع تجاوز قيود الشبكة... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. إخبار النظام بتجاهل البروكسي لهذا العنوان
            export no_proxy=192.168.49.2,localhost,127.0.0.1
            export NO_PROXY=192.168.49.2,localhost,127.0.0.1

            # 2. تحميل kubectl (إذا لم يكن موجوداً)
            if [ ! -f ./kubectl ]; then
                curl -LO "https://dl.k8s.io/release/\$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
                chmod +x ./kubectl
            fi
            
            # 3. تحديث ملف الـ YAML
            sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
            
            # 4. محاولة النشر مع إضافة flag لتجاوز فحص الشهادات مؤقتاً للتجربة
            ./kubectl apply -f k8s/deployment.yaml --kubeconfig=\$KUBECONFIG_PATH --insecure-skip-tls-verify
        """
    }
}
