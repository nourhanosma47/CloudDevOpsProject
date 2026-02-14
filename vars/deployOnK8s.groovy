def call(String imageName, String imageTag) {
    echo "جاري استكشاف بيئة Jenkins لحل مشكلة 'Command not found'... 🕵️‍♀️"
    
    sh """
        # 1. تحديث الصورة كالمعتاد
        sed -i "s|image: .*|image: ${imageName}:${imageTag}|g" k8s/deployment.yaml
        
        # 2. طباعة المسارات (PATH) لنفهم أين يبحث Jenkins
        echo "المسارات الحالية: \$PATH"
        
        # 3. محاولة البحث عن minikube في أماكنه الشائعة
        if [ -f "/usr/local/bin/minikube" ]; then
            echo "تم العثور على minikube في /usr/local/bin"
            /usr/local/bin/minikube kubectl -- apply -f k8s/deployment.yaml
        elif [ -f "/usr/bin/minikube" ]; then
            echo "تم العثور على minikube في /usr/bin"
            /usr/bin/minikube kubectl -- apply -f k8s/deployment.yaml
        else
            echo "للأسف لم أجد minikube في المسارات المعتادة."
            # سنحاول عرض المستخدم الحالي للتأكد من الصلاحيات
            whoami
        fi
    """
}
