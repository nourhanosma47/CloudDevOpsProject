def call(String imageName, String imageTag) {
    echo "جاري البدء في عملية النشر (Deployment) على Kubernetes... ☸️"
    
    // سنقوم بتحديث ملف الـ deployment بالصورة الجديدة
    // نفترض أن لديك ملف اسمه deployment.yaml داخل مجلد k8s
    sh "ls /.dockerenv || echo 'Not in Docker'"
    sh """
        sed -i 's|image: .*|image: ${imageName}:${imageTag}|g' k8s/deployment.yaml
        kubectl apply -f k8s/deployment.yaml
    """
    
    echo "تم النشر بنجاح على Kubernetes! 🎉"
}
