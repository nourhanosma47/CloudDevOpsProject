def call(String imageName, String imageTag) {
    echo "جاري محاولة النشر النهائية وتجاوز قيود الشبكة... ☸️"
    
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. تعريف استثناءات البروكسي لضمان محاولة الاتصال المباشر
            export no_proxy=192.168.49.2,localhost,127.0.0.1
            export NO_PROXY=192.168.49.2,localhost,127.0.0.1

            # 2. تحميل أداة kubectl إذا لم تكن موجودة في المجلد الحالي
            if [ ! -f ./kubectl ]; then
                curl -LO "https://dl.k8s.io/release/\$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
                chmod +x ./kubectl
            fi
            
            # 3. تحديث الصورة في ملف الـ YAML باستخدام sed
            sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
            
            # 4. عرض خريطة الشبكة (للتشخيص في حال الفشل)
            echo "--- Network Route Info ---"
            ip route
            
            # 5. تنفيذ الأمر مع تعطيل التحقق (Validation) وتجاوز الشهادات (TLS)
            ./kubectl apply -f k8s/deployment.yaml \
                --kubeconfig=\$KUBECONFIG_PATH \
                --insecure-skip-tls-verify \
                --validate=false
        """
    }
}
