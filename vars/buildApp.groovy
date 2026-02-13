def call() {
    echo 'جاري بناء التطبيق... 🏗️'
    sh 'mvn clean package -DskipTests' }
