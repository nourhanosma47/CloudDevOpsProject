def call(String imageName, String imageTag) {
    echo "جاري فحص البيئة وتجهيز kubectl... ☸️"
    
    sh """
        # التأكد من المعمارية أولاً
        uname -m
        
        # محاولة التحميل مع إظهار تفاصيل الخطأ إن وجدت
        curl -LO "https://dl.k8s.io/release/\$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" || { echo 'فشل التحميل'; exit 1; }
        
        chmod +x ./kubectl
        
        # التأكد من وجود ملف الإعدادات
        ls -la \$HOME/.kube/config || echo 'تحذير: ملف config غير موجود في مسار جينكينز'
        
        # تحديث الصورة
        sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
        
        # محاولة التنفيذ مع عرض نسخة kubectl للتأكد
        ./kubectl version --client
        ./kubectl apply -f k8s/deployment.yaml --kubeconfig=\$HOME/.kube/config
    """
}
