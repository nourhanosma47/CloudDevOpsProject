def call(String imageName, String imageTag) {
    echo "جاري النشر على Kubernetes باستخدام ملف الإعدادات السري... ☸️"
    
    // استدعاء ملف الـ kubeconfig الذي قمتِ برفعه بـ ID: 'kubeconfig-file'
    withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG_PATH')]) {
        sh """
            # 1. تحميل أداة kubectl (لضمان وجودها وصلاحيتها)
            curl -LO "https://dl.k8s.io/release/\$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
            chmod +x ./kubectl
            
            # 2. تحديث التاج (Tag) في ملف الـ YAML
            sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
            
            # 3. تنفيذ الأمر باستخدام ملف الإعدادات الممرر من جينكينز
            ./kubectl apply -f k8s/deployment.yaml --kubeconfig=\$KUBECONFIG_PATH
        """
    }
}
