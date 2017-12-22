Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.provider "virtualbox" do |v|
    v.memory = 4048
    v.cpus = 2
  end
  config.vm.network "forwarded_port", guest: 18080, host: 18080
  config.vm.network "forwarded_port", guest: 4000, host: 4000
end
