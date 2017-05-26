# -*- mode: ruby -*-
# vi: set ft=ruby :

# Specify Vagrant version and Vagrant API version
Vagrant.require_version ">= 1.6.0"
VAGRANTFILE_API_VERSION = "2"
ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'

# Create and configure the Docker container(s)
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  [:up, :reload].each do |cmd|
    config.trigger.before cmd, :vm => ["order", "kitchen", "delivery"] do
      run "gradle build -p services/#{@machine.name}"
    end
  end

  # Disable synced folders for the Docker container
  # (prevents an NFS error on "vagrant up")
  #config.vm.synced_folder ".", "/vagrant", disabled: true

  config.vm.define "lb" do |lb|
    # Configure the Docker provider for Vagrant
    lb.vm.provider "docker" do |docker|

      # Define the location of the Vagrantfile for the host VM
      # Comment out this line to use default host VM that is
      # based on boot2docker
      docker.vagrant_vagrantfile = "host/Vagrantfile"
      docker.vagrant_machine = 'docker-host'
      docker.create_args = ['--network=pizza']

      docker.remains_running = true

      # Maps host share to container path
      docker.volumes = ["/var/log/apache2:/var/log/apache2"]

      # Specify the Docker image to use
      docker.build_dir = "lb/"

      # Specify port mappings
      # If omitted, no ports are mapped!
      docker.ports = ['80:80', '443:443']

      # Specify a friendly name for the Docker container
      docker.name = 'lb'
    end
  end

  config.vm.define "order" do |order|

    # Configure the Docker provider for Vagrant
    order.vm.provider "docker" do |docker|

      # Define the location of the Vagrantfile for the host VM
      # Comment out this line to use default host VM that is
      # based on boot2docker
      docker.vagrant_vagrantfile = "host/Vagrantfile"
      docker.vagrant_machine = 'docker-host'
      docker.create_args = ['--network=pizza']

      docker.remains_running = true

      # Maps host share to container path
      docker.volumes = ["/var/log/order:/var/log/order"]

      # Specify the Docker image to use
      docker.build_dir = "services/order/"

      # Specify port mappings for debug
      # If omitted, no ports are mapped!
      docker.ports = ['5000:5000']

      # Specify a friendly name for the Docker container
      docker.name = 'order'
    end
  end

  config.vm.define "kitchen" do |kitchen|

    # Configure the Docker provider for Vagrant
    kitchen.vm.provider "docker" do |docker|

      # Define the location of the Vagrantfile for the host VM
      # Comment out this line to use default host VM that is
      # based on boot2docker
      docker.vagrant_vagrantfile = "host/Vagrantfile"
      docker.vagrant_machine = 'docker-host'
      docker.create_args = ['--network=pizza']

      docker.remains_running = true

      # Maps host share to container path
      docker.volumes = ["/var/log/kitchen:/var/log/kitchen"]

      # Specify the Docker image to use
      docker.build_dir = "services/kitchen/"

      # Specify port mappings for debug
      # If omitted, no ports are mapped!
      docker.ports = ['5001:5001']

      # Specify a friendly name for the Docker container
      docker.name = 'kitchen'
    end
  end

  config.vm.define "delivery" do |delivery|

    # Configure the Docker provider for Vagrant
    delivery.vm.provider "docker" do |docker|

      # Define the location of the Vagrantfile for the host VM
      # Comment out this line to use default host VM that is
      # based on boot2docker
      docker.vagrant_vagrantfile = "host/Vagrantfile"
      docker.vagrant_machine = 'docker-host'
      docker.create_args = ['--network=pizza']

      docker.remains_running = true

      # Maps host share to container path
      docker.volumes = ["/var/log/delivery:/var/log/delivery"]

      # Specify the Docker image to use
      docker.build_dir = "services/delivery/"

      # Specify port mappings for debug
      # If omitted, no ports are mapped!
      docker.ports = ['5002:5002']

      # Specify a friendly name for the Docker container
      docker.name = 'delivery'
    end
  end

  config.vm.define "messages" do |messages|

    # Configure the Docker provider for Vagrant
    messages.vm.provider "docker" do |docker|

      # Define the location of the Vagrantfile for the host VM
      # Comment out this line to use default host VM that is
      # based on boot2docker
      docker.vagrant_vagrantfile = "host/Vagrantfile"
      docker.vagrant_machine = 'docker-host'
      docker.create_args = ['--network=pizza', '--hostname=messages']

      docker.remains_running = true

      # Specify the Docker image to use
      docker.image = "rabbitmq"

      # Specify port mappings
      # If omitted, no ports are mapped!
      docker.ports = ['5672:5672', '15672:15672']

      # Specify a friendly name for the Docker container
      docker.name = 'messages'
    end
  end
end
