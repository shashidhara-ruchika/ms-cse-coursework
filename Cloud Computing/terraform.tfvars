credentials_file_path = ".terraform/virtual-rarity-414323-bfce42d9dd58.json"
project_id            = "virtual-rarity-414323"
region                = "us-west4"
replica               = 1

service_account = {
  account_id                   = "vm-service-account",
  display_name                 = "VM Service Account",
  create_ignore_already_exists = true,
}

service_agents = {
  compute_engine_service_agent = "service-951548181877@compute-system.iam.gserviceaccount.com",
  cloud_storage_service_agent  = "service-951548181877@gs-project-accounts.iam.gserviceaccount.com"
}

roles = {
  logging_admin_role            = "roles/logging.admin",
  monitoring_metric_writer_role = "roles/monitoring.metricWriter"

  pubsub_publisher_role              = "roles/pubsub.publisher"
  service_account_token_creator_role = "roles/iam.serviceAccountTokenCreator"

  cloud_functions_developer_role = "roles/cloudfunctions.developer"
  cloud_run_invoker_role         = "roles/run.invoker"

  artifact_registry_create_on_push_writer = "roles/artifactregistry.createOnPushWriter"
  storage_object_admin_role               = "roles/storage.objectAdmin"
  logs_writer_role                        = "roles/logging.logWriter"

  crypto_key_encrypter_decrypter = "roles/cloudkms.cryptoKeyEncrypterDecrypter"
}

vpc = {
  name                    = "vpc"
  auto_create_subnetworks = false
  delete_default_routes   = true
  routing_mode            = "REGIONAL"
}

vpc_subnet_webapp = {
  name          = "webapp"
  ip_cidr_range = "10.0.1.0/24"
}

vpc_subnet_db = {
  name                            = "db"
  ip_cidr_range                   = "10.0.2.0/24"
  enable_private_ip_google_access = true
}

vpc_webapp_route = {
  name             = "webapp-route"
  dest_range       = "0.0.0.0/0"
  next_hop_gateway = "default-internet-gateway"
}

private_ip_address = {
  name                         = "private-ip-address"
  global_address_address_type  = "INTERNAL"
  global_address_purpose       = "VPC_PEERING"
  global_address_prefix_length = 24
}

private_vpc_connection = {
  google_service_nw_connection_service = "servicenetworking.googleapis.com"

}

serverless_vpc_access = {
  name               = "serverless-connector"
  ip_cidr_range      = "10.0.3.0/28"
  machine_type       = "e2-micro"
  minimum_instances  = 2
  maximum_instances  = 8
  maximum_throughput = 300
}

firewall_allow = {
  firewall_allow_protocol = "tcp"
  firewall_allow_ports    = ["22"]
  firewall_allow_priority = 1000
}

firewall_deny = {
  firewall_deny_priority = 2000
}

firewall_load_balancer_allow = {
  firewall_load_balancer_allow_protocol = "tcp"
  firewall_load_balancer_allow_ports    = ["8080", "22"]
  firewall_load_balancer_allow_priority = 1000
  source_ranges                         = ["130.211.0.0/22", "35.191.0.0/16"]
}

dns_record = {
  domain_name           = "ruchikashashidhara.me."
  managed_zone_dns_name = "csye6225-zone"
  ttl                   = 300
  type                  = "A"
}

compute_engine = {
  compute_engine_webapp_tag   = "webapp-server"
  compute_engine_machine_type = "e2-medium"
  compute_engine_machine_zone = "us-west4-b"
  # boot_disk_image                          = "csye6225-webapp-1712112215"
  boot_disk_image                          = "csye6225-webapp-image-1712758399"
  boot_disk_type                           = "pd-balanced"
  boot_disk_size                           = 100
  compute_engine_allow_stopping_for_update = true
  compute_engine_service_account_scopes    = ["userinfo-email", "compute-ro", "storage-ro", "logging-write", "monitoring-write", "pubsub", "cloud-platform"]
  can_ip_forward                           = false
  disk_auto_delete                         = true
  boot_disk                                = true
  reservation_affinity_type                = "ANY_RESERVATION"
  scheduling_automatic_restart             = true
  scheduling_preemptible                   = false
}

database = {
  name                      = "webapp-cloudsql-instance"
  database_version          = "POSTGRES_15"
  region                    = "us-west4"
  deletion_protection       = false
  tier                      = "db-f1-micro"
  availability_type         = "REGIONAL"
  disk_type                 = "pd-ssd"
  disk_size                 = 100
  ipv4_enabled              = false
  enabled_private_path      = true
  database_name             = "webapp"
  password_length           = 16
  password_includes_special = true
  password_override_special = "-_"
  database_user             = "webapp"
  root_password             = "password"

}

pubsub_verify_email = {

  schema = {
    name       = "verify_email_schema"
    type       = "AVRO"
    definition = "{\n  \"type\" : \"record\",\n  \"name\" : \"Avro\",\n  \"fields\" : [\n    {\n      \"name\" : \"token\",\n      \"type\" : \"string\"\n    },\n    {\n      \"name\" : \"email\",\n      \"type\" : \"string\"\n    }\n  ]\n}\n"
  }

  topic = {
    name                       = "verify_email"
    message_retention_duration = "604800s"
    schema_settings_encoding   = "JSON"

  }

  subscription = {
    name = "verify_email_subscription"
  }

}

cloud_function = {
  name        = "servless-send-verify-email-function"
  description = "Cloud Function to send email"

  build_config = {
    entry_point   = "sendVerifyEmail"
    runtime       = "nodejs20"
    source_bucket = "csye6225-ruchika"
    source_object = "serverless.zip"
  }

  service_config = {
    environment_variables = {
      MAILGUN_API_KEY             = "<mailgun-api.key>"
      MAILGUN_DOMAIN              = "ruchikashashidhara.me"
      MAILGUN_FROM                = "Ruchika Shashidhara <postmaster@ruchikashashidhara.me>"
      VERIFY_EMAIL_LINK           = "https://ruchikashashidhara.me/v1/user/verify"
      VERIFY_EMAIL_EXPIRY_SECONDS = 120
    }
    timeout_seconds                  = 60
    available_memory                 = "4Gi"
    max_instance_request_concurrency = 1
    min_instance_count               = 1
    max_instance_count               = 10
    available_cpu                    = 2
    ingress_settings                 = "ALLOW_INTERNAL_ONLY"
    vpc_connector_egress_settings    = "PRIVATE_RANGES_ONLY"
    all_traffic_on_latest_revision   = true
  }

  event_trigger = {
    event_type   = "google.cloud.pubsub.topic.v1.messagePublished"
    resource     = "projects/virtual-rarity-414323/topics/verify_email"
    retry_policy = "RETRY_POLICY_RETRY"
  }
}

health_check = {
  name                = "webapp-health-check"
  check_interval_sec  = 5
  timeout_sec         = 5
  healthy_threshold   = 2
  unhealthy_threshold = 2
  port_name           = "http"
  request_path        = "/healthz"
  port                = "8080"
}

webapp_instance_group_manager = {
  name                                 = "webapp-instance-group-manager"
  base_instance_name                   = "webapp"
  description                          = "Webapp Instance Group Manager"
  distribution_policy_zones            = ["us-west4-a", "us-west4-b", "us-west4-c"]
  distribution_policy_target_shape     = "EVEN"
  life_cycle_create_before_destroy     = true
  auto_healing_policy_inital_delay_sec = 100
  force_update_on_repair               = "YES"
  default_action_on_failure            = "REPAIR"
}

load_balancer = {
  name                  = "webapp-load-balancer"
  protocol              = "HTTP"
  port_name             = "http"
  load_balancing_scheme = "EXTERNAL"
  timeout_sec           = 10
  enable_cdn            = true
  balancing_mode        = "UTILIZATION"
  capacity_scaler       = 1.0
  locality_lb_policy    = "ROUND_ROBIN"
}

auto_scaler = {
  name                   = "webapp-auto-scaler"
  max_repliacs           = 4
  min_replicas           = 1
  cooldown_period        = 60
  cpu_utilization_target = 0.05
}

ssl_certificates = ["projects/virtual-rarity-414323/global/sslCertificates/webapp-ssl-certificate"]

webapp_forwarding_rule = {
  name                  = "webapp-forwarding-rule"
  ip_protocol           = "TCP"
  load_balancing_scheme = "EXTERNAL"
  port_range            = "443"
}

sqladmin_service_identity_account_service = "sqladmin.googleapis.com"

key_ring = {
  name                      = "webapp-key-ring"
  length                    = 8
  special_characters        = false
  lifecycle_prevent_destroy = false
  rotation_period           = "2592000s"
}

bucket = {
  force_destroy            = true
  public_access_prevention = "enforced"
  source                   = "./.terraform/serverless.zip"
}


