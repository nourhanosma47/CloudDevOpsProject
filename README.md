# 🚀 Cloud DevOps Graduation Project

## 🎯 Project Overview

This is a complete end-to-end DevOps project demonstrating:
- **Containerization** of a Flask web application
- **Infrastructure as Code** using Terraform on AWS
- **Configuration Management** using Ansible
- **Container Orchestration** with Kubernetes
- **CI/CD Pipeline** with Jenkins and Shared Libraries
- **GitOps Deployment** using ArgoCD

**Application:** Simple Flask web app  
**Cloud Provider:** AWS  
**Container Registry:** Docker Hub  
**Source Control:** GitHub

---

## 🏗️ Architecture Diagram🏗️ Architecture Diagram
```
┌─────────────────────────────────────────────────────────────────┐
│                         GITHUB REPOSITORY                        │
│  (Source Code, Dockerfile, K8s Manifests, Terraform, Ansible)   │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ├──────────────────┐
                         │                  │
                         ▼                  ▼
              ┌──────────────────┐  ┌──────────────────┐
              │   JENKINS CI/CD   │  │  ARGOCD GITOPS   │
              │  (Build, Scan,    │  │  (Auto Sync &    │
              │   Push Image)     │  │   Deploy to K8s) │
              └─────────┬─────────┘  └────────┬─────────┘
                        │                     │
                        ▼                     ▼
              ┌──────────────────┐  ┌──────────────────┐
              │   DOCKER HUB      │  │  KUBERNETES      │
              │  (Image Registry) │  │  (Minikube)      │
              └───────────────────┘  └──────────────────┘
                                              │
                                              ▼
                                    ┌──────────────────┐
                                    │  FLASK APP       │
                                    │  (Running Pods)  │
                                    └──────────────────┘
```

**AWS Infrastructure (Terraform):**
```
┌────────────────────────────────────────────────────────┐
│                      AWS VPC (10.0.0.0/16)             │
│                                                         │
│  ┌──────────────────┐        ┌──────────────────┐    │
│  │  Public Subnet 1  │        │  Public Subnet 2  │    │
│  │  (us-east-1a)     │        │  (us-east-1b)     │    │
│  │  10.0.1.0/24      │        │  10.0.2.0/24      │    │
│  │                   │        │                   │    │
│  │  ┌─────────────┐ │        │                   │    │
│  │  │ EC2 Instance│ │        │                   │    │
│  │  │  (Jenkins)  │ │        │                   │    │
│  │  └─────────────┘ │        │                   │    │
│  └──────────────────┘        └──────────────────┘    │
│           │                           │                │
│           └────────┬──────────────────┘                │
│                    │                                   │
│          ┌─────────▼─────────┐                        │
│          │  Internet Gateway  │                        │
│          └────────────────────┘                        │
└────────────────────────────────────────────────────────┘
```

---


## 📂 Project Structure
```
CloudDevOpsProject/
│
├── FinalProject/                    # Flask Application
│   ├── app.py                       # Main application file
│   ├── requirements.txt             # Python dependencies
│   ├── Dockerfile                   # Docker image definition
│   ├── static/                      # Static files (CSS, JS, images)
│   └── templates/                   # HTML templates
│
├── k8s/                             # Kubernetes Manifests
│   ├── deployment.yaml              # Deployment configuration (2 replicas)
│   └── service.yaml                 # Service configuration (NodePort)
│
├── terraform/                       # Infrastructure as Code
│   ├── main.tf                      # Main Terraform configuration
│   ├── provider.tf                  # AWS provider settings
│   ├── variables.tf                 # Input variables
│   ├── outputs.tf                   # Output values
│   ├── backend.tf                   # S3 backend configuration
│   │
│   └── modules/
│       ├── network/                 # Network Module
│       │   ├── main.tf              # VPC, Subnets, IGW, Route Tables, NACL
│       │   ├── variables.tf
│       │   └── outputs.tf
│       │
│       └── server/                  # Server Module
│           ├── main.tf              # EC2, Security Groups, CloudWatch
│           ├── variables.tf
│           └── outputs.tf
│
├── ansible/                         # Configuration Management
│   ├── playbook.yml                 # Main playbook
│   ├── inventory.ini                # Static inventory
│   ├── aws_ec2.yml                  # Dynamic inventory
│   ├── ansible.cfg                  # Ansible configuration
│   ├── requirements.yml             # Required collections
│   │
│   └── roles/
│       ├── common/                  # Common packages role
│       │   └── tasks/
│       │       └── main.yml         # Install Git, Java, etc.
│       │
│       ├── docker/                  # Docker role
│       │   └── tasks/
│       │       └── main.yml         # Install & configure Docker
│       │
│       └── jenkins/                 # Jenkins role
│           └── tasks/
│               └── main.yml         # Install & configure Jenkins
│
├── jenkins/
│   └── Jenkinsfile                  # CI/CD Pipeline definition
│
├── jenkins-shared-library/          # Reusable Pipeline Functions
│   └── vars/
│       ├── buildImage.groovy        # Build Docker image
│       ├── scanImage.groovy         # Scan with Trivy
│       ├── pushImage.groovy         # Push to Docker Hub
│       ├── removeImage.groovy       # Remove local image
│       └── deployOnK8s.groovy       # Update K8s manifests
│
├── argocd/
│   └── application.yaml             # ArgoCD Application manifest
│
├── .gitignore                       # Git ignore rules
└── README.md                        # This file
```

---

## 📖 Detailed Setup Guide

### Prerequisites

Before starting, ensure you have:

- ✅ **Local Machine:** Ubuntu/Linux
- ✅ **AWS Account** (with credentials)
- ✅ **Docker Hub Account**
- ✅ **GitHub Account**
- ✅ **Tools Installed:**
  - Git
  - Docker
  - Kubectl
  - Minikube
  - Terraform
  - Ansible
  - AWS CLI (optional)

---

## 1. GitHub Repository Setup

### Step 1.1: Create Repository
```bash
# On GitHub.com
# Click: New Repository
# Name: CloudDevOpsProject
# ✅ Initialize with README
# Click: Create Repository
```

### Step 1.2: Clone Repository
```bash
# Clone to local machine
git clone https://github.com/YOUR_USERNAME/CloudDevOpsProject.git
cd CloudDevOpsProject
```

### Step 1.3: Add Source Code
```bash
# Clone the Flask application
git clone https://github.com/IbrahimAdel15/FinalProject.git

# Remove nested .git to avoid submodule issues
rm -rf FinalProject/.git
```

**✅ Deliverable:** GitHub repository URL

---

## 2. Containerization with Docker

### Step 2.1: Create Dockerfile
```bash
cd FinalProject
nano Dockerfile
```


### Step 2.2: Build Docker Image
```bash
# Build the image
docker build -t flask-app:v1.0 .

# Verify the image
docker images | grep flask-app
```

### Step 2.3: Test Locally
```bash
# Run container
docker run -d -p 5555:5000 --name test-flask flask-app:v1.0

# Test application
curl http://localhost:5555

# Check logs
docker logs test-flask

# Stop and remove
docker stop test-flask
docker rm test-flask
```

### Step 2.4: Push to Docker Hub
```bash
# Login to Docker Hub
docker login

# Tag the image
docker tag flask-app:v1.0 YOUR_DOCKERHUB_USERNAME/flask-app:v1.0

# Push to Docker Hub
docker push YOUR_DOCKERHUB_USERNAME/flask-app:v1.0
```

### Step 2.5: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add FinalProject/Dockerfile
git commit -m "Add Dockerfile for Flask application"
git push origin main
```

**✅ Deliverable:** Dockerfile committed to repository

---

## 3. Kubernetes Deployment

### Step 3.1: Start Minikube
```bash
# Start Minikube cluster
minikube start

# Verify cluster
minikube status
kubectl cluster-info
```

### Step 3.2: Create Namespace
```bash
# Create ivolve namespace
kubectl create namespace ivolve

# Verify
kubectl get namespaces
```

### Step 3.3: Load Docker Image into Minikube
```bash
# Load image to Minikube nodes
minikube image load flask-app:v1.0

# Verify
minikube ssh docker images | grep flask-app
```

### Step 3.4: Create Kubernetes Manifests
```bash
cd ~/CloudDevOpsProject
mkdir k8s
cd k8s
```

#### deployment.yaml
```bash
nano deployment.yaml
```

#### service.yaml
```bash
nano service.yaml
```


### Step 3.7: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add k8s/
git commit -m "Add Kubernetes deployment and service manifests"
git push origin main
```

**✅ Deliverable:** Kubernetes YAML files committed to repository

---

## 4. Infrastructure Provisioning (Terraform)

### Step 4.1: Install Terraform
```bash
# Verify installation
terraform --version

# If not installed:
sudo apt update
sudo apt install -y gnupg software-properties-common
wget -O- https://apt.releases.hashicorp.com/gpg | gpg --dearmor | sudo tee /usr/share/keyrings/hashicorp-archive-keyring.gpg
echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
sudo apt update
sudo apt install terraform
```

### Step 4.2: Create Terraform Structure
```bash
cd ~/CloudDevOpsProject
mkdir -p terraform/modules/{network,server}
cd terraform
```

### Step 4.3: Create Root Configuration Files

#### provider.tf
```bash
nano provider.tf
```
#### variables.tf
```bash
nano variables.tf
```
#### main.tf
```bash
nano main.tf
```

#### outputs.tf
```bash
nano outputs.tf
```
#### terraform.tfvars
```bash
nano terraform.tfvars
```

### Step 4.4: Create Network Module
```bash
cd modules/network
```

#### network/main.tf
```bash
nano main.tf
```

#### network/outputs.tf
```bash
nano outputs.tf
```

### Step 4.5: Create Server Module
```bash
cd ../server
```

#### server/main.tf
```bash
nano main.tf
```

#### server/variables.tf
```bash
nano variables.tf
```

#### server/outputs.tf
```bash
nano outputs.tf
```


### Step 4.6: Initialize and Apply Terraform
```bash
cd ~/CloudDevOpsProject/terraform

# Initialize Terraform
terraform init

# Validate configuration
terraform validate

# Plan infrastructure
terraform plan

# Apply (create resources)
terraform apply

# Type 'yes' when prompted
```

**Expected Output:**
```
Apply complete! Resources: 11 added, 0 changed, 0 destroyed.

Outputs:

jenkins_instance_id = "i-xxxxxxxxxx"
jenkins_public_ip = "x.x.x.x"
vpc_id = "vpc-xxxxxxxxxx"
```

### Step 4.7: Verify Infrastructure

**In AWS Console:**
- ✅ VPC created
- ✅ 2 Subnets created
- ✅ Internet Gateway attached
- ✅ EC2 instance running
- ✅ Security Group configured
- ✅ CloudWatch alarm created

### Step 4.8: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add terraform/
git add .gitignore
git commit -m "Add Terraform infrastructure with network and server modules"
git push origin main
```

### Step 4.9: Cleanup (Save Credits)
```bash
# When done testing
terraform destroy

# Type 'yes' when prompted
```

**✅ Deliverable:** Terraform scripts committed to repository

---

## 5. Configuration Management (Ansible)

### Step 5.1: Install Ansible
```bash
# Verify installation
ansible --version

# If not installed:
sudo apt update
sudo apt install -y ansible
```

### Step 5.2: Create Ansible Structure
```bash
cd ~/CloudDevOpsProject
mkdir -p ansible/roles
cd ansible
```

### Step 5.3: Create Ansible Configuration

#### ansible.cfg
```bash
nano ansible.cfg
```

#### inventory.ini
```bash
nano inventory.ini
```
**Replace `<JENKINS_PUBLIC_IP>` with actual IP from Terraform output**

### Step 5.4: Create Ansible Roles
```bash
cd roles

# Create role structures
ansible-galaxy init common
ansible-galaxy init docker
ansible-galaxy init jenkins
```

#### common role
```bash
cd common/tasks
nano main.yml
```

#### docker role
```bash
cd ../../docker/tasks
nano main.yml
```

#### jenkins role
```bash
cd ../../jenkins/tasks
nano main.yml
```

### Step 5.5: Create Main Playbook
```bash
cd ~/CloudDevOpsProject/ansible
nano playbook.yml
```
---

### Step 5.6: Manual Installation (Alternative)

**If Ansible has issues, install Jenkins manually:**
```bash
# SSH to EC2
ssh -i ~/.ssh/vockey.pem ubuntu@

# Update system
sudo apt update

# Install Java
sudo apt install -y openjdk-17-jdk

# Download and run Jenkins WAR
cd ~
wget https://get.jenkins.io/war-stable/latest/jenkins.war
nohup java -jar jenkins.war --httpPort=8080 > jenkins.log 2>&1 &

# Wait 30 seconds
sleep 30

# Get initial password
cat ~/.jenkins/secrets/initialAdminPassword

# Exit SSH
exit
```

### Step 5.7: Run Ansible Playbook
```bash
cd ~/CloudDevOpsProject/ansible

# Test connection
ansible jenkins -m ping -i inventory.ini

# Run playbook
ansible-playbook -i inventory.ini playbook.yml
```

### Step 5.8: Access Jenkins
```bash
# Open browser
http://:8080

# Use the initial admin password from Ansible output or manual installation
```

### Step 5.9: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add ansible/
git commit -m "Add Ansible playbooks and roles for Jenkins configuration"
git push origin main
```

**✅ Deliverable:** Ansible playbooks and roles committed to repository

---

## 6. CI/CD Pipeline (Jenkins)

### Step 6.1: Setup Jenkins

**Access Jenkins UI:**
```
http://<JENKINS_IP>:8080
```

**Initial Setup:**
1. Enter admin password
2. Click "Install suggested plugins"
3. Create first admin user
4. Save Jenkins URL
5. Start using Jenkins

### Step 6.2: Configure Shared Library in Jenkins

**Navigate to:** Manage Jenkins → System

**Scroll to:** Global Pipeline Libraries → Add

**Configuration:**
- **Name:** `shared-library`
- **Default version:** `main`
- ✅ **Load implicitly**
- ✅ **Allow default version to be overridden**
- **Retrieval method:** Modern SCM
- **Source Code Management:** Git
- **Project Repository:** `https://github.com/YOUR_USERNAME/CloudDevOpsProject.git`
- **Library Path:** `jenkins-shared-library`

Click **Save**

### Step 6.3: Create Jenkinsfile
```bash
cd ~/CloudDevOpsProject
mkdir jenkins
cd jenkins
nano Jenkinsfile
```

### Step 6.4: Verify Shared Library Structure
```bash
cd ~/CloudDevOpsProject
ls -R jenkins-shared-library/
```

**Expected structure:**
```
jenkins-shared-library/
└── vars/
    ├── buildImage.groovy
    ├── scanImage.groovy
    ├── pushImage.groovy
    ├── removeImage.groovy
    └── deployOnK8s.groovy
```

### Step 6.5: Create Jenkins Pipeline Job

**In Jenkins UI:**

1. Click **New Item**
2. Enter name: `flask-app-pipeline`
3. Select **Pipeline**
4. Click **OK**

**Pipeline Configuration:**
- **Definition:** Pipeline script from SCM
- **SCM:** Git
- **Repository URL:** `https://github.com/YOUR_USERNAME/CloudDevOpsProject.git`
- **Branch:** `*/main`
- **Script Path:** `jenkins/Jenkinsfile`

Click **Save**

### Step 6.6: Run Pipeline

Click **Build Now**

**Pipeline Stages:**
1. ✅ Build Image
2. ✅ Scan Image
3. ✅ Push Image
4. ✅ Remove Local Image
5. ✅ Deploy on K8s

### Step 6.7: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add jenkins/
git commit -m "Add Jenkinsfile for CI/CD pipeline"
git push origin main
```

**✅ Deliverable:** Jenkinsfile and Shared Library committed to repository

---

## 7. GitOps with ArgoCD

### Step 7.1: Install ArgoCD
```bash
# Create ArgoCD namespace
kubectl create namespace argocd

# Install ArgoCD
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

# Wait for pods to be ready
kubectl get pods -n argocd -w
```

**Press Ctrl+C when all pods are Running**

### Step 7.2: Access ArgoCD UI

**Terminal 1 (keep running):**
```bash
# Port forward ArgoCD server
kubectl port-forward svc/argocd-server -n argocd 8081:443
```

**Terminal 2:**
```bash
# Get admin password
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d
echo
```

**Copy the password**

**Open browser:**
```
https://localhost:8081
```

**Login:**
- Username: `admin`
- Password: (copied from above)

**Accept security warning if prompted**

### Step 7.3: Create ArgoCD Application YAML
```bash
cd ~/CloudDevOpsProject
mkdir argocd
cd argocd
nano application.yaml
```

**Replace `YOUR_USERNAME` with your GitHub username**

### Step 7.4: Deploy Application via ArgoCD UI

**In ArgoCD UI:**

1. Click **+ NEW APP**

**GENERAL:**
- Application Name: `flask-app`
- Project: `default`
- Sync Policy: `Automatic`
  - ✅ PRUNE RESOURCES
  - ✅ SELF HEAL

**SOURCE:**
- Repository URL: `https://github.com/YOUR_USERNAME/CloudDevOpsProject.git`
- Revision: `main`
- Path: `k8s`

**DESTINATION:**
- Cluster URL: `https://kubernetes.default.svc`
- Namespace: `ivolve`

3. Click **CREATE**

### Step 7.5: Verify Deployment

**In ArgoCD UI:**
- Application should show as "Synced" and "Healthy"
- Click on application to see resource tree

**In terminal:**
```bash
# Check ArgoCD application
kubectl get application -n argocd

# Check deployed resources
kubectl get all -n ivolve
```

### Step 7.6: Apply Application via CLI (Alternative)
```bash
# Apply the application manifest
kubectl apply -f argocd/application.yaml

# Check status
kubectl get application -n argocd
```

### Step 7.7: Commit to GitHub
```bash
cd ~/CloudDevOpsProject
git add argocd/
git commit -m "Add ArgoCD application manifest for GitOps deployment"
git push origin main
```

**✅ Deliverable:** ArgoCD application YAML committed to repository

---


## 🐛 Troubleshooting

### Docker Issues

**Problem:** Permission denied
```bash
# Solution: Add user to docker group
sudo usermod -aG docker $USER
newgrp docker
```

**Problem:** Image not found in Minikube
```bash
# Solution: Load image
minikube image load flask-app:v1.0
```

### Kubernetes Issues

**Problem:** ImagePullBackOff
```bash
# Solution: Use imagePullPolicy: Never for local images
# Or load image: minikube image load flask-app:v1.0
```

**Problem:** Pods pending
```bash
# Check events
kubectl describe pod  -n ivolve

# Check resources
kubectl top nodes
```

### Terraform Issues

**Problem:** Authentication error
```bash
# Verify credentials in terraform.tfvars
# Ensure AWS access key and secret key are correct
```

**Problem:** Resource already exists
```bash
# Import existing resource or destroy and recreate
terraform destroy
terraform apply
```

### Ansible Issues

**Problem:** SSH connection refused
```bash
# Check SSH key permissions
chmod 400 ~/.ssh/vockey.pem

# Test SSH connection
ssh -i ~/.ssh/vockey.pem ubuntu@
```

**Problem:** Apt repository errors
```bash
# Update cache manually on target server
ssh -i ~/.ssh/vockey.pem ubuntu@
sudo apt update
```

### Jenkins Issues

**Problem:** Shared library not found
```bash
# Verify library configuration in Jenkins
# Check library path: jenkins-shared-library
# Verify vars/ directory exists
```

**Problem:** Pipeline fails
```bash
# Check Jenkins logs
# Verify credentials (Docker Hub, GitHub)
# Ensure Docker is installed on Jenkins server
```

### ArgoCD Issues

**Problem:** Application out of sync
```bash
# Manual sync
kubectl patch application flask-app -n argocd --type merge -p '{"spec":{"source":{"targetRevision":"main"}}}'

# Or use UI: Click SYNC
```

**Problem:** Can't access UI
```bash
# Restart port-forward
kubectl port-forward svc/argocd-server -n argocd 8081:443
```

---

## 🧹 Cleanup

### Stop All Services
```bash
# Stop ArgoCD port-forward (Ctrl+C in terminal)

# Delete Kubernetes resources
kubectl delete namespace ivolve
kubectl delete namespace argocd

# Stop Minikube
minikube stop

# Destroy Terraform infrastructure
cd ~/CloudDevOpsProject/terraform
terraform destroy
```

### Remove Local Docker Images
```bash
docker rmi flask-app:v1.0
docker rmi nourhanosama/flask-app:v1.0
```

---

## 📚 Learning Resources

- **Docker:** https://docs.docker.com/
- **Kubernetes:** https://kubernetes.io/docs/
- **Terraform:** https://developer.hashicorp.com/terraform/docs
- **Ansible:** https://docs.ansible.com/
- **Jenkins:** https://www.jenkins.io/doc/
- **ArgoCD:** https://argo-cd.readthedocs.io/

---

## 👤 Author

**Nourhan Osama**

- GitHub: [@nourhanosma47](https://github.com/nourhanosma47)
- Project Repository: [CloudDevOpsProject](https://github.com/nourhanosma47/CloudDevOpsProject)

---

## 📝 Notes

- This project was created for educational purposes as part of DevOps training
- All sensitive credentials should be stored securely and never committed to Git
- AWS resources incur costs - remember to destroy infrastructure when not in use
- Screenshots are essential for project documentation and evaluation

---

*End of README.md*
