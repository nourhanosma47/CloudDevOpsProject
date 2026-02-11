output "vpc_id" {
  description = "VPC ID"
  value       = module.network.vpc_id
}

output "jenkins_public_ip" {
  description = "Jenkins server public IP"
  value       = module.server.jenkins_public_ip
}

output "jenkins_instance_id" {
  description = "Jenkins instance ID"
  value       = module.server.jenkins_instance_id
}
