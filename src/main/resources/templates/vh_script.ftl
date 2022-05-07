#!/bin/bash
sudo mkdir -p /var/www/${domainName}/public_html
sudo cp index.html /var/www/${domainName}/public_html/index.html
sudo chown -R $USER:$USER /var/www/${domainName}/public_html
sudo chmod -R 755 /var/www
sudo cp ${domainName}.conf /etc/apache2/sites-available/${domainName}.conf
sudo a2ensite ${domainName}.conf
sudo /etc/init.d/apache2 restart